/**
 * @author Fernando Moraes Oliveira
 * Matéria 4716 - Engenharia de Software 2
 * 3º ADS - Tarde
 * Iniciado em 04/05/2016
 */

package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Despesas;
import persistence.DespesasCondominioDao;
import view.TelaDespesaCondominio;
import view.TelaMenu;

public class DespesasCondominioController implements ComponentListener {

	private TelaDespesaCondominio janela; 
	private JPanel painel; 
	private JTable tabela;
	private JLabel lblDtVenc;
	private JLabel lblVlr; 
	private JLabel lblId; 
	private JLabel lblDtReg;
	private JLabel lblDtAlt;
	private JTextField txtId; 
	private JTextField txtReferencia; 
	private JTextField txtDespesa; 
	private JFormattedTextField ftxtDtVenc; 
	private JFormattedTextField ftxtDtReg;
	private JFormattedTextField ftxtDtAlt;
	private JFormattedTextField ftxtValor;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtVlrTotal;
	private JComboBox<String> cboReferencia; 
	private JComboBox<String> cboDespesa; 
	private JButton btnPesquisar; 
	private JButton btnLimpar; 
	private JButton btnEditar; 
	private JButton btnExcluir; 
	private JButton btnSalvar; 
	private JButton btnCancelar;
	private boolean validar;
	private String diretorio = "../projects/";
	private List<Despesas> despesas;
	private DespesasCondominioDao dao = new DespesasCondominioDao();

	public DespesasCondominioController( 
			TelaDespesaCondominio janela, 
			JPanel painel, 
			JTable tabela, 
			JLabel lblDtVenc, 
			JLabel lblVlr, 
			JLabel lblId, 
			JLabel lblDtReg, 
			JLabel lblDtAlt, 
			JTextField txtId, 
			JTextField txtReferencia, 
			JTextField txtDespesa, 
			JFormattedTextField ftxtDtVenc, 
			JFormattedTextField ftxtDtReg, 
			JFormattedTextField ftxtDtAlt,
			JFormattedTextField ftxtValor, 
			JFormattedTextField ftxtQtd, 
			JFormattedTextField ftxtVlrTotal, 
			JComboBox<String> cboReferencia, 
			JComboBox<String> cboDespesa, 
			JButton btnPesquisar, 
			JButton btnLimpar, 
			JButton btnEditar, 
			JButton btnExcluir, 
			JButton btnSalvar, 
			JButton btnCancelar ) {
			
		this.janela = janela;
		this.painel = painel;
		this.tabela = tabela;
		this.lblDtVenc = lblDtVenc;
		this.lblVlr = lblVlr;
		this.lblId = lblId;
		this.lblDtReg = lblDtReg;
		this.lblDtAlt = lblDtAlt;
		this.txtId = txtId;
		this.txtReferencia = txtReferencia;
		this.txtDespesa = txtDespesa;
		this.ftxtDtVenc = ftxtDtVenc;
		this.ftxtDtReg = ftxtDtReg;
		this.ftxtDtAlt = ftxtDtAlt;
		this.ftxtValor = ftxtValor;
		this.ftxtQtd = ftxtQtd;
		this.ftxtVlrTotal = ftxtVlrTotal;
		this.cboReferencia = cboReferencia;
		this.cboDespesa = cboDespesa;
		this.btnPesquisar = btnPesquisar;
		this.btnLimpar = btnLimpar;
		this.btnEditar = btnEditar;
		this.btnExcluir = btnExcluir;
		this.btnSalvar = btnSalvar;
		this.btnCancelar = btnCancelar;
		this.despesas = new ArrayList<Despesas>();

		iniciar();
	}
	

	public void iniciar(){
		carregarDespesas();
		preencherReferencia();
		preencherDespesa();
		
		alterarCampos ("protegerCampos");
		formatarTabela( despesas );
	}


	public String obterDataAtual(){
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String data = (sdf.format(date));
		return data;
	}
	
	
	public String obterMesRef( String data ){

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		try {
			dt = sdf.parse( data );
		} catch (ParseException e) {
			msg( "erro", e.getMessage() );
		}
		DateFormat ref = new SimpleDateFormat("MMMM/yyyy");
		String mesRef = (ref.format(dt).toUpperCase());
		return mesRef;
	}
	

	public void focarCampo(){

		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				ftxtDtVenc.requestFocus();  
			}  
		});
	}


	public void atualizarTotal( List<Despesas> lista ){
		
		float totalVlr = 0;
		int qtd = 1;
		for( int i = 0; i < lista.size(); i++ ){
			if ( cboReferencia.getSelectedItem() == "Todos os Meses" ||
					obterMesRef(lista.get(i).getDtVencimento() ).equals( cboReferencia.getSelectedItem()) ){
			
			totalVlr = totalVlr + ( lista.get(i).getValor() );
		
		ftxtQtd.setValue( Integer.toString ( qtd++ ));
		ftxtVlrTotal.setValue( totalVlr );
			}
		}
	}
	
	
	public void alterarCampos ( String opt ){
		
		switch ( opt ){

		case "novo":
			
			cboDespesa.setVisible(true);
			tabela.setEnabled(false);
			tabela.setForeground(Color.GRAY);
			tabela.clearSelection();
			btnEditar.setVisible(false);
			btnExcluir.setEnabled(false);
			break;
			
		case "protegerCampos":
			
			tabela.setEnabled(true);
			tabela.setForeground(Color.black);
			lblDtVenc.setVisible(false);
			lblVlr.setVisible(false);
			lblId.setVisible(false);
			lblDtReg.setVisible(false);
			lblDtAlt.setVisible(false);
			txtReferencia.setVisible(false);
			txtDespesa.setVisible(true);
			txtId.setVisible(false);
			ftxtDtVenc.setVisible(false);
			ftxtValor.setVisible(false);
			ftxtDtReg.setVisible(false);
			ftxtDtAlt.setVisible(false);
			cboReferencia.setVisible(true);
			cboDespesa.setVisible(false);
			btnPesquisar.setVisible(true);
			btnLimpar.setText("Novo");
			btnCancelar.setEnabled(false);
			btnEditar.setText("Editar");
			btnEditar.setVisible(true);
			btnExcluir.setEnabled(false);
			btnSalvar.setVisible(false);
			break;

		case "desprotegerCampos":
			
			lblDtVenc.setVisible(true);
			lblVlr.setVisible(true);
			lblId.setVisible(true);
			lblDtReg.setVisible(false);
			lblDtAlt.setVisible(false);
			txtReferencia.setVisible(true);
			txtDespesa.setVisible(false);
			txtId.setVisible(true);
			ftxtDtVenc.setVisible(true);
			ftxtValor.setVisible(true);
			ftxtDtReg.setVisible(false);
			ftxtDtAlt.setVisible(false);
			cboReferencia.setVisible(false);
			cboDespesa.setVisible(true);
			btnPesquisar.setVisible(false);
			btnLimpar.setText("Limpar");
			btnCancelar.setEnabled(true);
			btnEditar.setText("Salvar");
			btnEditar.setVisible(true);
			btnExcluir.setEnabled(true);
			btnSalvar.setVisible(true);
			break;		
		}
	}
	
	
	public void limparCampos(){

		for (Component p : painel.getComponents()) {
			if ( p instanceof JTextField ) {
				JTextField l = ( JTextField )p;
				l.setText(null);
			}
			if ( p instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )p;
				l.setValue(null);
			}
			if ( p instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )p;
				l.setText(null);
			}
		}

			btnCancelar.setEnabled(true);
			btnSalvar.setVisible(true);
			btnEditar.setVisible(false);
			btnExcluir.setEnabled(false);
	}
	
	
	public void preencherReferencia(){

		String [] ordena = new String[despesas.size()];
		ArrayList<String> referencias = new ArrayList<>();
		for ( int i = 0; i < despesas.size(); i++ ){
			ordena[i] = despesas.get(i).getDtVencimento();
		}
		Arrays.sort( ordena );
		for ( int i = 0; i < ordena.length; i++ ){
			referencias.add(i, obterMesRef( ordena[i].toString() ));
		}
		for ( int i = 0; i < referencias.size(); i++ ){
			Object a = referencias.get(i);
			for (int j = i+1; j < referencias.size(); j++) {
				Object b = referencias.get(j);
				if (a.equals(b)) {
					referencias.remove(j);
					j--;
				}
			}
		}
		cboReferencia.addItem( "Todos os Meses" );
		for ( int i = 0; i < referencias.size(); i++ ) {
			cboReferencia.addItem( referencias.get(i) );
		}
	}
	
	
	public void preencherDespesa(){
		String [] ordena = new String[despesas.size()];
		ArrayList<String> d = new ArrayList<>();
		for ( int i = 0; i < despesas.size(); i++ ){
			ordena[i] = despesas.get(i).getDespesa();
		}
		Arrays.sort( ordena );
		for ( int i = 0; i < ordena.length; i++ ){
			d.add(i, ordena[i].toString() );
		}
		for ( int i = 0; i < d.size(); i++ ){
			Object a = d.get(i);
			for (int j = i+1; j < d.size(); j++) {
				Object b = d.get(j);
				if (a.equals(b)) {
					d.remove(j);
					j--;
				}			
			}
			cboDespesa.addItem( d.get(i) );
		}
		cboDespesa.addItem( ">>> NOVA DESPESA" );
	}


	public void formatarTabela( List<Despesas> lista ){

		List<String[]> linhas = new ArrayList<>(); 

		if(lista != null){
			DecimalFormat formato = new DecimalFormat("#,##0.00");
			for ( int i = 0; i < lista.size(); i++ ) {
				if ( cboReferencia.getSelectedItem() == "Todos os Meses" ||
						obterMesRef( lista.get(i).getDtVencimento()).equals( cboReferencia.getSelectedItem()) ){
				String[] item = { 
						Integer.toString ( lista.get(i).getId() ), 
						lista.get(i).getDespesa(), 
						obterMesRef( lista.get(i).getDtVencimento() ), 
						lista.get(i).getDtVencimento(), 
						formato.format( lista.get(i).getValor() ),  
				};
				linhas.add(item);
				}
			}
		} else {
			msg("", "Problema ao carregar a Base de Dados!");
		}

		((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		String[] nomesColunas = { "","Despesa", "Referência", "Data de Vencimento", "Valor", "Selecionar"};

		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[ linhas.size() ][]), nomesColunas)
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false, false, false, true   
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tabela.setModel(model);

		tabela.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(1).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(5).setCellRenderer(centralizado);

		tabela.getColumnModel().getColumn(0).setMinWidth(0);
		tabela.getColumnModel().getColumn(0).setMaxWidth(0);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(350);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(5).setMinWidth(0);
		tabela.getColumnModel().getColumn(5).setMaxWidth(0);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(0);

		atualizarTotal( lista );
	}
	
	
	public void selecionarTabLinha(){

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
			validar = true;
			this.cancelar.actionPerformed(null);
		} else {
			if( tabela.getRowCount() > 0 ){
				for( int i = 0; i < despesas.size(); i ++ ){
					if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString() )
							.equals(Integer.toString(despesas.get(i).getId() ))){

						txtId.setText( Integer.toString( despesas.get(i).getId() ));
						txtDespesa.setText( despesas.get(i).getDespesa() );
						ftxtValor.setValue( despesas.get(i).getValor() );
						ftxtDtVenc.setText( despesas.get(i).getDtVencimento() ) ;
						txtReferencia.setText( obterMesRef( despesas.get(i).getDtVencimento() ));
					}
				}
			}
			cboDespesa.setSelectedItem( txtDespesa.getText() );
			validar = false;
		}
	}
	
	
	public void removerTabLinha(){

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
		} else {
			if( tabela.getRowCount() > 0 ){
				(( DefaultTableModel ) tabela.getModel()).removeRow(tabela.getSelectedRow());
				tabela.updateUI();
				atualizarTotal( despesas );
			}
		}
	}

	
	public void carregarDespesas(){
		
		try {
			despesas = dao.consultaDespesas();
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}

	
	public void pesquisar(){

		ArrayList<Despesas> lista = new ArrayList<>();

		if ( !txtDespesa.getText().isEmpty() ) {
			cboReferencia.setSelectedIndex(0);
			for ( int i = 0; i < despesas.size(); i++ ) {
				boolean filtro = despesas.get(i).getDespesa().toLowerCase()
						.contains( txtDespesa.getText().toLowerCase() );

				if ( filtro == true ) {

					Despesas d = new Despesas();
					d.setId( despesas.get(i).getId() );
					d.setDespesa( despesas.get(i).getDespesa() );
					d.setValor( despesas.get(i).getValor() );
					d.setDtVencimento( despesas.get(i).getDtVencimento() );
					lista.add( d );
					validar = true;
				} else {
					if ( i == despesas.size()-1 ){
						if ( validar != true ){
							msg( "vazioPesquisa", txtDespesa.getText() );
						}
					}
				}
			}
			if ( validar == true){
				formatarTabela( lista );
				validar = false;
			}
		} else {
			msg( "erroPesquisa", txtDespesa.getText() );
			formatarTabela( despesas );
		}
	}

	
	public void editar() {
		
		for( int i = 0; i < despesas.size(); i ++ ){
			if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
					.equals(Integer.toString(despesas.get(i).getId()))){			
				msg( "confirmaEditar", txtDespesa.getText() );
				if (validar != false){
					Despesas despesa = new Despesas();
					despesa.setId( Integer.parseInt( txtId.getText() ));
					despesa.setDespesa( txtDespesa.getText() );
					despesa.setValor( Float.parseFloat( ftxtValor.getValue().toString() ));
					despesa.setDtVencimento( ftxtDtVenc.getText() );
					try {
						dao.atualizaDespesa( despesa );
						despesas.set( i, despesa );
						msg( "editar", txtDespesa.getText() );
					} catch (SQLException e) {
						msg( "erro", e.getMessage() );
					}
				}
				validar = false;
			}
		}
	}
	
	
	public void salvar() {
		
		if ( !txtDespesa.getText().isEmpty() &&
				!ftxtDtVenc.getText().isEmpty() &&
				!ftxtValor.getText().isEmpty() ){			
			Despesas despesa = new Despesas();
			despesa.setDespesa( txtDespesa.getText() );
			despesa.setValor( Float.parseFloat( ftxtValor.getValue().toString() ));
			despesa.setDtVencimento( ftxtDtVenc.getText() );
			try {
				dao.insereDespesa( despesa );
				despesas.add( despesa );
				msg( "salvar", txtDespesa.getText() );
			} catch (SQLException e) {
				msg( "erro", e.getMessage() );
			}
		} else {
			msg( "erroVazio", "" );
		}
	}
	
	
	public void excluir() {

		for( int i = 0; i < despesas.size(); i ++ ){
			if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
					.equals(Integer.toString(despesas.get(i).getId()))){
				msg( "confirmaExcluir", txtDespesa.getText() );
				if (validar != false){
					Despesas despesa = new Despesas();
					despesa.setId( despesas.get(i).getId() );
					despesa.setDespesa( despesas.get(i).getDespesa() );
					despesa.setValor( despesas.get(i).getValor() );
					despesa.setDtVencimento( despesas.get(i).getDtVencimento() );
					try {
						dao.excluiDespesa( despesa );
						despesas.remove(i);
						removerTabLinha();
						msg( "excluir", txtDespesa.getText() );
					} catch (SQLException e) {
						msg( "erro", e.getMessage() );
					}
				}
				validar = false; 
			}
		}
	}


	public void msg( String tipo, String mensagem ) {
		
		janela.setAlwaysOnTop ( false );

		switch ( tipo ) {

		case "salvar":
			JOptionPane.showMessageDialog(null, 
					"A despesa '" + mensagem + "' foi salva com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/record.png" ));
			break;
			
		case "editar":
			JOptionPane.showMessageDialog(null, 
					"A despesa '" + mensagem + "' foi editada com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/record.png" ));
			break;
		
		case "excluir":
			JOptionPane.showMessageDialog(null, 
					"A despesa '" + mensagem + "' foi excluída com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/record.png" ));
			break;
		
		case "confirmaEditar":
			Object[] editar = { "Confirmar", "Cancelar" };  
			int ed = JOptionPane.showOptionDialog(null, 
					"Você confirma a edição da despesa '" + mensagem + "' ?",
					"Edição de Despesa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ), editar, editar[1]);
			if (ed == 1) { validar = false; } else { validar = true; }
			break;
			
		case "confirmaExcluir":
			Object[] excluir = { "Confirmar", "Cancelar" };  
			int ex = JOptionPane.showOptionDialog(null, 
					"Você confirma a exclusão da despesa '" + mensagem + "' ?",
					"Exclusão de Despesa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ), excluir, excluir[1]);
			if (ex == 0) { validar = true; } else { validar = false; }
			break;
			
		case "vazioPesquisa":
			Object[] pesquisar = { "Confirmar", "Cancelar" };  
			int pq = JOptionPane.showOptionDialog(null, 
					"ATENÇÃO!\n\nNenhum resultado foi encontrado com: " + mensagem
					+ "\n Gostaria de adicionar como nova Despesa?", 
					"Despesa não Localizada", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ), pesquisar, pesquisar[1]);
			if (pq == 0) { 
				String d = txtDespesa.getText();
				limparCampos();
				this.editar.actionPerformed(null);
				txtDespesa.setText(d);
			} else { return; }
			break;
			
		case "erroPesquisa":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite algo para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
			break;
			
		case "erroSalvar":
			JOptionPane.showMessageDialog(null, 
					"Despesa já cadastrada!\n\n"
							+ "Verifique sua digitação ou escolha um nome de Usuário diferente.",
					"Já Cadastrada", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
			break;
		
		case "erroExcluir":
			JOptionPane.showMessageDialog(null, 
					"A Despesa " + mensagem + " não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
			break;
			
		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"Os campos da nova Despesa têm que estar preenchidos.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
			break;

		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione uma despesa para editar ou excluir.", 
					"Despesa não selecionado…", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/src/resources/error.png" ));
			break;

		case "sistema":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o " + mensagem 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa!", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ), exit, exit[1] );
			if ( fechar == 0 ) { validar = true; } else { validar = false; }
			break;

		default:
			JOptionPane.showMessageDialog(null, 
					mensagem, 
					"Erro no Sistema", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/src/resources/error.png" ));
		}
		janela.setAlwaysOnTop ( true );
	}
	

	public void fechar(){
		if(janela != null)
			janela.dispose();
	}
	
	public ActionListener pesquisar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			pesquisar();
		}
	};
	
	public ActionListener limpar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			limparCampos();
			txtId.setText(null);
			ftxtDtReg.setText( obterDataAtual() );
			ftxtDtAlt.setText( obterDataAtual() );
			atualizarTotal( despesas );
			
			if ( btnLimpar.getText() == "Novo" ){
				alterarCampos ("desprotegerCampos");
				alterarCampos ("novo");
			}
		}
	};
	
	public ActionListener filtrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			formatarTabela( despesas );
		}
	};
	
	public ActionListener adicionar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if ( cboDespesa.getSelectedItem().equals( ">>> NOVA DESPESA" ) ){
				
				cboDespesa.setVisible(false);
				txtDespesa.setVisible(true);
			} else {
				txtDespesa.setText( cboDespesa.getSelectedItem().toString() );
			}
		}
	};

	public ActionListener cancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			txtId.setText( null );
			ftxtDtReg.setText( null );
			ftxtDtAlt.setText( null );
			cboDespesa.setSelectedIndex(0);
			txtDespesa.setText(null);
			alterarCampos ("protegerCampos");
			btnLimpar.setEnabled(true);
			atualizarTotal( despesas );
		}
	};

	public ActionListener editar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( !btnExcluir.isEnabled() ){
				selecionarTabLinha();
				if ( validar != true ){
					alterarCampos ("desprotegerCampos");
					btnLimpar.setEnabled(false);
					focarCampo();
				}
			} else {
				editar();
				limparCampos();
				formatarTabela( despesas );
				alterarCampos ("protegerCampos");                                                                                                                                                                              
			}
		}
	};

	public ActionListener inserir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			salvar();
			limparCampos();
			formatarTabela( despesas );
			alterarCampos ("protegerCampos"); 
		}
	};

	public ActionListener excluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			excluir();
		}
	};
	
	public ActionListener voltar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			TelaMenu menu = new TelaMenu();
			menu.setVisible(true);
			fechar();
		}
	};

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
	
	
	public KeyListener tecla = new KeyListener() {  

		@Override  
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode=e.getKeyCode();

			switch (keyCode) {

			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_ESCAPE:
				fechar();
				break;
			case KeyEvent.VK_DELETE:
				if (  btnExcluir.isEnabled() ){
					excluir();
				}
				break;
			case 8:
				if (  btnExcluir.isEnabled() ){
					excluir();
				}
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};
	
	
	public FocusListener foco = new FocusListener(){

		@Override
		public void focusGained(FocusEvent e) {
			if ( btnEditar.getText() == "Editar" ){
			txtDespesa.setText( null );
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			Object source = e.getSource();

			if( source == ftxtDtVenc && !ftxtDtVenc.getText().contentEquals("  /  /    ") ){
				txtReferencia.setText( obterMesRef( ftxtDtVenc.getText() ) );
			}
		}	
	};
	
	
	public MouseListener clique = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			Object source = e.getSource();
			
			if(e.getClickCount() == 2){

				if ( source == tabela && btnExcluir.isEnabled() ){
					excluir();
				} 
			} else {
				if ( source == tabela && btnExcluir.isEnabled() ){
					selecionarTabLinha();
				}
			}
		}
	};
}
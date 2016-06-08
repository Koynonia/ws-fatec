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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Despesas;
import persistence.CondominioDao;
import view.TelaCondominio;

public class CondominioController implements ComponentListener {

	private TelaCondominio janela; 
	private JPanel painel; 
	private JTable tabela;
	private JLabel lblDtVenc;
	private JLabel lblVlr; 
	private JLabel lblDt;
	private JLabel lblId;
	private JTextField txtId; 
	private JTextField txtReferencia; 
	private JTextField txtDespesa; 
	private JFormattedTextField ftxtDtVenc; 
	private JFormattedTextField ftxtData; 
	private JFormattedTextField ftxtValor;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtVlrTotal;
	private JComboBox<String> cboReferencia; 
	private JButton btnPesquisar; 
	private JButton btnLimpar; 
	private JButton btnEditar; 
	private JButton btnExcluir; 
	private JButton btnSalvar; 
	private JButton btnCancelar;
//	private JCheckBox ok; 
	private boolean validar;
	private String diretorio = "../4716_Condominio/";
	private List<Despesas> despesas;
	private Despesas condominio = new Despesas();
	private CondominioDao dao = new CondominioDao();

	public CondominioController( 
			TelaCondominio janela, 
			JPanel painel, 
			JTable tabela, 
			JLabel lblDtVenc, 
			JLabel lblVlr, 
			JLabel lblDt, 
			JLabel lblId, 
			JTextField txtId, 
			JTextField txtReferencia, 
			JTextField txtDespesa, 
			JFormattedTextField ftxtDtVenc, 
			JFormattedTextField ftxtData, 
			JFormattedTextField ftxtValor, 
			JFormattedTextField ftxtQtd, 
			JFormattedTextField ftxtVlrTotal, 
			JComboBox<String> cboReferencia, 
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
		this.lblDt = lblDt;
		this.lblId = lblId;
		this.txtId = txtId;
		this.txtReferencia = txtReferencia;
		this.txtDespesa = txtDespesa;
		this.ftxtDtVenc = ftxtDtVenc;
		this.ftxtData = ftxtData;
		this.ftxtValor = ftxtValor;
		this.ftxtQtd = ftxtQtd;
		this.ftxtVlrTotal = ftxtVlrTotal;
		this.cboReferencia = cboReferencia;
		this.btnPesquisar = btnPesquisar;
		this.btnLimpar = btnLimpar;
		this.btnEditar = btnEditar;
		this.btnExcluir = btnExcluir;
		this.btnSalvar = btnSalvar;
		this.btnCancelar = btnCancelar;
//		this.ok = new JCheckBox();
		this.despesas = new ArrayList<Despesas>();

		dados();
		tela();
	}
	

	public void dados(){
		try {
			carregarCondominio();
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
		
		preencherRef();
	}


	public void tela(){
		
		alterarCampos ("protegerCampos");
		formatarTabela();
	}


	public String obterData(){
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
		DateFormat ref = new SimpleDateFormat("MMM yyyy");
		String mesRef = (ref.format(dt).toUpperCase());
		return mesRef;
	}
	
	
	public String gerarId(){
		
		String novoId;
		if ( despesas.size() > 0){
			novoId = String.format( "%09d", despesas.get( despesas.size() -1 ).getId() +1 );
		} else {
			novoId = String.format( "%09d", 1 );
		}
		
		return novoId;
	}


	public void atualizarTotal(){
		
		float totalVlr = 0;
		int qtd = 1;
		for( int i = 0; i < despesas.size(); i++ ){
			if ( cboReferencia.getSelectedItem() == "Todos os Meses" ||
					obterMesRef(despesas.get(i).getDtVencimento() ).equals( cboReferencia.getSelectedItem()) ){
			
			totalVlr = totalVlr + ( despesas.get(i).getValor() );
		
		ftxtQtd.setValue( Integer.toString ( qtd++ ));
		ftxtVlrTotal.setValue( totalVlr );
			}
		}
	}
	
	
	public void alterarCampos ( String opt ){
		
		switch ( opt ){

		case "novo":
			
			tabela.setEnabled(false);
			tabela.setForeground(Color.GRAY);
			btnEditar.setVisible(false);
			btnExcluir.setEnabled(false);
			break;
			
		case "protegerCampos":
			
			tabela.setEnabled(true);
			tabela.setForeground(Color.black);
			lblDtVenc.setVisible(false);
			lblVlr.setVisible(false);
			lblDt.setVisible(false);
			lblId.setVisible(false);
			txtReferencia.setVisible(false);
//			txtDespesa.setVisible(false);
			txtId.setVisible(false);
			ftxtDtVenc.setVisible(false);
			ftxtValor.setVisible(false);
			ftxtData.setVisible(false);
			cboReferencia.setVisible(true);
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
			lblDt.setVisible(true);
			lblId.setVisible(true);
			txtReferencia.setVisible(true);
//			txtDespesa.setVisible(true);
			txtId.setVisible(true);
			ftxtDtVenc.setVisible(true);
			ftxtValor.setVisible(true);
			ftxtData.setVisible(true);
			cboReferencia.setVisible(false);
			cboReferencia.setSelectedIndex(0);
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
	
	
	public void preencherRef(){

		String [] ordena = new String[despesas.size()];
		ArrayList<String> referencias = new ArrayList<>();

		for ( int i = 0; i < despesas.size(); i++ ){
			ordena[i] = obterMesRef( despesas.get(i).getDtVencimento() );
		}
		Arrays.sort( ordena );

		for ( int i = 0; i < ordena.length; i++ ){
			referencias.add(i, ordena[i].toString() );
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


	public void formatarTabela(){

		List<String[]> linhas = new ArrayList<>(); 

		if(despesas != null){
			DecimalFormat formato = new DecimalFormat("#,##0.00");
			for ( int i = 0; i < despesas.size(); i++ ) {
				if ( cboReferencia.getSelectedItem() == "Todos os Meses" ||
						obterMesRef(despesas.get(i).getDtVencimento() ).equals( cboReferencia.getSelectedItem()) ){
				String[] item = { 
						Integer.toString ( despesas.get(i).getId() ), 
						despesas.get(i).getDespesa(), 
						obterMesRef( despesas.get(i).getDtVencimento() ), 
						despesas.get(i).getDtVencimento(), 
						formato.format( despesas.get(i).getValor() ), 
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

		String[] nomesColunas = { "","Despesa", "Referência", "Data de Vencimento", "Valor", ""};

		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
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
		tabela.getColumnModel().getColumn(2).setCellRenderer(centralizado);
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

		atualizarTotal();
	}


	public void removeLinha(){

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
		} else {
			if(tabela.getRowCount() > 0){
				msg( "retirar", "" );

				if (validar != false){
					for(int i = 0; i < despesas.size(); i ++){
						if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
								.equals(despesas.get(i).getId())){
							despesas.remove(i);
						}
					}
					validar = false;
//					atualizarDao();

					((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
					tabela.updateUI();
					atualizarTotal();
				} 
			}
		}
	}
	
	
	public void carregarCondominio() throws SQLException{
		
		despesas = dao.consultaDespesas();
	}

	
	public void pesquisar(){
		condominio.setDespesa( txtId.getText() );
		try {
			System.out.println( dao.consultaDespesa( condominio ));
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}

	
	public void editar() {
		msg("Teste","Editar");
	}
	
	
	public void salvar() {
		
		if ( !txtDespesa.getText().isEmpty() &&
				!ftxtDtVenc.getText().isEmpty() &&
				!ftxtValor.getText().isEmpty() ){
			condominio.setDespesa( txtDespesa.getText() );
			condominio.setValor( Float.parseFloat( ftxtValor.getValue().toString() ));
			condominio.setDtVencimento( ftxtDtVenc.getText() );
			condominio.setDtReferencia( obterData() );

			try {
				dao.insereDespesa( condominio );
				msg( "salvar", txtDespesa.getText() );
				limparCampos();
				carregarCondominio();
				formatarTabela();
			} catch (SQLException e) {
				msg( "erro", e.getMessage() );
			}
		} else {
			msg( "erroVazio", "" );
		}
	}
	
	
	public void excluir() {
		for(int i = 0; i < despesas.size(); i ++){
			System.out.println((tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
			if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
					.equals(despesas.get(i).getId())){
				Despesas despesa = new Despesas();
				despesa.setId( despesas.get(i).getId() );
				despesa.setDespesa( despesas.get(i).getDespesa());
				despesa.setValor(despesas.get(i).getValor());
				despesa.setDtVencimento(despesas.get(i).getDtVencimento());
				despesa.setDtReferencia(despesas.get(i).getDtReferencia());
				try {
					dao.excluiDespesa( despesa );
					msg( "excluir", txtDespesa.getText() );
				} catch (SQLException e) {
					msg( "erro", e.getMessage() );
				}
			}
		}
		removeLinha();
	}
	
	public void atualizarDao () {
		condominio.setDespesa( txtId.getText() );
		condominio.setDespesa( txtDespesa.getText() );
		condominio.setValor( Float.parseFloat( ftxtValor.getValue().toString() ));
		condominio.setDtVencimento( ftxtDtVenc.getText() );
		condominio.setDtReferencia( obterData() );	
		try {
			dao.atualizaDespesa( condominio );
			msg( "editar", txtDespesa.getText() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}


	public void msg( String tipo, String mensagem ) {
		janela.setAlwaysOnTop ( false );

		switch ( tipo ) {

		case "salvar":
			JOptionPane.showMessageDialog(null, 
					"A Despesa " + mensagem + " foi salva com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
			
		case "editar":
			JOptionPane.showMessageDialog(null, 
					"A Despesa " + mensagem + " foi editada com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "excluir":
			JOptionPane.showMessageDialog(null, 
					"A Despesa " + mensagem + " foi excluída com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "confirmaEditar":
			Object[] editar = { "Confirmar", "Cancelar" };  
			int ed = JOptionPane.showOptionDialog(null, 
					"Você confirma a edição da Despesa " + mensagem + " ?",
					"Edição de Despesa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), editar, editar[1]);
			if (ed == 1) { validar = false; } else { validar = true; }
			break;
			
		case "confirmaExcluir":
			Object[] excluir = { "Confirmar", "Cancelar" };  
			int ex = JOptionPane.showOptionDialog(null, 
					"Você confirma a exclusão da Despesa " + mensagem + " ?",
					"Exclusão de Despesa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), excluir, excluir[1]);
			if (ex == 1) { validar = true; } else { validar = false; }
			break;
			
		case "vazioPesquisa":
			Object[] pesquisar = { "Confirmar", "Cancelar" };  
			int pq = JOptionPane.showOptionDialog(null, 
					"ATENÇÃO!\n\nNenhum resultado foi encontrado com: " + mensagem
					+ "\n Gostaria de adicionar esta Despesa?", 
					"Despesa não Localizada", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), pesquisar, pesquisar[1]);
			if (pq == 0) { validar = true; } else { validar = false; }
			break;
			
		case "erroPesquisa":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite algo para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroSalvar":
			JOptionPane.showMessageDialog(null, 
					"Despesa já cadastrado!\n\n"
							+ "Verifique sua digitação ou escolha um nome de Usuário diferente.",
					"Já Cadastrado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
		
		case "erroExcluir":
			JOptionPane.showMessageDialog(null, 
					"A Despesa " + mensagem + " não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"Os campos da nova Despesa têm que estar preenchidos.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;

		case "retirar":
			Object[] opt = { "Confirmar", "Cancelar" };
			int retirar = JOptionPane.showOptionDialog(null, mensagem +
					"\n\nDeseja retirar a Despesa da Tabela?",
					"Retirar Despesa…", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/error.png" ), opt, opt[1]);
			if (retirar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;

		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione uma Despesa para retirar.", 
					"Despesa não selecionado…", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "erroDigit":
			JOptionPane.showMessageDialog(null, 
					"Entrada inválida:\n\n" +
							mensagem +
							"\n\nPor favor, entre somente com números.", 
							"Entrada Inválida…", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "sistema":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o " + mensagem 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa!", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), exit, exit[1] );
			if ( fechar == 0 ) {
				validar = true;
			} else {
				validar = false;
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, 
					mensagem, 
					"Erro no Sistema", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
		}
		janela.setAlwaysOnTop ( true );
	}
	

	public void fechar(){
		if(janela != null)
			janela.dispose();
	}

	public void mostrar(){
		if(janela != null)
			janela.setVisible(true);
	}

	public void esconder(){
		if(janela != null)
			janela.setVisible(false);
	}

	public void sair(){
		msg("sistema","Fechamento");
		if(validar == true){
			System.exit(0);
		}
	}
	
	
	public ActionListener pesquisar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			pesquisar();
		}
	};

	public ActionListener alterar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
	
	public ActionListener apagar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			removeLinha();
		}
	};
	
	public ActionListener limpar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			limparCampos();
			ftxtData.setText( obterData() );
			txtId.setText( gerarId() );
			atualizarTotal();
			
			if ( btnLimpar.getText() == "Novo" ){
				alterarCampos ("desprotegerCampos");
				alterarCampos ("novo");
			}
		}
	};
	
	public ActionListener filtrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			formatarTabela();
		}
	};

	public ActionListener cancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ftxtData.setText( "" );
			txtId.setText( "" );
			alterarCampos ("protegerCampos");
			btnLimpar.setEnabled(true);
		}
	};

	public ActionListener editar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( !btnExcluir.isEnabled() ){
				alterarCampos ("desprotegerCampos");
				btnLimpar.setEnabled(false);
			} else {
				editar();
				alterarCampos ("protegerCampos");                                                                                                                                                                               
			}
		}
	};

	public ActionListener salvar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			salvar();
			alterarCampos ("protegerCampos"); 
		}
	};

	public ActionListener excluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			excluir();
			alterarCampos ("protegerCampos");
		}
	};
	
	public ActionListener voltar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
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
				removeLinha();
				break;
			case 8:
				removeLinha();
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

			if(e.getClickCount() == 2){  
				removeLinha();
			}
		}
	};
}
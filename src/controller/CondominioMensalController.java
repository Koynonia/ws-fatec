/**
 * @author Fernando Moraes Oliveira
 * Matéria 4716 - Engenharia de Software 2
 * 3º ADS - Tarde
 * Iniciado em 10/06/2016
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import model.Apartamentos;
import model.Condominio;
import model.Despesas;
import model.Moradores;
import persistence.CondominioMensalDao;
import view.TelaCondominioMensal;
import view.TelaDespesaApartamento;
import view.TelaDespesaCondominio;
import view.TelaMenu;

public class CondominioMensalController implements ComponentListener {

	private TelaCondominioMensal janela; 
	private JPanel painel; 
	private JTable tabela;
	private JLabel lblId; 
	private JLabel lblDtReg;
	private JLabel lblDtAlt;
	private JTextField txtId; 
	private JTextField txtNome; 
	private JTextField txtDespesa; 
	private JFormattedTextField ftxtDtVenc; 
	private JFormattedTextField ftxtDtPagto; 
	private JFormattedTextField ftxtDtReg;
	private JFormattedTextField ftxtDtAlt;
	private JFormattedTextField ftxtMulta;
	private JFormattedTextField ftxtValor;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtVlrTotal;
	private JComboBox<String> cboReferencia; 
	private JComboBox<String> cboApto; 
	private JCheckBox chkMulta; 
	private JButton btnLimpar; 
	private boolean validar;
	private String diretorio = "../projects/";
	private List<Moradores> moradores;
	private List<Apartamentos> apartamentos;
	private List<Despesas> despesas;
	private List<Condominio> condominioMensal;
	private CondominioMensalDao dao = new CondominioMensalDao();

	public CondominioMensalController( 
			TelaCondominioMensal janela, 
			JPanel painel, 
			JTable tabela, 
			JLabel lblId, 
			JLabel lblDtReg, 
			JLabel lblDtAlt, 
			JTextField txtId, 
			JTextField txtNome, 
			JTextField txtDespesa, 
			JFormattedTextField ftxtDtVenc, 
			JFormattedTextField ftxtDtPagto, 
			JFormattedTextField ftxtDtReg, 
			JFormattedTextField ftxtDtAlt,
			JFormattedTextField ftxtMulta, 
			JFormattedTextField ftxtValor, 
			JFormattedTextField ftxtQtd, 
			JFormattedTextField ftxtVlrTotal, 
			JComboBox<String> cboReferencia, 
			JComboBox<String> cboApto, 
			JCheckBox chkMulta, 
			JButton btnLimpar ) {
			
		this.janela = janela;
		this.painel = painel;
		this.tabela = tabela;
		this.lblId = lblId;
		this.lblDtReg = lblDtReg;
		this.lblDtAlt = lblDtAlt;
		this.txtId = txtId;
		this.txtNome = txtNome;
		this.txtDespesa = txtDespesa;
		this.ftxtDtVenc = ftxtDtVenc;
		this.ftxtDtPagto = ftxtDtPagto;
		this.ftxtDtReg = ftxtDtReg;
		this.ftxtDtAlt = ftxtDtAlt;
		this.ftxtMulta = ftxtMulta;
		this.ftxtValor = ftxtValor;
		this.ftxtQtd = ftxtQtd;
		this.ftxtVlrTotal = ftxtVlrTotal;
		this.cboReferencia = cboReferencia;
		this.cboApto = cboApto;
		this.chkMulta = chkMulta;
		this.btnLimpar = btnLimpar;
		this.moradores = new ArrayList<Moradores>();
		this.apartamentos = new ArrayList<Apartamentos>();
		this.despesas = new ArrayList<Despesas>();
		this.condominioMensal = new ArrayList<Condominio>();

		dados();
		tela();
	}
	

	public void dados(){
		
		carregarMoradores();
		carregarAptos();
		carregarDespesasApto();
		carregarDespesasCond();
		carregarCondominioMensal();
		preencherReferencia();
		preencherApto();
	}


	public void tela(){
		
		alterarCampos ("protegerCampos");
		formatarTabela( despesas );
	}


	public void alterarCampos ( String opt ){

		switch ( opt ){

		case "protegerCampos":

			tabela.setEnabled(true);
			tabela.setForeground(Color.black);
			lblId.setVisible(false);
			lblDtReg.setVisible(false);
			lblDtAlt.setVisible(false);
			txtNome.setEnabled(false);
			txtId.setVisible(false);
			ftxtMulta.setEditable(false);
			ftxtValor.setEditable(false);
			ftxtDtReg.setVisible(false);
			ftxtDtAlt.setVisible(false);
			break;	
		}
	}
	
	
	public void focarCampo(){

		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				ftxtDtVenc.requestFocus();  
			}  
		});
	}
	
	
	public void limparCampos(){

		for (Component p : painel.getComponents()) {
			if ( p instanceof JTextField ) {
				JTextField l = ( JTextField )p;
				if ( l.isEnabled() ){
					l.setText(null);
				}
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
	}
	

	public String obterDataProrrogar(String data){
		
		String dtProrrogado = null;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		
		try {
			dt = sdf.parse( data );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if( ftxtDtVenc.getValue() != null && ftxtDtPagto.getValue() != null ){
			Calendar c = Calendar.getInstance();
			 c.setTime( dt );
			 c.set( Calendar.DAY_OF_MONTH, c.get( Calendar.DAY_OF_MONTH ) +30 );
			 dtProrrogado = ( sdf.format( c.getTime() ) );
		} else {
			msg("Erro nas datas", "Existe um erro nas datas!\nVerifique e tente novamente.");
		}

		return dtProrrogado;
	}
	
	
	public String obterData(){
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
	
	
	public void atualizarMensal( List<Despesas> despesa ){

		float condVlr = 0;
		float aptoVlr = 0;
		int apto = apartamentos.size();
		int quartos = 0;
		int qtd = 1;
		
		for( int i = 0; i < apartamentos.size(); i++ ){

			quartos = quartos + apartamentos.get(i).getQuartos();
		}

		for( int i = 0; i < despesa.size(); i++ ){

			if ( cboReferencia.getSelectedItem() == "Todos os Meses" 
					|| obterMesRef(despesa.get(i).getDtVencimento() ).equals( cboReferencia.getSelectedItem()) ){

				if ( despesa.get(i).getIdApto() == 0 ){
					condVlr = condVlr + ( despesa.get(i).getValor() );
				} else {
					aptoVlr = aptoVlr + ( despesa.get(i).getValor() );
				}

				ftxtQtd.setValue( Integer.toString ( qtd++ ));	
			}	
		}

		if( cboApto.getSelectedItem() == "Todos" ){
			condVlr = condVlr / apto;
			aptoVlr = aptoVlr + condVlr;
		} else {
			for( int i = 0; i < apartamentos.size(); i++ ){
				if( cboApto.getSelectedItem().equals( Integer.toString( apartamentos.get(i).getNumero() ))){
					condVlr = condVlr / quartos * apartamentos.get(i).getQuartos();
					aptoVlr = aptoVlr + condVlr;
				}
			}
		}
		
		
		ftxtValor.setValue( aptoVlr );
		ftxtVlrTotal.setValue( aptoVlr );
		
		if( ftxtDtVenc.getValue() != null && ftxtDtPagto.getValue() != null ){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date venc = null;
			Date pagto = null;
			try {
				venc = df.parse((String) ftxtDtVenc.getValue());
				pagto = df.parse((String) ftxtDtPagto.getValue());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(venc.before(pagto)){
				float multa = ( aptoVlr * 2 ) / 100;
				ftxtMulta.setValue( multa );
				ftxtValor.setValue( aptoVlr + multa );
			}
		} else {
//			msg("Erro nas datas", "Existe um erro nas datas!\nVerifique e tente novamente.");
		}
	}
	
	
	public void preencherMorador(){

		if( cboApto.getSelectedItem() == "Todos" ){
			txtNome.setText( null );
		} else {

			for( int i = 0; i < apartamentos.size(); i++ ){
				if( cboApto.getSelectedItem().equals( Integer.toString( apartamentos.get(i).getNumero() ))){
					txtNome.setText( moradores.get( i ).getNome() );
				}
			}
		}
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
	
	
	public void preencherApto(){

		int [] ordena = new int[apartamentos.size()];
		ArrayList<String> apto = new ArrayList<>();

		for ( int i = 0; i < apartamentos.size(); i++ ){
			ordena[i] = apartamentos.get(i).getNumero();
		}
		Arrays.sort( ordena );
		for ( int i = 0; i < ordena.length; i++ ){
			apto.add(i, Integer.toString( ordena[i] ));
		}
		cboApto.addItem( "Todos" );
		for ( int i = 0; i < apto.size(); i++ ){
			Object a = apto.get(i);
			for (int j = i+1; j < apto.size(); j++) {
				Object b = apto.get(j);
				if (a.equals(b)) {
					apto.remove(j);
					j--;
				}			
			}
			cboApto.addItem( apto.get(i) );
		}
	}


	public void formatarTabela( List<Despesas> lista ){

		List<String[]> linhas = new ArrayList<>(); 

		if(lista != null){
			DecimalFormat formato = new DecimalFormat("#,##0.00");
			for ( int i = 0; i < lista.size(); i++ ) {
				if ( cboReferencia.getSelectedItem() == "Todos os Meses" && cboApto.getSelectedItem() == "Todos" 
						|| obterMesRef( lista.get(i).getDtVencimento()).equals( cboReferencia.getSelectedItem()) 
						){
					
					String numApto = null;
					if ( lista.get(i).getIdApto() == 0 ){
						numApto = "";
					} else {
						numApto = Integer.toString( apartamentos.get(i).getNumero() );
					}
					
				String[] item = { 
						Integer.toString ( lista.get(i).getId() ), 
						lista.get(i).getDespesa(), 
						numApto, 
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

		String[] nomesColunas = { "","Despesa", "Apartamento", "Referência", "Data de Vencimento", "Valor", "Selecionar"};

		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[ linhas.size() ][]), nomesColunas)
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false, false, false, false, true   
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
		tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(5).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(6).setCellRenderer(centralizado);

		tabela.getColumnModel().getColumn(0).setMinWidth(0);
		tabela.getColumnModel().getColumn(0).setMaxWidth(0);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(350);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(6).setMinWidth(0);
		tabela.getColumnModel().getColumn(6).setMaxWidth(0);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(0);

		if ( cboApto.getSelectedItem() == "Todos" ){
			atualizarTotal( lista );
		} else {
			atualizarMensal( lista );
		}
	}
	
	
	public void selecionarTabLinha() throws ParseException{

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
			validar = true;
			this.cancelar.actionPerformed(null);
		} else {
			if( tabela.getRowCount() > 0 ){
				for( int i = 0; i < despesas.size(); i ++ ){
					if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString() )
							.equals(Integer.toString(despesas.get(i).getId() ))){
						if( (String) tabela.getModel().getValueAt(tabela.getSelectedRow(), 2) == "" ){
							TelaDespesaCondominio despesasCond = new TelaDespesaCondominio();
							despesasCond.setVisible(true);
						} else {
							TelaDespesaApartamento despesasApto = new TelaDespesaApartamento();
							despesasApto.setVisible(true);
						}
						fechar();
					}
				}
			}
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
				if ( cboApto.getSelectedItem() == "Todos" ){
					atualizarTotal( despesas );
				} else {
					atualizarMensal( despesas );
				}
			}
		}
	}

	
	public void carregarCondominioMensal(){
		
		try {
			condominioMensal = dao.consultaCondominioMensal();
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	public void carregarMoradores(){

		try {
			moradores.addAll( dao.consultaMoradores() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	public void carregarAptos(){

		try {
			apartamentos.addAll( dao.consultaAptos() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	public void carregarDespesasApto(){

		try {
			despesas.addAll( dao.despesasApto() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	public void carregarDespesasCond(){

		try {
			despesas.addAll( dao.despesasCond() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}

	
	public void pesquisar(){

		ArrayList<Despesas> lista = new ArrayList<>();

		if ( !despesas.isEmpty() ) {
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
	
	
	public void salvar() {

		if ( cboApto.getSelectedItem() != "Todos" &&
				ftxtDtVenc.getValue() != null 
				&& ftxtDtPagto.getValue() != null
				&& !ftxtValor.getText().isEmpty() ){
			
			for( int i = 0; i < condominioMensal.size(); i++ ){
				for( int j = 0; j < apartamentos.size(); j++ ){
					if( apartamentos.get(j).getId() == condominioMensal.get(i).getIdApto() 
							&& ftxtDtVenc.getText().contains( condominioMensal.get(i).getDtVencimento() ) 
							){
						msg( "erroSalvar", cboApto.getSelectedItem().toString() );
						return;
					}
				}
			}
			

			msg( "confirmaSalvar", cboApto.getSelectedItem().toString() );

			if ( validar != false ){

				Condominio despesa = new Condominio();

				for( int i = 0; i < apartamentos.size(); i++ ){
					if( cboApto.getSelectedItem()
							.equals( Integer.toString( apartamentos.get(i).getNumero() ))){
						despesa.setIdApto( apartamentos.get(i).getId() );
					} 
				}
				despesa.setValor( Float.parseFloat( ftxtValor.getValue().toString() ));
				despesa.setDtVencimento( ftxtDtVenc.getText() );
				despesa.setDtPagamento( ftxtDtVenc.getText() );
				if( chkMulta.isSelected() ){
					despesa.setDtProrrogado( obterDataProrrogar( ftxtDtVenc.getText() ));
				} else {
					despesa.setDtProrrogado( ftxtDtVenc.getText() );
				}
				try {
					dao.insereCondominio( despesa );
					condominioMensal.add( despesa );
					msg( "salvar", txtDespesa.getText() );
				} catch (SQLException e) {
					msg( "erro", e.getMessage() );
				}
			}

		} else {
			msg( "erroVazio", "" );
		}
	}

	
	public void editar() {
		
		for( int i = 0; i < condominioMensal.size(); i ++ ){
			if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
					.equals(Integer.toString(despesas.get(i).getId()))){			
				msg( "confirmaEditar", txtDespesa.getText() );
				if (validar != false){
					Condominio despesa = new Condominio();
					despesa.setId( Integer.parseInt( txtId.getText() ));
					despesa.setValor( Float.parseFloat( ftxtValor.getValue().toString() ));
					despesa.setDtVencimento( ftxtDtVenc.getText() );
					try {
						dao.atualizaCondominio( despesa );
						condominioMensal.set( i, despesa );
						msg( "editar", txtDespesa.getText() );
					} catch (SQLException e) {
						msg( "erro", e.getMessage() );
					}
				}
				validar = false;
			}
		}
	}
	
	
	public void excluir() {

		for( int i = 0; i < despesas.size(); i ++ ){
			if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
					.equals(Integer.toString(despesas.get(i).getId()))){
				msg( "confirmaExcluir", txtDespesa.getText() );
				if (validar != false){
					Condominio despesa = new Condominio();
					despesa.setId( despesas.get(i).getId() );
					despesa.setValor( despesas.get(i).getValor() );
					despesa.setDtVencimento( despesas.get(i).getDtVencimento() );
					try {
						dao.excluiCondominio( despesa );
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
					"O condomínio mensal '" + mensagem + "' foi salvo com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/record.png" ));
			break;
			
		case "editar":
			JOptionPane.showMessageDialog(null, 
					"O condomínio mensal '" + mensagem + "' foi editado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/record.png" ));
			break;
		
		case "excluir":
			JOptionPane.showMessageDialog(null, 
					"O condomínio mensal '" + mensagem + "' foi excluído com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/record.png" ));
			break;

		case "confirmaSalvar":
			Object[] salvar = { "Confirmar", "Cancelar" };  
			int sv = JOptionPane.showOptionDialog(null, 
					"Você confirma a gravação da mensalidade do apto " + mensagem + "?",
					"Mensalidade de Condomínio", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ), salvar, salvar[1]);
			if (sv != 1) { validar = true; } else { validar = false; }
			break;

		case "confirmaEditar":
			Object[] editar = { "Confirmar", "Cancelar" };  
			int ed = JOptionPane.showOptionDialog(null, 
					"Você confirma a edição da condomínio mensal '" + mensagem + "' ?",
					"Edição de Condomínio", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ), editar, editar[1]);
			if (ed != 1) { validar = true; } else { validar = false; }
			break;
			
		case "confirmaExcluir":
			Object[] excluir = { "Confirmar", "Cancelar" };  
			int ex = JOptionPane.showOptionDialog(null, 
					"Você confirma a exclusão do condomínio '" + mensagem + "' ?",
					"Exclusão de Condomínio", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ), excluir, excluir[1]);
			if (ex != 1) { validar = true; } else { validar = false; }
			break;
			
		case "vazioPesquisa":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nNenhum resultado foi encontrado com: " + mensagem, 
					"Não Localizado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
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
					"Condomínio já cadastrado!\n\n"
							+ "Apatarmento " + mensagem + " já possui o registro.\n"
							+ "Selecione outro apartamento para registrar a mensalidade.",
					"Já Cadastrado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
			break;
		
		case "erroExcluir":
			JOptionPane.showMessageDialog(null, 
					"O condomínio mensal" + mensagem + " não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
			break;
			
		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"Todos os campos têm que estar preenchidos\npara a mensalidade do Condomínio",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/src/resources/warning.png" ));
			break;

		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione um condomínio para editar ou excluir.", 
					"condomínio não selecionado…", 
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
			if ( fechar != 1 ) { validar = true; } else { validar = false; }
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
	
	public ActionListener limpar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			limparCampos();
			ftxtDtReg.setText( obterData() );
			ftxtDtAlt.setText( obterData() );
			atualizarMensal( despesas );
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
			preencherMorador();
			
		}
	};
	

	public ActionListener cancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			txtId.setText( null );
			ftxtDtReg.setText( null );
			ftxtDtAlt.setText( null );
			cboApto.setSelectedIndex(0);
			txtDespesa.setText(null);
			alterarCampos ("protegerCampos");
			btnLimpar.setEnabled(true);
			atualizarMensal( despesas );
			atualizarTotal( despesas );
		}
	};

	public ActionListener editar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
					btnLimpar.setEnabled(false);
					focarCampo();
		}
	};

	public ActionListener inserir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			salvar();
//			limparCampos();
			formatarTabela( despesas );
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
				break;
			case 8:
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
			atualizarMensal( despesas );
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

				if ( source == tabela ){
					try {
						selecionarTabLinha();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	};
}
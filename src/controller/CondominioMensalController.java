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
import persistence.DespesasApartamentoDao;
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
	private JLabel lblMulta; 
	private JTextField txtId; 
	private JTextField txtNome; 
	private JTextField txtPesquisa; 
	private JFormattedTextField ftxtDtVenc; 
	private JFormattedTextField ftxtDtPagto; 
	private JFormattedTextField ftxtDtReg;
	private JFormattedTextField ftxtDtAlt;
	private JFormattedTextField ftxtMulta;
	private JFormattedTextField ftxtValor;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtVlrTotal;
	private JComboBox<String> cboFiltro; 
	private JComboBox<String> cboApto; 
	private JComboBox<String> cboReferencia; 
	private JCheckBox chkMulta; 
	private JButton btnLimpar; 
	private boolean validar;
	private String diretorio = "../projects/";
	private List<Moradores> moradores;
	private List<Apartamentos> apartamentos;
	private List<Despesas> despesas;
	private List<Condominio> condominioMensal;
	private CondominioMensalDao dao = new CondominioMensalDao();
	private DespesasApartamentoDao daoApto = new DespesasApartamentoDao();

	public CondominioMensalController( 
			TelaCondominioMensal janela, 
			JPanel painel, 
			JTable tabela, 
			JLabel lblId, 
			JLabel lblDtReg, 
			JLabel lblDtAlt, 
			JLabel lblMulta, 
			JTextField txtId, 
			JTextField txtNome, 
			JTextField txtPesquisa, 
			JFormattedTextField ftxtDtVenc, 
			JFormattedTextField ftxtDtPagto, 
			JFormattedTextField ftxtDtReg, 
			JFormattedTextField ftxtDtAlt,
			JFormattedTextField ftxtMulta, 
			JFormattedTextField ftxtValor, 
			JFormattedTextField ftxtQtd, 
			JFormattedTextField ftxtVlrTotal, 
			JComboBox<String> cboFiltro, 
			JComboBox<String> cboApto, 
			JComboBox<String> cboReferencia, 
			JCheckBox chkMulta, 
			JButton btnLimpar ) {
			
		this.janela = janela;
		this.painel = painel;
		this.tabela = tabela;
		this.lblId = lblId;
		this.lblDtReg = lblDtReg;
		this.lblDtAlt = lblDtAlt;
		this.lblMulta = lblMulta;
		this.txtId = txtId;
		this.txtNome = txtNome;
		this.txtPesquisa = txtPesquisa;
		this.ftxtDtVenc = ftxtDtVenc;
		this.ftxtDtPagto = ftxtDtPagto;
		this.ftxtDtReg = ftxtDtReg;
		this.ftxtDtAlt = ftxtDtAlt;
		this.ftxtMulta = ftxtMulta;
		this.ftxtValor = ftxtValor;
		this.ftxtQtd = ftxtQtd;
		this.ftxtVlrTotal = ftxtVlrTotal;
		this.cboFiltro = cboFiltro;
		this.cboApto = cboApto;
		this.cboReferencia = cboReferencia;
		this.chkMulta = chkMulta;
		this.btnLimpar = btnLimpar;
		this.moradores = new ArrayList<Moradores>();
		this.apartamentos = new ArrayList<Apartamentos>();
		this.despesas = new ArrayList<Despesas>();
		this.condominioMensal = new ArrayList<Condominio>();

		iniciar();
	}
	

	private void iniciar(){
		
		carregarMoradores();
		carregarAptos();
		carregarDespesasApto();
		carregarDespesasCond();
		carregarCondominioMensal();
		preencherFiltro();
		preencherApto();
		preencherReferencia();
		
		alterarCampos ("protegerCampos");
		preencherTabelaDespesas( despesas );
	}


	private void alterarCampos ( String opt ){

		switch ( opt ){

		case "protegerCampos":

			tabela.setEnabled(true);
			tabela.setForeground(Color.black);
			lblId.setVisible(false);
			lblDtReg.setVisible(false);
			lblDtAlt.setVisible(false);
			lblMulta.setVisible(false);
			txtNome.setEnabled(false);
			txtId.setVisible(false);
			ftxtMulta.setEditable(false);
			ftxtMulta.setVisible(false);
			ftxtValor.setEditable(false);
			ftxtDtReg.setVisible(false);
			ftxtDtAlt.setVisible(false);
			chkMulta.setVisible(false);
			break;	
		}
	}
	
	
	private void focarCampo(){

		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				ftxtDtVenc.requestFocus();  
			}  
		});
	}
	
	
	private void limparCampos(){

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
	
	
	private String obterDataProrrogada(String data){
		
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
	
	
	private String obterDataAtual(){
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String data = (sdf.format(date));
		return data;
	}
	
	
	private String obterMesRef( String data ){

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
	
	
	private String obterDataVencimento(){
		
		String venc = null;
		
		if( cboReferencia.getSelectedItem() != "Todos os Meses" ){
			DateFormat df = new SimpleDateFormat("MMMM/yyyy");
			Date dt = null;

			try {
				dt = df.parse( cboReferencia.getSelectedItem().toString() );
			} catch (ParseException e) {
				e.printStackTrace();
			}

			DateFormat ref = new SimpleDateFormat("MM/yyyy");
			venc ="10/" +  ref.format( dt );	
		}
		return venc;
	}
	
	
	private void atualizarTotal( List<Despesas> lista ){

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
	
	
	private void totalDespesa( List<Despesas> despesa ){

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
				if ( !chkMulta.isSelected() ){
				float multa = ( aptoVlr * 2 ) / 100;
				ftxtMulta.setValue( multa );
				ftxtValor.setValue( aptoVlr + multa );
				lblMulta.setText("Multa (2 %)");
				lblMulta.setVisible(true);
				ftxtMulta.setVisible(true);
				chkMulta.setVisible(true);
				} else {
					float multa = ( aptoVlr * 5 ) / 100;
					lblMulta.setText("Multa (5 %)");
					ftxtMulta.setValue( multa );
					ftxtValor.setValue( aptoVlr + multa );
				}
			} else {
				ftxtMulta.setValue( null );
				ftxtValor.setValue( aptoVlr );
				lblMulta.setVisible(false);
				ftxtMulta.setVisible(false);
				chkMulta.setVisible(false);
			}
		}
	}
	
	
	private void totalCondominio( List<Condominio> despesa ){

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
				if ( !chkMulta.isSelected() ){
				float multa = ( aptoVlr * 2 ) / 100;
				ftxtMulta.setValue( multa );
				ftxtValor.setValue( aptoVlr + multa );
				lblMulta.setText("Multa (2 %)");
				lblMulta.setVisible(true);
				ftxtMulta.setVisible(true);
				chkMulta.setVisible(true);
				} else {
					float multa = ( aptoVlr * 5 ) / 100;
					lblMulta.setText("Multa (5 %)");
					ftxtMulta.setValue( multa );
					ftxtValor.setValue( aptoVlr + multa );
				}
			} else {
				ftxtMulta.setValue( null );
				ftxtValor.setValue( aptoVlr );
				lblMulta.setVisible(false);
				ftxtMulta.setVisible(false);
				chkMulta.setVisible(false);
			}
		}
	}
	
	
	private void preencherMorador(){

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
	
	
	private void preencherFiltro(){
		
		cboFiltro.addItem( "Despesas" );
		cboFiltro.addItem( "Mensalidades" );
	}
	
	
	private void preencherReferencia(){

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
	
	
	private void preencherApto(){

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


	private void preencherTabelaDespesas( List<Despesas> lista ){
		
		List<String[]> linhas = new ArrayList<>();
		
		if(lista != null){	
			String numApto = null;

			DecimalFormat formato = new DecimalFormat("#,##0.00");
				
			for ( int i = 0; i < lista.size(); i++ ) {
				
				for ( int a = 0; a < apartamentos.size(); a++ ){
					if ( apartamentos.get(a).getId() == despesas.get(i).getIdApto() ){
						numApto = Integer.toString( apartamentos.get(a).getNumero() );
					}
				}
				
				if ( cboReferencia.getSelectedItem() == "Todos os Meses" 
						&& cboApto.getSelectedItem() == "Todos" 
						|| cboReferencia.getSelectedItem().
						equals( obterMesRef( lista.get(i).getDtVencimento()) ) 
						|| cboApto.getSelectedItem().equals( numApto ) 
						){
					
					if ( despesas.get(i).getIdApto() < 1 ){
						numApto = null;
					}
					
				String[] item = { 
						Integer.toString ( lista.get(i).getId() ), 
						lista.get(i).getDespesa(), 
						numApto, 
						obterMesRef( lista.get(i).getDtVencimento() ), 
						lista.get(i).getDtVencimento(), 
						formato.format( lista.get(i).getValor() ),  
				};
				linhas.add( item );
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
			totalDespesa( lista );
		}
	}
	
	
	private void preencherTabelaMensalidades( List<Condominio> lista ){
		
		List<String[]> linhas = new ArrayList<>();
		
		if(lista != null){		
			String numApto = null;
			String multa = null;
			String dtProrroga = null;
			float calc = 0;
			
			DecimalFormat formato = new DecimalFormat("#,##0.00");
				
			for ( int i = 0; i < lista.size(); i++ ) {
				
				for ( int a = 0; a < apartamentos.size(); a++ ){
					if ( apartamentos.get(a).getId() == condominioMensal.get(i).getIdApto() ){
						numApto = Integer.toString( apartamentos.get(a).getNumero() );
					}
				}
				
				if ( cboReferencia.getSelectedItem() == "Todos os Meses" 
						&& cboApto.getSelectedItem() == "Todos" 
						|| cboReferencia.getSelectedItem().
						equals( obterMesRef( lista.get(i).getDtVencimento()) ) 
						|| cboApto.getSelectedItem().equals( numApto ) 
						){
					
					if ( condominioMensal.get(i).getDtVencimento()
							.equals( condominioMensal.get(i).getDtPagamento() )){
						dtProrroga = null;
						multa = null;
					} else if ( condominioMensal.get(i).getDtVencimento()
							.equals( condominioMensal.get(i).getDtProrrogado() )){
						calc = ( lista.get(i).getValor() * 2 ) / 100;
						multa = formato.format( calc ) + " (2 %)";
						dtProrroga = null;
					} else {
						calc = ( lista.get(i).getValor() * 5 ) / 100;
						multa = formato.format( calc ) + " (5 %)";
						dtProrroga = lista.get(i).getDtProrrogado();
					}
					
				String[] item = { 
						Integer.toString ( lista.get(i).getId() ), 
						numApto, 
						obterMesRef( lista.get(i).getDtVencimento() ), 
						lista.get(i).getDtVencimento(), 
						lista.get(i).getDtPagamento(), 
						dtProrroga, 
						formato.format( lista.get(i).getValor() ), 
						multa, 
						formato.format( lista.get(i).getValor() + calc ), 
				};
				linhas.add( item );
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

		String[] nomesColunas = { "","Apartamento", "Referência", 
				"Data Vencimento", "Data Pagamento", "Data Prorrogação", 
				"Valor", "Valor Multa", "Valor Total", "Selecionar"};

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
		tabela.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(6).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(7).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(8).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(9).setCellRenderer(centralizado);

		tabela.getColumnModel().getColumn(0).setMinWidth(0);
		tabela.getColumnModel().getColumn(0).setMaxWidth(0);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(9).setMinWidth(0);
		tabela.getColumnModel().getColumn(9).setMaxWidth(0);
		tabela.getColumnModel().getColumn(9).setPreferredWidth(0);

		if ( cboApto.getSelectedItem() == "Todos" ){
			totalCondominio( lista );
		} else {
			totalCondominio( lista );
		}
	}
	
	
	private void selecionarTabLinha() throws ParseException{

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
			validar = true;
		} else {
			if( tabela.getRowCount() > 0 ){
				
				for( int d = 0; d < despesas.size(); d ++ ){
					
					if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString() )
							.equals(Integer.toString( despesas.get(d).getId() ))){
						
						for( int c = 0; c < condominioMensal.size(); c++ ){
							
							if( obterMesRef (despesas.get(d).getDtVencimento())
									.equals( obterMesRef (condominioMensal.get(c).getDtVencimento() ))){
								
								if( Integer.toString( despesas.get(d).getIdApto() )
										.equals( Integer.toString( condominioMensal.get(c).getIdApto() ))){
									
									cboReferencia.setSelectedItem( obterMesRef( condominioMensal.get(c).getDtVencimento() ));
									
									for ( int a = 0; a < apartamentos.size(); a++ ){
										if ( apartamentos.get(a).getId() == condominioMensal.get(c).getIdApto() ){
											cboApto.setSelectedItem( Integer.toString( 
													apartamentos.get(a).getNumero() ));
										}
									}
									
									ftxtDtVenc.setText( condominioMensal.get(c).getDtVencimento() );
									
									if ( condominioMensal.get(c).getDtVencimento()
											.equals( condominioMensal.get(c).getDtProrrogado() )){
										ftxtDtPagto.setText( condominioMensal.get(c).getDtPagamento() );
									} else {
										ftxtDtPagto.setText( condominioMensal.get(c).getDtProrrogado() );
									}
									
									preencherMorador();
									totalDespesa( despesas );
								}
							}
						}
						return;
					} else if( (tabela.getValueAt(tabela.getSelectedRow(), 0).toString() )
							.equals(Integer.toString( condominioMensal.get(d).getId() ))){

						for( int c = 0; c < condominioMensal.size(); c++ ){

							if( condominioMensal.get(d).getId() == condominioMensal.get(c).getId()){
								cboReferencia.setSelectedItem( obterMesRef( condominioMensal.get(c).getDtVencimento() ));
								ftxtDtVenc.setText( condominioMensal.get(c).getDtVencimento() );

								if ( condominioMensal.get(c).getDtVencimento()
										.equals( condominioMensal.get(c).getDtProrrogado() )){
									ftxtDtPagto.setText( condominioMensal.get(c).getDtPagamento() );
								} else if ( !condominioMensal.get(c).getDtPagamento()
										.equals( condominioMensal.get(c).getDtProrrogado() )){ 
									ftxtDtPagto.setText( condominioMensal.get(c).getDtPagamento() );
								} 
							}
							preencherMorador();
							totalCondominio( condominioMensal );
						}
						return;
					}
					validar = false;
				}
			}
		}
	}
	
	
	private void editarTabLinha() throws ParseException{

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
			validar = true;
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

	
	private void carregarCondominioMensal(){
		
		try {
			condominioMensal = dao.consultaCondominioMensal();
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	private void carregarMoradores(){

		try {
			moradores.addAll( dao.consultaMoradores() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	private void carregarAptos(){

		try {
			apartamentos.addAll( dao.consultaAptos() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	private void carregarDespesasApto(){

		try {
			despesas.addAll( dao.despesasApto() );
		} catch (SQLException e) {
			msg( "erro", e.getMessage() );
		}
	}
	
	
	private void carregarDespesasCond(){

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
						.contains( txtPesquisa.getText().toLowerCase() );

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
							msg( "vazioPesquisa", txtPesquisa.getText() );
						}
					}
				}
			}
			if ( validar == true){
				preencherTabelaDespesas( lista );
				validar = false;
			}
		} else {
			msg( "erroPesquisa", txtPesquisa.getText() );
			preencherTabelaDespesas( despesas );
		}
	}
	
	
	private void multa(){

		for( int i = 0; i < condominioMensal.size(); i++ ){
			
			if( condominioMensal.get(i).getDtProrrogado() !=  condominioMensal.get(i).getDtVencimento() ){
				
				if( !despesas.get(i).equals( "Multa prorrogada (" + condominioMensal.get(i).getDtVencimento() + ")") ){
					
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date venc = null;
				Date pro = null;

				try {
					venc = df.parse((String) condominioMensal.get(i).getDtVencimento() );
					pro = df.parse((String) condominioMensal.get(i).getDtProrrogado() );
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat inv = new SimpleDateFormat("yyyy.MM.dd");
				Date dt = null;
					try {
						dt = sdf.parse( condominioMensal.get(i).getDtProrrogado() );
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				
				if(venc.before(pro)){
					Despesas despesa = new Despesas();
					
					despesa.setId( condominioMensal.get(i).getIdApto() );
					despesa.setDespesa( "Multa prorrogada (" + condominioMensal.get(i).getDtVencimento() + ")" );
					despesa.setValor( ( condominioMensal.get(i).getValor() * 5 ) / 100 );
					despesa.setDtVencimento( inv.format( dt ) );
					despesa.setDtReferencia( inv.format( dt ) );
					despesas.add( despesa );
					try {
						daoApto.insereDespesaApartamento( despesa, condominioMensal.get(i).getIdApto() );
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			}
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
							&& obterMesRef( ftxtDtVenc.getText() )
							.contains( obterMesRef( condominioMensal.get(i).getDtVencimento() )) 
							){
						
						if ( cboApto.getSelectedItem().equals( Integer.toString( apartamentos.get(j).getNumero() )) ){
							msg( "erroSalvar", cboApto.getSelectedItem().toString() );
							return;
									
						} else {
							msg( "confirmaSalvar", cboApto.getSelectedItem().toString() );
						}
					}
				}
			}		

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
				despesa.setDtPagamento( ftxtDtPagto.getText() );
				if( chkMulta.isSelected() ){
					despesa.setDtProrrogado( obterDataProrrogada( ftxtDtVenc.getText() ));
				} else {
					despesa.setDtProrrogado( ftxtDtVenc.getText() );
				}
				try {
					dao.insereCondominio( despesa );
					condominioMensal.add( despesa );
					if( chkMulta.isSelected() ){
						multa();
					}
					msg( "salvar", txtPesquisa.getText() );
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
				msg( "confirmaEditar", txtPesquisa.getText() );
				if (validar != false){
					Condominio despesa = new Condominio();
					despesa.setId( Integer.parseInt( txtId.getText() ));
					despesa.setValor( Float.parseFloat( ftxtValor.getValue().toString() ));
					despesa.setDtVencimento( ftxtDtVenc.getText() );
					try {
						dao.atualizaCondominio( despesa );
						condominioMensal.set( i, despesa );
						msg( "editar", txtPesquisa.getText() );
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
				msg( "confirmaExcluir", txtPesquisa.getText() );
				if (validar != false){
					Condominio despesa = new Condominio();
					despesa.setId( despesas.get(i).getId() );
					despesa.setValor( despesas.get(i).getValor() );
					despesa.setDtVencimento( despesas.get(i).getDtVencimento() );
					try {
						dao.excluiCondominio( despesa );
						despesas.remove(i);
						msg( "excluir", txtPesquisa.getText() );
					} catch (SQLException e) {
						msg( "erro", e.getMessage() );
					}
				}
				validar = false; 
			}
		}
	}


	private void msg( String tipo, String mensagem ) {
		
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
							+ "O condomnio do apartamento " + mensagem + " já foi registrado.\n"
							+ "Selecione outro apartamento para novo registro.",
					"Atenção!", 
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
	

	private void fechar(){
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
			ftxtDtReg.setText( obterDataAtual() );
			ftxtDtAlt.setText( obterDataAtual() );
			totalDespesa( despesas );
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
			
			ftxtDtPagto.setText(null);
			Object source = e.getSource();
			
			if ( source == cboFiltro  ){
				if ( cboFiltro.getSelectedItem() == "Mensalidades" ){
					preencherTabelaMensalidades( condominioMensal );
					ftxtDtVenc.setText(null);
				} else {
					preencherTabelaDespesas( despesas );
					ftxtDtVenc.setText( obterDataVencimento() );
					ftxtDtPagto.setText(null);
					totalDespesa( despesas );
					preencherMorador();	
				}
			}
			if ( source ==  cboReferencia  ){
				if ( cboFiltro.getSelectedItem() == "Mensalidades" ){
					preencherTabelaMensalidades( condominioMensal );
					if( cboApto.getSelectedItem().equals( "Todos" ) 
							|| !cboReferencia.getSelectedItem().equals( "Todos os Meses") ){
					cboApto.setSelectedIndex(0);
					}
				} else {
					preencherTabelaDespesas( despesas );
					ftxtDtVenc.setText( obterDataVencimento() );
					ftxtDtPagto.setText(null);
					totalDespesa( despesas );
					preencherMorador();if( cboApto.getSelectedItem().equals( "Todos" ) 
							|| !cboReferencia.getSelectedItem().equals( "Todos os Meses") ){
						cboApto.setSelectedIndex(0);
					}
				}
			}
			if ( source == cboApto ){
				if ( cboFiltro.getSelectedItem() == "Mensalidades" ){
					preencherTabelaMensalidades( condominioMensal );
					ftxtDtVenc.setText(null);
					if( cboReferencia.getSelectedItem().equals( "Todos os Meses" ) 
							|| !cboApto.getSelectedItem().equals( "Todos" )){
						cboReferencia.setSelectedIndex(0);
						preencherMorador();
					}
				} else {
					preencherTabelaDespesas( despesas );
					ftxtDtVenc.setText( obterDataVencimento() );
					ftxtDtPagto.setText(null);
					totalDespesa( despesas );
					preencherMorador();
					if( cboReferencia.getSelectedItem().equals( "Todos os Meses" ) 
							|| !cboApto.getSelectedItem().equals( "Todos" )){
						cboReferencia.setSelectedIndex(0);
					}
				}
			}
		}
	};
	
	public ActionListener atualizar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			totalDespesa( despesas );
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
			preencherTabelaDespesas( despesas );
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
			totalDespesa( despesas );
		}

		@Override
		public void focusLost(FocusEvent e) {
			totalDespesa( despesas );
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
						editarTabLinha();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			} else {
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
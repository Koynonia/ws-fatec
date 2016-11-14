/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 11/11/2016
 */

package edu.pousada.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.pousada.dao.ChaleDAO;
import edu.pousada.dao.ChaleDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Chale;
import edu.pousada.entity.Reserva;

public class HospedagemCtrl {

	//private JTextField txtPesquisa;
	private static JFormattedTextField ftxtQtdReservaChale;
	private static JFormattedTextField ftxtVlrReservaChale;
	private static JFormattedTextField ftxtQtdReservaServico;
	private static JFormattedTextField ftxtVlrReservaServico;
	private static JTable tabReserva;
	private static JTable tabServico;
	private JButton btnPesquisar;
	private JButton btnReservaEditar;
	private JButton btnServicoEditar;

	private static List<Reserva>reservas;
	private static List<Chale> chales;

	public HospedagemCtrl(
			JTextField txtPesquisa, 
			JFormattedTextField ftxtQtdReservaChale, 
			JFormattedTextField ftxtVlrReservaChale, 
			JFormattedTextField ftxtQtdReservaServico, 
			JFormattedTextField ftxtVlrReservaServico, 
			JTable tabReserva, 
			JTable tabServico, 
			JButton btnPesquisar, 
			JButton btnReservaEditar, 
			JButton btnServicoEditar
			) {

		//this.txtPesquisa = txtPesquisa;
		HospedagemCtrl.ftxtQtdReservaChale = ftxtQtdReservaChale;
		HospedagemCtrl.ftxtVlrReservaChale = ftxtVlrReservaChale;
		HospedagemCtrl.ftxtQtdReservaServico = ftxtQtdReservaServico;
		HospedagemCtrl.ftxtVlrReservaServico = ftxtVlrReservaServico;
		this.btnPesquisar = btnPesquisar;
		this.btnReservaEditar = btnReservaEditar;
		this.btnServicoEditar = btnServicoEditar;
		HospedagemCtrl.tabReserva = tabReserva;
		HospedagemCtrl.tabServico = tabServico;
		HospedagemCtrl.reservas = new ArrayList<Reserva>();
		HospedagemCtrl.chales = new ArrayList<Chale>();
		
		LogonCtrl.getInstance();
	}


	public HospedagemCtrl() {
		carregaTabelas();
	}
	
	public static void carregaTabelas() {
		
		formataTabela( "reserva", tabReserva );
		formataTabela( "servico", tabServico );
	}
	
	
	//  DAO  //////////////////////////////////////


	public static void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}
	
	
	public void excluiReserva( Reserva r ) {

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			dao.excluir( r );
		} catch (SQLException e) { 
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nHospedagemCtrl > excluiReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public static void cargaChale(){

		ChaleDAO dao = new ChaleDAOImpl();

		try {
			chales = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaChale()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	//  CRUD  /////////////////////////////////////


	public void cancelaReserva( JTable tabela ){

		// seleciona a linha da tabela a ser cancelada
		if ( tabela.getSelectedRowCount() == 0 ) {
			MensagensCtrl.msg( "erroLinha", "" );
		} else {

			// atualiza a base de dados excluindo o registro selecionado
			Reserva r = new Reserva();
			for(int i = 0; i < reservas.size(); i ++){

				// limpa a mascara no numero da reserva
				if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString().replaceFirst("0*", ""))
						.equals( reservas.get(i).getId().toString() )){

					if ( MensagensCtrl.msg( "reservaCancelar", "nº " 
							+ String.format( "%06d", reservas.get(i).getId()) ) != false ){
						// verifica se a reserva é ativa (paga)
						if( reservas.get(i).getAtiva() != true ){
							r.setId(reservas.get(i).getId());
							excluiReserva( r );
							cargaReserva();

							// atualiza a tabela, removendo a linha
							((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
							tabela.updateUI();

							// atualiza o valor total
							atualizaValor( tabela );
						} else {
							MensagensCtrl.msg("reservaAtiva","nº " + String.format( "%06d", reservas.get(i).getId()) );
							return;
						}
					} 
				}
			}	
		}
	}


	// TABELA //////////////////////////////////

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] colunasTabela( String opt ){
		//escolhe as colunas que a tabela exibirá

		String[] colunas = null;
		List titulos = new ArrayList();

		switch ( opt ){

		case "reserva":
			titulos.clear();
			titulos.add( 0, "Reserva nº" );
			titulos.add( 1, "Chalé nº" );
			titulos.add( 2, "Categoria" );
			titulos.add( 3, "Reserva Início" );
			titulos.add( 4, "Reserva Fim" );
			titulos.add( 5, "Cliente" );
			titulos.add( 6, "Qtd. Pessoas" );
			titulos.add( 7, "Vlr. Diária" );
			titulos.add( 8, "Vlr. Total" );
			titulos.add( 9, "Ativa" );

			for ( int i = 0; i< titulos.size(); i++ ) {
				colunas = (String[]) titulos.toArray (new String[i]);
			}
			break;

		case "servico":
			titulos.clear();
			titulos.add( 0, "Chalé nº" );
			titulos.add( 1, "" );
			titulos.add( 2, "Serviço" );
			titulos.add( 3, "Data" );
			titulos.add( 4, "Hora Início" );
			titulos.add( 5, "Hora Fim" );
			titulos.add( 6, "" );
			titulos.add( 7, "Cliente" );
			titulos.add( 8, "Valor" );
			titulos.add( 9, "" );



			for ( int i = 0; i< titulos.size(); i++ ) {
				colunas = (String[]) titulos.toArray (new String[i]);
			}
			break;
		}
		return colunas;
	}


	public static Object[][] linhasTabela( String opt ){
		//escolhe as linhas que a tabela exibirá

		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat hr = new SimpleDateFormat("HH:mm");
		DecimalFormat vlr = new DecimalFormat("#,##0.00");
		dt.setLenient(false);
		hr.setLenient(false);
		Object[][] linhas = null;

		switch ( opt ){

		case "reserva":

			cargaReserva();

			//calcula o valor total das reservas em cada linha
			if( !reservas.isEmpty() ){
				linhas = new Object[reservas.size()][];
				for (int i = 0; i < reservas.size(); i++) {

					float vlrTotal = ((( reservas.get(i).getDtFim().getTime() 
							- reservas.get(i).getDtInicio().getTime() ) + 3600000) / 86400000L) 
							* reservas.get(i).getChale().getDiaria() ;

					float total = 0;
					if ( vlrTotal != 0){
						total = vlrTotal;
					} else {
						total = reservas.get(i).getChale().getDiaria();
					}

					//carrega as linhas na tabela
					linhas[i] = new Object[]{
							String.format( "%06d",reservas.get(i).getId() ),
							String.format( "%03d",reservas.get(i).getChale().getId() ),
							reservas.get(i).getChale().getCategoria(),
							dt.format( reservas.get(i).getDtInicio() ),
							dt.format( reservas.get(i).getDtFim() ),
							"  " + reservas.get(i).getCliente().getNome(),
							Integer.toString( reservas.get(i).getQtdAdulto() + reservas.get(i).getQtdCrianca() ) ,   
							vlr.format( reservas.get(i).getChale().getDiaria() ),
							vlr.format( total ),
							reservas.get(i).getAtiva() //deveria se tornar um checkbox!!!
					};
				}
			}
			break;

		case "servico":

			cargaChale();

			if( !chales.isEmpty() ){
				linhas = new Object[chales.size()][];
				for (int i = 0; i < chales.size(); i++) {
					linhas[i] = new Object[]{
							String.format( "%03d", chales.get(i).getId() ),
							"escondido",
							"implementar",
							dt.format( new Date() ),
							hr.format( new Date() ),
							hr.format( new Date() ),
							"escondido",
							"implementar",
							vlr.format( 20.50 ),
							"escondido"				
					};
				}
			}
			break;
		}
		return linhas;
	}


	public static void formataTabela( String opt, JTable tabela ){
		// realiza a formatação conforme o contexto e tabela	

		//alinhamento de titulos das colunas
		((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//alinhamento das celulas
		DefaultTableCellRenderer esquerdo = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direito = new DefaultTableCellRenderer();  

		esquerdo.setHorizontalAlignment(SwingConstants.LEFT);
		centro.setHorizontalAlignment(SwingConstants.CENTER);  
		direito.setHorizontalAlignment(SwingConstants.RIGHT);

		//cria um modelo com os dados (linhas e colunas)
		final DefaultTableModel model = new DefaultTableModel(
				linhasTabela( opt ), colunasTabela( opt )) {
			private static final long serialVersionUID = 1L;

			//Disabilita a edicao de qualquer celula, mesnos a coluna mencionada
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return (colIndex == 9);
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				switch (column) {

				case 9:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		//define como selecao a linha inteira
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//define o model da tabela
		tabela.setModel(model);

		if( colunasTabela( opt ) != null ){

			//aplica o alinhamento
			tabela.getColumnModel().getColumn(0).setCellRenderer(centro);
			tabela.getColumnModel().getColumn(1).setCellRenderer(centro);
			tabela.getColumnModel().getColumn(2).setCellRenderer(esquerdo);
			tabela.getColumnModel().getColumn(3).setCellRenderer(centro);
			tabela.getColumnModel().getColumn(4).setCellRenderer(centro);
			tabela.getColumnModel().getColumn(5).setCellRenderer(centro);
			tabela.getColumnModel().getColumn(6).setCellRenderer(centro);
			tabela.getColumnModel().getColumn(7).setCellRenderer(direito);
			tabela.getColumnModel().getColumn(8).setCellRenderer(direito);
			tabela.getColumnModel().getColumn(9).setCellRenderer(centro);

			//tamanho das colunas
			tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(15);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(30);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(20);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(20);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(20);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(20);
			tabela.getColumnModel().getColumn(9).setPreferredWidth(20);


			//formata e esconde coluna por tabela
			switch ( opt){

			case "reserva":
				tabela.getColumnModel().getColumn(5).setCellRenderer(esquerdo);
				tabela.getColumnModel().getColumn(5).setPreferredWidth(120);
				break;

			case "servico":
				tabela.getColumnModel().getColumn(1).setMinWidth(0);
				tabela.getColumnModel().getColumn(1).setMaxWidth(0);
				tabela.getColumnModel().getColumn(6).setMinWidth(0);
				tabela.getColumnModel().getColumn(6).setMaxWidth(0);
				tabela.getColumnModel().getColumn(7).setCellRenderer(esquerdo);
				tabela.getColumnModel().getColumn(7).setPreferredWidth(150);
				tabela.getColumnModel().getColumn(9).setMinWidth(0);
				tabela.getColumnModel().getColumn(9).setMaxWidth(0);
				break;
			}
		}
		atualizaValor( tabela );
	}


	//  SUPORTE  ////////////////////////////////


	public static void atualizaValor( JTable tabela ){
		//Atualiza quantidade e valor total

		float total = 0;

		NumberFormat nf = NumberFormat.getInstance();

		//verifica se a tabela está preenchida
		if( tabela.getRowCount() > 0 ){
			//precorre as linhas selecionando o valor
			for(int i = 0; i < tabela.getRowCount(); i ++){

				try {
					//converte a String e realiza a soma
					total+= nf.parse( tabela.getValueAt(i, 8).toString() ).doubleValue();
				} catch (ParseException e) {
					MensagensCtrl.msg("", "ERRO " + e.getCause() 
							+ "\n\nLocal:\nPrincipalCtrl > atualizaValor()."  
							+ "\n\nMensagem:\n" + e.getMessage() );
					//e.printStackTrace();
				}
			}
		}

		//aplica os totais no campo correspondente
		switch ( tabela.getName() ){

		case "reserva":
			ftxtQtdReservaChale.setValue( Integer.toString ( tabela.getRowCount() ) );
			ftxtVlrReservaChale.setValue( total );
			break;

		case "servico":
			ftxtQtdReservaServico.setValue( Integer.toString ( tabela.getRowCount() ) );
			ftxtVlrReservaServico.setValue( total );
			break;
		}
	}
	
	
	// BOTAO //////////////////////////////////


		public ActionListener acionar = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//verifica qual botao esta solicitando a acao
				Object source = e.getSource();

				if( source == btnPesquisar ){
					MensagensCtrl.msg( "construir", "" );
				}
				if( source == btnReservaEditar ){
					cancelaReserva( tabReserva );
				}
				if( source == btnServicoEditar ){
					MensagensCtrl.msg( "construir", "" );
				}
			}
		};


		// CONTROLE TECLA ///////////////////////////////


		public KeyListener teclas = new KeyListener() {

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
					break;
				case KeyEvent.VK_DELETE:
					//verifica qual componenete esta solicitando a acao e desabilita
					Object source = e.getSource();

					if( source == tabReserva ){
						cancelaReserva( tabReserva );
					}
					break;
				case 8: //MAC OSX: DELETE
					Object sourceMac = e.getSource();
					if( sourceMac == tabReserva ){
						cancelaReserva( tabReserva );
					}
					break;
				case KeyEvent.VK_TAB:
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		};


		// MOUSE //////////////////////////////////


		public MouseListener clicar = new MouseListener() {

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
				// ao clicar 2x aciona
				if(e.getClickCount() == 2){ 

					//verifica qual componenete esta solicitando a acao e desabilita
					Object source = e.getSource();

					if( source == tabReserva ){
						cancelaReserva( tabReserva );
					}
					if( source == tabServico ){
						MensagensCtrl.msg( "construir", "" );
					}
				}
			}
		};
}
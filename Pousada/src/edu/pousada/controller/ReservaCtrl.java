/**
 * @author FERNANDO MORAES OLIVEIRA
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.ChaleDAO;
import edu.pousada.dao.ChaleDAOImpl;
import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Chale;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Logon;
import edu.pousada.entity.Reserva;

public class ReservaCtrl {

	private PrincipalCtrl ctrlPrincipal;

	// construtor ReservaFrm
	private static ReservaFrm janela;
	private JTable tabela;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtValor;
	private JButton btnLimpar;
	private JButton btnCancelar; 
	private JButton btnConcluir; 
	private JButton btnVoltar;

	// construtor PrincipalFrm
	private static JLabel lblReservaImg;
	private static JLabel lblReservaObs;
	private static JTextField txtReservaQtdAdulto; 
	private static JTextField txtReservaQtdCrianca; 
	private static JTextField txtReservaNome;  
	private static JTextField txtReservaEmail; 
	private static JTextField txtReservaCidade; 
	private static JTextField txtReservaEstado; 
	private static JTextField txtReservaPais; 
	private static JFormattedTextField ftxtReservaDocNum;
	private static JFormattedTextField ftxtReservaTelefone; 
	private static JFormattedTextField ftxtReservaCelular; 
	private static JFormattedTextField ftxtReservaDtInicio; 
	private static JFormattedTextField ftxtReservaDtFim;
	private static JTextArea txtaReservaObs;
	private static JComboBox<String> cboReservaCategoria; 
	private static JComboBox<String> cboReservaDocTipo; 
	private static JButton btnReservaEnviar;
	private static JButton btnReservaLimpar;
	public static JButton btnReservas;

	private static boolean validar;
	private ImageIcon imagem;

	private static LogonCtrl ctrlLogon = LogonCtrl.getInstance();
	private static List<Reserva>reservas;
	private List<Chale> chales;
	private static List<Cliente> clientes;

	// construtor 1 ReservaFrm
	public ReservaCtrl(
			ReservaFrm janela, 
			JTable tabela, 
			JFormattedTextField ftxtQtd,  
			JFormattedTextField ftxtValor, 
			JButton btnLimpar,
			JButton btnCancelar, 
			JButton btnConcluir, 
			JButton btnVoltar
			) {

		ReservaCtrl.janela = janela;
		this.tabela = tabela;
		this.ftxtQtd = ftxtQtd;
		this.ftxtValor = ftxtValor;
		this.btnLimpar = btnLimpar;
		this.btnCancelar = btnCancelar;
		this.btnConcluir = btnConcluir;
		this.btnVoltar = btnVoltar;
		ReservaCtrl.reservas = new ArrayList<Reserva>();

		// atualiza o Logon com o perfil
		if( !ctrlLogon.getSession().isEmpty() ){
			ctrlLogon.getSession().get(0).setTela( janela.getName() );
		}

		// prepara o ambiente
		cargaReserva();
		formataTabela();
	}

	// construtor 2 PrincipalFrm
	public ReservaCtrl(
			JLabel lblReservaImg, 
			JLabel lblReservaObs,
			JTextField txtReservaQtdAdulto, 
			JTextField txtReservaQtdCrianca, 
			JTextField txtReservaNome, 
			JTextField txtReservaEmail, 
			JTextField txtReservaCidade, 
			JTextField txtReservaEstado, 
			JTextField txtReservaPais, 
			JFormattedTextField ftxtReservaDocNum, 
			JFormattedTextField ftxtReservaTelefone, 
			JFormattedTextField ftxtReservaCelular, 
			JFormattedTextField ftxtReservaDtInicio, 
			JFormattedTextField ftxtReservaDtFim, 
			JTextArea txtaReservaObs, 
			JComboBox<String> cboReservaChale, 
			JComboBox<String> cboReservaDocTipo, 
			JButton btnReservaEnviar,
			JButton btnReservaLimpar, 
			JButton btnReservas
			) {

		ReservaCtrl.lblReservaImg = lblReservaImg;
		ReservaCtrl.lblReservaObs =lblReservaObs;
		ReservaCtrl.txtReservaQtdAdulto = txtReservaQtdAdulto; 
		ReservaCtrl.txtReservaQtdCrianca = txtReservaQtdCrianca; 
		ReservaCtrl.txtReservaNome = txtReservaNome; 
		ReservaCtrl.txtReservaEmail = txtReservaEmail; 
		ReservaCtrl.txtReservaCidade = txtReservaCidade; 
		ReservaCtrl.txtReservaEstado = txtReservaEstado; 
		ReservaCtrl.txtReservaPais = txtReservaPais; 
		ReservaCtrl.ftxtReservaDocNum = ftxtReservaDocNum; 
		ReservaCtrl.ftxtReservaTelefone = ftxtReservaTelefone; 
		ReservaCtrl.ftxtReservaCelular = ftxtReservaCelular; 
		ReservaCtrl.ftxtReservaDtInicio = ftxtReservaDtInicio; 
		ReservaCtrl.ftxtReservaDtFim = ftxtReservaDtFim;
		ReservaCtrl.txtaReservaObs = txtaReservaObs;
		ReservaCtrl.cboReservaCategoria = cboReservaChale; 
		ReservaCtrl.cboReservaDocTipo = cboReservaDocTipo; 
		ReservaCtrl.btnReservaEnviar = btnReservaEnviar; 
		ReservaCtrl.btnReservaLimpar = btnReservaLimpar;
		ReservaCtrl.btnReservas = btnReservas;

		preencheCategoria();
		preencheTipoDoc();

		ctrlPrincipal = new PrincipalCtrl();
	}

	public ReservaCtrl() {}


	//  DAO  //////////////////////////////////////


	public static void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			janela.setAlwaysOnTop ( false ); 
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > cargaReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
			janela.setAlwaysOnTop ( true ); 
		}
	}


	public void adicionar( Reserva obj ){

		ReservaDAO dao = new ReservaDAOImpl();

		try {
			dao.adicionar( obj );
		} catch (SQLException e) {
			janela.setAlwaysOnTop ( false ); 
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > adicionar()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
			janela.setAlwaysOnTop ( true );
		}
	}


	public void alterar( Reserva obj ){

		ReservaDAO dao = new ReservaDAOImpl();

		try {
			dao.alterar( obj );
		} catch (SQLException e) {
			janela.setAlwaysOnTop ( false ); 
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > alterar()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
			janela.setAlwaysOnTop ( true );
		}
	}


	public void excluir( Reserva obj ) {

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			dao.excluir( obj );
		} catch (SQLException e) {
			janela.setAlwaysOnTop ( false ); 
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > excluir()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
			janela.setAlwaysOnTop ( true );
		}
	}


	public void cargaChale(){

		ChaleDAO dao = new ChaleDAOImpl();

		try {
			chales = dao.todos();
		} catch (SQLException e) {
			janela.setAlwaysOnTop ( false ); 
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > cargaChale()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
			janela.setAlwaysOnTop ( true );
		}
	}


	public Integer chaleDisponivel( Reserva obj ){

		ReservaDAOImpl dao = new ReservaDAOImpl();
		int qtd = 0;

		try {
			qtd = dao.chaleDisponivel(obj);
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > chaleDisponivel()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
		return qtd;
	}


	public static void cargaCliente(){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			clientes = dao.todos();
		} catch (SQLException e) {
			janela.setAlwaysOnTop ( false ); 
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > cargaCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
			janela.setAlwaysOnTop ( true ); 
		}
	}

	public void adicionarCliente( Cliente obj ){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			dao.adicionar( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > adicionaCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}

	//  CRUD  //////////////////////////////////////


	public void adicionaReserva () throws ParseException{

		// verifica se os campos foram preenchidos
		if( CamposCtrl.valida( "reserva" ) != false 
				&& CamposCtrl.data(ftxtReservaDtInicio, ftxtReservaDtFim) != false ){

			cargaReserva();
			cargaCliente();
			cargaChale();

			Chale ch = new Chale();

			if( !chales.isEmpty() ){
				if( !reservas.isEmpty() ){

					for( int r = 0; r < reservas.size(); r++ ){

						// seleciona o chale da categoria escolhida ou retorna erro
						for( int i = 0; i < chales.size(); i++ ){
							if( chales.get(i).getCategoria().equals( cboReservaCategoria.getSelectedItem() )){

								// verifica se o chale já foi reservado
								if ( !reservas.get(r).getChale().getId().equals( chales.get(i).getId() )){

									ch.setId( chales.get(i).getId() );
									ch.setCategoria( chales.get(i).getCategoria() );
									ch.setDiaria( chales.get(i).getDiaria() );
									ch.setFrigobar( chales.get(i).getFrigobar() );

								} 
							}
						}
					}
				} else {
					// seleciona o chale da categoria escolhida ou retorna erro se não houver reservas
					for( int i = 0; i < chales.size(); i++ ){
						if( chales.get(i).getCategoria().equals( cboReservaCategoria.getSelectedItem() )){

							ch.setId( chales.get(i).getId() );
							ch.setCategoria( chales.get(i).getCategoria() );
							ch.setDiaria( chales.get(i).getDiaria() );
							ch.setFrigobar( chales.get(i).getFrigobar() );
						}
					}
				}
			} else {
				MensagensCtrl.msg("erroChale", (String) cboReservaCategoria.getSelectedItem() );
				return;
			}
			// se não houver cliente na base de dados, cadastra o primeiro parcialmente e o usa na reserva
			Cliente cl = new Cliente();
			if( clientes.isEmpty() ){

				cl.setNome( txtReservaNome.getText() );
				cl.setEmail( txtReservaEmail.getText() );
				cl.setDocumento( ftxtReservaDocNum.getText()
						.replace(".","").replace("/","").replace("-","")  );
				cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
				cl.setDtNasc( new Date() );
				cl.setTelefone( ftxtReservaTelefone.getText()
						.replace("(","").replace(")", "").replace(" ","").replace("-","") );
				cl.setCelular( ftxtReservaCelular.getText()
						.replace("(","").replace(")", "").replace(" ","").replace("-","") );
				cl.setCidade( txtReservaCidade.getText() );
				cl.setEstado( txtReservaEstado.getText() );
				cl.setPais( txtReservaPais.getText() );
				cl.setDtCadastro( new Date() );
				cl.setAtivo( false );
				adicionarCliente( cl );

				// atualiza com o novo cliente
				cargaCliente();

				// atualiza o logon para um perfil visitante
				List<Logon> log = new ArrayList<Logon>();
				Logon l = new Logon();

				l.setIdUsuario( clientes.get(0).getId() );
				l.setTela( "Reservas" );
				l.setPerfil( 0 );
				l.setLogoff( 0 );
				l.setDtLogon( new Date() );
				log.add(l);
				ctrlLogon.setLogon( log );

			} else {
				// se já houver clientes, busca pelo documento do cliente
				for( int i = 0; i < clientes.size(); i++ ){
					if( clientes.get(i).getDocumento().equals( ftxtReservaDocNum.getText()
							.replace(".","").replace("/","").replace("-","")  )){
						validar = true;
					}
					// ao final da busca, se não encontrar o cliente, o cadastra parcialmente e o usa na reserva
					if( i == clientes.size()-1 ){
						if(	validar != true ){

							cl.setId( clientes.size() );
							cl.setNome( txtReservaNome.getText() );
							cl.setEmail( txtReservaEmail.getText() );
							cl.setDocumento( ftxtReservaDocNum.getText()
									.replace(".","").replace("/","").replace("-","") );
							cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
							cl.setDtNasc( new Date() );
							cl.setTelefone( ftxtReservaTelefone.getText()
									.replace("(","").replace(")", "").replace(" ","").replace("-","") );
							cl.setCelular( ftxtReservaCelular.getText()
									.replace("(","").replace(")", "").replace(" ","").replace("-","") );
							cl.setCidade( txtReservaCidade.getText() );
							cl.setEstado( txtReservaEstado.getText() );
							cl.setPais( txtReservaPais.getText() );
							cl.setAtivo( false );
							cl.setDtCadastro( new Date() );
							adicionarCliente( cl );

							// atualiza com o cliente
							cargaCliente();

							// atualiza o logon para um perfil visitante
							List<Logon> log = new ArrayList<Logon>();
							Logon l = new Logon();

							l.setIdUsuario( clientes.get( clientes.size()-1 ).getId() );
							l.setTela( "Reservas" );
							l.setPerfil( 0 );
							l.setLogoff( 0 );
							l.setDtLogon( new Date() );
							log.add(l);
							ctrlLogon.setLogon( log );

						} else {

							// caso o cliente seja encontrado, apenas recupera seus dados para a reserva
							for( int j = 0; j < clientes.size(); j++ ){
								if( clientes.get(j).getDocumento().equals( ftxtReservaDocNum.getText()
										.replace(".","").replace("/","").replace("-","")  )){
									cl.setId( clientes.get(j).getId() );
									cl.setNome( clientes.get(j).getNome() );
									cl.setEmail( clientes.get(j).getEmail() );
									cl.setDocumento( clientes.get(j).getDocumento() );
									cl.setDocTipo( clientes.get(j).getDocTipo() );
									cl.setDtNasc( clientes.get(j).getDtNasc() );
									cl.setTelefone( clientes.get(j).getTelefone() );
									cl.setCelular( clientes.get(j).getCelular() );
									cl.setEndereco( clientes.get(j).getEndereco() );
									cl.setBairro( clientes.get(j).getBairro() );
									cl.setCidade( clientes.get(j).getCidade() );
									cl.setEstado( clientes.get(j).getEstado() );
									cl.setPais( clientes.get(j).getPais() );
									cl.setCep( clientes.get(j).getCep() );
									cl.setDtCadastro( clientes.get(j).getDtCadastro() );
									cl.setAtivo( clientes.get(j).getAtivo() );

									// atualiza o logon para um perfil visitante
									List<Logon> log = new ArrayList<Logon>();
									Logon l = new Logon();
									l.setIdUsuario( clientes.get( j ).getId() );
									l.setTela( "Reservas" );
									l.setPerfil( 0 );
									l.setLogoff( 0 );
									l.setDtLogon( new Date() );
									log.add(l);
									ctrlLogon.setLogon( log );
								}
							}
							validar = false;
						}
					}
				}
			}		
			// monta a reserva com os objetos Chale e Cliente
			Reserva r = new Reserva();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			r.setCliente( cl );
			r.setChale( ch );
			r.setQtdAdulto( Integer.parseInt( txtReservaQtdAdulto.getText() ));
			r.setQtdCrianca( Integer.parseInt(txtReservaQtdCrianca.getText() ));
			r.setDtInicio( sdf.parse( ftxtReservaDtInicio.getText().replace("/","") ));
			r.setDtFim( sdf.parse( ftxtReservaDtFim.getText().replace("/","") ));
			r.setMensagem( txtaReservaObs.getText() );
			r.setDesconto( 0 );
			r.setAtiva(false);
			r.setDtCadastro( new Date() );

			// verifica se a reserva do chale esta disponivel (verifica as datas)
			if( chaleDisponivel(r) != 0  ) {

				MensagensCtrl.msg("erroChale", (String) cboReservaCategoria.getSelectedItem() );
			} else {

				adicionar( r );

				if( ctrlLogon.getSession().get(0).getPerfil() != 2 ){

					// atualiza o estado do botão Reserva na tela Principal
					btnReservas.setText( "Reservas ( " + ctrlLogon.reservaQtd() + " )" );
					btnReservas.setVisible(true);
					abrir( "reservas" );
				} else {
					ctrlPrincipal.trocaPerfil(2);
				}
			}
		}
	}


	// SUPORTE ReservaFrm /////////////////////////

	public static void preencheReserva(){
		// preenche a reserva com os dados do cliente logado e trava os campos

		cargaCliente();

		if( !clientes.isEmpty() ){

			for (int i = 0; i < clientes.size(); i++) {

				if( clientes.get(i).getId()
						.equals( ctrlLogon.getSession().get(0).getIdUsuario() )){

					CamposCtrl.limpa("reserva");

					txtReservaNome.setText( clientes.get(i).getNome() );
					ftxtReservaDocNum.setText( clientes.get(i).getDocumento() );
					cboReservaDocTipo.setSelectedItem( clientes.get(i).getDocTipo() );
					txtReservaEmail.setText( clientes.get(i).getEmail() );
					ftxtReservaTelefone.setText( clientes.get(i).getTelefone() );
					ftxtReservaCelular.setText( clientes.get(i).getCelular() );
					txtReservaCidade.setText( clientes.get(i).getCidade() );
					txtReservaEstado.setText( clientes.get(i).getEstado() );
					txtReservaPais.setText( clientes.get(i).getPais() ); 

					CamposCtrl.desativa("reserva");
				}
			}
		}
	}

	public void excluiReserva(){

		janela.setAlwaysOnTop ( false ); 

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
					if ( MensagensCtrl.msg( "cancelar", "nº " + String.format( "%06d", reservas.get(i).getId()) )){
						r.setId(reservas.get(i).getId());
						excluir( r );
						cargaReserva();

						// atualiza o estado do botão Reserva na tela Principal
						if ( ctrlLogon.reservaQtd() != 0) {
							btnReservas.setText( "Reservas ( " + ctrlLogon.reservaQtd() + " )" );
							btnReservas.setVisible(true);
						} else {
							btnReservas.setText( "Reservas");
							btnReservas.setVisible(false);
						}

						// atualiza a tabela, removendo a linha
						((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
						tabela.updateUI();

						// atualiza o valor total
						atualizaValor();
					}
				}
			}	
		}
		janela.setAlwaysOnTop ( true ); 
	}


	public void atualizaValor(){

		// atualiza total da reserva
		float total = 0;

		NumberFormat nf = NumberFormat.getInstance();

		// verifica se a tabela está preenchida
		if( tabela.getRowCount() > 0 ){
			// precorre as linhas selecionando o valor
			for(int i = 0; i < tabela.getRowCount(); i ++){

				try {
					// converte a String e realiza a soma
					total+= nf.parse( tabela.getValueAt(i, 5).toString() ).doubleValue();
				} catch (ParseException e) {
					janela.setAlwaysOnTop ( false ); 
					MensagensCtrl.msg("", "ERRO " + e.getCause() 
							+ "\n\nLocal:\nReservaCtrl > atualizaValor()."  
							+ "\n\nMensagem:\n" + e.getMessage() );
					//e.printStackTrace();
					janela.setAlwaysOnTop ( true ); 
				}
			}
		}
		ftxtQtd.setValue( Integer.toString ( tabela.getRowCount() ) );
		ftxtValor.setValue( total );
	}


	public static void alteraBtnReserva(){
		// verifica se existem reservas e altera o botão

		cargaReserva();

		if( !reservas.isEmpty() ){
			for (int i = 0; i < reservas.size(); i++) {
				if( reservas.get(i).getCliente().getId()
						.equals( ctrlLogon.getSession().get(0).getIdUsuario() )
						&& reservas.get(i).getAtiva() != true ){

					btnReservas.setText( "Reservas ( " + ctrlLogon.reservaQtd() + " )" );
					btnReservas.setVisible(true);
				} else {
					btnReservas.setVisible(false);
				}
			}
		}
	}


	// TABELA ReservaFrm //////////////////////////////////

	public void formataTabela(){

		// vetor das linhas da tabela
		List<String[]> linhas = new ArrayList<>(); 

		// carrega as linhas na tabela com os dados da compra (somente as colunas configuradas)
		if(reservas != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			DecimalFormat formato = new DecimalFormat("#,##0.00");

			for (int i = 0; i < reservas.size(); i++) {
				if( reservas.get(i).getCliente().getId()
						.equals( ctrlLogon.getSession().get(0).getIdUsuario() )
						&& reservas.get(i).getAtiva() != true ){

					// calcula o valor total das reservas em cada linha
					float vlrTotal = ((( reservas.get(i).getDtFim().getTime() 
							- reservas.get(i).getDtInicio().getTime() ) + 3600000) / 86400000L) 
							* reservas.get(i).getChale().getDiaria() ;

					float total = 0;
					if ( vlrTotal != 0){
						total = vlrTotal;
					} else {
						total = reservas.get(i).getChale().getDiaria();
					}

					String[] item = { 
							String.format( "%06d",reservas.get(i).getId() ),
							sdf.format( reservas.get(i).getDtInicio() ),
							sdf.format( reservas.get(i).getDtFim() ),
							reservas.get(i).getChale().getCategoria(),   
							formato.format( reservas.get(i).getChale().getDiaria() ),
							formato.format( total )
					};
					linhas.add(item);
				}
			}
		} 
		// configura o alinhamento dos titulos das colunas da tabela
		((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		// configura o alinhamento das colunas da tabela
		DefaultTableCellRenderer esquerdo = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direito = new DefaultTableCellRenderer();  
		esquerdo.setHorizontalAlignment(SwingConstants.LEFT);  
		centro.setHorizontalAlignment(SwingConstants.CENTER);  
		direito.setHorizontalAlignment(SwingConstants.RIGHT);

		// nomes das colunas da tabela
		String[] nomesColunas = { "Número","Chegada","Partida", "Chalé", "Diária", "Valor Total"};

		// cria um defaultablemodel com os dados (linhas e colunas)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)

		// trava a edição das celulas
		{ boolean[] canEdit = new boolean []{    
				false, false, false, false, false, false };
		@Override    
		public boolean isCellEditable(int rowIndex, int columnIndex) {    
			return canEdit [columnIndex]; } 
		};

		// define seleionar toda a linha
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// define o modelo da tabela
		tabela.setModel(model);

		// define o alinhamento das colunas
		tabela.getColumnModel().getColumn(0).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(4).setCellRenderer(direito);
		tabela.getColumnModel().getColumn(5).setCellRenderer(direito);

		atualizaValor();
	}


	// COMBOBOX  PrincipalFrm /////////////////////////////////


	public void preencheCategoria(){

		cargaChale();

		// carrega um Array com as categorias
		String[] lista = new String[chales.size()];
		for ( int i = 0; i < chales.size(); i++ ){
			String item = chales.get(i).getCategoria();
			lista[i] = item;
		}
		
		// remove categorias repetidas
		String[] combo = CamposCtrl.removeRepetidos( lista );
		
		// adiciona na combobox
		cboReservaCategoria.addItem( "Selecione…" );
		for ( int i = 0; i < combo.length; i++ ){
				cboReservaCategoria.addItem( combo[i] );
		}
	}


	public void preencheTipoDoc(){

		String[] tipos = {
				"CPF",
				"RG",
				"CNH",
				"Passaporte"
		};
		// adicionar na combobox
		cboReservaDocTipo.addItem( "Selecione…" );
		for ( int i = 0; i < tipos.length; i++ ){
			cboReservaDocTipo.addItem( tipos[i] );
		}
	}


	public void imagensCombo(){

		imagem = new ImageIcon( LogonCtrl.getCaminho() + "/imagens/chale" 
				+ cboReservaCategoria.getSelectedIndex() + ".jpg" );
		lblReservaImg.setIcon( new ImageIcon( 
				imagem.getImage().getScaledInstance( 
						lblReservaImg.getWidth(), 
						lblReservaImg.getHeight(), 
						Image.SCALE_DEFAULT )));
	}


	// JANELA /////////////////////////////////

	public void abrir ( String nome ){

		switch ( nome ){

		case "principal":	
			janela.dispose();
			break;

		case "reservas":
			ReservaFrm reserva;
			try {
				reserva = new ReservaFrm();
				reserva.setVisible(true);
				reserva.setAlwaysOnTop ( false );
			} catch (ParseException e) {
				MensagensCtrl.msg("", "ERRO " + e.getCause() 
						+ "\n\nLocal: ReservaCtrl >  abrir()."  
						+ "\n\nMensagem:\n" + e.getMessage() );
				//e.printStackTrace();
			}
			break;
		}
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
		janela.setAlwaysOnTop ( false ); 
		if( !MensagensCtrl.msg("sistema","Fechamento") ){
			System.exit(0);
		}
		janela.setAlwaysOnTop ( true ); 
	}


	// BOTAO //////////////////////////////////


	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if( source == cboReservaDocTipo ){
				CamposCtrl.mascara( "reserva" );
			}
			if( source == cboReservaCategoria ){
				imagensCombo();
			}
			if(source == btnLimpar){
				if( reservas.size() > 0 ){
					janela.setAlwaysOnTop ( false );
					if ( MensagensCtrl.msg("limpar","") ){
						Reserva r = new Reserva();
						for( int i = 0; i < reservas.size(); i++ ){
							if( reservas.get(i).getCliente().getId()
									.equals( ctrlLogon.getSession().get(0).getIdUsuario() )
									&& reservas.get(i).getAtiva() != true ){
								r.setId( reservas.get(i).getId() );	
								excluir( r );
							}
						}
						cargaReserva();
						formataTabela();
						// atualiza o estado do botão Reserva na tela Principal
						btnReservas.setText( "Reservas");
						btnReservas.setVisible(false);
						janela.setAlwaysOnTop ( false );
						MensagensCtrl.msg("sucesso","");
						janela.setAlwaysOnTop ( false );
					}
					janela.setAlwaysOnTop ( true );
				}
			}
			if(source == btnCancelar){
				excluiReserva();
			}
			if(source == btnConcluir){
				janela.setAlwaysOnTop ( false );
				MensagensCtrl.msg( "construir", "" );
				janela.setAlwaysOnTop ( true );
			}
			if(source == btnVoltar){
				abrir( "principal" );
			}
			if( source == btnReservaLimpar ){
				CamposCtrl.limpa( "reserva" );
			}
			if( source == btnReservaEnviar ){
				try {
					adicionaReserva ();
				} catch (ParseException e1) {
					MensagensCtrl.msg("", "ERRO " + e1.getCause() 
							+ "\n\nLocal: ReservaCtrl >  acionar() : adicionaReserva()"  
							+ "\n\nMensagem:\n" + e1.getMessage() );
					//e1.printStackTrace();
				}
			}
		}
	};


	// CONTROLE TECLA ///////////////////////////////


	public KeyListener teclas = new KeyListener() {  

		@Override  
		public void keyTyped(KeyEvent e) {

			String tipo = "";
			String tam = "";

			// verifica qual componenete e configura o tipo de entrada (alfa ou numero)
			Object source = e.getSource();

			if( source == txtReservaNome ){
				tipo = "alfa";
			}
			if( source == txtReservaCidade ){
				tipo = "alfa";
			}
			if( source == txtReservaEstado ){
				tipo = "alfa";
			}
			if( source == txtReservaPais ){
				tipo = "alfa";
			}
			if( source == txtReservaQtdAdulto ){
				tipo = "numero";
			}
			if( source == txtReservaQtdCrianca ){
				tipo = "numero";
			}
			if( source == txtaReservaObs ){
				tam = txtaReservaObs.getText();
				lblReservaObs.setText("Observações (" + (tam.length()+1) + " de 300):");
				if(tam.length() > 300){
					txtaReservaObs.setBackground(new Color(255,240,245));
					return;
				} else {
					txtaReservaObs.setBackground(new Color(255,255,255));
				}
			}

			switch ( tipo ) {

			case "numero":
				String numeros = "0987654321";
				if(!numeros.contains(e.getKeyChar()+"")){
					e.consume();
				}
				break;

			case "alfa":
				String caracteres = "0987654321";
				if(caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
				break;
			}
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
				janela.setAlwaysOnTop ( false );
				MensagensCtrl.msg( "sair", "" );
				janela.setAlwaysOnTop ( true );
				break;
			case KeyEvent.VK_DELETE:

				//cancela(); //precisa alterar para o foco do controle (conflita com obs)
				break;
			case 8: //MAC OSX: DELETE
				//cancela();
				break;
			case KeyEvent.VK_TAB:

				Object sourceTab = e.getSource();

				//desabilita a tecla TAB
				if( sourceTab == txtaReservaObs ){
					if(e.getModifiers() > 0) txtaReservaObs.transferFocusBackward();
					else txtaReservaObs.transferFocus(); 
					e.consume();
				}
				break;
			}
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

				excluiReserva();
			}
		}
	};
}
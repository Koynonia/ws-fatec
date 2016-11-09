/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.awt.Color;
import java.awt.Component;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.ChaleDAO;
import edu.pousada.dao.ChaleDAOImpl;
import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.ContatoDAO;
import edu.pousada.dao.ContatoDAOImpl;
import edu.pousada.dao.FuncionarioDAO;
import edu.pousada.dao.FuncionarioDAOImpl;
import edu.pousada.dao.InfoDAO;
import edu.pousada.dao.InfoDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Chale;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Contato;
import edu.pousada.entity.Funcionario;
import edu.pousada.entity.Logon;
import edu.pousada.entity.Info;
import edu.pousada.entity.Reserva;

public class PrincipalCtrl {

	private PrincipalFrm janela; 
	private JTabbedPane tabContainer;
	private JPanel painelPrincipal;
	private JPanel painelChale;
	private JPanel painelLazer;
	private JPanel painelServico;
	private JPanel painelReserva;
	private JPanel painelContato;
	private JPanel painelClientePrincipal;
	private JPanel painelClienteServico;
	private JPanel painelClienteHistorico;
	private JPanel painelCadastro;
	private JPanel painelAdmPrincipal; 
	private JPanel painelAdmChale; 
	private JPanel painelAdmServico;  
	private JPanel painelAdmCadastro; 
	private JPasswordField pwdSenha;
	private JLabel lblLogin;
	private JLabel lblPwd;
	private JLabel lblMsg;
	private JLabel lblRelogio;
	private JLabel lblPrincipalImg;
	private JLabel lblChaleImg; 
	private JLabel lblLazerImg;
	private JLabel lblServicoImg;
	private JLabel lblReservaImg;
	private JLabel lblReservaMsg;
	private JLabel lblContatoMsg;
	private JTextField txtLogin;
	private JTextField txtPesquisa;
	private JTextField txtReservaQtdAdulto; 
	private JTextField txtReservaQtdCrianca; 
	private JTextField txtReservaNome; 
	private JTextField txtReservaDocNum; 
	private JTextField txtReservaEmail; 
	private JTextField txtReservaTelefone; 
	private JTextField txtReservaCelular; 
	private JTextField txtReservaCidade; 
	private JTextField txtReservaEstado; 
	private JTextField txtReservaPais; 
	private JTextField txtContatoNome; 
	private JTextField txtContatoEmail; 
	private JTextField txtContatoTelefone; 
	private JTextField txtContatoCidade; 
	private JTextField txtContatoEstado; 
	private JTextField txtContatoPais; 
	private JTextField txtCadastroNome;
	private JTextField txtCadastroDocNum;
	private JTextField txtCadastroEmail;
	private JTextField txtCadastroTelefone;
	private JTextField txtCadastroCelular;
	private JTextField txtCadastroEndereco;
	private JTextField txtCadastroBairro;
	private JTextField txtCadastroCidade;
	private JTextField txtCadastroEstado;
	private JTextField txtCadastroCep;
	private JTextField txtCadastroPais;
	private JFormattedTextField ftxtReservaDtInicio; 
	private JFormattedTextField ftxtReservaDtFim;
	private JFormattedTextField ftxtQtdReservaChale;
	private JFormattedTextField ftxtVlrReservaChale;
	private JFormattedTextField ftxtQtdReservaServico;
	private JFormattedTextField ftxtVlrReservaServico;
	private JFormattedTextField ftxtCadastroDtNasc;
	private JTextArea txtaPrincipalInfo; 
	private JTextArea txtaPrincipalDetalhe; 
	private JTextArea txtaChaleInfo; 
	private JTextArea txtaChaleDetalhe; 
	private JTextArea txtaLazerInfo; 
	private JTextArea txtaLazerDetalhe; 
	private JTextArea txtaServicoInfo; 
	private JTextArea txtaServicoDetalhe; 
	private JTextArea txtaReservaInfo; 
	private JTextArea txtaReservaMsg; 
	private JTextArea txtaContatoInfo; 
	private JTextArea txtaContatoMsg; 
	private JTextArea txtaClienteInfo;
	private JTextArea txtaClienteDetalhe;
	private JTextArea txtaCadastroInfo;
	private JComboBox<String> cboReservaCategoria; 
	private JComboBox<String> cboReservaDocTipo; 
	private JComboBox<String> cboContatoAssunto; 
	private JComboBox<String> cboCadastroDocTipo;
	private JTable tabReserva; 
	private JTable tabServico; 
	private JButton btnLogin; 
	public static JButton btnReservas;
	private JButton btnReservaEnviar; 
	private JButton btnContatoEnviar;
	private JButton btnPesquisar;
	private JButton btnReservaLimpar;
	private JButton btnContatoLimpar;
	private JButton btnReservaEditar;
	private JButton btnServicoEditar;
	private JButton btnCadastroLimpar;
	private JButton btnCadastroEditar;
	private JButton btnCadastroSalvar;
	private JButton btnCadastroExcluir;
	private String diretorio = "../Pousada/resources/";
	private LogonCtrl logon = LogonCtrl.getInstance();
	private ImageIcon imagem;
	private static boolean validar;
	private List<Info> infos;
	private List<Chale> chales;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<Reserva> reservas;

	@SuppressWarnings("static-access")
	public PrincipalCtrl(
			PrincipalFrm janela, 
			JTabbedPane tabContainer, 
			JPanel painelPrincipal,
			JPanel painelChale,
			JPanel painelLazer,
			JPanel painelServico,
			JPanel painelReserva,
			JPanel painelContato, 
			JPanel painelClientePrincipal, 
			JPanel painelClienteServico,
			JPanel painelClienteHistorico,
			JPanel painelCadastro,
			JPanel painelAdmPrincipal, 
			JPanel painelAdmChale, 
			JPanel painelAdmServico, 
			JPasswordField pwdSenha,
			JLabel lblLogin,
			JLabel lblPwd,
			JLabel lblMsg,
			JLabel lblRelogio, 
			JLabel lblPrincipalImg, 
			JLabel lblChaleImg, 
			JLabel lblLazerImg, 
			JLabel lblServicoImg, 
			JLabel lblReservaImg,  
			JLabel lblReservaMsg, 
			JLabel lblContatoMsg,
			JLabel lblVersao,
			JTextField txtLogin,
			JTextField txtPesquisa, 
			JTextField txtReservaQtdAdulto, 
			JTextField txtReservaQtdCrianca, 
			JTextField txtReservaNome, 
			JTextField txtReservaDocNum, 
			JTextField txtReservaEmail, 
			JTextField txtReservaTelefone, 
			JTextField txtReservaCelular, 
			JTextField txtReservaCidade, 
			JTextField txtReservaEstado, 
			JTextField txtReservaPais, 
			JTextField txtContatoNome, 
			JTextField txtContatoEmail, 
			JTextField txtContatoTelefone, 
			JTextField txtContatoCidade, 
			JTextField txtContatoEstado, 
			JTextField txtContatoPais, 
			JTextField txtCadastroNome,
			JTextField txtCadastroDocNum,
			JTextField txtCadastroEmail,
			JTextField txtCadastroTelefone,
			JTextField txtCadastroCelular,
			JTextField txtCadastroEndereco,
			JTextField txtCadastroBairro,
			JTextField txtCadastroCidade,
			JTextField txtCadastroEstado,
			JTextField txtCadastroCep,
			JTextField txtCadastroPais,
			JFormattedTextField ftxtReservaDtInicio, 
			JFormattedTextField ftxtReservaDtFim, 
			JFormattedTextField ftxtQtdReservaChale,
			JFormattedTextField ftxtVlrReservaChale,
			JFormattedTextField ftxtQtdReservaServico,
			JFormattedTextField ftxtVlrReservaServico,
			JFormattedTextField ftxtClienteDtNasc,
			JTextArea txtaPrincipalInfo, 
			JTextArea txtaPrincipal, 
			JTextArea txtaChaleInfo, 
			JTextArea txtaChale, 
			JTextArea txtaLazerInfo, 
			JTextArea txtaLazer, 
			JTextArea txtaServicoInfo, 
			JTextArea txtaServico, 
			JTextArea txtaReservaInfo, 
			JTextArea txtaReservaObs, 
			JTextArea txtaContatoInfo, 
			JTextArea txtaContatoMsg, 
			JTextArea txtaClienteInfo, 
			JTextArea txtaClienteDetalhe, 
			JTextArea txtaCadastroInfo, 
			JComboBox<String> cboReservaChale, 
			JComboBox<String> cboReservaDocTipo, 
			JComboBox<String> cboContatoAssunto, 
			JComboBox<String> cboCadastroDocTipo,
			JTable tabReserva, 
			JTable tabServico, 
			JButton btnLogin, 
			JButton btnReservas,
			JButton btnReservaEnviar, 
			JButton btnContatoEnviar,
			JButton btnPesquisar, 
			JButton btnReservaLimpar, 
			JButton btnContatoLimpar,
			JButton btnReservaEditar, 
			JButton btnServicoEditar,
			JButton btnCadastroLimpar,
			JButton btnCadastroEditar,
			JButton btnCadastroSalvar,
			JButton btnCadastroExcluir
			){

		this.janela = janela; 
		this.tabContainer = tabContainer;
		this.painelPrincipal = painelPrincipal;
		this.painelChale = painelChale;
		this.painelLazer = painelLazer;
		this.painelServico = painelServico;
		this.painelReserva = painelReserva;
		this.painelContato = painelContato;
		this.painelClientePrincipal = painelClientePrincipal;
		this.painelClienteServico = painelClienteServico;
		this.painelClienteHistorico = painelClienteHistorico;
		this.painelCadastro = painelCadastro;
		this.painelAdmPrincipal = painelAdmPrincipal;
		this.painelAdmChale = painelAdmChale;
		this.painelAdmServico = painelAdmServico;
		this.pwdSenha = pwdSenha;
		this.lblLogin = lblLogin;
		this.lblPwd = lblPwd;
		this.lblMsg = lblMsg;
		this.lblRelogio = lblRelogio;
		this.lblPrincipalImg = lblPrincipalImg;
		this.lblChaleImg = lblChaleImg;
		this.lblLazerImg = lblLazerImg;
		this.lblServicoImg = lblServicoImg;
		this.lblReservaImg = lblReservaImg;
		this.lblReservaMsg =lblReservaMsg;
		this.lblContatoMsg = lblContatoMsg;
		this.txtLogin = txtLogin;
		this.txtPesquisa = txtPesquisa;
		this.txtReservaQtdAdulto = txtReservaQtdAdulto; 
		this.txtReservaQtdCrianca = txtReservaQtdCrianca; 
		this.txtReservaNome = txtReservaNome; 
		this.txtReservaDocNum = txtReservaDocNum; 
		this.txtReservaEmail = txtReservaEmail; 
		this.txtReservaTelefone = txtReservaTelefone; 
		this.txtReservaCelular = txtReservaCelular; 
		this.txtReservaCidade = txtReservaCidade; 
		this.txtReservaEstado = txtReservaEstado; 
		this.txtReservaPais = txtReservaPais; 
		this.txtContatoNome = txtContatoNome; 
		this.txtContatoEmail = txtContatoEmail; 
		this.txtContatoTelefone = txtContatoTelefone; 
		this.txtContatoCidade = txtContatoCidade; 
		this.txtContatoEstado = txtContatoEstado; 
		this.txtContatoPais = txtContatoPais; 
		this.txtCadastroNome = txtCadastroNome;
		this.txtCadastroDocNum = txtCadastroDocNum;
		this.txtCadastroEmail = txtCadastroEmail;
		this.txtCadastroTelefone = txtCadastroTelefone;
		this.txtCadastroCelular = txtCadastroCelular;
		this.txtCadastroEndereco = txtCadastroEndereco;
		this.txtCadastroBairro = txtCadastroBairro;
		this.txtCadastroCidade = txtCadastroCidade;
		this.txtCadastroEstado = txtCadastroEstado;
		this.txtCadastroCep = txtCadastroCep;
		this.txtCadastroPais = txtCadastroPais;
		this.ftxtReservaDtInicio = ftxtReservaDtInicio; 
		this.ftxtReservaDtFim = ftxtReservaDtFim;
		this.ftxtQtdReservaChale = ftxtQtdReservaChale;
		this.ftxtVlrReservaChale = ftxtVlrReservaChale;
		this.ftxtQtdReservaServico = ftxtQtdReservaServico;
		this.ftxtVlrReservaServico = ftxtVlrReservaServico;
		this.ftxtCadastroDtNasc = ftxtClienteDtNasc;
		this.txtaPrincipalInfo = txtaPrincipalInfo; 
		this.txtaPrincipalDetalhe = txtaPrincipal; 
		this.txtaChaleInfo = txtaChaleInfo; 
		this.txtaChaleDetalhe = txtaChale; 
		this.txtaLazerInfo = txtaLazerInfo; 
		this.txtaLazerDetalhe = txtaLazer; 
		this.txtaServicoInfo = txtaServicoInfo; 
		this.txtaServicoDetalhe = txtaServico; 
		this.txtaReservaInfo = txtaReservaInfo; 
		this.txtaReservaMsg = txtaReservaObs; 
		this.txtaContatoInfo = txtaContatoInfo; 
		this.txtaContatoMsg = txtaContatoMsg; 
		this.txtaClienteInfo = txtaClienteInfo; 
		this.txtaClienteDetalhe = txtaClienteDetalhe; 
		this.txtaCadastroInfo = txtaCadastroInfo;
		this.cboReservaCategoria = cboReservaChale; 
		this.cboReservaDocTipo = cboReservaDocTipo; 
		this.cboContatoAssunto = cboContatoAssunto; 
		this.cboCadastroDocTipo = cboCadastroDocTipo; 
		this.tabReserva = tabReserva; 
		this.tabServico = tabServico; 
		this.btnLogin = btnLogin; 
		this.btnReservas = btnReservas;
		this.btnReservaEnviar = btnReservaEnviar; 
		this.btnContatoEnviar = btnContatoEnviar;
		this.btnPesquisar = btnPesquisar;
		this.btnReservaLimpar = btnReservaLimpar;
		this.btnContatoLimpar = btnContatoLimpar;
		this.btnReservaEditar = btnReservaEditar;
		this.btnServicoEditar = btnServicoEditar;
		this.btnCadastroLimpar = btnCadastroLimpar;
		this.btnCadastroEditar = btnCadastroEditar;
		this.btnCadastroSalvar = btnCadastroSalvar;
		this.btnCadastroExcluir = btnCadastroExcluir;
		this.infos = new ArrayList<Info>();
		this.chales = new ArrayList<Chale>();
		this.clientes = new ArrayList<Cliente>();
		this.funcionarios = new ArrayList<Funcionario>();
		this.reservas = new ArrayList<Reserva>();


		// realiza a preparação do ambiente visitante
		cargaInfo();
		preecheInfo();
		preencheCategoria();
		preencheTipoDoc();
		preencheAssunto();
		temporizador();
		imagensRandom();

		// realiza o login com uma sessão anterior perdida
		logon.autoLogin( false );

		// recupera o ambiente de uma sessão perdida 
		if( logon.getSession().isEmpty() ){
			trocaPerfil(0);
		} else {

			trocaPerfil( logon.getSession().get(0).getPerfil() );
			lblMsg.setText( "- Sessão recuperada!" );
			lblLogin.setVisible(false);
			txtLogin.setVisible(false);
			lblPwd.setVisible(false);
			pwdSenha.setVisible(false);
			btnLogin.setText("Sair");
		}

		// aciona o relogio exibido na tela
		relogio();
		lblVersao.setText( "versão: " + infos.get(0).getVersao() );
	}


	public void alteraBtnReserva(){
		// verifica se existem reservas e altera o botão

		cargaReserva();

		if( !reservas.isEmpty() ){
			for (int i = 0; i < reservas.size(); i++) {
				if( reservas.get(i).getCliente().getId()
						.equals( logon.getSession().get(0).getIdUsuario() )
						&& reservas.get(i).getAtiva() != true ){

					btnReservas.setText( "Reservas ( " + logon.reservaQtd() + " )" );
					btnReservas.setVisible(true);}
			}
		}
	}


	// DAO //////////////////////////////////////


	public void cargaInfo(){

		InfoDAO dao = new InfoDAOImpl();
		try {
			infos = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaInfo()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void cargaChale(){

		ChaleDAO dao = new ChaleDAOImpl();

		try {
			chales = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaChale()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public int chaleDisponivel( Reserva r ){

		ReservaDAOImpl dao = new ReservaDAOImpl();
		int ch = 0;

		try {
			ch = dao.chaleDisponivel(r);
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > chaleDisponivelPelaData()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
		return ch;
	}


	public int chaleDisponivelPelaData( Reserva r ){

		ReservaDAOImpl dao = new ReservaDAOImpl();
		int ch = 0;

		try {
			ch = dao.chaleDisponivelPelaData(r);
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > chaleDisponivelPelaData()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
		return ch;
	}


	public void cargaCliente(){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			clientes = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void adicionaCliente( Cliente c ){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			dao.adicionar( c );
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > adicionaCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void cargaFuncionario(){

		FuncionarioDAO dao = new FuncionarioDAOImpl();
		try {
			funcionarios = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaFuncionario()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void adicionaReserva( Reserva r ){

		ReservaDAO dao = new ReservaDAOImpl();

		try {
			dao.adicionar( r );
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > adicionaReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void excluiReserva( Reserva r ) {

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			dao.excluir( r );
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > excluir()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void adicionaContato( Contato c ){

		ContatoDAO dao = new ContatoDAOImpl();

		try {
			dao.adicionar( c );
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > adicionaContato()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	// RESERVA /////////////////////////


	public void adicionaReserva () throws ParseException{
		//adiciona uma reserva verificando sua data com um objeto Chale e Cliente

		cargaCliente();
		cargaChale();
		cargaReserva();
		validaCampo();

		//verifica se a validação está desligada
		if( validar != false  ){
			Chale ch = new Chale();
			validar = false;
			if( !chales.isEmpty() ){
				if( !reservas.isEmpty() ){

					for( int r = 0; r < reservas.size(); r++ ){

						//seleciona o chale da categoria escolhida ou retorna erro
						for( int i = 0; i < chales.size(); i++ ){
							if( chales.get(i).getCategoria().equals( cboReservaCategoria.getSelectedItem() )){

								//verifica se o chale já foi reservado
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
					//seleciona o chale da categoria escolhida ou retorna erro se não houver reservas
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
				msg("erroChale", (String) cboReservaCategoria.getSelectedItem() );
				return;
			}
			//se não houver cliente na base de dados, cadastra o primeiro parcialmente e o usa na reserva
			Cliente cl = new Cliente();
			if( clientes.isEmpty() ){
				
				cl.setNome( txtReservaNome.getText() );
				cl.setEmail( txtReservaEmail.getText() );
				cl.setDocumento( txtReservaDocNum.getText() );
				cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
				cl.setDtNasc( new Date() );
				cl.setTelefone( txtReservaTelefone.getText() );
				cl.setCelular( txtReservaCelular.getText() );
				cl.setCidade( txtReservaCidade.getText() );
				cl.setEstado( txtReservaEstado.getText() );
				cl.setPais( txtReservaPais.getText() );
				cl.setDtCadastro( new Date() );
				cl.setAtivo( false );
				adicionaCliente( cl );
				
				//atualiza com o novo cliente
				cargaCliente();
				
				//atualiza o logon para um perfil visitante
				List<Logon> log = new ArrayList<Logon>();
				Logon l = new Logon();
				l.setIdUsuario( clientes.get(0).getId() );
				l.setTela( janela.getName() );
				l.setPerfil( 0 );
				l.setLogoff( 0 );
				l.setDtLogon( new Date() );
				log.add(l);
				logon.setLogon( log );
				
			} else {
				//se já houver clientes, busca pelo documento do cliente
				for( int i = 0; i < clientes.size(); i++ ){
					if( clientes.get(i).getDocumento().equals( txtReservaDocNum.getText() )){
						validar = true;
					}
					//ao final da busca, se não encontrar o cliente, o cadastra parcialmente e o usa na reserva
					if( i == clientes.size()-1 ){
						if(	validar == false ){
							
							cl.setId( clientes.size() );
							cl.setNome( txtReservaNome.getText() );
							cl.setEmail( txtReservaEmail.getText() );
							cl.setDocumento( txtReservaDocNum.getText() );
							cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
							cl.setDtNasc( new Date() );
							cl.setTelefone( txtReservaTelefone.getText() );
							cl.setCelular( txtReservaCelular.getText() );
							cl.setCidade( txtReservaCidade.getText() );
							cl.setEstado( txtReservaEstado.getText() );
							cl.setPais( txtReservaPais.getText() );
							cl.setAtivo( false );
							cl.setDtCadastro( new Date() );
							adicionaCliente( cl );
							
							//atualiza com o cliente
							cargaCliente();
							
							//atualiza o logon para um perfil visitante
							List<Logon> log = new ArrayList<Logon>();
							Logon l = new Logon();
							l.setIdUsuario( clientes.get( clientes.size()-1 ).getId() );
							l.setTela( janela.getName() );
							l.setPerfil( 0 );
							l.setLogoff( 0 );
							l.setDtLogon( new Date() );
							log.add(l);
							logon.setLogon( log );
							
						} else {
							//caso o cliente seja encontrado, apenas recupera seus dados para a reserva
							for( int j = 0; j < clientes.size(); j++ ){
								if( clientes.get(j).getDocumento().equals( txtReservaDocNum.getText() )){
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
									
									//atualiza o logon para um perfil visitante
									List<Logon> log = new ArrayList<Logon>();
									Logon l = new Logon();
									l.setIdUsuario( clientes.get( j ).getId() );
									l.setTela( janela.getName() );
									l.setPerfil( 0 );
									l.setLogoff( 0 );
									l.setDtLogon( new Date() );
									log.add(l);
									logon.setLogon( log );
								}
							}
							validar = false;
						}
					}
				}
			}
			//monta a reserva com os objetos Chale e Cliente
			Reserva r = new Reserva();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			r.setCliente( cl );
			r.setChale( ch );
			r.setQtdAdulto( Integer.parseInt( txtReservaQtdAdulto.getText() ));
			r.setQtdCrianca( Integer.parseInt(txtReservaQtdCrianca.getText() ));
			r.setDtInicio( sdf.parse( ftxtReservaDtInicio.getText().replace("/","") ));
			r.setDtFim( sdf.parse( ftxtReservaDtFim.getText().replace("/","") ));
			r.setMensagem( txtaReservaMsg.getText() );
			r.setDesconto( 0 );
			r.setAtiva(false);
			r.setDtCadastro( new Date() );

			//verifica se a reserva do chale esta disponivel (verifica as datas)
			if( chaleDisponivel(r) != 0  ) {
				
				msg("erroChale", (String) cboReservaCategoria.getSelectedItem() );
			} else {
				
				adicionaReserva( r );
				
				if( logon.getSession().get(0).getPerfil() != 2 ){
					
				//atualiza o estado do botão Reserva na tela Principal
				btnReservas.setText( "Reservas ( " + logon.reservaQtd() + " )" );
				btnReservas.setVisible(true);
				abrir( "reservas" );
				} else {
					trocaPerfil(2);
				}
			}
		}
	}


	public void cancelaReserva( JTable tabela ){

		// seleciona a linha da tabela a ser cancelada
		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
		} else {

			// atualiza a base de dados excluindo o registro selecionado
			Reserva r = new Reserva();
			for(int i = 0; i < reservas.size(); i ++){

				// limpa a mascara no numero da reserva
				if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString().replaceFirst("0*", ""))
						.equals( reservas.get(i).getId().toString() )){

					msg( "reservaCancelar", "nº " + String.format( "%06d", reservas.get(i).getId()) );
					if (validar != false){
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
							msg("reservaAtiva","nº " + String.format( "%06d", reservas.get(i).getId()) );
							return;
						}
					} 
				}
			}
			validar = false;	
		}
	}


	// CONTATO /////////////////////////


	public void adicionaContato(){

		validaCampo();

		if( validar != false  ){
			Contato c = new Contato();
			c.setNome( txtContatoNome.getText() );
			c.setEmail( txtContatoEmail.getText() );
			c.setTelefone( txtContatoTelefone.getText() );
			c.setCidade( txtContatoCidade.getText() );
			c.setEstado( txtContatoEstado.getText() );
			c.setPais( txtContatoPais.getText() );
			c.setAssunto( cboContatoAssunto.getSelectedIndex() );
			c.setMensagem( txtaContatoMsg.getText() );
			c.setDtCadastro( new Date() );

			adicionaContato( c );
			msg( "msg", cboContatoAssunto.getSelectedItem().toString() );
			txtaReservaMsg.setText(null);
			cboReservaCategoria.setSelectedIndex(0);
			ftxtReservaDtInicio.setText(null);
			ftxtReservaDtFim.setText(null);
			txtReservaQtdAdulto.setText(null);
			txtReservaQtdCrianca.setText(null);
		}
		validar = false;
	}


	// LOGIN /////////////////////////////


	public void acesso() {

		cargaCliente();
		cargaFuncionario();

		//verifica se o botao está no contexto de login ou sair
		if ( btnLogin.getText().equals("Login") ){
			if ( !funcionarios.isEmpty() || !clientes.isEmpty() ){
				//verifica se os campos login e senha estão preenchidos
				if (!txtLogin.getText().isEmpty() 
						&& pwdSenha.getPassword().length != 0) {
					//verifica se um funcionario está se logando
					for (int f = 0; f < funcionarios.size(); f++) {
						if ( txtLogin.getText().equalsIgnoreCase(funcionarios.get(f).getLogin() )
								&& validaSenha(funcionarios.get(f).getSenha()) == true ) {
							//realiza o logon
							trocaPerfil(2);

							List<Logon> log = new ArrayList<Logon>();
							Logon l = new Logon();

							l.setIdUsuario( funcionarios.get(f).getId() );
							l.setTela( janela.getName() );
							l.setPerfil( 2 );
							l.setLogoff( 1 );
							l.setDtLogon( new Date() );
							log.add(l);
							logon.setLogon( log );
							logon.login();

							validar = true;
							lblMsg.setText( "- Bem vindo, " + funcionarios.get(f).getNome() );
							lblLogin.setVisible(false);
							txtLogin.setVisible(false);
							lblPwd.setVisible(false);
							pwdSenha.setVisible(false);
							btnLogin.setText("Sair");
							//msg("autorizado", funcionarios.get(f).getNome() );
						} else {
							//verifica se um cliente está se logando
							for (int c = 0; c < clientes.size(); c++) {
								if (txtLogin.getText().equalsIgnoreCase(clientes.get(c).getLogin())
										&& validaSenha(clientes.get(c).getSenha()) == true) {
									//realiza o logon
									trocaPerfil(1);

									List<Logon> log = new ArrayList<Logon>();
									Logon l = new Logon();

									l.setIdUsuario( clientes.get(c).getId() );
									l.setTela( janela.getName() );
									l.setPerfil( 1 );
									l.setLogoff( 1 );
									l.setDtLogon( new Date() );
									log.add(l);
									logon.setLogon( log );
									logon.login();

									validar = true;
									lblMsg.setText( "- Bem vindo, " + clientes.get(c).getNome() );
									lblLogin.setVisible(false);
									txtLogin.setVisible(false);
									lblPwd.setVisible(false);
									pwdSenha.setVisible(false);
									btnLogin.setText("Sair");
									alteraBtnReserva();
									preencheReserva();
									preencheContato();
									preencheCadastro();
									msg("autorizado", clientes.get(c).getNome() );
								} 
							}
						}
					}
					if (validar == false){
						msg("erroSenha", txtLogin.getText());
						senhas();
						return;
					} 

				} else {	
					msg("erroVazio", txtLogin.getText());
					senhas();
				}
				validar = false;
			} else {
				msg("erroDados", txtLogin.getText());
				senhas();
			}
		} else {
			//ao se deslogar, prepara a tela para o perfil visitante
			trocaPerfil(0);
			btnReservas.setVisible(false);
			lblMsg.setText(null);
			btnLogin.setText("Login");
			lblLogin.setVisible(true);
			txtLogin.setVisible(true);
			txtLogin.setText(null);
			lblPwd.setVisible(true);
			pwdSenha.setVisible(true);
			pwdSenha.setText(null);
			ativaCampo("reserva");
			ativaCampo("contato");
			ativaCampo("cadastro");
			logon.logoff();
		}
	}
	
	
	public void preencheReserva(){
		//Preenche a reserva com os dados do cliente logado e trava os campos

		cargaCliente();
		
		for (int i = 0; i < clientes.size(); i++) {

			if( clientes.get(i).getId()
					.equals( logon.getSession().get(0).getIdUsuario() )){
			
				txtReservaNome.setText( clientes.get(i).getNome() );
				txtReservaDocNum.setText( clientes.get(i).getDocumento() );
				cboReservaDocTipo.setSelectedItem( clientes.get(i).getDocTipo() );
				txtReservaEmail.setText( clientes.get(i).getEmail() );
				txtReservaTelefone.setText( clientes.get(i).getTelefone() );
				txtReservaCelular.setText( clientes.get(i).getCelular() );
				txtReservaCidade.setText( clientes.get(i).getCidade() );
				txtReservaEstado.setText( clientes.get(i).getEstado() );
				txtReservaPais.setText( clientes.get(i).getPais() ); 
				
				desativaCampo("reserva");
			}
		}
	}
	
	
	public void preencheContato(){
		//Preenche o contato com os dados do cliente logado e trava os campos

		cargaCliente();
		
		for (int i = 0; i < clientes.size(); i++) {

			if( clientes.get(i).getId()
					.equals( logon.getSession().get(0).getIdUsuario() )){
			
				txtContatoNome.setText( clientes.get(i).getNome() );;
				txtContatoEmail.setText( clientes.get(i).getEmail() );
				txtContatoTelefone.setText( clientes.get(i).getTelefone() );
				txtContatoCidade.setText( clientes.get(i).getCidade() );
				txtContatoEstado.setText( clientes.get(i).getEstado() );
				txtContatoPais.setText( clientes.get(i).getPais() ); 
				
				desativaCampo("contato");
			}
		}
	}
	
	
	public void preencheCadastro(){
		//Preenche o cadastro com os dados do cliente logado e trava os campos

		cargaCliente();
		
		for (int i = 0; i < clientes.size(); i++) {

			if( clientes.get(i).getId()
					.equals( logon.getSession().get(0).getIdUsuario() )){
				
                SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                
				txtCadastroNome.setText( clientes.get(i).getNome() );
				txtCadastroDocNum.setText( clientes.get(i).getDocumento() );
				cboCadastroDocTipo.setSelectedItem( clientes.get(i).getDocTipo() );
				txtCadastroEmail.setText( clientes.get(i).getEmail() );
				ftxtCadastroDtNasc.setText( dt.format( clientes.get(i).getDtNasc() ));
				txtCadastroTelefone.setText( clientes.get(i).getTelefone() );
				txtCadastroCelular.setText( clientes.get(i).getCelular() );
				txtCadastroEndereco.setText( clientes.get(i).getEndereco() );
				txtCadastroBairro.setText( clientes.get(i).getBairro() );
				txtCadastroCidade.setText( clientes.get(i).getCidade() );
				txtCadastroEstado.setText( clientes.get(i).getEstado() );
				txtCadastroCep.setText( clientes.get(i).getCep() );
				txtCadastroPais.setText( clientes.get(i).getPais() ); 
				
				desativaCampo("cadastro");
			}
		}
	}


	public void trocaPerfil( int op ){
		//realiza a troca das guias conforme o perfil

		switch ( op ){

		case 0:
			tabContainer.remove( painelClientePrincipal );
			tabContainer.remove( painelClienteServico );
			tabContainer.remove( painelClienteHistorico );
			tabContainer.remove( painelCadastro );
			tabContainer.remove( painelAdmPrincipal );
			tabContainer.remove( painelAdmChale );
			tabContainer.remove( painelAdmServico );
			
			tabContainer.add( "Principal", painelPrincipal );
			tabContainer.add( "Chalés", painelChale );
			tabContainer.add( "Lazer", painelLazer );
			tabContainer.add( "Serviços", painelServico );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Contato", painelContato );
			break;

		case 1:
			tabContainer.remove( painelPrincipal );
			tabContainer.remove( painelChale );
			tabContainer.remove( painelLazer );
			tabContainer.remove( painelServico );
			tabContainer.remove( painelReserva );
			tabContainer.remove( painelContato );
			tabContainer.remove( painelCadastro );
			tabContainer.remove( painelAdmPrincipal );
			tabContainer.remove( painelAdmChale );
			tabContainer.remove( painelAdmServico );
			
			tabContainer.add( "Principal", painelClientePrincipal );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Serviços", painelClienteServico );
			tabContainer.add( "Histórico", painelClienteHistorico );
			tabContainer.add( "Cadastro", painelCadastro );
			tabContainer.add( "Contato", painelContato );
			break;

		case 2:
			PrincipalCtrl.btnReservas.setVisible(false);
			formataTabela( "reserva", tabReserva );
			formataTabela( "servico", tabServico );

			tabContainer.remove( painelPrincipal );
			tabContainer.remove( painelChale );
			tabContainer.remove( painelLazer );
			tabContainer.remove( painelServico );
			tabContainer.remove( painelReserva );
			tabContainer.remove( painelContato );
			tabContainer.remove( painelCadastro );
			tabContainer.remove( painelClientePrincipal );
			tabContainer.remove( painelClienteServico );
			tabContainer.remove( painelClienteHistorico );
			
			tabContainer.add( "Principal", painelAdmPrincipal );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Chalés", painelAdmChale );
			tabContainer.add( "Serviço", painelAdmServico );
			tabContainer.add( "Cadastro", painelCadastro );
			break;
		}
	}


	// INFORMACOES ////////////////////////////


	public void preecheInfo(){
		//preenche as informações de apresentação na tela

		if(infos != null){
			for (int i = 0; i < infos.size(); i++) {
				txtaPrincipalInfo.setText( infos.get(i).getPrincipalInfo() ); 
				txtaPrincipalDetalhe.setText( infos.get(i).getPrincipalDetalhe() ); 
				txtaChaleInfo.setText( infos.get(i).getChaleInfo() ); 
				txtaChaleDetalhe.setText( infos.get(i).getChaleDetalhe() ); 
				txtaLazerInfo.setText( infos.get(i).getLazerInfo() ); 
				txtaLazerDetalhe.setText( infos.get(i).getLazerDetalhe() ); 
				txtaServicoInfo.setText( infos.get(i).getServicoInfo() ); 
				txtaServicoDetalhe.setText( infos.get(i).getServicoDetalhe() ); 
				txtaReservaInfo.setText( infos.get(i).getReservaInfo() ); 
				txtaContatoInfo.setText( infos.get(i).getContatoInfo() ); 
				txtaClienteInfo.setText( infos.get(i).getClienteInfo() );
				txtaClienteDetalhe.setText( infos.get(i).getClienteDetalhe() );
				txtaCadastroInfo.setText( infos.get(i).getCadastroInfo() );
			}
		}
	}


	// COMBOBOX /////////////////////////////////


	public void preencheCategoria(){

		cargaChale();

		//Ordenar alfabeticamente
		String[] lista = new String[chales.size()];
		for ( int i = 0; i < chales.size(); i++ ){
			String item = chales.get(i).getCategoria();		
			lista[i] = item;	

		}
		Arrays.sort(lista);

		//Verificar itens repetidos e adiciona na combobox
		cboReservaCategoria.addItem( "Selecione…" );
		for ( int i = 0; i < chales.size(); i++ ){
			if( !lista[i].equals(cboReservaCategoria.getItemAt(i) ))
				cboReservaCategoria.addItem( lista[i] );
		}
	}

	public void preencheTipoDoc(){

		String[] tipos = {
				"CPF",
				"RG",
				"CNH",
				"Passaporte"
		};
		//Adicionar na combobox reserva
		cboReservaDocTipo.addItem( "Selecione…" );
		for ( int i = 0; i < tipos.length; i++ ){
			cboReservaDocTipo.addItem( tipos[i] );
		}
		//Adicionar na combobox cadastro
		cboCadastroDocTipo.addItem( "Selecione…" );
		for ( int i = 0; i < tipos.length; i++ ){
			cboCadastroDocTipo.addItem( tipos[i] );
		}
	}

	public void imagensCombo(){

		imagem = new ImageIcon( diretorio + "/imagens/chale" 
				+ cboReservaCategoria.getSelectedIndex() + ".jpg" );
		lblReservaImg.setIcon( new ImageIcon( 
				imagem.getImage().getScaledInstance( 
						lblReservaImg.getWidth(), 
						lblReservaImg.getHeight(), 
						Image.SCALE_DEFAULT )));
	}

	public void preencheAssunto(){

		String[] assuntos = {
				"Central de Reservas",
				"Reservas para Grupos",
				"Reservas para Eventos Corporativos",
				"Sugestões, Elogios ou Reclamações",
				"Outros"
		};
		//Adicionar na combobox
		cboContatoAssunto.addItem( "Selecione…" );
		for ( int i = 0; i < assuntos.length; i++ ){
			cboContatoAssunto.addItem( assuntos[i] );
		}
	}


	// TABELA //////////////////////////////////


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] colunasTabela( String opt ){
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


	public Object[][] linhasTabela( String opt ){
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


	public void formataTabela( String opt, JTable tabela ){
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


	// METODOS DE SUPORTE //////////////////////


	public void atualizaValor( JTable tabela ){
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
					msg("", "ERRO " + e.getCause() 
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


	public void temporizador(){
		// configura o tempo de troca das imagens da tela principal

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				imagensRandom();
			}
		}, 2000, 3000);
	}


	public void imagensRandom(){
		// verifca qual guia está sendo exibida e troca as imagens e registra a guia no logon

		Integer guiaAtiva = tabContainer.getSelectedIndex();

		Random gerador = new Random(); 
		int numero = gerador.nextInt(4);

		switch ( guiaAtiva ){
		case 0:
			imagem = new ImageIcon( diretorio + "/imagens/externa" + numero + ".jpg" );
			lblPrincipalImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblPrincipalImg.getWidth(), 
							lblPrincipalImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !logon.getSession().isEmpty() )
				logon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(0) );

			break;
		case 1:
			imagem = new ImageIcon( diretorio + "/imagens/chale" + numero + ".jpg" );
			lblChaleImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblChaleImg.getWidth(), 
							lblChaleImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !logon.getSession().isEmpty() )
				logon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(1) );

			break;
		case 2:
			imagem = new ImageIcon( diretorio + "/imagens/lazer" + numero + ".jpg" );
			lblLazerImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblLazerImg.getWidth(), 
							lblLazerImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !logon.getSession().isEmpty() )
				logon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(2) );

			break;
		case 3:
			imagem = new ImageIcon( diretorio + "/imagens/servico" + numero + ".jpg" );
			lblServicoImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblServicoImg.getWidth(), 
							lblServicoImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !logon.getSession().isEmpty() )
				logon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(3) );
			break;
		case 4:
			if( !logon.getSession().isEmpty() )
				logon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(4) );
			break;
		case 5:
			if( !logon.getSession().isEmpty() )
				logon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(5) );
			break;
		}
	}


	public void senhas(){
		// limpa os campos login e senha e retorna o foco

		txtLogin.setText(null);
		pwdSenha.setText(null);

		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				txtLogin.requestFocus();  
			}  
		});
	}


	public boolean validaSenha( String validaSenha ) {  
		// verifica o cumprimento da senha por caracter

		if (validaSenha != null){
			char[] senha = pwdSenha.getPassword();
			if (senha.length != validaSenha.length()) {  
				return false;
			} else {  
				for (int i = 0; i < senha.length; i++) {   
					if (senha[i] != validaSenha.charAt(i)) {  
						return false; 
					}  
				}  
			}  
			return true; 
		}
		return false;
	}


	public void limpaCampo(){
		// limpa todos os campos da tela

		Integer guiaAtiva = tabContainer.getSelectedIndex();
		Component[] painelAtivo = null;

		switch ( guiaAtiva){
		case 4:
			painelAtivo = painelReserva.getComponents();
			break;

		case 5:
			painelAtivo = painelContato.getComponents();
			break;
		}
		for ( Component c : painelAtivo ) {
			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				l.setText(null);
				l.setBackground(new Color(255,255,255));
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				l.setValue(null);
				l.setBackground(new Color(255,255,255));
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				l.setSelectedIndex(0);
				l.setBackground(new Color(255,255,255));
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if( !l.getName().contains( "Info") ){
					l.setText(null);
					l.setBackground(new Color(255,255,255));
				}
			}
		}
	}
	
	public void ativaCampo( String guia ){
		// ativa os campos para edição

		Component[] painel = null;

		switch ( guia ){

		case "reserva":
			painel = painelReserva.getComponents();
			break;
			
		case "contato":
			painel = painelContato.getComponents();
			break;
		
		case "cadastro":
			painel = painelCadastro.getComponents();
			break;
		}

		for ( Component c : painel ) {

			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				l.setEnabled(true);
				if( guia != "cadastro" )
					l.setText(null);
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				l.setEnabled(true);
				if( guia != "cadastro" )
					l.setText(null);
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				l.setEnabled(true);
				if( guia != "cadastro" )
					l.setSelectedIndex(0);
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if( !l.getName().contains( "Info") ){
					l.setText(null);
					if( guia != "cadastro" )
						l.setEnabled(true);
				}
			}
		}
	}


	public void desativaCampo( String guia ){
		// desativa os campos para edição

		if ( logon.getSession().get(0).getPerfil() != 2 ){

			Component[] painel = null;

			switch ( guia ){

			case "reserva":
				painel = painelReserva.getComponents();
				break;
				
			case "contato":
				painel = painelContato.getComponents();
				break;
				
			case "cadastro":
				painel = painelCadastro.getComponents();
				break;
			}

			for ( Component c : painel ) {

				if ( c instanceof JTextField ) {
					JTextField l = ( JTextField )c;
					if ( l.isEnabled() 
							&& !l.getName().equalsIgnoreCase("adultos")
							&& !l.getName().equalsIgnoreCase("criancas") 
							&& !l.getName().equalsIgnoreCase("inicio")
							&& !l.getName().equalsIgnoreCase("fim")){

						l.setEnabled(false);
					}
				}
				if ( c instanceof JFormattedTextField ) {
					JFormattedTextField  l = ( JFormattedTextField )c;
					if ( l.isEnabled() 
							&& !l.getName().equalsIgnoreCase("inicio")
							&& !l.getName().equalsIgnoreCase("fim") ){
						l.setEnabled(false);
					}
				}
				if (c instanceof JComboBox ) {
					@SuppressWarnings("unchecked")
					JComboBox<String> l = ( JComboBox<String> )c;
					if ( l.isEnabled() 
							&& l.getName() != "categoria"
							&& l.getName() != "assunto"){

						l.setEnabled(false);
					}
				}
				if ( c instanceof JTextArea ) {
					JTextArea  l = ( JTextArea )c;
					if ( !l.getName().equalsIgnoreCase( "Observações" )
							&& !l.getName().contains( "Info") ){
						if ( l.isEnabled() ){

							l.setEnabled(false);
						}
					}
				}
			}
		}
	}


	public void validaCampo(){
		// valida todos os campos da tela se foram preenchidos

		boolean vazio = false;
		String campo = null;
		Integer guiaAtiva = tabContainer.getSelectedIndex();
		Component[] painelAtivo = null;

		switch ( guiaAtiva ){
		
		case 1: //perfil funcionario
			painelAtivo = painelReserva.getComponents();
			break;
			
		case 4:
			painelAtivo = painelReserva.getComponents();
			break;

		case 5:
			painelAtivo = painelContato.getComponents();
			break;
		}

		for ( Component c : painelAtivo ) {

			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				if ( l.getText().isEmpty() && l.isVisible() ){
					if ( !l.getName().equalsIgnoreCase( "pesquisa")){
						vazio = true;
						campo = l.getName();
						l.requestFocus();
						l.setBackground(new Color(255,240,245));
					}
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				if ( l.getText().isEmpty() || l.getText().equalsIgnoreCase("  /  /    ")){
					vazio = true;
					campo = l.getName();
					l.requestFocus();
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				if ( l.getSelectedIndex() == 0){
					vazio = true;
					campo = l.getName();
					l.requestFocus();
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if ( !l.getName().equalsIgnoreCase( "Observações")){
					if ( l.getText().isEmpty() ){
						vazio = true;
						campo = l.getName();
						l.requestFocus();
						l.setBackground(new Color(255,240,245));
					} else {
						l.setBackground(new Color(255,255,255));
					}
				}
			}
		}
		if ( vazio == true ){
			msg( "erroCampo", campo );
			validar = false;
			return;
		} else {
			validar = true;
			if( guiaAtiva == 4 ){
				validaData( ftxtReservaDtInicio, ftxtReservaDtFim );
			}
		}
	}


	public void validaData( JFormattedTextField dtInicio, JFormattedTextField dtFim ){
		//valida entre as datas inicio e final da reserva

		Date inicio = null;
		Date fim = null;
		Date atual = null;
		Date dt = new Date();
		String dta;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		dta = sdf.format( dt );

		try {
			inicio = sdf.parse( dtInicio.getText() );
			fim = sdf.parse( dtFim.getText() );
			atual = sdf.parse( dta );
		} catch (ParseException e) {
			msg("", "LogonCtrl : cargaLogon. Erro: " + e.getCause() 
					+ "\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}

		//verifica se a data inicial é inferior à data atual do sistema
		if( !inicio.equals( atual ) ){
			if ( !inicio.after( atual )){
				msg("erroDataInicial","");
				dtInicio.setText(null);
				dtInicio.requestFocus();
				validar = false;
				return;
				//após corrigir data inicial, verifica se a data final é anterior à inicial
			} else if( fim.before(inicio) ){
				msg("erroDataFinal","");
				dtFim.setText(null);
				dtFim.requestFocus();
				validar = false;
				return;
			}
		} else if( fim.before(inicio) ){
			//verifica se a data final é anterior à inicial
			msg("erroDataFinal","");
			dtFim.setText(null);
			dtFim.requestFocus();
			validar = false;
			return;
		} else {
			validar = true;
		}
	}

	public void relogio(){

		int delay = 1000; // delay de 1 seg.
		int interval = 1000; // intervalo de 1 minuto.
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {

				SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
				lblRelogio.setText(dateFormat.format(new Date()) + " horas");
			}
		}, delay, interval);
	}




	// JANELA //////////////////////////////////


	public void abrir ( String nome ){

		switch ( nome ){

		case "reservas":
			ReservaFrm reserva;
			try {
				reserva = new ReservaFrm();
				reserva.setVisible(true);
				reserva.setAlwaysOnTop ( false );
			} catch (ParseException e) {
				msg("", "ERRO " + e.getCause() 
						+ "\n\nLocal: Principaltrl >  abrir()."  
						+ "\n\nMensagem:\n" + e.getMessage() );
				//e.printStackTrace();
			}
			break;
		}
	}


	// BOTAO //////////////////////////////////


	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if( source == btnLogin ){
				acesso();
			}
			if( source == pwdSenha ){
				acesso();
			}
			if( source == txtPesquisa ){
				msg( "construir", "" );
			}
			if( source == btnReservas ){
				
				abrir("reservas");
			}
			if( source == btnReservaEnviar ){
				try {
					adicionaReserva ();
				} catch (ParseException e1) {
					msg("", "ERRO " + e1.getCause() 
							+ "\n\nLocal: Principaltrl >  acionar()."  
							+ "\n\nMensagem:\n" + e1.getMessage() );
					//e1.printStackTrace();
				}
			}
			if( source == btnContatoEnviar ){
				adicionaContato();
			}
			if( source == btnPesquisar ){
				msg( "construir", "" );
			}
			if( source == btnReservaLimpar ){
				limpaCampo();
			}
			if( source == btnContatoLimpar ){
				limpaCampo();
			}
			if( source == cboReservaCategoria ){
				imagensCombo();
			}
			if( source == btnReservaEditar ){
				cancelaReserva( tabReserva );
			}
			if( source == btnServicoEditar ){
				msg( "construir", "" );
			}
			if( source == btnCadastroLimpar ){
				limpaCampo();
			}
			if( source == btnCadastroExcluir ){
				msg( "construir", "" );
			}
			if( source == btnCadastroEditar ){
				ativaCampo("cadastro");
				msg( "construir", "" );
			}
			if( source == btnCadastroSalvar ){
				desativaCampo("cadastro");
				msg( "construir", "" );
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
				msg( "sair", "" );
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
				Object sourceTab = e.getSource();

				//desabilita a tecla TAB
				if( sourceTab == txtaReservaMsg ){
					if(e.getModifiers() > 0) txtaReservaMsg.transferFocusBackward();
					else txtaReservaMsg.transferFocus(); 
					e.consume();
				}
				if( sourceTab == txtaContatoMsg ){
					if(e.getModifiers() > 0) txtaContatoMsg.transferFocusBackward();
					else txtaContatoMsg.transferFocus(); 
					e.consume();
				}
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

			String tipo = "";
			String tam = "";

			//verifica qual componenete e configura o tipo de entrada (alfa ou numero)
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
			if( source == txtaReservaMsg ){
				tam = txtaReservaMsg.getText();
				lblReservaMsg.setText("Observações (" + (tam.length()+1) + " de 300):");
				if(tam.length() > 300){
					txtaReservaMsg.setBackground(new Color(255,240,245));
					return;
				} else {
					txtaReservaMsg.setBackground(new Color(255,255,255));
				}
			}
			if( source == txtaContatoMsg ){
				tam = txtaContatoMsg.getText();
				lblContatoMsg.setText("Sua mensagem (" + (tam.length()+1) + " de 300):");
				if(tam.length() > 300){
					txtaContatoMsg.setBackground(new Color(255,240,245));
					return;
				} else {
					txtaContatoMsg.setBackground(new Color(255,255,255));
				}
			}
			if( source == txtContatoNome ){
				tipo = "alfa";
			}
			if( source == txtContatoCidade ){
				tipo = "alfa";
			}
			if( source == txtContatoEstado ){
				tipo = "alfa";
			}
			if( source == txtContatoPais ){
				tipo = "alfa";
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
					msg( "construir", "" );
				}
			}
		}
	};


	// MENSAGENS //////////////////////////////


	public void msg( String tipo, String mensagem ) {

		switch ( tipo ) {

		case "msg":

			JOptionPane.showMessageDialog(null, 
					"CONFIRMADO!\n\nSua mensagem com o assunto:\n" + mensagem + "\nfoi enviada.", 
					"Mensagem Recebida", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/email.png" ));
			validar = true;
			break;

		case "erroChale":
			JOptionPane.showMessageDialog(null, 
					"INDISPONÍVEL\n\nOs Chalés da categoria " + mensagem
					+ "\nnão estão disponíveis neste período.\n\nPor favor, selecione um período ou categoria diferente.", 
					"Atenção", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/info.png" ));
			break;

		case "erroCampo":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nCampo '" + mensagem + "' não preenchido."
							+ "\nPor favor, digite o dado solicitado pelo campo.", 
							"Erro", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/write.png" ));
			break;

		case "erroSelecao":
			JOptionPane.showMessageDialog(null, 
					"Seleção inválida!\n\n" 
							+ "Por favor, selecione uma categoria.", 
							"Erro", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "erroDataInicial":
			JOptionPane.showMessageDialog(null, 
					"ERRO nas datas!\n\n" 
							+ "Por favor, digite uma data de Chegada\nigual ou superior à data de atual.", 
							"Erro", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "erroDataFinal":
			JOptionPane.showMessageDialog(null, 
					"ERRO nas datas!\n\n" 
							+ "Por favor, digite uma data de Chegada\nanterior ou igual à data de Partida.", 
							"Erro", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "autorizado":
			JOptionPane.showMessageDialog(null, 
					"Seja bem-vindo, " + mensagem 
					+ "!\n\nDúvidas, elogios e sugestões "
					+ "\npodem ser enviadas através da guia Contato.", 
					"Acesso", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/user.png" ));
			break;

		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"CAMPOS NÃO PREENCHIDOS.\n\nPor favor, verifique sua digitação \ne digite o Usuário e a Senha.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "erroSenha":
			JOptionPane.showMessageDialog(null, 
					"USUÁRIO OU SENHA ERRADOS.\n\nO usuário '" + mensagem + "' ou sua senha não conferem!\n"
							+ "Verifique sua digitação e tente novamente.",
							"Erro", 
							JOptionPane.PLAIN_MESSAGE, 
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;
			
		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione uma Reserva para cancelar.", 
					"Reserva não selecionada", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "reservaCancelar":
			int retirar = JOptionPane.showOptionDialog(null, 
					"Deseja editar ou excluir a Reserva " + mensagem + "?",
					"Editar ou Excluir Reserva", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/alert.png" ), 
					new String[]{"Excluir", "Editar","Cancelar"}, "Cancelar");
			if(retirar == JOptionPane.YES_OPTION) {
				validar = true;
			}
			else if(retirar == JOptionPane.NO_OPTION) {
				msg( "construir", "" );
				validar = false;
			}
			else if(retirar == JOptionPane.CANCEL_OPTION) {
				validar = false;
			}
			break;
			
		case "reservaAtiva":
			JOptionPane.showMessageDialog(null, 
					"A Reserva " + mensagem 
					+ " não pode ser cancelada pois está ativa.\n"
					+ "É necessário a sua desativação para efetivar seu cancelamento", 
					"Reserva Ativa", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/alert.png" ));
			break;

		case "construir":
			JOptionPane.showMessageDialog(null, 
					"Em construção!\n\nEsta função ainda não foi implementada.", 
					"Sem implementação", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/builder.png" ));
			break;

		case "sair":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o fechamento" 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/alert.png" ), exit, exit[1] );
			if ( fechar == 0 ) {
				validar = true;
			} else {
				validar = false;
			}
			if(validar == true){
				System.exit(0);
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, 
					"ERRO de sistema!\n\n" 
							+ mensagem + "\n" +  this, 
							"Erro", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png"));
		}
	}
}
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.FuncionarioDAO;
import edu.pousada.dao.FuncionarioDAOImpl;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Funcionario;
import edu.pousada.entity.Logon;

public class LoginCtrl {

	private PrincipalCtrl ctrlPrincipal;
	private JLabel lblLogin;
	private JLabel lblPwd;
	private JLabel lblMsg;
	private JTextField txtLogin;
	private JPasswordField pwdSenha;
	private JButton btnLogin;

	private LogonCtrl ctrlLogon = LogonCtrl.getInstance();
	private static boolean validar;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	public LoginCtrl(
			JLabel lblLogin, 
			JLabel lblPwd, 
			JLabel lblMsg, 
			JTextField txtLogin, 
			JPasswordField pwdSenha, 
			JButton btnLogin
			){

		this.pwdSenha = pwdSenha;
		this.lblLogin = lblLogin;
		this.lblPwd = lblPwd;
		this.lblMsg = lblMsg;
		this.txtLogin = txtLogin;
		this.btnLogin = btnLogin;

		ctrlPrincipal = new PrincipalCtrl();
	}


	//  DAO  //////////////////////////////////////


	public void cargaCliente(){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			clientes = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void cargaFuncionario(){

		FuncionarioDAO dao = new FuncionarioDAOImpl();
		try {
			funcionarios = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLoginCtrl > cargaFuncionario()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	//  CRUD  ///////////////////////////////////////


	public void acesso() {

		// valida se houve login
		validar = false;
		
		cargaCliente();
		cargaFuncionario();

		// verifica se o botao está no contexto de login ou sair
		if ( btnLogin.getText().equals("Login") ){
			if ( !funcionarios.isEmpty() || !clientes.isEmpty() ){

				// verifica se os campos login e senha estão preenchidos
				if (!txtLogin.getText().isEmpty() 
						&& pwdSenha.getPassword().length != 0) {

					// verifica se um funcionario está se logando e valida
					for (int f = 0; f < funcionarios.size(); f++) {
						if ( txtLogin.getText().equalsIgnoreCase(funcionarios.get(f).getLogin() )
								&& validaSenha(funcionarios.get(f).getSenha()) != false ) {

							// realiza o logon
							validar = true;

							List<Logon> log = new ArrayList<Logon>();
							Logon l = new Logon();

							l.setIdUsuario( funcionarios.get(f).getId() );
							l.setTela( ctrlPrincipal.getJanela() );
							l.setPerfil( 2 );
							l.setLogoff( 1 );
							l.setDtLogon( new Date() );
							log.add(l);
							ctrlLogon.setLogon( log );

							//prepara as guias

							ctrlLogon.login();
							ctrlPrincipal.trocaPerfil(2);
							lblMsg.setText( "- Bem vindo, " + funcionarios.get(f).getNome() );
							lblLogin.setVisible(false);
							txtLogin.setVisible(false);
							lblPwd.setVisible(false);
							pwdSenha.setVisible(false);
							btnLogin.setText("Sair");
							//msg("autorizado", funcionarios.get(f).getNome() );
						} else if( f == funcionarios.size()-1 ){

							// ao final do funcionario, verifica se um cliente está se logando e valida
							for (int c = 0; c < clientes.size(); c++) {
								if (txtLogin.getText().equalsIgnoreCase(clientes.get(c).getLogin())
										&& validaSenha(clientes.get(c).getSenha()) != false){

									// realiza o logon
									validar = true;

									List<Logon> log = new ArrayList<Logon>();
									Logon l = new Logon();

									l.setIdUsuario( clientes.get(c).getId() );
									l.setTela( ctrlPrincipal.getJanela() );
									l.setPerfil( 1 );
									l.setLogoff( 1 );
									l.setDtLogon( new Date() );
									log.add(l);
									ctrlLogon.setLogon( log );
									ctrlLogon.login();

									// prepara as guias
									ctrlPrincipal.trocaPerfil(1);
									lblMsg.setText( "- Bem vindo, " + clientes.get(c).getNome() );
									lblLogin.setVisible(false);
									txtLogin.setVisible(false);
									lblPwd.setVisible(false);
									pwdSenha.setVisible(false);
									btnLogin.setText("Sair");
									ReservaCtrl.alteraBtnReserva();

									ReservaCtrl.preencheReserva();
									ContatoCtrl.preencheContato();
									CadastroCtrl.preencheCadastro();
									CadastroCtrl.alteraBotao( true );
									MensagensCtrl.msg("autorizado", clientes.get(c).getNome() );
								}
							}
							if ( validar != true ){
								MensagensCtrl.msg("erroSenha", txtLogin.getText());
								senhas();
								return;
							}
						}
					} 

				} else {	
					MensagensCtrl.msg("erroVazio", txtLogin.getText());
					senhas();
				}
			} else {
				// caso não exista nenhum cadastro, pede um cadastro inicial
				// não usado pois o sistema cria um cadastro adm padrão no início
				if( MensagensCtrl.msg("erroCadastro", txtLogin.getText()) != false){
					ctrlPrincipal.trocaPerfil(4);
				} else {
					ctrlPrincipal.trocaPerfil(3);
				}
				senhas();
			}
		} else {
			// ao se deslogar, prepara a tela para o perfil visitante
			ctrlLogon.logoff();
			ctrlPrincipal.trocaPerfil(0);
			ReservaCtrl.btnReservas.setVisible(false);
			lblMsg.setText(null);
			btnLogin.setText("Login");
			lblLogin.setVisible(true);
			txtLogin.setVisible(true);
			txtLogin.setText(null);
			lblPwd.setVisible(true);
			pwdSenha.setVisible(true);
			pwdSenha.setText(null);
			CamposCtrl.ativa("reserva");
			CamposCtrl.ativa("contato");
			CamposCtrl.ativa("cadastro");
			CamposCtrl.limpa( "cadastro" );
		}
	}


	//  SUPORTE  ////////////////////////////////

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


	public Boolean validaSenha( String validaSenha ) {  
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


	// BOTAO //////////////////////////////////


	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			// verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if(source == pwdSenha){
				acesso();
			}
			if(source == btnLogin){
				acesso();
			}
		}
	};


	// CONTROLE TECLA ///////////////////////////////


	public KeyListener teclas = new KeyListener() {  

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

				MensagensCtrl.msg( "sair", "" );
				break;
			case KeyEvent.VK_DELETE:
				break;
			case 8: //MAC OSX: DELETE
				break;
			case KeyEvent.VK_TAB:
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};
}
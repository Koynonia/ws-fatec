/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 03/05/2016
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import boundary.FrmCliente;
import boundary.FrmLivro;
import boundary.FrmLogin;
import boundary.FrmAdministrador;
import dao.ArquivoCliente;
import dao.ArquivoSessao;
import dao.ArquivoAdministrador;
import entity.Cliente;
import entity.Sessao;
import entity.Administrador;


public class LoginController implements ComponentListener {

	private FrmLogin janela; 
	private FrmAdministrador janelaUsuario; 
	private FrmCliente janelaCliente;
	private FrmLivro janelaLivro;
	private JTextArea txtaAviso; 
	private JTextField txtUsuario; 
	private JPasswordField pwdSenha; 
	private JLabel lblUsuario; 
	private JLabel lblSenha; 
	private JButton btnLivros; 
	private JButton btnUsuarios; 
	private JButton btnClientes; 
	private JButton btnCadastrar; 
	private JButton btnEntrar; 
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "administrador";
	private PrincipalController principalCtrl;
	private List<Administrador> usuarios;
	private List<Cliente> clientes;
	private List<Sessao> usuarioAtivo;
	private ArquivoAdministrador dao = new ArquivoAdministrador();
	private ArquivoCliente daoCliente = new ArquivoCliente();
	private ArquivoSessao daoSessao = new ArquivoSessao();
	private SessaoController logon = SessaoController.getInstance();
	private static int contador = 1;
	private boolean validar;

	public LoginController (
			FrmLogin janela, 
			PrincipalController principalCtrl, 
			JTextArea txtaAviso, 
			JTextField txtUsuario, 
			JPasswordField pwdSenha, 
			JLabel lblUsuario, 
			JLabel lblSenha, 
			JButton btnLivros, 
			JButton btnUsuarios, 
			JButton btnClientes, 
			JButton btnCadastrar, 
			JButton btnEntrar 
			) {

		this.janela = janela;
		this.principalCtrl = principalCtrl;
		this.txtaAviso = txtaAviso;
		this.txtUsuario = txtUsuario;
		this.pwdSenha = pwdSenha;
		this.lblUsuario = lblUsuario;
		this.lblSenha = lblSenha;
		this.btnLivros = btnLivros;
		this.btnUsuarios = btnUsuarios;
		this.btnClientes = btnClientes;
		this.btnCadastrar = btnCadastrar;
		this.btnEntrar = btnEntrar;
		this.usuarios = new ArrayList<Administrador>();
		this.clientes = new ArrayList<Cliente>();
		this.usuarioAtivo = new ArrayList<Sessao>();
		
		dados();
		tela();
	}
	
	
	//  TELA    ///////////////
	
	public void dados(){
		
		logon.rastrear( janela.getName() );
		lerCliente();
		lerAdministrador();
	}
	

	public void tela(){
		
		campos();
	}

	public void campos(){

		if ( logon.getLogon().size() > 0 && 
				logon.getLogon().get(0).getNivel().equalsIgnoreCase("Administrador") ){
			
			btnUsuarios.setVisible(true);
			btnLivros.setVisible(true);
			btnClientes.setText( "Clientes" );
			btnClientes.setBounds(395, 160, 117, 29);
			txtaAviso.setVisible(true);
			txtaAviso.setText(
					"Bem-vindo "+ logon.getLogon().get(0).getUsuario() 
					+ "!\n\n\nEste painel acessa funcões administrativas."
					+ "\nSelecione ao lado a função desejada.");
			
		} else if ( !logon.getLogon().isEmpty() ){
			btnClientes.setText( "Seus Dados" );
			btnLivros.setVisible(false);
			txtaAviso.setVisible(true);
			txtaAviso.setText(
					"Olá "+ logon.getLogon().get(0).getUsuario() 
					+ "!\n\nAqui você acessa seus dados.\n"
					+ "Você pode sair da Livraria Digital "
					+ "e retornar no momento que preferir…\n"
					+ "No entanto, os itens adicionados no Carrinho serão perdidos!");
		}
		
		if ( !logon.getLogon().isEmpty() ){
			
			lblUsuario.setVisible(false);
			lblSenha.setVisible(false);
			txtUsuario.setVisible(false);
			pwdSenha.setVisible(false);
			btnClientes.setVisible(true);
			btnCadastrar.setVisible(false);
			btnEntrar.setText("Sair");			
		} else {			
			lblUsuario.setVisible(true);
			lblSenha.setVisible(true);
			txtUsuario.setVisible(true);
			pwdSenha.setVisible(true);
			btnUsuarios.setVisible(false);
			btnLivros.setVisible(false);
			btnClientes.setVisible(false);
			btnCadastrar.setVisible(true);
			txtaAviso.setVisible(false);
			btnEntrar.setText("Acessar");
		}
	}
	
	
	public void senhas(){

		txtUsuario.setText(null);
		pwdSenha.setText(null);

		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				txtUsuario.requestFocus();  
			}  
		});
	}
	
	
	public boolean validarSenha( String validaSenha ) {  

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
	
	
	public void abrirJanela ( String nome ){
		
		switch ( nome ){

		case "administrador":
			if( janelaUsuario == null ){
				FrmAdministrador usuario;
				usuario = new FrmAdministrador();
				usuario.setVisible(true);
				fechar();
			} else {
				janelaUsuario.setVisible(true);
				fechar();
			}
			break;
			
		case "cliente":
			if( janelaCliente == null ){
				FrmCliente cliente;
				try {
					if ( !logon.getLogon().isEmpty() ){
						cliente = new FrmCliente( logon.getLogon().get(0).getId() );
					} else {
						cliente = new FrmCliente( null );
					}
					cliente.setVisible(true);
					principalCtrl.fechar();
					fechar();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				janelaCliente.setVisible(true);
				principalCtrl.fechar();
				fechar();
			}
			break;
			
		case "livro":
			if( janelaLivro == null ){
				FrmLivro livro;
				try {
					livro = new FrmLivro();
					livro.setVisible(true);
					principalCtrl.fechar();
					fechar();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				janelaLivro.setVisible(true);
				principalCtrl.fechar();
				fechar();
			}
			break;
		}
	}


	// CRUD //////////////////////////

	public void entrar() {

		if ( btnEntrar.getText().equals("Acessar") ){
			if ( !usuarios.isEmpty() || !clientes.isEmpty() ){
				if (!txtUsuario.getText().isEmpty() 
						&& pwdSenha.getPassword().length != 0) {
					for (int i = 0; i < usuarios.size(); i++) {
						
						if (txtUsuario.getText().equalsIgnoreCase(usuarios.get(i).getUsuario())
								&& validarSenha(usuarios.get(i).getSenha()) == true) {
							logon.registrar( 
									usuarios.get(i).getId(), 
									usuarios.get(i).getUsuario(), 
									usuarios.get(i).getNivel(), 
									janela.getName());				
							validar = true;
							fechar();
							msg("autorizado", txtUsuario.getText());
						}
					}

					for (int i = 0; i < clientes.size(); i++) {
						if (txtUsuario.getText().equalsIgnoreCase(clientes.get(i).getUsuario())
								&& validarSenha(clientes.get(i).getSenha()) == true) {
							logon.registrar( 
									clientes.get(i).getCpf(), 
									clientes.get(i).getUsuario(), 
									clientes.get(i).getNome(), 
									janela.getName());				
							validar = true;
							fechar();
							msg("autorizado", txtUsuario.getText());
						} 
					}

					if (validar == false){
						msg("erroSenha", txtUsuario.getText());
						senhas();
						//limpaCampos();
					} 
				} else {	
					msg("erroVazio", txtUsuario.getText());
					senhas();
				}
				validar = false;
			} else {
				msg("erroDados", txtUsuario.getText());
				senhas();
			}
		} else {
			logon.sair();
			fechar();
		}
	}
	
	
	public void lerCliente() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			daoCliente.lerArquivo( diretorio + "dados/", "cliente" );
			linha = daoCliente.getBuffer();
			String[] listaItens = linha.split(";");
			for (String s : listaItens) {
				String text = s.replaceAll(".*: ", "");
				lista.add(text);
				if (s.contains("---")) {
					Cliente cliente = new Cliente();
					cliente.setUsuario( lista.get(0) );
					cliente.setSenha( lista.get(1) );
					cliente.setNome( lista.get(3) );
					cliente.setCpf( lista.get(5) );
					clientes.add(cliente);
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void lerAdministrador() {

		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		try {
			dao.lerArquivo( diretorio + "dados/", arquivo );
			linha = dao.getBuffer();
			String[] listaUsuario = linha.split(";");
			for (String s : listaUsuario) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					Administrador usuario = new Administrador();
					usuario.setId(list.get(0));
					usuario.setUsuario(list.get(1));
					usuario.setSenha(list.get(2));
					usuario.setNivel(list.get(3));
					usuarios.add(usuario);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void encerraSessao(List<Sessao> log){

		for (int i = 0; i < usuarioAtivo.size(); i++) {
			usuarioAtivo.remove(i);		
		}	
		File f = new File(diretorio + "dados/" + "log" );
		f.delete();
		for (Sessao usuarioAtivo : log) {
			try {
				daoSessao.escreverArquivo(diretorio  + "dados/", "log", "", usuarioAtivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// MENSAGENS //////////////////////////////

	public void msg(String tipo, String mensagem) {
		
		janela.setAlwaysOnTop ( false );

		switch (tipo) {
		
		case "autorizado":
			JOptionPane.showMessageDialog(null, 
					"Seja bem-vindo à Livraria Digital, " + mensagem + "!", 
					"Acesso Permitido", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/ok.png" ));
			break;

		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\n\nPor favor, digite o Usuário e a Senha.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroSenha":
			JOptionPane.showMessageDialog(null, 
					"O usuário '" + mensagem + "' ou sua senha não conferem!\n\n"
							+ "Verifique sua digitação e tente novamente.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroDados":
			JOptionPane.showMessageDialog(null, 
					"O usuário '" + mensagem + "' não pode ser autenticado!!\n\n"
							+ "Houve um problema de comunicação com a Base de Dados.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
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
	
	
	//  COMPORTAMENTO JANELA  //////////////////////////

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


	// CONTROLE BOTAO //////////////////////////////


	public ActionListener entrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			entrar();
		}
	};
	
	public ActionListener fechar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			fechar();
		}
	};
	
	public ActionListener janelas = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//verifica qual botãoo está solicitando a ação
			Object source = e.getSource();

			if(source == btnCadastrar){
				if ( logon.getLogon().size() > 0 && 
						logon.getLogon().get(0).getNivel().equalsIgnoreCase("Administrador") ){
					abrirJanela( "administrador" );
				} else {
					abrirJanela( "cliente" );
				}
			}
			if(source == btnLivros){

				abrirJanela( "livro" );
			}
			if(source == btnClientes){

				abrirJanela( "cliente" );
			}
			if(source == btnUsuarios){

				abrirJanela( "administrador" );
			}
		}
	};
	
	
	// CONTROLE TECLA ///////////////////////////////


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
			case 8: //MAC OSX: DELETE
			break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};
	
	// CONTROLE MOUSE ///////////////////////////////


	public MouseListener limpaCampo = new MouseListener() {

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

			if (contador == 1) {
				txtUsuario.setText(null);
				contador += 1;
			}
		}
	};

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}

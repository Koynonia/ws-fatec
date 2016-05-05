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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import boundary.FrmLogin;
import boundary.FrmUsuario;
import dao.ArquivoSessao;
import dao.ArquivoUsuario;
import entity.Sessao;
import entity.Usuario;


public class LoginController implements ComponentListener {

	private FrmLogin janela; 
	private FrmUsuario janelaUsuario; 
	private JTextArea txtaAviso; 
	private JTextField txtUsuario; 
	private JPasswordField pwdSenha; 
	private JLabel lblUsuario; 
	private JLabel lblSenha; 
	private JButton btnCadastrar; 
	private JButton btnEntrar; 
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "usuario";
	private List<Usuario> usuarios;
	private List<Sessao> usuarioAtivo;
	private ArquivoUsuario dao = new ArquivoUsuario();
	private ArquivoSessao daoSessao = new ArquivoSessao();
	private SessaoController logon = SessaoController.getInstance();
	private static int contador = 1;
	private boolean validar;

	public LoginController (
			FrmLogin janela, 
			JTextArea txtaAviso, 
			JTextField txtUsuario, 
			JPasswordField pwdSenha, 
			JLabel lblUsuario, 
			JLabel lblSenha, 
			JButton btnCadastrar, 
			JButton btnEntrar
			) {

		this.janela = janela;
		this.txtaAviso = txtaAviso;
		this.txtUsuario = txtUsuario;
		this.pwdSenha = pwdSenha;
		this.lblUsuario = lblUsuario;
		this.lblSenha = lblSenha;
		this.btnCadastrar = btnCadastrar;
		this.btnEntrar = btnEntrar;
		this.usuarios = new ArrayList<Usuario>();
		this.usuarioAtivo = new ArrayList<Sessao>();
		
		dados();
		tela();
	}
	
	
	//  TELA    ///////////////
	
	public void dados(){
		
		logon.rastrear( janela.getName() );
		lerUsuarios();
	}
	

	public void tela(){
		
		campos();
	}
	
	public void campos(){
		
		
		if ( !logon.getLogon().isEmpty() ){
			
			lblUsuario.setVisible(false);
			lblSenha.setVisible(false);
			txtUsuario.setVisible(false);
			pwdSenha.setVisible(false);
			btnCadastrar.setVisible(false);
			btnEntrar.setText("Sair");
			txtaAviso.setVisible(true);
			txtaAviso.setText(
					"Olá "+ logon.getLogon().get(0).getUsuario() 
					+ "!\n\nVocê pode sair da Livraria Digital "
					+ "e retornar no momento que preferir…");			
		} else {			
			lblUsuario.setVisible(true);
			lblSenha.setVisible(true);
			txtUsuario.setVisible(true);
			pwdSenha.setVisible(true);
			btnCadastrar.setVisible(true);
			txtaAviso.setVisible(false);
			btnEntrar.setText("Entrar");
		}
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

		case "usuario":
			if( janelaUsuario == null ){
				FrmUsuario usuario;
				usuario = new FrmUsuario();
				usuario.setVisible(true);
			} else {
				janelaUsuario.setVisible(true);
			}
			break;
		}
	}


	// CRUD //////////////////////////

	public void entrar() {

		if ( btnEntrar.getText().equals("Entrar") ){

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
				if (validar == false){
					msg("erroSenha", txtUsuario.getText());
					//limpaCampos();
				} 
			} else {	
				msg("erroVazio", txtUsuario.getText());
			}
			validar = false;
		} else {
			logon.sair();
			fechar();
		}
	}
	
	
	public void lerUsuarios() {

		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		try {
			dao.lerArquivo( diretorio + "data/", arquivo );
			linha = dao.getBuffer();
			String[] listaUsuario = linha.split(";");
			for (String s : listaUsuario) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					Usuario usuario = new Usuario();
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
		
		File f = new File(diretorio + "data/" + "log" );
		f.delete();
		for (Sessao usuarioAtivo : log) {
			try {
				daoSessao.escreverArquivo(diretorio  + "data/", "log", "", usuarioAtivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// MENSAGENS //////////////////////////////

	public void msg(String tipo, String mensagem) {

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
		default:
			JOptionPane.showMessageDialog(null, 
					mensagem, 
					"Erro no Sistema", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
		}
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
				abrirJanela( "usuario" );
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

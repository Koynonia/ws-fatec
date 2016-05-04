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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import boundary.FrmLogin;
import boundary.FrmPrincipal;
import boundary.FrmUsuario;
import dao.ArquivoUsuario;
import entity.Usuario;


public class LoginController implements ComponentListener {

	private FrmLogin janela;
	private FrmUsuario janelaUsuario;
	private JTextField txtId; 
	private JTextField txtUsuario;
	private JPasswordField autorizadoSenha; 
	private JButton btnCadastrar;
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "usuario";
	private List<Usuario> usuarios;
	private ArquivoUsuario dao = new ArquivoUsuario();
	private SessaoController logon = SessaoController.getInstance();
	private static int contador = 1;
	private boolean validar;

	public LoginController (
			FrmLogin janela,  
			JTextField txtId, 
			JTextField txtUsuario, 
			JPasswordField autorizadoSenha, 
			JButton btnCadastrar, 
			JButton btnEntrar
			) {

		this.janela = janela;
		this.txtId = txtId;
		this.txtUsuario = txtUsuario;
		this.autorizadoSenha = autorizadoSenha;
		this.btnCadastrar = btnCadastrar;
		this.usuarios = new ArrayList<Usuario>();

		lerUsuarios();
	}
	
	
	// METODOS DE SUPORTE ////////////////////////

	public void limpaCampos() {

		txtId.setText(null);
		txtUsuario.setText(null);
		autorizadoSenha.setText(null);
	}


	public boolean validar(String validaSenha) {  

		if (validaSenha != null){
			char[] senha = autorizadoSenha.getPassword();
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


	public void entrar() {

		if (!txtUsuario.getText().isEmpty() 
				&& autorizadoSenha.getPassword().length != 0) {
			for (int i = 0; i < usuarios.size(); i++) {
				if (txtUsuario.getText().equalsIgnoreCase(usuarios.get(i).getUsuario())
						&& validar(usuarios.get(i).getSenha()) == true) {
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
	}
	
	
	// MENSAGENS //////////////////////////////

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\n\nPor favor, digite o Usuário e a Senha.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
		case "autorizado":
			JOptionPane.showMessageDialog(null, 
					"Seja bem vindo ao sistema " + mensagem + "!", 
					"Acesso Permitido", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/ok.png" ));
			break;
		case "erroSenha":
			JOptionPane.showMessageDialog(null, 
					"O usuário '" + mensagem + "' ou sua senha não conferem!\n\nVerifique sua digitação e tente novamente.",
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

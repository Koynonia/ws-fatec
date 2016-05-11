/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 03/05/2016
 */


package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import controller.PrincipalController;

@SuppressWarnings("serial")
public class FrmLogin extends JFrame {

	private JPanel painel; 
	private JTextArea txtaAviso; 
	private JTextField txtUsuario; 
	private JSeparator linha; 
	private JLabel lblLogo; 
	private JLabel lblPagina; 
	private JLabel lblUsuario; 
	private JLabel lblSenha; 
	private JButton btnLivros; 
	private JButton btnUsuarios; 
	private JButton btnClientes; 
	private JButton btnFechar; 
	private JButton btnCadastrar; 
	private JButton btnEntrar; 
	private JPasswordField pwdSenha; 
	
	
	public FrmLogin( PrincipalController principalCtrl ) {
		setTitle("Login da Loja");
		setName("Login");
		setAlwaysOnTop (true);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 540, 250);
		setLocationRelativeTo(null);
		
		painel = new JPanel();
		setContentPane(painel);
		painel.setLayout(null);
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		linha = new JSeparator();
		linha.setBounds(6, 51, 528, 12);
		painel.add(linha);
		
		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(new Color(50, 205, 50));
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(16, 16, 314, 45);
		painel.add(lblLogo);
		
		lblPagina = new JLabel("Login");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(465, 31, 49, 23);
		painel.add(lblPagina);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(83, 86, 61, 16);
		painel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Digite aqui o usuário…");
		txtUsuario.setBounds(145, 80, 178, 28);
		painel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(83, 126, 61, 16);
		painel.add(lblSenha);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setToolTipText("Digite aqui a senha…");
		pwdSenha.setBounds(145, 120, 178, 28);
		painel.add(pwdSenha);
		
		btnUsuarios = new JButton("Usuários");
		btnUsuarios.setBounds(395, 75, 117, 29);
		painel.add(btnUsuarios);
		
		btnLivros = new JButton("Livros");
		btnLivros.setBounds(395, 115, 117, 29);
		painel.add(btnLivros);
		
		btnClientes = new JButton("Seus Dados");
		btnClientes.setBounds(139, 194, 117, 29);
		painel.add(btnClientes);
		
		btnFechar = new JButton("Fechar");
		btnFechar.setBounds(268, 194, 117, 29);
		painel.add(btnFechar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(139, 194, 117, 29);
		painel.add(btnCadastrar);

		btnEntrar = new JButton("Acessar");
		btnEntrar.setBounds(397, 194, 117, 29);
		painel.add(btnEntrar);
		
		txtaAviso = new JTextArea(1, 1);
		txtaAviso.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtaAviso.setBackground(SystemColor.window);
		txtaAviso.setEditable(false);
		txtaAviso.setVisible(false);
		txtaAviso.setLineWrap(true);
		txtaAviso.setWrapStyleWord(true);
		txtaAviso.setBounds(33, 75, 473, 86);
		painel.add(txtaAviso);
		
				LoginController loginCtrl = new LoginController( 
						this, 
						principalCtrl, 
						txtaAviso, 
						txtUsuario, 
						pwdSenha, 
						lblUsuario, 
						lblSenha, 
						btnLivros, 
						btnUsuarios, 
						btnClientes, 
						btnCadastrar, 
						btnEntrar
						);
				
		txtUsuario.addMouseListener(loginCtrl.limpaCampo);
		txtUsuario.addActionListener(loginCtrl.entrar);
		pwdSenha.addActionListener(loginCtrl.entrar);
		btnLivros.addActionListener(loginCtrl.janelas);
		btnUsuarios.addActionListener(loginCtrl.janelas);
		btnClientes.addActionListener(loginCtrl.janelas);
		btnFechar.addActionListener(loginCtrl.fechar);
		btnEntrar.addActionListener(loginCtrl.entrar);
		btnCadastrar.addActionListener(loginCtrl.janelas);
		txtUsuario.addKeyListener(loginCtrl.tecla);
		pwdSenha.addKeyListener(loginCtrl.tecla);
		btnEntrar.addKeyListener(loginCtrl.tecla);
	}
}

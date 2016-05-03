/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 03/05/2016
 */


package boundary;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;

public class FrmLogin extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField txtId;
	private JTextField txtUsuario;
	private JLabel lblId; 
	private JLabel lblUsuario; 
	private JLabel lblSenha;
	private JButton btnNovo; 
	private JButton btnEntrar;
	private JPasswordField pwdSenha;  
	private JPasswordField pwdSenha2; 
	private JCheckBox chckbxAdm; 
	private JCheckBox chckbxOpera;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FrmLogin() {
		setTitle("Login da Loja");
		setResizable(false);
		//setUndecorated(true);
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 540, 250);
		setLocationRelativeTo(null);
		painel = new JPanel();
		painel.setName("USR");
		painel.setLayout(null);
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);

		lblId = new JLabel("ID");
		lblId.setVisible(false);
		lblId.setBounds(36, 34, 61, 16);
		painel.add(lblId);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtId.setBounds(98, 32, 178, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		painel.add(txtId);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(36, 71, 61, 16);
		painel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Digite aqui o usuário…");
		txtUsuario.setBounds(98, 65, 178, 28);
		painel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(36, 111, 61, 16);
		painel.add(lblSenha);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setToolTipText("Digite aqui a senha…");
		pwdSenha.setBounds(98, 105, 178, 28);
		painel.add(pwdSenha);
		
		chckbxAdm = new JCheckBox("Administrativo");
		buttonGroup.add(chckbxAdm);
		chckbxAdm.setVisible(false);
		chckbxAdm.setBounds(366, 32, 128, 23);
		painel.add(chckbxAdm);
		
		chckbxOpera = new JCheckBox("Operacional");
		buttonGroup.add(chckbxOpera);
		chckbxOpera.setVisible(false);
		chckbxOpera.setBounds(366, 55, 128, 23);
		painel.add(chckbxOpera);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(286, 166, 97, 34);
		painel.add(btnNovo);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(397, 166, 97, 34);
		painel.add(btnEntrar);

		UsuarioController usuarioCtrl = new UsuarioController( 
				painel, 
				txtId, 
				txtUsuario, 
				pwdSenha, 
				pwdSenha2, 
				chckbxAdm, 
				chckbxOpera, 
				btnNovo, 
				btnEntrar);

		txtUsuario.addMouseListener(usuarioCtrl.limpaCampo);
		txtUsuario.addActionListener(usuarioCtrl.entrar);
		pwdSenha.addActionListener(usuarioCtrl.entrar);
		btnEntrar.addActionListener(usuarioCtrl.entrar);
		btnNovo.addActionListener(usuarioCtrl.cadastrar);
	}
}

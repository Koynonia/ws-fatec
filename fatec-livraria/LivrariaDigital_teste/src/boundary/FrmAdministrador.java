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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.AdministradorController;

public class FrmAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField txtId; 
	private JTextField txtUsuario;
	private JLabel lblId; 
	private JLabel lblUsuario; 
	private JLabel lblSenha; 
	private JLabel lblSenha2;
	private JButton btnPesquisar;
	private JButton btnCadastrar; 
	private JButton btnEditar;  
	private JButton btnExcluir;
	private JButton btnFechar;
	private JPasswordField pwdSenha; 
	private JPasswordField pwdSenha2;
	private JCheckBox chckbxAdm; 
	private JCheckBox chckbxOpera;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAdministrador frame = new FrmAdministrador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FrmAdministrador() {

		setTitle("Administrar Usuários");
		setName("Adminsitrador");
		setAlwaysOnTop (true);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		setLocationRelativeTo(null);
		
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);

		lblId = new JLabel("ID");
		lblId.setVisible(true);
		lblId.setBounds(35, 21, 61, 16);
		painel.add(lblId);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(true);
		txtId.setBounds(128, 19, 149, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		painel.add(txtId);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(35, 56, 61, 16);
		painel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Digite aqui o usuário…");
		txtUsuario.setBounds(128, 50, 149, 28);
		txtUsuario.setColumns(10);
		painel.add(txtUsuario);
		
		lblSenha = new JLabel("Nova Senha");
		lblSenha.setBounds(35, 94, 91, 16);
		painel.add(lblSenha);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setToolTipText("Digite aqui a senha…");
		pwdSenha.setBounds(128, 88, 149, 28);
		painel.add(pwdSenha);
		
		lblSenha2 = new JLabel("Repita a Senha");
		lblSenha2.setBounds(35, 132, 91, 16);
		painel.add(lblSenha2);
		
		pwdSenha2 = new JPasswordField();
		pwdSenha2.setToolTipText("Digite aqui a senha…");
		pwdSenha2.setBounds(128, 126, 149, 28);
		painel.add(pwdSenha2);
		
		chckbxAdm = new JCheckBox("Administrador");
		buttonGroup.add(chckbxAdm);
		chckbxAdm.setBounds(366, 98, 128, 23);
		painel.add(chckbxAdm);
		
		chckbxOpera = new JCheckBox("Operacional");
		chckbxOpera.setSelected(true);
		buttonGroup.add(chckbxOpera);
		chckbxOpera.setBounds(366, 121, 128, 23);
		painel.add(chckbxOpera);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(288, 47, 117, 29);
		painel.add(btnPesquisar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(22, 185, 117, 29);
		painel.add(btnEditar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(139, 185, 117, 29);
		painel.add(btnCadastrar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(256, 185, 117, 29);
		painel.add(btnExcluir);
		
		btnFechar = new JButton("Fechar");
		btnFechar.setBounds(398, 185, 117, 29);
		painel.add(btnFechar);
		
		AdministradorController userCtrl = new AdministradorController( 
				this, 
				painel, 
				txtId, 
				txtUsuario, 
				pwdSenha, 
				pwdSenha2, 
				chckbxAdm, 
				chckbxOpera, 
				btnCadastrar, 
				btnFechar);
		
		userCtrl.gerarId();
		txtUsuario.addMouseListener(userCtrl.limpaCampo);
		txtUsuario.addActionListener(userCtrl.pesquisar);
		pwdSenha.addActionListener(userCtrl.salvar);
		btnPesquisar.addActionListener(userCtrl.pesquisar);
		btnExcluir.addActionListener(userCtrl.excluir);
		btnEditar.addActionListener(userCtrl.editar);
		btnCadastrar.addActionListener(userCtrl.salvar);
		btnFechar.addActionListener(userCtrl.sair);
	}
}
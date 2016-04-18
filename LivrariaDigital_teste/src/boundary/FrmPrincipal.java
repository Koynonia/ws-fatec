package boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import controller.PrincipalController;

@SuppressWarnings("serial")
public class FrmPrincipal extends JFrame {
	private JLabel lblItem;
	private JComboBox<String> cbItensPesquisa;
	private JTextField txtPesquisa;
	private JButton btnProcurar;
	private JLabel lblFiltro;
	private JButton btnCarrinho;
	private JTextField txtQtd;
	private JLabel lblLogo;
	private JSeparator separador;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FrmPrincipal() {
		
		setTitle("Principal");
		setResizable(false);
		setSize( 1024, 768 );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		
		JPanel painel = new JPanel();
		setContentPane( painel );
		painel.setLayout(null);
		
		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(41, 36, 314, 45);
		painel.add(lblLogo);
		
		separador = new JSeparator();
		separador.setBounds(6, 146, 1012, 12);
		painel.add(separador);
			
		lblItem = new JLabel("Pesquisa");
		lblItem.setBounds(41, 112, 66, 16);
		painel.add(lblItem);
		
		cbItensPesquisa = new JComboBox<String>();
		cbItensPesquisa.setBounds(420, 107, 96, 27);
		cbItensPesquisa.addItem("");
		cbItensPesquisa.addItem("Titulo");
		cbItensPesquisa.addItem("Autor");
		cbItensPesquisa.addItem("Editora");
		cbItensPesquisa.addItem("Categoria");
		painel.add(cbItensPesquisa);
		
		txtPesquisa = new JTextField(20);
		txtPesquisa.setBounds(106, 106, 254, 28);
		painel.add(txtPesquisa);
		
		btnProcurar = new JButton("Ir");
		btnProcurar.setBounds(527, 106, 75, 29);
		painel.add(btnProcurar);	
		
		lblFiltro = new JLabel("Filtrar");
		lblFiltro.setBounds(372, 112, 43, 16);
		painel.add(lblFiltro);
		
		JButton btnLogin = new JButton("Entre ou Cadastre-se");
		btnLogin.setBounds(634, 106, 160, 29);
		painel.add(btnLogin);
		
		btnCarrinho = new JButton("Meu Carrinho");
		btnCarrinho.setBounds(809, 106, 117, 29);
		painel.add(btnCarrinho);
		
		txtQtd = new JTextField();
		txtQtd.setEnabled(false);
		txtQtd.setEditable(false);
		txtQtd.setColumns(10);
		txtQtd.setBounds(938, 106, 50, 28);
		painel.add(txtQtd);
		
		PrincipalController controlador = new PrincipalController(
				cbItensPesquisa, txtPesquisa, btnProcurar, btnCarrinho, this);	
		
		btnProcurar.addActionListener(controlador);
		btnCarrinho.addActionListener(controlador);	
	}
}

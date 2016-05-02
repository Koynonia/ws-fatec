/**
 * @author Fernando Moraes Oliveira e Vitor Fagundes Arantes
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 06/04/2016
 */

package boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import controller.PrincipalController;

@SuppressWarnings("serial")
public class FrmPrincipal extends JFrame {
	
	private JPanel painel;
	private JTextField txtPesquisar;
	private JLabel lblItem;
	private JLabel lblFiltro;
	private JLabel lblLogo;
	private JButton btnPesquisar;
	private JButton btnLogin;
	private JButton btnCarrinho;
	private JComboBox<String> cboFiltrar;
	private JSeparator linha;
	private String diretorio = "../LivrariaDigital_teste/";
	private ImageIcon capa = new ImageIcon( diretorio + "imagem/capa.png" );
	private JLabel lblLivroLanc_1;
	private JLabel lblLivroLanc_2;
	private JLabel lblLivroLanc_3;
	private JLabel lblLivroLanc_4;
	private JLabel lblLivroLanc_5;
	private JLabel lblLivroLanc_6;
	private JLabel lblLivroVend_1;
	private JLabel lblLivroVend_2;
	private JLabel lblLivroVend_3;
	private JLabel lblLivroVend_4;
	private JLabel lblLivroVend_5;
	private JLabel lblLivroVend_6;
	private JLabel lblLancamento;
	private JLabel lblVendido;
	private JButton btnLivro;
	private JLabel lblLivro;
	private JButton btnSair;
	
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
		setSize( 1024, 710 );
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		show();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		
		painel = new JPanel();
		setContentPane( painel );
		painel.setLayout(null);
		
		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(36, 36, 314, 45);
		painel.add(lblLogo);
		
		linha = new JSeparator();
		linha.setBounds(6, 146, 1012, 12);
		painel.add(linha);
			
		lblItem = new JLabel("Pesquisa");
		lblItem.setBounds(36, 111, 66, 16);
		painel.add(lblItem);
		
		cboFiltrar = new JComboBox<String>();
		cboFiltrar.setBounds(440, 106, 117, 27);
		painel.add(cboFiltrar);
		
		txtPesquisar = new JTextField(20);
		txtPesquisar.setToolTipText("Digite aqui o termo que deseja pesquisar…");
		txtPesquisar.setBounds(101, 105, 254, 28);
		painel.add(txtPesquisar);
		
		btnPesquisar = new JButton("Ir");
		btnPesquisar.setBounds(560, 105, 75, 29);
		painel.add(btnPesquisar);	
		
		lblFiltro = new JLabel("Filtrar por:");
		lblFiltro.setBounds(367, 112, 75, 16);
		painel.add(lblFiltro);
		
		btnLogin = new JButton("Entre ou Cadastre-se");
		btnLogin.setBounds(668, 105, 160, 29);
		btnLogin.setVisible(true);
		painel.add(btnLogin);

		btnCarrinho = new JButton("Meu Carrinho");
		btnCarrinho.setBounds(830, 105, 160, 29);
		painel.add(btnCarrinho);
		
		lblLivroLanc_1 = new JLabel();
		lblLivroLanc_1.setName( "lblLivroLanc_1" );
		lblLivroLanc_1.setToolTipText("Capa do Livro 1…");
		lblLivroLanc_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroLanc_1.setBorder(new LineBorder(Color.GRAY));
		lblLivroLanc_1.setBounds(30, 208, 135, 188);
		lblLivroLanc_1.setVisible(false);
		lblLivroLanc_1.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroLanc_1.getWidth(), 
				lblLivroLanc_1.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroLanc_1);
		
		lblLivroLanc_2 = new JLabel();
		
		lblLivroLanc_2.setName( "lblLivroLanc_2" );
		lblLivroLanc_2.setToolTipText("Capa do Livro 2…");
		lblLivroLanc_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroLanc_2.setBorder(new LineBorder(Color.GRAY));
		lblLivroLanc_2.setBounds(195, 208, 135, 188);
		lblLivroLanc_2.setVisible(false);
		lblLivroLanc_2.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroLanc_2.getWidth(), 
				lblLivroLanc_2.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroLanc_2);
		
		lblLivroLanc_3 = new JLabel();
		
		lblLivroLanc_3.setName( "lblLivroLanc_3" );
		lblLivroLanc_3.setToolTipText("Capa do Livro 3…");
		lblLivroLanc_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroLanc_3.setBorder(new LineBorder(Color.GRAY));
		lblLivroLanc_3.setBounds(360, 208, 135, 188);
		lblLivroLanc_3.setVisible(false);
		lblLivroLanc_3.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroLanc_3.getWidth(), 
				lblLivroLanc_3.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroLanc_3);
		
		lblLivroLanc_4 = new JLabel();
		lblLivroLanc_4.setName( "lblLivroLanc_4" );
		lblLivroLanc_4.setToolTipText("Capa do Livro 4…");
		lblLivroLanc_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroLanc_4.setBorder(new LineBorder(Color.GRAY));
		lblLivroLanc_4.setBounds(525, 208, 135, 188);
		lblLivroLanc_4.setVisible(false);
		lblLivroLanc_4.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroLanc_4.getWidth(), 
				lblLivroLanc_4.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroLanc_4);
		
		lblLivroLanc_5 = new JLabel();
		lblLivroLanc_5.setName( "lblLivroLanc_5" );
		lblLivroLanc_5.setToolTipText("Capa do Livro 5…");
		lblLivroLanc_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroLanc_5.setBorder(new LineBorder(Color.GRAY));
		lblLivroLanc_5.setBounds(690, 208, 135, 188);
		lblLivroLanc_5.setVisible(false);
		lblLivroLanc_5.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroLanc_5.getWidth(), 
				lblLivroLanc_5.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroLanc_5);
		
		lblLivroLanc_6 = new JLabel();
		lblLivroLanc_6.setName( "lblLivroLanc_6" );
		lblLivroLanc_6.setToolTipText("Capa do Livro 6…");
		lblLivroLanc_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroLanc_6.setBorder(new LineBorder(Color.GRAY));
		lblLivroLanc_6.setBounds(855, 208, 135, 188);
		lblLivroLanc_6.setVisible(false);
		lblLivroLanc_6.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroLanc_6.getWidth(), 
				lblLivroLanc_6.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroLanc_6);
		
		lblLivroVend_1 = new JLabel();
		lblLivroVend_1.setName( "lblLivroVend_1" );
		lblLivroVend_1.setToolTipText("Capa do Livro 1…");
		lblLivroVend_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroVend_1.setBorder(new LineBorder(Color.GRAY));
		lblLivroVend_1.setBounds(30, 464, 135, 188);
		lblLivroVend_1.setVisible(false);
		lblLivroVend_1.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroVend_1.getWidth(), 
				lblLivroVend_1.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroVend_1);
		
		lblLivroVend_2 = new JLabel();
		lblLivroVend_2.setName( "lblLivroVend_2" );
		lblLivroVend_2.setToolTipText("Capa do Livro 2…");
		lblLivroVend_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroVend_2.setBorder(new LineBorder(Color.GRAY));
		lblLivroVend_2.setBounds(195, 464, 135, 188);
		lblLivroVend_2.setVisible(false);
		lblLivroVend_2.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroVend_2.getWidth(), 
				lblLivroVend_2.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroVend_2);
		
		lblLivroVend_3 = new JLabel();
		lblLivroVend_3.setName( "lblLivroVend_3" );
		lblLivroVend_3.setToolTipText("Capa do Livro 3…");
		lblLivroVend_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroVend_3.setBorder(new LineBorder(Color.GRAY));
		lblLivroVend_3.setBounds(360, 464, 135, 188);
		lblLivroVend_3.setVisible(false);
		lblLivroVend_3.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroVend_3.getWidth(), 
				lblLivroVend_3.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroVend_3);
		
		lblLivroVend_4 = new JLabel();
		lblLivroVend_4.setName( "lblLivroVend_4" );
		lblLivroVend_4.setToolTipText("Capa do Livro 4…");
		lblLivroVend_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroVend_4.setBorder(new LineBorder(Color.GRAY));
		lblLivroVend_4.setBounds(525, 464, 135, 188);
		lblLivroVend_4.setVisible(false);
		lblLivroVend_4.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroVend_4.getWidth(), 
				lblLivroVend_4.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroVend_4);
		
		lblLivroVend_5 = new JLabel();
		lblLivroVend_5.setName( "lblLivroVend_5" );
		lblLivroVend_5.setToolTipText("Capa do Livro 5…");
		lblLivroVend_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroVend_5.setBorder(new LineBorder(Color.GRAY));
		lblLivroVend_5.setBounds(690, 464, 135, 188);
		lblLivroVend_5.setVisible(false);
		lblLivroVend_5.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroVend_5.getWidth(), 
				lblLivroVend_5.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroVend_5);
		
		lblLivroVend_6 = new JLabel();
		lblLivroVend_6.setName( "lblLivroVend_6" );
		lblLivroVend_6.setToolTipText("Capa do Livro 6…");
		lblLivroVend_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroVend_6.setBorder(new LineBorder(Color.GRAY));
		lblLivroVend_6.setBounds(855, 464, 135, 188);
		lblLivroVend_6.setVisible(false);
		lblLivroVend_6.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblLivroVend_6.getWidth(), 
				lblLivroVend_6.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblLivroVend_6);
		
		lblLancamento = new JLabel("Lançamentos:");
		lblLancamento.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblLancamento.setEnabled(false);
		lblLancamento.setBounds(36, 170, 135, 26);
		painel.add(lblLancamento);
		
		lblVendido = new JLabel("Mais Vendidos:");
		lblVendido.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblVendido.setEnabled(false);
		lblVendido.setBounds(36, 426, 135, 26);
		painel.add(lblVendido);
		
		//Acesso Administrador - para testes
		lblLivro = new JLabel("Adiministrador:");
		lblLivro.setBounds(710, 11, 98, 16);
		painel.add(lblLivro);

		btnLivro = new JButton("Livros");
		btnLivro.setBounds(812, 6, 117, 29);
		painel.add(btnLivro);

		btnSair = new JButton("Sair");
		btnSair.setBounds(812, 36, 117, 29);
		painel.add(btnSair);
		
		
		PrincipalController principalCtrl = new PrincipalController(
				this, 
				painel, 
				cboFiltrar, 
				txtPesquisar, 
				btnLogin, 
				btnCarrinho, 
				btnLivro,
				lblLivroLanc_1, 
				lblLivroLanc_2, 
				lblLivroLanc_3, 
				lblLivroLanc_4, 
				lblLivroLanc_5, 
				lblLivroLanc_6,
				lblLivroVend_1, 
				lblLivroVend_2, 
				lblLivroVend_3, 
				lblLivroVend_4, 
				lblLivroVend_5, 
				lblLivroVend_6 );
		
		
		txtPesquisar.addActionListener(principalCtrl.pesquisar);
		btnPesquisar.addActionListener(principalCtrl.pesquisar);
		btnLogin.addActionListener(principalCtrl.janelas);
		btnLivro.addActionListener(principalCtrl.janelas);
		btnCarrinho.addActionListener(principalCtrl.janelas);
		btnSair.addActionListener(principalCtrl.sairSistema);
		lblLivroLanc_1.addMouseListener(principalCtrl.clique);
		lblLivroLanc_2.addMouseListener(principalCtrl.clique);
		lblLivroLanc_3.addMouseListener(principalCtrl.clique);
		lblLivroLanc_4.addMouseListener(principalCtrl.clique);
		lblLivroLanc_5.addMouseListener(principalCtrl.clique);
		lblLivroLanc_6.addMouseListener(principalCtrl.clique);
		lblLivroVend_1.addMouseListener(principalCtrl.clique);
		lblLivroVend_2.addMouseListener(principalCtrl.clique);
		lblLivroVend_3.addMouseListener(principalCtrl.clique);
		lblLivroVend_4.addMouseListener(principalCtrl.clique);
		lblLivroVend_5.addMouseListener(principalCtrl.clique);
		lblLivroVend_6.addMouseListener(principalCtrl.clique);
		txtPesquisar.addKeyListener(principalCtrl.tecla);
		btnCarrinho.addFocusListener(principalCtrl.foco);
	}
}

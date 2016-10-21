/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * Iniciado em 19/10/2016
 */
package edu.pousada.boundary;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = -7459629918660280765L;
	private String perfil;
	private JPanel painel;
	private JLabel lblLogo;
	private JLabel lblLogin;
	private JLabel lblPwd;
	private JLabel lblVersao;
	private JLabel lblPrincipalInfo;
	private JLabel lblPrincipal;
	private JLabel lblPrincipalImg;
	private JLabel lblChaleInfo;
	private JLabel lblChaleCategoria;
	private JLabel lblChaleImg;
	private JLabel lblLazerInfo;
	private JLabel lblLazer;
	private JLabel lblLazerImg;
	private JLabel lblServicoImg;
	private JLabel lblPesquisa;
	private JLabel lblChaleTab;
	private JLabel lblReservaTab;
	private JTextField txtLogin;
	private JTextField txtPwd;
	private JTextField txtPesquisa;
	private JTextArea txtaPrincipalInfo;
	private JTextArea txtaPrincipal;
	private JTextArea txtaChaleInfo;
	private JTextArea txtaLazerInfo;
	private JTextArea txtaServicoInfo;
	private JTextArea txtaChale;
	private JTextArea txtaLazer;
	private JComboBox<String> cboChaleCategoria;
	private JButton btnLogin;
	private JButton btnRealizar;
	private JButton btnReservar;
	private JTable tabChale;
	private JTable tabReserva;
	private JScrollPane spChale;
	private JScrollPane spReserva;
	private ImageIcon fundo = new ImageIcon( "../Pousada/resources/backstage/img4.jpg" );
	private ImageIcon principal = new ImageIcon( "../Pousada/resources/pousada/img1.jpg" );
	private ImageIcon chale = new ImageIcon( "../Pousada/resources/chale/img1.jpg" );
	private ImageIcon lazer = new ImageIcon( "../Pousada/resources/lazer/img1.jpg" );

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

		setTitle("Pousada Inn Von Dix");
		setName("Principal");
		setBounds(100, 100, 1024, 710);
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		setResizable(false);

		painel = new JPanel();
		setContentPane( painel );
		painel.setName("Principal");
		painel.setLayout(null);

		lblLogo = new JLabel("POUSADA INN VON DIX");
		lblLogo.setEnabled(false);
		lblLogo.setForeground(Color.DARK_GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(31, 16, 407, 45);
		painel.add(lblLogo);

		lblLogin = new JLabel("Usuário:");
		lblLogin.setBounds(738, 22, 62, 16);
		lblLogin.setVisible(true);
		painel.add(lblLogin);

		txtLogin = new JTextField(20);
		txtLogin.setToolTipText("Digite seu usuário");
		txtLogin.setBounds(798, 16, 105, 28);
		txtLogin.setVisible(true);
		painel.add(txtLogin);

		lblPwd = new JLabel("Senha:");
		lblPwd.setBounds(738, 57, 62, 16);
		lblPwd.setVisible(true);
		painel.add(lblPwd);

		txtPwd = new JTextField(20);
		txtPwd.setToolTipText("Digite sua senha");
		txtPwd.setBounds(798, 51, 105, 28);
		txtPwd.setVisible(true);
		painel.add(txtPwd);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(914, 51, 79, 29);
		btnLogin.setToolTipText("Clique aqui para registrar-se");
		btnLogin.setVisible(true);
		painel.add(btnLogin);

		JTabbedPane tabContainer = new JTabbedPane(JTabbedPane.TOP);
		tabContainer.setBounds(23, 92, 976, 580);
		painel.add(tabContainer);

		JPanel painelPrincipal = new JPanel();
		JPanel painelChale = new JPanel();
		JPanel painelLazer = new JPanel();
		JPanel painelServico = new JPanel();
		JPanel painelReserva = new JPanel();
		JPanel painelContato = new JPanel();
		
		painelPrincipal.setLayout(null);
		painelChale.setLayout(null);
		painelLazer.setLayout(null);
		painelServico.setLayout(null);
		painelReserva.setLayout(null);
		painelContato.setLayout(null);

		perfil = "ad";

		switch ( perfil ){

		case "ad":
			lblPrincipalInfo = new JLabel("SEJA BEM VINDO, visitante!");
			lblPrincipalInfo.setBounds(20, 22, 500, 16);		
			painelPrincipal.add(lblPrincipalInfo);

			txtaPrincipalInfo = new JTextArea();
			txtaPrincipalInfo.setEditable(false);
			txtaPrincipalInfo .setLineWrap(true);
			txtaPrincipalInfo .setWrapStyleWord(true);
			txtaPrincipalInfo.setBounds(20, 46, 914, 114);
			painelPrincipal.add(txtaPrincipalInfo);

			lblPrincipal = new JLabel("O que oferecemos:");
			lblPrincipal.setBounds(600, 175, 300, 16);
			painelPrincipal.add(lblPrincipal);

			txtaPrincipal = new JTextArea(10, 20);
			txtaPrincipal.setEditable(false);
			txtaPrincipal.setLineWrap(true);
			txtaPrincipal.setWrapStyleWord(true);
			txtaPrincipal.setBounds(600, 203, 331, 312);
			painelPrincipal.add(txtaPrincipal);

			lblPrincipalImg = new JLabel();
			lblPrincipalImg.setName( "lblImagem" );
			lblPrincipalImg.setToolTipText("Vista da pousada");
			lblPrincipalImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrincipalImg.setBorder(new LineBorder(Color.GRAY));
			lblPrincipalImg.setBounds(20, 172, 555, 343);
			lblPrincipalImg.setVisible(true);
			lblPrincipalImg.setIcon(new ImageIcon(principal.getImage().getScaledInstance(lblPrincipalImg.getWidth(), 
			lblPrincipalImg.getHeight(), Image.SCALE_DEFAULT)));
			painelPrincipal.add(lblPrincipalImg);
			
			lblChaleInfo = new JLabel("Conheça nossas acomodações!");
			lblChaleInfo.setBounds(20, 22, 500, 16);		
			painelChale.add(lblChaleInfo);

			txtaChaleInfo = new JTextArea();
			txtaChaleInfo.setEditable(false);
			txtaChaleInfo .setLineWrap(true);
			txtaChaleInfo .setWrapStyleWord(true);
			txtaChaleInfo.setBounds(20, 46, 914, 114);
			painelChale.add(txtaChaleInfo);
			
			lblChaleCategoria = new JLabel("Selecione uma categoria:");
			lblChaleCategoria.setBounds(600, 175, 170, 16);
			painelChale.add(lblChaleCategoria);
			
			cboChaleCategoria = new JComboBox<String>();
			cboChaleCategoria.setBounds(760, 172, 173, 27);
			painelChale.add(cboChaleCategoria);
			
			txtaChale = new JTextArea(10, 20);
			txtaChale.setEditable(false);
			txtaChale.setLineWrap(true);
			txtaChale.setWrapStyleWord(true);
			txtaChale.setBounds(600, 210, 331, 250);
			painelChale.add(txtaChale);
			
			btnReservar = new JButton("Reservar");
			btnReservar.setBounds(725, 479, 90, 29);
			painelChale.add(btnReservar);
			
			lblChaleImg = new JLabel();
			lblChaleImg.setName( "lblImgChale" );
			lblChaleImg.setToolTipText("Imagem dos Chalés");
			lblChaleImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblChaleImg.setBorder(new LineBorder(Color.GRAY));
			lblChaleImg.setBounds(20, 172, 555, 343);
			lblChaleImg.setVisible(true);
			lblChaleImg.setIcon(new ImageIcon(chale.getImage().getScaledInstance(lblChaleImg.getWidth(), 
			lblChaleImg.getHeight(), Image.SCALE_DEFAULT)));
			painelChale.add(lblChaleImg);
			
			lblLazerInfo = new JLabel("Nossa programação de lazer!");
			lblLazerInfo.setBounds(20, 22, 500, 16);		
			painelLazer.add(lblLazerInfo);

			txtaLazerInfo = new JTextArea();
			txtaLazerInfo.setEditable(false);
			txtaLazerInfo .setLineWrap(true);
			txtaLazerInfo .setWrapStyleWord(true);
			txtaLazerInfo.setBounds(20, 46, 914, 114);
			painelLazer.add(txtaLazerInfo);
			
			lblLazer = new JLabel("Seu lazer garantido:");
			lblLazer.setBounds(600, 175, 300, 16);
			painelLazer.add(lblLazer);

			txtaLazer = new JTextArea(10, 20);
			txtaLazer.setEditable(false);
			txtaLazer.setLineWrap(true);
			txtaLazer.setWrapStyleWord(true);
			txtaLazer.setBounds(600, 203, 331, 312);
			painelLazer.add(txtaLazer);

			lblLazerImg = new JLabel();
			lblLazerImg.setName( "lblImagem" );
			lblLazerImg.setToolTipText("Vista da pousada");
			lblLazerImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblLazerImg.setBorder(new LineBorder(Color.GRAY));
			lblLazerImg.setBounds(20, 172, 555, 343);
			lblLazerImg.setVisible(true);
			lblLazerImg.setIcon(new ImageIcon(lazer.getImage().getScaledInstance(lblLazerImg.getWidth(), 
			lblLazerImg.getHeight(), Image.SCALE_DEFAULT)));
			painelLazer.add(lblLazerImg);
			
			lblServicoImg = new JLabel("Serviços que oferecemos!");
			lblServicoImg.setBounds(20, 22, 500, 16);		
			painelServico.add(lblServicoImg);

			txtaServicoInfo = new JTextArea();
			txtaServicoInfo.setEditable(false);
			txtaServicoInfo .setLineWrap(true);
			txtaServicoInfo .setWrapStyleWord(true);
			txtaServicoInfo.setBounds(20, 46, 914, 114);
			painelServico.add(txtaServicoInfo);
			break;

		case "adm":
			lblPesquisa = new JLabel("Pesquisar cliente:");
			lblPesquisa.setBounds(37, 17, 117, 16);
			painelPrincipal.add(lblPesquisa);

			txtPesquisa = new JTextField(20);
			txtPesquisa.setBounds(161, 11, 239, 28);
			painelPrincipal.add(txtPesquisa);
			txtPesquisa.setToolTipText("Digite o nome ou código como critério");

			btnRealizar = new JButton("Realizar");
			btnRealizar.setBounds(412, 11, 79, 29);
			painelPrincipal.add(btnRealizar);
			btnRealizar.setVisible(true);

			lblChaleTab = new JLabel("Controle dos Chalés");
			lblChaleTab.setBounds(790, 30, 130, 16);
			painelPrincipal.add(lblChaleTab);

			spChale = new JScrollPane();
			spChale.setToolTipText("clique 2 vezes para editar");
			spChale.setBounds(30, 50, 895, 210);
			painelPrincipal.add(spChale);

			tabChale = new JTable();
			tabChale.setBorder(null);
			spChale.setViewportView(tabChale);

			lblReservaTab = new JLabel("Reservas de Serviços");
			lblReservaTab.setBounds(790, 280, 130, 16);
			painelPrincipal.add(lblReservaTab);

			spReserva = new JScrollPane();
			spReserva.setToolTipText("clique 2 vezes para editar");
			spReserva.setBounds(30, 300, 895, 210);
			painelPrincipal.add(spReserva);

			tabReserva = new JTable();
			tabReserva.setBorder(null);
			spReserva.setViewportView(tabReserva);
			break;
		}
		tabContainer.add( "Principal", painelPrincipal );
		tabContainer.add( "Chalés", painelChale );
		tabContainer.add( "Lazer", painelLazer );
		tabContainer.add( "Serviços", painelServico );
		tabContainer.add( "Reservas", painelReserva );
		tabContainer.add( "Contato", painelContato );

		lblVersao = new JLabel("Versão: 0.0.4");
		lblVersao.setEnabled(false);
		lblVersao.setBounds(48, 665, 97, 16);
		painel.add(lblVersao);

		JLabel lblFundo = new JLabel();
		lblFundo.setBounds(0, 0, 1024, 710);
		lblFundo.setIcon(new ImageIcon(fundo.getImage().getScaledInstance(lblFundo.getWidth(), 
				lblFundo.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblFundo);

	}
}
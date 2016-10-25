/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 19/10/2016
 */

package edu.pousada.boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import edu.pousada.controller.PrincipalCtrl;

public class PrincipalFrm extends JFrame {

	private static final long serialVersionUID = -7459629918660280765L;
	private String perfil;
	private String versao;
	private JPanel painel;
	private JLabel lblLogin;
	private JLabel lblPwd;
	private JLabel lblVersao;
	private JLabel lblPrincipalImg;
	private JLabel lblChaleImg;
	private JLabel lblLazerImg;
	private JLabel lblServicoImg;
	private JLabel lblReservaImg;
	private JLabel lblContatoImg;
	private JTextField txtLogin;
	private JTextField txtPwd;
	private JTextField txtPesquisa;
	private JTextField txtReservaQtdAdulto;
	private JTextField txtReservaQtdCrianca;
	private JTextField txtReservaNome;
	private JTextField txtReservaDocNum;
	private JTextField txtReservaEmail;
	private JTextField txtReservaTelefone;
	private JTextField txtReservaCelular;
	private JTextField txtReservaCidade;
	private JTextField txtReservaEstado;
	private JTextField txtReservaPais;
	private JTextField txtContatoNome;
	private JTextField txtContatoEmail;
	private JTextField txtContatoTelefone;
	private JTextField txtContatoCidade;
	private JTextField txtContatoEstado;
	private JTextField txtContatoPais;
	private JFormattedTextField ftxtReservaDtInicio;
	private JFormattedTextField ftxtReservaDtFim;
	private JTextArea txtaPrincipalInfo;
	private JTextArea txtaPrincipalDetalhe;
	private JTextArea txtaChaleInfo;
	private JTextArea txtaChaleDetalhe;
	private JTextArea txtaLazerInfo;
	private JTextArea txtaLazerDetalhe;
	private JTextArea txtaServicoInfo;
	private JTextArea txtaServicoDetalhe;
	private JTextArea txtaReservaInfo;
	private JTextArea txtaReservaObs;
	private JTextArea txtaContatoInfo;
	private JTextArea txtaContatoMsg;
	private JComboBox<String> cboReservaCategoria;
	private JComboBox<String> cboReservaDocTipo;
	private JComboBox<String> cboContatoAssunto;
	private JButton btnLogin;
	private JButton btnPesquisar;
	private JButton btnReservaEnviar;
	private JButton btnContatoEnviar;
	private JButton btnReservaLimpar;
	private JButton btnContatoLimpar;
	private JTable tabChale;
	private JTable tabReserva;
	private JTabbedPane tabContainer;
	private JScrollPane spChale;
	private JScrollPane spReserva;
	private ImageIcon fundo = new ImageIcon( "../Pousada/resources/backstage/img4.jpg" );
	private ImageIcon principal = new ImageIcon( "../Pousada/resources/imagens/externa0.jpg" );
	private ImageIcon chale = new ImageIcon( "../Pousada/resources/imagens/chale0.jpg" );
	private ImageIcon lazer = new ImageIcon( "../Pousada/resources/imagens/lazer0.jpg" );
	private ImageIcon servico = new ImageIcon( "../Pousada/resources/imagens/servico0.jpg" );
	private ImageIcon contato = new ImageIcon( "../Pousada/resources/imagens/local0.png" );
	private Border borderClean = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalFrm frame = new PrincipalFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrincipalFrm() throws ParseException {

		setTitle("Pousada Inn Von Dix");
		setName("Principal");
		setBounds(100, 100, 1024, 710);
		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		setLocationRelativeTo(null);
		setResizable(false);

		painel = new JPanel();
		setContentPane( painel );
		painel.setName("Principal");
		painel.setLayout(null);

		JLabel lblLogo = new JLabel("POUSADA INN VON DIX");
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

		tabContainer = new JTabbedPane(JTabbedPane.TOP);
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

		perfil = "visitante"; //Subistituir por função

		switch ( perfil ){

		case "visitante":

			// PRINCIPAL //////////////////////////
			JLabel lblPrincipalInfo = new JLabel("SEJA BEM VINDO, visitante!");
			lblPrincipalInfo.setBounds(20, 22, 500, 16);		
			painelPrincipal.add(lblPrincipalInfo);

			txtaPrincipalInfo = new JTextArea();
			txtaPrincipalInfo.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaPrincipalInfo.setEditable(false);
			txtaPrincipalInfo .setLineWrap(true);
			txtaPrincipalInfo .setWrapStyleWord(true);
			txtaPrincipalInfo.setBounds(20, 46, 914, 90);
			painelPrincipal.add(txtaPrincipalInfo);

			JLabel lblPrincipal = new JLabel("O que oferecemos:");
			lblPrincipal.setBounds(600, 175, 300, 16);
			painelPrincipal.add(lblPrincipal);

			txtaPrincipalDetalhe = new JTextArea(10, 20);
			txtaPrincipalDetalhe.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaPrincipalDetalhe.setEditable(false);
			txtaPrincipalDetalhe.setLineWrap(true);
			txtaPrincipalDetalhe.setWrapStyleWord(true);
			txtaPrincipalDetalhe.setBounds(600, 203, 334, 312);
			painelPrincipal.add(txtaPrincipalDetalhe);

			lblPrincipalImg = new JLabel();
			lblPrincipalImg.setName( "lblPrincipalImg");
			lblPrincipalImg.setToolTipText("Vista da pousada");
			lblPrincipalImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrincipalImg.setBorder(new LineBorder(Color.LIGHT_GRAY));
			lblPrincipalImg.setBounds(20, 178, 555, 337);
			lblPrincipalImg.setVisible(true);
			lblPrincipalImg.setIcon(new ImageIcon(
					principal.getImage().getScaledInstance(
							lblPrincipalImg.getWidth(), 
							lblPrincipalImg.getHeight(), 
							Image.SCALE_DEFAULT)));
			painelPrincipal.add(lblPrincipalImg);

			// CHALE //////////////////////////

			JLabel lblChaleInfo = new JLabel("Conheça nossas acomodações!");
			lblChaleInfo.setBounds(20, 22, 500, 16);		
			painelChale.add(lblChaleInfo);

			txtaChaleInfo = new JTextArea();
			txtaChaleInfo.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaChaleInfo.setEditable(false);
			txtaChaleInfo .setLineWrap(true);
			txtaChaleInfo .setWrapStyleWord(true);
			txtaChaleInfo.setBounds(20, 46, 914, 90);
			painelChale.add(txtaChaleInfo);

			JLabel lblChale = new JLabel("Nossos Chalés:");
			lblChale.setBounds(600, 175, 300, 16);
			painelChale.add(lblChale);

			txtaChaleDetalhe = new JTextArea(10, 20);
			txtaChaleDetalhe.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaChaleDetalhe.setEditable(false);
			txtaChaleDetalhe.setLineWrap(true);
			txtaChaleDetalhe.setWrapStyleWord(true);
			txtaChaleDetalhe.setBounds(600, 203, 334, 312);
			painelChale.add(txtaChaleDetalhe);

			lblChaleImg = new JLabel();
			lblChaleImg.setName( "lblChaleImg");
			lblChaleImg.setToolTipText("Vista de nossas acomodações");
			lblChaleImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblChaleImg.setBorder(new LineBorder(Color.LIGHT_GRAY));
			lblChaleImg.setBounds(20, 178, 555, 337);
			lblChaleImg.setVisible(true);
			lblChaleImg.setIcon(new ImageIcon(
					chale.getImage().getScaledInstance(
							lblChaleImg.getWidth(), 
							lblChaleImg.getHeight(), 
							Image.SCALE_DEFAULT)));
			painelChale.add(lblChaleImg);

			// LAZER //////////////////////////

			JLabel lblLazerInfo = new JLabel("Nossa programação de lazer!");
			lblLazerInfo.setBounds(20, 22, 500, 16);		
			painelLazer.add(lblLazerInfo);

			txtaLazerInfo = new JTextArea();
			txtaLazerInfo.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaLazerInfo.setEditable(false);
			txtaLazerInfo .setLineWrap(true);
			txtaLazerInfo .setWrapStyleWord(true);
			txtaLazerInfo.setBounds(20, 46, 914, 90);
			painelLazer.add(txtaLazerInfo);

			JLabel lblLazer = new JLabel("Seu lazer garantido:");
			lblLazer.setBounds(600, 175, 300, 16);
			painelLazer.add(lblLazer);

			txtaLazerDetalhe = new JTextArea(10, 20);
			txtaLazerDetalhe.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaLazerDetalhe.setEditable(false);
			txtaLazerDetalhe.setLineWrap(true);
			txtaLazerDetalhe.setWrapStyleWord(true);
			txtaLazerDetalhe.setBounds(600, 203, 334, 312);
			painelLazer.add(txtaLazerDetalhe);

			lblLazerImg = new JLabel();
			lblLazerImg.setName( "lblLazerImg" );
			lblLazerImg.setToolTipText("Atividades de Lazer.");
			lblLazerImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblLazerImg.setBorder(new LineBorder(Color.LIGHT_GRAY));
			lblLazerImg.setBounds(20, 178, 555, 337);
			lblLazerImg.setVisible(true);
			lblLazerImg.setIcon(new ImageIcon(lazer.getImage().getScaledInstance(
					lblLazerImg.getWidth(), 
					lblLazerImg.getHeight(), 
					Image.SCALE_DEFAULT)));
			painelLazer.add(lblLazerImg);

			// SERVICO //////////////////////////

			JLabel lblServicoInfo = new JLabel("Serviços que oferecemos.");
			lblServicoInfo.setBounds(20, 22, 500, 16);		
			painelServico.add(lblServicoInfo);

			txtaServicoInfo = new JTextArea();
			txtaServicoInfo.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaServicoInfo.setEditable(false);
			txtaServicoInfo .setLineWrap(true);
			txtaServicoInfo .setWrapStyleWord(true);
			txtaServicoInfo.setBounds(20, 46, 914, 90);
			painelServico.add(txtaServicoInfo);

			JLabel lblServico = new JLabel("Nossos serviços para o seu conforto:");
			lblServico.setBounds(600, 175, 300, 16);
			painelServico.add(lblServico);

			txtaServicoDetalhe = new JTextArea(10, 20);
			txtaServicoDetalhe.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaServicoDetalhe.setEditable(false);
			txtaServicoDetalhe.setLineWrap(true);
			txtaServicoDetalhe.setWrapStyleWord(true);
			txtaServicoDetalhe.setBounds(600, 203, 334, 312);
			painelServico.add(txtaServicoDetalhe);

			lblServicoImg = new JLabel();
			lblServicoImg.setName( "lblServicoImg" );
			lblServicoImg.setToolTipText("Serviços oferecidos");
			lblServicoImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblServicoImg.setBorder(new LineBorder(Color.LIGHT_GRAY));
			lblServicoImg.setBounds(20, 178, 555, 337);
			lblServicoImg.setVisible(true);
			lblServicoImg.setIcon(new ImageIcon(servico.getImage().getScaledInstance(
					lblServicoImg.getWidth(), 
					lblServicoImg.getHeight(), 
					Image.SCALE_DEFAULT)));
			painelServico.add(lblServicoImg);

			// RESERVA //////////////////////////

			JLabel lblReservaInfo = new JLabel("Como realizar a sua reserva?");
			lblReservaInfo.setBounds(20, 22, 500, 16);		
			painelReserva.add(lblReservaInfo);

			txtaReservaInfo = new JTextArea();
			txtaReservaInfo.setName("reservaInfo");
			txtaReservaInfo.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaReservaInfo.setEditable(false);
			txtaReservaInfo .setLineWrap(true);
			txtaReservaInfo .setWrapStyleWord(true);
			txtaReservaInfo.setBounds(20, 46, 914, 90);
			painelReserva.add(txtaReservaInfo);

			JLabel lblReservaChale = new JLabel("Categoria do Chalé:");
			lblReservaChale.setBounds(20, 157, 125, 16);
			lblReservaChale.setToolTipText("Selecione a categoria do Chalé");
			painelReserva.add(lblReservaChale);

			cboReservaCategoria = new JComboBox<String>();
			cboReservaCategoria.setBounds(160, 152, 185, 27);
			painelReserva.add(cboReservaCategoria);

			lblReservaImg = new JLabel();
			lblReservaImg.setName( "lblReservaImg" );
			lblReservaImg.setToolTipText("Acomodações do Chalé");
			lblReservaImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblReservaImg.setBorder(new LineBorder(Color.LIGHT_GRAY));
			lblReservaImg.setBounds(20, 193, 324, 197);
			lblReservaImg.setVisible(true);
			lblReservaImg.setIcon(new ImageIcon(chale.getImage().getScaledInstance(
					lblReservaImg.getWidth(), 
					lblReservaImg.getHeight(), 
					Image.SCALE_DEFAULT)));
			painelReserva.add(lblReservaImg);

			JLabel lblReservaDtInicio = new JLabel("Chegada:");
			lblReservaDtInicio.setBounds(20, 413, 100, 16);
			painelReserva.add(lblReservaDtInicio);

			MaskFormatter mskData = new MaskFormatter("##/##/####");
			ftxtReservaDtInicio = new JFormattedTextField(mskData);
			ftxtReservaDtInicio.setHorizontalAlignment(SwingConstants.CENTER);
			ftxtReservaDtInicio.setBounds(80, 406, 100, 28);
			ftxtReservaDtInicio.setToolTipText("Digite a data inicial de sua estadia");
			painelReserva.add(ftxtReservaDtInicio);

			JLabel lblReservaDtFim = new JLabel("Partida:");
			lblReservaDtFim.setBounds(185, 413, 100, 16);
			painelReserva.add(lblReservaDtFim);

			mskData = new MaskFormatter("##/##/####");
			ftxtReservaDtFim = new JFormattedTextField(mskData);
			ftxtReservaDtFim.setHorizontalAlignment(SwingConstants.CENTER);
			ftxtReservaDtFim.setBounds(250, 406, 100, 28);
			ftxtReservaDtFim.setToolTipText("Digite a data final de sua estadia");
			painelReserva.add(ftxtReservaDtFim);

			JLabel lblReservaQtdAdulto = new JLabel("Adultos:");
			lblReservaQtdAdulto.setBounds(20, 453, 100, 16);
			painelReserva.add(lblReservaQtdAdulto);

			txtReservaQtdAdulto = new JTextField(20);
			txtReservaQtdAdulto.setBounds(80, 447, 100, 28);
			txtReservaQtdAdulto.setToolTipText("Digite a quantidade de Adultos");
			painelReserva.add(txtReservaQtdAdulto);

			JLabel lblReservaQtdCrianca = new JLabel("Crianças:");
			lblReservaQtdCrianca.setBounds(185, 453, 100, 16);
			painelReserva.add(lblReservaQtdCrianca);

			txtReservaQtdCrianca = new JTextField(20);
			txtReservaQtdCrianca.setBounds(250, 447, 100, 28);
			txtReservaQtdCrianca.setToolTipText("Digite a quantidade de Crianças");
			painelReserva.add(txtReservaQtdCrianca);
			
			btnReservaEnviar = new JButton("Fazer Reserva");
			btnReservaEnviar.setBounds(246, 485, 110, 29);
			painelReserva.add(btnReservaEnviar);
			
			btnReservaLimpar = new JButton("Limpar");
			btnReservaLimpar.setBounds(130, 485, 110, 29);
			btnReservaLimpar.setToolTipText("Clique aqui para limpar os campos preenchidos");
			btnReservaLimpar.setVisible(true);
			painelReserva.add(btnReservaLimpar);

			JLabel lblReservaNome = new JLabel("Nome:");
			lblReservaNome.setBounds(450, 157, 150, 16);
			painelReserva.add(lblReservaNome);

			txtReservaNome = new JTextField(20);
			txtReservaNome.setBounds(535, 151, 400, 28);
			txtReservaNome.setToolTipText("Digite seu nome completo");
			painelReserva.add(txtReservaNome);

			JLabel lblReservaEmail = new JLabel("Email:");
			lblReservaEmail.setBounds(450, 197, 150, 16);
			painelReserva.add(lblReservaEmail);

			txtReservaEmail = new JTextField(20);
			txtReservaEmail.setBounds(535, 191, 400, 28);
			txtReservaEmail.setToolTipText("Digite seu Email");
			painelReserva.add(txtReservaEmail);

			JLabel lblReservaDocNum = new JLabel("Documento:");
			lblReservaDocNum.setBounds(450, 237, 150, 16);
			painelReserva.add(lblReservaDocNum);

			txtReservaDocNum = new JTextField(20);
			txtReservaDocNum.setBounds(535, 231, 150, 28);
			txtReservaDocNum.setToolTipText("Digite seu Documento");
			painelReserva.add(txtReservaDocNum);

			JLabel lblReservaDocTipo = new JLabel("Tipo:");
			lblReservaDocTipo.setBounds(720, 237, 100, 16);
			lblReservaDocTipo.setToolTipText("Selecione o tipo de documento");
			painelReserva.add(lblReservaDocTipo);

			cboReservaDocTipo = new JComboBox<String>();
			cboReservaDocTipo.setBounds(785, 231, 150, 27);
			painelReserva.add(cboReservaDocTipo);

			JLabel lblReservaTelefone = new JLabel("Telefone:");
			lblReservaTelefone.setBounds(450, 277, 150, 16);
			painelReserva.add(lblReservaTelefone);

			txtReservaTelefone = new JTextField(20);
			txtReservaTelefone.setBounds(535, 271, 150, 28);
			txtReservaTelefone.setToolTipText("Digite seu Telefone");
			painelReserva.add(txtReservaTelefone);

			JLabel lblReservaCelular = new JLabel("Celular:");
			lblReservaCelular.setBounds(720, 277, 150, 16);
			painelReserva.add(lblReservaCelular);

			txtReservaCelular = new JTextField(20);
			txtReservaCelular.setBounds(785, 271, 150, 28);
			txtReservaCelular.setToolTipText("Digite seu Celular");
			painelReserva.add(txtReservaCelular);

			JLabel lblReservaCidade = new JLabel("Cidade:");
			lblReservaCidade.setBounds(450, 317, 150, 16);
			painelReserva.add(lblReservaCidade);

			txtReservaCidade = new JTextField(20);
			txtReservaCidade.setBounds(535, 311, 150, 28);
			txtReservaCidade.setToolTipText("Digite sua Cidade");
			painelReserva.add(txtReservaCidade);

			JLabel lblReservaEstado = new JLabel("Estado:");
			lblReservaEstado.setBounds(720, 317, 150, 16);
			painelReserva.add(lblReservaEstado);

			txtReservaEstado = new JTextField(20);
			txtReservaEstado.setBounds(785, 311, 150, 28);
			txtReservaEstado.setToolTipText("Digite seu Estado");
			painelReserva.add(txtReservaEstado);

			JLabel lblReservaPais = new JLabel("Pais:");
			lblReservaPais.setBounds(720, 357, 150, 16);
			painelReserva.add(lblReservaPais);

			txtReservaPais = new JTextField(20);
			txtReservaPais.setBounds(785, 351, 150, 28);
			txtReservaPais.setToolTipText("Digite seu Pais");
			painelReserva.add(txtReservaPais);

			JLabel lblReservaObs = new JLabel("Observações:");
			lblReservaObs.setBounds(450, 377, 100, 16);		
			painelReserva.add(lblReservaObs);

			txtaReservaObs = new JTextArea();
			txtaReservaObs.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaReservaObs.setBorder(new LineBorder(Color.LIGHT_GRAY));
			txtaReservaObs.setLineWrap(true);
			txtaReservaObs.setWrapStyleWord(true);
			txtaReservaObs.setBounds(450, 401, 484, 114);
			painelReserva.add(txtaReservaObs);

			// CONTATO //////////////////////////

			JLabel lblContatoInfo = new JLabel("Fale conosco");
			lblContatoInfo.setBounds(20, 22, 500, 16);		
			painelContato.add(lblContatoInfo);

			txtaContatoInfo = new JTextArea();
			txtaContatoInfo.setName("contatoInfo");
			txtaContatoInfo.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaContatoInfo.setEditable(false);
			txtaContatoInfo .setLineWrap(true);
			txtaContatoInfo .setWrapStyleWord(true);
			txtaContatoInfo.setBounds(20, 46, 914, 90);
			painelContato.add(txtaContatoInfo);
			
			JLabel lblContatoLocal= new JLabel("Nossa localização:");
			lblContatoLocal.setBounds(505, 217, 150, 16);
			painelContato.add(lblContatoLocal);
			
			lblContatoImg = new JLabel();
			lblContatoImg.setName( "lblContatoImg" );
			lblContatoImg.setToolTipText("Localização da pousada");
			lblContatoImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblContatoImg.setBorder(new LineBorder(Color.GRAY));
			lblContatoImg.setBounds(505, 245, 428, 269);
			lblContatoImg.setVisible(true);
			lblContatoImg.setIcon(new ImageIcon(contato.getImage().getScaledInstance(
					lblContatoImg.getWidth(), 
					lblContatoImg.getHeight(), 
					Image.SCALE_DEFAULT)));
			painelContato.add(lblContatoImg);

			JLabel lblContatoNome = new JLabel("Nome:");
			lblContatoNome.setBounds(20, 157, 150, 16);
			painelContato.add(lblContatoNome);

			txtContatoNome = new JTextField(20);
			txtContatoNome.setBounds(85, 151, 400, 28);
			txtContatoNome.setToolTipText("Digite seu nome completo");
			painelContato.add(txtContatoNome);

			JLabel lblContatoEmail = new JLabel("Email:");
			lblContatoEmail.setBounds(20, 197, 150, 16);
			painelContato.add(lblContatoEmail);

			txtContatoEmail = new JTextField(20);
			txtContatoEmail.setBounds(85, 191, 400, 28);
			txtContatoEmail.setToolTipText("Digite seu Email");
			painelContato.add(txtContatoEmail);

			JLabel lblContatoTelefone = new JLabel("Telefone:");
			lblContatoTelefone.setBounds(20, 237, 150, 16);
			painelContato.add(lblContatoTelefone);

			txtContatoTelefone = new JTextField(20);
			txtContatoTelefone.setBounds(85, 231, 150, 28);
			txtContatoTelefone.setToolTipText("Digite seu Telefone");
			painelContato.add(txtContatoTelefone);

			JLabel lblContatoCidade = new JLabel("Cidade:");
			lblContatoCidade.setBounds(285, 237, 150, 16);
			painelContato.add(lblContatoCidade);

			txtContatoCidade = new JTextField(20);
			txtContatoCidade.setBounds(335, 231, 150, 28);
			txtContatoCidade.setToolTipText("Digite sua Cidade");
			painelContato.add(txtContatoCidade);

			JLabel lblContatoEstado = new JLabel("Estado:");
			lblContatoEstado.setBounds(20, 277, 150, 16);
			painelContato.add(lblContatoEstado);

			txtContatoEstado = new JTextField(20);
			txtContatoEstado.setBounds(85, 271, 150, 28);
			txtContatoEstado.setToolTipText("Digite seu Estado");
			painelContato.add(txtContatoEstado);

			JLabel lblContatoPais = new JLabel("Pais:");
			lblContatoPais.setBounds(285, 277, 150, 16);
			painelContato.add(lblContatoPais);

			txtContatoPais = new JTextField(20);
			txtContatoPais.setBounds(335, 271, 150, 28);
			txtContatoPais.setToolTipText("Digite seu Pais");
			painelContato.add(txtContatoPais);

			JLabel lblContatoAssunto = new JLabel("Assunto:");
			lblContatoAssunto.setBounds(20, 337, 100, 16);
			lblContatoAssunto.setToolTipText("Selecione um asssunto para a sua mensagem");
			painelContato.add(lblContatoAssunto);

			cboContatoAssunto = new JComboBox<String>();
			cboContatoAssunto.setBounds(85, 331, 285, 27);
			painelContato.add(cboContatoAssunto);

			JLabel lblContatoMsg = new JLabel("Sua mensagem:");
			lblContatoMsg.setBounds(20, 377, 100, 16);		
			painelContato.add(lblContatoMsg);

			txtaContatoMsg = new JTextArea();
			txtaContatoMsg.setBorder(BorderFactory.createCompoundBorder(borderClean, 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			txtaContatoMsg.setBorder(new LineBorder(Color.LIGHT_GRAY));
			txtaContatoMsg.setLineWrap(true);
			txtaContatoMsg.setWrapStyleWord(true);
			txtaContatoMsg.setBounds(20, 401, 460, 114);
			painelContato.add(txtaContatoMsg);
			
			btnContatoLimpar = new JButton("Limpar");
			btnContatoLimpar.setBounds(390, 331, 100, 29);
			btnContatoLimpar.setToolTipText("Clique aqui para limpar os campos preenchidos");
			btnContatoLimpar.setVisible(true);
			painelContato.add(btnContatoLimpar);
			
			btnContatoEnviar = new JButton("Enviar");
			btnContatoEnviar.setBounds(390, 365, 100, 29);
			painelContato.add(btnContatoEnviar);
			break;

		case "administrador":
			
			// PRINCIPAL ADM //////////////////////////
			
			JLabel lblPesquisa = new JLabel("Pesquisar cliente:");
			lblPesquisa.setBounds(37, 17, 117, 16);
			painelPrincipal.add(lblPesquisa);

			txtPesquisa = new JTextField(20);
			txtPesquisa.setBounds(161, 11, 239, 28);
			txtPesquisa.setToolTipText("Digite o nome ou código como critério");
			painelPrincipal.add(txtPesquisa);

			btnPesquisar = new JButton("Realizar");
			btnPesquisar.setBounds(412, 11, 79, 29);
			btnPesquisar.setVisible(true);
			painelPrincipal.add(btnPesquisar);

			JLabel lblChaleTab = new JLabel("Controle dos Chalés");
			lblChaleTab.setBounds(790, 30, 130, 16);
			painelPrincipal.add(lblChaleTab);

			spChale = new JScrollPane();
			spChale.setToolTipText("clique 2 vezes para editar");
			spChale.setBounds(30, 50, 895, 210);
			painelPrincipal.add(spChale);

			tabChale = new JTable();
			tabChale.setBorder(null);
			spChale.setViewportView(tabChale);

			JLabel lblReservaTab = new JLabel("Reservas de Serviços");
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

		versao = "Versão: 0.1.5";//Substituir por função
		lblVersao = new JLabel( versao );
		lblVersao.setEnabled(false);
		lblVersao.setBounds(33, 666, 97, 16);
		painel.add(lblVersao);

		JLabel lblFundo = new JLabel();
		lblFundo.setBounds(0, 0, 1024, 710);
		lblFundo.setIcon(new ImageIcon(fundo.getImage().getScaledInstance(lblFundo.getWidth(), 
				lblFundo.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblFundo);

		PrincipalCtrl ctrl = new PrincipalCtrl(
				this,
				tabContainer,
				painelReserva,
				painelContato,
				lblPrincipalImg,
				lblChaleImg,
				lblLazerImg,
				lblServicoImg,
				lblReservaImg,
				lblContatoImg,
				txtPesquisa,
				txtaPrincipalInfo,
				txtaPrincipalDetalhe,
				txtaChaleInfo,
				txtaChaleDetalhe,
				txtaLazerInfo,
				txtaLazerDetalhe,
				txtaServicoInfo,
				txtaServicoDetalhe,
				txtaReservaInfo,
				txtaReservaObs,
				txtaContatoInfo,
				txtaContatoMsg,
				cboReservaCategoria,
				cboReservaDocTipo,
				cboContatoAssunto,
				tabChale,
				tabReserva,
				btnLogin,
				btnReservaEnviar,
				btnContatoEnviar,
				btnPesquisar,
				btnReservaLimpar,
				btnContatoLimpar
				);
		
		txtLogin.addKeyListener( ctrl.teclar );
		
		switch ( perfil ){
		
		case "visitante":
			btnLogin.addActionListener( ctrl.acionar );
			btnReservaEnviar.addActionListener( ctrl.acionar );
			btnContatoEnviar.addActionListener( ctrl.acionar );
			btnReservaLimpar.addActionListener( ctrl.acionar );
			btnContatoLimpar.addActionListener( ctrl.acionar );
			cboReservaCategoria.addActionListener( ctrl.acionar );
			break;

		case "administrador":
			btnPesquisar.addActionListener( ctrl.acionar );
			tabReserva.addMouseListener( ctrl.clicar );
			break;
		}
	}
}
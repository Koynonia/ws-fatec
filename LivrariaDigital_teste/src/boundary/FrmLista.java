/**
 * @author Fernando Moraes Oliveira e Vitor Fagundes Arantes
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 06/04/2016
 */

package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import controller.ListaController;
import controller.PrincipalController;
import entity.Livro;

@SuppressWarnings("serial")
public class FrmLista extends JFrame {

	private JPanel painel = new JPanel();
	private JLayeredPane camada;
	private JSeparator linha;
	private JComboBox<String> cboFiltrar;
	private JLabel lblLogo;
	private JLabel lblItem;
	private JLabel lblFiltro;
//	private JLabel lblLivroLanc_1;
//	private JLabel lblLivroLanc_2;
//	private JLabel lblLivroLanc_3;
//	private JLabel lblLivroLanc_4;
//	private JLabel lblLivroLanc_5;
//	private JLabel lblLivroLanc_6;
//	private JLabel lblLivroVend_1;
//	private JLabel lblLivroVend_2;
//	private JLabel lblLivroVend_3;
//	private JLabel lblLivroVend_4;
//	private JLabel lblLivroVend_5;
//	private JLabel lblLivroVend_6;
	private JLabel lblTitulo;
	private JLabel lblAutor;
	private JLabel lblValor;	
	private JLabel lblLivroCapa_1;
	private JLabel lblLivroCapa_2;
	private JLabel lblLivroTitulo_1;
	private JLabel lblLivroAutor_1;
	private JLabel lblLivroValor_1;
	private JLabel lblLivroTitulo_2;
	private JLabel lblLivroAutor_2;
	private JLabel lblLivroValor_2;
	private JTextField txtPesquisar;
<<<<<<< HEAD
=======
	private JTextField txtQtd;
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
	private JButton btnPesquisar;
	private JButton btnLogin;
	private JButton btnCarrinho;
	private JButton btnAddCarrinho_1;
	private JButton btnAddCarrinho_2;	
	private JButton btnDetalhes_1;
	private JButton btnDetalhes_2;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnVoltar;
	private JButton btnLivro;
	private JLabel lblLivro;
	
	
	public FrmLista(String parametro, String pesquisa, final List<Livro> livros) {
		
		
		boolean exibir = false; //colocar no painel administrador

		setTitle("Resultados da Pesquisa");
		setSize(1024, 710);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setContentPane(painel);

		painel.setLayout(null);

		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(36, 36, 314, 45);
		painel.add(lblLogo);

		if(exibir == true){

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
			
		}
			
<<<<<<< HEAD
		btnLogin = new JButton("Entre ou Cadastre-se");
		btnLogin.setBounds(668, 105, 160, 29);
		btnLogin.setVisible(false);
		painel.add(btnLogin);

		btnCarrinho = new JButton("Meu Carrinho");
		btnCarrinho.setBounds(830, 105, 160, 29);
		painel.add(btnCarrinho);

=======
			btnLogin = new JButton("Entre ou Cadastre-se");
			btnLogin.setBounds(650, 105, 160, 29);
			btnLogin.setVisible(false);
			painel.add(btnLogin);
			
			btnCarrinho = new JButton("Meu Carrinho");
			btnCarrinho.setBounds(812, 105, 117, 29);
			painel.add(btnCarrinho);
			
			txtQtd = new JTextField();
			txtQtd.setToolTipText("Total de Livros no Carrinho…");
			txtQtd.setEnabled(false);
			txtQtd.setEditable(false);
			txtQtd.setColumns(10);
			txtQtd.setHorizontalAlignment(SwingConstants.CENTER);
			txtQtd.setBounds(941, 105, 50, 28);
			painel.add(txtQtd);
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535

		camada = new JLayeredPane();
		camada.setBounds(6, 146, 1012, 499);
		camada.setBorder(BorderFactory.createTitledBorder("  " + livros.size()
				+ " resultado(s) da pesquisa por " + parametro + " : "
				+ pesquisa + " "));


		lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(226, 40, 66, 16);
		camada.add(lblTitulo);

		lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(550, 40, 66, 16);
		camada.add(lblAutor);

		lblValor = new JLabel("Preço:");
		lblValor.setBounds(740, 40, 66, 16);
		camada.add(lblValor);

		linha = new JSeparator();
		linha.setBounds(226, 55, 750, 12);
		camada.add(linha);

		lblLivroCapa_1 = new JLabel();
		lblLivroCapa_1.setBounds(41, 70, 128, 188);
		lblLivroCapa_1.setBorder(new LineBorder(Color.GRAY));
		lblLivroCapa_1.setHorizontalAlignment(SwingConstants.CENTER);
		camada.add(lblLivroCapa_1);

		lblLivroTitulo_1 = new JLabel();
		lblLivroTitulo_1.setBounds(226, 106, 300, 16);
		camada.add(lblLivroTitulo_1);

		lblLivroAutor_1 = new JLabel();
		lblLivroAutor_1.setBounds(550, 106, 178, 16);
		camada.add(lblLivroAutor_1);

		lblLivroValor_1 = new JLabel();
<<<<<<< HEAD
		lblLivroValor_1.setBounds(740, 106, 60, 16);
=======
		lblLivroValor_1.setBounds(740, 106, 50, 16);
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		camada.add(lblLivroValor_1);

		btnAddCarrinho_1 = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho_1.setBounds(815, 102, 164, 29);
		camada.add(btnAddCarrinho_1);
		
		btnDetalhes_1 = new JButton("Detalhes do Livro");
		btnDetalhes_1.setBounds(226, 134, 139, 29);
		camada.add(btnDetalhes_1);
		
		lblLivroCapa_2 = new JLabel();
		lblLivroCapa_2.setToolTipText("");
		lblLivroCapa_2.setVisible(false);
		lblLivroCapa_2.setBounds(41, 291, 128, 188);
		lblLivroCapa_2.setBorder(new LineBorder(Color.GRAY));
		lblLivroCapa_2.setHorizontalAlignment(SwingConstants.CENTER);
		camada.add(lblLivroCapa_2);

		lblLivroTitulo_2 = new JLabel();
		lblLivroTitulo_2.setVisible(false);
		lblLivroTitulo_2.setBounds(226, 306, 300, 16);
		camada.add(lblLivroTitulo_2);

		lblLivroAutor_2 = new JLabel();
		lblLivroAutor_2.setVisible(false);
		lblLivroAutor_2.setBounds(550, 306, 178, 16);
		camada.add(lblLivroAutor_2);

		lblLivroValor_2 = new JLabel();
		lblLivroValor_2.setVisible(false);
<<<<<<< HEAD
		lblLivroValor_2.setBounds(740, 306, 60, 16);
=======
		lblLivroValor_2.setBounds(740, 306, 50, 16);
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		camada.add(lblLivroValor_2);

		btnAddCarrinho_2 = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho_2.setVisible(false);
		btnAddCarrinho_2.setBounds(815, 302, 164, 29);
		camada.add(btnAddCarrinho_2);

		btnDetalhes_2 = new JButton("Detalhes do Livro");
		btnDetalhes_2.setVisible(false);
		btnDetalhes_2.setBounds(226, 329, 139, 29);
		camada.add(btnDetalhes_2);

		painel.add(camada);

		btnAnterior = new JButton("Página Anterior");
		btnAnterior.setBounds(627, 653, 125, 29);
		painel.add(btnAnterior);

		btnProximo = new JButton("Próxima Página");
		btnProximo.setBounds(764, 653, 125, 29);
		painel.add(btnProximo);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(901, 653, 117, 29);
		painel.add(btnVoltar);
		
//		btnLivro = new JButton("Livros");
//		btnLivro.setVisible(false);
//		btnLivro.setBounds(812, 6, 117, 29);
//		painel.add(btnLivro);
//		
//		//Acesso Administrador - para testes
//		lblLivro = new JLabel("Adiministrador:");
//		lblLivro.setVisible(false);
//		lblLivro.setBounds(710, 11, 98, 16);
//		painel.add(lblLivro);
		
		
//		PrincipalController principalCtrl = new PrincipalController(
//			this, 
//			painel, 
//			cboFiltrar, 
//			txtPesquisar, 
//			btnLogin, 
//			btnCarrinho, 
//			txtQtd, 
//			btnLivro, 
//			lblLivroLanc_1, 
//			lblLivroLanc_2, 
//			lblLivroLanc_3, 
//			lblLivroLanc_4, 
//			lblLivroLanc_5, 
//			lblLivroLanc_6,
//			lblLivroVend_1, 
//			lblLivroVend_2, 
//			lblLivroVend_3, 
//			lblLivroVend_4, 
//			lblLivroVend_5, 
//			lblLivroVend_6 );
//		
//		txtPesquisar.addActionListener(principalCtrl.pesquisar);
//		btnPesquisar.addActionListener(principalCtrl.pesquisar);
//		btnLogin.addActionListener(principalCtrl.janelas);
//		btnCarrinho.addActionListener(principalCtrl.janelas);
//		btnLivro.addActionListener(principalCtrl.janelas);
		
		
		ListaController listaCtrl = new ListaController(
				this, 
				livros, 
				lblTitulo, 
				lblAutor, 
				lblValor, 	
				lblLivroCapa_1, 
				lblLivroTitulo_1, 
				lblLivroAutor_1, 
				lblLivroValor_1, 
				lblLivroCapa_2,
				lblLivroTitulo_2, 
				lblLivroAutor_2, 
<<<<<<< HEAD
				lblLivroValor_2,  
=======
				lblLivroValor_2, 
				txtQtd, 
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
				btnDetalhes_1, 
				btnAddCarrinho_1, 
				btnDetalhes_2,	
				btnAddCarrinho_2, 
				btnCarrinho, 
				btnAnterior, 
				btnProximo, 
				btnVoltar);
		
		btnCarrinho.addActionListener(listaCtrl.janelas);
		lblLivroCapa_1.addMouseListener(listaCtrl.clique);
		lblLivroCapa_2.addMouseListener(listaCtrl.clique);
		btnDetalhes_1.addActionListener(listaCtrl.janelas);
		btnDetalhes_2.addActionListener(listaCtrl.janelas);
		btnAddCarrinho_1.addActionListener(listaCtrl.adicionar);
		btnAddCarrinho_2.addActionListener(listaCtrl.adicionar);
		btnAnterior.addActionListener(listaCtrl.registros);
		btnProximo.addActionListener(listaCtrl.registros);
		btnVoltar.addActionListener(listaCtrl.janelas);
<<<<<<< HEAD
		btnAddCarrinho_1.addFocusListener(listaCtrl.foco);
		btnAddCarrinho_2.addFocusListener(listaCtrl.foco);
		btnCarrinho.addFocusListener(listaCtrl.foco);
=======
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
	}
}
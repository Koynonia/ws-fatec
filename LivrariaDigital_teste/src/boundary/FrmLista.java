package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import entity.Livro;
import controller.PrincipalController;

@SuppressWarnings("serial")
public class FrmLista extends JFrame {

	private FrmPrincipal janelaPrincipal;
	private JFrame janela = new JFrame("Resultado da Pesquisa");
	private JPanel painel = new JPanel();
	private JLayeredPane camada;
	private JSeparator separador;
	private JComboBox<String> cbItensPesquisa;
	private JLabel lblLogo;
	private JLabel lblItem;
	private JLabel lblFiltro;
	private JLabel lblTitulo;
	private JLabel lblAutor;
	private JLabel lblValor;	
	private JLabel lblImagem1;
	private JLabel lblImagem2;
	private JLabel lblLivroTitulo1;
	private JLabel lblLivroAutor1;
	private JLabel lblLivroValor1;
	private JLabel lblLivroTitulo2;
	private JLabel lblLivroAutor2;
	private JLabel lblLivroValor2;
	private JTextField txtPesquisa;
	private JTextField txtQtd;
	private JButton btnProcurar;
	private JButton btnCarrinho;
	private JButton btnAddCarrinho1;
	private JButton btnAddCarrinho2;	
	private JButton btnDetalhes;
	private JButton btnDetalhes2;
	private JButton btnAnterior;
	private JButton btnProxima;
	private JButton btnVoltar;


	public FrmLista(String parametro, String pesquisa, List<Livro> livro) {

		boolean exibir = true; //colocar no painel administrador

		janela.setSize(1024, 768);
		janela.setVisible(true);
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		janela.setContentPane(painel);

		painel.setLayout(null);

		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(41, 36, 314, 45);
		painel.add(lblLogo);

//		if(exibir == true){

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
			btnProcurar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					janela.dispose();
				}
			});
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
//		}

		camada = new JLayeredPane();
		camada.setBounds(6, 146, 1012, 499);
		camada.setBorder(BorderFactory.createTitledBorder("  " + livro.size()
				+ " resultado(s) da pesquisa por " + parametro + " - "
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

		separador = new JSeparator();
		separador.setBounds(226, 55, 750, 12);
		camada.add(separador);

		lblImagem1 = new JLabel("Falta Imagem");
		lblImagem1.setBounds(41, 70, 128, 188);
		lblImagem1.setBorder(new LineBorder(Color.GRAY));
		lblImagem1.setHorizontalAlignment(SwingConstants.CENTER);
		camada.add(lblImagem1);

		lblLivroTitulo1 = new JLabel(livro.get(0).getTitulo());
		lblLivroTitulo1.setBounds(226, 106, 300, 16);
		camada.add(lblLivroTitulo1);

		lblLivroAutor1 = new JLabel(livro.get(0).getAutor());
		lblLivroAutor1.setBounds(550, 106, 178, 16);
		camada.add(lblLivroAutor1);

		lblLivroValor1 = new JLabel(String.valueOf(livro.get(0).getPrecoVenda()));
		lblLivroValor1.setBounds(740, 106, 50, 16);
		camada.add(lblLivroValor1);

		btnAddCarrinho1 = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAddCarrinho1.setBounds(815, 102, 164, 29);
		camada.add(btnAddCarrinho1);

		lblImagem2 = new JLabel("Falta Imagem");
		lblImagem2.setBounds(41, 291, 128, 188);
		lblImagem2.setBorder(new LineBorder(Color.GRAY));
		lblImagem2.setHorizontalAlignment(SwingConstants.CENTER);
		camada.add(lblImagem2);

		lblLivroTitulo2 = new JLabel(livro.get(1).getTitulo());
		lblLivroTitulo2.setBounds(226, 306, 300, 16);
		camada.add(lblLivroTitulo2);

		lblLivroAutor2 = new JLabel(livro.get(1).getAutor());
		lblLivroAutor2.setBounds(550, 306, 178, 16);
		camada.add(lblLivroAutor2);

		lblLivroValor2 = new JLabel(String.valueOf(livro.get(1).getPrecoVenda()));
		lblLivroValor2.setBounds(740, 306, 50, 16);
		camada.add(lblLivroValor2);

		btnAddCarrinho2 = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho2.setBounds(815, 302, 164, 29);
		camada.add(btnAddCarrinho2);

		btnDetalhes = new JButton("Detalhes do Livro");
		btnDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetalhes.setBounds(226, 134, 139, 29);
		camada.add(btnDetalhes);

		btnDetalhes2 = new JButton("Detalhes do Livro");
		btnDetalhes2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetalhes2.setBounds(226, 329, 139, 29);
		camada.add(btnDetalhes2);

		painel.add(camada);

		btnAnterior = new JButton("Página Anterior");
		btnAnterior.setBounds(627, 645, 125, 29);
		painel.add(btnAnterior);

		btnProxima = new JButton("Próxima Página");
		btnProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int position = 2;
				camada.setLayer(btnAddCarrinho1, position);
			}
		});
		btnProxima.setBounds(764, 645, 125, 29);
		painel.add(btnProxima);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (janelaPrincipal == null){
					janelaPrincipal = new FrmPrincipal();
					janelaPrincipal.setVisible(true);
				} else {
					janelaPrincipal.setVisible(true);
					janelaPrincipal.setState(JFrame.NORMAL);
				}
				janela.dispose();
			}
		});
		btnVoltar.setBounds(901, 645, 117, 29);
		painel.add(btnVoltar);
		
		PrincipalController controlador = new PrincipalController(
				cbItensPesquisa, txtPesquisa, btnProcurar, btnCarrinho, this);
		
		btnProcurar.addActionListener(controlador);
		btnCarrinho.addActionListener(controlador);
	}
}
/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 27/04/2016
 */

package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import controller.DetalheController;
import entity.Livro;
<<<<<<< HEAD
import java.awt.SystemColor;
=======
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535

@SuppressWarnings("serial")
public class FrmDetalhe extends JFrame {

	private String diretorio = "../LivrariaDigital_teste/";
	private ImageIcon capa = new ImageIcon( diretorio + "imagem/capa.png" );
	private JPanel painel;
	private JSeparator linha;
	private JLabel lblLogo;
	private JLabel lblPagina; 
	private JLabel lblTitulo; 
	private JLabel lblAutor; 
	private JLabel lblCategria; 
	private JLabel lblTipoCapa; 
	private JLabel lblEditora; 
	private JLabel lblPaginas; 
	private JLabel lblIsbn; 
	private JLabel lblDtPub; 
	private JLabel lblCapa; 
	private JLabel lblSumario; 
	private JLabel lblResumo; 
	private JLabel lblPreco;
	private JTextArea txtaSumario;
	private JTextArea txtaResumo;
	private JButton btnAddCarrinho;
	private JButton btnVoltar;
	
	
	public FrmDetalhe(JFrame janelaAnterior, int opt, ArrayList<String> isbn, final List<Livro> livros) {

		setTitle("Detalhes do Livro");
		setSize(1024, 710);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		painel = new JPanel();
		setContentPane( painel );
		painel.setLayout(null);
//		setContentPane(painel);
		
		
		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(36, 36, 314, 45);
		painel.add(lblLogo);
		
		lblPagina = new JLabel("Detalhes do Livro");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(837, 76, 140, 16);
		painel.add(lblPagina);
		
		linha = new JSeparator();
		linha.setBounds(6, 93, 1012, 12);
		painel.add(linha);
		
		lblCapa = new JLabel();
		lblCapa.setName( "lblCapa" );
		lblCapa.setToolTipText("Capa");
		lblCapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapa.setBorder(new LineBorder(Color.GRAY));
		lblCapa.setBounds(36, 140, 287, 394);
		lblCapa.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblCapa.getWidth(), 
		lblCapa.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblCapa);
		
		lblTitulo = new JLabel("Título: ");
		lblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblTitulo.setBounds(347, 140, 630, 29);
		painel.add(lblTitulo);
		
		lblAutor = new JLabel("Autor(es): ");
		lblAutor.setBounds(347, 174, 630, 16);
		painel.add(lblAutor);
		
		lblCategria = new JLabel("Categoria(s): ");
		lblCategria.setBounds(347, 191, 630, 16);
		painel.add(lblCategria);
		
		lblTipoCapa = new JLabel("Tipo de Capa: ");
		lblTipoCapa.setBounds(347, 235, 630, 16);
		painel.add(lblTipoCapa);
		
		lblEditora = new JLabel("Editora: ");
		lblEditora.setBounds(347, 255, 630, 16);
		painel.add(lblEditora);
		
		lblPaginas = new JLabel("Páginas: ");
		lblPaginas.setBounds(347, 275, 630, 16);
		painel.add(lblPaginas);
		
		lblIsbn = new JLabel("ISBN: ");
		lblIsbn.setBounds(347, 295, 630, 16);
		painel.add(lblIsbn);
		
		lblDtPub = new JLabel("Data de Publicação: ");
		lblDtPub.setBounds(347, 315, 630, 16);
		painel.add(lblDtPub);
		
		lblSumario = new JLabel("Sumário");
<<<<<<< HEAD
		lblSumario.setBounds(347, 389, 83, 16);		
		painel.add(lblSumario);
		
		txtaSumario = new JTextArea(1, 1);
		txtaSumario.setBackground(SystemColor.window);
=======
		lblSumario.setBounds(347, 395, 83, 16);		
		painel.add(lblSumario);
		
		txtaSumario = new JTextArea(1, 1);
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		txtaSumario.setEditable(false);
//		txtaSumario .setFont(new Font("Serif", Font.ITALIC, 16));
		txtaSumario .setLineWrap(true);
		txtaSumario .setWrapStyleWord(true);
		txtaSumario.setBounds(431, 389, 546, 66);
		painel.add(txtaSumario);
		
		lblResumo = new JLabel("Resumo");
<<<<<<< HEAD
		lblResumo.setBounds(347, 475, 83, 16);
		painel.add(lblResumo);
		
		txtaResumo = new JTextArea(1, 1);
		txtaResumo.setBackground(SystemColor.window);
=======
		lblResumo.setBounds(347, 481, 83, 16);
		painel.add(lblResumo);
		
		txtaResumo = new JTextArea(1, 1);
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		txtaResumo.setEditable(false);
//		txtaResumo.setFont(new Font("Serif", Font.ITALIC, 16));
		txtaResumo.setLineWrap(true);
		txtaResumo.setWrapStyleWord(true);
		txtaResumo.setBounds(431, 475, 546, 66);
		painel.add(txtaResumo);
		
		lblPreco = new JLabel("Preço: R$ ");
		lblPreco.setBounds(40, 566, 118, 16);
		painel.add(lblPreco);
		
		btnAddCarrinho = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho.setBounds(170, 561, 161, 29);
		painel.add(btnAddCarrinho);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(860, 634, 117, 29);
		painel.add(btnVoltar);
		
		
		DetalheController detalhesCtrl = new DetalheController(
				this, 
				janelaAnterior, 
				livros, 
				lblTitulo, 
				lblAutor, 
				lblCategria, 
				lblTipoCapa, 
				lblEditora, 
				lblPaginas, 
				lblIsbn, 
				lblDtPub, 
				lblCapa, 
				lblPreco,
				txtaSumario,
				txtaResumo,
				btnAddCarrinho, 
				btnVoltar);
		
		detalhesCtrl.escreverTela(opt, isbn);
		btnAddCarrinho.addActionListener(detalhesCtrl.adicionar);
		btnVoltar.addActionListener(detalhesCtrl.janelas);
	}
}
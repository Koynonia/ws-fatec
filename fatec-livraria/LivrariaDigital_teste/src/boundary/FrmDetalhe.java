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
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class FrmDetalhe extends JFrame {

	private String diretorio = "../LivrariaDigital_teste/";
	private ImageIcon capa = new ImageIcon( diretorio + "imagem/capa.png" );
	private JPanel painel;
	private JSeparator linha;
	private JLabel lblLogo;
	private JLabel lblPagina;  
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
	private JTextArea txtaTitulo; 
	private JTextArea txtaSumario; 
	private JTextArea txtaResumo; 
	private JButton btnAddCarrinho; 
	private JButton btnVoltar; 
	
	
	public FrmDetalhe(JFrame janelaAnterior, int opt, ArrayList<String> isbn, final List<Livro> livros) {

		setTitle("Detalhes do Livro");
		setName("Detalhes");
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
		lblLogo.setForeground(new Color(50, 205, 50));
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
		lblCapa.setBounds(36, 140, 321, 441);
		lblCapa.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblCapa.getWidth(), 
		lblCapa.getHeight(), Image.SCALE_DEFAULT)));
		painel.add(lblCapa);
		
		txtaTitulo = new JTextArea(1, 1);
		txtaTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		txtaTitulo.setBackground(SystemColor.window);
		txtaTitulo.setEditable(false);
		txtaTitulo.setLineWrap(true);
		txtaTitulo.setWrapStyleWord(true);
		txtaTitulo.setBounds(395, 140, 582, 50);
		painel.add(txtaTitulo);
		
		lblAutor = new JLabel("Autor(es): ");
		lblAutor.setBounds(395, 194, 582, 16);
		painel.add(lblAutor);
		
		lblCategria = new JLabel("Categoria(s): ");
		lblCategria.setBounds(395, 211, 582, 16);
		painel.add(lblCategria);
		
		lblTipoCapa = new JLabel("Tipo de Capa: ");
		lblTipoCapa.setBounds(395, 255, 582, 16);
		painel.add(lblTipoCapa);
		
		lblEditora = new JLabel("Editora: ");
		lblEditora.setBounds(395, 275, 582, 16);
		painel.add(lblEditora);
		
		lblPaginas = new JLabel("Páginas: ");
		lblPaginas.setBounds(395, 295, 582, 16);
		painel.add(lblPaginas);
		
		lblIsbn = new JLabel("ISBN: ");
		lblIsbn.setBounds(395, 315, 582, 16);
		painel.add(lblIsbn);
		
		lblDtPub = new JLabel("Data de Publicação: ");
		lblDtPub.setBounds(395, 335, 582, 16);
		painel.add(lblDtPub);
		
		lblPreco = new JLabel("Preço: R$ ");
		lblPreco.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreco.setBounds(814, 256, 161, 16);
		painel.add(lblPreco);
		
		lblSumario = new JLabel("Sumário:");
		lblSumario.setBounds(395, 377, 83, 16);		
		painel.add(lblSumario);
		
		txtaSumario = new JTextArea(1, 1);
		txtaSumario.setBackground(SystemColor.window);
		txtaSumario.setEditable(false);
//		txtaSumario .setFont(new Font("Serif", Font.ITALIC, 16));
		txtaSumario .setLineWrap(true);
		txtaSumario .setWrapStyleWord(true);
		txtaSumario.setBounds(395, 405, 582, 66);
		painel.add(txtaSumario);
		
		lblResumo = new JLabel("Resumo:");
		lblResumo.setBounds(395, 483, 83, 16);
		painel.add(lblResumo);
		
		txtaResumo = new JTextArea(1, 1);
		txtaResumo.setBackground(SystemColor.window);
		txtaResumo.setEditable(false);
//		txtaResumo.setFont(new Font("Serif", Font.ITALIC, 16));
		txtaResumo.setLineWrap(true);
		txtaResumo.setWrapStyleWord(true);
		txtaResumo.setBounds(395, 513, 582, 66);
		painel.add(txtaResumo);
		
		btnAddCarrinho = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho.setBounds(113, 607, 161, 29);
		painel.add(btnAddCarrinho);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(860, 634, 117, 29);
		painel.add(btnVoltar);
		
		
		DetalheController detalhesCtrl = new DetalheController(
				this, 
				janelaAnterior, 
				livros, 
				txtaTitulo, 
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
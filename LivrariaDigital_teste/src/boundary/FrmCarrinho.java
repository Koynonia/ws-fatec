/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 11/04/2016
 */

package boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controller.CarrinhoController;
import java.text.Format;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class FrmCarrinho extends JFrame {
	
	public JTable tabCompra; //Campos compartilhados com o FrmLista
	public JFormattedTextField ftxtQtd;
	public JFormattedTextField ftxtVlrTotal;
	private JPanel painel;
	private JScrollPane spCompras;
	private JLabel lblLogo;
	private JLabel lblPagina;
	private JSeparator linha;
	private JLabel lblQtd;
	private JLabel lblVlrTotal;
	private JButton btnAlterar;
	private JButton btnConcluir;
	private JButton btnVoltar;
	private JButton btnLimpar;
	private DecimalFormat maskValor;
	private JButton btnRetirar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCarrinho frame = new FrmCarrinho();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmCarrinho() {
		
		
		setTitle("Carrinho");
		setName("Carrinho");
		setResizable(false);
		setAlwaysOnTop (true);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 480);
		setLocationRelativeTo(null);
		
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);
		
		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(36, 36, 314, 45);
		painel.add(lblLogo);
		
		lblPagina = new JLabel("Carrinho");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(656, 54, 73, 16);
		painel.add(lblPagina);
		
		linha = new JSeparator();
		linha.setBounds(6, 71, 756, 12);
		painel.add(linha);
		
		spCompras = new JScrollPane();
		spCompras.setToolTipText("Selecione um livro para alterar a quantidade"
				+ " ou clique 2 vezes para excluir…");
		spCompras.setBounds(38, 107, 691, 210);
		painel.add(spCompras);
		
		tabCompra = new JTable();
		tabCompra.setToolTipText("clique 2 vezes para excluir…");
		spCompras.setViewportView(tabCompra);
		tabCompra.setBorder(null);
		
		lblQtd = new JLabel("Quantidade");
		lblQtd.setBounds(397, 334, 73, 16);
		painel.add(lblQtd);
		
		ftxtQtd = new JFormattedTextField((Format) null);
		ftxtQtd.setBackground(SystemColor.window);
		ftxtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtQtd.setEditable(false);
		ftxtQtd.setColumns(10);
		ftxtQtd.setBounds(478, 329, 58, 28);
		painel.add(ftxtQtd);
		
		lblVlrTotal = new JLabel("Total Geral");
		lblVlrTotal.setBounds(559, 335, 73, 16);
		painel.add(lblVlrTotal);
		
		maskValor = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter total = new NumberFormatter(maskValor);
		total.setFormat(maskValor);
		total.setAllowsInvalid(false);
		ftxtVlrTotal = new JFormattedTextField(maskValor);
		ftxtVlrTotal.setBackground(SystemColor.window);
		ftxtVlrTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtVlrTotal.setEditable(false);
		ftxtVlrTotal.setBounds(631, 329, 98, 28);
		ftxtVlrTotal.setColumns(10);
		painel.add(ftxtVlrTotal);
		
		btnAlterar = new JButton("Quantidade");
		btnAlterar.setToolTipText("Selecione o Livro que deseja alterar a quantidade…");
		btnAlterar.setBounds(38, 329, 117, 29);
		painel.add(btnAlterar);
		
		btnRetirar = new JButton("Retirar");
		btnRetirar.setToolTipText("Selecione o Livro que deseja retirar do Carrinho…");
		btnRetirar.setBounds(157, 329, 117, 29);
		painel.add(btnRetirar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(353, 408, 117, 29);
		painel.add(btnLimpar);
		
		btnConcluir = new JButton("Concluir Pedido");
		btnConcluir.setBounds(478, 408, 127, 29);
		painel.add(btnConcluir);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(612, 408, 117, 29);
		painel.add(btnVoltar);
				
		CarrinhoController carrinhoCtrl = new CarrinhoController(
				this, 
				tabCompra, 
				ftxtQtd, 
				ftxtVlrTotal);
		
		tabCompra.addMouseListener(carrinhoCtrl.clique);
		tabCompra.addKeyListener(carrinhoCtrl.tecla);
		btnAlterar.addActionListener(carrinhoCtrl.alterar);
		btnRetirar.addActionListener(carrinhoCtrl.retirar);
		btnLimpar.addActionListener(carrinhoCtrl.limpar);
		btnConcluir.addActionListener(carrinhoCtrl.concluir);
		btnVoltar.addActionListener(carrinhoCtrl.voltar);
		
	  
	}
}

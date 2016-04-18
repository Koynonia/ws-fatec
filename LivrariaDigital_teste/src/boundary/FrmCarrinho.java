/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controller.CarrinhoController;

@SuppressWarnings("serial")
public class FrmCarrinho extends JFrame {

	private JPanel painel;
	private JScrollPane spCompras;
	private JTable tabCompra;
	private JButton btnAlterar;
	private JLabel lblVlrTotal;
	private JFormattedTextField ftxtVlrTotal;
	private DecimalFormat maskValor;
	private JButton btnConcluir;
	private JButton btnVoltar;
	private JButton btnLimpar;
	
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
		setResizable(false);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 480);
		setLocationRelativeTo(null);
		
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);
		
		JSeparator separador = new JSeparator();
		separador.setBounds(6, 71, 756, 12);
		painel.add(separador);
		
		spCompras = new JScrollPane();
		spCompras.setBounds(38, 107, 691, 210);
		painel.add(spCompras);
		
		tabCompra = new JTable();
		tabCompra.setToolTipText("Clique para selecionarâ€¦");
		spCompras.setViewportView(tabCompra);
		tabCompra.setBorder(null);
		
		lblVlrTotal = new JLabel("Total Geral");
		lblVlrTotal.setBounds(546, 334, 73, 16);
		painel.add(lblVlrTotal);
		
		maskValor = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter subtotal = new NumberFormatter(maskValor);
		subtotal.setFormat(maskValor);
		subtotal.setAllowsInvalid(false);
		ftxtVlrTotal = new JFormattedTextField(maskValor);
		ftxtVlrTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtVlrTotal.setEnabled(false);
		ftxtVlrTotal.setEditable(false);
		ftxtVlrTotal.setBounds(631, 329, 98, 28);
		ftxtVlrTotal.setColumns(10);
		painel.add(ftxtVlrTotal);
		
		btnAlterar = new JButton("Alterar Quantidade");
		btnAlterar.setBounds(38, 329, 142, 29);
		painel.add(btnAlterar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(353, 424, 117, 29);
		painel.add(btnLimpar);
		
		btnConcluir = new JButton("Concluir Pedido");
		btnConcluir.setBounds(478, 424, 127, 29);
		painel.add(btnConcluir);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(612, 424, 117, 29);
		painel.add(btnVoltar);
		
		JLabel lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(38, 25, 314, 45);
		painel.add(lblLogo);
		
		CarrinhoController controle = new CarrinhoController(this, tabCompra, ftxtVlrTotal);
		
		btnAlterar.addActionListener(controle.alterar);
		btnLimpar.addActionListener(controle.limpar);
		btnConcluir.addActionListener(controle.concluir);
		btnVoltar.addActionListener(controle.voltar);
	}
}

/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.boundary;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;

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

import edu.pousada.controller.ReservaCtrl;

public class ReservaFrm extends JFrame {

	private static final long serialVersionUID = -1419041163683085620L;
	private JPanel painel;
	private JScrollPane spCompras;
	private JSeparator linha;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtVlrTotal;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JButton btnConcluir;
	private JButton btnVoltar;
	private JTable tabCompra;
	private DecimalFormat maskValor;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaFrm frame = new ReservaFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReservaFrm() throws ParseException {

		setTitle("Reservas");
		setName("Reservas");
		setResizable(false);
		setAlwaysOnTop (true);
		setUndecorated(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 480);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);

		JLabel lblPagina = new JLabel("Reservas");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblPagina.setBounds(38, 47, 117, 23);
		painel.add(lblPagina);

		linha = new JSeparator();
		linha.setBounds(6, 71, 756, 12);
		painel.add(linha);

		spCompras = new JScrollPane();
		spCompras.setToolTipText("Selecione uma reserva para alterar a quantidade"
				+ " ou clique 2 vezes para excluir…");
		spCompras.setBounds(38, 107, 691, 210);
		painel.add(spCompras);

		tabCompra = new JTable();
		tabCompra.setToolTipText("clique 2 vezes para excluir…");
		spCompras.setViewportView(tabCompra);
		tabCompra.setBorder(null);

		JLabel lblQtd = new JLabel("Quantidade");
		lblQtd.setBounds(397, 334, 73, 16);
		painel.add(lblQtd);

		ftxtQtd = new JFormattedTextField((Format) null);
		ftxtQtd.setBackground(SystemColor.window);
		ftxtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtQtd.setEditable(false);
		ftxtQtd.setColumns(10);
		ftxtQtd.setBounds(478, 329, 58, 28);
		painel.add(ftxtQtd);

		JLabel lblVlrTotal = new JLabel("Total Geral");
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

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Selecione a reserva que deseja cancelar");
		btnCancelar.setBounds(38, 329, 117, 29);
		painel.add(btnCancelar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(353, 408, 117, 29);
		painel.add(btnLimpar);

		btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(478, 408, 127, 29);
		painel.add(btnConcluir);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(612, 408, 117, 29);
		painel.add(btnVoltar);

		ReservaCtrl ctrl = new ReservaCtrl(
				this, 
				tabCompra, 
				ftxtQtd, 
				ftxtVlrTotal,
				btnCancelar,
				btnLimpar,
				btnConcluir,
				btnVoltar
				);

		tabCompra.addMouseListener(ctrl.clicar);
		tabCompra.addKeyListener(ctrl.teclar);
		btnCancelar.addActionListener(ctrl.acionar);
		btnLimpar.addActionListener(ctrl.acionar);
		btnConcluir.addActionListener(ctrl.acionar);
		btnVoltar.addActionListener(ctrl.acionar);
	}
}
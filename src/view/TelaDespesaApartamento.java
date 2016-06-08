package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BotaoDespesasApartamentoController;
import controller.TableDespesasAptoController;
import controller.TableModelDespesasApto;

public class TelaDespesaApartamento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumApto;
	private JTextField txtDespesa;
	private JTextField txtRef;
	private JTextField txtVencimento;
	private JTextField txtValor;
	private JTable table;
	private JTextField txtTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDespesaApartamento frame = new TelaDespesaApartamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaDespesaApartamento() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNmeroDoApartamento = new JLabel("N\u00FAmero do Apartamento:");
		lblNmeroDoApartamento.setBounds(58, 48, 165, 14);
		contentPane.add(lblNmeroDoApartamento);

		txtNumApto = new JTextField();
		txtNumApto.setBounds(233, 45, 86, 20);
		contentPane.add(txtNumApto);
		txtNumApto.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(355, 44, 89, 23);
		contentPane.add(btnPesquisar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 698, 14);
		contentPane.add(separator);

		JLabel lblNomeDaDespesa = new JLabel("Nome da despesa:");
		lblNomeDaDespesa.setBounds(58, 104, 141, 14);
		contentPane.add(lblNomeDaDespesa);

		txtDespesa = new JTextField();
		txtDespesa.setBounds(185, 101, 220, 20);
		contentPane.add(txtDespesa);
		txtDespesa.setColumns(10);

		txtRef = new JTextField();
		txtRef.setBounds(185, 132, 108, 20);
		contentPane.add(txtRef);
		txtRef.setColumns(10);

		JLabel lblReferncia = new JLabel("Refer\u00EAncia:");
		lblReferncia.setBounds(89, 135, 86, 14);
		contentPane.add(lblReferncia);

		JLabel lblVencimento = new JLabel("Vencimento:");
		lblVencimento.setBounds(89, 166, 77, 14);
		contentPane.add(lblVencimento);

		txtVencimento = new JTextField();
		txtVencimento.setBounds(185, 163, 108, 20);
		contentPane.add(txtVencimento);
		txtVencimento.setColumns(10);

		txtValor = new JTextField();
		txtValor.setBounds(185, 191, 108, 20);
		contentPane.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(120, 194, 46, 14);
		contentPane.add(lblValor);

		JButton btnAtualizarDespesa = new JButton("Atualizar Despesa");
		btnAtualizarDespesa.setBounds(456, 157, 189, 23);
		contentPane.add(btnAtualizarDespesa);

		JButton btnGravarDespesa = new JButton("Gravar Despesa");
		btnGravarDespesa.setBounds(456, 190, 189, 23);
		contentPane.add(btnGravarDespesa);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 254, 587, 205);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		Object[][] dados = new Object[][] {};
		String[] cabecalho = new String[5];

		cabecalho[0] = "Nome da Despesa";
		cabecalho[1] = "Referencia";
		cabecalho[2] = "Vencimento";
		cabecalho[3] = "Valor";
		cabecalho[4] = "Seleção";

		DefaultTableModel model = new TableModelDespesasApto(dados,
				cabecalho);

		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(418);
		table.getColumnModel().getColumn(1).setPreferredWidth(121);
		table.getColumnModel().getColumn(2).setPreferredWidth(121);
		table.getColumnModel().getColumn(3).setPreferredWidth(121);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);

//		DespesasApartamentoTableController dAController = new DespesasApartamentoTableController(
//				model);
//
//		try {
//			dAController.preencheTable();
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
//					JOptionPane.ERROR_MESSAGE);
//		}

		JButton btnExcluirSeleo = new JButton("Excluir Selecionado(s)");
		btnExcluirSeleo.setBounds(279, 477, 165, 33);
		contentPane.add(btnExcluirSeleo);

		txtTotal = new JTextField();
		txtTotal.setBounds(559, 477, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(503, 480, 46, 14);
		contentPane.add(lblTotal);

		JButton btnSelecionarTudo = new JButton("Selecionar\r\n Tudo");
		btnSelecionarTudo.setBounds(58, 477, 165, 33);
		contentPane.add(btnSelecionarTudo);

		BotaoDespesasApartamentoController bDAController = new BotaoDespesasApartamentoController(
				txtNumApto, txtDespesa, txtRef, txtValor, txtVencimento,
				btnPesquisar, btnAtualizarDespesa, btnExcluirSeleo,
				btnGravarDespesa, btnSelecionarTudo);
		
		btnPesquisar.addActionListener(bDAController);
		btnAtualizarDespesa.addActionListener(bDAController);
		btnExcluirSeleo.addActionListener(bDAController);
		btnGravarDespesa.addActionListener(bDAController);
		btnSelecionarTudo.addActionListener(bDAController);
	}
}

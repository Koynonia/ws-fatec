package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.DespesasAptoController;
import controller.TableClickDespesaApto;
import controller.TableModelDespesasApto;
import model.Apartamentos;
import model.Despesas;
import persistence.DespesasApartamentoDao;
import persistence.IDespesasApartamentoDao;
import javax.swing.ImageIcon;

public class TelaDespesaApartamento extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField txtNumApto;
	private JTextField txtDespesa;
	private JTextField txtValor;
	private JTextField idDespesa;
	private JTextField txtTotal;

	private JLabel lblLogo;
	private JLabel lblPagina;
	private JLabel lblNomeDaDespesa;
	private JLabel lblReferncia;
	private JLabel lblVencimento;
	private JLabel lblValor;
	private JLabel lblTotal;
	private JLabel lblNmeroDoApartamento;
	private JLabel lblBack;

	private JButton btnAtualizarDespesa;
	private JButton btnGravarDespesa;
	private JButton btnExcluirSeleo;
	private JButton btnPesquisar;
	private JButton btnSelecionarTudo;
	private JButton btnVoltar;

	private JSeparator separator;

	private JSeparator separator_1;

	private JFormattedTextField txtRef;
	private JFormattedTextField txtVencimento;

	private JTable table;

	private DefaultTableModel model;

	private JScrollPane scrollPane;

	private MaskFormatter formater;

	private static final DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

	private static int idApto;
	

	// private static int idDespesa;

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
		setTitle("Cadastro de Despesas do Apartamento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 721);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLogo = new JLabel("CONDOMINIO DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(48, 36, 410, 45);
		contentPane.add(lblLogo);

		lblPagina = new JLabel("Cadastro de Despesas do Apartamento");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(320, 92, 361, 22);
		contentPane.add(lblPagina);

		lblNmeroDoApartamento = new JLabel("N\u00FAmero do Apartamento:");
		lblNmeroDoApartamento.setBounds(58, 147, 165, 14);
		contentPane.add(lblNmeroDoApartamento);

		txtNumApto = new JTextField();
		txtNumApto.setBounds(233, 140, 86, 28);
		contentPane.add(txtNumApto);
		txtNumApto.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(355, 139, 101, 28);
		contentPane.add(btnPesquisar);

		separator = new JSeparator();
		separator.setBounds(10, 189, 671, 14);
		contentPane.add(separator);

		lblNomeDaDespesa = new JLabel("Nome da despesa:");
		lblNomeDaDespesa.setBounds(58, 221, 141, 14);
		contentPane.add(lblNomeDaDespesa);

		txtDespesa = new JTextField();
		txtDespesa.setBounds(184, 214, 220, 28);
		contentPane.add(txtDespesa);
		txtDespesa.setColumns(10);

		txtRef = new JFormattedTextField(df);
		txtRef.setBounds(184, 253, 108, 28);
		txtRef.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtRef);
		txtRef.setColumns(10);

		txtVencimento = new JFormattedTextField(df);
		txtVencimento.setBounds(184, 292, 108, 28);
		txtVencimento.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtVencimento);
		txtVencimento.setColumns(10);

		idDespesa = new JTextField();
		idDespesa.setBounds(1, 1, 1, 1);
		contentPane.add(idDespesa);
		idDespesa.setFocusable(false);

		lblReferncia = new JLabel("Refer\u00EAncia:");
		lblReferncia.setBounds(89, 260, 86, 14);
		contentPane.add(lblReferncia);

		lblVencimento = new JLabel("Vencimento:");
		lblVencimento.setBounds(95, 299, 78, 14);
		contentPane.add(lblVencimento);

		formater = new MaskFormatter();

		try {
			formater.setMask("##/##/####");
			formater.install(txtVencimento);
			formater.install(txtRef);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtValor = new JTextField();
		txtValor.setBounds(184, 331, 108, 28);
		contentPane.add(txtValor);
		txtValor.setColumns(10);

		lblValor = new JLabel("Valor:");
		lblValor.setBounds(119, 338, 46, 14);
		contentPane.add(lblValor);

		btnAtualizarDespesa = new JButton("Atualizar Despesa");
		btnAtualizarDespesa.setBounds(456, 292, 189, 28);
		contentPane.add(btnAtualizarDespesa);
		btnAtualizarDespesa.setEnabled(false);

		btnGravarDespesa = new JButton("Gravar Despesa");
		btnGravarDespesa.setBounds(456, 331, 189, 28);
		contentPane.add(btnGravarDespesa);

		btnExcluirSeleo = new JButton("Excluir Selecionado(s)");
		btnExcluirSeleo.setBounds(279, 610, 165, 28);
		contentPane.add(btnExcluirSeleo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 387, 587, 205);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Object[][] dados = new Object[][] {};
		String[] cabecalho = new String[5];

		cabecalho[0] = "Id";
		cabecalho[1] = "Nome da Despesa";
		cabecalho[2] = "Referencia";
		cabecalho[3] = "Vencimento";
		cabecalho[4] = "Valor";

		model = new TableModelDespesasApto(dados, cabecalho);

		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(418);
		table.getColumnModel().getColumn(2).setPreferredWidth(121);
		table.getColumnModel().getColumn(3).setPreferredWidth(121);
		table.getColumnModel().getColumn(4).setPreferredWidth(121);

		txtTotal = new JTextField();
		txtTotal.setBounds(559, 610, 86, 28);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEditable(false);

		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(503, 613, 46, 14);
		contentPane.add(lblTotal);

		btnSelecionarTudo = new JButton("Selecionar\r\n Tudo");
		btnSelecionarTudo.setBounds(58, 610, 165, 28);
		contentPane.add(btnSelecionarTudo);

		TableClickDespesaApto tCDApto = new TableClickDespesaApto(table, model, txtDespesa, txtRef, txtVencimento,
				txtValor, idDespesa, btnAtualizarDespesa);

		separator_1 = new JSeparator();
		separator_1.setBounds(10, 115, 671, 14);
		contentPane.add(separator_1);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(556, 650, 89, 28);
		contentPane.add(btnVoltar);
		
		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(TelaDespesaApartamento.class.getResource("/resources/back.jpg")));
		lblBack.setBounds(1, 0, 706, 721);
		contentPane.add(lblBack);
		// btnPesquisar.addActionListener(bDAController);

		btnAtualizarDespesa.addActionListener(this);
		btnExcluirSeleo.addActionListener(this);
		btnGravarDespesa.addActionListener(this);
		btnSelecionarTudo.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnVoltar.addActionListener(this);
		txtNumApto.addActionListener(this);

		table.addMouseListener(tCDApto);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnPesquisar || e.getSource() == txtNumApto) {

			try {
				preencheTable();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == btnAtualizarDespesa) {
			atualizarDespesa();

		} else if (e.getSource() == btnGravarDespesa) {
			gravarDespesa();

		} else if (e.getSource() == btnExcluirSeleo) {
			excluirDespesa();

		} else if (e.getSource() == btnSelecionarTudo) {
			selecionaTudo();
		} else if (e.getSource() == btnVoltar) {
			TelaMenu menu = new TelaMenu();
			menu.setVisible(true);
			this.dispose();
		}

	}

	private void selecionaTudo() {
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionInterval(0, table.getRowCount());
	}

	private void atualizarDespesa() {
		Despesas d = new Despesas();
		DespesasAptoController dAController = new DespesasAptoController();
		d.setDespesa(txtDespesa.getText());
		d.setDtReferencia(converteDataView(txtRef.getText()));
		d.setDtVencimento(converteDataView(txtVencimento.getText()));
		d.setValor(Float.parseFloat(txtValor.getText()));
		d.setId(Integer.parseInt(idDespesa.getText()));
		dAController.atualizarDespesa(d);

		try {
			preencheTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void gravarDespesa() {
		Despesas d = new Despesas();
		DespesasAptoController dAController = new DespesasAptoController();
		d.setDespesa(txtDespesa.getText());
		d.setDtReferencia(converteDataView(txtRef.getText()));
		d.setDtVencimento(converteDataView(txtVencimento.getText()));
		d.setValor(Float.parseFloat(txtValor.getText()));
		dAController.gravarDespesa(d, idApto);
		try {
			preencheTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String converteDataView(String data) {
		String aux;
		aux = data.substring(6, 10);
		aux = aux + "." + data.substring(3, 5);
		aux = aux + "." + data.substring(0, 2);
		return aux;
	}

	private String converteDataDB(String data) {
		String aux;
		aux = data.substring(8, 10);
		aux = aux + "/" + data.substring(5, 7);
		aux = aux + "/" + data.substring(0, 4);
		return aux;

	}

	private void excluirDespesa() {
		List<Integer> d = new ArrayList<Integer>();

		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.isCellSelected(i, 0)) {
				d.add(Integer.parseInt(table.getValueAt(i, 0).toString()));
			}
		}

		DespesasAptoController tDAController = new DespesasAptoController();

		tDAController.excluirDespesa(d);

		try {
			preencheTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void preencheTable() throws SQLException {
		Apartamentos a = new Apartamentos();
		DespesasAptoController dAController = new DespesasAptoController();
		IDespesasApartamentoDao dADao = new DespesasApartamentoDao();
		List<Despesas> listaDespesas;

		a.setNumero(Integer.parseInt(txtNumApto.getText()));
		idApto = dAController.pesquisarApto(a);
		listaDespesas = dADao.consultaDespesasApartamento(idApto);

		model.setRowCount(0);

		if (idApto > 0) {
			Double valorTotal = 0.0;
			for (Despesas d : listaDespesas) {
				Object[] linha = new Object[5];
				linha[1] = d.getDespesa();
				linha[2] = converteDataDB(d.getDtReferencia());
				linha[3] = converteDataDB(d.getDtVencimento());
				linha[4] = d.getValor();
				linha[0] = d.getId();
				valorTotal += d.getValor();
				model.addRow(linha);
			}
			txtTotal.setText(valorTotal.toString());
		} else {
			JOptionPane.showMessageDialog(null, "ERRO", "Apartamento n�o encontrado", JOptionPane.ERROR_MESSAGE);
		}

	}
}
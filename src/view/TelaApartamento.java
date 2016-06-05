package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.BotaoApartamentosController;
import controller.RadioApartamentosController;

public class TelaApartamento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNum;
	private JTextField txtQuartos;
	private JTextField txtNome;
	private JTextField txtTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaApartamento frame = new TelaApartamento();
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
	public TelaApartamento() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNumeroDoApartamento = new JLabel("Numero do Apartamento:");
		lblNumeroDoApartamento.setBounds(109, 64, 126, 14);
		contentPane.add(lblNumeroDoApartamento);

		txtNum = new JTextField();
		txtNum.setBounds(259, 61, 86, 20);
		contentPane.add(txtNum);
		txtNum.setColumns(10);

		JButton btnPesqApto = new JButton("Pesquisar");
		btnPesqApto.setBounds(377, 60, 89, 23);
		contentPane.add(btnPesqApto);

		txtQuartos = new JTextField();
		txtQuartos.setBounds(259, 111, 86, 20);
		contentPane.add(txtQuartos);
		txtQuartos.setColumns(10);

		JLabel lblQuantidadeDeQuartos = new JLabel("Quantidade de Quartos:");
		lblQuantidadeDeQuartos.setBounds(109, 114, 126, 14);
		contentPane.add(lblQuantidadeDeQuartos);

		JLabel lblTipoDeOcupao = new JLabel("Tipo de Ocupa\u00E7\u00E3o:");
		lblTipoDeOcupao.setBounds(109, 169, 126, 14);
		contentPane.add(lblTipoDeOcupao);

		JRadioButton rdbtnInquilino = new JRadioButton("Inquilino");
		rdbtnInquilino.setBounds(109, 201, 109, 23);
		contentPane.add(rdbtnInquilino);

		JRadioButton rdbtnProprietario = new JRadioButton("Proprietario");
		rdbtnProprietario.setBounds(249, 201, 109, 23);
		contentPane.add(rdbtnProprietario);

		JRadioButton rdbtnVazio = new JRadioButton("Vazio");
		rdbtnVazio.setBounds(377, 201, 109, 23);
		contentPane.add(rdbtnVazio);

		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		separator.setBounds(10, 288, 654, 14);
		contentPane.add(separator);

		JLabel lblMorador = new JLabel("Morador");
		lblMorador.setBounds(32, 313, 46, 14);
		contentPane.add(lblMorador);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(57, 360, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(109, 357, 248, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(42, 413, 46, 14);
		contentPane.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(109, 410, 193, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		JButton btnAtualizarMorador = new JButton("Atualizar");
		btnAtualizarMorador.setBounds(514, 409, 89, 23);
		contentPane.add(btnAtualizarMorador);

		JButton btnExcluirMorador = new JButton("Excluir");
		btnExcluirMorador.setBounds(514, 441, 89, 23);
		contentPane.add(btnExcluirMorador);

		JButton btnPesquisarMorador = new JButton("Pesquisar");
		btnPesquisarMorador.setBounds(377, 356, 89, 23);
		contentPane.add(btnPesquisarMorador);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(10, 288, 12, 190);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(662, 288, 12, 190);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setToolTipText("");
		separator_3.setBounds(10, 477, 654, 14);
		contentPane.add(separator_3);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(109, 502, 89, 23);
		contentPane.add(btnGravar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(446, 502, 89, 23);
		contentPane.add(btnExcluir);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(288, 502, 89, 23);
		contentPane.add(btnAtualizar);

		JButton btnGravarMorador = new JButton("Gravar");
		btnGravarMorador.setBounds(514, 375, 89, 23);
		contentPane.add(btnGravarMorador);

		RadioApartamentosController rController = new RadioApartamentosController(rdbtnInquilino, rdbtnProprietario,
				rdbtnVazio, txtNome, txtTelefone, btnAtualizarMorador, btnExcluirMorador, btnPesquisarMorador,
				btnGravarMorador);

		rdbtnInquilino.addActionListener(rController);
		rdbtnProprietario.addActionListener(rController);
		rdbtnVazio.addActionListener(rController);

		BotaoApartamentosController apeController = new BotaoApartamentosController(txtNum, txtQuartos, txtNome,
				txtTelefone, btnAtualizar, btnExcluir, btnGravar, btnPesqApto, rController.getSelectedButton(),
				btnGravarMorador, btnExcluirMorador, btnPesquisarMorador, btnAtualizarMorador);

		btnAtualizar.addActionListener(apeController);
		btnExcluir.addActionListener(apeController);
		btnGravar.addActionListener(apeController);
		btnPesqApto.addActionListener(apeController);
		btnAtualizarMorador.addActionListener(apeController);
		btnExcluirMorador.addActionListener(apeController);
		btnGravarMorador.addActionListener(apeController);
		btnPesquisarMorador.addActionListener(apeController);

	}
}

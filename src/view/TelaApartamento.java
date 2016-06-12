package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ApartamentosController;
import model.Apartamentos;
import model.Moradores;

public class TelaApartamento extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;

	private JButton btnAtualizar;
	private JButton btnAtualizarMorador;
	private JButton btnExcluir;
	private JButton btnExcluirMorador;
	private JButton btnGravar;
	private JButton btnGravarMorador;
	private JButton btnPesqApto;
	private JButton btnPesquisarMorador;
	
	private JPanel contentPane;
	
	private JLabel lblMorador;
	private JLabel lblNome;
	private JLabel lblNumeroDoApartamento;
	private JLabel lblQuantidadeDeQuartos;
	private JLabel lblTelefone;
	private JLabel lblTipoDeOcupao;
	private JLabel lblLogo;
	private JLabel lblPagina;
	
	private JRadioButton rdbtnInquilino;
	private JRadioButton rdbtnProprietario;
	private JRadioButton rdbtnVazio;
	
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	
	private JSpinner spinnerNum;
	private JSpinner spinnerQuartos;
	
	private JTextField txtNome;
	private JTextField txtTelefone;

	private String selectedRadio;

	private int idMorador;

	private Apartamentos a = new Apartamentos();

	private Moradores m = new Moradores();

	private ApartamentosController apeController;
	private JSeparator separator_4;

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

	public TelaApartamento() {
		setTitle("Gerenciamento dos Apartamentos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNumeroDoApartamento = new JLabel("Numero do Apartamento:");
		lblNumeroDoApartamento.setBounds(109, 202, 126, 14);
		contentPane.add(lblNumeroDoApartamento);

		btnPesqApto = new JButton("Pesquisar");
		btnPesqApto.setBounds(377, 198, 89, 23);
		contentPane.add(btnPesqApto);

		lblQuantidadeDeQuartos = new JLabel("Quantidade de Quartos:");
		lblQuantidadeDeQuartos.setBounds(109, 244, 126, 14);
		contentPane.add(lblQuantidadeDeQuartos);

		lblTipoDeOcupao = new JLabel("Tipo de Ocupa\u00E7\u00E3o:");
		lblTipoDeOcupao.setBounds(109, 286, 126, 14);
		contentPane.add(lblTipoDeOcupao);

		rdbtnInquilino = new JRadioButton("Inquilino");
		rdbtnInquilino.setSelected(true);
		rdbtnInquilino.setBounds(109, 318, 109, 23);
		contentPane.add(rdbtnInquilino);
		rdbtnInquilino.setSelected(true);
		a.setOcupacao(rdbtnInquilino.getText());

		rdbtnProprietario = new JRadioButton("Proprietario");
		rdbtnProprietario.setBounds(249, 318, 109, 23);
		contentPane.add(rdbtnProprietario);

		rdbtnVazio = new JRadioButton("Vazio");
		rdbtnVazio.setBounds(377, 318, 109, 23);
		contentPane.add(rdbtnVazio);

		separator = new JSeparator();
		separator.setToolTipText("");
		separator.setBounds(10, 377, 654, 14);
		contentPane.add(separator);
		
		lblLogo = new JLabel("CONDOMÌNIO DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(32, 42, 410, 45);
		contentPane.add(lblLogo);
		
		lblPagina = new JLabel("Cadastro de Apartamentos");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(324, 98, 340, 22);
		contentPane.add(lblPagina);

		lblMorador = new JLabel("Morador");
		lblMorador.setBounds(32, 402, 83, 14);
		contentPane.add(lblMorador);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(32, 475, 67, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(110, 472, 248, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(32, 440, 67, 14);
		contentPane.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(109, 437, 249, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		btnAtualizarMorador = new JButton("Atualizar");
		btnAtualizarMorador.setBounds(514, 471, 89, 23);
		contentPane.add(btnAtualizarMorador);

		btnExcluirMorador = new JButton("Excluir");
		btnExcluirMorador.setBounds(514, 505, 89, 23);
		contentPane.add(btnExcluirMorador);

		btnPesquisarMorador = new JButton("Pesquisar");
		btnPesquisarMorador.setBounds(377, 436, 89, 23);
		contentPane.add(btnPesquisarMorador);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(10, 377, 12, 190);
		contentPane.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(662, 377, 12, 190);
		contentPane.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setToolTipText("");
		separator_3.setBounds(10, 566, 654, 14);
		contentPane.add(separator_3);

		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(109, 591, 89, 23);
		contentPane.add(btnGravar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(446, 591, 89, 23);
		contentPane.add(btnExcluir);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(288, 591, 89, 23);
		contentPane.add(btnAtualizar);

		btnGravarMorador = new JButton("Gravar");
		btnGravarMorador.setBounds(514, 436, 89, 23);
		contentPane.add(btnGravarMorador);

		spinnerNum = new JSpinner();
		spinnerNum.setBounds(245, 199, 89, 20);
		contentPane.add(spinnerNum);

		spinnerQuartos = new JSpinner();
		spinnerQuartos.setBounds(245, 241, 89, 20);
		contentPane.add(spinnerQuartos);

		rdbtnInquilino.addItemListener(this);
		rdbtnProprietario.addItemListener(this);
		rdbtnVazio.addItemListener(this);

		apeController = new ApartamentosController(spinnerNum, spinnerQuartos, rdbtnInquilino, rdbtnProprietario,
				rdbtnVazio);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(21, 150, 643, 14);
		contentPane.add(separator_4);

		btnAtualizar.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnGravar.addActionListener(this);
		btnPesqApto.addActionListener(this);
		btnAtualizarMorador.addActionListener(this);
		btnExcluirMorador.addActionListener(this);
		btnGravarMorador.addActionListener(this);
		btnPesquisarMorador.addActionListener(this);
		groupButton();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		a.setNumero(Integer.parseInt(spinnerNum.getValue().toString()));
		a.setQuartos(Integer.parseInt(spinnerQuartos.getValue().toString()));

		if (!a.getOcupacao().equals("Vazio")) {
			m.setNome(txtNome.getText());
			m.setTelefone(txtTelefone.getText());
		} else {
			m.setId(apeController.verificaMorador().getId());
			a.setId_morador(m.getId());
		}

		if (e.getSource() == btnGravar) {
			apeController.gravarApartamento(a);
		} else if (e.getSource() == btnAtualizar) {
			apeController.atualizarApartamento(a);
		} else if (e.getSource() == btnExcluir) {
			apeController.excluirApartamento(a);
		} else if (e.getSource() == btnPesqApto) {
			preencheCamposApartamento(apeController.pesquisarApto(a));
		} else if (e.getSource() == btnAtualizarMorador) {
			apeController.atualizarMorador(m);
		} else if (e.getSource() == btnExcluirMorador) {
			apeController.excluirMorador(m);
		} else if (e.getSource() == btnGravarMorador) {
			apeController.gravarMorador(m);
			apeController.pesquisarMorador(m);
		} else if (e.getSource() == btnPesquisarMorador) {
			m = apeController.pesquisarMorador(m);
			preencheCamposMorador(m);
			a.setId_morador(m.getId());
			System.out.println(idMorador);
		}

	}

	public void preencheCamposMorador(Moradores m) {

		txtNome.setText(m.getNome());
		txtTelefone.setText(m.getTelefone());

	}

	public void preencheCamposApartamento(Apartamentos a) {

		spinnerNum.setValue(Integer.valueOf(a.getNumero()));
		spinnerQuartos.setValue(Integer.valueOf(a.getQuartos()));

		if (!(a.getId() == -1)) {
			selecionaRadio(a.getOcupacao());
		}

		m.setId(a.getId_morador());

		preencheCamposMorador(apeController.pesquisarMorador(m));

	}

	public void selecionaRadio(String o) {
		if (o.equals("Vazio")) {

			rdbtnVazio.setSelected(true);
			a.setOcupacao(rdbtnVazio.getText());

		} else if (o.equals("Inquilino")) {

			rdbtnInquilino.setSelected(true);
			a.setOcupacao(rdbtnInquilino.getText());

		} else {

			rdbtnProprietario.setSelected(true);
			a.setOcupacao(rdbtnProprietario.getText());

		}

	}

	private void groupButton() {
		ButtonGroup bg = new ButtonGroup();

		bg.add(rdbtnInquilino);
		bg.add(rdbtnProprietario);
		bg.add(rdbtnVazio);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getSource() == rdbtnVazio) {
				txtNome.setEditable(false);
				txtTelefone.setEditable(false);
				btnAtualizarMorador.setEnabled(false);
				btnExcluirMorador.setEnabled(false);
				btnPesquisarMorador.setEnabled(false);
				btnGravarMorador.setEnabled(false);

				a.setOcupacao(rdbtnVazio.getText());
				m = apeController.verificaMorador();
			} else {
				txtNome.setEditable(true);
				txtTelefone.setEditable(true);
				btnAtualizarMorador.setEnabled(true);
				btnExcluirMorador.setEnabled(true);
				btnPesquisarMorador.setEnabled(true);
				btnGravarMorador.setEnabled(true);

				if (rdbtnInquilino.isSelected()) {
					a.setOcupacao(rdbtnInquilino.getText());
				} else {
					a.setOcupacao(rdbtnProprietario.getText());
				}
			}
			apeController = new ApartamentosController(a.getOcupacao());
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
		}
	}

	public void limpaCampos() {

		txtNome.setText("");
		spinnerNum.setValue(0);
		spinnerQuartos.setValue(0);
		txtTelefone.setText("");

	}

}

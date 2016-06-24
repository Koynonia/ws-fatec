package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
import javax.swing.ImageIcon;

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
	private JButton btnVoltar;
	
	private JPanel contentPane;
	
	private JLabel lblMorador;
	private JLabel lblNome;
	private JLabel lblNumeroDoApartamento;
	private JLabel lblQuantidadeDeQuartos;
	private JLabel lblTelefone;
	private JLabel lblTipoDeOcupao;
	private JLabel lblLogo;
	private JLabel lblPagina;
	private JLabel lblBack;
	
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
		setResizable(false);
		setTitle("Controle de Apartamentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 675);
		setBounds(100, 100, 680, 686);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNumeroDoApartamento = new JLabel("Numero do Apartamento:");
		lblNumeroDoApartamento.setBounds(49, 179, 159, 14);
		contentPane.add(lblNumeroDoApartamento);

		btnPesqApto = new JButton("Pesquisar");
		btnPesqApto.setBounds(322, 172, 109, 28);
		contentPane.add(btnPesqApto);

		lblQuantidadeDeQuartos = new JLabel("Quantidade de Quartos:");
		lblQuantidadeDeQuartos.setBounds(49, 221, 149, 14);
		contentPane.add(lblQuantidadeDeQuartos);

		lblTipoDeOcupao = new JLabel("Tipo de Ocupa\u00E7\u00E3o:");
		lblTipoDeOcupao.setBounds(32, 259, 126, 14);
		contentPane.add(lblTipoDeOcupao);

		rdbtnInquilino = new JRadioButton("Inquilino");
		rdbtnInquilino.setSelected(true);
		rdbtnInquilino.setBounds(32, 291, 109, 28);
		contentPane.add(rdbtnInquilino);
		rdbtnInquilino.setSelected(true);
		a.setOcupacao(rdbtnInquilino.getText());

		rdbtnProprietario = new JRadioButton("Proprietario");
		rdbtnProprietario.setBounds(172, 291, 109, 28);
		contentPane.add(rdbtnProprietario);

		rdbtnVazio = new JRadioButton("Vazio");
		rdbtnVazio.setBounds(300, 291, 109, 28);
		contentPane.add(rdbtnVazio);

		separator = new JSeparator();
		separator.setToolTipText("");
		separator.setBounds(10, 345, 654, 14);
		contentPane.add(separator);
		
		lblLogo = new JLabel("CONDOMINIO DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(32, 42, 437, 45);
		contentPane.add(lblLogo);
		
		lblPagina = new JLabel("Controle de Apartamentos");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(435, 128, 222, 22);
		contentPane.add(lblPagina);

		lblMorador = new JLabel("Morador");
		lblMorador.setBounds(32, 370, 83, 14);
		contentPane.add(lblMorador);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(49, 447, 41, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(110, 440, 248, 28);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(33, 412, 67, 14);
		contentPane.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(109, 405, 249, 28);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		btnAtualizarMorador = new JButton("Atualizar");
		btnAtualizarMorador.setBounds(514, 439, 109, 28);
		contentPane.add(btnAtualizarMorador);

		btnExcluirMorador = new JButton("Excluir");
		btnExcluirMorador.setBounds(514, 473, 109, 28);
		contentPane.add(btnExcluirMorador);

		btnPesquisarMorador = new JButton("Pesquisar");
		btnPesquisarMorador.setBounds(377, 404, 109, 28);
		contentPane.add(btnPesquisarMorador);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(10, 345, 12, 190);
		contentPane.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(662, 345, 12, 190);
		contentPane.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setToolTipText("");
		separator_3.setBounds(10, 534, 654, 14);
		contentPane.add(separator_3);

		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(110, 559, 109, 28);
		contentPane.add(btnGravar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(427, 559, 109, 28);
		contentPane.add(btnExcluir);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(269, 559, 109, 28);
		contentPane.add(btnAtualizar);

		btnGravarMorador = new JButton("Gravar");
		btnGravarMorador.setBounds(514, 404, 109, 28);
		contentPane.add(btnGravarMorador);

		spinnerNum = new JSpinner();
		spinnerNum.setBounds(203, 172, 89, 28);
		contentPane.add(spinnerNum);

		spinnerQuartos = new JSpinner();
		spinnerQuartos.setBounds(203, 214, 89, 28);
		contentPane.add(spinnerQuartos);

		rdbtnInquilino.addItemListener(this);
		rdbtnProprietario.addItemListener(this);
		rdbtnVazio.addItemListener(this);

		apeController = new ApartamentosController(spinnerNum, spinnerQuartos, rdbtnInquilino, rdbtnProprietario,
				rdbtnVazio);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(21, 150, 643, 14);
		contentPane.add(separator_4);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(555, 613, 109, 28);
		contentPane.add(btnVoltar);
		
		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(TelaApartamento.class.getResource("/resources/back.jpg")));
		lblBack.setBounds(0, -16, 674, 673);
		contentPane.add(lblBack);

		btnAtualizar.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnGravar.addActionListener(this);
		btnPesqApto.addActionListener(this);
		btnAtualizarMorador.addActionListener(this);
		btnExcluirMorador.addActionListener(this);
		btnGravarMorador.addActionListener(this);
		btnPesquisarMorador.addActionListener(this);
		btnVoltar.addActionListener(this);
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
			apeController.pesquisarMoradorPorTelefone(m);
		} else if (e.getSource() == btnPesquisarMorador) {
			m = apeController.pesquisarMoradorPorTelefone(m);
			preencheCamposMorador(m);
			a.setId_morador(m.getId());
			System.out.println(idMorador);
		}else if (e.getSource() == btnVoltar){
			TelaMenu menu = new TelaMenu();
			menu.setVisible(true);
			this.dispose();
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

		preencheCamposMorador(apeController.pesquisarMoradorPorId(m));

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


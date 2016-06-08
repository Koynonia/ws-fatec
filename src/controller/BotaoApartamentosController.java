package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import model.Apartamentos;
import model.Moradores;
import persistence.ApartamentosDao;
import persistence.IApartamentosDao;
import persistence.IMoradoresDao;
import persistence.MoradoresDao;

public class BotaoApartamentosController implements ActionListener {

	JSpinner spinnerNum, spinnerQuartos;
	JTextField txtNome, txtTelefone;
	JButton btnAtualizar, btnExcluir, btnGravar, btnPesqApto, btnGravarMorador, btnExcluirMorador, btnPesquisarMorador,
			btnAtualizarMorador;
	String selectedRadio;
	int idMorador;

	public BotaoApartamentosController(JSpinner spinnerNum, JSpinner spinnerQuartos, JTextField txtNome,
			JTextField txtTelefone, JButton btnAtualizar, JButton btnExcluir, JButton btnGravar, JButton btnPesqApto,
			String selectedRadio, JButton btnGravarMorador, JButton btnExcluirMorador, JButton btnPesquisarMorador,
			JButton btnAtualizarMorador) {
		this.txtNome = txtNome;
		this.spinnerNum = spinnerNum;
		this.spinnerQuartos = spinnerQuartos;
		this.txtTelefone = txtTelefone;
		this.btnAtualizar = btnAtualizar;
		this.btnExcluir = btnExcluir;
		this.btnGravar = btnGravar;
		this.btnPesqApto = btnPesqApto;
		this.selectedRadio = selectedRadio;
		this.btnAtualizarMorador = btnAtualizarMorador;
		this.btnExcluirMorador = btnExcluirMorador;
		this.btnGravarMorador = btnGravarMorador;
		this.btnPesquisarMorador = btnPesquisarMorador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Apartamentos a = new Apartamentos();
		Moradores m = new Moradores();
		RadioApartamentosController rAController = new RadioApartamentosController();

		selectedRadio = rAController.getSelectedButton();

		a.setNumero(Integer.parseInt(spinnerNum.getValue().toString()));
		a.setOcupacao(selectedRadio);
		a.setQuartos(Integer.parseInt(spinnerQuartos.getValue().toString()));

		if (!selectedRadio.equals("Vazio")) {
			m.setNome(txtNome.getText());
			m.setTelefone(txtTelefone.getText());
			// m.setId(idMorador);
		} else {
			m.setId(verificaMorador());

		}
		a.setId_morador(idMorador);

		if (e.getSource() == btnGravar) {
			gravarApartamento(a);
		} else if (e.getSource() == btnAtualizar) {
			atualizarApartamento(a);
		} else if (e.getSource() == btnExcluir) {
			excluirApartamento(a);
		} else if (e.getSource() == btnPesqApto) {
			pesquisarApto(a);
		} else if (e.getSource() == btnAtualizarMorador) {
			atualizarMorador(m);
		} else if (e.getSource() == btnExcluirMorador) {
			excluirMorador(m);
		} else if (e.getSource() == btnGravarMorador) {
			gravarMorador(m);
			pesquisarMorador(m);
		} else if (e.getSource() == btnPesquisarMorador) {
			pesquisarMorador(m);
			System.out.println(idMorador);
		}

	}

	private int verificaMorador() {
		IMoradoresDao mDao = new MoradoresDao();
		Moradores m = new Moradores();
		Moradores m1 = new Moradores();
		m.setNome("Moderador");
		m.setTelefone("0000");

		try {
			m1 = mDao.consultaMorador(m);
			if (m1.getTelefone() != null) {
				return m1.getId();
			} else {
				mDao.insereMorador(m);
				return mDao.consultaMorador(m).getId();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return 0;
		}

	}

	private void pesquisarApto(Apartamentos a) {
		IApartamentosDao aDao = new ApartamentosDao();
		Apartamentos apartamento = new Apartamentos();
		try {
			apartamento = aDao.consultaApartamento(a);
			preencheCamposApartamento(apartamento);
			JOptionPane.showMessageDialog(null, "Apartamento encontrado", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void pesquisarMorador(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();
		Moradores morador = new Moradores();

		try {
			morador = mDao.consultaMorador(m);
			preencheCamposMorador(morador);
			idMorador = morador.getId();
			// trocar isso aqui
			JOptionPane.showMessageDialog(null, "Morador encontrado", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void preencheCamposApartamento(Apartamentos a) {
		RadioApartamentosController rAController = new RadioApartamentosController();
		Moradores m = new Moradores();

		spinnerNum.setValue(Integer.valueOf(a.getNumero()));
		spinnerQuartos.setValue(Integer.valueOf(a.getQuartos()));

	    rAController.selecionaRadio(a.getOcupacao());
		m.setId(a.getId_morador());

		pesquisarMorador(m);

	}

	private void preencheCamposMorador(Moradores m) {
		txtNome.setText(m.getNome());
		txtTelefone.setText(m.getTelefone());
	}

	private void excluirApartamento(Apartamentos a) {
		IApartamentosDao aDao = new ApartamentosDao();
		try {
			aDao.excluiApartamento(a);
			JOptionPane.showMessageDialog(null, "Apartamento excluido", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void excluirMorador(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();
		try {
			mDao.excluiMorador(m);
			JOptionPane.showMessageDialog(null, "Morador excluido", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void atualizarApartamento(Apartamentos a) {
		System.out.println("id morador:" + a.getId_morador());
		IApartamentosDao aDao = new ApartamentosDao();
		try {
			aDao.atualizaApartamento(a);
			JOptionPane.showMessageDialog(null, "Apartamento atualizado", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void atualizarMorador(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();
		try {
			mDao.atualizaMorador(m);
			JOptionPane.showMessageDialog(null, "Morador atualizado", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void limpaCampos() {
		txtNome.setText("");
		spinnerNum.setValue(0);
		spinnerQuartos.setValue(0);
		txtTelefone.setText("");
		RadioApartamentosController rAController = new RadioApartamentosController();
		rAController.setSelectedButton("Vazio");
	}

	private void gravarApartamento(Apartamentos a) {

		IApartamentosDao aDao = new ApartamentosDao();
		try {
			aDao.insereApartamento(a);
			JOptionPane.showMessageDialog(null, "Apartamento inserido", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void gravarMorador(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();
		try {
			mDao.insereMorador(m);
			JOptionPane.showMessageDialog(null, "Morador inserido", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
}

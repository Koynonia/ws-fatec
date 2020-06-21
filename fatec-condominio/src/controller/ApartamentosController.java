package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import model.Apartamentos;
import model.Moradores;

import persistence.ApartamentosDao;
import persistence.IApartamentosDao;
import persistence.IMoradoresDao;
import persistence.MoradoresDao;

public class ApartamentosController {

	private JSpinner spinnerNum;
	private JSpinner spinnerQuartos;

	private JRadioButton rdbtnInquilino;
	private JRadioButton rdbtnProprietario;
	private JRadioButton rdbtnVazio;
	
	private Apartamentos a = new Apartamentos();

	public ApartamentosController(JSpinner spinnerNum, 
								  JSpinner spinnerQuartos, 
								  JRadioButton rdbtnInquilino,
								  JRadioButton rdbtnProprietario,
								  JRadioButton rdbtnVazio) {
		this.rdbtnInquilino = rdbtnInquilino;
		this.rdbtnProprietario = rdbtnProprietario;
		this.rdbtnVazio = rdbtnVazio;
		this.spinnerNum = spinnerNum;
		this.spinnerQuartos = spinnerQuartos;
	}

	public ApartamentosController(String selectedRadio) {}

	public Moradores verificaMorador() {
		IMoradoresDao mDao = new MoradoresDao();

		Moradores m = new Moradores();
		Moradores m1 = new Moradores();

		m.setNome("Moderador");
		m.setTelefone("0000");

		try {

			m1 = mDao.consultaMoradorPorTelefone(m);

			if (m1.getTelefone() != null) {
				return m1;
			} else {
				mDao.insereMorador(m);
				return mDao.consultaMoradorPorTelefone(m);
			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"ERRO", JOptionPane.ERROR_MESSAGE);
			return null;

		}

	}

	public Apartamentos pesquisarApto(Apartamentos a) {
		IApartamentosDao aDao = new ApartamentosDao();
		Apartamentos apartamento = new Apartamentos();

		try {

			apartamento = aDao.consultaApartamento(a);
			if (apartamento.getOcupacao() == null) {
				JOptionPane.showMessageDialog(null, 
						"Apartamento n�o encontrado", "ERRO",
						JOptionPane.INFORMATION_MESSAGE);
				apartamento.setId(-1);
				return apartamento;
			} else {
				return apartamento;
			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
			apartamento.setId(-1);
			return apartamento;
		}

	}

	public Moradores pesquisarMoradorPorId(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();
		Moradores morador = new Moradores();

		try {

			morador = mDao.consultaMoradorPorId(m);

			return morador;
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		

	}
	
	public Moradores pesquisarMoradorPorTelefone(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();
		Moradores morador = new Moradores();

		try {

			morador = mDao.consultaMoradorPorTelefone(m);

			return morador;
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		

	}


	

	public void excluirApartamento(Apartamentos a) {
		IApartamentosDao aDao = new ApartamentosDao();

		try {

			aDao.excluiApartamento(a);
			JOptionPane.showMessageDialog(null, "Apartamento excluido",
					"SUCESSO", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERRO", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void excluirMorador(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();

		try {

			mDao.excluiMorador(m);
			JOptionPane.showMessageDialog(null, "Morador excluido",
					"SUCESSO", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"ERRO", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void atualizarApartamento(Apartamentos a) {
		IApartamentosDao aDao = new ApartamentosDao();

		try {
			if (a.getOcupacao().equals("Vazio")) {
				a.setId_morador(verificaMorador().getId());
			}
			aDao.atualizaApartamento(a);
			JOptionPane.showMessageDialog(null, "Apartamento atualizado",
					"SUCESSO", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"ERRO", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void atualizarMorador(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();
		try {

			mDao.atualizaMorador(m);
			JOptionPane.showMessageDialog(null, "Morador atualizado",
					"SUCESSO", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	

	public void gravarApartamento(Apartamentos a) {

		IApartamentosDao aDao = new ApartamentosDao();

		try {

			aDao.insereApartamento(a);
			JOptionPane.showMessageDialog(null, "Apartamento inserido",
					"SUCESSO", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void gravarMorador(Moradores m) {
		IMoradoresDao mDao = new MoradoresDao();

		try {

			mDao.insereMorador(m);
			JOptionPane.showMessageDialog(null, "Morador inserido",
					"SUCESSO", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"ERRO", JOptionPane.ERROR_MESSAGE);

		}
	}

	

}

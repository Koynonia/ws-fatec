package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import model.Apartamentos;
import model.Despesas;
import persistence.ApartamentosDao;
import persistence.DespesasApartamentoDao;
import persistence.IApartamentosDao;
import persistence.IDespesasApartamentoDao;

public class DespesasAptoController   {


	public void gravarDespesa(Despesas despesa, int idApartamento) {
		IDespesasApartamentoDao dADao = new DespesasApartamentoDao();
		try {
			dADao.insereDespesaApartamento(despesa, idApartamento);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	//ap pesquisar apto deve carregar as despesas na table
	public int pesquisarApto(Apartamentos a) {
		IApartamentosDao aDao = new ApartamentosDao();
		Apartamentos apartamento = new Apartamentos();
		try {
			apartamento = aDao.consultaApartamento(a);
			return apartamento.getId();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}

	public void atualizarDespesa(Despesas d) {
		IDespesasApartamentoDao dADao = new DespesasApartamentoDao();
		try {
			dADao.atualizaDespesaApartamento(d);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void excluirDespesa(List<Integer> d) {
		IDespesasApartamentoDao dADao = new DespesasApartamentoDao();
		try {
			dADao.excluiDespesaApartamento(d);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}







}

	

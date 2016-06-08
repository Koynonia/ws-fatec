package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Despesas;

public interface IDespesasApartamentoDao {

	public void insereDespesaApartamento(Despesas despesa, int idApartamento) throws SQLException;

	public void atualizaDespesaApartamento(Despesas despesa) throws SQLException;

	public void excluiDespesaApartamento(Despesas despesa) throws SQLException;

	public List<Despesas> consultaDespesasApartamento(int numApto) throws SQLException;

}

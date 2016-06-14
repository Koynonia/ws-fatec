package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Despesas;

public interface IDespesasCondominioDao {

	public void insereDespesa(Despesas despesa) throws SQLException;

	public void atualizaDespesa(Despesas despesa) throws SQLException;

	public void excluiDespesa(Despesas despesa) throws SQLException;

	public List<Despesas> consultaDespesas() throws SQLException;
}
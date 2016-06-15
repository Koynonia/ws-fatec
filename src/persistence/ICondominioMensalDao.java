package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Apartamentos;
import model.Condominio;

public interface ICondominioMensalDao {

	public void insereDespesa(Condominio despesa) throws SQLException;

	public void atualizaDespesa(Condominio despesa) throws SQLException;

	public void excluiDespesa(Condominio despesa) throws SQLException;

	public List<Condominio> consultaCondominio() throws SQLException;
	
	public List<Condominio> consultaDespesasCond() throws SQLException;
	
	public List<Condominio> consultaDespesasApto() throws SQLException;
	
	public List<Apartamentos> consultaAptos() throws SQLException;
}
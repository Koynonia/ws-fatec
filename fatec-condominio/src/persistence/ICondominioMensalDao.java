package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Apartamentos;
import model.Condominio;
import model.Despesas;
import model.Moradores;

public interface ICondominioMensalDao {

	public void insereCondominio(Condominio despesa) throws SQLException;

	public void atualizaCondominio(Condominio despesa) throws SQLException;

	public void excluiCondominio(Condominio despesa) throws SQLException;

	public List<Condominio> consultaCondominioMensal() throws SQLException;

	public List<Moradores> consultaMoradores() throws SQLException;

	public List<Apartamentos> consultaAptos() throws SQLException;

	public List<Despesas> despesasCond() throws SQLException;

	public List<Despesas> despesasApto() throws SQLException;
}
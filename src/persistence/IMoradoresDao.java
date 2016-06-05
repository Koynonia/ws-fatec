package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Moradores;

public interface IMoradoresDao {

	public void insereMorador(Moradores morador) throws SQLException;

	public void atualizaMorador(Moradores morador) throws SQLException;

	public void excluiMorador(Moradores morador) throws SQLException;

	public List<Moradores> consultaMoradores() throws SQLException;

	public Moradores consultaMorador(Moradores morador) throws SQLException;
}

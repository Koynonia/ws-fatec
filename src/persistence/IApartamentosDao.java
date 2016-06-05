package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Apartamentos;
import model.Moradores;

public interface IApartamentosDao {
	
	public void insereApartamento(Apartamentos apartamento) throws SQLException;
	
	public void atualizaApartamento(Apartamentos apartamento) throws SQLException;
	
	public void excluiApartamento(Apartamentos apartamento) throws SQLException;
	
	public List<Apartamentos> consultaApartamentos() throws SQLException;
	
	public Apartamentos consultaApartamento(Apartamentos apartamento) throws SQLException;

}

/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Chale;

public interface ChaleDAO {

	public void adicionarChale(Chale chale) throws SQLException;
	public void alterarChale(Chale chale) throws SQLException;
	public void apagarChale(Chale chale) throws SQLException;
	public Chale consultarChale(Chale chale) throws SQLException;
	public List<Chale> todosChales() throws SQLException;
}

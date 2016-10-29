/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Internet;

public interface InternetDAO {
	
	public void adicionar(Internet internet) throws SQLException;
	public void alterar(Internet internet) throws SQLException;
	public void excluir(Internet internet) throws SQLException;
	public List<Internet> todos() throws SQLException;
	
}
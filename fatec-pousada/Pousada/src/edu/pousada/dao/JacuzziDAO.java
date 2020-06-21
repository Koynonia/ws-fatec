/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Jacuzzi;

public interface JacuzziDAO {
	
	public void adicionar(Jacuzzi j) throws SQLException;
	public void alterar(Jacuzzi j) throws SQLException;
	public void excluir(Jacuzzi j) throws SQLException;
	public List<Jacuzzi> todos() throws SQLException;

}
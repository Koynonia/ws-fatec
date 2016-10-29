/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Spa;

public interface SpaDAO {
	
	public void adicionar(Spa s) throws SQLException;
	public void alterar(Spa s) throws SQLException;
	public void excluir(Spa s) throws SQLException;
	public Spa consultar(Spa s) throws SQLException;
	public List<Spa> todos() throws SQLException;
	
}
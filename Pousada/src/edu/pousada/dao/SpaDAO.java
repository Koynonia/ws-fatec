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
	
	public void adicionar(Spa spa) throws SQLException;
	public void alterar(Spa spa) throws SQLException;
	public void excluir(Spa spa) throws SQLException;
	public Spa consultar(Spa spa) throws SQLException;
	public List<Spa> todos() throws SQLException;
	
}

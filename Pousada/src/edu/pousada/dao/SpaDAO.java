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
	
	public void adicionaSpa(Spa spa) throws SQLException;
	public void alteraSpa(Spa spa) throws SQLException;
	public void excluiSpa(Spa spa) throws SQLException;
	public Spa consultaSpa(Spa spa) throws SQLException;
	public List<Spa> listaSpa() throws SQLException;
	
}

/**
 * @author Guilherme Jatobá
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;

import edu.pousada.entity.Frigobar;

public interface FrigobarDAO {
	
	public void adicionarFrigobar(Frigobar frigobar) throws SQLException;
	public void alterarFrigobar(Frigobar frigobar) throws SQLException;
	public void excluirFrigobar(Frigobar frigobar) throws SQLException;
	public Frigobar consultaFrigobar(Frigobar frigobar) throws SQLException;
	
}

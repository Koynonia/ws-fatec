/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
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

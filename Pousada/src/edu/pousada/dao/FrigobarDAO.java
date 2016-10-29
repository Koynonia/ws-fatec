/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Frigobar;

public interface FrigobarDAO {
	
	public void adicionar(Frigobar frigobar) throws SQLException;
	public void alterar(Frigobar frigobar) throws SQLException;
	public void excluir(Frigobar frigobar) throws SQLException;
	public Frigobar consultar(Frigobar frigobar) throws SQLException;
	public List<Frigobar> todos() throws SQLException;
	
}
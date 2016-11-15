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
	
	public void adicionar(Frigobar obj) throws SQLException;
	public void alterar(Frigobar obj) throws SQLException;
	public void excluir(Frigobar obj) throws SQLException;
	public Frigobar consultar(Frigobar obj) throws SQLException;
	public List<Frigobar> todos() throws SQLException;
}
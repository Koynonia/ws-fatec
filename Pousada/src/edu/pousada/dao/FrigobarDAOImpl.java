/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Frigobar;

public class FrigobarDAOImpl implements FrigobarDAO{

		
	private Connection con = DBUtil.getInstance().getConnection();

	@Override
	public void adicionar(Frigobar f) throws SQLException {
	}

	@Override
	public void alterar(Frigobar f) throws SQLException {
		
		
	}

	@Override
	public void excluir(Frigobar f) throws SQLException {
		
		
	}

	@Override
	public Frigobar consultar(Frigobar f) throws SQLException {
		
		return null;
	}

	@Override
	public List<Frigobar> todos() throws SQLException {
		
		return null;
	}
}
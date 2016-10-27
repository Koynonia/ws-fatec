/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.SQLException;

import edu.pousada.entity.Frigobar;

public class FrigobarDAOImpl implements FrigobarDAO{

		
	private Connection con = DBUtil.getInstance().getConnection();

	@Override
	public void adicionarFrigobar(Frigobar frigobar) throws SQLException {
	}

	@Override
	public void alterarFrigobar(Frigobar frigobar) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirFrigobar(Frigobar frigobar) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Frigobar consultaFrigobar(Frigobar frigobar) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Internet;

public class InternetDAOImpl implements InternetDAO{

	private Connection con = DBUtil.getInstance().getConnection();
	
	/**
	 * CREATE TABLE internet(
	 *  id INT AUTO_INCREMENT PRIMARY KEY,
	 * dtReserva DATE NOT NULL,
	 * hrReserva DATE NOT NULL,
	 * valor DECIMAL(7,2) NOT NULL
	 * );
	 */

	@Override
	public void adicionar(Internet i) throws SQLException {
		
		String sql = "INSERT INTO internet VALUES (NULL,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( i.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( i.getHrReserva().getTime() ));
		ps.setFloat(3, i.getValor());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Internet i) throws SQLException {
		
		String sql = "UPDATE lanchonete SET "
				+ "dtReserva = ?, "
				+ "hrReserva = ?, "
				+ "valor = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( i.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( i.getHrReserva().getTime() ));
		ps.setFloat(3, i.getValor());
		ps.setInt(4, i.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Internet i) throws SQLException {
		
		String sql = "DELETE FROM internet WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, i.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public List<Internet> todos() throws SQLException {
		
		String sql = "SELECT * FROM internet";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Internet> listaInternet = new ArrayList<Internet>();
		while(rs.next()){
			Internet i = new Internet();
			i.setId(rs.getInt("id"));
			i.setDtReserva(rs.getDate("dtReserva"));
			i.setHrReserva(rs.getDate("hrReserva"));
			i.setValor(rs.getFloat("valor"));
			listaInternet.add(i);
		}
		rs.close();
		ps.close();
		
		return listaInternet;
	}
}
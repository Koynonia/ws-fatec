/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.Date;
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

	public void adicionar(Internet internet) throws SQLException {
		String sql = "INSERT INTO internet VALUES (NULL,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( internet.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( internet.getHrReserva().getTime() ));
		ps.setFloat(3, internet.getValor());
		ps.execute();
		ps.close();
	}

	public void alterar(Internet internet) throws SQLException {
		String sql = "UPDATE lanchonete SET dtReserva = ?, hrReserva = ?, valor = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( internet.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( internet.getHrReserva().getTime() ));
		ps.setFloat(3, internet.getValor());
		ps.setInt(4, internet.getId());
		ps.execute();
		ps.close();
	}

	public void excluir(Internet internet) throws SQLException {
		String sql = "DELETE FROM internet WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, internet.getId());
		ps.execute();
		ps.close();
	}

	public List<Internet> todos() throws SQLException {
		List<Internet> listaInternet = new ArrayList<Internet>();
		String sql = "SELECT * FROM internet";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Internet internet = new Internet();
			internet.setId(rs.getInt("id"));
			internet.setDtReserva(rs.getDate("dtReserva"));
			internet.setHrReserva(rs.getDate("hrReserva"));
			internet.setValor(rs.getFloat("valor"));
			listaInternet.add(internet);
		}
		rs.close();
		ps.close();
		
		return listaInternet;
	}
}
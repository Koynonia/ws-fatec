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

import edu.pousada.entity.Spa;

public class SpaDAOImpl implements SpaDAO{

	private Connection con = DBUtil.getInstance().getConnection();

	/**
	 * CREATE TABLE spa (
	 * id INT PRIMARY KEY,
	 * servico VARCHAR(50) NOT NULL,
	 * dtReserva DATE NOT NULL,
	 * hrReserva DATE NOT NULL, 
	 * valor FLOAT NOT NULL
	 * );
	 */

	public void adicionar(Spa spa) throws SQLException {
		String sql = "INSERT INTO spa VALUES (NULL,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(2, spa.getServico());
		ps.setDate(3, new java.sql.Date( spa.getDtReserva().getTime() ));
		ps.setDate(4, new java.sql.Date( spa.getHrReserva().getTime() ));
		ps.setFloat(5, spa.getValor());
		ps.execute();
		ps.close();
	}

	public void alterar(Spa spa) throws SQLException {
		String sql =  "UPDATE spa SET servico = ?, dtReserva = ?, hrReserva = ?, valor = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, spa.getServico());
		ps.setDate(2, new java.sql.Date( spa.getDtReserva().getTime() ));
		ps.setDate(3, new java.sql.Date( spa.getHrReserva().getTime() ));
		ps.setFloat(4, spa.getValor());
		ps.setInt(5, spa.getId());			
		ps.execute();
		ps.close();		
	}

	public void excluir(Spa spa) throws SQLException {
		String sql = "DELETE spa WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, spa.getId());
		ps.execute();
		ps.close();
	}

	public Spa consultar(Spa spa) throws SQLException {
		String sql = "SELECT * FROM spa WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, spa.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			spa.setId(rs.getInt("id"));
			spa.setServico(rs.getString("servico"));
			spa.setDtReserva(rs.getDate("dtReserva"));
			spa.setHrReserva(rs.getDate("hrReserva"));
			spa.setValor(rs.getFloat("valor"));
		}
		rs.close();
		ps.close();
		return spa;
	}

	public List<Spa> todos() throws SQLException {
		List<Spa> listaSpa = new ArrayList<Spa>();
		String sql = "SELECT * FROM spa";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Spa spa = new Spa();
			spa.setId(rs.getInt("id"));
			spa.setServico(rs.getString("servico"));
			spa.setDtReserva(rs.getDate("dtReserva"));
			spa.setHrReserva(rs.getDate("hrReserva"));
			spa.setValor(rs.getFloat("valor"));
			listaSpa.add(spa);
		}
		rs.close();
		ps.close();
		return listaSpa;
	}
}
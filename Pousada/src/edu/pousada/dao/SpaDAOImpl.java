/**
 * @author Guilherme Jatobá
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

import edu.pousada.entity.Spa;

public class SpaDAOImpl implements SpaDAO{

	private Connection con = DBUtil.getInstance().getConnection();
	
	/*
	 * CREATE TABLE spa (
idSpa INT PRIMARY KEY,
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor FLOAT NOT NULL,
 servico VARCHAR(100) NOT NULL
	 */
	public void adicionaSpa(Spa spa) throws SQLException {
		String sql = "INSERT INTO spa VALUES (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, spa.getIdSpa());
		ps.setDate(2, (Date) spa.getData());
		ps.setDate(3, (Date) spa.getHora());
		ps.setFloat(4, spa.getValor());
		ps.setString(5, spa.getServico());
		
		ps.execute();
		ps.close();
	}

	public void alteraSpa(Spa spa) throws SQLException {
		String sql =  "UPDATE spa SET data = ?, hora = ?, valor = ?, servico = ? WHERE idSpa = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, (Date) spa.getData());
		ps.setDate(2, (Date) spa.getHora());
		ps.setFloat(3, spa.getValor());
		ps.setString(4, spa.getServico());
		
		ps.setInt(5, spa.getIdSpa());
			
		ps.execute();
		ps.close();		
	}

	public void excluirSpa(Spa spa) throws SQLException {
		String sql = "DELETE spa WHERE idSpa = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, spa.getIdSpa());
		ps.execute();
		ps.close();
		
	}

	public Spa consultaSpa(Spa spa) throws SQLException {
		String sql = "SELECT * FROM spa WHERE idSpa = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, spa.getIdSpa());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			spa.setIdSpa(rs.getInt("idSpa"));
			spa.setData(rs.getDate("data"));
			spa.setHora(rs.getDate("hora"));
			spa.setValor(rs.getFloat("valor"));
			spa.setServico(rs.getString("servico"));
		}
		
		return spa;
	
	}

	public List<Spa> listaSpa() throws SQLException {
		List<Spa> listaSpa = new ArrayList<Spa>();
		String sql = "SELECT * FROM spa";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Spa spa = new Spa();
			spa.setIdSpa(rs.getInt("idSpa"));
			spa.setData(rs.getDate("data"));
			spa.setHora(rs.getDate("hora"));
			spa.setValor(rs.getFloat("valor"));
			spa.setServico(rs.getString("servico"));
			listaSpa.add(spa);
		}
		
		return listaSpa;
	}

}

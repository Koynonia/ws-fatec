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

import edu.pousada.entity.Chale;

public class ChaleDAOImpl implements ChaleDAO {

	private Connection con = DBUtil.getInstance().getConnection();
	
	/**
	 * CREATE TABLE chale (
	 * numero INT PRIMARY KEY, 
	 * categoria VARCHAR(30) NOT NULL, 
	 * diaria DOUBLE NOT NULL
	 * );
	 */

	@Override
	public void adicionarChale(Chale chale) throws SQLException {
		String sql = "INSERT INTO chale (numero, categoria, diaria) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, chale.getNumero());
		ps.setString(2, chale.getCategoria());
		ps.setDouble(3, chale.getDiaria());
		ps.execute();
		ps.close();
	}

	public void alterarChale(Chale chale) throws SQLException {

		String sql = "UPDATE chale SET categoria = ?, diaria = ? WHERE numero = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, chale.getCategoria());
		ps.setDouble(2, chale.getDiaria());
		ps.setInt(3, chale.getNumero());
		ps.execute();
		ps.close();

	}

	public void apagarChale(Chale chale) throws SQLException {
		String sql = "DELETE chale WHERE numero = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, chale.getNumero());
		ps.execute();
		ps.close();

	}

	@Override
	public Chale consultarChale(Chale chale) throws SQLException {
		String sql = "SELECT numero, categoria, diaria FROM chale WHERE numero = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, chale.getNumero());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			chale.setNumero(rs.getInt("numero"));
			chale.setCategoria(rs.getString("categoria"));
			chale.setDiaria(rs.getFloat("diaria"));
		}
		rs.close();
		ps.close();
		
		return chale;
	}

	@Override
	public List<Chale> todosChales() throws SQLException {
		List<Chale> listaChale = new ArrayList<Chale>();
		String sql = "SELECT numero, categoria, diaria FROM chale";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Chale chale = new Chale();
			chale.setNumero(rs.getInt("numero"));
			chale.setCategoria(rs.getString("categoria"));
			chale.setDiaria(rs.getFloat("diaria"));
			listaChale.add(chale);
		}
		rs.close();
		ps.close();
		
		return listaChale;
	}
}
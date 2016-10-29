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
	 * id INT AUTO_INCREMENT PRIMARY KEY, 
	 * categoria VARCHAR(30) NOT NULL, 
	 * diaria DOUBLE NOT NULL
	 * );
	 */

	@Override
	public void adicionar(Chale chale) throws SQLException {
		String sql = "INSERT INTO chale (NULL, categoria, diaria) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, chale.getCategoria());
		ps.setDouble(2, chale.getDiaria());
		ps.execute();
		ps.close();
	}

	public void alterar(Chale chale) throws SQLException {

		String sql = "UPDATE chale SET categoria = ?, diaria = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, chale.getCategoria());
		ps.setDouble(2, chale.getDiaria());
		ps.setInt(3, chale.getId());
		ps.execute();
		ps.close();

	}

	public void excluir(Chale chale) throws SQLException {
		String sql = "DELETE FROM chale WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, chale.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Chale consultar(Chale chale) throws SQLException {
		String sql = "SELECT id, categoria, diaria FROM chale WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, chale.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			chale.setId(rs.getInt("id"));
			chale.setCategoria(rs.getString("categoria"));
			chale.setDiaria(rs.getFloat("diaria"));
		}
		rs.close();
		ps.close();
		
		return chale;
	}

	@Override
	public List<Chale> todos() throws SQLException {
		List<Chale> listaChale = new ArrayList<Chale>();
		String sql = "SELECT id, categoria, diaria FROM chale";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Chale chale = new Chale();
			chale.setId(rs.getInt("id"));
			chale.setCategoria(rs.getString("categoria"));
			chale.setDiaria(rs.getFloat("diaria"));
			listaChale.add(chale);
		}
		rs.close();
		ps.close();
		
		return listaChale;
	}
}
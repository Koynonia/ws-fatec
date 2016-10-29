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
	public void adicionar(Chale c) throws SQLException {
		
		String sql = "INSERT INTO chale (NULL, categoria, diaria) VALUES (?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getCategoria());
		ps.setDouble(2, c.getDiaria());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Chale c) throws SQLException {

		String sql = "UPDATE chale SET categoria = ?, diaria = ? WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getCategoria());
		ps.setDouble(2, c.getDiaria());
		ps.setInt(3, c.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public void excluir(Chale c) throws SQLException {
		
		String sql = "DELETE FROM chale WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, c.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Chale consultar(Chale c) throws SQLException {
		
		String sql = "SELECT id, categoria, diaria FROM chale WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, c.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			c.setId(rs.getInt("id"));
			c.setCategoria(rs.getString("categoria"));
			c.setDiaria(rs.getFloat("diaria"));
		}
		rs.close();
		ps.close();
		
		return c;
	}

	@Override
	public List<Chale> todos() throws SQLException {
		
		String sql = "SELECT id, categoria, diaria FROM chale";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Chale> lista = new ArrayList<Chale>();
		while(rs.next()){
			Chale c = new Chale();
			c.setId(rs.getInt("id"));
			c.setCategoria(rs.getString("categoria"));
			c.setDiaria(rs.getFloat("diaria"));
			lista.add(c);
		}
		rs.close();
		ps.close();
		
		return lista;
	}
}
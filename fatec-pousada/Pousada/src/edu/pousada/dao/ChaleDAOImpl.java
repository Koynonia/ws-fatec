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
	public void adicionar(Chale obj) throws SQLException {
		
		String sql = "INSERT INTO chale VALUES (NULL,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getCategoria());
		ps.setDouble(2, obj.getDiaria());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Chale obj) throws SQLException {

		String sql = "UPDATE chale SET "
				+ "categoria = ?, "
				+ "diaria = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getCategoria());
		ps.setDouble(2, obj.getDiaria());
		ps.setInt(3, obj.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public void excluir(Chale obj) throws SQLException {
		
		String sql = "DELETE FROM chale WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Chale consultar(Chale obj) throws SQLException {
		
		String sql = "SELECT * FROM chale WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			obj.setId(rs.getInt("id"));
			obj.setCategoria(rs.getString("categoria"));
			obj.setDiaria(rs.getFloat("diaria"));
		}
		rs.close();
		ps.close();
		
		return obj;
	}

	@Override
	public List<Chale> todos() throws SQLException {
		
		String sql = "SELECT * FROM chale";
		
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

	public List<Chale> disponivel() throws SQLException {

		String sql = "SELECT * FROM chale "
				+ "LEFT OUTER JOIN reserva "
				+ "ON chale.id = reserva.idChale "
				+ "WHERE reserva.idChale IS NULL";

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
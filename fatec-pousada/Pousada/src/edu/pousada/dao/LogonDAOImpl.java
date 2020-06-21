/**
 * @author FERNANDO MORAES OLIVEIRA
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 01/11/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Logon;

public class LogonDAOImpl implements LogonDAO {

	private Connection con = DBUtil.getInstance().getConnection();
	
	/**
	 * CREATE TABLE logon (
	 * id INT AUTO_INCREMENT PRIMARY KEY, 
	 * idUsuario INT NOT NULL, 
	 * tela VARCHAR(15) NOT NULL, 
	 * perfil INT NOT NULL,
	 * logoff INT NOT NULL,
	 * dtLogon TIMESTAMP NOT NULL
	 * ) ENGINE = innodb;
	 */

	@Override
	public void adicionar(Logon obj) throws SQLException {
		
		String sql = "INSERT INTO logon VALUES (NULL,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getIdUsuario());
		ps.setString(2, obj.getTela());
		ps.setInt(3, obj.getPerfil());
		ps.setInt(4, obj.getLogoff());
		ps.setTimestamp(5, new java.sql.Timestamp( obj.getDtLogon().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Logon obj) throws SQLException {

		String sql = "UPDATE logon SET "
				+ "idUsuario = ?, "
				+ "tela = ? ,"
				+ "perfil = ? ,"
				+ "logoff = ? ,"
				+ "dtLogon = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getIdUsuario());
		ps.setString(2, obj.getTela());
		ps.setInt(3, obj.getPerfil());
		ps.setInt(4, obj.getLogoff());
		ps.setTimestamp(5, new java.sql.Timestamp( obj.getDtLogon().getTime() ));
		ps.setInt(6, obj.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public void excluir(Logon obj) throws SQLException {
		
		String sql = "DELETE FROM logon WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Logon consultar(Logon obj) throws SQLException {
		
		String sql = "SELECT * FROM logon WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			obj.setId(rs.getInt("id"));
			obj.setIdUsuario(rs.getInt("idUsuario"));
			obj.setTela(rs.getString("tela"));
			obj.setPerfil(rs.getInt("perfil"));
			obj.setLogoff(rs.getInt("logoff"));
			obj.setDtLogon(rs.getTimestamp("dtLogon"));
		}
		rs.close();
		ps.close();
		
		return obj;
	}

	@Override
	public List<Logon> todos() throws SQLException {
		
		String sql = "SELECT * FROM logon";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Logon> lista = new ArrayList<Logon>();
		while(rs.next()){
			Logon l = new Logon();
			l.setId(rs.getInt("id"));
			l.setIdUsuario(rs.getInt("idUsuario"));
			l.setTela(rs.getString("tela"));
			l.setPerfil(rs.getInt("perfil"));
			l.setLogoff(rs.getInt("logoff"));
			l.setDtLogon(rs.getTimestamp("dtLogon"));
			lista.add(l);
		}
		rs.close();
		ps.close();
		
		return lista;
	}
}
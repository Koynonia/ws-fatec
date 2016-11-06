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
	public void adicionar(Logon l) throws SQLException {
		
		String sql = "INSERT INTO logon VALUES (NULL,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, l.getIdUsuario());
		ps.setString(2, l.getTela());
		ps.setInt(3, l.getPerfil());
		ps.setInt(4, l.getLogoff());
		ps.setTimestamp(5, new java.sql.Timestamp( l.getDtLogon().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Logon l) throws SQLException {

		String sql = "UPDATE logon SET "
				+ "idUsuario = ?, "
				+ "tela = ? ,"
				+ "perfil = ? ,"
				+ "logoff = ? ,"
				+ "dtLogon = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, l.getIdUsuario());
		ps.setString(2, l.getTela());
		ps.setInt(3, l.getPerfil());
		ps.setInt(4, l.getLogoff());
		ps.setTimestamp(5, new java.sql.Timestamp( l.getDtLogon().getTime() ));
		ps.setInt(6, l.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public void excluir(Logon l) throws SQLException {
		
		String sql = "DELETE FROM logon WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, l.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Logon consultar(Logon l) throws SQLException {
		
		String sql = "SELECT * FROM logon WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, l.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			l.setId(rs.getInt("id"));
			l.setIdUsuario(rs.getInt("idUsuario"));
			l.setTela(rs.getString("tela"));
			l.setPerfil(rs.getInt("perfil"));
			l.setLogoff(rs.getInt("logoff"));
			l.setDtLogon(rs.getTimestamp("dtLogon"));
		}
		rs.close();
		ps.close();
		
		return l;
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
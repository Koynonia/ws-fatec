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

	@Override
	public void adicionar(Spa s) throws SQLException {
		
		String sql = "INSERT INTO spa VALUES (NULL,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(2, s.getServico());
		ps.setDate(3, new java.sql.Date( s.getDtReserva().getTime() ));
		ps.setDate(4, new java.sql.Date( s.getHrReserva().getTime() ));
		ps.setFloat(5, s.getValor());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Spa s) throws SQLException {
		
		String sql =  "UPDATE spa SET "
				+ "servico = ?, "
				+ "dtReserva = ?, "
				+ "hrReserva = ?, "
				+ "valor = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, s.getServico());
		ps.setDate(2, new java.sql.Date( s.getDtReserva().getTime() ));
		ps.setDate(3, new java.sql.Date( s.getHrReserva().getTime() ));
		ps.setFloat(4, s.getValor());
		ps.setInt(5, s.getId());			
		ps.execute();
		ps.close();		
	}

	@Override
	public void excluir(Spa s) throws SQLException {
		
		String sql = "DELETE FROM spa WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, s.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public Spa consultar(Spa s) throws SQLException {
		
		String sql = "SELECT * FROM spa WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, s.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			s.setId(rs.getInt("id"));
			s.setServico(rs.getString("servico"));
			s.setDtReserva(rs.getDate("dtReserva"));
			s.setHrReserva(rs.getDate("hrReserva"));
			s.setValor(rs.getFloat("valor"));
		}
		rs.close();
		ps.close();
		return s;
	}

	@Override
	public List<Spa> todos() throws SQLException {
		
		String sql = "SELECT * FROM spa";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Spa> lista = new ArrayList<Spa>();
		while(rs.next()){
			Spa s = new Spa();
			s.setId(rs.getInt("id"));
			s.setServico(rs.getString("servico"));
			s.setDtReserva(rs.getDate("dtReserva"));
			s.setHrReserva(rs.getDate("hrReserva"));
			s.setValor(rs.getFloat("valor"));
			lista.add(s);
		}
		rs.close();
		ps.close();
		return lista;
	}
}
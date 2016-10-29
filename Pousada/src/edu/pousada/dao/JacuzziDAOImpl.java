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

import edu.pousada.entity.Jacuzzi;

public class JacuzziDAOImpl implements JacuzziDAO {

	private Connection con = DBUtil.getInstance().getConnection();

	/**
	 * 	CREATE TABLE jacuzzi(
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * dtReserva DATE NOT NULL,
	 * hrReserva DATE NOT NULL,
	 * valor DECIMAL(7,2) NOT NULL
	 * );
	 */
	
	@Override
	public void adicionar(Jacuzzi j) throws SQLException {
		
		String sql = "INSERT INTO jacuzzi VALUES (NULL,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( j.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( j.getHrReserva().getTime() ));
		ps.setFloat(3, j.getValor());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Jacuzzi j) throws SQLException {
		
		String sql = "UPDATE jacuzzki SET "
				+ "dtReserva = ?, "
				+ "hrReserva = ?, "
				+ "valor = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( j.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( j.getHrReserva().getTime() ));
		ps.setFloat(3, j.getValor());		
		ps.setInt(4, j.getId());
		ps.execute();
		ps.close();	
	}

	@Override
	public void excluir(Jacuzzi j) throws SQLException {
		
		String sql = "DELETE FROM jacuzzi WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, j.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public List<Jacuzzi> todos() throws SQLException {
		
		String sql = "SELECT * FROM jacuzzi";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Jacuzzi> lista = new ArrayList<Jacuzzi>();
		while(rs.next()){
			Jacuzzi j = new Jacuzzi();
			j.setId(rs.getInt("id"));
			j.setDtReserva(rs.getDate("dtReserva"));
			j.setHrReserva(rs.getDate("hrReserva"));
			j.setValor(rs.getFloat("valor"));
			lista.add(j);
		}
		rs.close();
		ps.close();
		
		return lista;
	}
}
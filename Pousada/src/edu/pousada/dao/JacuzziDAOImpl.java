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
	
	public void adicionar(Jacuzzi jacuzzi) throws SQLException {
		String sql = "INSERT INTO jacuzzi VALUES (NULL,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( jacuzzi.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( jacuzzi.getHrReserva().getTime() ));
		ps.setFloat(3, jacuzzi.getValor());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Jacuzzi jacuzzi) throws SQLException {
		String sql = "UPDATE jacuzzki SET dtReserva = ?, hrReserva = ?, valor = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date( jacuzzi.getDtReserva().getTime() ));
		ps.setDate(2, new java.sql.Date( jacuzzi.getHrReserva().getTime() ));
		ps.setFloat(3, jacuzzi.getValor());		
		ps.setInt(4, jacuzzi.getId());
		ps.execute();
		ps.close();	
	}

	@Override
	public void excluir(Jacuzzi jacuzzi) throws SQLException {
		String sql = "DELETE FROM jacuzzi WHERE idJacuzzi = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, jacuzzi.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public List<Jacuzzi> todos() throws SQLException {
		List<Jacuzzi> listaJacuzzi = new ArrayList<Jacuzzi>();
		String sql = "SELECT * FROM jacuzzi";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Jacuzzi jacuzzi = new Jacuzzi();
			jacuzzi.setId(rs.getInt("id"));
			jacuzzi.setDtReserva(rs.getDate("dtReserva"));
			jacuzzi.setHrReserva(rs.getDate("hrReserva"));
			jacuzzi.setValor(rs.getFloat("valor"));
			listaJacuzzi.add(jacuzzi);
		}
		rs.close();
		ps.close();
		
		return listaJacuzzi;
	}
}
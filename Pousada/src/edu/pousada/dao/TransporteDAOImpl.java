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

import edu.pousada.entity.Transporte;

public class TransporteDAOImpl implements TransporteDAO {

	private Connection con = DBUtil.getInstance().getConnection();

	/**
	 * CREATE TABLE transporte(
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * placa VARCHAR(100) UNIQUE NOT NULL,
	 * estado VARCHAR(30) NOT NULL,
	 * destino VARCHAR(200) NOT NULL,
	 * dtReserva DATE NOT NULL,
	 * hrReserva DATE NOT NULL,
	 * valor DECIMAL(7,2) NOT NULL
	 * );
	 */
	
	public void adicionaTransporte(Transporte transporte) throws SQLException {
		String sql = "INSERT INTO transporte VALUES (NULL,?,?,?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, transporte.getPlaca());
		ps.setString(2, transporte.getEstado());
		ps.setString(3, transporte.getDestino());
		ps.setDate(4, new java.sql.Date( transporte.getDtReserva().getTime() ));
		ps.setDate(5, new java.sql.Date( transporte.getHrReserva().getTime() ));
		ps.setFloat(6, transporte.getValor());
		ps.execute();
		ps.close();
	}

	public void alteraTransporte(Transporte transporte) throws SQLException {
		String sql = "UPDATE transporte SET placa = ?, estado = ?, destino = ? dtReserva = ?, hrReserva = ?, valor = ? WHERE placa = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, transporte.getPlaca());
		ps.setString(2, transporte.getEstado());
		ps.setString(3, transporte.getDestino());
		ps.setDate(4, new java.sql.Date( transporte.getDtReserva().getTime() ));
		ps.setDate(5, new java.sql.Date( transporte.getHrReserva().getTime() ));
		ps.setFloat(6, transporte.getValor());
		ps.setString(7, transporte.getPlaca());
		ps.execute();
		ps.close();
	}

	public void excluiTransporte(Transporte transporte) throws SQLException {
		String sql = "DELETE transporte WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, transporte.getId());
		ps.execute();
		ps.close();
	}

	public Transporte consultaTransporte(Transporte transporte) throws SQLException {
		String sql = "SELECT * FROM transporte WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, transporte.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			transporte.setPlaca(rs.getString("placa"));
			transporte.setEstado(rs.getString("estado"));
			transporte.setDestino(rs.getString("destino"));
			transporte.setData(rs.getDate("dtReserva"));
			transporte.setHora(rs.getDate("hrReserva"));
			transporte.setValor(rs.getFloat("valor"));
		}
		rs.close();
		ps.close();
		
		return transporte;
	}

	public List<Transporte> listaTransporte() throws SQLException {
		List<Transporte> listaTransporte = new ArrayList<Transporte>();
		String sql = "SELECT * FROM transporte";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Transporte transporte = new Transporte();
			transporte.setPlaca(rs.getString("placa"));
			transporte.setEstado(rs.getString("estado"));
			transporte.setDestino(rs.getString("destino"));
			transporte.setData(rs.getDate("dtReserva"));
			transporte.setHora(rs.getDate("hrReserva"));
			transporte.setValor(rs.getFloat("valor"));
			listaTransporte.add(transporte);
		}
		rs.close();
		ps.close();
		
		return listaTransporte;
	}
}
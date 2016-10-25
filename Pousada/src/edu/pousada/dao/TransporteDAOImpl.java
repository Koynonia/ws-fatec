/**
 * @author Guilherme Jatobá
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Transporte;

public class TransporteDAOImpl implements TransporteDAO {

	private Connection con = DBUtil.getInstance().getConnection();

	/*
	 * CREATE TABLE transporte( id VARCHAR(100) PRIMARY KEY, estado VARCHAR(30)
	 * NOT NULL, destino VARCHAR(200) NOT NULL, qtd INT NOT NULL, -- cartao
	 * VARCHAR(100) NOT NULL, dt DATE NOT NULL, hora DATE NOT NULL, valor FLOAT
	 * NOT NULL
	 */
	public void adicionaTransporte(Transporte transporte) throws SQLException {
		String sql = "INSERT INTO transporte VALUES () ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, transporte.getId());
		ps.setString(2, transporte.getStatus());
		ps.setString(3, transporte.getLocal());
		ps.setInt(4, transporte.getQuantidade());
		ps.setDate(3, (Date) transporte.getData());
		ps.setDate(4, (Date) transporte.getHora());
		ps.setFloat(5, transporte.getValor());

		ps.execute();
		ps.close();

	}

	public void alteraTransporte(Transporte transporte) throws SQLException {
		String sql = "UPDATE transporte SET estado = ?, destino = ?, qtd = ? dt = ?, hora = ?, valor = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(7, transporte.getId());
		ps.setString(1, transporte.getStatus());
		ps.setString(2, transporte.getLocal());
		ps.setInt(3, transporte.getQuantidade());
		ps.setDate(4, (Date) transporte.getData());
		ps.setDate(5, (Date) transporte.getHora());
		ps.setFloat(6, transporte.getValor());

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
			transporte.setStatus(rs.getString("estado"));
			transporte.setLocal(rs.getString("destino"));
			transporte.setQuantidade(rs.getInt("qtd"));
			transporte.setData(rs.getDate("data"));
			transporte.setHora(rs.getDate("hora"));
			transporte.setValor(rs.getFloat("valor"));
		}

		return transporte;

	}

	public List<Transporte> listaTransporte() throws SQLException {
		List<Transporte> listaTransporte = new ArrayList<Transporte>();
		String sql = "SELECT * FROM transporte";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Transporte transporte = new Transporte();
			transporte.setStatus(rs.getString("estado"));
			transporte.setLocal(rs.getString("destino"));
			transporte.setQuantidade(rs.getInt("qtd"));
			transporte.setData(rs.getDate("data"));
			transporte.setHora(rs.getDate("hora"));
			transporte.setValor(rs.getFloat("valor"));

			listaTransporte.add(transporte);
		}

		return listaTransporte;
	}

}

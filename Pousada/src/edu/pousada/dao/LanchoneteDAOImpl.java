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

import edu.pousada.entity.Lanchonete;

public class LanchoneteDAOImpl implements LanchoneteDAO{

	private Connection con = DBUtil.getInstance().getConnection();
	
	/**
	 * CREATE TABLE lanchonete(
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * nome VARCHAR(100) NOT NULL,
	 * tipo VARCHAR(100) NOT NULL,
	 * dtReserva DATE NOT NULL,
	 * hrReserva DATE NOT NULL,
	 * valor DECIMAL(7,2) NOT NULL
	 * );
	 */

	public void adicionar(Lanchonete lanchonete) throws SQLException {
		String sql = "INSERT INTO lanchonete VALUES (NULL,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, lanchonete.getNome());
		ps.setString(2, lanchonete.getTipo());
		ps.setDate(3, new java.sql.Date( lanchonete.getDtReserva().getTime() ));
		ps.setDate(4, new java.sql.Date( lanchonete.getHrReserva().getTime() ));
		ps.setFloat(5, lanchonete.getValor());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Lanchonete lanchonete) throws SQLException {
		String sql =  "UPDATE lanchonete SET nome = ?, tipo = ?, dtReserva = ?, hrReserva = ?, valor = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, lanchonete.getNome());
		ps.setString(2, lanchonete.getTipo());
		ps.setDate(3, new java.sql.Date( lanchonete.getDtReserva().getTime() ));
		ps.setDate(4, new java.sql.Date( lanchonete.getHrReserva().getTime() ));
		ps.setFloat(5, lanchonete.getValor());
		ps.setInt(6, lanchonete.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Lanchonete lanchonete) throws SQLException {
		String sql = "DELETE FROM lanchonete WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, lanchonete.getId());
		ps.execute();
		ps.close();
	}


	public Lanchonete consultar(Lanchonete lanchonete) throws SQLException {
		String sql = "SELECT * FROM lanchonete WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, lanchonete.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			lanchonete.setId(rs.getInt("id"));
			lanchonete.setNome(rs.getString("nome"));
			lanchonete.setTipo(rs.getString("tipo"));
			lanchonete.setDtReserva(rs.getDate("dtReserva"));
			lanchonete.setHrReserva(rs.getDate("hrReserva"));
			lanchonete.setValor(rs.getFloat("valor"));
			lanchonete.setId(rs.getInt("id"));
		}
		ps.execute();
		ps.close();
		rs.close();
		
		return lanchonete;
	}

	public List<Lanchonete> todos() throws SQLException {
		List<Lanchonete> listaLanchonete = new ArrayList<Lanchonete>();
		String sql = "SELECT * FROM lanchonete";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Lanchonete lanchonete = new Lanchonete();
			lanchonete.setId(rs.getInt("id"));
			lanchonete.setNome(rs.getString("nome"));
			lanchonete.setTipo(rs.getString("tipo"));
			lanchonete.setDtReserva(rs.getDate("dtReserva"));
			lanchonete.setHrReserva(rs.getDate("hrReserva"));
			lanchonete.setValor(rs.getFloat("valor"));
			listaLanchonete.add(lanchonete);
		}
		ps.execute();
		ps.close();
		rs.close();
		
		return listaLanchonete;
	}
}
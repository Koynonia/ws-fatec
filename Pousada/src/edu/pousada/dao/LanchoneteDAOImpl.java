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


import edu.pousada.entity.Lanchonete;

public class LanchoneteDAOImpl implements LanchoneteDAO{

	private Connection con = DBUtil.getInstance().getConnection();
	/*
 nome VARCHAR(100) NOT NULL,
 tipo VARCHAR(100) NOT NULL,  
-- cartao VARCHAR(100) NOT NULL,
 data DATE NOT NULL,
 hora DATE NOT NULL,
 valor FLOAT NOT NULL
	 */
	public void adicionarLanchonete(Lanchonete lanchonete) throws SQLException {
		String sql = "INSERT INTO lanchonete VALUES (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, lanchonete.getNome());
		ps.setString(2, lanchonete.getTipo());
		ps.setDate(3, (Date) lanchonete.getData());
		ps.setDate(4, (Date) lanchonete.getHora());
		ps.setFloat(5, lanchonete.getValor());
		
		ps.execute();
		ps.close();
	}

	@Override
	public void alterarLanchonete(Lanchonete lanchonete) throws SQLException {
		String sql =  "UPDATE lanchonete SET nome = ?, tipo = ?, data = ?, hora = ?, valor = ? WHERE idLanchonete = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, lanchonete.getNome());
		ps.setString(2, lanchonete.getTipo());
		ps.setDate(3, (Date) lanchonete.getData());
		ps.setDate(4, (Date) lanchonete.getHora());
		ps.setFloat(5, lanchonete.getValor());
		
		ps.setInt(6, lanchonete.getIdLanchonete() );
		ps.execute();
		ps.close();
	}

	@Override
	public void excluirLanchonete(Lanchonete lanchonete) throws SQLException {
		String sql = "DELETE lanchonete WHERE idLanchonete = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, lanchonete.getIdLanchonete());
		ps.execute();
		ps.close();
		
	}


	public Lanchonete consultaLanchonete(Lanchonete lanchonete) throws SQLException {
		String sql = "SELECT * FROM lanchonete WHERE idLanchonete = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, lanchonete.getIdLanchonete());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			lanchonete.setIdLanchonete(rs.getInt("idLanchonete"));
			lanchonete.setNome(rs.getString("nome"));
			lanchonete.setTipo(rs.getString("tipo"));
			lanchonete.setData(rs.getDate("data"));
			lanchonete.setHora(rs.getDate("hora"));
			lanchonete.setValor(rs.getFloat("valor"));
			
		}
		return lanchonete;
		
	}

	public List<Lanchonete> listaLanchonete() throws SQLException {
		List<Lanchonete> listaLanchonete = new ArrayList<Lanchonete>();
		String sql = "SELECT * FROM lanchonete";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Lanchonete lanchonete = new Lanchonete();
			lanchonete.setIdLanchonete(rs.getInt("idLanchonete"));
			lanchonete.setNome(rs.getString("nome"));
			lanchonete.setTipo(rs.getString("tipo"));
			lanchonete.setData(rs.getDate("data"));
			lanchonete.setHora(rs.getDate("hora"));
			lanchonete.setValor(rs.getFloat("valor"));
			listaLanchonete.add(lanchonete);
		}
		
		return listaLanchonete;
	}

}

/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 29/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Contato;

public class ContatoDAOImpl implements ContatoDAO {

	/**
	 * CREATE TABLE contato(
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * nome VARCHAR(100) NOT NULL,
	 * email VARCHAR(30) NOT NULL,
	 * telefone VARCHAR(11) NOT NULL,
	 * bairro VARCHAR(30),
	 * cidade VARCHAR(30),
	 * estado VARCHAR(15),
	 * pais VARCHAR(10),
	 * assunto INT NOT NULL,
	 * mensagem VARCHAR(300),
	 * dtCadastro DATE NOT NULL
	 * ) ENGINE = innodb;
	 */
	
	private Connection con = DBUtil.getInstance().getConnection();

	@Override
	public void adicionar(Contato c) throws SQLException {
		
		String sql = "INSERT INTO contato VALUES (NULL,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getEmail());
		ps.setString(3, c.getTelefone());
		ps.setString(4, c.getCidade());
		ps.setString(5, c.getEstado());
		ps.setString(6, c.getPais());
		ps.setInt(7, c.getAssunto());
		ps.setString(8, c.getMensagem());
		ps.setDate(9, new java.sql.Date( c.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Contato c) throws SQLException {
		
		String sql = "UPDATE contato SET "
				+ "nome = ?, "
				+ "email = ?, "
				+ "telefone = ?, "
				+ "cidade = ?, "
				+ "estado = ?, "
				+ "pais = ?, "
				+ "assunto = ?"
				+ "mensagem = ?"
				+ "dtCadastro = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getEmail());
		ps.setString(3, c.getTelefone());
		ps.setString(4, c.getCidade());
		ps.setString(5, c.getEstado());
		ps.setString(6, c.getPais());
		ps.setInt(7, c.getAssunto());
		ps.setString(8, c.getMensagem());
		ps.setDate(9, new java.sql.Date( c.getDtCadastro().getTime() ));
		ps.setInt(10, c.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Contato c) throws SQLException {
		
		String sql = "DELETE FROM contato WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, c.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Contato consultar(Contato c) throws SQLException {
		
		String sql = "SELECT * FROM contato WHERE id =  ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, c.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));;
			c.setTelefone(rs.getString("telefone"));
			c.setCidade(rs.getString("cidade"));
			c.setEstado(rs.getString("estado"));
			c.setPais(rs.getString("pais"));
			c.setAssunto(rs.getInt("assunto"));
			c.setMensagem(rs.getString("mensagem"));
			c.setDtCadastro(rs.getDate("dtCadastro"));
		}
		rs.close();
		ps.close();

		return c;
	}

	@Override
	public List<Contato> todos() throws SQLException {
		List<Contato> listaContato = new ArrayList<Contato>();
		String sql = "SELECT * FROM contato";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Contato c = new Contato();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));;
			c.setTelefone(rs.getString("telefone"));
			c.setCidade(rs.getString("cidade"));
			c.setEstado(rs.getString("estado"));
			c.setPais(rs.getString("pais"));
			c.setAssunto(rs.getInt("assunto"));
			c.setMensagem(rs.getString("mensagem"));
			c.setDtCadastro(rs.getDate("dtCadastro"));
			listaContato.add(c);
		}
		rs.close();
		ps.close();
		
		return listaContato;
	}
}
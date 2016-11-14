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
	 * lida BOOLEAN NOT NULL,
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
	public void adicionar(Contato obj) throws SQLException {
		
		String sql = "INSERT INTO contato VALUES (NULL,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBoolean(1, obj.getLida());
		ps.setString(2, obj.getNome());
		ps.setString(3, obj.getEmail());
		ps.setString(4, obj.getTelefone());
		ps.setString(5, obj.getCidade());
		ps.setString(6, obj.getEstado());
		ps.setString(7, obj.getPais());
		ps.setInt(8, obj.getAssunto());
		ps.setString(9, obj.getMensagem());
		ps.setDate(10, new java.sql.Date( obj.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Contato obj) throws SQLException {
		
		String sql = "UPDATE contato SET "
				+ "lida = ?, "
				+ "nome = ?, "
				+ "email = ?, "
				+ "telefone = ?, "
				+ "cidade = ?, "
				+ "estado = ?, "
				+ "pais = ?, "
				+ "assunto = ?, "
				+ "mensagem = ?, "
				+ "dtCadastro = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBoolean(1, obj.getLida());
		ps.setString(2, obj.getNome());
		ps.setString(3, obj.getEmail());
		ps.setString(4, obj.getTelefone());
		ps.setString(5, obj.getCidade());
		ps.setString(6, obj.getEstado());
		ps.setString(7, obj.getPais());
		ps.setInt(8, obj.getAssunto());
		ps.setString(9, obj.getMensagem());
		ps.setDate(10, new java.sql.Date( obj.getDtCadastro().getTime() ));
		ps.setInt(11, obj.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Contato obj) throws SQLException {
		
		String sql = "DELETE FROM contato WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Contato consultar(Contato obj) throws SQLException {
		
		String sql = "SELECT * FROM contato WHERE id =  ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			obj.setId(rs.getInt("id"));
			obj.setLida(rs.getBoolean("Lida"));
			obj.setNome(rs.getString("nome"));
			obj.setEmail(rs.getString("email"));;
			obj.setTelefone(rs.getString("telefone"));
			obj.setCidade(rs.getString("cidade"));
			obj.setEstado(rs.getString("estado"));
			obj.setPais(rs.getString("pais"));
			obj.setAssunto(rs.getInt("assunto"));
			obj.setMensagem(rs.getString("mensagem"));
			obj.setDtCadastro(rs.getDate("dtCadastro"));
		}
		rs.close();
		ps.close();

		return obj;
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
			c.setLida(rs.getBoolean("Lida"));
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
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

import edu.pousada.entity.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	/**
	 * CREATE TABLE cliente(
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * nome VARCHAR(100) NOT NULL,
	 * email VARCHAR(30) UNIQUE NOT NULL,
	 * documento VARCHAR(15) PRIMARY KEY,
	 * docTipo VARCHAR(15),
	 * dtNasc DATE,
	 * telefone VARCHAR(11) UNIQUE,
	 * celular VARCHAR(12) UNIQUE NOT NULL,
	 * endereco VARCHAR(200),
	 * bairro VARCHAR(30),
	 * cidade VARCHAR(30) NOT NULL,
	 * estado VARCHAR(15) NOT NULL,
	 * pais VARCHAR(10) NOT NULL,
	 * cep VARCHAR(8),
	 * usuario VARCHAR(15),
	 * senha VARCHAR(15),
	 * ativo BOOLEAN NOT NULL,
	 * dtCadastro DATE NOT NULL
	 * );
	 */

	private Connection con = DBUtil.getInstance().getConnection();

	@Override
	public void adicionar(Cliente c) throws SQLException {

		String sql = "INSERT INTO cliente VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getEmail());
		ps.setString(3, c.getDocumento());
		ps.setString(4, c.getDocTipo());
		ps.setDate(5, new java.sql.Date( c.getDtNasc().getTime() ));
		ps.setString(6, c.getTelefone());
		ps.setString(7, c.getCelular());
		ps.setString(8, c.getEndereco());
		ps.setString(9, c.getBairro());
		ps.setString(10, c.getCidade());
		ps.setString(11, c.getEstado());
		ps.setString(12, c.getPais());
		ps.setString(13, c.getCep());
		ps.setString(14, c.getLogin());
		ps.setString(15, c.getSenha());
		ps.setBoolean(16, c.getAtivo());
		ps.setDate(17, new java.sql.Date( c.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Cliente c) throws SQLException {

		String sql = "UPDATE cliente SET "
				+ "nome = ?, "
				+ "email = ?, "
				+ "docTipo = ?, "
				+ "dtNasc = ?, "
				+ "telefone = ?, "
				+ "celular = ?, "
				+ "endereco = ?, "
				+ "bairro = ?, "
				+ "cidade = ?, "
				+ "estado = ?, "
				+ "pais = ?, "
				+ "cep = ?, "
				+ "login = ? "
				+ "senha = ? "
				+ "ativo = ? "
				+ "dtCadastro = ?, "
				+ "WHERE documento = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getEmail());
		ps.setString(3, c.getDocumento());
		ps.setString(4, c.getDocTipo());
		ps.setDate(5, new java.sql.Date( c.getDtNasc().getTime() ));
		ps.setString(6, c.getTelefone());
		ps.setString(7, c.getCelular());
		ps.setString(8, c.getEndereco());
		ps.setString(9, c.getBairro());
		ps.setString(10, c.getCidade());
		ps.setString(11, c.getEstado());
		ps.setString(12, c.getPais());
		ps.setString(13, c.getCep());
		ps.setString(14, c.getLogin());
		ps.setString(15, c.getSenha());
		ps.setBoolean(16, c.getAtivo());
		ps.setString(17, c.getDocumento());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Cliente cliente) throws SQLException {

		String sql = "DELETE FROM cliente WHERE documento = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cliente.getDocumento());
		ps.execute();
		ps.close();
	}

	@Override
	public Cliente consultar(Cliente c) throws SQLException {

		String sql = "SELECT * FROM cliente WHERE documento =  ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getDocumento());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setDocumento(rs.getString("documento"));
			c.setDocTipo(rs.getString("docTipo"));
			c.setDtNasc(rs.getDate("dtNasc"));
			c.setTelefone(rs.getString("telefone"));
			c.setCelular(rs.getString("celular"));
			c.setEndereco(rs.getString("endereco"));
			c.setBairro(rs.getString("bairro"));
			c.setCidade(rs.getString("cidade"));
			c.setEstado(rs.getString("estado"));
			c.setPais(rs.getString("pais"));
			c.setCep(rs.getString("cep"));
			c.setLogin(rs.getString("login"));
			c.setSenha(rs.getString("senha"));
			c.setAtivo(rs.getBoolean("ativo"));
			c.setDtCadastro(rs.getDate("dtCadastro"));
		}
		rs.close();
		ps.close();

		return c;
	}

	@Override
	public List<Cliente> todos() throws SQLException {

		String sql = "SELECT * FROM cliente";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		while (rs.next()) {
			Cliente c = new Cliente();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setDocumento(rs.getString("documento"));
			c.setDocTipo(rs.getString("docTipo"));
			c.setDtNasc(rs.getDate("dtNasc"));
			c.setTelefone(rs.getString("telefone"));
			c.setCelular(rs.getString("celular"));
			c.setEndereco(rs.getString("endereco"));
			c.setBairro(rs.getString("bairro"));
			c.setCidade(rs.getString("cidade"));
			c.setEstado(rs.getString("estado"));
			c.setPais(rs.getString("pais"));
			c.setCep(rs.getString("cep"));
			c.setLogin(rs.getString("login"));
			c.setSenha(rs.getString("senha"));
			c.setAtivo(rs.getBoolean("ativo"));
			c.setDtCadastro(rs.getDate("dtCadastro"));
			listaCliente.add(c);
		}
		rs.close();
		ps.close();
		
		return listaCliente;
	}
}
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
	public void adicionar(Cliente obj) throws SQLException {

		String sql = "INSERT INTO cliente VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getNome());
		ps.setString(2, obj.getEmail());
		ps.setString(3, obj.getDocumento());
		ps.setString(4, obj.getDocTipo());
		ps.setDate(5, new java.sql.Date( obj.getDtNasc().getTime() ));
		ps.setString(6, obj.getTelefone());
		ps.setString(7, obj.getCelular());
		ps.setString(8, obj.getEndereco());
		ps.setString(9, obj.getBairro());
		ps.setString(10, obj.getCidade());
		ps.setString(11, obj.getEstado());
		ps.setString(12, obj.getPais());
		ps.setString(13, obj.getCep());
		ps.setString(14, obj.getLogin());
		ps.setString(15, obj.getSenha());
		ps.setBoolean(16, obj.getAtivo());
		ps.setDate(17, new java.sql.Date( obj.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Cliente obj) throws SQLException {

		String sql = "UPDATE cliente SET "
				+ "nome = ?, "
				+ "email = ?, "
				+ "documento = ?, "
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
				+ "login = ?, "
				+ "senha = ?, "
				+ "ativo = ? "
				+ "WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getNome());
		ps.setString(2, obj.getEmail());
		ps.setString(3, obj.getDocumento());
		ps.setString(4, obj.getDocTipo());
		ps.setDate(5, new java.sql.Date( obj.getDtNasc().getTime() ));
		ps.setString(6, obj.getTelefone());
		ps.setString(7, obj.getCelular());
		ps.setString(8, obj.getEndereco());
		ps.setString(9, obj.getBairro());
		ps.setString(10, obj.getCidade());
		ps.setString(11, obj.getEstado());
		ps.setString(12, obj.getPais());
		ps.setString(13, obj.getCep());
		ps.setString(14, obj.getLogin());
		ps.setString(15, obj.getSenha());
		ps.setBoolean(16, obj.getAtivo());
		ps.setInt(17, obj.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Cliente cliente) throws SQLException {

		String sql = "DELETE FROM cliente WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, cliente.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public Cliente consultar(Cliente obj) throws SQLException {

		String sql = "SELECT * FROM cliente WHERE id =  ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			obj.setId(rs.getInt("id"));
			obj.setNome(rs.getString("nome"));
			obj.setEmail(rs.getString("email"));
			obj.setDocumento(rs.getString("documento"));
			obj.setDocTipo(rs.getString("docTipo"));
			obj.setDtNasc(rs.getDate("dtNasc"));
			obj.setTelefone(rs.getString("telefone"));
			obj.setCelular(rs.getString("celular"));
			obj.setEndereco(rs.getString("endereco"));
			obj.setBairro(rs.getString("bairro"));
			obj.setCidade(rs.getString("cidade"));
			obj.setEstado(rs.getString("estado"));
			obj.setPais(rs.getString("pais"));
			obj.setCep(rs.getString("cep"));
			obj.setLogin(rs.getString("login"));
			obj.setSenha(rs.getString("senha"));
			obj.setAtivo(rs.getBoolean("ativo"));
			obj.setDtCadastro(rs.getDate("dtCadastro"));
		}
		rs.close();
		ps.close();

		return obj;
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
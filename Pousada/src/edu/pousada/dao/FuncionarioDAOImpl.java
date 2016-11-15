/**
 * @author FERNANDO MORAES OLIVEIRA
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 28/10/2016
 */
package edu.pousada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Funcionario;

public class FuncionarioDAOImpl implements FuncionarioDAO {

	/**
	 * CREATE TABLE funcionario(
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * nome VARCHAR(100) NOT NULL,
	 * email VARCHAR(30) UNIQUE NOT NULL,
	 * cpf VARCHAR(11) UNIQUE NOT NULL,
	 * dtNasc DATE NOT NULL,
	 * telefone VARCHAR(11) UNIQUE,
	 * celular VARCHAR(12) UNIQUE,
	 * endereco VARCHAR(200) NOT NULL,
	 * bairro VARCHAR(30) NOT NULL,
	 * cidade VARCHAR(30) NOT NULL,
	 * estado VARCHAR(15) NOT NULL,
	 * pais VARCHAR(10) NOT NULL,
	 * cep VARCHAR(8) NOT NULL,
	 * cargo VARCHAR(30) NOT NULL,
	 * setor VARCHAR(30) NOT NULL, 
	 * login VARCHAR(15) NOT NULL,
	 * senha VARCHAR(15) NOT NULL,
	 * ativo BOOLEAN NOT NULL,
	 * dtCadastro DATE NOT NULL
	 * ) ENGINE = innodb;
	 */

	private Connection con = DBUtil.getInstance().getConnection();

	@Override
	public void adicionar(Funcionario obj) throws SQLException {

		String sql = "INSERT INTO funcionario VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getNome());
		ps.setString(2, obj.getEmail());
		ps.setString(3, obj.getCpf());
		ps.setDate(4, new java.sql.Date( obj.getDtNasc().getTime() ));
		ps.setString(5, obj.getTelefone());
		ps.setString(6, obj.getCelular());
		ps.setString(7, obj.getEndereco());
		ps.setString(8, obj.getBairro());
		ps.setString(9, obj.getCidade());
		ps.setString(10, obj.getEstado());
		ps.setString(11, obj.getPais());
		ps.setString(12, obj.getCep());
		ps.setString(13, obj.getCargo());
		ps.setString(14, obj.getSetor());
		ps.setString(15, obj.getLogin());
		ps.setString(16, obj.getSenha());
		ps.setBoolean(17, obj.getAtivo());
		ps.setDate(18, new java.sql.Date( obj.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Funcionario obj) throws SQLException {

		String sql = "UPDATE funcionario SET "
				+ "nome = ?, "
				+ "email = ?, "
				+ "cpf = ?, "
				+ "dtNasc = ?, "
				+ "telefone = ?, "
				+ "celular = ?, "
				+ "endereco = ?, "
				+ "bairro = ?, "
				+ "cidade = ?, "
				+ "estado = ?, "
				+ "pais = ?, "
				+ "cep = ?, "
				+ "cargo = ?, "
				+ "setor = ?, "
				+ "login = ?, "
				+ "senha = ?, "
				+ "ativo = ?, "
				+ "WHERE cpf = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getNome());
		ps.setString(2, obj.getEmail());
		ps.setString(3, obj.getCpf());
		ps.setDate(4, new java.sql.Date( obj.getDtNasc().getTime() ));
		ps.setString(5, obj.getTelefone());
		ps.setString(6, obj.getCelular());
		ps.setString(7, obj.getEndereco());
		ps.setString(8, obj.getBairro());
		ps.setString(9, obj.getCidade());
		ps.setString(10, obj.getEstado());
		ps.setString(11, obj.getPais());
		ps.setString(12, obj.getCep());
		ps.setString(13, obj.getCargo());
		ps.setString(14, obj.getSetor());
		ps.setString(15, obj.getLogin());
		ps.setString(16, obj.getSenha());
		ps.setBoolean(17, obj.getAtivo());
		ps.setString(18, obj.getCpf());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Funcionario obj) throws SQLException {

		String sql = "DELETE FROM funcionario WHERE cpf = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getCpf());
		ps.execute();
		ps.close();

	}

	@Override
	public Funcionario consultar(Funcionario obj) throws SQLException {

		String sql = "SELECT * FROM funcionarios WHERE cpf =  ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			obj.setId(rs.getInt("id"));
			obj.setNome(rs.getString("nome"));
			obj.setEmail(rs.getString("email"));
			obj.setCpf(rs.getString("cpf"));
			obj.setDtNasc(rs.getDate("dtNasc"));
			obj.setTelefone(rs.getString("telefone"));
			obj.setCelular(rs.getString("celular"));
			obj.setEndereco(rs.getString("endereco"));
			obj.setBairro(rs.getString("bairro"));
			obj.setCidade(rs.getString("cidade"));
			obj.setEstado(rs.getString("estado"));
			obj.setPais(rs.getString("pais"));
			obj.setCep(rs.getString("cep"));
			obj.setCargo(rs.getString("cargo"));
			obj.setSetor(rs.getString("setor"));
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
	public List<Funcionario> todos() throws SQLException {

		String sql = "SELECT * FROM funcionario";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Funcionario> lista = new ArrayList<Funcionario>();
		while (rs.next()) {
			Funcionario f = new Funcionario();
			f.setId(rs.getInt("id"));
			f.setNome(rs.getString("nome"));
			f.setEmail(rs.getString("email"));
			f.setCpf(rs.getString("cpf"));
			f.setDtNasc(rs.getDate("dtNasc"));
			f.setTelefone(rs.getString("telefone"));
			f.setCelular(rs.getString("celular"));
			f.setEndereco(rs.getString("endereco"));
			f.setBairro(rs.getString("bairro"));
			f.setCidade(rs.getString("cidade"));
			f.setEstado(rs.getString("estado"));
			f.setPais(rs.getString("pais"));
			f.setCep(rs.getString("cep"));
			f.setCargo(rs.getString("cargo"));
			f.setSetor(rs.getString("setor"));
			f.setLogin(rs.getString("login"));
			f.setSenha(rs.getString("senha"));
			f.setAtivo(rs.getBoolean("ativo"));
			f.setDtCadastro(rs.getDate("dtCadastro"));
			lista.add(f);
		}
		rs.close();
		ps.close();
		return lista;
	}
}
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
	public void adicionar(Funcionario f) throws SQLException {

		String sql = "INSERT INTO funcionario VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, f.getNome());
		ps.setString(2, f.getEmail());
		ps.setString(3, f.getCpf());
		ps.setDate(4, new java.sql.Date( f.getDtNasc().getTime() ));
		ps.setString(5, f.getTelefone());
		ps.setString(6, f.getCelular());
		ps.setString(7, f.getEndereco());
		ps.setString(8, f.getBairro());
		ps.setString(9, f.getCidade());
		ps.setString(10, f.getEstado());
		ps.setString(11, f.getPais());
		ps.setString(12, f.getCep());
		ps.setString(13, f.getCargo());
		ps.setString(14, f.getSetor());
		ps.setString(15, f.getLogin());
		ps.setString(16, f.getSenha());
		ps.setBoolean(17, f.getAtivo());
		ps.setDate(18, new java.sql.Date( f.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Funcionario f) throws SQLException {

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
		ps.setString(1, f.getNome());
		ps.setString(2, f.getEmail());
		ps.setString(3, f.getCpf());
		ps.setDate(4, new java.sql.Date( f.getDtNasc().getTime() ));
		ps.setString(5, f.getTelefone());
		ps.setString(6, f.getCelular());
		ps.setString(7, f.getEndereco());
		ps.setString(8, f.getBairro());
		ps.setString(9, f.getCidade());
		ps.setString(10, f.getEstado());
		ps.setString(11, f.getPais());
		ps.setString(12, f.getCep());
		ps.setString(13, f.getCargo());
		ps.setString(14, f.getSetor());
		ps.setString(15, f.getLogin());
		ps.setString(16, f.getSenha());
		ps.setBoolean(17, f.getAtivo());
		ps.setString(18, f.getCpf());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Funcionario f) throws SQLException {

		String sql = "DELETE FROM funcionario WHERE cpf = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, f.getCpf());
		ps.execute();
		ps.close();

	}

	@Override
	public Funcionario consultar(Funcionario f) throws SQLException {

		String sql = "SELECT * FROM funcionarios WHERE cpf =  ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, f.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
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
		}
		rs.close();
		ps.close();

		return f;
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
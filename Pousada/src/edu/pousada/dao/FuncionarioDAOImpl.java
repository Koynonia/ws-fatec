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
	 * dtCadastro DATE NOT NULL,
	 * ativo BOOLEAN NOT NULL
	 * );
	 */
	
	private Connection con = DBUtil.getInstance().getConnection();

	public void adicionarFuncionario(Funcionario funcionario) throws SQLException {
		String sql = "INSERT INTO funcionario VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, funcionario.getNome());
		ps.setString(2, funcionario.getEmail());
		ps.setString(3, funcionario.getCpf());
		ps.setDate(4, new java.sql.Date( funcionario.getDtNasc().getTime() ));
		ps.setString(5, funcionario.getTelefone());
		ps.setString(6, funcionario.getCelular());
		ps.setString(7, funcionario.getEndereco());
		ps.setString(8, funcionario.getBairro());
		ps.setString(9, funcionario.getCidade());
		ps.setString(10, funcionario.getEstado());
		ps.setString(11, funcionario.getPais());
		ps.setString(12, funcionario.getCep());
		ps.setString(13, funcionario.getCargo());
		ps.setString(14, funcionario.getSetor());
		ps.setDate(15, new java.sql.Date( funcionario.getDtCadastro().getTime() ));
		ps.setBoolean(16, funcionario.getAtivo());
		ps.execute();
		ps.close();
	}

	public void alterarFuncionario(Funcionario funcionario) throws SQLException {
		String sql = "UPDATE funcionario SET "
				+ "nome = ?, email = ?, cpf = ?, dtNasc = ?, telefone = ?, celular = ?, "
				+ "endereco = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, cep = ?, "
				+ "cargo = ?, setor = ?, dtCadastro = ?, ativo = ? WHERE cpf = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, funcionario.getNome());
		ps.setString(2, funcionario.getEmail());
		ps.setString(3, funcionario.getCpf());
		ps.setDate(4, new java.sql.Date( funcionario.getDtNasc().getTime() ));
		ps.setString(5, funcionario.getTelefone());
		ps.setString(6, funcionario.getCelular());
		ps.setString(7, funcionario.getEndereco());
		ps.setString(8, funcionario.getBairro());
		ps.setString(9, funcionario.getCidade());
		ps.setString(10, funcionario.getEstado());
		ps.setString(11, funcionario.getPais());
		ps.setString(12, funcionario.getCep());
		ps.setString(13, funcionario.getCargo());
		ps.setString(14, funcionario.getSetor());
		ps.setDate(15, new java.sql.Date( funcionario.getDtCadastro().getTime() ));
		ps.setBoolean(16, funcionario.getAtivo());
		ps.setString(17, funcionario.getCpf());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiFuncionario(Funcionario funcionario) throws SQLException {
		String sql = "DELETE funcionario WHERE cpf = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, funcionario.getCpf());
		ps.execute();
		ps.close();

	}


	public Funcionario consultaFuncionario(Funcionario funcionario) throws SQLException {
		String sql = "SELECT * FROM funcionarios WHERE cpf =  ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, funcionario.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			funcionario.setId(rs.getInt("id"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setCpf(rs.getString("cpf"));
			funcionario.setDtNasc(rs.getDate("dtNasc"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setCelular(rs.getString("celular"));
			funcionario.setEndereco(rs.getString("endereco"));
			funcionario.setBairro(rs.getString("bairro"));
			funcionario.setCidade(rs.getString("cidade"));
			funcionario.setEstado(rs.getString("estado"));
			funcionario.setPais(rs.getString("pais"));
			funcionario.setCep(rs.getString("cep"));
			funcionario.setCargo(rs.getString("cargo"));
			funcionario.setSetor(rs.getString("setor"));
			funcionario.setDtCadastro(rs.getDate("dtCadastro"));
			funcionario.setAtivo(rs.getBoolean("ativo"));
		}
		rs.close();
		ps.close();

		return funcionario;
	}

	public List<Funcionario> todosFuncionarios() throws SQLException {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM funcionario";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(rs.getInt("id"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setCpf(rs.getString("cpf"));
			funcionario.setDtNasc(rs.getDate("dtNasc"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setCelular(rs.getString("celular"));
			funcionario.setEndereco(rs.getString("endereco"));
			funcionario.setBairro(rs.getString("bairro"));
			funcionario.setCidade(rs.getString("cidade"));
			funcionario.setEstado(rs.getString("estado"));
			funcionario.setPais(rs.getString("pais"));
			funcionario.setCep(rs.getString("cep"));
			funcionario.setCargo(rs.getString("cargo"));
			funcionario.setSetor(rs.getString("setor"));
			funcionario.setDtCadastro(rs.getDate("dtCadastro"));
			funcionario.setAtivo(rs.getBoolean("ativo"));
		}
		rs.close();
		ps.close();
		
		return listaFuncionario;
	}
}
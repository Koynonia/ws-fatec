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

import edu.pousada.entity.Pessoa;

public class PessoaDAOImpl implements PessoaDAO{

	private Connection con = DBUtil.getInstance().getConnection();

	/**
	 * CREATE TABLE pessoa (
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * nome VARCHAR(200) NOT NULL,
	 * dtNasc DATE NOT NULL,
	 * responsavel VARCHAR(200) NOT NULL
	 * );
	 */

	public void adicionaPessoa(Pessoa pessoa) throws SQLException {
		String sql = "INSERT INTO pessoa VALUES (NULL,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, pessoa.getNome());
		ps.setDate(2, new java.sql.Date( pessoa.getDtNasc().getTime() ));
		ps.setString(3, pessoa.getResponsavel());
		ps.execute();
		ps.close();
	}

	public void alteraPessoa(Pessoa pessoa) throws SQLException {
		String sql =  "UPDATE pessoa SET nome = ?, dtNasc = ?, responsavel = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, pessoa.getNome());
		ps.setDate(2, new java.sql.Date( pessoa.getDtNasc().getTime() ));
		ps.setString(3, pessoa.getResponsavel());
		ps.setInt(4, pessoa.getId());
		ps.execute();
		ps.close();		
	}

	public void excluiPessoa(Pessoa pessoa) throws SQLException {
		String sql = "DELETE pessoa WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, pessoa.getId());
		ps.execute();
		ps.close();

	}

	public Pessoa consultaPessoa(Pessoa pessoa) throws SQLException {
		String sql = "SELECT * FROM pessoa WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, pessoa.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setDtNasc(rs.getDate("dtNasc"));
			pessoa.setResponsavel(rs.getString("responsavel"));
		}
		rs.close();
		ps.close();
		return pessoa;
	}

	public List<Pessoa> listaPessoa() throws SQLException {
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM pessoa";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setDtNasc(rs.getDate("dtNasc"));
			pessoa.setResponsavel(rs.getString("responsavel"));
			listaPessoa.add(pessoa);
		}
		rs.close();
		ps.close();
		return listaPessoa;
	}
}
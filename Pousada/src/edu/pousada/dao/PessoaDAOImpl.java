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
	 * responsavel VARCHAR(200) NOT NULL,
	 * dtCadastro DATE NOT NULL
	 * ) ENGINE = innodb;
	 */

	@Override
	public void adicionar(Pessoa obj) throws SQLException {
		
		String sql = "INSERT INTO pessoa VALUES (NULL,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getNome());
		ps.setDate(2, new java.sql.Date( obj.getDtNasc().getTime() ));
		ps.setString(3, obj.getResponsavel());
		ps.setDate(4, new java.sql.Date( obj.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Pessoa obj) throws SQLException {
		
		String sql =  "UPDATE pessoa SET "
				+ "nome = ?, "
				+ "dtNasc = ?, "
				+ "responsavel = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, obj.getNome());
		ps.setDate(2, new java.sql.Date( obj.getDtNasc().getTime() ));
		ps.setString(3, obj.getResponsavel());
		ps.setInt(4, obj.getId());
		ps.execute();
		ps.close();		
	}

	@Override
	public void excluir(Pessoa obj) throws SQLException {
		
		String sql = "DELETE FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Pessoa consultar(Pessoa obj) throws SQLException {
		
		String sql = "SELECT * FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			obj.setId(rs.getInt("id"));
			obj.setNome(rs.getString("nome"));
			obj.setDtNasc(rs.getDate("dtNasc"));
			obj.setResponsavel(rs.getString("responsavel"));
			obj.setDtCadastro(rs.getDate("dtCadastro"));
		}
		rs.close();
		ps.close();
		return obj;
	}

	@Override
	public List<Pessoa> todos() throws SQLException {
		
		String sql = "SELECT * FROM pessoa";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Pessoa> lista = new ArrayList<Pessoa>();
		while(rs.next()){
			Pessoa p = new Pessoa();
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setDtNasc(rs.getDate("dtNasc"));
			p.setResponsavel(rs.getString("responsavel"));
			p.setDtCadastro(rs.getDate("dtCadastro"));
			lista.add(p);
		}
		rs.close();
		ps.close();
		return lista;
	}
}
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

	@Override
	public void adicionar(Pessoa p) throws SQLException {
		
		String sql = "INSERT INTO pessoa VALUES (NULL,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.setDate(2, new java.sql.Date( p.getDtNasc().getTime() ));
		ps.setString(3, p.getResponsavel());
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Pessoa p) throws SQLException {
		
		String sql =  "UPDATE pessoa SET "
				+ "nome = ?, "
				+ "dtNasc = ?, "
				+ "responsavel = ? "
				+ "WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.setDate(2, new java.sql.Date( p.getDtNasc().getTime() ));
		ps.setString(3, p.getResponsavel());
		ps.setInt(4, p.getId());
		ps.execute();
		ps.close();		
	}

	@Override
	public void excluir(Pessoa p) throws SQLException {
		
		String sql = "DELETE FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Pessoa consultar(Pessoa p) throws SQLException {
		
		String sql = "SELECT * FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setDtNasc(rs.getDate("dtNasc"));
			p.setResponsavel(rs.getString("responsavel"));
		}
		rs.close();
		ps.close();
		return p;
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
			lista.add(p);
		}
		rs.close();
		ps.close();
		return lista;
	}
}
package edu.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.curso.entidade.Time;

public class TimeDAOImpl implements TimeDAO {
	private Connection con;

	public TimeDAOImpl() { 
		con = DBUtil.getInstance().getConn();
	}

	@Override
	public void adicionar(Time t) {
		String sql ="INSERT INTO times (id, nome, modalidade, mascote) " + 
				"VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setLong(1, t.getId());
			psmt.setString(2, t.getNome());
			psmt.setString(3,  t.getModalidade());
			psmt.setString(4,  t.getMascote());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Time> pesquisarPorNome(String nome) {
		List<Time> times = new ArrayList<Time>();
		String sql ="SELECT id, nome, modalidade, mascote FROM times " + 
				"WHERE nome like ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%" + nome + "%");
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Time t = new Time();
				t.setId( rs.getLong("id") );
				t.setMascote( rs.getString("mascote") );
				t.setModalidade( rs.getString("modalidade") );
				t.setNome( rs.getString("nome") );
				times.add( t );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return times;
	}

}

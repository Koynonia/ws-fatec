package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Moradores;

public class MoradoresDao implements IMoradoresDao{
	
	private Connection c;
	
	public MoradoresDao(){
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void insereMorador(Moradores morador) throws SQLException {
		String sql = "INSERT INTO morador (nome, telefone) VALUES (?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, morador.getNome());
		ps.setString(2, morador.getTelefone());
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizaMorador(Moradores morador) throws SQLException {
		String sql = "UPDATE morador SET nome = ?, telefone = ? WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, morador.getNome());
		ps.setString(2, morador.getTelefone());
		ps.setInt(3, morador.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiMorador(Moradores morador) throws SQLException {
		String sql = "DELETE morador WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, morador.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public List<Moradores> consultaMoradores() throws SQLException {
		String sql = "SELECT id, nome, telefone FROM morador";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Moradores> ListaMoradores = new ArrayList<Moradores>();
		while (rs.next()) {
			Moradores m = new Moradores();
			m.setId(rs.getInt("id"));
			m.setNome(rs.getString("nome"));
			m.setTelefone(rs.getString("telefone"));
			ListaMoradores.add(m);
		}
		rs.close();
		ps.close();
		return ListaMoradores;
	}

	@Override
	public Moradores consultaMoradores(Moradores morador) throws SQLException {
		String sql = "SELECT id, nome, telefone FROM morador WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, morador.getId());
		ResultSet rs = ps.executeQuery();
		Moradores m = new Moradores();
		if (rs.next()) {
			m.setId(rs.getInt("id"));
			m.setNome(rs.getString("nome"));
			m.setTelefone(rs.getString("telefone"));
		}
		rs.close();
		ps.close();
		return m;
	}

}

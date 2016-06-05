package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Apartamentos;
import model.Moradores;

public class ApartamentosDao implements IApartamentosDao {
	
	private Connection c;
	
	public ApartamentosDao(){
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void insereApartamento(Apartamentos apartamento) throws SQLException {
		String sql = "INSERT INTO apartamento (numero, quartos, ocupacao, id_morador) VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, apartamento.getNumero());
		ps.setInt(2, apartamento.getQuartos());
		ps.setString(3, apartamento.getOcupacao());
		ps.setInt(4, apartamento.getId_morador());
		ps.execute();
		ps.close();
		
	}

	@Override
	public void atualizaApartamento(Apartamentos apartamento) throws SQLException {
		String sql = "UPDATE apartamento SET numero = ?, quartos = ?, ocupacao = ?, id_morador = ? WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, apartamento.getNumero());
		ps.setInt(2, apartamento.getQuartos());
		ps.setString(3, apartamento.getOcupacao());
		ps.setInt(4, apartamento.getId_morador());
		ps.setInt(5, apartamento.getId());
		
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiApartamento(Apartamentos apartamento) throws SQLException {
		String sql = "DELETE apartamento WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, apartamento.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public List<Apartamentos> consultaApartamentos() throws SQLException {
		String sql = "SELECT id, numero, quartos, ocupacao, id_morador FROM apartamento";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Apartamentos> ListaApartamentos = new ArrayList<Apartamentos>();
		while (rs.next()) {
			Apartamentos ape = new Apartamentos();
			Moradores mor = new Moradores();
			ape.setId(rs.getInt("id"));
			ape.setNumero(rs.getInt("numero"));
			ape.setQuartos(rs.getInt("quartos"));
			ape.setOcupacao(rs.getString("ocupacao"));
			ape.setId_morador(rs.getInt("id_morador"));
			ListaApartamentos.add(ape);
		}
		rs.close();
		ps.close();
		return ListaApartamentos;
	}

	@Override
	public Apartamentos consultaApartamento(Apartamentos apartamento) throws SQLException {
		String sql = "SELECT id, numero, quartos, ocupacao, id_morador FROM apartamento WHERE numero = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, apartamento.getNumero());
		ResultSet rs = ps.executeQuery();
		Apartamentos ape = new Apartamentos();
		if (rs.next()) {
			ape.setId(rs.getInt("id"));
			ape.setNumero(rs.getInt("numero"));
			ape.setQuartos(rs.getInt("quartos"));
			ape.setOcupacao(rs.getString("ocupacao"));
			ape.setId_morador(rs.getInt("id_morador"));
		}
		rs.close();
		ps.close();
		return ape;
	}

}

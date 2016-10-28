/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Reserva;

public class ReservaDAOImpl implements ReservaDAO{

	private Connection con = DBUtil.getInstance().getConnection();
	
	/**
	 * CREATE TABLE reserva (
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * cliente VARCHAR(50) NOT NULL,
	 * chale INT NOT NULL,
	 * qtdAdulto INT NOT NULL,
	 * qtdCrianca INT NOT NULL,
	 * dtInicio DATE NOT NULL,
	 * dtFim DATE NOT NULL, 
	 * desconto INT NOT NULL,
	 * dtCadastro DATE NOT NULL
	 * );
	 */
	
	@Override
	public void adicionaReserva(Reserva reserva) throws SQLException {
		String sql = "INSERT INTO reserva VALUES (NULL,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, reserva.getCliente().getDocumento());
		ps.setInt(2, reserva.getChale().getId());
		ps.setInt(3, reserva.getQtdAdulto());
		ps.setInt(4, reserva.getQtdCrianca());
		ps.setDate(5, new java.sql.Date( reserva.getDtInicio().getTime() ));
		ps.setDate(6, new java.sql.Date( reserva.getDtFim().getTime() ));
		ps.setInt(7, reserva.getDesconto());
		ps.setDate(8, new java.sql.Date( reserva.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alteraReserva(Reserva reserva) throws SQLException {
		String sql =  "UPDATE reserva SET cliente = ?, "
				+ "chale = ?, qtdAdulto = ?, qtdCrianca = ?, "
				+ "dtInicio = ?, dtFim = ?, "
				+ "desconto = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, reserva.getCliente().getDocumento());
		ps.setInt(2, reserva.getChale().getId());
		ps.setInt(3, reserva.getQtdAdulto());
		ps.setInt(4, reserva.getQtdCrianca());
		ps.setDate(5, new java.sql.Date( reserva.getDtInicio().getTime() ));
		ps.setDate(6, new java.sql.Date( reserva.getDtFim().getTime() ));
		ps.setInt(7, reserva.getDesconto());
		ps.setInt(8, reserva.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiReserva(Reserva reserva) throws SQLException {
		String sql = "DELETE reserva WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, reserva.getId() );
		ps.execute();
		ps.close();	
	}

	@Override
	public Reserva consultaReserva(Reserva reserva) throws SQLException {
		String sql = "SELECT * FROM reserva WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, reserva.getId() );
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			reserva.setId(rs.getInt("id"));
			//reserva.setCliente(rs.getString("cliente"));
			//reserva.setChale(rs.getString("chale"));
			reserva.setQtdAdulto(rs.getInt("qtdAdulto"));
			reserva.setQtdCrianca(rs.getInt("qtdCrianca"));
			reserva.setDtInicio(rs.getDate("dtInicio"));
			reserva.setDtFim(rs.getDate("dtFim"));
			reserva.setDesconto(rs.getInt("desconto"));
			reserva.setDtCadastro(rs.getDate("dtCadastro"));
		}
		rs.close();
		ps.close();
		return reserva;
	}

	@Override
	public List<Reserva> todasReservas() throws SQLException {
		String sql = "SELECT * FROM reserva";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Reserva>lista = new ArrayList<>();
		while (rs.next()) {
			Reserva reservas = new Reserva();
			reservas.setId(rs.getInt("id"));
			//reservas.setCliente(rs.getString("cliente"));
			//reserva.setChale(rs.getString("chale"));
			reservas.setQtdAdulto(rs.getInt("qtdAdulto"));
			reservas.setQtdCrianca(rs.getInt("qtdCrianca"));
			reservas.setDtInicio(rs.getDate("dtInicio"));
			reservas.setDtFim(rs.getDate("dtFim"));
			reservas.setDesconto(rs.getInt("desconto"));
			reservas.setDtCadastro(rs.getDate("dtCadastro"));
			lista.add( reservas );
		}
		rs.close();
		ps.close();
		return lista;
	}
}
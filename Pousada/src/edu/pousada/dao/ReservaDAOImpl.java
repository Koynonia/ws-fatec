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
	 * numero INT PRIMARY KEY,
	 * cliente VARCHAR(50) NOT NULL,
	 * chale INT NOT NULL,
	 * qtdAdulto INT NOT NULL,
	 * qtdCrianca INT NOT NULL,
	 * quantidade INT NOT NULL,
	 * dtIncio DATE NOT NULL,
	 * dtFim DATE NOT NULL, 
	 * desconto INT NOT NULL,
	 * dtCadastro DATE NOT NULL
	 * );
	 */
	
	@Override
	public void adicionaReserva(Reserva reserva) throws SQLException {
		String sql = "INSERT INTO reserva VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, reserva.getNumero());
		ps.setString(2, reserva.getCliente().getDocumento());
		ps.setInt(3, reserva.getChale().getNumero());
		ps.setInt(4, reserva.getQtdAdulto());
		ps.setInt(5, reserva.getQtdCrianca());
		ps.setInt(6, reserva.getQuantidade());
		ps.setDate(7, new java.sql.Date( reserva.getDtInicio().getTime() ));
		ps.setDate(8, new java.sql.Date( reserva.getDtFim().getTime() ));
		ps.setInt(9, reserva.getDesconto());
		ps.setDate(10, new java.sql.Date( reserva.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alteraReserva(Reserva reserva) throws SQLException {
		String sql =  "UPDATE reserva SET numero = ?, cliente = ?, "
				+ "chale = ?, qtdAdulto = ?, qtdCrianca = ?, "
				+ "qtdAdulto = ?, qtdCrianca = ?, quantidade = ?, "
				+ "dtInicio = ?, dtFim = ?, desconto = ? "
				+ "dtCadastro = ? WHERE numero = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, reserva.getNumero());
		ps.setString(2, reserva.getCliente().getDocumento());
		ps.setInt(4, reserva.getQtdAdulto());
		ps.setInt(5, reserva.getQtdCrianca());
		ps.setInt(6, reserva.getQuantidade());
		ps.setDate(7, new java.sql.Date( reserva.getDtInicio().getTime() ));
		ps.setDate(8, new java.sql.Date( reserva.getDtFim().getTime() ));
		ps.setInt(9, reserva.getDesconto());
		ps.setDate(10, new java.sql.Date( reserva.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiReserva(Reserva reserva) throws SQLException {
		String sql = "DELETE reserva WHERE numero = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, reserva.getNumero() );
		ps.execute();
		ps.close();	
	}

	@Override
	public Reserva consultaReserva(Reserva reserva) throws SQLException {
		String sql = "SELECT * FROM reserva WHERE num = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, reserva.getNumero() );
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			reserva.setNumero(rs.getInt("numero"));
			//reserva.setCliente(rs.getString("cliente"));
			//reserva.setChale(rs.getString("chale"));
			reserva.setQtdAdulto(rs.getInt("qtdAdulto"));
			reserva.setQtdCrianca(rs.getInt("qtdCrianca"));
			reserva.setQuantidade(rs.getInt("quantidade"));
			reserva.setDtInicio(rs.getDate("dtInicio"));
			reserva.setDtFim(rs.getDate("dtFim"));
			reserva.setDesconto(rs.getInt("desconto"));
			reserva.setDtCadastro(rs.getDate("dtCadastro"));
		}
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
			Reserva itens = new Reserva();
			lista.add( itens );
		}
		rs.close();
		ps.close();
		return lista;
	}
}
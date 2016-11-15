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

import edu.pousada.entity.Chale;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Reserva;

public class ReservaDAOImpl implements ReservaDAO{

	private Connection con = DBUtil.getInstance().getConnection();

	/**
	 * CREATE TABLE reserva (
	 * id INT AUTO_INCREMENT PRIMARY KEY,
	 * cliente INT NOT NULL,
	 * chale INT NOT NULL,
	 * qtdAdulto INT NOT NULL,
	 * qtdCrianca INT NOT NULL,
	 * dtInicio DATE NOT NULL,
	 * dtFim DATE NOT NULL, 
	 * mensagem VARCHAR(300),
	 * desconto INT NOT NULL,
	 * ativa BOOLEAN NOT NULL,
	 * dtCadastro DATE NOT NULL
	 * ) ENGINE = innodb;
	 */

	@Override 
	public void adicionar(Reserva obj) throws SQLException {

		String sql = "INSERT INTO reserva VALUES (NULL,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getCliente().getId());
		ps.setInt(2, obj.getChale().getId());
		ps.setInt(3, obj.getQtdAdulto());
		ps.setInt(4, obj.getQtdCrianca());
		ps.setDate(5, new java.sql.Date( obj.getDtInicio().getTime() ));
		ps.setDate(6, new java.sql.Date( obj.getDtFim().getTime() ));
		ps.setString(7, obj.getMensagem());
		ps.setInt(8, obj.getDesconto());
		ps.setBoolean(9, obj.getAtiva() );
		ps.setDate(10, new java.sql.Date( obj.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Reserva obj) throws SQLException {

		String sql =  "UPDATE reserva SET "
				+ "cliente = ?, "
				+ "chale = ?, "
				+ "qtdAdulto = ?, "
				+ "qtdCrianca = ?, "
				+ "dtInicio = ?, "
				+ "dtFim = ?, "
				+ "mensagem = ?, "
				+ "desconto = ?,  "
				+ "ativa = ? "
				+ "WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, obj.getCliente().getId());
		ps.setInt(2, obj.getChale().getId());
		ps.setInt(3, obj.getQtdAdulto());
		ps.setInt(4, obj.getQtdCrianca());
		ps.setDate(5, new java.sql.Date( obj.getDtInicio().getTime() ));
		ps.setDate(6, new java.sql.Date( obj.getDtFim().getTime() ));
		ps.setString(7, obj.getMensagem());
		ps.setInt(8, obj.getDesconto());
		ps.setBoolean(9, obj.getAtiva() );
		ps.setInt(10, obj.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Reserva obj) throws SQLException {

		String sql = "DELETE FROM reserva WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, obj.getId() );
		ps.execute();
		ps.close();

		excluirCliente();
	}

	@Override
	public Reserva consultar(Reserva obj) throws SQLException {

		String sql = "SELECT * FROM reserva WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, obj.getId() );
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			obj.setId(rs.getInt("id"));
			obj.setCliente( cliente( rs.getInt("cliente") ));
			obj.setChale( chale( rs.getInt("chale") ));
			obj.setQtdAdulto(rs.getInt("qtdAdulto"));
			obj.setQtdCrianca(rs.getInt("qtdCrianca"));
			obj.setDtInicio(rs.getDate("dtInicio"));
			obj.setDtFim(rs.getDate("dtFim"));
			obj.setMensagem(rs.getString("mensagem"));
			obj.setDesconto(rs.getInt("desconto"));
			obj.setAtiva(rs.getBoolean("ativa"));
			obj.setDtCadastro(rs.getDate("dtCadastro"));
		}
		rs.close();
		ps.close();
		return obj;
	}

	@Override
	public List<Reserva> todos() throws SQLException {

		String sql = "SELECT * FROM reserva";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Reserva>lista = new ArrayList<>();
		while (rs.next()) {
			Reserva r = new Reserva();
			r.setId(rs.getInt("id"));
			r.setCliente( cliente( rs.getInt("cliente") ));
			r.setChale( chale( rs.getInt("chale") ));
			r.setQtdAdulto(rs.getInt("qtdAdulto"));
			r.setQtdCrianca(rs.getInt("qtdCrianca"));
			r.setDtInicio(rs.getDate("dtInicio"));
			r.setDtFim(rs.getDate("dtFim"));
			r.setMensagem(rs.getString("mensagem"));
			r.setDesconto(rs.getInt("desconto"));
			r.setAtiva(rs.getBoolean("ativa"));
			r.setDtCadastro(rs.getDate("dtCadastro"));
			lista.add( r );
		}
		rs.close();
		ps.close();

		return lista;
	}

	public Cliente cliente( Integer id ) throws SQLException{

		Cliente idCliente = new Cliente();
		Cliente cliente = new Cliente();
		idCliente.setId(id);

		ClienteDAO dao = new ClienteDAOImpl();
		cliente = dao.consultar( idCliente );

		return cliente;
	}

	public void excluirCliente() throws SQLException{

		String sql = "DELETE cliente FROM cliente "
				+ "LEFT OUTER JOIN reserva "
				+ "ON cliente.id = reserva.cliente "
				+ "WHERE reserva.cliente IS NULL AND cliente.ativo = 0";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
		ps.close();
	}

	public Chale chale( Integer id ) throws SQLException{

		Chale idChale = new Chale();
		Chale chale = new Chale();
		idChale.setId(id);

		ChaleDAO dao = new ChaleDAOImpl();
		chale = dao.consultar( idChale );

		return chale;
	}

	public int chaleDisponivel( Reserva r ) throws SQLException{

		int count = 0;

		String sql = "SELECT reserva.id "
				+ "FROM chale "
				+ "INNER JOIN reserva "
				+ "ON chale.id = reserva.chale "
				+ "WHERE ( "
				+ "(reserva.dtInicio BETWEEN ? AND ?) OR "
				+ "(reserva.dtFim BETWEEN ? AND ?) OR "
				+ "(? BETWEEN reserva.dtInicio AND reserva.dtFim) OR "
				+ "(? BETWEEN reserva.dtInicio AND reserva.dtFim )"
				+ ") AND reserva.chale = ?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setDate( 1, new java.sql.Date ( r.getDtInicio().getTime() ));
		ps.setDate( 2, new java.sql.Date ( r.getDtFim().getTime() ));
		ps.setDate( 3, new java.sql.Date ( r.getDtInicio().getTime() ));
		ps.setDate( 4, new java.sql.Date ( r.getDtFim().getTime() ));
		ps.setDate( 5, new java.sql.Date ( r.getDtInicio().getTime() ));
		ps.setDate( 6, new java.sql.Date ( r.getDtFim().getTime() ));
		ps.setInt( 7, r.getChale().getId() );
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			count++;
		}
		rs.close();
		ps.close();

		return count;	
	}
}
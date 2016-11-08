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
	public void adicionar(Reserva r) throws SQLException {

		String sql = "INSERT INTO reserva VALUES (NULL,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, r.getCliente().getId());
		ps.setInt(2, r.getChale().getId());
		ps.setInt(3, r.getQtdAdulto());
		ps.setInt(4, r.getQtdCrianca());
		ps.setDate(5, new java.sql.Date( r.getDtInicio().getTime() ));
		ps.setDate(6, new java.sql.Date( r.getDtFim().getTime() ));
		ps.setString(7, r.getMensagem());
		ps.setInt(8, r.getDesconto());
		ps.setBoolean(9, r.getAtiva() );
		ps.setDate(10, new java.sql.Date( r.getDtCadastro().getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void alterar(Reserva r) throws SQLException {

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
		ps.setInt(1, r.getCliente().getId());
		ps.setInt(2, r.getChale().getId());
		ps.setInt(3, r.getQtdAdulto());
		ps.setInt(4, r.getQtdCrianca());
		ps.setDate(5, new java.sql.Date( r.getDtInicio().getTime() ));
		ps.setDate(6, new java.sql.Date( r.getDtFim().getTime() ));
		ps.setString(7, r.getMensagem());
		ps.setInt(8, r.getDesconto());
		ps.setBoolean(9, r.getAtiva() );
		ps.setInt(10, r.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Reserva r) throws SQLException {

		String sql = "DELETE FROM reserva WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, r.getId() );
		ps.execute();
		ps.close();

		excluirCliente();
	}

	@Override
	public Reserva consultar(Reserva r) throws SQLException {

		String sql = "SELECT * FROM reserva WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, r.getId() );
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
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
		}
		rs.close();
		ps.close();
		return r;
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

	public Cliente cliente(Integer id) throws SQLException {

		String sql = "SELECT * FROM cliente AS cl "
				+ "INNER JOIN reserva AS rs "
				+ "ON cl.id = rs.cliente "
				+ "WHERE cl.id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, id );
		ResultSet rs = ps.executeQuery();
		Cliente cl = new Cliente();
		while (rs.next()) {
			cl.setId(rs.getInt("id"));
			cl.setNome(rs.getString("nome"));
			cl.setEmail(rs.getString("email"));
			cl.setDocumento(rs.getString("documento"));
			cl.setDocTipo(rs.getString("docTipo"));
			cl.setDtNasc(rs.getDate("dtNasc"));
			cl.setTelefone(rs.getString("telefone"));
			cl.setCelular(rs.getString("celular"));
			cl.setEndereco(rs.getString("endereco"));
			cl.setBairro(rs.getString("bairro"));
			cl.setCidade(rs.getString("cidade"));
			cl.setEstado(rs.getString("estado"));
			cl.setPais(rs.getString("pais"));
			cl.setCep(rs.getString("cep"));
			cl.setDtCadastro(rs.getDate("dtCadastro"));
			cl.setAtivo(rs.getBoolean("ativo"));
		}
		rs.close();
		ps.close();

		return cl;
	}

	public Chale chale(Integer id) throws SQLException {

		String sql = "SELECT * FROM chale AS ch "
				+ "INNER JOIN reserva AS rs "
				+ "ON ch.id = ?";	

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, id );
		ResultSet rs = ps.executeQuery();
		Chale ch = new Chale();
		while (rs.next()) {
			ch.setId(rs.getInt("id"));
			ch.setCategoria(rs.getString("categoria"));
			ch.setDiaria(rs.getFloat("diaria"));
		}
		rs.close();
		ps.close();

		return ch;
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

	//Função que verifica se Chalé está disponivel naquela data
	public int chaleDisponivelPelaData( Reserva r ) throws SQLException{
		
		int disponivel = 0;
		
		String sql = "SELECT fn_reserva2(?, ?, ?) AS disponivel";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt( 1, r.getChale().getId() );
		ps.setDate( 2, new java.sql.Date ( r.getDtInicio().getTime() ));
		ps.setDate( 3, new java.sql.Date ( r.getDtFim().getTime() ));
		ResultSet rs = ps.executeQuery();	

		if( rs.next() ){
			disponivel = rs.getInt("disponivel");
		}
		rs.close();
		ps.close();
		
		return disponivel;
	}
}
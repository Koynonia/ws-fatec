/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.DataBases;
import model.Grupo;
import model.Jogo;
import model.Resultado;
import model.Times;

public class CampeonatoDAOImpl implements CampeonatoDAO {

	Connection con;

	public CampeonatoDAOImpl() {
		GenericDAO gDao = new GenericDAOImpl();
		con = gDao.getConnection();
	}

	public Times time(int codigoTime) throws SQLException {

		String sql = "SELECT * FROM times WHERE codigoTime = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, codigoTime);
		ResultSet rs = ps.executeQuery();
		Times time = new Times();
		if (rs.next()) {
			time.setCodigoTime(rs.getInt("codigoTime"));
			time.setNomeTime(rs.getString("nomeTime"));
			time.setCidade(rs.getString("cidade"));
			time.setEstadio(rs.getString("estadio"));
		}
		return time;
	}

	@Override
	public void geraGrupos(int trigger) throws SQLException {

		String sql = "{CALL sp_grupos(?)}";

		CallableStatement cs = con.prepareCall(sql);
		cs.setInt(1, trigger);
		cs.execute();
		cs.close();
	}

	@Override
	public List<Grupo> consultaGrupos() throws SQLException {

		String sql = "SELECT  g.Grupo, t.NomeTime " + "FROM Times AS t " + "INNER JOIN Grupos AS g "
				+ "ON g.CodigoTime = t.CodigoTime " + "GROUP BY g.Grupo, t.NomeTime "
				+ "ORDER BY g.Grupo ASC, t.NomeTime DESC";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Grupo> lista = new ArrayList<Grupo>();
		while (rs.next()) {
			Grupo g = new Grupo();
			g.setGrupo(rs.getString("Grupo"));
			g.setTime(rs.getString("NomeTime"));
			lista.add(g);
		}
		rs.close();
		ps.close();

		return lista;
	}

	@Override
	public void apagaGrupos() throws SQLException {

		String sql = "DELETE FROM Grupos";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
		ps.close();
	}

	@Override
	public void geraJogos(Date dtInicio, int trigger) throws SQLException {

		String sql = "{CALL sp_jogos(?,?)}";

		CallableStatement cs = con.prepareCall(sql);
		long num = dtInicio.getTime();
		java.sql.Date data = new java.sql.Date(num);
		cs.setDate(1, data);
		cs.setInt(2, trigger);
		cs.execute();
		cs.close();
	}

	@Override
	public void atualizaJogos(List<Jogo> gols) throws SQLException {

		String sql = "UPDATE Jogos SET " + "GolsTimeA = ?, " + "GolsTimeB = ? "
				+ "WHERE CodigoTimeA = ? AND CodigoTimeB = ? AND Data = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		for (int i = 0; i < gols.size(); i++) {
			ps.setInt(1, gols.get(i).getGolTimeA());
			ps.setInt(2, gols.get(i).getGolTimeB());
			ps.setInt(3, gols.get(i).getCodigoTimeA());
			ps.setInt(4, gols.get(i).getCodigoTimeB());
			ps.setDate(5, new java.sql.Date(gols.get(i).getData().getTime()));
			ps.execute();
		}

		ps.close();
	}

	@Override
	public List<Jogo> consultaJogos() throws SQLException {

		String sql = "SELECT j.Data, j.CodigoTimeA as CodA, a.NomeTime  as 'Time A', j.CodigoTimeB  as CodB, b.NomeTime as 'Time B' " 
				+ "FROM Jogos j "
				+ "INNER JOIN Times a " 
				+ "ON a.CodigoTime = j.CodigoTimeA " 
				+ "INNER JOIN Times b "
				+ "ON b.CodigoTime = j.CodigoTimeB";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Jogo> lista = new ArrayList<Jogo>();
		while (rs.next()) {
			Jogo j = new Jogo();
			j.setData(rs.getDate("Data"));
			j.setCodigoTimeA(rs.getInt("CodA"));
			j.setCodigoTimeB(rs.getInt("CodB"));
			j.setTimeA(rs.getString("Time A")); 
			j.setTimeB(rs.getString("Time B"));
			lista.add(j);
		}

		rs.close();
		ps.close();

		return lista;
	}

	@Override
	public List<Jogo> consultaDataJogos(Date dtPesq) throws SQLException {

		String sql = "";
		if (dtPesq != null) {
			sql = "SELECT j.Data, j.CodigoTimeA as CodA, a.NomeTime  as 'Time A', j.CodigoTimeB  as CodB, b.NomeTime as 'Time B' "
					+ "FROM Jogos j "
					+ "INNER JOIN Times a "
					+ "ON a.CodigoTime = j.CodigoTimeA "
					+ "INNER JOIN Times b "
					+ "ON b.CodigoTime = j.CodigoTimeB "
					+ "WHERE j.Data = ?";
		} else {
			sql = "SELECT j.Data, j.CodigoTimeA as CodA, a.NomeTime  as 'Time A', j.CodigoTimeB  as CodB, b.NomeTime as 'Time B' "
					+ "FROM Jogos j "
					+ "INNER JOIN Times a "
					+ "ON a.CodigoTime = j.CodigoTimeA "
					+ "INNER JOIN Times b "
					+ "ON b.CodigoTime = j.CodigoTimeB ";
		}
		PreparedStatement ps = con.prepareStatement(sql);
		if (dtPesq != null) {
			long num = dtPesq.getTime();
			java.sql.Date data = new java.sql.Date(num);
			ps.setDate(1, data);
		}
		ResultSet rs = ps.executeQuery();
		List<Jogo> lista = new ArrayList<Jogo>();
		while (rs.next()) {
			Jogo j = new Jogo();
			j.setData(rs.getDate("Data"));
			j.setCodigoTimeA(rs.getInt("CodA"));
			j.setCodigoTimeB(rs.getInt("CodB"));
			j.setTimeA(rs.getString("Time A")); 
			j.setTimeB(rs.getString("Time B"));
			lista.add(j);
		}
		rs.close();
		ps.close();

		return lista;
	}

	@Override
	public void apagaJogos() throws SQLException {

		String sql = "DELETE FROM Jogos";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
		ps.close();
	}

	@Override
	public List<Resultado> resultadoGrupo(String grupo) throws SQLException {

		String sql = "SELECT * FROM fn_grupos(?) ORDER BY pontos DESC";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, grupo);
		ResultSet rs = ps.executeQuery();
		List<Resultado> lista = new ArrayList<Resultado>();
		while (rs.next()) {
			Resultado r = new Resultado();
			r.setNomeTime(rs.getString("nome_time"));
			r.setJogosDisputados(rs.getInt("num_jogos_disputados"));
			r.setVitorias(rs.getInt("vitorias"));
			r.setEmpates(rs.getInt("empates"));
			r.setDerrotas(rs.getInt("derrotas"));
			r.setGolsMarcados(rs.getInt("gols_marcados"));
			r.setGolsSofridos(rs.getInt("gols_sofridos"));
			r.setSaldoGols(rs.getInt("saldo_gols"));
			r.setPontos(rs.getInt("pontos"));
			lista.add(r);
		}
		return lista;
	}

	@Override
	public List<Resultado> resultadoGeral() throws SQLException {

		String sql = "SELECT * FROM fn_campeonato() ";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Resultado> lista = new ArrayList<Resultado>();
		while (rs.next()) {
			Resultado c = new Resultado();
			c.setNomeTime(rs.getString("nome_time"));
			c.setJogosDisputados(rs.getInt("num_jogos_disputados"));
			c.setVitorias(rs.getInt("vitorias"));
			c.setEmpates(rs.getInt("empates"));
			c.setDerrotas(rs.getInt("derrotas"));
			c.setGolsMarcados(rs.getInt("gols_marcados"));
			c.setGolsSofridos(rs.getInt("gols_sofridos"));
			c.setSaldoGols(rs.getInt("saldo_gols"));
			c.setPontos(rs.getInt("pontos"));
			lista.add(c);
		}
		return lista;
	}

	@Override
	public List<Times> quartasdeFinal() throws SQLException {

		String sql = "SELECT * FROM fn_quartaFinal()";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Times> listaTimes = new ArrayList<>();
		while (rs.next()) {
			Times time = new Times();
			time = time(rs.getInt("codigoTime1"));
			listaTimes.add(time);
		}
		return listaTimes;
	}
	
	
	@Override
	public List<DataBases> listaDatabases() throws SQLException {

		String sql = "SELECT * FROM fn_databases()";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<DataBases> listaDataBases = new ArrayList<>();
		while (rs.next()) {
			DataBases db = new DataBases();
			db.setId(rs.getInt("id"));
			db.setNome(rs.getString("nome"));
			db.setData(rs.getTimestamp("dtCriacao"));
			listaDataBases.add(db);
		}
		return listaDataBases;
	}
	
	
	@Override
	public void backupBases(String path) throws SQLException {

		String sql = "{CALL sp_backupbases(?)}";

		CallableStatement cs = con.prepareCall(sql);
		
		cs.setString(1, path);
		cs.execute();
		cs.close();
	}
	
	
	@Override
	public void backupBase(String path, String base) throws SQLException {

		String sql = "{CALL sp_backupbase(?,?)}";

		CallableStatement cs = con.prepareCall(sql);
		
		cs.setString(1, path);
		cs.setString(2, base);
		cs.execute();
		cs.close();
	}
	
	
	@Override
	public void restauraBase(String path, String base) throws SQLException {

		String sql = "{CALL sp_restaurabase(?,?)}";

		CallableStatement cs = con.prepareCall(sql);
		
		cs.setString(1, path);
		cs.setString(2, base);
		cs.execute();
		cs.close();
	}
}
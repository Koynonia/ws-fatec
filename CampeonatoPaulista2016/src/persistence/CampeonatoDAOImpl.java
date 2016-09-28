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
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Grupo;
import model.Jogo;

public class CampeonatoDAOImpl implements CampeonatoDAO {

	Connection con;
	
	public CampeonatoDAOImpl(){
		GenericDAO gDao = new GenericDAOImpl();
		con = gDao.getConnection();
	}
	
	@Override
	public String geraGrupos() {

		String sql = "{CALL sp_grupos}";
		String saida = "";
		try {
			CallableStatement cs = con.prepareCall(sql);
			//cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			//saida = cs.getString(1);
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saida;
	}

	@Override
	public List<Grupo> consultaGrupos(String grupo) throws CampeonatoDAOException{
		
		List<Grupo> lista = new ArrayList<Grupo>();
		String sql = "SELECT  g.Grupo, t.NomeTime "
					+ "FROM Times AS t "
					+ "INNER JOIN Grupos AS g "
					+ "ON g.CodigoTime = t.CodigoTime "
					+ "GROUP BY g.Grupo, t.NomeTime "
					+ "ORDER BY g.Grupo ASC, t.NomeTime DESC";
		try {
				PreparedStatement pst = con.prepareStatement( sql );
				//pst.setString( 1, grupo );
				ResultSet rs = pst.executeQuery();
				while( rs.next() ) { 
					Grupo g = new Grupo();
					g.setGrupo( rs.getString( "Grupo" ));
					g.setTime( rs.getString( "NomeTime" ));
					lista.add( g );
				}
		} catch( SQLException e ) {
			e.printStackTrace();
			throw new CampeonatoDAOException( e );
		}
		return lista;
	}
	
	@Override
	public String geraJogos(Date dtInicio) {

		String sql = "{CALL sp_jogos(?)}";
		String saida = "";
		try {
			CallableStatement cs = con.prepareCall(sql);
			long num = dtInicio.getTime();
			java.sql.Date data = new java.sql.Date(num);
			cs.setDate(1, data);
			//cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			//saida = cs.getString(1);
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saida;
	}

	@Override
	public List<Jogo> consultaJogos() throws CampeonatoDAOException{
		
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "SELECT a.Data, a.NomeTime AS 'Time A', b.NomeTime AS 'Time B' "
				+ "FROM("
				+ "SELECT j.Data, t.NomeTime "
				+ "FROM Jogos AS j "
				+ "INNER JOIN Times AS t "
				+ "ON j.CodigoTimeA = t.CodigoTime) a "
				+ "INNER JOIN("
				+ "SELECT j.Data, t.NomeTime "
				+ "FROM Jogos AS j "
				+ "INNER JOIN Times AS t "
				+ "ON j.CodigoTimeB = t.CodigoTime) b "
				+ "ON a.Data = b.Data ";
		try {
				PreparedStatement pst = con.prepareStatement( sql );
				//pst.setString(1, "%" + data + "%");
				ResultSet rs = pst.executeQuery();
				while( rs.next() ) { 
					Jogo j = new Jogo();
					j.setData( rs.getDate( "Data" ) );
					j.setTimeA( rs.getString( "Time A" ) );
					j.setTimeB( rs.getString( "Time B" ) );
					lista.add( j );
				}
		} catch( SQLException e ) { 
			e.printStackTrace();
			throw new CampeonatoDAOException( e );
		}
		return lista;
	}

	public List<Jogo> consultaDataJogos(Date dtPesq) throws CampeonatoDAOException{
		
		List<Jogo> lista = new ArrayList<Jogo>();
		
		String sql = "SELECT a.Data, a.NomeTime AS 'Time A', b.NomeTime AS 'Time B' "
				+ "FROM("
				+ "SELECT j.Data, t.NomeTime "
				+ "FROM Jogos AS j "
				+ "INNER JOIN Times AS t "
				+ "ON j.CodigoTimeA = t.CodigoTime) a "
				+ "INNER JOIN("
				+ "SELECT j.Data, t.NomeTime "
				+ "FROM Jogos AS j "
				+ "INNER JOIN Times AS t "
				+ "ON j.CodigoTimeB = t.CodigoTime) b "
				+ "ON a.Data = b.Data "
				+ "WHERE a.Data = ?";
		try {
				PreparedStatement pst = con.prepareStatement( sql );
				long num = dtPesq.getTime();
				java.sql.Date data = new java.sql.Date(num);
				pst.setDate(1, data);
				ResultSet rs = pst.executeQuery();
				while( rs.next() ) { 
					Jogo j = new Jogo();
					j.setData( rs.getDate( "Data" ) );
					j.setTimeA( rs.getString( "Time A" ) );
					j.setTimeB( rs.getString( "Time B" ) );
					lista.add( j );
				}
		} catch( SQLException e ) { 
			e.printStackTrace();
			throw new CampeonatoDAOException( e );
		}
		return lista;
	}
}
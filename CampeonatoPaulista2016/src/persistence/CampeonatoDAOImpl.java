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

		String sql = "{CALL sp_grupos(?)}";
		String saida = "";
		try {
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(1);
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saida;
	}

	@Override
	public String geraJogos() {

		String sql = "{CALL sp_jogos(?)}";
		String saida = "";
		try {
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(1);
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
					+ "WHERE g.Grupo LIKE ?"
					+ "GROUP BY g.Grupo, t.NomeTime"
					+ "ORDER BY g.Grupo ASC, t.NomeTime DESC";
		try {
				PreparedStatement pst = con.prepareStatement( sql );
				pst.setString( 1, grupo );
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
	public List<Jogo> consultaJogos(String data) throws CampeonatoDAOException{
		
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "SELECT * FROM Jogos WHERE nome like ?";
		try {
				PreparedStatement pst = con.prepareStatement( sql );
				pst.setString(1, "%" + data + "%");
				ResultSet rs = pst.executeQuery();
				while( rs.next() ) { 
					Jogo j = new Jogo();
					j.setData( rs.getDate( "Data" ) );
					j.setTimeA( rs.getString( "NomeTime" ) );
					j.setTimeB( rs.getString( "NomeTime" ) );
					lista.add( j );
				}
		} catch( SQLException e ) { 
			e.printStackTrace();
			throw new CampeonatoDAOException( e );
		}
		return lista;
	}
	
}
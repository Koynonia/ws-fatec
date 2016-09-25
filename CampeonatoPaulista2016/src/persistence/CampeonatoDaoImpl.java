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

public class CampeonatoDaoImpl implements CampeonatoDao {

	Connection con;
	
	public CampeonatoDaoImpl(){
		GenericDao gDao = new GenericDaoImpl();
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
		String sql = "SELECT * FROM Grupos WHERE nome like ?";
		try {
				PreparedStatement pst = con.prepareStatement( sql );
				pst.setString(1, "%" + grupo + "%");
				ResultSet rs = pst.executeQuery();
				while( rs.next() ) { 
					Grupo g = new Grupo();
					g.setCodTime( rs.getInt("CodigoTime") );
					g.setTime( rs.getString("NomeTime") );
					g.setGrupo( rs.getString("Grupo") );
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
					j.setCodTimeA( rs.getInt("CodigoTimeA") );
					j.setTimeA( rs.getString("NomeTime") );
					j.setCodTimeB( rs.getInt("CodigoTimeB") );
					j.setTimeB( rs.getString("NomeTime") );
					lista.add( j );
				}
		} catch( SQLException e ) { 
			e.printStackTrace();
			throw new CampeonatoDAOException( e );
		}
		return lista;
	}
	
}
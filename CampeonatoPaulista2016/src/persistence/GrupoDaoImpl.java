package persistence;
import java.sql.*;

public class GrupoDaoImpl implements GrupoDao {

	Connection c;
	
	public GrupoDaoImpl(){
		GenericDao gDao = new GenericDaoImpl();
		c = gDao.getConnection();
	}
	
	@Override
	public String informaConsumo() {
		// TODO Auto-generated method stub
		String sql = "{CALL sp_grupos(?)}";
		String saida = "";
		try {
			CallableStatement cs = c.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(1);
			cs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saida;
	}
}
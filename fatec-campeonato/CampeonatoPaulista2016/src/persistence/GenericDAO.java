package persistence;

import java.sql.Connection;

public interface GenericDAO {

	public Connection getConnection();
	public void fechaConexao();
	
}

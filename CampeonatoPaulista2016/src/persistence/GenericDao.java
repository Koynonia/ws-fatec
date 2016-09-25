package persistence;

import java.sql.Connection;

public interface GenericDao {

	public Connection getConnection();
	public void fechaConexao();
	
}

package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {

	private Connection con;

	public Connection getConnection() {

		try {
				Class.forName("org.gjt.mm.mysql.Driver");

				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bancoCondominio", "root", "root");

			} catch (ClassNotFoundException e) {

				e.printStackTrace();

			} catch (SQLException e) {

				e.printStackTrace();

			} catch (Exception e) {

				e.printStackTrace();

			}
		return con;
	}

	public void fechaConexao() {
		try {

			if (con != null)
				con.close();

			con = null;

			} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
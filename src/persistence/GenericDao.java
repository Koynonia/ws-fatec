package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Criado pelo Prof. M.Sc. Leandro Colevati dos Santos
 */
public class GenericDao {

	private Connection con;

	public Connection getConnection() {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
<<<<<<< HEAD
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bancoCondominio", "root", "aluno");
=======
			con = DriverManager.getConnection("jdbc:mysql://"
					+ "127.0.0.1:3306/bancoCondominio",
					"root", "1234");
>>>>>>> eac9c82e59a9437b048973dcdf6a18d4f970775b
//			System.out.println("Conexao ok");
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
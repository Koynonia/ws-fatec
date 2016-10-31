package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAOImpl implements GenericDAO{

	private static Connection con;

	public Connection getConnection() {
		fechaConexao() ;
		sqlServer();
		//mySql();
		
		//System.out.println("Conexao ok");
		return con;
	}

	public void sqlServer(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			String url = "jdbc:sqlserver://10.68.77.25:1433;DatabaseName=campeonato;namedPipe=true";
			String usr = "Fernando";
			String pwd = "12345";
			con = DriverManager.getConnection( url, usr, pwd );
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*	public void mySql(){
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/campeonato";
			String usr = "root";
			String pwd = "root";
			con = DriverManager.getConnection( url, usr, pwd );
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

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

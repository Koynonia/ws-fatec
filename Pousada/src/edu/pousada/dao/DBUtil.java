/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * Realizado em 23/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/pousada";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "mysqlroot";
	private static DBUtil instancia;
	
	private Connection con;
	
	
	private DBUtil() { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBUtil getInstance() { 
		if (instancia == null) { 
			instancia = new DBUtil();
		}
		return instancia;
	}
	
	public Connection getConnection() { 
		return con;
	}
}
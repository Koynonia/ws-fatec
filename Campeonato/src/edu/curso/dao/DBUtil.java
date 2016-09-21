package edu.curso.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String URL = 
			"jdbc:mysql://localhost:3306/champ?autoReconnect=true";
	private static final String USER="root";
	private static final String PASS="aluno";
	private static final String DRIVER="com.mysql.jdbc.Driver";
	
	
	private static DBUtil dbUtil = null;
	
	private Connection con = null;
	
	private DBUtil() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static DBUtil getInstance() { 
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		return dbUtil;		
	}
	
	public Connection getConn() { 
		return con;
	}

}

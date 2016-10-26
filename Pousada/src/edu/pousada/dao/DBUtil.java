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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DBUtil {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/pousada";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "root";
	private static DBUtil instancia;
	
	private Connection con;
	
	
	private DBUtil() { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, 
					"ERRO: " + e.getSQLState() 
					+ "\n\nO servidor de banco de dados pode estar inacessível "
					+ "\nou os parâmetros de conexão estão incorretos."
					+ "\n\nPor favor verifique as configurações na classe DBUtil "
					+ "\nou se o SGDB MySQL está funcional.", 
					"Falha de conexão", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( "../Pousada/resources/icons/errordb.png" ));
			;
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
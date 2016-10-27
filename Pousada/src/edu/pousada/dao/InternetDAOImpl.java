/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Internet;

public class InternetDAOImpl implements InternetDAO{

	private Connection con = DBUtil.getInstance().getConnection();
	
	/**
	 * CREATE TABLE internet(
	 * qtd INT NOT NULL,
	 * -- cartao VARCHAR(100) NOT NULL,
	 * dt DATE NOT NULL,
	 * hora DATE NOT NULL,
	 * valor DECIMAL(7,2) NOT NULL
	 * );
	 */

	public void adicionaInternet(Internet internet) throws SQLException {
		String sql = "INSERT INTO internet VALUES (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, internet.getQuantidade());
		ps.setDate(2, (Date) internet.getData());
		ps.setDate(3, (Date) internet.getHora());
		ps.setFloat(4, internet.getValor());
		
		ps.execute();
		ps.close();
	}

	public void alterarInternet(Internet internet) throws SQLException {
		String sql = "UPDATE lanchonete SET qtd = ?, dt = ?, hora = ?, valor = ? WHERE idInternet = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, internet.getQuantidade());
		ps.setDate(2, (Date) internet.getData());
		ps.setDate(3, (Date) internet.getHora());
		ps.setFloat(4, internet.getValor());
		
		ps.setInt(5, internet.getIdLanchonete());
		ps.execute();
		ps.close();
	}

	public void excluiIntenret(Internet internet) throws SQLException {
		String sql = "DELETE lanchonete WHERE idLanchonete = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, internet.getIdLanchonete());
		
		ps.execute();
		ps.close();
		
	}

	public List<Internet> listaInternet() throws SQLException {
		List<Internet> listaInternet = new ArrayList<Internet>();
		String sql = "SELECT * FROM internet";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Internet internet = new Internet();
			internet.setIdLanchonete(rs.getInt("qtd"));
			internet.setData(rs.getDate("dt"));
			internet.setHora(rs.getDate("hora"));
			internet.setValor(rs.getFloat("valor"));
			listaInternet.add(internet);
			
		}
		
		return listaInternet;
	}
	

}

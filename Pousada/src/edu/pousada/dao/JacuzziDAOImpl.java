/**
 * @author Guilherme Jatobá
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
import edu.pousada.entity.Jacuzzi;

public class JacuzziDAOImpl implements JacuzziDAO {

	private Connection con = DBUtil.getInstance().getConnection();

	/*
	 * qtd INT NOT NULL, -- cartao VARCHAR(100) NOT NULL, dt DATE NOT NULL, hora
	 * DATE NOT NULL, valor FLOAT NOT NULL );
	 */
	public void adicionaJacuzzi(Jacuzzi jacuzzi) throws SQLException {
		String sql = "INSERT INTO jacuzzi VALUES (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, jacuzzi.getQuantidade());
		ps.setDate(2, (Date) jacuzzi.getData());
		ps.setDate(3, (Date) jacuzzi.getHora());
		ps.setFloat(4, jacuzzi.getValor());

		ps.execute();
		ps.close();

	}

	@Override
	public void alteraJacuzzi(Jacuzzi jacuzzi) throws SQLException {
		String sql = "UPDATE jacuzzki SET qtd = ?, dt = ?, hora = ?, valor = ? WHERE idJacuzzi = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, jacuzzi.getQuantidade());
		ps.setDate(2, (Date) jacuzzi.getData());
		ps.setDate(3, (Date) jacuzzi.getHora());
		ps.setFloat(4, jacuzzi.getValor());		
		
		ps.setInt(5, jacuzzi.getIdJacuzzi());
		ps.execute();
		ps.close();
		
	}

	@Override
	public void excluiJacuzzi(Jacuzzi jacuzzi) throws SQLException {
		String sql = "DELETE jacuzzi WHERE idJacuzzi = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, jacuzzi.getIdJacuzzi());
		
		ps.execute();
		ps.close();
	}

	@Override
	public List<Jacuzzi> listaJacuzzi() throws SQLException {
		List<Jacuzzi> listaJacuzzi = new ArrayList<Jacuzzi>();
		String sql = "SELECT * FROM jacuzzi";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Jacuzzi jacuzzi = new Jacuzzi();
			jacuzzi.setIdJacuzzi(rs.getInt("idJacuzzi"));
			jacuzzi.setData(rs.getDate("dt"));
			jacuzzi.setHora(rs.getDate("hora"));
			jacuzzi.setValor(rs.getFloat("valor"));
			listaJacuzzi.add(jacuzzi);
			
		}
		return listaJacuzzi;
	}

}

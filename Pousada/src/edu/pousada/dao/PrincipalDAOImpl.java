/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pousada.entity.Chale;
import edu.pousada.entity.Principal;

public class PrincipalDAOImpl implements PrincipalDAO {

	private Connection con = DBUtil.getInstance().getConnection();

	@Override
	public List<Principal> info() throws SQLException {

		String sql = "SELECT * FROM principal";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Principal>listaInfo = new ArrayList<>();

		while (rs.next()) {
			Principal info = new Principal();
			info.setPrincipalInfo(rs.getString( "principalInfo" ));
			info.setPrincipalDetalhe(rs.getString( "principalDetalhe" ));
			info.setChaleInfo(rs.getString( "chaleInfo" ));
			info.setChaleDetalhe(rs.getString( "chaleDetalhe" ));
			info.setLazerInfo(rs.getString( "LazerInfo" ));
			info.setLazerDetalhe(rs.getString( "lazerDetalhe" ));
			info.setServicoInfo(rs.getString( "servicoInfo" ));
			info.setServicoDetalhe(rs.getString( "servicoDetalhe" ));
			info.setReservaInfo(rs.getString( "reservaInfo" ));
			info.setContatoInfo(rs.getString( "contatoInfo" ));
			listaInfo.add( info );
		}
		rs.close();
		ps.close();
		return listaInfo;
	}

	@Override
	public List<Chale> categoria() throws SQLException {

		String sql = "SELECT categoria FROM chale";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Chale>listaCategoria = new ArrayList<>();

		while (rs.next()) {
			Chale categoria = new Chale();
			categoria.setCategoria(rs.getString( "categoria" ));
			listaCategoria.add( categoria );
		}
		rs.close();
		ps.close();
		return listaCategoria;
	}
}
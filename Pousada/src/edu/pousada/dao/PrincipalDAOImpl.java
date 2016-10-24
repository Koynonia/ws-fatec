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
		List<Principal>lista = new ArrayList<>();

		while (rs.next()) {
			Principal itens = new Principal();
			itens.setPrincipalInfo(rs.getString( "principalInfo" ));
			itens.setPrincipalDetalhe(rs.getString( "principalDetalhe" ));
			itens.setChaleInfo(rs.getString( "chaleInfo" ));
			itens.setChaleDetalhe(rs.getString( "chaleDetalhe" ));
			itens.setLazerInfo(rs.getString( "LazerInfo" ));
			itens.setLazerDetalhe(rs.getString( "lazerDetalhe" ));
			itens.setServicoInfo(rs.getString( "servicoInfo" ));
			itens.setServicoDetalhe(rs.getString( "servicoDetalhe" ));
			itens.setReservaInfo(rs.getString( "reservaInfo" ));
			itens.setContatoInfo(rs.getString( "contatoInfo" ));
			lista.add( itens );
		}
		rs.close();
		ps.close();
		return lista;
	}

	@Override
	public List<Chale> categoria() throws SQLException {

		String sql = "SELECT categoria FROM chale";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Chale>lista = new ArrayList<>();

		while (rs.next()) {
			Chale itens = new Chale();
			itens.setCategoria(rs.getString( "categoria" ));
			lista.add( itens );
		}
		rs.close();
		ps.close();
		return lista;
	}
}
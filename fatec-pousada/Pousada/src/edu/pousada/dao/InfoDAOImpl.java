/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
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

import edu.pousada.entity.Info;

public class InfoDAOImpl implements InfoDAO {

	private Connection con = DBUtil.getInstance().getConnection();
	
	/**	
	 * CREATE TABLE info(
	 * principalInfo VARCHAR(550) NOT NULL,
	 * principalDetalhe VARCHAR(850) NOT NULL,
	 * chaleInfo VARCHAR(550) NOT NULL,
	 * chaleDetalhe VARCHAR(850) NOT NULL,
	 * lazerInfo VARCHAR(550) NOT NULL,
	 * lazerDetalhe VARCHAR(850) NOT NULL,
	 * servicoInfo VARCHAR(550) NOT NULL,
	 * servicoDetalhe VARCHAR(850) NOT NULL,
	 * reservaInfo VARCHAR(550) NOT NULL,
	 * contatoInfo VARCHAR(550) NOT NULL,
	 * clienteInfo VARCHAR(550) NOT NULL,
	 * clienteDetalhe VARCHAR(850) NOT NULL,
	 * cadastroInfo VARCHAR(550) NOT NULL,
	 * versao VARCHAR(10) NOT NULL 
	 * );
	 */

	@Override
	public void adicionar(Info obj) throws SQLException {
	}

	@Override
	public void alterar(Info obj) throws SQLException {	
	}

	@Override
	public void excluir(Info obj) throws SQLException {
	}

	@Override
	public Info consultar(Info obj) throws SQLException {
		return null;
	}
	
	@Override
	public List<Info> todos() throws SQLException {

		String sql = "SELECT * FROM info";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Info>lista = new ArrayList<>();
		while (rs.next()) {
			Info itens = new Info();
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
			itens.setClienteInfo(rs.getString( "clienteInfo" ));
			itens.setClienteDetalhe(rs.getString( "clienteDetalhe" ));
			itens.setClienteInfo(rs.getString( "clienteInfo" ));
			itens.setCadastroInfo(rs.getString( "cadastroInfo" ));
			itens.setVersao(rs.getString( "versao" ));
			lista.add( itens );
		}
		rs.close();
		ps.close();
		return lista;
	}
}
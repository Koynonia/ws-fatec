/**
 * @author Fernando Moraes Oliveira
 * Matéria 4716 - Engenharia de Software 2
 * 3º ADS - Tarde
 * Iniciado em 10/06/2016
 */

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Apartamentos;
import model.Condominio;;

public class CondominioMensalDao implements ICondominioMensalDao {
	
	private Connection c;

	public CondominioMensalDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void insereDespesa(Condominio despesa) throws SQLException {
		
		String sql = "INSERT INTO despesa_condominio ("
				+ "despesa, valor, dtVencimento) VALUES (?,?,?)";
		PreparedStatement ps = c.prepareStatement( sql );
		ps.setString( 1, despesa.getDespesa() );
		ps.setFloat( 2, despesa.getValor() );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date vencimento = null;
		
		try {
			vencimento = sdf.parse( despesa.getDtVencimento() );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ps.setDate( 3, new java.sql.Date( vencimento.getTime( ) ));
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizaDespesa(Condominio despesa) throws SQLException {
		
		String sql = "UPDATE despesa_condominio SET despesa = ?, valor = ?, "
				+ "dtVencimento = ? WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString( 1, despesa.getDespesa() );
		ps.setFloat( 2, despesa.getValor() );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date vencimento = null;
		
		try {
			vencimento = sdf.parse( despesa.getDtVencimento() );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ps.setDate( 3, new java.sql.Date( vencimento.getTime( ) ));
		ps.setInt( 4, despesa.getId() );
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiDespesa(Condominio despesa) throws SQLException {
		
		String sql = "DELETE FROM despesa_condominio WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt( 1, despesa.getId() );
		ps.execute();
		ps.close();
	}

	@Override
	public List<Condominio> consultaCondominio() throws SQLException {
		
		String sql = "SELECT id, despesa, valor, dtVencimento "
				+ "FROM despesa_condominio";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Condominio> listaCondominio = new ArrayList<Condominio>();
		
		while (rs.next()) {
			Condominio despesas = new Condominio();
			despesas.setId( rs.getInt("id") );
			despesas.setDespesa( rs.getString("despesa") );
			despesas.setValor( rs.getFloat("valor") );
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date venc = rs.getDate("dtVencimento");
			String dtVenc =  sdf.format( venc );
			
			despesas.setDtVencimento( dtVenc );
			listaCondominio.add( despesas );
		}
		rs.close();
		ps.close();
		return listaCondominio;
	}

	@Override
	public List<Condominio> consultaDespesasCond() throws SQLException {
		
		String sql = "SELECT id, despesa, valor, dtVencimento "
				+ "FROM despesa_condominio";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Condominio> listaDespesas = new ArrayList<Condominio>();
		
		while (rs.next()) {
			Condominio despesas = new Condominio();
			despesas.setId( rs.getInt("id") );
			despesas.setDespesa( rs.getString("despesa") );
			despesas.setValor( rs.getFloat("valor") );
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date venc = rs.getDate("dtVencimento");
			String dtVenc =  sdf.format( venc );
			
			despesas.setDtVencimento( dtVenc );
			despesas.setApto( 0 );
			despesas.setQuartos( 0 );
			listaDespesas.add( despesas );
		}
		rs.close();
		ps.close();
		return listaDespesas;
	}

	@Override
	public List<Condominio> consultaDespesasApto() throws SQLException {
		
		String sql = "SELECT d.*, a.`numero`, a.`quartos` "
				+ "FROM `despesa_apartamento` AS d "
				+ "INNER JOIN `apartamento` AS a ON d.`id_apartamento` = a.`id` "
				+ "ORDER BY a.`numero` ASC";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Condominio> listaDespesas = new ArrayList<Condominio>();
		
		while (rs.next()) {
			Condominio despesas = new Condominio();
			despesas.setId( rs.getInt("id") );
			despesas.setDespesa( rs.getString("despesa") );
			despesas.setValor( rs.getFloat("valor") );
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date venc = rs.getDate("dtVencimento");
			String dtVenc =  sdf.format( venc );
			
			despesas.setDtVencimento( dtVenc );
			despesas.setApto( rs.getInt("numero") );
			despesas.setQuartos( rs.getInt("quartos") );
			listaDespesas.add( despesas );
		}
		rs.close();
		ps.close();
		return listaDespesas;
	}

	@Override
	public List<Apartamentos> consultaApto() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
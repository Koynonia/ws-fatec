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

import model.Despesas;
import model.Moradores;
import model.Apartamentos;
import model.Condominio;

public class CondominioMensalDao implements ICondominioMensalDao {
	
	private Connection c;

	public CondominioMensalDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void insereCondominio(Condominio despesa) throws SQLException {
		
		String sql = "INSERT INTO condominio_mensal ("
				+ "idApto, valor, dtVencimento, dtPagamento, dtProrrogado) "
				+ "VALUES (?,?,?,?,?)";
		
		PreparedStatement ps = c.prepareStatement( sql );
		ps.setInt( 1, despesa.getIdApto() );
		ps.setFloat( 2, despesa.getValor() );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date vencimento = null;
		Date pagamento = null;
		Date prorrogado = null;
		
		try {
			vencimento = sdf.parse( despesa.getDtVencimento() );
			pagamento = sdf.parse( despesa.getDtPagamento() );
			prorrogado = sdf.parse( despesa.getDtProrrogado() );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ps.setDate( 3, new java.sql.Date( vencimento.getTime( ) ));
		ps.setDate( 4, new java.sql.Date( pagamento.getTime( ) ));
		ps.setDate( 5, new java.sql.Date( prorrogado.getTime() ));
		ps.execute();
		ps.close();
	}
	
	@Override
	public void atualizaCondominio(Condominio despesa) throws SQLException {
		
		String sql = "UPDATE condominio_mensal SET "
				+ "idApto = ?, valor = ?, dtVencimento = ?, dtPagamento = ? WHERE id = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt( 1, despesa.getIdApto() );
		ps.setFloat( 2, despesa.getValor() );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date vencimento = null;
		Date pagamento = null;
		Date prorrogado = null;
		
		try {
			vencimento = sdf.parse( despesa.getDtVencimento() );
			pagamento = sdf.parse( despesa.getDtPagamento() );
			prorrogado = sdf.parse( despesa.getDtProrrogado() );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ps.setDate( 3, new java.sql.Date( vencimento.getTime( ) ));
		ps.setDate( 4, new java.sql.Date( pagamento.getTime( ) ));
		ps.setDate( 5, new java.sql.Date( prorrogado.getTime() ));
		ps.execute();
		ps.close();
	}

	@Override
	public void excluiCondominio(Condominio despesa) throws SQLException {
		
		String sql = "DELETE FROM condominio_mensal WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt( 1, despesa.getId() );
		ps.execute();
		ps.close();
	}

	@Override
	public List<Condominio> consultaCondominioMensal() throws SQLException {
		
		String sql = "SELECT * FROM condominio_mensal";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Condominio> listaCondominio = new ArrayList<Condominio>();
		
		while (rs.next()) {
			Condominio despesas = new Condominio();
			despesas.setId( rs.getInt("id") );
			despesas.setIdApto( rs.getInt("idApto") );
			despesas.setValor( rs.getFloat("valor") );
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date venc = rs.getDate("dtVencimento");
			java.util.Date pag = rs.getDate("dtPagamento");
			java.util.Date pro = rs.getDate("dtProrrogado");
			String dtVenc =  sdf.format( venc );
			String dtPagto =  sdf.format( pag );
			String dtPro =  sdf.format( pro );
			
			despesas.setDtVencimento( dtVenc );
			despesas.setDtPagamento( dtPagto );
			despesas.setDtProrrogado( dtPro );
			listaCondominio.add( despesas );
		}
		rs.close();
		ps.close();
		return listaCondominio;
	}
	
	@Override
	public List<Moradores> consultaMoradores() throws SQLException {
		
		String sql = "SELECT * FROM `morador`";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Moradores> listaMoradores = new ArrayList<Moradores>();
		
		while (rs.next()) {
			Moradores m = new Moradores();
			m.setId( rs.getInt("id") );
			m.setNome( rs.getString( "nome" ) );
			listaMoradores.add( m );
		}
		rs.close();
		ps.close();
		return listaMoradores;
	}
	
	@Override
	public List<Apartamentos> consultaAptos() throws SQLException {
		
//		String sql = "SELECT a.`id`, a.`numero`, a.`quartos`, m.`nome` "
//		+ "FROM `apartamento` AS a "
//		+ "INNER JOIN `morador` AS m ON a.`id_morador` = m.`id` "
//		+ "ORDER BY a.`numero`, m.`nome` ASC";
		
		String sql = "SELECT * FROM `apartamento` ";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Apartamentos> listaAptos = new ArrayList<Apartamentos>();
		
		while (rs.next()) {
			Apartamentos apto = new Apartamentos();
			apto.setId( rs.getInt("id") );
			apto.setNumero( rs.getInt("numero") );
			apto.setQuartos( rs.getInt("quartos") );
			apto.setOcupacao( rs.getString( "ocupacao" ) );
			apto.setId_morador( rs.getInt("id_morador") );
			listaAptos.add( apto );
		}
		rs.close();
		ps.close();
		return listaAptos;
	}

	@Override
	public List<Despesas> despesasCond() throws SQLException {
		
		String sql = "SELECT * FROM despesa_condominio";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Despesas> listaDespesas = new ArrayList<Despesas>();
		
		while (rs.next()) {
			Despesas despesas = new Despesas();
			despesas.setId( rs.getInt("id") );
			despesas.setDespesa( rs.getString("despesa") );
			despesas.setValor( rs.getFloat("valor") );
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date venc = rs.getDate("dtVencimento");
			String dtVenc =  sdf.format( venc );
			
			despesas.setDtVencimento( dtVenc );
			listaDespesas.add( despesas );
		}
		rs.close();
		ps.close();
		return listaDespesas;
	}

	@Override
	public List<Despesas> despesasApto() throws SQLException {
		
//		String sql = "SELECT d.*, a.`numero`, a.`quartos` "
//				+ "FROM `despesa_apartamento` AS d "
//				+ "INNER JOIN `apartamento` AS a ON d.`id_apartamento` = a.`id` "
//				+ "ORDER BY a.`numero` ASC";
		
		String sql = "SELECT * FROM `despesa_apartamento`";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Despesas> listaDespesas = new ArrayList<Despesas>();
		
		while (rs.next()) {
			Despesas despesas = new Despesas();
			despesas.setId( rs.getInt("id") );
			despesas.setIdApto( rs.getInt("id_apartamento") );
			despesas.setDespesa( rs.getString("despesa") );
			despesas.setValor( rs.getFloat("valor") );
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date venc = rs.getDate("dtVencimento");
			String dtVenc =  sdf.format( venc );
			
			despesas.setDtVencimento( dtVenc );
			listaDespesas.add( despesas );
		}
		rs.close();
		ps.close();
		return listaDespesas;
	}	
}
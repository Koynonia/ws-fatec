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

public class DespesasCondominioDao implements IDespesasCondominioDao {

	private Connection c;

	public DespesasCondominioDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void insereDespesa(Despesas despesa) throws SQLException {

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
	public void atualizaDespesa(Despesas despesa) throws SQLException {

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
	public void excluiDespesa(Despesas despesa) throws SQLException {

		String sql = "DELETE FROM despesa_condominio WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt( 1, despesa.getId() );
		ps.execute();
		ps.close();
	}

	@Override
	public List<Despesas> consultaDespesas() throws SQLException {

		String sql = "SELECT id, despesa, valor, dtVencimento "
				+ "FROM despesa_condominio";
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
}
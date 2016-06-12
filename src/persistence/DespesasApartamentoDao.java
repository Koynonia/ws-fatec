package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Despesas;

public class DespesasApartamentoDao implements IDespesasApartamentoDao {

	private Connection c;

	public DespesasApartamentoDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void atualizaDespesaApartamento(Despesas despesa) throws SQLException {
		String sql = "UPDATE despesa_apartamento SET despesa = ?, valor = ?, "
				+ "dtVencimento = ?, dtReferencia = ?  WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, despesa.getDespesa());
		ps.setFloat(2, despesa.getValor());
		ps.setString(3, despesa.getDtVencimento());
		ps.setString(4, despesa.getDtReferencia());
		ps.setInt(5, despesa.getId());

		ps.execute();
		ps.close();
	}

	@Override
	public void excluiDespesaApartamento(List<Integer> d) throws SQLException {
		String sql = "DELETE FROM despesa_apartamento WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);

		// for (int i = 0; i < despesas.length; i++) {
		// ps.setInt(1, despesas[i]);
		// ps.execute();
		// }
		for (int i = 0; i < d.size(); i++) {
			ps.setInt(1, d.get(i));
			ps.execute();
		}

		ps.close();
	}

	@Override
	public void insereDespesaApartamento(Despesas despesa, int idApartamento) throws SQLException {
		String sql = "INSERT INTO despesa_apartamento "
				+ "(id_apartamento, despesa, valor, dtVencimento, dtReferencia) " + "VALUES (?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, idApartamento);
		ps.setString(2, despesa.getDespesa());
		ps.setFloat(3, despesa.getValor());
		ps.setString(4, despesa.getDtVencimento());
		ps.setString(5, despesa.getDtReferencia());

		ps.execute();
		ps.close();

	}

	// DATE_FORMAT( `date` , '%d/%c/%Y %H:%i:%s' )
	// select( 'DB::raw('DATE_FORMAT(my_date, \'%m-%d-%Y\') AS arrival')' );
	@Override
	public List<Despesas> consultaDespesasApartamento(int idApto) throws SQLException {
		String sql = "SELECT da.id, da.despesa, da.valor, da.dtVencimento, " + "da.dtReferencia"
				+ " FROM despesa_apartamento da, apartamento a " + " WHERE da.id_apartamento = a.id AND a.id = ?";
		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, idApto);

		ResultSet rs = ps.executeQuery();
		List<Despesas> ListaDespesas = new ArrayList<Despesas>();

		while (rs.next()) {
			Despesas d = new Despesas();
			d.setId(rs.getInt("id"));
			d.setDespesa(rs.getString("despesa"));
			d.setValor(rs.getFloat("valor"));
			d.setDtVencimento(rs.getString("dtVencimento"));
			d.setDtReferencia(rs.getString("dtReferencia"));
			ListaDespesas.add(d);
		}

		rs.close();
		ps.close();
		return ListaDespesas;
	}

}

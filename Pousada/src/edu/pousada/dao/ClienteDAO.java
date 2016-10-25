/**
 * @author Guilherme Jatobá
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Cliente;

public interface ClienteDAO {

	public void adicionarCliente(Cliente cliente) throws SQLException;
	public void alterarCliente(Cliente cliente) throws SQLException;
	public void excluiCliente(Cliente cliente) throws SQLException;
	public Cliente consultaCliente(Cliente cliente) throws SQLException;
	public List<Cliente> listaCliente() throws SQLException;
	
}

/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Cliente;

public interface ClienteDAO {

	public void adicionar(Cliente cliente) throws SQLException;
	public void alterar(Cliente cliente) throws SQLException;
	public void excluir(Cliente cliente) throws SQLException;
	public Cliente consultar(Cliente cliente) throws SQLException;
	public List<Cliente> todos() throws SQLException;
	
}

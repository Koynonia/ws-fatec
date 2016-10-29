/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Principal;

public interface PrincipalDAO {

	public void adicionar(Principal p) throws SQLException;
	public void alterar(Principal p) throws SQLException;
	public void excluir(Principal p) throws SQLException;
	public Principal consultar(Principal p) throws SQLException;
	public List<Principal> todos() throws SQLException;
	
}
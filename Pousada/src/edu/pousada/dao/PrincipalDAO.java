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

	public void adicionaPrincipal(Principal Principal) throws SQLException;
	public void alteraPrincipal(Principal Principal) throws SQLException;
	public void excluiPrincipal(Principal Principal) throws SQLException;
	public Principal consultaPrincipal(Principal Principal) throws SQLException;
	public List<Principal> todasPrincipal() throws SQLException;
	
}
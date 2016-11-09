/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Info;

public interface InfoDAO {

	public void adicionar(Info p) throws SQLException;
	public void alterar(Info p) throws SQLException;
	public void excluir(Info p) throws SQLException;
	public Info consultar(Info p) throws SQLException;
	public List<Info> todos() throws SQLException;
	
}
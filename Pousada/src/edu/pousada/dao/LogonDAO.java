/**
 * @author FERNANDO MORAES OLIVEIRA
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 01/11/2016
 */


package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Logon;

public interface LogonDAO {

	public void adicionar(Logon l) throws SQLException;
	public void alterar(Logon l) throws SQLException;
	public void excluir(Logon l) throws SQLException;
	public Logon consultar(Logon l) throws SQLException;
	public List<Logon> todos() throws SQLException;
}
/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Chale;

public interface ChaleDAO {

	public void adicionar(Chale obj) throws SQLException;
	public void alterar(Chale obj) throws SQLException;
	public void excluir(Chale obj) throws SQLException;
	public Chale consultar(Chale obj) throws SQLException;
	public List<Chale> todos() throws SQLException;
}
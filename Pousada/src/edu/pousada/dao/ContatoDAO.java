/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 29/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Contato;

public interface ContatoDAO {

	public void adicionar(Contato c) throws SQLException;
	public void alterar(Contato c) throws SQLException;
	public void excluir(Contato c) throws SQLException;
	public Contato consultar(Contato c) throws SQLException;
	public List<Contato> todos() throws SQLException;
	
}
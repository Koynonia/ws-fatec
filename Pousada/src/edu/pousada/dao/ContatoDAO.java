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

	public void adicionar(Contato obj) throws SQLException;
	public void alterar(Contato obj) throws SQLException;
	public void excluir(Contato obj) throws SQLException;
	public Contato consultar(Contato obj) throws SQLException;
	public List<Contato> todos() throws SQLException;	
}
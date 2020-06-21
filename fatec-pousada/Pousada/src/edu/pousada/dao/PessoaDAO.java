/**
 * @author FERNANDO MORAES OLIVEIRA
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 28/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Pessoa;

public interface PessoaDAO {
	
	public void adicionar(Pessoa obj) throws SQLException;
	public void alterar(Pessoa obj) throws SQLException;
	public void excluir(Pessoa obj) throws SQLException;
	public Pessoa consultar(Pessoa obj) throws SQLException;
	public List<Pessoa> todos() throws SQLException;
}
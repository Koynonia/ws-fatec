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
	
	public void adicionaPessoa(Pessoa pessoa) throws SQLException;
	public void alteraPessoa(Pessoa pessoa) throws SQLException;
	public void excluiPessoa(Pessoa pessoa) throws SQLException;
	public Pessoa consultaPessoa(Pessoa pessoa) throws SQLException;
	public List<Pessoa> listaPessoa() throws SQLException;
	
}
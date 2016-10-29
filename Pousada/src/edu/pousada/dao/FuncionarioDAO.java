/**
 * @author FERNANDO MORAES OLIVEIRA
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 28/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Funcionario;

public interface FuncionarioDAO {

	public void adicionar(Funcionario f) throws SQLException;
	public void alterar(Funcionario f) throws SQLException;
	public void excluir(Funcionario f) throws SQLException;
	public Funcionario consultar(Funcionario f) throws SQLException;
	public List<Funcionario> todos() throws SQLException;
	
}
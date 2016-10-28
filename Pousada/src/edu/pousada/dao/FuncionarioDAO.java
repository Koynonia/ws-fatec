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

	public void adicionarFuncionario(Funcionario funcionario) throws SQLException;
	public void alterarFuncionario(Funcionario funcionario) throws SQLException;
	public void excluiFuncionario(Funcionario funcionario) throws SQLException;
	public Funcionario consultaFuncionario(Funcionario funcionario) throws SQLException;
	public List<Funcionario> todosFuncionarios() throws SQLException;
	
}
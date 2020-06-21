/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Lanchonete;

public interface LanchoneteDAO {
	
	public void adicionar(Lanchonete l)  throws SQLException;
	public void alterar(Lanchonete l)  throws SQLException;
	public void excluir(Lanchonete l) throws SQLException;
	public Lanchonete consultar(Lanchonete l) throws SQLException;
	public List<Lanchonete> todos() throws SQLException;
	
}
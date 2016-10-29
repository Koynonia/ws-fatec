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
	
	public void adicionar(Lanchonete lanchonete)  throws SQLException;
	public void alterar(Lanchonete lanchonete)  throws SQLException;
	public void excluir(Lanchonete lanchonete) throws SQLException;
	public Lanchonete consultar(Lanchonete lanchonete) throws SQLException;
	public List<Lanchonete> todos() throws SQLException;
	
}
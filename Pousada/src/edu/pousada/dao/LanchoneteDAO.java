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
	
	public void adicionarLanchonete(Lanchonete lanchonete)  throws SQLException;
	public void alterarLanchonete(Lanchonete lanchonete)  throws SQLException;
	public void excluirLanchonete(Lanchonete lanchonete) throws SQLException;
	public Lanchonete consultaLanchonete(Lanchonete lanchonete) throws SQLException;
	public List<Lanchonete> listaLanchonete() throws SQLException;
	
	
	
}

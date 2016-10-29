/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Transporte;

public interface TransporteDAO {

	public void adicionar(Transporte transporte) throws SQLException;
	public void alterar(Transporte transporte) throws SQLException;
	public void excluir(Transporte transporte) throws SQLException;
	public Transporte consultar(Transporte transporte) throws SQLException;
	public List<Transporte> todos() throws SQLException;
	
}
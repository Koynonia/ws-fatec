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

	public void adicionaTransporte(Transporte transporte) throws SQLException;
	public void alteraTransporte(Transporte transporte) throws SQLException;
	public void excluiTransporte(Transporte transporte) throws SQLException;
	public Transporte consultaTransporte(Transporte transporte) throws SQLException;
	public List<Transporte> listaTransporte() throws SQLException;
	
}
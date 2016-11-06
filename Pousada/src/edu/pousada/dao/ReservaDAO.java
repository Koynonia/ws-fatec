/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Chale;
import edu.pousada.entity.Reserva;

public interface ReservaDAO {
	
	public void adicionar(Reserva r) throws SQLException;
	public void alterar(Reserva r) throws SQLException;
	public void excluir(Reserva r) throws SQLException;
	public Reserva consultar(Reserva r) throws SQLException;
	public List<Reserva> todos() throws SQLException;
}
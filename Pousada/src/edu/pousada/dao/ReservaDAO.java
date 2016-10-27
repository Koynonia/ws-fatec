/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Reserva;

public interface ReservaDAO {
	
	public void adicionaReserva(Reserva Reserva) throws SQLException;
	public void alteraReserva(Reserva Reserva) throws SQLException;
	public void excluiReserva(Reserva Reserva) throws SQLException;
	public Reserva consultaReserva(Reserva Reserva) throws SQLException;
	public List<Reserva> todasReservas() throws SQLException;
}
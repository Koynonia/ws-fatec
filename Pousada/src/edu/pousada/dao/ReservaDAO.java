package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Reserva;

public interface ReservaDAO {
	
	public List<Reserva> reservas() throws SQLException;
}
package edu.pousada.dao;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.entity.Chale;
import edu.pousada.entity.Principal;

public interface PrincipalDAO {

	public List<Principal> info() throws SQLException;
	public List<Chale> categoria() throws SQLException;
}
/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 11/11/2016
 */

package edu.pousada.controller;

import java.sql.SQLException;
import java.util.List;

import edu.pousada.dao.ChaleDAO;
import edu.pousada.dao.ChaleDAOImpl;
import edu.pousada.entity.Chale;

public class ChaleCtrl {

	private LogonCtrl ctrlLogon = LogonCtrl.getInstance();
	private List<Chale> chales;

	public ChaleCtrl(

			) {
	}
	
	// DAO //////////////////////////////////////
	
	
	public void cargaChale(){

		ChaleDAO dao = new ChaleDAOImpl();

		try {
			chales = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nChaleCtrl > cargaChale()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}
}
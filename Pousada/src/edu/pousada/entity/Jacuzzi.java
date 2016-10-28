/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

import java.util.Date;

public class Jacuzzi extends Servico {
	
	private Integer id;
	private Date dtReserva;
	private Date hrReserva;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDtReserva() {
		return dtReserva;
	}
	public void setDtReserva(Date dtReserva) {
		this.dtReserva = dtReserva;
	}
	public Date getHrReserva() {
		return hrReserva;
	}
	public void setHrReserva(Date hrReserva) {
		this.hrReserva = hrReserva;
	}
}
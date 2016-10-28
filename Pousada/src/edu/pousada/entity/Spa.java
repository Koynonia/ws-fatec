/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

import java.util.Date;

public class Spa extends Servico {
	
	private Integer id;
	private String servico;
	private Date dtReserva;
	private Date hrReserva;
	private Float valor;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
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
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
}
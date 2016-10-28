/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

import java.util.Date;

public class Transporte extends Servico {

	private String id;
	private String placa;
	private String estado;
	private String destino;
	private Date dtReserva;
	private Date hrReserva;
	private Float valor;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
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
/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

import java.util.Date;

public class Lanchonete extends Servico{
	
	private Integer id;
	private String nome;
	private String tipo;
	private Date dtReserva;
	private Date hrReserva;
	private Date dtHora;
	private Float valor;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getDtReserva() {
		return dtReserva;
	}
	public void setDtReserva(Date dtReserva) {
		this.dtReserva = dtReserva;
	}
	public Date getDtHora() {
		return dtHora;
	}
	public void setDtHora(Date dtHora) {
		this.dtHora = dtHora;
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
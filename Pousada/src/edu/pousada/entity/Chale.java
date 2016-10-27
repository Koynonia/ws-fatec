/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.entity;

public class Chale {

	private Integer numero;
	private String categoria;
	private Float diaria;
	private Frigobar frigobar;
	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Float getDiaria() {
		return diaria;
	}
	public void setDiaria(Float diaria) {
		this.diaria = diaria;
	}
	public Frigobar getFrigobar() {
		return frigobar;
	}
	public void setFrigobar(Frigobar frigobar) {
		this.frigobar = frigobar;
	}
}
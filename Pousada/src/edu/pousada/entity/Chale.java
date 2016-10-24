package edu.pousada.entity;

public class Chale {

	private Integer numero;
	private String categoria;
	private Double diaria;
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
	public Double getDiaria() {
		return diaria;
	}
	public void setDiaria(Double diaria) {
		this.diaria = diaria;
	}
	public Frigobar getFrigobar() {
		return frigobar;
	}
	public void setFrigobar(Frigobar frigobar) {
		this.frigobar = frigobar;
	}
}
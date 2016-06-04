package model;


public class Apartamentos {
	
	private int id;
	

	private int numero;
	private int quartos;
	private String ocupacao;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getQuartos() {
		return quartos;
	}
	
	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}
	
	public String getOcupacao() {
		return ocupacao;
	}
	
	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}
}

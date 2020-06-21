package model;


public class Apartamentos {
	
	private int id;
	
	private int id_morador;
	private int numero;
	private int quartos;
	private String ocupacao;
	
	public int getId_morador() {
		return id_morador;
	}

	public void setId_morador(int id_morador) {
		this.id_morador = id_morador;
	}
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

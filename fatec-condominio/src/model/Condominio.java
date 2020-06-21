/**
 * @author Fernando Moraes Oliveira
 * Matéria 4716 - Engenharia de Software 2
 * 3º ADS - Tarde
 * Iniciado em 10/06/2016
 */

package model;

public class Condominio {
	private int id;
	private int idApto;
	private float valor;
	private String dtVencimento;
	private String dtPagamento;
	private String dtProrrogado;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdApto() {
		return idApto;
	}
	public void setIdApto(int idApto) {
		this.idApto = idApto;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public String getDtPagamento() {
		return dtPagamento;
	}
	public void setDtPagamento(String dtPagamento) {
		this.dtPagamento = dtPagamento;
	}
	public String getDtProrrogado() {
		return dtProrrogado;
	}
	public void setDtProrrogado(String dtProrrogado) {
		this.dtProrrogado = dtProrrogado;
	}
}
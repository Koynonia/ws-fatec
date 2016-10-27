/**
 * @author GUILHERME JATOBÁ
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

public class Lanchonete extends Servico{
	
	private Integer idLanchonete;
	private String nome;
	private String tipo;
	
	
	
	public Integer getIdLanchonete() {
		return idLanchonete;
	}
	public void setIdLanchonete(Integer idLanchonete) {
		this.idLanchonete = idLanchonete;
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
	
	
}

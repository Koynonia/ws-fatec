package edu.curso.entidade;

import java.io.Serializable;

public class Time implements Serializable {
	private static final long serialVersionUID = 1323434843482229584L;
	
	private long id;
	private String nome;
	private String modalidade;
	private String mascote;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	public String getMascote() {
		return mascote;
	}
	public void setMascote(String mascote) {
		this.mascote = mascote;
	}
}

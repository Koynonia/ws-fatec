/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 23/04/2016
 */

package controller;

import java.util.Date;

public abstract class Usuario {
	
	private String nome;
	private Date dtNasc;
	private Date dtCadastro;
	private String login;
	private String senha;
	private int nivelAcesso;
	
	
	public void definirNome( String nome) {
		
	}
	
	public String obterNome(){
		return nome;
	}
	
	
}



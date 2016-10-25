/**
 * @author Guilherme Jatobá
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

public class Spa extends Servico {
	
	private Integer idSpa;
	private String servico;

	public Integer getIdSpa() {
		return idSpa;
	}

	public void setIdSpa(Integer idSpa) {
		this.idSpa = idSpa;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}
	
	
}

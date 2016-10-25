/**
 * @author Guilherme Jatobá
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

public class Internet extends Servico {
	
	private Integer idLanchonete;
	private Integer quantidade;

	
	public Integer getIdLanchonete() {
		return idLanchonete;
	}

	public void setIdLanchonete(Integer idLanchonete) {
		this.idLanchonete = idLanchonete;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}

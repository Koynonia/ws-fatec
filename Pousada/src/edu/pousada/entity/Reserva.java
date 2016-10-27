/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

import java.util.Date;

public class Reserva {
	
	private Integer numero;
	private Cliente cliente;
	private Chale chale;
	private Integer qtdAdulto;
	private Integer qtdCrianca;
	private Integer quantidade;
	private Date dtInicio;
	private Date dtFim;
	private Integer desconto;
	private Date dtCadastro;
	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Chale getChale() {
		return chale;
	}
	public void setChale(Chale chale) {
		this.chale = chale;
	}
	public Integer getQtdAdulto() {
		return qtdAdulto;
	}
	public void setQtdAdulto(Integer qtdAdulto) {
		this.qtdAdulto = qtdAdulto;
	}
	public Integer getQtdCrianca() {
		return qtdCrianca;
	}
	public void setQtdCrianca(Integer qtdCrianca) {
		this.qtdCrianca = qtdCrianca;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Date getDtFim() {
		return dtFim;
	}
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
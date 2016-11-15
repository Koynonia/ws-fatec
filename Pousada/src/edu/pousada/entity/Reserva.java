/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.entity;

import java.util.Date;

public class Reserva {
	
	private Integer id;
	private Cliente cliente;
	private Chale chale;
	private Integer qtdAdulto;
	private Integer qtdCrianca;
	private Date dtInicio;
	private Date dtFim;
	private String observacao;
	private Integer desconto;
	private Boolean ativa;
	private Date dtCadastro;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getObervacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public Boolean getAtiva() {
		return ativa;
	}
	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
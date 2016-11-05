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
	private Cliente idCliente;
	private Chale idChale;
	private Integer qtdAdulto;
	private Integer qtdCrianca;
	private Date dtInicio;
	private Date dtFim;
	private String mensagem;
	private Integer desconto;
	private Date dtCadastro;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	public Chale getIdChale() {
		return idChale;
	}
	public void setIdChale(Chale idChale) {
		this.idChale = idChale;
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
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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
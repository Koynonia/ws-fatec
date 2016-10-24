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
	private String cliente;
	private Integer qtdAdulto;
	private Integer qtdCrianca;
	private String chale;
	private Integer quantidade;
	private Date dtInicio;
	private Date dtFim;
	private Float vlrUnitario;
	private String pagamento;
	private String estado;
	private Date dtCadastro;
	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	public String getChale() {
		return chale;
	}
	public void setChale(String chale) {
		this.chale = chale;
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
	public Float getVlrUnitario() {
		return vlrUnitario;
	}
	public void setVlrUnitario(Float vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
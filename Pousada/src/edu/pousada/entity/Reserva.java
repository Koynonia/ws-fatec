package edu.pousada.entity;

import java.util.Date;

public class Reserva {
	
	private Integer numero;
	private String cliente;
	private Integer qtdAdulto;
	private Integer qtdCrianca;
	private Date dtInicio;
	private Date dtFim;
	private String estado;
	private String pagamento;
	
	
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
}

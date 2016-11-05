/**
 * @author FERNANDO MORAES OLIVEIRA
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 01/11/2016
 */

package edu.pousada.entity;

import java.util.Date;

public class Logon {

	private Integer id;
	private Integer idUsuario;
	private String tela;
	private Integer perfil;
	private Integer logoff;
	private Date dtLogon;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTela() {
		return tela;
	}
	public void setTela(String tela) {
		this.tela = tela;
	}
	public Integer getPerfil() {
		return perfil;
	}
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
	public Integer getLogoff() {
		return logoff;
	}
	public void setLogoff(Integer logoff) {
		this.logoff = logoff;
	}
	public Date getDtLogon() {
		return dtLogon;
	}
	public void setDtLogon(Date dtLogon) {
		this.dtLogon = dtLogon;
	}
}
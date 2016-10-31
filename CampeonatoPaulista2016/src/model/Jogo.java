/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package model;

import java.util.Date;

public class Jogo {
	
	private Date data;
	private Integer CodigoTimeA;
	private Integer CodigoTimeB;
	private String timeA;
	private String timeB;
	private Integer golTimeA;
	private Integer golTimeB;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getCodigoTimeA() {
		return CodigoTimeA;
	}
	public void setCodigoTimeA(Integer codigoTimeA) {
		CodigoTimeA = codigoTimeA;
	}
	public Integer getCodigoTimeB() {
		return CodigoTimeB;
	}
	public void setCodigoTimeB(Integer codigoTimeB) {
		CodigoTimeB = codigoTimeB;
	}
	public String getTimeA() {
		return timeA;
	}
	public void setTimeA(String timeA) {
		this.timeA = timeA;
	}
	public String getTimeB() {
		return timeB;
	}
	public void setTimeB(String timeB) {
		this.timeB = timeB;
	}
	public Integer getGolTimeA() {
		return golTimeA;
	}
	public void setGolTimeA(Integer golTimeA) {
		this.golTimeA = golTimeA;
	}
	public Integer getGolTimeB() {
		return golTimeB;
	}
	public void setGolTimeB(Integer golTimeB) {
		this.golTimeB = golTimeB;
	}
	@Override
	public String toString() {
		return "Cod A: "+this.CodigoTimeA+" - Cod B: "+this.CodigoTimeB;
	}
	
	
//	public Date getData() {
//		return data;
//	}
//	public void setData(Date data) {
//		this.data = data;
//	}
//	public Integer getCodigoTimeA() {
//		return CodigoTimeA;
//	}
//	public void setCodigoTimeA(Integer codigoTimeA) {
//		CodigoTimeA = codigoTimeA;
//	}
//	public Integer getCodigoTimeB() {
//		return CodigoTimeB;
//	}
//	public void setCodigoTimeB(Integer codigoTimeB) {
//		CodigoTimeB = codigoTimeB;
//	}
//	public String getTimeA() {
//		return timeA;
//	}
//	public void setTimeA(String timeA) {
//		this.timeA = timeA;
//	}
//	public String getTimeB() {
//		return timeB;
//	}
//	public void setTimeB(String timeB) {
//		this.timeB = timeB;
//	}
//	public Integer getGolTimeA() {
//		return golTimeA;
//	}
//	public void setGolTimeA(Integer golTimeA) {
//		this.golTimeA = golTimeA;
//	}
//	public Integer getGolTimeB() {
//		return golTimeB;
//	}
//	public void setGolTimeB(Integer golTimeB) {
//		this.golTimeB = golTimeB;
//	}
}
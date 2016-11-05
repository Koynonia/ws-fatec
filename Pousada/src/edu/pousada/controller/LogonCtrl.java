/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.pousada.entity.Logon;

public class LogonCtrl {
	
	private static LogonCtrl instance;
	private List<Logon> logon;

	public static LogonCtrl getInstance() {
		// Método público estático de acesso único ao objeto
		if (instance == null)
			instance = new LogonCtrl();
		return instance;
	}
	
	public List<Logon> getLogon() {
		return logon;
	}

	public void setLogon(List<Logon> logon) {
		this.logon = logon;
	}
	
	
	public void logoff(){

		if ( temporizador() != false ){
			sair();
		}
	}
	
	
	public String obterData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String data = (dateFormat.format(date));
		return data;
	}
	
	
	public boolean testarNumero(String str) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(str);
		return m.find();    
	}
	
	
	public String mascaraCampo(String pMask, String pValue,
			boolean pReturnValueEmpty){
		
		/* 
		 * Verifica se foi configurado para nao retornar a  
		 * mascara se a string for nula ou vazia se nao 
		 * retorna somente a mascara. 
		 */ 
		if (pReturnValueEmpty == true
				&& (pValue == null || pValue.trim().equals("")))
			return "";
		
		if ( pValue.contains("-") || 
				( pValue.contains(".") ) || 
				( pValue.contains("(") ) || 
				( pValue.contains(")") ))
			return pValue;
			
		/* 
		 * Substituir as mascaras passadas como  9, X, * por # para efetuar a formatcao
		 */
//		pMask = pMask.replaceAll("*", "#");
		pMask = pMask.replaceAll("9", "#");
//		pMask = pMask.toUpperCase().replaceAll("X", "#");
		/* 
		 * Formata valor com a mascara passada  
		 */
		for(int i = 0; i < pValue.length(); i++){
			pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
		}
		/* 
		 * Subistitui por string vazia os digitos restantes da mascara 
		 * quando o valor passado é menor que a mascara   
		 */
		return pMask.replaceAll("#", "");
	}
	
	
	public Boolean temporizador(){

		boolean dt = false;
		
		if ( logon.size() > 0 ){
			int tempo = 5; //variavel que controla os minutos da sessão
			String dtAtual = obterData();
			String dtLogin = logon.get(0).getData();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			//Converte para Date
			Date dateA = null;
			Date dateB = null;
			try {
				dateA = df.parse(dtAtual);
				dateB = df.parse(dtLogin);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Calendar calB = Calendar.getInstance();
			calB.setTime(dateB);
			//Adiciona o tempo configurado para a B
			//Calendar.MINUTE pode ser alterado para qtd de tempo desejada
			calB.add(Calendar.MINUTE, tempo);
			dateB = calB.getTime();

			if ( dateA.after(dateB) ){
				dt = true;
			}	
		}
		return dt;
	}
	
	public void sair(){
		
	}
}
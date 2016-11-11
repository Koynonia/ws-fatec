/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.pousada.dao.LogonDAO;
import edu.pousada.dao.LogonDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Funcionario;
import edu.pousada.entity.Logon;
import edu.pousada.entity.Reserva;

public class LogonCtrl {

	private String diretorio = "../Pousada/resources/";
	private static LogonCtrl instance;
	private List<Logon> logon;
	private List<Reserva> reservas;

	// Construtor privado (suprime o construtor público padrão).
	private LogonCtrl() {

		new ArrayList<Funcionario>();
		this.setLogon( new ArrayList<Logon>() );
		this.reservas = new ArrayList<Reserva>();
	}

	public static LogonCtrl getInstance() {
		// Método público estático de acesso único ao objeto
		if (instance == null)
			instance = new LogonCtrl();
		return instance;
	}

	public List<Logon> getSession() {
		return logon;
	}

	public void setLogon(List<Logon> logon) {
		this.logon = logon;
	}


	public void autoLogoff(){

		if ( temporizador( "logon" ) != false ){
			logoff();
		}
		if ( temporizador( "reserva" ) != false ){
			excluiReservas();
		}
	}

	public void encerrar(){

		excluiReservas();
		logoff();
	}


	// DAO //////////////////////////////////////


	public List<Logon> cargaLogon(){

		List<Logon> log = new ArrayList<Logon>();

		LogonDAO dao = new LogonDAOImpl();
		try {
			log = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl > cargaLogon()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
		return log;
	}

	public void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl >  cargaReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}

	public void adicionaLogon() {

		LogonDAO dao = new LogonDAOImpl();
		try {
			dao.adicionar( getSession().get(0) );
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl >  adicionaLogon()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			e.printStackTrace();
		}
	}

	public void alteraLogon() {

		LogonDAO dao = new LogonDAOImpl();
		try {
			dao.alterar( getSession().get(0) );
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl >  alteraLogon()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	// verifica se um usuario fechou a janela sem se deslogar
	// pode recuperar a sessão ou não
	public void autoLogin( boolean ativar ){

		List<Logon> logons = cargaLogon();
		List<Logon> log = new ArrayList<Logon>();
		Logon l = new Logon();

		if ( !logons.isEmpty() ){
			for( int i = 0; i < logons.size(); i++ ){
				if( logons.get(i).getLogoff().equals( 1 ) ){

					l.setId( logons.get(i).getId() );
					l.setIdUsuario( logons.get(i).getIdUsuario() );
					l.setPerfil( logons.get(i).getPerfil() );
					l.setTela( logons.get(i).getTela() );
					l.setLogoff( logons.get(i).getLogoff() );
					l.setDtLogon( new Date() );

					if( ativar != false ){

						log.add(l);
						setLogon( log );	
						login();

					} else {

						l.setLogoff( 0 );
						log.add(l);
						setLogon( log );
						alteraLogon();
						getSession().clear();
					}
				}
			}
		}
	}


	public void login(){

		List<Logon> logons = cargaLogon();
		List<Logon> log = new ArrayList<Logon>();
		Logon l = new Logon();

		if ( !logons.isEmpty() ){
			for( int i = 0; i < logons.size(); i++ ){

				if( logons.get(i).getPerfil().equals( logon.get(0).getPerfil() ) 
						&& logons.get(i).getIdUsuario().equals( logon.get(0).getIdUsuario() )){

					l.setId( logons.get(i).getId() );
					l.setIdUsuario( logon.get(0).getIdUsuario() );
					l.setPerfil( logon.get(0).getPerfil() );
					l.setTela( logon.get(0).getTela() );
					l.setLogoff( 1 );
					l.setDtLogon( new Date() );
					log.add(l);
					setLogon( log );
					alteraLogon();
					return;

				} else if( i+1 == logons.size() ){

					l.setIdUsuario( logon.get(0).getIdUsuario() );
					l.setPerfil( logon.get(0).getPerfil() );
					l.setTela( logon.get(0).getTela() );
					l.setLogoff( 1 );
					l.setDtLogon( new Date() );
					log.add(l);
					setLogon( log );
					adicionaLogon();
				}
			}
		} else {

			l.setIdUsuario( logon.get(0).getIdUsuario() );
			l.setPerfil( logon.get(0).getPerfil() );
			l.setTela( logon.get(0).getTela() );
			l.setLogoff( 1 );
			l.setDtLogon( new Date() );
			log.add(l);
			setLogon( log );
			adicionaLogon();
		}
	}


	public void logoff(){

		List<Logon> logons = cargaLogon();
		List<Logon> log = new ArrayList<Logon>();
		Logon l = new Logon();

		if ( !logons.isEmpty() ){
			for( int i = 0; i < logons.size(); i++ ){
				if( logons.get(i).getPerfil().equals( logon.get(0).getPerfil() ) 
						&& logons.get(i).getIdUsuario().equals( logon.get(0).getIdUsuario() )){

					l.setId( logons.get(i).getId() );
					l.setIdUsuario( logon.get(0).getIdUsuario() );
					l.setPerfil( logon.get(0).getPerfil() );
					l.setTela( logon.get(0).getTela() );
					l.setLogoff( 0 );
					l.setDtLogon( new Date() );
					log.add(l);
					setLogon( log );
					alteraLogon();
				}
			}
		}
		excluiReservas();
		getSession().clear();
	}


	public Integer reservaQtd() {

		reservas.clear(); //limpa a lista das Reservas
		cargaReserva(); //recarrega a lista das Reservas atualizada
		temporizador( "reservas" ); //verifica se a Reserva já ultrapassou o tempo
		int qtd = 0;

		for ( int i = 0; i < reservas.size(); i++ ){
			if( reservas.get(i).getCliente().getId()
					.equals( logon.get(0).getIdUsuario() ) 
					&& reservas.get(i).getAtiva() != true ){
				qtd =  qtd + 1;
			}
		}
		return qtd;
	}


	public void excluiReservas (){

		int count = 0;
		cargaReserva();

		if( !reservas.isEmpty() ){

			// se for um funcionario não exclui as reservas não confirmadas
			if ( logon.get(0).getPerfil() != 2 ){

				// verifica se o cliente posssui reservas não confirmadas
				for( int c = 0; c < reservas.size(); c++ ){

					if( reservas.get(c).getCliente().getId()
							.equals( logon.get(0).getIdUsuario() ) 
							&& reservas.get(c).getAtiva() != true ){					
						count++;
					}
				}
				if( count > 0){
					if( !msg( "excluirReservas", "" + reservas.size() )){
						for( int i = 0; i < reservas.size(); i++ ){
							if( reservas.get(i).getCliente().getId()
									.equals( logon.get(0).getIdUsuario() ) 
									&& reservas.get(i).getAtiva() != true ){
								ReservaDAO dao = new ReservaDAOImpl();
								Reserva r = new Reserva();
								r.setId( reservas.get(i).getId() );
								try {
									dao.excluir( r );
								} catch (SQLException e) {
									msg("", "ERRO SQL " + e.getSQLState() 
											+ "\n\nLocal:\nLogonCtrl >  excluiReservas()."  
											+ "\n\nMensagem:\n" + e.getMessage() );
									//e.printStackTrace();
								}
								PrincipalCtrl.btnReservas.setText( "Reservas");
								PrincipalCtrl.btnReservas.setVisible(false);
							}
						}
					}
				}
			}
		}
	}


	// METODOS DE SUPORTE //////////////////////


	public Boolean temporizador( String op ){

		boolean dt = false;
		Date data = null;

		if ( logon.size() > 0 ){
			int tempo = 1; //variavel que controla os minutos da sessão

			switch (op){
			case "logon":
				data = logon.get(0).getDtLogon();
				break;
			case "reservas":
				if ( !reservas.isEmpty() ){
					data = reservas.get(0).getDtCadastro();
				} else {
					data = new Date();
				}
				break;
			}
			Date dtAtual = new Date();

			Calendar calB = Calendar.getInstance();
			calB.setTime( data );
			//Adiciona o tempo configurado para a B
			//Calendar.MINUTE pode ser alterado para qtd de tempo desejada
			calB.add(Calendar.MINUTE, tempo);
			data = calB.getTime();

			if ( dtAtual.after( data ) ){
				dt = true;
			}	
		}
		return dt;
	}

	//Classe que reduz as imagens
	public void reduzImagem (String orig, String thumb, int maxDim) { 
		try { 

			Image inImage = new ImageIcon(orig).getImage(); 
			double scale = (double)maxDim/(double)inImage.getWidth(null); 

			int scaledW = (int)(scale*inImage.getWidth(null)); 
			int scaledH = (int)(scale*inImage.getHeight(null)); 
			BufferedImage outImage = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB); 
			AffineTransform tx = new AffineTransform(); 

			if (scale < 1.0d) { 
				tx.scale(scale, scale); 
			} 

			Graphics2D g2d = outImage.createGraphics(); 
			g2d.drawImage(inImage, tx, null); 
			g2d.dispose();

			// Diretório de saída
			OutputStream os = new FileOutputStream( diretorio + "imagens/miniaturas/" + thumb );

			//formato da imagem de saída
			ImageWriter encoder = (ImageWriter)ImageIO.getImageWritersByFormatName( "jpg" ).next();
			ImageOutputStream ios = ImageIO.createImageOutputStream( os );
			encoder.setOutput( ios );
			IIOMetadata imageMetaData = encoder.getDefaultImageMetadata(new ImageTypeSpecifier( outImage ), null);
			encoder.write(imageMetaData, new IIOImage( outImage, null, null ), null);
			os.close(); 
		} catch (IOException e) { 
			System.out.println("Erro: "+e.getMessage()); 
			e.printStackTrace(); 
		} 
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


	// MENSAGENS //////////////////////////////

	public boolean msg( String tipo, String mensagem ) {

		switch (tipo) {

		case "excluirReservas":
			Object[] excluir = { "Cancelar Reservas", "Apenas Sair" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nExistem reservas não finalizadas" 
					+ " no sistema.\nDeseja cancelar a Reserva(s)?",
					"Atenção", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/trash.png" ), excluir, excluir[1] );
			if ( fechar != 0 ) {
				return true;
			} else {
				return false;
			}

		case "errorsession":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nPor favor, solicite a autorização de um administrador.", 
					"Bloqueado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png"));
			break;

		default:
			JOptionPane.showMessageDialog(null, mensagem + "\n", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png"));
		}
		return false;
	}
}
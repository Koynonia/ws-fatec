/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.pousada.dao.LogonDAO;
import edu.pousada.dao.LogonDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Funcionario;
import edu.pousada.entity.Logon;
import edu.pousada.entity.Reserva;

public class LogonCtrl {

	private static String diretorio;
	private static LogonCtrl instance;
	private static List<Logon> logon;
	private static List<Reserva> reservas;

	// construtor privado (suprime o construtor público padrão).
	private LogonCtrl() {

		new ArrayList<Funcionario>();
		LogonCtrl.setLogon( new ArrayList<Logon>() );
		LogonCtrl.reservas = new ArrayList<Reserva>();
	}

	public static LogonCtrl getInstance() {
		// método público estático de acesso único ao objeto
		if (instance == null)
			instance = new LogonCtrl();
		return instance;
	}

	public static String getCaminho() {

		diretorio = "../Pousada/resources/";

		return diretorio;
	}

	public static List<Logon> getSession() {
		return logon;
	}

	public static void setLogon(List<Logon> logon) {
		LogonCtrl.logon = logon;
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


	public static List<Logon> cargaLogon(){

		List<Logon> log = new ArrayList<Logon>();

		LogonDAO dao = new LogonDAOImpl();
		try {
			log = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl > cargaLogon()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
		return log;
	}

	public static void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl >  cargaReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}

	public static void adicionaLogon() {

		LogonDAO dao = new LogonDAOImpl();
		try {
			dao.adicionar( getSession().get(0) );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl >  adicionaLogon()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			e.printStackTrace();
		}
	}

	public static void alteraLogon() {

		LogonDAO dao = new LogonDAOImpl();
		try {
			dao.alterar( getSession().get(0) );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nLogonCtrl >  alteraLogon()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}

	
	public static void autoLogin( Boolean ativar ){
		// verifica se um usuario fechou a janela sem se deslogar
		// pode recuperar a sessão ou não
		
		List<Logon> logons = cargaLogon();

		if ( !logons.isEmpty() ){
			List<Logon> log = new ArrayList<Logon>();
			Logon l = new Logon();

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
		} else {
			// inicio do sistema sem dados
			Funcionario f = new Funcionario();

			f.setNome( "Administrador Teste" );
			f.setEmail( "" );
			f.setCpf( ""  );			
			f.setDtNasc( new Date() );
			f.setTelefone( "" );
			f.setCelular( "" );
			f.setEndereco( "" );
			f.setBairro( "" );
			f.setCidade( "" );
			f.setEstado( "" );
			f.setPais( "" );
			f.setCep( "" );
			f.setCargo("");
			f.setSetor("");
			f.setLogin( "admin" );
			f.setSenha( "admin" );
			f.setAtivo( true );
			f.setDtCadastro( new Date() );

			CadastroCtrl.adicionarFuncionario( f );
			
			List<Logon> log = new ArrayList<Logon>();
			Logon l = new Logon();
			l.setIdUsuario( 1 );
			l.setPerfil( 2 );
			l.setTela( "Principal" );
			l.setLogoff( 0 );
			l.setDtLogon( new Date() );
			log.add(l);
			setLogon( log );
			adicionaLogon();
			MensagensCtrl.msg("inicio", f.getNome() );
		}
	}


	public static void login(){

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


	public static void logoff(){

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


	public static Integer reservaQtd() {

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


	public static void excluiReservas (){

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
					if( !MensagensCtrl.msg( "excluirReservas", "" + reservaQtd() )){
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
									MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
											+ "\n\nLocal:\nLogonCtrl >  excluiReservas()."  
											+ "\n\nMensagem:\n" + e.getMessage() );
									//e.printStackTrace();
								}
								ReservaCtrl.btnReservas.setText( "Reservas");
								ReservaCtrl.btnReservas.setVisible(false);
							}
						}
					}
				}
			}
		}
	}


	// METODOS DE SUPORTE //////////////////////


	public static Boolean temporizador( String op ){

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
}
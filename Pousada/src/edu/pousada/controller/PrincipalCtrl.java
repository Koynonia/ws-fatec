/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.InfoDAO;
import edu.pousada.dao.InfoDAOImpl;
import edu.pousada.entity.Info;

public class PrincipalCtrl {

	private PrincipalFrm janela;
	private static JTabbedPane tabContainer;
	private static JPanel painelPrincipal;
	private static JPanel painelChale;
	private static JPanel painelLazer;
	private static JPanel painelServico;
	private static JPanel painelReserva;
	private static JPanel painelContato;
	private static JPanel painelClientePrincipal;
	private static JPanel painelClienteServico;
	private static JPanel painelClienteHistorico;
	private static JPanel painelCadastro;
	private static JPanel painelAdmPrincipal; 
	private static JPanel painelAdmChale; 
	private static JPanel painelAdmServico; 
	private JLabel lblRelogio;
	private JLabel lblPrincipalImg;
	private JLabel lblChaleImg; 
	private JLabel lblLazerImg;
	private JLabel lblServicoImg;
	private JTextArea txtaPrincipalInfo; 
	private JTextArea txtaPrincipalDetalhe; 
	private JTextArea txtaChaleInfo; 
	private JTextArea txtaChaleDetalhe; 
	private JTextArea txtaLazerInfo; 
	private JTextArea txtaLazerDetalhe; 
	private JTextArea txtaServicoInfo; 
	private JTextArea txtaServicoDetalhe; 
	private JTextArea txtaReservaInfo;  
	private JTextArea txtaContatoInfo; 
	private JTextArea txtaClienteInfo;
	private JTextArea txtaClienteDetalhe;
	private JTextArea txtaCadastroInfo;

	private static LogonCtrl ctrlLogon = LogonCtrl.getInstance();
	private ImageIcon imagem;
	private List<Info> infos;

	public PrincipalCtrl(
			PrincipalFrm janela, 
			JTabbedPane tabContainer, 
			JPanel painelPrincipal,
			JPanel painelChale,
			JPanel painelLazer,
			JPanel painelServico,
			JPanel painelReserva,
			JPanel painelContato, 
			JPanel painelClientePrincipal, 
			JPanel painelClienteServico,
			JPanel painelClienteHistorico,
			JPanel painelCadastro,
			JPanel painelAdmPrincipal, 
			JPanel painelAdmChale, 
			JPanel painelAdmServico, 
			JLabel lblRelogio, 
			JLabel lblPrincipalImg, 
			JLabel lblChaleImg, 
			JLabel lblLazerImg, 
			JLabel lblServicoImg, 
			JLabel lblVersao,
			JTextArea txtaPrincipalInfo, 
			JTextArea txtaPrincipal, 
			JTextArea txtaChaleInfo, 
			JTextArea txtaChale, 
			JTextArea txtaLazerInfo, 
			JTextArea txtaLazer, 
			JTextArea txtaServicoInfo, 
			JTextArea txtaServico, 
			JTextArea txtaReservaInfo, 
			JTextArea txtaContatoInfo, 
			JTextArea txtaClienteInfo, 
			JTextArea txtaClienteDetalhe, 
			JTextArea txtaCadastroInfo
			){

		this.janela = janela; 
		PrincipalCtrl.tabContainer = tabContainer;
		PrincipalCtrl.painelPrincipal = painelPrincipal;
		PrincipalCtrl.painelChale = painelChale;
		PrincipalCtrl.painelLazer = painelLazer;
		PrincipalCtrl.painelServico = painelServico;
		PrincipalCtrl.painelReserva = painelReserva;
		PrincipalCtrl.painelContato = painelContato;
		PrincipalCtrl.painelClientePrincipal = painelClientePrincipal;
		PrincipalCtrl.painelClienteServico = painelClienteServico;
		PrincipalCtrl.painelClienteHistorico = painelClienteHistorico;
		PrincipalCtrl.painelCadastro = painelCadastro;
		PrincipalCtrl.painelAdmPrincipal = painelAdmPrincipal;
		PrincipalCtrl.painelAdmChale = painelAdmChale;
		PrincipalCtrl.painelAdmServico = painelAdmServico;
		this.lblRelogio = lblRelogio;
		this.lblPrincipalImg = lblPrincipalImg;
		this.lblChaleImg = lblChaleImg;
		this.lblLazerImg = lblLazerImg;
		this.lblServicoImg = lblServicoImg;
		this.txtaPrincipalInfo = txtaPrincipalInfo; 
		this.txtaPrincipalDetalhe = txtaPrincipal; 
		this.txtaChaleInfo = txtaChaleInfo; 
		this.txtaChaleDetalhe = txtaChale; 
		this.txtaLazerInfo = txtaLazerInfo; 
		this.txtaLazerDetalhe = txtaLazer; 
		this.txtaServicoInfo = txtaServicoInfo; 
		this.txtaServicoDetalhe = txtaServico; 
		this.txtaReservaInfo = txtaReservaInfo;  
		this.txtaContatoInfo = txtaContatoInfo; 
		this.txtaClienteInfo = txtaClienteInfo; 
		this.txtaClienteDetalhe = txtaClienteDetalhe; 
		this.txtaCadastroInfo = txtaCadastroInfo;
		this.infos = new ArrayList<Info>();


		// realiza a preparação do ambiente visitante		
		cargaInfo();
		preecheInfo();
		temporizador();
		imagensRandom();

		// realiza o login com uma sessão anterior perdida
		ctrlLogon.autoLogin( false );

		// inicia o ambiente como perfil visitante
		if( ctrlLogon.getSession().isEmpty() ){
			trocaPerfil(0);
		} 

		// aciona o relogio exibido na tela
		relogio();
		lblVersao.setText( "versão: " + infos.get(0).getVersao() );
	}


	public PrincipalCtrl() {}


	public String getJanela() {

		return "Principal";
	}


	// DAO //////////////////////////////////////


	public void cargaInfo(){

		InfoDAO dao = new InfoDAOImpl();
		try {
			infos = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nPrincipalCtrl > cargaInfo()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public void trocaPerfil( int op ){
		//realiza a troca das guias conforme o perfil

		tabContainer.remove( painelPrincipal );
		tabContainer.remove( painelChale );
		tabContainer.remove( painelLazer );
		tabContainer.remove( painelServico );
		tabContainer.remove( painelReserva );
		tabContainer.remove( painelContato );
		tabContainer.remove( painelCadastro );
		tabContainer.remove( painelClientePrincipal );
		tabContainer.remove( painelClienteServico );
		tabContainer.remove( painelClienteHistorico );
		tabContainer.remove( painelAdmPrincipal );
		tabContainer.remove( painelAdmChale );
		tabContainer.remove( painelAdmServico );

		switch ( op ){

		case 1:
			tabContainer.add( "Principal", painelClientePrincipal );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Serviços", painelClienteServico );
			tabContainer.add( "Histórico", painelClienteHistorico );
			tabContainer.add( "Cadastro", painelCadastro );
			tabContainer.add( "Contato", painelContato );
			break;

		case 2:
			ReservaCtrl.btnReservas.setVisible(false);
			HospedagemCtrl.carregaTabelas();

			tabContainer.add( "Principal", painelAdmPrincipal );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Chalés", painelAdmChale );
			tabContainer.add( "Serviço", painelAdmServico );
			tabContainer.add( "Cadastro", painelCadastro );
			break;

		case 3:
			tabContainer.add( "Principal", painelPrincipal );
			tabContainer.add( "Chalés", painelChale );
			tabContainer.add( "Lazer", painelLazer );
			tabContainer.add( "Serviços", painelServico );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Contato", painelContato );
			tabContainer.setSelectedIndex(4);
			break;

		case 4:
			tabContainer.add( "Principal", painelPrincipal );
			tabContainer.add( "Chalés", painelChale );
			tabContainer.add( "Lazer", painelLazer );
			tabContainer.add( "Serviços", painelServico );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Contato", painelContato );
			tabContainer.add( "Cadastro", painelCadastro );
			tabContainer.setSelectedIndex(6);
			CadastroCtrl.btnCadastroEditar.setText("Salvar");
			CadastroCtrl.btnCadastroLimpar.setEnabled(true);
			break;

		default:
			tabContainer.add( "Principal", painelPrincipal );
			tabContainer.add( "Chalés", painelChale );
			tabContainer.add( "Lazer", painelLazer );
			tabContainer.add( "Serviços", painelServico );
			tabContainer.add( "Reservas", painelReserva );
			tabContainer.add( "Contato", painelContato );
		}
	}


	//  CRUD  //////////////////////////////////


	public void preecheInfo(){
		//preenche as informações de apresentação na tela

		if( !infos.isEmpty() ){
			for (int i = 0; i < infos.size(); i++) {
				txtaPrincipalInfo.setText( infos.get(i).getPrincipalInfo() ); 
				txtaPrincipalDetalhe.setText( infos.get(i).getPrincipalDetalhe() ); 
				txtaChaleInfo.setText( infos.get(i).getChaleInfo() ); 
				txtaChaleDetalhe.setText( infos.get(i).getChaleDetalhe() ); 
				txtaLazerInfo.setText( infos.get(i).getLazerInfo() ); 
				txtaLazerDetalhe.setText( infos.get(i).getLazerDetalhe() ); 
				txtaServicoInfo.setText( infos.get(i).getServicoInfo() ); 
				txtaServicoDetalhe.setText( infos.get(i).getServicoDetalhe() ); 
				txtaReservaInfo.setText( infos.get(i).getReservaInfo() ); 
				txtaContatoInfo.setText( infos.get(i).getContatoInfo() ); 
				txtaClienteInfo.setText( infos.get(i).getClienteInfo() );
				txtaClienteDetalhe.setText( infos.get(i).getClienteDetalhe() );
				txtaCadastroInfo.setText( infos.get(i).getCadastroInfo() );
			}
		}
	}


	//  SUPORTE  ////////////////////////////////


	public void temporizador(){
		// configura o tempo de troca das imagens da tela principal

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				imagensRandom();
			}
		}, 2000, 3000);
	}


	public void imagensRandom(){
		// verifca qual guia está sendo exibida e troca as imagens e registra a guia no logon

		Integer guiaAtiva = tabContainer.getSelectedIndex();

		Random gerador = new Random(); 
		int numero = gerador.nextInt(4);

		switch ( guiaAtiva ){

		case 0:
			imagem = new ImageIcon( LogonCtrl.getCaminho() + "/imagens/externa" + numero + ".jpg" );
			lblPrincipalImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblPrincipalImg.getWidth(), 
							lblPrincipalImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !ctrlLogon.getSession().isEmpty() )
				ctrlLogon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(0) );

			break;

		case 1:
			imagem = new ImageIcon( LogonCtrl.getCaminho() + "/imagens/chale" + numero + ".jpg" );
			lblChaleImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblChaleImg.getWidth(), 
							lblChaleImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !ctrlLogon.getSession().isEmpty() )
				ctrlLogon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(1) );

			break;

		case 2:
			imagem = new ImageIcon( LogonCtrl.getCaminho() + "/imagens/lazer" + numero + ".jpg" );
			lblLazerImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblLazerImg.getWidth(), 
							lblLazerImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !ctrlLogon.getSession().isEmpty() )
				ctrlLogon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(2) );

			break;

		case 3:
			imagem = new ImageIcon( LogonCtrl.getCaminho() + "/imagens/servico" + numero + ".jpg" );
			lblServicoImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblServicoImg.getWidth(), 
							lblServicoImg.getHeight(), 
							Image.SCALE_DEFAULT )));

			if( !ctrlLogon.getSession().isEmpty() )
				ctrlLogon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(3) );
			break;

		case 4:
			if( !ctrlLogon.getSession().isEmpty() )
				ctrlLogon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(4) );
			break;

		case 5:
			if( !ctrlLogon.getSession().isEmpty() )
				ctrlLogon.getSession().get(0).setTela( janela.getName() + " : " + tabContainer.getTitleAt(5) );
			break;
		}
	}


	public void relogio(){

		int delay = 1000; // delay de 1 seg.
		int interval = 1000; // intervalo de 1 minuto.
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {

				SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
				lblRelogio.setText(dateFormat.format(new Date()) + " horas");
			}
		}, delay, interval);
	}


	// JANELA //////////////////////////////////


	public void abrir ( String nome ){

		switch ( nome ){

		case "reservas":
			ReservaFrm reserva;
			try {
				reserva = new ReservaFrm();
				reserva.setVisible(true);
				reserva.setAlwaysOnTop ( false );
			} catch (ParseException e) {
				MensagensCtrl.msg("", "ERRO " + e.getCause() 
						+ "\n\nLocal: Principaltrl >  abrir()."  
						+ "\n\nMensagem:\n" + e.getMessage() );
				//e.printStackTrace();
			}
			break;
		}
	}


	// BOTAO //////////////////////////////////


	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// verifica qual botao esta solicitando a acao
			Object source = e.getSource();
			if( source == ReservaCtrl.btnReservas ){

				abrir("reservas");
			}
		}
	};

	// CONTROLE TECLA ///////////////////////////////


	public KeyListener teclas = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode=e.getKeyCode();

			switch (keyCode) {

			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_ESCAPE:
				MensagensCtrl.msg( "sair", "" );
				break;
			case KeyEvent.VK_DELETE:
				break;
			case 8: //MAC OSX: DELETE
				break;
			case KeyEvent.VK_TAB:
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};
}
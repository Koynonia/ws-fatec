/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.ChaleDAO;
import edu.pousada.dao.ChaleDAOImpl;
import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.ContatoDAO;
import edu.pousada.dao.ContatoDAOImpl;
import edu.pousada.dao.PrincipalDAO;
import edu.pousada.dao.PrincipalDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Chale;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Contato;
import edu.pousada.entity.Principal;
import edu.pousada.entity.Reserva;

public class PrincipalCtrl {

	private PrincipalFrm form; 
	private JTabbedPane tabContainer;
	private JPanel painelReserva;
	private JPanel painelContato;
	private JLabel lblPrincipalImg;
	private JLabel lblChaleImg; 
	private JLabel lblLazerImg;
	private JLabel lblServicoImg;
	private JLabel lblReservaImg;
	private JLabel lblVersao;
	private JTextField txtPesquisa;
	private JTextField txtReservaQtdAdulto; 
	private JTextField txtReservaQtdCrianca; 
	private JTextField txtReservaNome; 
	private JTextField txtReservaDocNum; 
	private JTextField txtReservaEmail; 
	private JTextField txtReservaTelefone; 
	private JTextField txtReservaCelular; 
	private JTextField txtReservaCidade; 
	private JTextField txtReservaEstado; 
	private JTextField txtReservaPais; 
	private JTextField txtContatoNome; 
	private JTextField txtContatoEmail; 
	private JTextField txtContatoTelefone; 
	private JTextField txtContatoCidade; 
	private JTextField txtContatoEstado; 
	private JTextField txtContatoPais; 
	private JFormattedTextField ftxtReservaDtInicio; 
	private JFormattedTextField ftxtReservaDtFim;
	private JTextArea txtaPrincipalInfo; 
	private JTextArea txtaPrincipalDetalhe; 
	private JTextArea txtaChaleInfo; 
	private JTextArea txtaChaleDetalhe; 
	private JTextArea txtaLazerInfo; 
	private JTextArea txtaLazerDetalhe; 
	private JTextArea txtaServicoInfo; 
	private JTextArea txtaServicoDetalhe; 
	private JTextArea txtaReservaInfo; 
	private JTextArea txtaReservaObs; 
	private JTextArea txtaContatoInfo; 
	private JTextArea txtaContatoMsg; 
	private JComboBox<String> cboReservaCategoria; 
	private JComboBox<String> cboReservaDocTipo; 
	private JComboBox<String> cboContatoAssunto; 
	private JTable tabChale; 
	private JTable tabReserva; 
	private JButton btnLogin;  
	private JButton btnReservaEnviar; 
	private JButton btnContatoEnviar;
	private JButton btnPesquisar;
	private JButton btnReservaLimpar;
	private JButton btnContatoLimpar;
	private String diretorio = "../Pousada/resources/";
	private LogonCtrl logon = LogonCtrl.getInstance();
	private ImageIcon imagem;
	private boolean validar;
	private List<Principal> infos;
	private List<Chale> chales;
	private List<Cliente> clientes;
	private List<Reserva> reservas;

	public PrincipalCtrl(
			PrincipalFrm form, 
			JTabbedPane tabContainer, 
			JPanel painelReserva, 
			JPanel painelContato, 
			JLabel lblPrincipalImg, 
			JLabel lblChaleImg, 
			JLabel lblLazerImg, 
			JLabel lblServicoImg, 
			JLabel lblReservaImg, 
			JLabel lblVersao, 
			JTextField txtPesquisa, 
			JTextField txtReservaQtdAdulto, 
			JTextField txtReservaQtdCrianca, 
			JTextField txtReservaNome, 
			JTextField txtReservaDocNum, 
			JTextField txtReservaEmail, 
			JTextField txtReservaTelefone, 
			JTextField txtReservaCelular, 
			JTextField txtReservaCidade, 
			JTextField txtReservaEstado, 
			JTextField txtReservaPais, 
			JTextField txtContatoNome, 
			JTextField txtContatoEmail, 
			JTextField txtContatoTelefone, 
			JTextField txtContatoCidade, 
			JTextField txtContatoEstado, 
			JTextField txtContatoPais, 
			JFormattedTextField ftxtReservaDtInicio, 
			JFormattedTextField ftxtReservaDtFim, 
			JTextArea txtaPrincipalInfo, 
			JTextArea txtaPrincipal, 
			JTextArea txtaChaleInfo, 
			JTextArea txtaChale, 
			JTextArea txtaLazerInfo, 
			JTextArea txtaLazer, 
			JTextArea txtaServicoInfo, 
			JTextArea txtaServico, 
			JTextArea txtaReservaInfo, 
			JTextArea txtaReservaObs, 
			JTextArea txtaContatoInfo, 
			JTextArea txtaContatoMsg,  
			JComboBox<String> cboReservaChale, 
			JComboBox<String> cboReservaDocTipo, 
			JComboBox<String> cboContatoAssunto, 
			JTable tabChale, 
			JTable tabReserva, 
			JButton btnLogin,  
			JButton btnReservaEnviar, 
			JButton btnContatoEnviar,
			JButton btnPesquisar, 
			JButton btnReservaLimpar, 
			JButton btnContatoLimpar
			){

		this.form = form; 
		this.tabContainer = tabContainer;
		this.painelReserva = painelReserva;
		this.painelContato = painelContato;
		this.lblPrincipalImg = lblPrincipalImg;
		this.lblChaleImg = lblChaleImg;
		this.lblLazerImg = lblLazerImg;
		this.lblServicoImg = lblServicoImg;
		this.lblReservaImg = lblReservaImg;
		this.lblVersao = lblVersao;
		this.txtPesquisa = txtPesquisa;
		this.txtReservaQtdAdulto = txtReservaQtdAdulto; 
		this.txtReservaQtdCrianca = txtReservaQtdCrianca; 
		this.txtReservaNome = txtReservaNome; 
		this.txtReservaDocNum = txtReservaDocNum; 
		this.txtReservaEmail = txtReservaEmail; 
		this.txtReservaTelefone = txtReservaTelefone; 
		this.txtReservaCelular = txtReservaCelular; 
		this.txtReservaCidade = txtReservaCidade; 
		this.txtReservaEstado = txtReservaEstado; 
		this.txtReservaPais = txtReservaPais; 
		this.txtContatoNome = txtContatoNome; 
		this.txtContatoEmail = txtContatoEmail; 
		this.txtContatoTelefone = txtContatoTelefone; 
		this.txtContatoCidade = txtContatoCidade; 
		this.txtContatoEstado = txtContatoEstado; 
		this.txtContatoPais = txtContatoPais; 
		this.ftxtReservaDtInicio = ftxtReservaDtInicio; 
		this.ftxtReservaDtFim = ftxtReservaDtFim;
		this.txtaPrincipalInfo = txtaPrincipalInfo; 
		this.txtaPrincipalDetalhe = txtaPrincipal; 
		this.txtaChaleInfo = txtaChaleInfo; 
		this.txtaChaleDetalhe = txtaChale; 
		this.txtaLazerInfo = txtaLazerInfo; 
		this.txtaLazerDetalhe = txtaLazer; 
		this.txtaServicoInfo = txtaServicoInfo; 
		this.txtaServicoDetalhe = txtaServico; 
		this.txtaReservaInfo = txtaReservaInfo; 
		this.txtaReservaObs = txtaReservaObs; 
		this.txtaContatoInfo = txtaContatoInfo; 
		this.txtaContatoMsg = txtaContatoMsg;  
		this.cboReservaCategoria = cboReservaChale; 
		this.cboReservaDocTipo = cboReservaDocTipo; 
		this.cboContatoAssunto = cboContatoAssunto; 
		this.tabChale = tabChale; 
		this.tabReserva = tabReserva; 
		this.btnLogin = btnLogin;  
		this.btnReservaEnviar = btnReservaEnviar; 
		this.btnContatoEnviar = btnContatoEnviar;
		this.btnPesquisar = btnPesquisar;
		this.btnReservaLimpar = btnReservaLimpar;
		this.btnContatoLimpar = btnContatoLimpar;
		this.infos = new ArrayList<Principal>();
		this.chales = new ArrayList<Chale>();
		this.clientes = new ArrayList<Cliente>();
		this.reservas = new ArrayList<Reserva>();

		cargaPrincipal();
		preecheInfo();
		preencheCategoria();
		preencheTipoDoc();
		preencheAssunto();
		imagensRandom();
		temporizador();
		versao();
	}


	public void versao(){

		lblVersao.setText( "versão: " + infos.get(0).getVersao() );	
	}


	public void limpaCampo(){

		Integer guiaAtiva = tabContainer.getSelectedIndex();
		Component[] painelAtivo = null;

		switch ( guiaAtiva){
		case 4:
			painelAtivo = painelReserva.getComponents();
			break;

		case 5:
			painelAtivo = painelContato.getComponents();
			break;
		}
		for ( Component c : painelAtivo ) {
			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				l.setText(null);
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				l.setValue(null);
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				l.setSelectedIndex(0);
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if( l.getName() == null ){
					l.setText(null);
				}
			}
		}
	}


	public void validaCampo(){

		boolean vazio = false;
		String campo = null;
		Integer guiaAtiva = tabContainer.getSelectedIndex();
		Component[] painelAtivo = null;

		switch ( guiaAtiva){
		case 4:
			painelAtivo = painelReserva.getComponents();
			break;

		case 5:
			painelAtivo = painelContato.getComponents();
			break;
		}

		for ( Component c : painelAtivo ) {

			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				if ( l.getText().isEmpty() && l.isVisible() ){
					if ( !l.getName().equalsIgnoreCase( "pesquisa")){
						vazio = true;
						campo = l.getName();
						l.requestFocus();
						l.setBackground(new Color(255,240,245));
					}
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				if ( l.getText().isEmpty() || l.getText().equalsIgnoreCase("  /  /    ")){
					vazio = true;
					campo = l.getName();
					l.requestFocus();
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				if ( l.getSelectedIndex() == 0){
					vazio = true;
					campo = l.getName();
					l.requestFocus();
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if ( !l.getName().equalsIgnoreCase( "Observações")){
					if ( l.getText().isEmpty() ){
						vazio = true;
						campo = l.getName();
						l.requestFocus();
						l.setBackground(new Color(255,240,245));
					} else {
						l.setBackground(new Color(255,255,255));
					}
				}
			}
		}
		if ( vazio == true ){
			msg( "erroCampo", campo );
			validar = false;
			return;
		} else {
			validar = true;
			validaData( ftxtReservaDtInicio, ftxtReservaDtFim );
		}
	}


	public void validaData( JFormattedTextField dtInicio, JFormattedTextField dtFim ){
		Date inicio = null;
		Date fim = null;
		Date atual = null;
		Date dt = new Date();
		String dta;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		dta = sdf.format( dt );

		try {
			inicio = sdf.parse( dtInicio.getText() );
			fim = sdf.parse( dtFim.getText() );
			atual = sdf.parse( dta );
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if( !inicio.equals( atual ) ){
			if ( !inicio.after( atual )){
				msg("erroDataInicial","");
				dtInicio.setText(null);
				dtInicio.requestFocus();
				validar = false;
				return;
			} else if( fim.before(inicio) ){
				msg("erroDataFinal","");
				dtFim.setText(null);
				dtFim.requestFocus();
				validar = false;
				return;
			}
		} else if( fim.before(inicio) ){
			msg("erroDataFinal","");
			dtFim.setText(null);
			dtFim.requestFocus();
			validar = false;
			return;
		} else {
			validar = true;
		}
	}


	public void temporizador(){

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				imagensRandom();
			}
		}, 2000, 3000);
	}


	public void imagensRandom(){

		Integer guiaAtiva = tabContainer.getSelectedIndex();

		Random gerador = new Random(); 
		int numero = gerador.nextInt(4);

		switch ( guiaAtiva){
		case 0:
			imagem = new ImageIcon( diretorio + "/imagens/externa" + numero + ".jpg" );
			lblPrincipalImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblPrincipalImg.getWidth(), 
							lblPrincipalImg.getHeight(), 
							Image.SCALE_DEFAULT )));
			break;
		case 1:
			imagem = new ImageIcon( diretorio + "/imagens/chale" + numero + ".jpg" );
			lblChaleImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblChaleImg.getWidth(), 
							lblChaleImg.getHeight(), 
							Image.SCALE_DEFAULT )));
			break;
		case 2:
			imagem = new ImageIcon( diretorio + "/imagens/lazer" + numero + ".jpg" );
			lblLazerImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblLazerImg.getWidth(), 
							lblLazerImg.getHeight(), 
							Image.SCALE_DEFAULT )));
			break;
		case 3:
			imagem = new ImageIcon( diretorio + "/imagens/servico" + numero + ".jpg" );
			lblServicoImg.setIcon( new ImageIcon( 
					imagem.getImage().getScaledInstance( 
							lblServicoImg.getWidth(), 
							lblServicoImg.getHeight(), 
							Image.SCALE_DEFAULT )));
			break;
		}
	}


	public void imagensCombo(){

		imagem = new ImageIcon( diretorio + "/imagens/chale" 
				+ cboReservaCategoria.getSelectedIndex() + ".jpg" );
		lblReservaImg.setIcon( new ImageIcon( 
				imagem.getImage().getScaledInstance( 
						lblReservaImg.getWidth(), 
						lblReservaImg.getHeight(), 
						Image.SCALE_DEFAULT )));
	}


	public void adicionaReserva () throws ParseException{

		cargaCliente();
		cargaChale();
		cargaReserva();
		validaCampo();

		if( validar != false  ){
			Chale ch = new Chale();
			validar = false;
			if( !chales.isEmpty() ){
				for( int i = 0; i < chales.size(); i++ ){
					if( chales.get(i).getCategoria().equals( cboReservaCategoria.getSelectedItem() )){
						validar = true;
					}
				}
				if( validar != false ){
					for( int i = 0; i < chales.size(); i++ ){
						if( chales.get(i).getCategoria().equals( cboReservaCategoria.getSelectedItem() )){
							if( chales.get(i).getId() != null ){
								ch.setId( chales.get(i).getId() );
								ch.setCategoria( chales.get(i).getCategoria() );
								ch.setDiaria( chales.get(i).getDiaria() );
								ch.setFrigobar( chales.get(i).getFrigobar() );
							}
						}
					}
					validar = false;
				} else {
					msg("erroChale", (String) cboReservaCategoria.getSelectedItem() );
					return;
				}
			} else {
				msg("erroChale", (String) cboReservaCategoria.getSelectedItem() );
				return;
			}
			Cliente cl = new Cliente();
			if( clientes.isEmpty() ){
				cl.setNome( txtReservaNome.getText() );
				cl.setEmail( txtReservaEmail.getText() );
				cl.setDocumento( txtReservaDocNum.getText() );
				cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
				cl.setDtNasc( new Date() );
				cl.setTelefone( txtReservaTelefone.getText() );
				cl.setCelular( txtReservaCelular.getText() );
				cl.setCidade( txtReservaCidade.getText() );
				cl.setEstado( txtReservaEstado.getText() );
				cl.setPais( txtReservaPais.getText() );
				cl.setDtCadastro( new Date() );
				cl.setAtivo( false );
				cadastraCliente( cl );
				cargaCliente();
			} else {
				for( int i = 0; i < clientes.size(); i++ ){
					if( clientes.get(i).getDocumento().equals( txtReservaDocNum.getText() )){
						validar = true;
					}
					if( i == clientes.size()-1 ){
						if(	validar == false ){
							cl.setId( clientes.size() );
							cl.setNome( txtReservaNome.getText() );
							cl.setEmail( txtReservaEmail.getText() );
							cl.setDocumento( txtReservaDocNum.getText() );
							cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
							cl.setDtNasc( new Date() );
							cl.setTelefone( txtReservaTelefone.getText() );
							cl.setCelular( txtReservaCelular.getText() );
							cl.setCidade( txtReservaCidade.getText() );
							cl.setEstado( txtReservaEstado.getText() );
							cl.setPais( txtReservaPais.getText() );
							cl.setAtivo( false );
							cl.setDtCadastro( new Date() );
							cadastraCliente( cl );
							cargaCliente();
						} else {
							for( int j = 0; j < clientes.size(); j++ ){
								if( clientes.get(j).getDocumento().equals( txtReservaDocNum.getText() )){
									cl.setId( clientes.get(j).getId() );
									cl.setNome( clientes.get(j).getNome() );
									cl.setEmail( clientes.get(j).getEmail() );
									cl.setDocumento( clientes.get(j).getDocumento() );
									cl.setDocTipo( clientes.get(j).getDocTipo() );
									cl.setDtNasc( clientes.get(j).getDtNasc() );
									cl.setTelefone( clientes.get(j).getTelefone() );
									cl.setCelular( clientes.get(j).getCelular() );
									cl.setEndereco( clientes.get(j).getEndereco() );
									cl.setBairro( clientes.get(j).getBairro() );
									cl.setCidade( clientes.get(j).getCidade() );
									cl.setEstado( clientes.get(j).getEstado() );
									cl.setPais( clientes.get(j).getPais() );
									cl.setCep( clientes.get(j).getCep() );
									cl.setDtCadastro( clientes.get(j).getDtCadastro() );
									cl.setAtivo( clientes.get(j).getAtivo() );
								}
							}
							validar = false;
						}
					}
				}
			}
			Reserva r = new Reserva();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			r.setCliente( cl );
			r.setChale( ch );
			r.setQtdAdulto( Integer.parseInt( txtReservaQtdAdulto.getText() ));
			r.setQtdCrianca( Integer.parseInt(txtReservaQtdCrianca.getText() ));
			r.setDtInicio( sdf.parse( ftxtReservaDtInicio.getText().replace("/","") ));
			r.setDtFim( sdf.parse( ftxtReservaDtFim.getText().replace("/","") ));
			r.setMensagem( txtaReservaObs.getText() );
			r.setDesconto( 0 );
			r.setDtCadastro( new Date() );
			cadastraReserva( r );
			abrir( "reservas" );
		}
	}

	public void adicionaContato(){

		Contato c = new Contato();
		c.setNome( txtContatoNome.getText() );
		c.setEmail( txtContatoEmail.getText() );
		c.setTelefone( txtContatoTelefone.getText() );
		c.setCidade( txtContatoCidade.getText() );
		c.setEstado( txtContatoEstado.getText() );
		c.setPais( txtContatoPais.getText() );
		c.setAssunto( cboContatoAssunto.getSelectedIndex() );
		c.setMensagem( txtaContatoMsg.getText() );
		c.setDtCadastro( new Date() );

		cadastraContato( c );
		msg( "sucesso", cboContatoAssunto.getSelectedItem().toString() );
		txtaReservaObs.setText(null);
		cboReservaCategoria.setSelectedIndex(0);
		ftxtReservaDtInicio.setText(null);
		ftxtReservaDtFim.setText(null);
		txtReservaQtdAdulto.setText(null);
		txtReservaQtdCrianca.setText(null);
	}


	// DAO //////////////////////////////////////

	public void cargaPrincipal(){

		PrincipalDAO dao = new PrincipalDAOImpl();
		try {
			infos = dao.todos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargaChale(){

		ChaleDAO dao = new ChaleDAOImpl();
		try {
			chales = dao.disponivel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargaCliente(){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			clientes = dao.todos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cadastraCliente( Cliente c ){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			dao.adicionar( c );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cadastraReserva( Reserva r ){

		ReservaDAO dao = new ReservaDAOImpl();

		try {
			dao.adicionar( r );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cadastraContato( Contato c ){

		ContatoDAO dao = new ContatoDAOImpl();

		try {
			dao.adicionar( c );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// AREA DE TEXTO /////////////////////////////

	public void preecheInfo(){

		if(infos != null){
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
			}
		}
	}


	// COMBOBOX /////////////////////////////////

	public void preencheCategoria(){
		
		List<Chale> c = new ArrayList<Chale>();
		ChaleDAO dao = new ChaleDAOImpl();
		try {
			c = dao.todos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Ordenar alfabeticamente
		String[] listaCategoria = new String[c.size()];
		for ( int i = 0; i < c.size(); i++ ){		
			String item = c.get(i).getCategoria();		
			listaCategoria[i] = item;	
		}
		Arrays.sort(listaCategoria);

		//Adicionar na combobox
		cboReservaCategoria.addItem( "Selecione…" );
		for ( int i = 0; i < c.size(); i++ ){
			cboReservaCategoria.addItem( listaCategoria[i] );
		}
	}

	public void preencheTipoDoc(){
		
		String[] tipos = {
				"CPF",
				"RG",
				"Passaporte"
		};
		//Adicionar na combobox
		cboReservaDocTipo.addItem( "Selecione…" );
		for ( int i = 0; i < tipos.length; i++ ){
			cboReservaDocTipo.addItem( tipos[i] );
		}
	}

	public void preencheAssunto(){

		String[] assuntos = {
				"Central de Reservas",
				"Reservas para Grupos",
				"Reservas para Eventos Corporativos",
				"Sugestões, Elogios ou Reclamações",
				"Outros"
		};
		//Adicionar na combobox
		cboContatoAssunto.addItem( "Selecione…" );
		for ( int i = 0; i < assuntos.length; i++ ){
			cboContatoAssunto.addItem( assuntos[i] );
		}
	}


	// TABELA //////////////////////////////////



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
				e.printStackTrace();
			}
			break;
		}
	}


	// BOTAO //////////////////////////////////

	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if( source == btnLogin ){
				msg( "construir", "" );
			}
			if( source == btnReservaEnviar ){
				try {
					adicionaReserva ();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			if( source == btnContatoEnviar ){
				adicionaContato();
				limpaCampo();
			}
			if( source == btnPesquisar ){
				msg( "construir", "" );
			}
			if( source == btnReservaLimpar ){
				limpaCampo();
			}
			if( source == btnContatoLimpar ){
				limpaCampo();
			}
			if( source == cboReservaCategoria ){
				imagensCombo();
			}
		}
	};

	// CONTROLE TECLA ///////////////////////////////

	public KeyListener numero = new KeyListener() {  

		@Override  
		public void keyTyped(KeyEvent e) {
			String caracteres="0987654321";
			if(!caracteres.contains(e.getKeyChar()+"")){
				e.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {			
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};

	public KeyListener alfa = new KeyListener() {  

		@Override  
		public void keyTyped(KeyEvent e) {
			String caracteres="0987654321";
			if(caracteres.contains(e.getKeyChar()+"")){
				e.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {			
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};

	public KeyListener teclar = new KeyListener() {  

		@Override  
		public void keyTyped(KeyEvent e) {
		}

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
				msg( "sair", "" );
				break;
			case KeyEvent.VK_DELETE:
				//removeLinha();
				break;
			case 8: //MAC OSX: DELETE
				//removeLinha();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};

	// MOUSE //////////////////////////////////

	public MouseListener clicar = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if(e.getClickCount() == 2){  

			}
		}
	};


	// MENSAGENS //////////////////////////////

	public void msg( String tipo, String mensagem ) {

		switch ( tipo ) {

		case "sucesso":
			JOptionPane.showMessageDialog(null, 
					"CONFIRMADO!\n\nSua mensagem com o assunto:\n" + mensagem + "\nfoi enviada.", 
					"Mensagem Recebida", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/confirm.png" ));
			break;

		case "erroChale":
			JOptionPane.showMessageDialog(null, 
					"INDISPONÍVEL\n\nOs Chalés da categoria " + mensagem
					+ " não estão disponíveis neste período.\nPor favor, selecione um período ou categoria diferente.", 
					"Não disponível", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;

		case "erroCampo":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nCampo '" + mensagem + "' não preenchido."
							+ "\nPor favor, digite o dado solicitado pelo campo.", 
							"Erro", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/warning.png" ));
			break;

		case "erroSelecao":
			JOptionPane.showMessageDialog(null, 
					"Seleção inválida!\n\n" 
							+ "Por favor, selecione uma categoria.", 
							"Seleção Inválida", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "erroDataInicial":
			JOptionPane.showMessageDialog(null, 
					"ERRO nas datas!\n\n" 
							+ "Por favor, digite uma data de Chegada\nigual ou superior à data de atual.", 
							"Datas Inválidas", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "erroDataFinal":
			JOptionPane.showMessageDialog(null, 
					"ERRO nas datas!\n\n" 
							+ "Por favor, digite uma data de Chegada\nanterior ou igual à data de Partida.", 
							"Datas Inválidas", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "construir":
			JOptionPane.showMessageDialog(null, 
					"Em construção!\n\nEsta função ainda não foi implementada.", 
					"Sem implementação", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/builder.png" ));
			break;

		case "sair":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o fechamento" 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), exit, exit[1] );
			if ( fechar == 0 ) {
				validar = true;
			} else {
				validar = false;
			}
			if(validar == true){
				System.exit(0);
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, 
					mensagem, 
					"Erro no Sistema", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
		}
	}
}
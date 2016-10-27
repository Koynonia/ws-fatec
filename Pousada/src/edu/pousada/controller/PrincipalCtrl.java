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
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.ChaleDAO;
import edu.pousada.dao.ChaleDAOImpl;
import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.PrincipalDAO;
import edu.pousada.dao.PrincipalDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Chale;
import edu.pousada.entity.Cliente;
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
		cargaChale();
		cargaCliente();
		cargaReserva();
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
					if ( l.getName() != "pesquisa" ){
						vazio = true;
						l.setBackground(new Color(255,240,245));
					}
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				if ( l.getText().isEmpty() ){
					vazio = true;
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				if ( l.getSelectedIndex() > 0){
				}
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if ( l.getText().isEmpty() ){
					vazio = true;
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
		}
		if ( vazio == true ){
			msg( "erroVazio", "" );
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

		validaCampo();

		if( validar != false  ){
			Chale ch = new Chale();

			for( int i = 0; i < chales.size(); i++ ){
				if( chales.get(i).getCategoria().equals( (String) cboReservaCategoria.getSelectedItem() )){
					if( chales.get(i).getNumero() != null ){
					ch.setNumero( chales.get(i).getNumero() );
					ch.setCategoria( chales.get(i).getCategoria() );
					ch.setDiaria( chales.get(i).getDiaria() );
					ch.setFrigobar( chales.get(i).getFrigobar() );
					}
				}
			}

			Cliente cl = new Cliente();

			if( clientes.isEmpty() ){
				cl.setNome( txtReservaNome.getText() );
				cl.setEmail( txtReservaEmail.getText() );
				cl.setDocumento( txtReservaDocNum.getText() );
				cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
				cl.setDtNasc( null );
				cl.setTelefone( txtReservaTelefone.getText() );
				cl.setCelular( txtReservaCelular.getText() );
				cl.setEndereco( null );
				cl.setBairro( null );
				cl.setCidade( txtReservaCidade.getText() );
				cl.setEstado( txtReservaEstado.getText() );
				cl.setPais( txtReservaPais.getText() );
				cl.setCep( null );
				cl.setDtCadastro( new Date() );
				cl.setAtivo( false );
			} else {
				for( int i = 0; i < clientes.size(); i++ ){
					if( !clientes.get(i).getDocumento().equals( txtReservaDocNum.getText() )){
						cl.setNome( txtReservaNome.getText() );
						cl.setEmail( txtReservaEmail.getText() );
						cl.setDocumento( txtReservaDocNum.getText() );
						cl.setDocTipo( cboReservaDocTipo.getSelectedItem().toString() );
						cl.setDtNasc( null );
						cl.setTelefone( txtReservaTelefone.getText() );
						cl.setCelular( txtReservaCelular.getText() );
						cl.setEndereco( null );
						cl.setBairro( null );
						cl.setCidade( txtReservaCidade.getText() );
						cl.setEstado( txtReservaEstado.getText() );
						cl.setPais( txtReservaPais.getText() );
						cl.setCep( null );
						cl.setDtCadastro( new Date() );
						cl.setAtivo( false );
					} else {
						cl.setNome( clientes.get(i).getNome() );
						cl.setEmail( clientes.get(i).getEmail() );
						cl.setDocumento( clientes.get(i).getDocumento() );
						cl.setDocTipo( clientes.get(i).getDocTipo() );
						cl.setDtNasc( clientes.get(i).getDtNasc() );
						cl.setTelefone( clientes.get(i).getTelefone() );
						cl.setCelular( clientes.get(i).getCelular() );
						cl.setEndereco( clientes.get(i).getEndereco() );
						cl.setBairro( clientes.get(i).getBairro() );
						cl.setCidade( clientes.get(i).getCidade() );
						cl.setEstado( clientes.get(i).getEstado() );
						cl.setPais( clientes.get(i).getPais() );
						cl.setCep( clientes.get(i).getCep() );
						cl.setDtCadastro( clientes.get(i).getDtCadastro() );
						cl.setAtivo( clientes.get(i).getAtivo() );
					}
				}
			}

			Reserva rs = new Reserva();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			rs.setNumero( 1 );
			rs.setCliente( cl );
			rs.setChale( ch );
			rs.setQtdAdulto( Integer.parseInt( txtReservaQtdAdulto.getText() ));
			rs.setQtdCrianca( Integer.parseInt(txtReservaQtdCrianca.getText() ));
			rs.setDtInicio( sdf.parse( ftxtReservaDtInicio.getText().replace("/","") ));
			rs.setDtFim( sdf.parse( ftxtReservaDtFim.getText().replace("/","") ));
			rs.setDesconto( 0 );
			rs.setDtCadastro( new Date() );
			
			ReservaFrm frm = new ReservaFrm();
			ReservaCtrl ctrl = new ReservaCtrl(
					frm, 
					frm.tabCompra, 
					frm.ftxtQtd, 
					frm.ftxtVlrTotal, 
					frm.btnAlterar,
					frm.btnRetirar,
					frm.btnLimpar,
					frm.btnConcluir,
					frm.btnVoltar
					);
			ctrl.adicionaChale ( rs );
			abrir( "reservas" );
		}
	}


	// DAO //////////////////////////////////////

	public void cargaPrincipal(){

		PrincipalDAO dao = new PrincipalDAOImpl();
		try {
			infos = dao.info();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargaChale(){

		ChaleDAO dao = new ChaleDAOImpl();
		try {
			chales = dao.todosChales();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargaCliente(){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			clientes = dao.todosClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todasReservas();
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

		//Ordenar alfabeticamente
		String[] listaCategoria = new String[chales.size()];
		for ( int i = 0; i < chales.size(); i++ ){		
			String item = chales.get(i).getCategoria();		
			listaCategoria[i] = item;	
		}
		Arrays.sort(listaCategoria);

		//Adicionar na combobox
		cboReservaCategoria.addItem( "Selecione…" );
		for ( int i = 0; i < chales.size(); i++ ){
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
				if ( cboReservaCategoria.getSelectedIndex() != 0 ){
					try {
						adicionaReserva ();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}else {
					msg( "erroSelecao", "" );
				}
			}
			if( source == btnContatoEnviar ){
				msg( "construir", "" );
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
				/*msg("sistema","Fechamento");
							if(validar == false){
							System.exit(0);
							}*/
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

		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nCampo não preenchido."
							+ "\nPor favor, digite o dado solicitado pelo campo.", 
							"Erro", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/warning.png" ));
			break;

		case "erroSelecao":
			JOptionPane.showMessageDialog(null, 
					"Seleção inválida:\n" 
							+ "\nPor favor, selecione uma categoria.", 
							"Seleção Inválida", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "construir":
			JOptionPane.showMessageDialog(null, 
					"Em construção!\nEsta função ainda não foi implementada.", 
					"Sem implementação", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/builder.png" ));
			break;

		case "sistema":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o " + mensagem 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa!", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), exit, exit[1] );
			if ( fechar == 0 ) {
				validar = true;
			} else {
				validar = false;
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
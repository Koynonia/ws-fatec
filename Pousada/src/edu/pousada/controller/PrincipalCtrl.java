/**
 * @author Fernando Moraes Oliveira
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import edu.pousada.dao.PrincipalDAO;
import edu.pousada.dao.PrincipalDAOImpl;
import edu.pousada.entity.Chale;
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
	private JLabel lblContatoImg;
	private JLabel lblVersao;
	private JTextField txtPesquisa; 
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
			JLabel lblContatoImg,  
			JLabel lblVersao, 
			JTextField txtPesquisa, 
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
		this.lblContatoImg = lblContatoImg;
		this.lblVersao = lblVersao;
		this.txtPesquisa = txtPesquisa;
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

		carregaPrincipalDAO();
		carregaChaleDAO();
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
	
	
	public void limpaCampos(){

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

	public void adicionaReserva ( Chale chale ){

		if( chales.size() > 0 ){

			ReservaFrm reserva;

			try {
				reserva = new ReservaFrm();
				reserva.setVisible(false);

				ReservaCtrl ctrl = new ReservaCtrl(
						reserva, 
						reserva.tabCompra, 
						reserva.ftxtQtd, 
						reserva.ftxtVlrTotal, 
						btnContatoEnviar, 
						btnContatoEnviar, 
						btnContatoEnviar, 
						btnContatoEnviar, 
						btnContatoEnviar);
				ctrl.adicionaChale ( chale );

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	// DAO //////////////////////////////////////

	public void carregaPrincipalDAO(){

		PrincipalDAO dao = new PrincipalDAOImpl();
		try {
			infos = dao.info();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void carregaChaleDAO(){

		ChaleDAO dao = new ChaleDAOImpl();
		try {
			chales = dao.todosChales();
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

		case "construir":
			JOptionPane.showMessageDialog(null, 
					"Em construção!\nEsta função ainda não foi implementada.", 
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
		}
	}


	// BOTAO //////////////////////////////////

	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if( source == btnLogin ){
				abrir( "construir" );
			}
			if( source == btnReservaEnviar ){
				if ( cboReservaCategoria.getSelectedIndex() != 0 ){
					abrir( "reservas" );
					adicionaReserva ( chales.get( cboReservaCategoria.getSelectedIndex() ));
				}else {
					JOptionPane.showMessageDialog(null, 
							"Seleção inválida:\n" 
									+ "\nPor favor, selecione uma categoria.", 
									"Seleção Inválida", JOptionPane.PLAIN_MESSAGE,
									new ImageIcon( diretorio + "/icons/error.png" ));
				}
			}
			if( source == btnContatoEnviar ){
				abrir( "construir" );
			}
			if( source == btnPesquisar ){
				abrir( "construir" );
			}
			if( source == btnReservaLimpar ){
				limpaCampos();
			}
			if( source == btnContatoLimpar ){
				limpaCampos();
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
				abrir( "sair" );
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
}
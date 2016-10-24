/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservasFrm;
import edu.pousada.dao.PrincipalDAO;
import edu.pousada.dao.PrincipalDAOImpl;
import edu.pousada.entity.Chale;
import edu.pousada.entity.Principal;

public class PrincipalCtrl {

	private PrincipalFrm form; 
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
	private JComboBox<String> cboChaleCategoria; 
	private JComboBox<String> cboReservaCategoria; 
	private JComboBox<String> cboReservaDocTipo; 
	private JComboBox<String> cboContatoAssunto; 
	private JTable tabChale; 
	private JTable tabReserva; 
	private JButton btnLogin;  
	private JButton btnReservaEnviar; 
	private JButton btnContatoEnviar;
	private JButton btnPesquisar;
	private String diretorio = "../Pousada/resources/";
	private String imagem;
	private boolean validar;
	private List<Principal> infos;
	private List<Chale> categorias;

	public PrincipalCtrl(
			PrincipalFrm form, 
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
			JComboBox<String> cboChaleCategoria, 
			JComboBox<String> cboReservaChale, 
			JComboBox<String> cboReservaDocTipo, 
			JComboBox<String> cboContatoAssunto, 
			JTable tabChale, 
			JTable tabReserva, 
			JButton btnLogin,  
			JButton btnReservaEnviar, 
			JButton btnContatoEnviar,
			JButton btnPesquisar
			){

		this.form = form; 
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
		this.cboChaleCategoria = cboChaleCategoria; 
		this.cboReservaCategoria = cboReservaChale; 
		this.cboReservaDocTipo = cboReservaDocTipo; 
		this.cboContatoAssunto = cboContatoAssunto; 
		this.tabChale = tabChale; 
		this.tabReserva = tabReserva; 
		this.btnLogin = btnLogin;  
		this.btnReservaEnviar = btnReservaEnviar; 
		this.btnContatoEnviar = btnContatoEnviar;
		this.btnPesquisar = btnPesquisar;
		this.infos = new ArrayList<Principal>();

		carregaDAO();
		preecheInfo();
		preencheCategoria();
		preencheTipoDoc();
		preencheAssunto();
	}

	// DAO //////////////////////////////////////

	public void carregaDAO(){

		PrincipalDAO dao = new PrincipalDAOImpl();
		try {
			infos = dao.info();
			categorias = dao.categoria();
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
		String[] listaCategoria = new String[categorias.size()];
		for ( int i = 0; i < categorias.size(); i++ ){		
			String item = categorias.get(i).getCategoria();		
			listaCategoria[i] = item;	
		}
		Arrays.sort(listaCategoria);

		//Adicionar na combobox
		cboChaleCategoria.addItem( "Categorias…" );
		cboReservaCategoria.addItem( "Categorias…" );
		for ( int i = 0; i < categorias.size(); i++ ){
			cboChaleCategoria.addItem( listaCategoria[i] );
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

	public void abrirJanela ( String nome ){

		switch ( nome ){

		case "login":
			sair();
			break;

		case "reservas":
			ReservasFrm reserva;
			try {
				reserva = new ReservasFrm();
				reserva.setVisible(true);
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			break;
		}
	}


	public void sair(){
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
	}

	// BOTAO //////////////////////////////////

	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if(source == btnLogin){
				abrirJanela( "login" );
			}
			if(source == btnReservaEnviar){
				abrirJanela( "reservas" );
			}
			if(source == btnContatoEnviar){
				abrirJanela( "contato" );
			}
			if(source == btnPesquisar){
				abrirJanela( "pesquisa" );
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
				form.dispose();
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
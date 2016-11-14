/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 11/11/2016
 */

package edu.pousada.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.ContatoDAO;
import edu.pousada.dao.ContatoDAOImpl;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Contato;

public class ContatoCtrl {

	private JLabel lblContatoMsg;
	private static JTextField txtContatoNome; 
	private static JTextField txtContatoEmail; 
	private static JTextField txtContatoCidade; 
	private static JTextField txtContatoEstado; 
	private static JTextField txtContatoPais; 
	private static JFormattedTextField ftxtContatoTelefone; 
	private JTextArea txtaContatoMsg; 
	private JComboBox<String> cboContatoAssunto; 
	private JButton btnContatoLimpar;
	private JButton btnContatoEnviar;

	private static LogonCtrl ctrlLogon = LogonCtrl.getInstance();
	private static List<Cliente> clientes;

	public ContatoCtrl(
			JLabel lblContatoMsg, 
			JTextField txtContatoNome, 
			JTextField txtContatoEmail,  
			JTextField txtContatoCidade, 
			JTextField txtContatoEstado, 
			JTextField txtContatoPais, 	
			JFormattedTextField ftxtContatoTelefone, 
			JTextArea txtaContatoMsg, 
			JComboBox<String> cboContatoAssunto, 
			JButton btnContatoLimpar, 
			JButton btnContatoEnviar
			){

		this.lblContatoMsg = lblContatoMsg;
		ContatoCtrl.txtContatoNome = txtContatoNome;
		ContatoCtrl.txtContatoEmail = txtContatoEmail;
		ContatoCtrl.txtContatoCidade = txtContatoCidade;
		ContatoCtrl.txtContatoEstado = txtContatoEstado;
		ContatoCtrl.txtContatoPais = txtContatoPais;
		ContatoCtrl.ftxtContatoTelefone = ftxtContatoTelefone;
		this.txtaContatoMsg = txtaContatoMsg;
		this.cboContatoAssunto = cboContatoAssunto;
		this.btnContatoLimpar = btnContatoLimpar;
		this.btnContatoEnviar = btnContatoEnviar;
		ContatoCtrl.clientes = new ArrayList<Cliente>();

		LogonCtrl.getInstance();
		preencheAssunto();
	}


	// DAO //////////////////////////////////////

	public static void cargaCliente(){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			clientes = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nContatoCtrl > cargaCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}
	
	
	public void adicionar( Contato obj ){

		ContatoDAO dao = new ContatoDAOImpl();

		try {
			dao.adicionar( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nContatoCtrl > adicionaContato()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}

	
	// CRUD ////////////////////////////////////
	

	public void enviaContato(){

		if( CamposCtrl.valida( "contato" ) != false ){
			
			Contato c = new Contato();
			
			c.setLida( false );
			c.setNome( txtContatoNome.getText() );
			c.setEmail( txtContatoEmail.getText() );
			c.setTelefone( ftxtContatoTelefone.getText()
					.replace("(","").replace(")", "").replace("_","").replace("-","") );
			c.setCidade( txtContatoCidade.getText() );
			c.setEstado( txtContatoEstado.getText() );
			c.setPais( txtContatoPais.getText() );
			c.setAssunto( cboContatoAssunto.getSelectedIndex() );
			c.setMensagem( txtaContatoMsg.getText() );
			c.setDtCadastro( new Date() );

			adicionar( c );
			MensagensCtrl.msg( "mensagemEnviada", cboContatoAssunto.getSelectedItem().toString() );
			txtaContatoMsg.setText(null);
		}
	}
	
	
	public static void preencheContato(){
		//Preenche o contato com os dados do cliente logado e trava os campos

		cargaCliente();

		if( !clientes.isEmpty() ){

			for (int i = 0; i < clientes.size(); i++) {

				if( clientes.get(i).getId()
						.equals( ctrlLogon.getSession().get(0).getIdUsuario() )){
					
					CamposCtrl.limpa("contato");
					
					txtContatoNome.setText( clientes.get(i).getNome() );
					txtContatoEmail.setText( clientes.get(i).getEmail() );
					ftxtContatoTelefone.setText( clientes.get(i).getTelefone() );
					txtContatoCidade.setText( clientes.get(i).getCidade() );
					txtContatoEstado.setText( clientes.get(i).getEstado() );
					txtContatoPais.setText( clientes.get(i).getPais() );
					

					CamposCtrl.desativa("contato");
				}
			}
		}
	}


	// COMBOBOX /////////////////////////////////


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


	// BOTAO //////////////////////////////////


	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if( source == btnContatoEnviar ){
				enviaContato();
			}
			if( source == btnContatoLimpar ){
				CamposCtrl.limpa( "contato" );
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

				Object sourceTab = e.getSource();

				//desabilita a tecla TAB
				if( sourceTab == txtaContatoMsg ){
					if(e.getModifiers() > 0) txtaContatoMsg.transferFocusBackward();
					else txtaContatoMsg.transferFocus(); 
					e.consume();
				}
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

			String tipo = "";
			String tam = "";

			//verifica qual componenete e configura o tipo de entrada (alfa ou numero)
			Object source = e.getSource();

			if( source == txtaContatoMsg ){
				tam = txtaContatoMsg.getText();
				lblContatoMsg.setText("Sua mensagem (" + (tam.length()+1) + " de 300):");
				if(tam.length() > 300){
					txtaContatoMsg.setBackground(new Color(255,240,245));
					return;
				} else {
					txtaContatoMsg.setBackground(new Color(255,255,255));
				}
			}
			if( source == txtContatoNome ){
				tipo = "alfa";
			}
			if( source == txtContatoCidade ){
				tipo = "alfa";
			}
			if( source == txtContatoEstado ){
				tipo = "alfa";
			}
			if( source == txtContatoPais ){
				tipo = "alfa";
			}

			switch ( tipo ) {

			case "numero":
				String numeros = "0987654321";
				if(!numeros.contains(e.getKeyChar()+"")){
					e.consume();
				}
				break;

			case "alfa":
				String caracteres = "0987654321";
				if(caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};
}
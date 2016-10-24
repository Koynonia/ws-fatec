/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservasFrm;

public class ReservaCtrl {

	private ReservasFrm form;
	private PrincipalFrm principal;
	private JTable tabela;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtValor;
	private JButton btnAlterar;
	private JButton btnRetirar; 
	private JButton btnLimpar; 
	private JButton btnConcluir; 
	private JButton btnVoltar;
	private String diretorio = "../Pousada/resources/";
	private boolean validar;

	public ReservaCtrl(
			ReservasFrm form, 
			JTable tabela, 
			JFormattedTextField ftxtQtd,  
			JFormattedTextField ftxtValor, 
			JButton btnAlterar, 
			JButton btnRetirar, 
			JButton btnLimpar, 
			JButton btnConcluir, 
			JButton btnVoltar
			) {

		this.form = form;
		this.tabela = tabela;
		this.ftxtQtd = ftxtQtd;
		this.ftxtValor = ftxtValor;
		this.btnAlterar = btnAlterar;
		this.btnRetirar = btnRetirar;
		this.btnLimpar = btnLimpar;
		this.btnRetirar = btnRetirar;
		this.btnVoltar = btnVoltar;

	}
	
	public ReservaCtrl(PrincipalFrm principal) {
		this.principal = principal;
	}

	// TABELA //////////////////////////////////



	// JANELA /////////////////////////////////

	public void abrirJanela ( String nome ){
		
		switch ( nome ){

		case "principal":
			
			//principal = new PrincipalFrm();
			//principal.setVisible(true);	
			fechar();
			break;
		}
	}

	public void fechar(){
		if(form != null)
			form.dispose();
	}

	public void mostrar(){
		if(form != null)
			form.setVisible(true);
	}

	public void esconder(){
		if(form != null)
			form.setVisible(false);
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

			if(source == btnAlterar){

			}
			if(source == btnRetirar){

			}
			if(source == btnLimpar){
				
			}
			if(source == btnConcluir){
				abrirJanela( "principal" );
			}
			if(source == btnVoltar){
				abrirJanela( "principal" );
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
				fechar();
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
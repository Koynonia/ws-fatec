/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import view.GrupoView;
import view.JogoView;
import view.MenuView;

public class MenuCtrl {

	private MenuView janela;
	private JogoView janelaJogo;
	private GrupoView janelaGrupo;
	private JButton btnJogo;
	private JButton btnGrupo;
	private JButton btnFechar;
	
	public MenuCtrl(
			MenuView janela, 
			JButton btnGrupo, 
			JButton btnJogo, 
			JButton btnFechar) {
		
		this.janela = janela;
		this.btnGrupo = btnGrupo;
		this.btnJogo = btnJogo;
		this.btnFechar = btnFechar;
	}
	
	public void abrirJanela ( String nome ){

		switch ( nome ){

		case "Grupo":
			if (janelaGrupo == null){
				janelaGrupo = new GrupoView();
				janelaGrupo.setVisible(true);
			} else {
				janelaGrupo.setVisible(true);
				janelaGrupo.setState(JFrame.NORMAL);
			}
			fechar();
			break;
			
		case "Jogo":
			if (janelaJogo == null){
				janelaJogo = new JogoView();
				janelaJogo.setVisible(true);
			} else {
				janelaJogo.setVisible(true);
				janelaJogo.setState(JFrame.NORMAL);
			}
			fechar();
			break;
		}
	}
	
	public void fechar(){
		if(janela != null)
			janela.dispose();
	}


	public ActionListener janelas = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();
			
			if( source == btnGrupo ){
				abrirJanela( "Grupo" );
				fechar();
			} 
			if( source ==  btnJogo ){
				abrirJanela( "Jogo" );
				fechar();
			} 
			if( source ==  btnFechar ){
				fechar();
			}
		}
	};
}
/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOException;
import persistence.CampeonatoDAOImpl;
import model.Grupo;
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
	private List<Grupo>grupos;

	public MenuCtrl(
			MenuView janela, 
			JButton btnGrupo, 
			JButton btnJogo, 
			JButton btnFechar) {

		this.janela = janela;
		this.btnGrupo = btnGrupo;
		this.btnJogo = btnJogo;
		this.btnFechar = btnFechar;
		this.grupos = new ArrayList<Grupo>();
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

			CampeonatoDAO dao = new CampeonatoDAOImpl();
			try {
				grupos = dao.consultaGrupos();
			} catch (CampeonatoDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ( grupos.isEmpty() ){

				Object[] excluir = { "Confirmar", "Cancelar" };  
				int ex = JOptionPane.showOptionDialog(null, 
								"Para gerar os Jogos do Campeonato "
								+ "\né necessário gerar os Grupos primeiro."
								+ "\n\nVocê quer gerar os Grupos do Campeonato agora?",
								"Não existem grupos", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
								new ImageIcon( "../CampeonatoPaulista2016/src/resources/warning.png" ), excluir, excluir[1]);
				if (ex == 0) {
					abrirJanela( "Grupo" );
				} else {
					return;
				}
			} else {
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
			} 
			if( source ==  btnJogo ){
				abrirJanela( "Jogo" );
			} 
			if( source ==  btnFechar ){
				fechar();
			}
		}
	};
}
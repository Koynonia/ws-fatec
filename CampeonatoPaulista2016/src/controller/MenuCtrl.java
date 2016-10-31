/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Grupo;
import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;
import view.GeralView;
import view.GrupoView;
import view.JogoView;
import view.MenuView;
import view.QuartasView;
import view.ResultadoView;

public class MenuCtrl {

	private MenuView janela;
	private JogoView janelaJogo;
	private GrupoView janelaGrupo;
	private ResultadoView janelaResultado;
	private GeralView janelaGeral;
	private QuartasView janelaQuartas;
	private JButton btnGrupo;
	private JButton btnJogo;
	private JButton btnResultado;
	private JButton btnGeral;
	private JButton btnQuartas;
	JButton btnFechar;
	private List<Grupo>grupos;

	public MenuCtrl(
			MenuView janela, 
			JButton btnGrupo, 
			JButton btnJogo, 
			JButton btnResultado, 
			JButton btnGeral,
			JButton btnQuartas,
			JButton btnFechar) {

		this.janela = janela;
		this.btnGrupo = btnGrupo;
		this.btnJogo = btnJogo;
		this.btnResultado = btnResultado;
		this.btnGeral = btnGeral;
		this.btnQuartas = btnQuartas;
		this.btnFechar = btnFechar;
		this.grupos = new ArrayList<Grupo>();
	}

	public void abrirJanela ( String nome ) throws SQLException{

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

			try {
				CampeonatoDAO dao = new CampeonatoDAOImpl();
				grupos = dao.consultaGrupos();
			} catch (SQLException e) {
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
			}
			fechar();
			break;

		case "Resultado":
			if (janelaResultado == null){
				try {
					janelaResultado = new ResultadoView();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				janelaResultado.setVisible(true);
			} else {
				janelaResultado.setVisible(true);
				janelaResultado.setState(JFrame.NORMAL);
			}
			fechar();
			break;

		case "Geral":
			if (janelaGeral == null){
				janelaGeral = new GeralView();
				janelaGeral.setVisible(true);
			} else {
				janelaGeral.setVisible(true);
				janelaGeral.setState(JFrame.NORMAL);
			}
			fechar();
			break;
			
		case "Quartas":
			if (janelaQuartas == null){
				janelaQuartas = new QuartasView();
				janelaQuartas.setVisible(true);
			} else {
				janelaQuartas.setVisible(true);
				janelaQuartas.setState(JFrame.NORMAL);
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
				try {
					abrirJanela( "Grupo" );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			if( source ==  btnJogo ){
				try {
					abrirJanela( "Jogo" );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			if( source ==  btnResultado){
				try {
					abrirJanela( "Resultado" );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if( source ==  btnGeral){
				try {
					abrirJanela( "Geral" );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if( source ==  btnQuartas){
				try {
					abrirJanela( "Quartas" );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if( source ==  btnFechar ){
				fechar();
			}
		}
	};
}
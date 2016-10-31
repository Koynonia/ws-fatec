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

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Resultado;
import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;
import view.GeralView;
import view.MenuView;

public class GeralCtrl {

	private GeralView janela;
	private MenuView janelaMenu;
	private JTable tabGeral;
	private List<Resultado> geral;

	public GeralCtrl( GeralView janela, JTable tabGeral ) {

		this.janela = janela;
		this.tabGeral = tabGeral;
		this.geral = new ArrayList<Resultado>();

		formataTabela();
	}


	public void carregaGeral() throws SQLException{

		CampeonatoDAO dao = new CampeonatoDAOImpl();
		geral = dao.resultadoGeral();
	}


	//FORMATA TABELA
	public void formataTabela(){

		List<String[]> linhas = new ArrayList<>();

		if( !geral.isEmpty() ){

			for (int i = 0; i < geral.size(); i++) {
				String[] g = {
						geral.get(i).getNomeTime(),
						Integer.toString(geral.get(i).getJogosDisputados()),
						Integer.toString(geral.get(i).getVitorias()),
						Integer.toString(geral.get(i).getEmpates()),
						Integer.toString(geral.get(i).getDerrotas()),
						Integer.toString(geral.get(i).getGolsMarcados()),
						Integer.toString(geral.get(i).getGolsSofridos()),
						Integer.toString(geral.get(i).getSaldoGols()),
						Integer.toString(geral.get(i).getPontos())
				};
				linhas.add(g);
			}
		} 
		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tabGeral.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centro.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = {
				"Time", 
				"Disputas", 
				"Vitórias", 
				"Empates", 
				"Derrotas", 
				"Gols Marcados", 
				"Gols Sofridos", 
				"Saldo de Gols", 
				"Pontos"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDICAO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false, false, false, false, false, false, false
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO A LINHA INTEIRA
		tabGeral.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabGeral.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabGeral.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabGeral.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabGeral.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabGeral.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabGeral.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabGeral.getColumnModel().getColumn(5).setCellRenderer(centro);
		tabGeral.getColumnModel().getColumn(6).setCellRenderer(centro);
		tabGeral.getColumnModel().getColumn(7).setCellRenderer(centro);
		tabGeral.getColumnModel().getColumn(8).setCellRenderer(centro);


		//CONFIGURA O TAMANHO DAS COLUNAS
		tabGeral.getColumnModel().getColumn(0).setPreferredWidth(160);
		tabGeral.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabGeral.getColumnModel().getColumn(2).setPreferredWidth(20);
		tabGeral.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabGeral.getColumnModel().getColumn(4).setPreferredWidth(15);
		tabGeral.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabGeral.getColumnModel().getColumn(6).setPreferredWidth(45);
		tabGeral.getColumnModel().getColumn(7).setPreferredWidth(45);
		tabGeral.getColumnModel().getColumn(8).setPreferredWidth(10);
	}


	public void fechar(){
		if(janela != null)
			janela.dispose();
	}


	public ActionListener preencherTabela = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			try {
				carregaGeral();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			formataTabela();
		}
	};

	public ActionListener fechar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if (janelaMenu == null){
				janelaMenu = new MenuView();
				janelaMenu.setVisible(true);
			} else {
				janelaMenu.setVisible(true);
				janelaMenu.setState(JFrame.NORMAL);
			}
			fechar();
		}
	};
}
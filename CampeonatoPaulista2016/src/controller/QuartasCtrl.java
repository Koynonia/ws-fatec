/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/10/2016
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

import model.Times;
import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;
import view.MenuView;
import view.QuartasView;

public class QuartasCtrl {
	private QuartasView janela;
	private MenuView janelaMenu;
	private JTable tabJogoA;
	private JTable tabJogoB;
	private JTable tabJogoC;
	private JTable tabJogoD;
	private List<Times> times;

	public QuartasCtrl( 
			QuartasView janela, 
			JTable tabJogoA, 
			JTable tabJogoB, 
			JTable tabJogoC,
			JTable tabJogoD) throws SQLException {

		this.janela = janela;
		this.tabJogoA = tabJogoA;
		this.tabJogoB = tabJogoB;
		this.tabJogoC = tabJogoC;
		this.tabJogoD = tabJogoD;
		this.times = new ArrayList<Times>();

		inicia();
	}

	public void inicia() throws SQLException{

		try {
			carregaGrupos();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			formataTabJogoA();
			formataTabJogoB();
			formataTabJogoC();
			formataTabJogoD();

	}

	public void carregaGrupos() throws SQLException{


		CampeonatoDAO dao = new CampeonatoDAOImpl();
		times = dao.quartasdeFinal();
	}


	//FORMATA JOGOO A
	public void formataTabJogoA(){

		List<String[]> linhas = new ArrayList<>();

		if( !times.isEmpty() ){

				String[] g = {
						times.get(0).getNomeTime(),
						" X ",
						times.get(2).getNomeTime(),
				};
				linhas.add(g);
		} 
		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tabJogoA.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centro.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = { "Time 1 do Grupo A", "", "Time 1 do Grupo B"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDICAO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO A LINHA INTEIRA
		tabJogoA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabJogoA.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabJogoA.getColumnModel().getColumn(0).setCellRenderer(centro);
		tabJogoA.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabJogoA.getColumnModel().getColumn(2).setCellRenderer(centro);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tabJogoA.getColumnModel().getColumn(0).setPreferredWidth(160);
		tabJogoA.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabJogoA.getColumnModel().getColumn(2).setPreferredWidth(160);
	}

	//FORMATA JOGO B
		public void formataTabJogoB(){

			List<String[]> linhas = new ArrayList<>();

			if( !times.isEmpty() ){

					String[] g = {
							times.get(1).getNomeTime(),
							" X ",
							times.get(3).getNomeTime(),
					};
					linhas.add(g);
			} 
			//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
			((DefaultTableCellRenderer) tabJogoB.getTableHeader().getDefaultRenderer())
			.setHorizontalAlignment(SwingConstants.CENTER);

			//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
			DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

			esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
			centro.setHorizontalAlignment(SwingConstants.CENTER);  
			direita.setHorizontalAlignment(SwingConstants.RIGHT);

			//NOMES DAS COLUNAS DA TABELA
			String[] nomesColunas = { "Time 2 do Grupo A", "", "Time 2 do Grupo B"};

			//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
			@SuppressWarnings("serial")
			DefaultTableModel model = new DefaultTableModel(
					linhas.toArray(new String[linhas.size()][]), nomesColunas)
			//TRAVA A EDICAO DAS CELULAS
			{  		  
				boolean[] canEdit = new boolean []{    
						false, false, false
				};
				@Override    
				public boolean isCellEditable(int rowIndex, int columnIndex) {    
					return canEdit [columnIndex];
				}  
			};

			//DEFINE COMO SELECAO A LINHA INTEIRA
			tabJogoB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			//DEFINE O MODEL DA TABELA
			tabJogoB.setModel(model);

			//DEFINE O ALINHAMENTO DAS COLUNAS
			tabJogoB.getColumnModel().getColumn(0).setCellRenderer(centro);
			tabJogoB.getColumnModel().getColumn(1).setCellRenderer(centro);
			tabJogoB.getColumnModel().getColumn(2).setCellRenderer(centro);

			//CONFIGURA O TAMANHO DAS COLUNAS
			tabJogoB.getColumnModel().getColumn(0).setPreferredWidth(160);
			tabJogoB.getColumnModel().getColumn(1).setPreferredWidth(20);
			tabJogoB.getColumnModel().getColumn(2).setPreferredWidth(160);
		}
	
		
		//FORMATA JOGO D
		public void formataTabJogoC(){

			List<String[]> linhas = new ArrayList<>();

			if( !times.isEmpty() ){

					String[] g = {
							times.get(4).getNomeTime(),
							" X ",
							times.get(6).getNomeTime(),
					};
					linhas.add(g);
			} 
			//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
			((DefaultTableCellRenderer) tabJogoC.getTableHeader().getDefaultRenderer())
			.setHorizontalAlignment(SwingConstants.CENTER);

			//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
			DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

			esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
			centro.setHorizontalAlignment(SwingConstants.CENTER);  
			direita.setHorizontalAlignment(SwingConstants.RIGHT);

			//NOMES DAS COLUNAS DA TABELA
			String[] nomesColunas = { "Time 1 do Grupo C", "", "Time 1 do Grupo D"};

			//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
			@SuppressWarnings("serial")
			DefaultTableModel model = new DefaultTableModel(
					linhas.toArray(new String[linhas.size()][]), nomesColunas)
			//TRAVA A EDICAO DAS CELULAS
			{  		  
				boolean[] canEdit = new boolean []{    
						false, false, false
				};
				@Override    
				public boolean isCellEditable(int rowIndex, int columnIndex) {    
					return canEdit [columnIndex];
				}  
			};

			//DEFINE COMO SELECAO A LINHA INTEIRA
			tabJogoC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			//DEFINE O MODEL DA TABELA
			tabJogoC.setModel(model);

			//DEFINE O ALINHAMENTO DAS COLUNAS
			tabJogoC.getColumnModel().getColumn(0).setCellRenderer(centro);
			tabJogoC.getColumnModel().getColumn(1).setCellRenderer(centro);
			tabJogoC.getColumnModel().getColumn(2).setCellRenderer(centro);

			//CONFIGURA O TAMANHO DAS COLUNAS
			tabJogoC.getColumnModel().getColumn(0).setPreferredWidth(160);
			tabJogoC.getColumnModel().getColumn(1).setPreferredWidth(20);
			tabJogoC.getColumnModel().getColumn(2).setPreferredWidth(160);
		}
		
		//FORMATA GRUPO A
		public void formataTabJogoD(){

			List<String[]> linhas = new ArrayList<>();

			if( !times.isEmpty() ){

					String[] g = {
							times.get(5).getNomeTime(),
							" X ",
							times.get(7).getNomeTime(),
					};
					linhas.add(g);
			} 
			//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
			((DefaultTableCellRenderer) tabJogoD.getTableHeader().getDefaultRenderer())
			.setHorizontalAlignment(SwingConstants.CENTER);

			//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
			DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

			esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
			centro.setHorizontalAlignment(SwingConstants.CENTER);  
			direita.setHorizontalAlignment(SwingConstants.RIGHT);

			//NOMES DAS COLUNAS DA TABELA
			String[] nomesColunas = { "Time 2 do Grupo C", "", "Time 2 do Grupo D"};

			//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
			@SuppressWarnings("serial")
			DefaultTableModel model = new DefaultTableModel(
					linhas.toArray(new String[linhas.size()][]), nomesColunas)
			//TRAVA A EDICAO DAS CELULAS
			{  		  
				boolean[] canEdit = new boolean []{    
						false, false, false
				};
				@Override    
				public boolean isCellEditable(int rowIndex, int columnIndex) {    
					return canEdit [columnIndex];
				}  
			};

			//DEFINE COMO SELECAO A LINHA INTEIRA
			tabJogoD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			//DEFINE O MODEL DA TABELA
			tabJogoD.setModel(model);

			//DEFINE O ALINHAMENTO DAS COLUNAS
			tabJogoD.getColumnModel().getColumn(0).setCellRenderer(centro);
			tabJogoD.getColumnModel().getColumn(1).setCellRenderer(centro);
			tabJogoD.getColumnModel().getColumn(2).setCellRenderer(centro);

			//CONFIGURA O TAMANHO DAS COLUNAS
			tabJogoD.getColumnModel().getColumn(0).setPreferredWidth(160);
			tabJogoD.getColumnModel().getColumn(1).setPreferredWidth(20);
			tabJogoD.getColumnModel().getColumn(2).setPreferredWidth(160);
		}


	public void fechar(){
		if(janela != null)
			janela.dispose();
	}


	public ActionListener preencherTabela = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			try {
				carregaGrupos();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			formataTabJogoA();
			formataTabJogoB();
			formataTabJogoC();
			formataTabJogoD();
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

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

import model.Resultado;
import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;
import view.ResultadoView;
import view.MenuView;

public class ResultadoCtrl {
	private ResultadoView janela;
	private MenuView janelaMenu;
	private JTable tabGrupoA;
	private JTable tabGrupoB;
	private JTable tabGrupoC;
	private JTable tabGrupoD;
	private List<Resultado> grupoA;
	private List<Resultado> grupoB;
	private List<Resultado> grupoC;
	private List<Resultado> grupoD;

	public ResultadoCtrl( 
			ResultadoView janela, 
			JTable tabGrupoA, 
			JTable tabGrupoB, 
			JTable tabGrupoC,
			JTable tabGrupoD) throws SQLException {

		this.janela = janela;
		this.tabGrupoA = tabGrupoA;
		this.tabGrupoB = tabGrupoB;
		this.tabGrupoC = tabGrupoC;
		this.tabGrupoD = tabGrupoD;
		this.grupoA = new ArrayList<Resultado>();
		this.grupoB = new ArrayList<Resultado>();
		this.grupoC = new ArrayList<Resultado>();
		this.grupoD = new ArrayList<Resultado>();

		inicia();
	}

	public void inicia() throws SQLException{

		if( !grupoA.isEmpty() ){
			formataTabGrupoA();
			formataTabGrupoB();
			formataTabGrupoC();
			formataTabGrupoD();
		} else {
			formataTabGrupoA();
			formataTabGrupoB();
			formataTabGrupoC();
			formataTabGrupoD();
		}
	}

	public void carregaGrupos() throws SQLException{


		CampeonatoDAO dao = new CampeonatoDAOImpl();
		grupoA = dao.resultadoGrupo("A");
		grupoB = dao.resultadoGrupo("B");
		grupoC = dao.resultadoGrupo("C");
		grupoD = dao.resultadoGrupo("D");
	}


	//FORMATA GRUPO A
	public void formataTabGrupoA(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupoA.isEmpty() ){

			for (int i = 0; i < grupoA.size(); i++) {
				String[] g = {
						grupoA.get(i).getNomeTime(),
						Integer.toString(grupoA.get(i).getJogosDisputados()),
						Integer.toString(grupoA.get(i).getVitorias()),
						Integer.toString(grupoA.get(i).getEmpates()),
						Integer.toString(grupoA.get(i).getDerrotas()),
						Integer.toString(grupoA.get(i).getGolsMarcados()),
						Integer.toString(grupoA.get(i).getGolsSofridos()),
						Integer.toString(grupoA.get(i).getSaldoGols()),
						Integer.toString(grupoA.get(i).getPontos())
				};
				linhas.add(g);
			}
		} 
		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tabGrupoA.getTableHeader().getDefaultRenderer())
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
		tabGrupoA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabGrupoA.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabGrupoA.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabGrupoA.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabGrupoA.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabGrupoA.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabGrupoA.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabGrupoA.getColumnModel().getColumn(5).setCellRenderer(centro);
		tabGrupoA.getColumnModel().getColumn(6).setCellRenderer(centro);
		tabGrupoA.getColumnModel().getColumn(7).setCellRenderer(centro);
		tabGrupoA.getColumnModel().getColumn(8).setCellRenderer(centro);


		//CONFIGURA O TAMANHO DAS COLUNAS
		tabGrupoA.getColumnModel().getColumn(0).setPreferredWidth(160);
		tabGrupoA.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabGrupoA.getColumnModel().getColumn(2).setPreferredWidth(20);
		tabGrupoA.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabGrupoA.getColumnModel().getColumn(4).setPreferredWidth(15);
		tabGrupoA.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabGrupoA.getColumnModel().getColumn(6).setPreferredWidth(45);
		tabGrupoA.getColumnModel().getColumn(7).setPreferredWidth(45);
		tabGrupoA.getColumnModel().getColumn(8).setPreferredWidth(10);
	}


	//FORMATA GRUPO B
	public void formataTabGrupoB(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupoB.isEmpty() ){

			for (int i = 0; i < grupoB.size(); i++) {
				String[] g = {
						grupoB.get(i).getNomeTime(),
						Integer.toString(grupoB.get(i).getJogosDisputados()),
						Integer.toString(grupoB.get(i).getVitorias()),
						Integer.toString(grupoB.get(i).getEmpates()),
						Integer.toString(grupoB.get(i).getDerrotas()),
						Integer.toString(grupoB.get(i).getGolsMarcados()),
						Integer.toString(grupoB.get(i).getGolsSofridos()),
						Integer.toString(grupoB.get(i).getSaldoGols()),
						Integer.toString(grupoB.get(i).getPontos())
				};
				linhas.add(g);
			}
		} 

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tabGrupoB.getTableHeader().getDefaultRenderer())
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
		tabGrupoB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabGrupoB.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabGrupoB.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabGrupoB.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabGrupoB.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabGrupoB.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabGrupoB.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabGrupoB.getColumnModel().getColumn(5).setCellRenderer(centro);
		tabGrupoB.getColumnModel().getColumn(6).setCellRenderer(centro);
		tabGrupoB.getColumnModel().getColumn(7).setCellRenderer(centro);
		tabGrupoB.getColumnModel().getColumn(8).setCellRenderer(centro);


		//CONFIGURA O TAMANHO DAS COLUNAS
		tabGrupoB.getColumnModel().getColumn(0).setPreferredWidth(160);
		tabGrupoB.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabGrupoB.getColumnModel().getColumn(2).setPreferredWidth(20);
		tabGrupoB.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabGrupoB.getColumnModel().getColumn(4).setPreferredWidth(15);
		tabGrupoB.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabGrupoB.getColumnModel().getColumn(6).setPreferredWidth(45);
		tabGrupoB.getColumnModel().getColumn(7).setPreferredWidth(45);
		tabGrupoB.getColumnModel().getColumn(8).setPreferredWidth(10);
	}


	//FORMATA GRUPO C
	public void formataTabGrupoC(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupoC.isEmpty() ){

			for (int i = 0; i < grupoC.size(); i++) {
				String[] g = {
						grupoC.get(i).getNomeTime(),
						Integer.toString(grupoC.get(i).getJogosDisputados()),
						Integer.toString(grupoC.get(i).getVitorias()),
						Integer.toString(grupoC.get(i).getEmpates()),
						Integer.toString(grupoC.get(i).getDerrotas()),
						Integer.toString(grupoC.get(i).getGolsMarcados()),
						Integer.toString(grupoC.get(i).getGolsSofridos()),
						Integer.toString(grupoC.get(i).getSaldoGols()),
						Integer.toString(grupoC.get(i).getPontos())
				};
				linhas.add(g);
			}
		} 

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tabGrupoC.getTableHeader().getDefaultRenderer())
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
		tabGrupoC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabGrupoC.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabGrupoC.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabGrupoC.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabGrupoC.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabGrupoC.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabGrupoC.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabGrupoC.getColumnModel().getColumn(5).setCellRenderer(centro);
		tabGrupoC.getColumnModel().getColumn(6).setCellRenderer(centro);
		tabGrupoC.getColumnModel().getColumn(7).setCellRenderer(centro);
		tabGrupoC.getColumnModel().getColumn(8).setCellRenderer(centro);


		//CONFIGURA O TAMANHO DAS COLUNAS
		tabGrupoC.getColumnModel().getColumn(0).setPreferredWidth(160);
		tabGrupoC.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabGrupoC.getColumnModel().getColumn(2).setPreferredWidth(20);
		tabGrupoC.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabGrupoC.getColumnModel().getColumn(4).setPreferredWidth(15);
		tabGrupoC.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabGrupoC.getColumnModel().getColumn(6).setPreferredWidth(45);
		tabGrupoC.getColumnModel().getColumn(7).setPreferredWidth(45);
		tabGrupoC.getColumnModel().getColumn(8).setPreferredWidth(10);
	}


	//FORMATA GRUPO D
	public void formataTabGrupoD(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupoD.isEmpty() ){

			for (int i = 0; i < grupoD.size(); i++) {
				String[] g = {
						grupoD.get(i).getNomeTime(),
						Integer.toString(grupoD.get(i).getJogosDisputados()),
						Integer.toString(grupoD.get(i).getVitorias()),
						Integer.toString(grupoD.get(i).getEmpates()),
						Integer.toString(grupoD.get(i).getDerrotas()),
						Integer.toString(grupoD.get(i).getGolsMarcados()),
						Integer.toString(grupoD.get(i).getGolsSofridos()),
						Integer.toString(grupoD.get(i).getSaldoGols()),
						Integer.toString(grupoD.get(i).getPontos())
				};
				linhas.add(g);
			}
		} 

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tabGrupoD.getTableHeader().getDefaultRenderer())
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
		tabGrupoD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabGrupoD.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabGrupoD.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabGrupoD.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabGrupoD.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabGrupoD.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabGrupoD.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabGrupoD.getColumnModel().getColumn(5).setCellRenderer(centro);
		tabGrupoD.getColumnModel().getColumn(6).setCellRenderer(centro);
		tabGrupoD.getColumnModel().getColumn(7).setCellRenderer(centro);
		tabGrupoD.getColumnModel().getColumn(8).setCellRenderer(centro);


		//CONFIGURA O TAMANHO DAS COLUNAS
		tabGrupoD.getColumnModel().getColumn(0).setPreferredWidth(160);
		tabGrupoD.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabGrupoD.getColumnModel().getColumn(2).setPreferredWidth(20);
		tabGrupoD.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabGrupoD.getColumnModel().getColumn(4).setPreferredWidth(15);
		tabGrupoD.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabGrupoD.getColumnModel().getColumn(6).setPreferredWidth(45);
		tabGrupoD.getColumnModel().getColumn(7).setPreferredWidth(45);
		tabGrupoD.getColumnModel().getColumn(8).setPreferredWidth(10);
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
			formataTabGrupoA();
			formataTabGrupoB();
			formataTabGrupoC();
			formataTabGrupoD();
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

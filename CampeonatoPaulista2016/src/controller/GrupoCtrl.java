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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOException;
import persistence.CampeonatoDAOImpl;
import view.GrupoView;
import view.MenuView;
import model.Grupo;

public class GrupoCtrl {

	private GrupoView janela;
	private MenuView janelaMenu;
	private JTable tbGrupoA;
	private JTable tbGrupoB;
	private JTable tbGrupoC;
	private JTable tbGrupoD;
	private JButton btnApagar;
	private List<Grupo> grupos;

	public GrupoCtrl(
			GrupoView janela, 
			JTable tbGrupoA, 
			JTable tbGrupoB, 
			JTable tbGrupoC,
			JTable tbGrupoD, 
			JButton btnApagar) {

		this.janela = janela;
		this.tbGrupoA = tbGrupoA;
		this.tbGrupoB = tbGrupoB;
		this.tbGrupoC = tbGrupoC;
		this.tbGrupoD = tbGrupoD;
		this.btnApagar = btnApagar;
		this.grupos = new ArrayList<Grupo>();

		inicia();
	}

	public void inicia(){
		try {
			consultaGrupos();
		} catch (CampeonatoDAOException e) {
			e.printStackTrace();
		}
		if( !grupos.isEmpty() ){
			formataTabGrupoA();
			formataTabGrupoB();
			formataTabGrupoC();
			formataTabGrupoD();
			btnApagar.setEnabled(true);
		} else {
			formataTabGrupoA();
			formataTabGrupoB();
			formataTabGrupoC();
			formataTabGrupoD();
			btnApagar.setEnabled(false);
		}
	}

	public void carregaGrupo() throws CampeonatoDAOException {

		CampeonatoDAO dao = new CampeonatoDAOImpl();
		dao.geraGrupos();
		consultaGrupos();
	}

	public void consultaGrupos() throws CampeonatoDAOException {

		CampeonatoDAO dao = new CampeonatoDAOImpl();
		grupos = dao.consultaGrupos();
	}

	//FORMATA GRUPO A
	public void formataTabGrupoA(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupos.isEmpty() ){

			for (int i = 0; i < grupos.size(); i++) {
				if( grupos.get(i).getGrupo().equalsIgnoreCase( "A" )){
					String[] g = {
							//grupos.get(i).getGrupo(),
							grupos.get(i).getTime(),
					};
					linhas.add(g);
				}
			}
		} 

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tbGrupoA.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = {"Clubes"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDICAO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO A LINHA INTEIRA
		tbGrupoA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tbGrupoA.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tbGrupoA.getColumnModel().getColumn(0).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbGrupoA.getColumnModel().getColumn(0).setPreferredWidth(100);
	}

	//FORMATA GRUPO B
	public void formataTabGrupoB(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupos.isEmpty() ){

			for (int i = 0; i < grupos.size(); i++) {
				if( grupos.get(i).getGrupo().equalsIgnoreCase( "B" )){
					String[] g = {
							//grupos.get(i).getGrupo(),
							grupos.get(i).getTime(),
					};
					linhas.add(g);
				}
			}
		} 

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tbGrupoB.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = {"Clubes"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDICAO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO A LINHA INTEIRA
		tbGrupoB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tbGrupoB.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tbGrupoB.getColumnModel().getColumn(0).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbGrupoB.getColumnModel().getColumn(0).setPreferredWidth(100);
	}

	//FORMATA GRUPO C
	public void formataTabGrupoC(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupos.isEmpty() ){

			for (int i = 0; i < grupos.size(); i++) {
				if( grupos.get(i).getGrupo().equalsIgnoreCase( "C" )){
					String[] g = {
							//grupos.get(i).getGrupo(),
							grupos.get(i).getTime(),
					};
					linhas.add(g);
				}
			}
		} 

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tbGrupoC.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = {"Clubes"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDICAO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO A LINHA INTEIRA
		tbGrupoC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tbGrupoC.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tbGrupoC.getColumnModel().getColumn(0).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbGrupoC.getColumnModel().getColumn(0).setPreferredWidth(100);
	}

	//FORMATA GRUPO D
	public void formataTabGrupoD(){

		List<String[]> linhas = new ArrayList<>();

		if( !grupos.isEmpty() ){

			for (int i = 0; i < grupos.size(); i++) {
				if( grupos.get(i).getGrupo().equalsIgnoreCase( "D" )){
					String[] g = {
							//grupos.get(i).getGrupo(),
							grupos.get(i).getTime(),
					};
					linhas.add(g);
				}
			}
		} 

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tbGrupoD.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = {"Clubes"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDICAO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO A LINHA INTEIRA
		tbGrupoD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tbGrupoD.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tbGrupoD.getColumnModel().getColumn(0).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbGrupoD.getColumnModel().getColumn(0).setPreferredWidth(100);
	}

	public void fechar(){
		if(janela != null)
			janela.dispose();
	}

	public ActionListener preencherTabela = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			try {
				carregaGrupo();
				formataTabGrupoA();
				formataTabGrupoB();
				formataTabGrupoC();
				formataTabGrupoD();
				btnApagar.setEnabled(true);
			} catch (CampeonatoDAOException e1) {
				e1.printStackTrace();
			}
		}
	};

	public ActionListener apagar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			Object[] excluir = { "Confirmar", "Cancelar" };  
			int ex = JOptionPane.showOptionDialog(null, 
					"Você confirma a exclusão dos Grupos?",
					"Exclusão dos Grupos", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( "../CampeonatoPaulista2016/src/resources/warning.png" ), excluir, excluir[1]);
			if (ex == 0) { 
				try {
					CampeonatoDAO dao = new CampeonatoDAOImpl();
					dao.apagaJogos();
					dao.apagaGrupos();
					
					while (tbGrupoA.getModel().getRowCount() > 0) {  
				           ((DefaultTableModel) tbGrupoA.getModel()).removeRow(0);  
				       }
					while (tbGrupoB.getModel().getRowCount() > 0) {  
				           ((DefaultTableModel) tbGrupoB.getModel()).removeRow(0);  
				       }
					while (tbGrupoC.getModel().getRowCount() > 0) {  
				           ((DefaultTableModel) tbGrupoC.getModel()).removeRow(0);  
				       }
					while (tbGrupoD.getModel().getRowCount() > 0) {  
				           ((DefaultTableModel) tbGrupoD.getModel()).removeRow(0);  
				       }
					tbGrupoA.updateUI();
					tbGrupoB.updateUI();
					tbGrupoC.updateUI();
					tbGrupoD.updateUI();
					btnApagar.setEnabled(false);
				} catch (CampeonatoDAOException e1) {
					e1.printStackTrace();
				} 			
			}
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
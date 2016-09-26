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

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOException;
import persistence.CampeonatoDAOImpl;
import model.Grupo;

public class GrupoCtrl {

	private JTable tbGrupoA;
	private JTable tbGrupoB;
	private JTable tbGrupoC;
	private JTable tbGrupoD;
	private List<Grupo> grupos;

	public GrupoCtrl(
			JTable tbGrupoA, 
			JTable tbGrupoB, 
			JTable tbGrupoC,
			JTable tbGrupoD) {
		// TODO Auto-generated constructor stub

		this.tbGrupoA = tbGrupoA;
		this.tbGrupoB = tbGrupoB;
		this.tbGrupoC = tbGrupoC;
		this.tbGrupoD = tbGrupoD;
		this.grupos = new ArrayList<Grupo>();
	}
	
	public void carregaGrupo() throws CampeonatoDAOException {
		
		CampeonatoDAO dao = new CampeonatoDAOImpl();
		grupos = dao.consultaGrupos("A");
		List<Grupo> dados = new ArrayList<Grupo>();
		grupos = dados;
	}

	public void formataTabGrupoA(){

		List<String[]> linhas = new ArrayList<>();
		if( !grupos.isEmpty() ){

			for (int i = 0; i < grupos.size(); i++) {
				String[] g = {
						grupos.get(i).getGrupo(),
						grupos.get(i).getTime(),
				};
				linhas.add(g);
			}
		} else {
			JOptionPane.showMessageDialog(null, 
					"Problema ao carregar a Base de Dados!", 
					"Erro no Sistema", 
					JOptionPane.PLAIN_MESSAGE);
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
		String[] nomesColunas = { "Grupo","Time"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDIÇÃO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELEÇÃO TODA A LINHA
		tbGrupoA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tbGrupoA.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tbGrupoA.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		tbGrupoA.getColumnModel().getColumn(1).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbGrupoA.getColumnModel().getColumn(0).setPreferredWidth(20);
		tbGrupoA.getColumnModel().getColumn(1).setPreferredWidth(100);
	}

	public ActionListener preencherTabela = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				carregaGrupo();
			} catch (CampeonatoDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			formataTabGrupoA();
		}
	};
}
/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFormattedTextField;
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
import view.JogoView;
import view.MenuView;
import model.Jogo;

public class JogoCtrl {

	private JogoView janela;
	private MenuView janelaMenu;
	private JFormattedTextField ftxtData;
	private JTable tabela;
	private List<Jogo>jogos;

	public JogoCtrl(JogoView janela, JFormattedTextField ftxtData, JTable tabela) {

		this.janela = janela;
		this.ftxtData = ftxtData;
		this.tabela = tabela;
		this.jogos = new ArrayList<Jogo>();
	}

	public void carregaJogo(Date dtInicio) throws CampeonatoDAOException {

		CampeonatoDAO dao = new CampeonatoDAOImpl();
		dao.geraJogos(dtInicio);
		jogos = dao.consultaJogos();
	}
	
	public void pesquisaJogo(Date data) throws CampeonatoDAOException {

		CampeonatoDAO dao = new CampeonatoDAOImpl();
		jogos = dao.consultaDataJogos(data);
	}

	public void formataTabela(){

		List<String[]> linhas = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if( !jogos.isEmpty() ){
			for (int i = 0; i < jogos.size(); i++) {
				String[] j = {
						sdf.format( jogos.get(i).getData() ),
						jogos.get(i).getTimeA(),
						jogos.get(i).getTimeB(),
				};
				linhas.add(j);
			}
		} else {
			JOptionPane.showMessageDialog(null, 
					"Problema ao carregar a Base de Dados!", 
					"Erro no Sistema", 
					JOptionPane.PLAIN_MESSAGE);
		}

		//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = {"Data","Time A", "Time B"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDIÇÃO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false  
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO TODA A LINHA
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabela.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(1).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(2).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);

		if( linhas.isEmpty()){
			JOptionPane.showMessageDialog(null, 
					"Problema ao carregar a Tabela!"
							+ "\n" + jogos.size() + " registros foram carregados com sucesso.", 
							"Erro no Sistema", 
							JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void fechar(){
		if(janela != null)
			janela.dispose();
	}


	public ActionListener preencherTabela = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try {
				java.util.Date dtInicio = new java.util.Date(sdf.parse((String) ftxtData.getValue()).getTime());
				carregaJogo( dtInicio );
				formataTabela();
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (CampeonatoDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	};
	
	public ActionListener verificaData = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try {
				java.util.Date data = new java.util.Date(sdf.parse((String) ftxtData.getValue()).getTime());
				pesquisaJogo( data );
				formataTabela();
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (CampeonatoDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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
	private JLabel lblData;
	private JLabel lblJogos;
	private JButton btnVerificar;
	private JButton btnGerarJogos;
	private JFormattedTextField ftxtData;
	private JTable tabela;
	private List<Jogo>jogos;

	public JogoCtrl(
			JogoView janela, 
			JLabel lblData, 
			JLabel lblJogos, 
			JButton btnVerificar, 
			JButton btnGerarJogos, 
			JFormattedTextField ftxtData, 
			JTable tabela) {

		this.janela = janela;
		this.lblData = lblData;
		this.lblJogos = lblJogos;
		this.btnVerificar = btnVerificar;
		this.btnGerarJogos = btnGerarJogos;
		this.ftxtData = ftxtData;
		this.tabela = tabela;
		this.jogos = new ArrayList<Jogo>();
	}

	public void focarCampo(){

		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				ftxtData.requestFocus();  
			}  
		});
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
						" X ",
						jogos.get(i).getTimeB(),
				};
				linhas.add(j);
				lblData.setText("Digite a data da Rodada:");
				btnVerificar.setVisible(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, 
					"Não foram encontradas partidas!"
							+ "\n\nNesta data não existem jogos "
							+ "\ndo Campeonato.", 
							"Não encontrado", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( "../CampeonatoPaulista2016/src/resources/warning.png" ));
			ftxtData.setValue("");
			focarCampo();
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
		String[] nomesColunas = {"Data","Time A", "Contra", "Time B"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDIÇÃO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false, false  
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];
			}  
		};

		//DEFINE COMO SELECAO A LINHA INTEIRA
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabela.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(1).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(3).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(170);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(5);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(170);
	}

	public void fechar(){
		if(janela != null)
			janela.dispose();
	}


	public ActionListener preencherTabela = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if( ftxtData.getValue() != null ){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				try {
					java.util.Date dtInicio = new java.util.Date(sdf.parse( (String) ftxtData.getValue()).getTime() );
					carregaJogo( dtInicio );
					formataTabela();
					ftxtData.setValue("");
					btnGerarJogos.setVisible(false);
					lblJogos.setText( jogos.size() + " Jogos do Campeonato:" );
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (CampeonatoDAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, 
						"Por favor, digite uma data válida!"
								+ "\n\nÉ necessário informar o início "
								+ "\ndo Campeonato no campo 'Data'.", 
								"Atenção", 
								JOptionPane.PLAIN_MESSAGE,
								new ImageIcon( "../CampeonatoPaulista2016/src/resources/error.png" ));
				focarCampo();
			}
		}
	};

	public ActionListener verificaData = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if( ftxtData.getValue() != null && ftxtData.getValue() != "" ){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				try {
					java.util.Date data = new java.util.Date(sdf.parse((String) ftxtData.getValue()).getTime());
					pesquisaJogo( data );
					formataTabela();
					lblJogos.setText( jogos.size() + " Jogos do Campeonato nesta rodada:" );
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (CampeonatoDAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, 
						"Por favor, digite uma data válida!"
								+ "\n\nÉ necessário informar uma data "
								+ "\npara verificar as partidas da rodada disponíveis.", 
								"Atenção", 
								JOptionPane.PLAIN_MESSAGE,
								new ImageIcon( "../CampeonatoPaulista2016/src/resources/error.png" ));
				focarCampo();
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
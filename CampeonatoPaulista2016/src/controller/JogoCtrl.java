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
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Jogo;
import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;
import view.JogoView;
import view.MenuView;

public class JogoCtrl {

	private JogoView janela;
	private MenuView janelaMenu;
	private JLabel lblData;
	private JLabel lblJogos;
	private JRadioButton rdbtnTrigger;
	private JButton btnVerificar;
	private JButton btnGerar;
	private JButton btnApagar;
	private JFormattedTextField ftxtData;
	private JTable tabela;
	private boolean validar;
	private int trigger;
	private List<Jogo>jogos;

	public JogoCtrl(
			JogoView janela, 
			JLabel lblData, 
			JLabel lblJogos, 
			JRadioButton rdbtnTrigger, 
			JButton btnVerificar, 
			JButton btnGerar, 
			JButton btnApagar, 
			JFormattedTextField ftxtData, 
			JTable tabela) {

		this.janela = janela;
		this.lblData = lblData;
		this.lblJogos = lblJogos;
		this.rdbtnTrigger = rdbtnTrigger;
		this.btnVerificar = btnVerificar;
		this.btnGerar = btnGerar;
		this.btnApagar = btnApagar;
		this.ftxtData = ftxtData;
		this.tabela = tabela;
		this.jogos = new ArrayList<Jogo>();

		inicia();
	}

	public void inicia(){

		pesquisaJogo(null);

		if( !jogos.isEmpty() ){
			formataTabela();
		} else {
			formataTabela();
			btnApagar.setEnabled(false);
			ftxtData.setText("31012016");
		}
	}

	protected void ativaTrigger() {

		if( rdbtnTrigger.isSelected() ){
			trigger = 1;
		} else {
			trigger = 0;
		}
	}

	public void focarCampo(){

		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				if( !jogos.isEmpty() ){
					ftxtData.requestFocus(); 
				} else {
					btnGerar.requestFocus();
				}
			}  
		});
	}

	public void carregaJogo( Date dtInicio ) {

		CampeonatoDAO dao = new CampeonatoDAOImpl();

		try {

			dao.geraJogos( dtInicio, trigger );
			jogos = dao.consultaJogos();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizaJogo() throws SQLException, ParseException{
		
		CampeonatoDAO dao = new CampeonatoDAOImpl();
		dao.atualizaJogos( tabelaToJogo());
	}

	public void pesquisaJogo( Date data ) {

		CampeonatoDAO dao = new CampeonatoDAOImpl();

		try {

			jogos = dao.consultaDataJogos(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void apagarJogos(){

		ativaTrigger();

		Object[] excluir = { "Confirmar", "Cancelar" };  
		int ex = JOptionPane.showOptionDialog(null, 
				"Você confirma a exclusão dos Jogos do Campeonato?",
				"Exclusão dos Jogos", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
				new ImageIcon( "../CampeonatoPaulista2016/src/resources/warning.png" ), excluir, excluir[1]);
		if (ex == 0) { 
			try {
				CampeonatoDAO dao = new CampeonatoDAOImpl();
				dao.apagaJogos();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while (tabela.getModel().getRowCount() > 0) {  
				((DefaultTableModel) tabela.getModel()).removeRow(0);  
			}

			tabela.updateUI();
			lblData.setText("Início do Campeonato:");
			lblJogos.setText("Jogos do Campeonato:");
			btnVerificar.setVisible(false);
			btnGerar.setVisible(true);
			btnApagar.setEnabled(false);
			ftxtData.setText("31012016");
			focarCampo();
		}
	}

	public void carregaTabela(){

		ativaTrigger();

		if( ftxtData.getValue() != null && ftxtData.getText() != null ){

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date dtInicio;
				dtInicio = new java.util.Date(sdf.parse( (String) ftxtData.getValue()).getTime() );
				carregaJogo( dtInicio );
				formataTabela();
				if( trigger != 1 ){
					ftxtData.setValue("");
					btnGerar.setVisible(false);
					lblJogos.setText( jogos.size() + " Jogos do Campeonato:" );
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, 
					"Por favor, digite uma data válida!"
							+ "\n\nÉ necessário informar o início "
							+ "\ndo Campeonato.", 
							"Atenção", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( "../CampeonatoPaulista2016/src/resources/error.png" ));
			focarCampo();
		}
	}
	
	public List<Jogo> tabelaToJogo() throws ParseException{
		
		List<Jogo> gols = new ArrayList<Jogo>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 0; i < jogos.size(); i++) {
			Jogo j = new Jogo();
			j.setData( sdf.parse( (String) tabela.getModel().getValueAt( i , 0 ) ));
			j.setTimeA( tabela.getModel().getValueAt( i , 1 ).toString() );
			//j.setCodigoTimeA( Integer.parseInt( (String) tabela.getModel().getValueAt( i , 2 ).toString() ));
			j.setGolTimeA( Integer.parseInt( (String) tabela.getModel().getValueAt( i , 3 ) ));
			j.setGolTimeB( Integer.parseInt( (String) tabela.getModel().getValueAt( i , 5 ) ));
			//j.setCodigoTimeB( Integer.parseInt( (String) tabela.getModel().getValueAt( i , 6 ).toString() ));
			j.setTimeB( tabela.getModel().getValueAt( i , 7 ).toString() );
			gols.add(j);
		}
		return gols;		
	}

	public void validaData(){

		if( ftxtData.getValue() != null && ftxtData.getText() != null ){

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date data;
				data = new java.util.Date(sdf.parse((String) ftxtData.getValue()).getTime());
				pesquisaJogo( data );
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, 
						"Por favor, digite uma data válida!"
								+ "\n\nÉ necessário informar uma data "
								+ "\npara verificar as partidas da rodada disponíveis.", 
								"Atenção", 
								JOptionPane.PLAIN_MESSAGE,
								new ImageIcon( "../CampeonatoPaulista2016/src/resources/error.png" ));
				focarCampo();
				e1.printStackTrace();
			}
			if ( !jogos.isEmpty() ){
				formataTabela();
				lblJogos.setText( jogos.size() + " Jogos do Campeonato nesta rodada:" );
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

	public void formataTabela(){

		List<String[]> linhas = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if( !jogos.isEmpty() ){
			if( validar != true ){
				for (int i = 0; i < jogos.size(); i++) {
					String[] j = {
							sdf.format( jogos.get(i).getData() ),
							jogos.get(i).getTimeA(),
							"",
							"",
							" X ",
							"",
							"",
							jogos.get(i).getTimeB(),
					};
					linhas.add(j);
					lblData.setText("Digite a data da Rodada:");
					btnVerificar.setVisible(true);
					btnGerar.setVisible(false);
					btnApagar.setEnabled(true);
				}
			} else {
				for (int i = 0; i < jogos.size(); i++) {
					String[] j = {
							sdf.format( jogos.get(i).getData() ),
							jogos.get(i).getTimeA(),
							"",
							//Integer.toString( jogos.get(i).getCodigoTimeA() ),
							Integer.toString( 0+(int)( 6*Math.random() )),
							" X ",
							Integer.toString( 0+(int)( 6*Math.random() )),
							//Integer.toString( jogos.get(i).getCodigoTimeB() ),
							"",
							jogos.get(i).getTimeB(),
					};
					linhas.add(j);
					lblData.setText("Digite a data da Rodada:");
					btnVerificar.setVisible(true);
					btnGerar.setVisible(false);
					btnApagar.setEnabled(true);
				}
			}
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
		String[] nomesColunas = {"Data","Time A", "", "Gols", "Contra", "Gols", "", "Time B"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDIÇÃO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false, true, false, false, true, false  
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
		tabela.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(7).setCellRenderer(esquerda);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(140);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(3);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(140);
		
		//ESCONDE COLUNA
		tabela.getColumnModel().getColumn(2).setMinWidth(0);
		tabela.getColumnModel().getColumn(2).setMaxWidth(0);
		tabela.getColumnModel().getColumn(6).setMinWidth(0);
		tabela.getColumnModel().getColumn(6).setMaxWidth(0);
	}

	
	public void fechar(){
		if(janela != null)
			janela.dispose();
	}


	public ActionListener preencherTabela = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			carregaTabela();
		}
	};

	public ActionListener data = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			validaData();
		}
	};

	public ActionListener gerarGols = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			validar = true;
			formataTabela();
			try {
				try {
					atualizaJogo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	public ActionListener apagar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			apagarJogos();
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
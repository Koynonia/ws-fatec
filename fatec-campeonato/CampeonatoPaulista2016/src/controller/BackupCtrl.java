/**
 * @author Fernando Moraes Oliveira
 * MatÃ©ria Laboratório de Banco de Dados
 * 5Âº ADS - Tarde
 * Iniciado em 26/11/2016
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.DataBases;
import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;
import view.BackupView;
import view.MenuView;

public class BackupCtrl {

	private BackupView janela;
	private MenuView janelaMenu;
	private JLabel lblInfo;
	private JRadioButton rdbtnBkp;
	private JRadioButton rdbtnSGDB;
	private JTable tabela;
	private List<DataBases>databases;
	private String path;
	private String base;
	private String msg;

	public BackupCtrl(
			BackupView janela, 
			JLabel lblInfo, 
			JRadioButton rdbtnBkp,
			JRadioButton rdbtnSGDB,
			JTable tabela) {

		this.janela = janela;
		this.lblInfo = lblInfo;
		this.rdbtnBkp = rdbtnBkp;
		this.rdbtnSGDB = rdbtnSGDB;
		this.tabela = tabela;
		this.databases = new ArrayList<DataBases>();

		inicia();
	}

	public void inicia(){

		carregaDB();

		if( !databases.isEmpty() ){
			formataTabela();
			lblInfo.setText("Bases de Dados do SGDB: " + databases.size());
		} else {
			formataTabela();
			lblInfo.setText("Bases de Dados do SGDB: ");
		}
	}


	public void carregaDB() {

		CampeonatoDAO dao = new CampeonatoDAOImpl();

		try {
			databases = dao.listaDatabases();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Boolean realizaBackup(){

		CampeonatoDAO dao = new CampeonatoDAOImpl();

		if( rdbtnSGDB.isSelected() != false ){
			try {
				dao.backupBases( path + "\\" );
				//dao.backupBases( "C:\\Backup\\" );
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		} else if( baseBkp() != null ){
			try {
				dao.backupBase( path + "\\", base );
				//dao.backupBase( "C:\\Backup\\", base );
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}


	public void restauraBackup(){

		CampeonatoDAO dao = new CampeonatoDAOImpl();

		if( rdbtnSGDB.isSelected() != false ){
			try {
				dao.restauraBase(path + "\\", base);
				//dao.restauraBase("C:\\Backup\\", base);
			} catch (SQLException e) {
				msg +=  e.getMessage() + "\n";
			}
		} else {
			try {
				dao.restauraBase(path + "\\", base);
				//dao.restauraBase("C:\\Backup\\", base);
			} catch (SQLException e) {
				msg = e.getMessage();
			}
		}
	}


	public String baseBkp(){

		if ( tabela.getSelectedRowCount() == 0 ) {
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione uma Base na tabela.", 
					"Base não selecionada", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( "../CampeonatoPaulista2016/src/resources/error.png" ));
			base = null;
		} else {
			base = (String) tabela.getValueAt( tabela.getSelectedRow(), 1 );
		}
		return base;
	}


	public void formataTabela(){

		List<String[]> linhas = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdh = new SimpleDateFormat("HH:mm");

		if( !databases.isEmpty() ){
			for (int i = 0; i < databases.size(); i++) {
				String[] j = {
						Integer.toString( databases.get(i).getId() ),
						databases.get(i).getNome(), 
						sdf.format( databases.get(i).getData() ),
						sdh.format( databases.get(i).getData() )
				};
				linhas.add(j);
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
		String[] nomesColunas = {"","Nome","Data de Criação", "Hora de Criação"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDIÃ‡ÃƒO DAS CELULAS
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
		tabela.getColumnModel().getColumn(1).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centralizado);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(5);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(5);

		//ESCONDE COLUNA
		tabela.getColumnModel().getColumn(0).setMinWidth(0);
		tabela.getColumnModel().getColumn(0).setMaxWidth(0);
	}


	public void backup() throws IOException{

		JFileChooser fc = new JFileChooser(); //JFileChooser(System.getProperty("user.dir")); 
		fc.setDialogTitle("Selecione a pasta para o Backupâ€¦");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = fc.showSaveDialog(null);

		if (option == JFileChooser.APPROVE_OPTION) {
			File arquivo = fc.getSelectedFile();  
			path = arquivo.getAbsolutePath();
			if ( realizaBackup() !=false ){

				JOptionPane.showMessageDialog(null, 
						"Backup realizado com sucesso em:\n '" + path + "'", 
						"Backup realizado", 
						JOptionPane.PLAIN_MESSAGE,
						new ImageIcon( "../CampeonatoPaulista2016/src/resources/confirm.png" ));

				//				File diretorio = new File(path + "\\Backup");
				//			if (!diretorio.exists()) {
				//				diretorio.mkdir();
				//			}
			}
		} else {
			JOptionPane.showMessageDialog(null, 
					"Não foi selecionada uma pasta para o backup!"
							+ "\nPor favor selecione uma pasta para realiazar o backup", 
							"Diretótio não selecionado", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( "../CampeonatoPaulista2016/src/resources/warning.png" ));
		} 
	}


	public void restaura() throws IOException{

		JFileChooser fc = new JFileChooser(); //JFileChooser(System.getProperty("user.dir")); 
		fc.setDialogTitle("Selecione o arquivo para restauração");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int option = fc.showOpenDialog(null);

		if (option == JFileChooser.APPROVE_OPTION) {
			File arquivo = fc.getSelectedFile();  
			String p = arquivo.getAbsolutePath();
			
			if( arquivo.isFile() ){
				rdbtnBkp.setSelected(true);
				String str = arquivo.getName();
				base = str.substring(0, str.lastIndexOf("."));
				path = p.substring(0, p.lastIndexOf(System.getProperty("file.separator"))+1);
				for( int i = 0; i < databases.size(); i++ ){		
					if( databases.get(i).getNome().equalsIgnoreCase( base )){
						Object[] opt = { "Continuar", "Cancelar" };
						int restaurar = JOptionPane.showOptionDialog(null, 
								"DATABASE " + base + " encontrada!\n\nSe optar por continuar, a DATABASE" 
										+ " será sobrescrita.\nDeseja continuar?",
										"DATABASE existente!", 
										JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
										new ImageIcon( "../CampeonatoPaulista2016/src/resources/alert.png" ), opt, opt[1]);
						if (restaurar == 0) {
							restauraBackup();
						} else {
							return;
						}
					}
				}
			} else {
				rdbtnSGDB.setSelected(true);
				String[] arquivos = arquivo.list();
                for( String file : arquivos){
                	if( file.lastIndexOf(".") != -1 ){
                	base = file.substring(0, file.lastIndexOf("."));
                	restauraBackup();
                	//System.out.println(path+base);
                	}
                }
			}
			JOptionPane.showMessageDialog(null, 
					"DATABASE restaurada com sucesso:\n" + msg, 
					"Backup restaurado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( "../CampeonatoPaulista2016/src/resources/confirm.png" ));
		} else {
			JOptionPane.showMessageDialog(null, 
					"Não foi selecionada uma pasta ou o arquivo!"
							+ "\nPor favor selecione uma pasta ou arquivo para realiazar a restauração.", 
							"Diretótio ou Arquivo não selecionado", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( "../CampeonatoPaulista2016/src/resources/warning.png" ));
		} 
	}


	public void fechar(){
		if(janela != null)
			janela.dispose();
	}


	public ActionListener backupDB = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			try {
				backup();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	};

	public ActionListener restaurar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			try {
				restaura();
			} catch (IOException e1) {
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

	public MouseListener clicar = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			rdbtnBkp.setSelected(true);
		}
	};
}
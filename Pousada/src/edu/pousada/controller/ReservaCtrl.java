/**
 * @author Fernando Moraes Oliveira
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 24/10/2016
 */

package edu.pousada.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Reserva;

public class ReservaCtrl {

	private ReservaFrm form;
	private PrincipalFrm principal;
	private JTable tabela;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtValor;
	private JButton btnAlterar;
	private JButton btnRetirar; 
	private JButton btnLimpar; 
	private JButton btnConcluir; 
	private JButton btnVoltar;
	private String diretorio = "../Pousada/resources/";
	private LogonCtrl logon = LogonCtrl.getInstance();
	private boolean validar;
	private int quantidade = 1;
	private List<Reserva>reservas;

	public ReservaCtrl(
			ReservaFrm form, 
			JTable tabela, 
			JFormattedTextField ftxtQtd,  
			JFormattedTextField ftxtValor, 
			JButton btnAlterar, 
			JButton btnRetirar, 
			JButton btnLimpar, 
			JButton btnConcluir, 
			JButton btnVoltar
			) {

		this.form = form;
		this.tabela = tabela;
		this.ftxtQtd = ftxtQtd;
		this.ftxtValor = ftxtValor;
		this.btnAlterar = btnAlterar;
		this.btnRetirar = btnRetirar;
		this.btnLimpar = btnLimpar;
		this.btnConcluir = btnConcluir;
		this.btnVoltar = btnVoltar;
		this.reservas = new ArrayList<Reserva>();

		formatarTabela();
	}
	
	
	public void limpaCampo(){

		reservas.removeAll(reservas);
		atualizaDAO(reservas);
		formatarTabela();
		ftxtValor.setValue(null);
	}
	

	public void atualizaValor(){

		//Atualiza total da reserva
		float total = 0;
		int qtd = 0;
		for( int i = 0; i < reservas.size(); i++ ){
			total = total + ( reservas.get(i).getQuantidade() 
					* reservas.get(i).getVlrUnitario() );	
			qtd = qtd + ( reservas.get(i).getQuantidade() );
		}
		ftxtQtd.setValue( Integer.toString ( qtd ) );
		ftxtValor.setValue( total );
	}


	public void atualizaQtd(){

		reservas.clear();
		carregaDAO();
		form.setAlwaysOnTop ( false );
		
		if(tabela.getRowCount() > 0){
			if ( tabela.getSelectedRowCount() != 0){
				for(int i = 0; i <= reservas.size(); i ++){
					if ( tabela.isRowSelected(i)){
						if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
								.equals( reservas.get(i).getNumero() )){
							String resp = JOptionPane.showInputDialog( null,
									"Digite a quantidade desejada da reserva do chalé " 
											+ reservas.get(i).getChale()+ ":",
											"Alterar Quantidade…", JOptionPane.QUESTION_MESSAGE );
							if ( resp == null){
								return;
							} else {
								if ( ! logon.testarNumero( resp ) ){
									JOptionPane.showMessageDialog(null, 
											"Entrada inválida:\n\n" +
													resp +
													"\n\nPor favor, entre somente com números para a quantidade.", 
													"Entrada Inválida…", 
													JOptionPane.PLAIN_MESSAGE,
													new ImageIcon( diretorio + "/icons/error.png" ));
								} else {
									if ( resp.contains( "0" ) ) {
										removeLinha();
									} else {
										quantidade = Integer.parseInt( resp );
										validar = true;			
									}
								}
							}
							if ( validar != false){
								Reserva item = new Reserva();
								item.setQuantidade( quantidade );
								item.setDtCadastro( new Date() );
								reservas.set(i,item);
								//atualizarDAO(reservas);
								formatarTabela();
								validar = false;
								JOptionPane.showMessageDialog( null, 
										"A reserva do chalé " + reservas.get(i).getChale() 
										+ " foi alterada com sucesso.", 
										"Confirmação", 
										JOptionPane.PLAIN_MESSAGE, 
										new ImageIcon( diretorio + "/icons/confirm.png" ));
							}
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, 
						"Por Favor, selecione uma reserva para alterar a quantidade!", 
						"Erro", 
						JOptionPane.PLAIN_MESSAGE,
						new ImageIcon( diretorio + "/icons/error.png" ));
			} 
		} else {
			JOptionPane.showMessageDialog(null, 
					"Por favor, adicione primeiro uma reserva.", 
					"Reserva não encontrada", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
		}
		form.setAlwaysOnTop ( true );
	}


	// DAO //////////////////////////////////////

	public void carregaDAO(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.reservas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizaDAO( List<Reserva> lista ){
		
	}


	// TABELA //////////////////////////////////

	public void formatarTabela(){

		//Vetor das linhas da tabela
		List<String[]> linhas = new ArrayList<>(); 

		//Carrega as linhas na tabela com os dados da compra (somente as colunas configuradas)
		if(reservas != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DecimalFormat formato = new DecimalFormat("#,##0.00");
			for (int i = 0; i < reservas.size(); i++) {
				String[] item = { 
						sdf.format( reservas.get(i).getDtInicio() ),
						sdf.format( reservas.get(i).getDtFim() ),
						reservas.get(i).getChale(),
						Integer.toString( reservas.get(i).getQuantidade() ),  
						formato.format( reservas.get(i).getVlrUnitario() ),
						formato.format( reservas.get(i).getQuantidade() 
								* reservas.get(i).getVlrUnitario() ),
				};
				linhas.add(item);
			}
		} 
		//Configura o alinhamento dos titulos das colunas da tabela
		((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.CENTER);

		//Configura o alinhamento das colunas da tabela
		DefaultTableCellRenderer esquerdo = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direito = new DefaultTableCellRenderer();  
		esquerdo.setHorizontalAlignment(SwingConstants.LEFT);  
		centro.setHorizontalAlignment(SwingConstants.CENTER);  
		direito.setHorizontalAlignment(SwingConstants.RIGHT);

		//Nomes das colunas da tabela
		String[] nomesColunas = { "Chegada","Partida", "Chalé", "Quantidade", "Preço Unitário", "Valor Total"};

		//Cria um defaultablemodel com os dados (linhas e colunas)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)

		//Trava a edição das celulas
		{ boolean[] canEdit = new boolean []{    
				false, false, false, true, false, false };
		@Override    
		public boolean isCellEditable(int rowIndex, int columnIndex) {    
			return canEdit [columnIndex]; } 
		};

		//Define seleionar toda a linha
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Define o modelo da tabela
		tabela.setModel(model);

		//Define o alinhamento das colunas
		tabela.getColumnModel().getColumn(0).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(2).setCellRenderer(esquerdo);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(4).setCellRenderer(direito);
		tabela.getColumnModel().getColumn(5).setCellRenderer(direito);

		//Configura o tamanho das colunas
		tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(0);

		atualizaValor();
	}


	public void removeLinha(){
		
		form.setAlwaysOnTop ( false );
		
		if ( tabela.getSelectedRowCount() == 0 ) {
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione uma reserva para retirar.", 
					"Reserva não selecionada…", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
		} else {
			if(tabela.getRowCount() > 0){
				Object[] opt = { "Confirmar", "Cancelar" };
				int retirar = JOptionPane.showOptionDialog(null, 
						"\n\nDeseja cancelar esta reserva?\nVocê poderá adicioná-la novamente.",
						"Cancelar Reserva…", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
						new ImageIcon( diretorio + "/icons/error.png" ), opt, opt[1]);
				if (retirar == 0) {
					validar = true;
				} else {
					validar = false;
				}
				if (validar != false){

					//Atualiza a base de dados excluindo o registro selecionado
					for(int i = 0; i < reservas.size(); i ++){
						if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
								.equals( reservas.get(i).getNumero() )){
							reservas.remove(i);
						}
					}
					validar = false;
					//Atualiza base de dados
					atualizaDAO( reservas );

					//Atualiza a tabela, removendo o dado
					((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
					tabela.updateUI();

					//Atualiza o valor total
					atualizaValor();
				} 
			}
		}
		form.setAlwaysOnTop ( true );
	}


	// JANELA /////////////////////////////////

	public void abrir ( String nome ){

		form.setAlwaysOnTop ( false );

		switch ( nome ){

		case "principal":	
			form.dispose();
			break;

		case "construir":
			JOptionPane.showMessageDialog(null, 
					"Em construção!\nEsta função ainda não foi implementada.", 
					"Sem implementação", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/builder.png" ));
			break;

		case "sair":
			form.setAlwaysOnTop ( false );
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o fechamento" 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), exit, exit[1] );
			if ( fechar == 0 ) {
				validar = true;
			} else {
				validar = false;
			}
			if(validar == true){
				System.exit(0);
			}
			break;
		}

		form.setAlwaysOnTop ( true );
	}


	// BOTAO //////////////////////////////////

	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if(source == btnAlterar){
				atualizaQtd();
			}
			if(source == btnRetirar){
				removeLinha();
			}
			if(source == btnLimpar){
				limpaCampo();
			}
			if(source == btnConcluir){
				abrir( "construir" );
			}
			if(source == btnVoltar){
				abrir( "principal" );
			}
		}
	};

	// CONTROLE TECLA ///////////////////////////////


	public KeyListener teclar = new KeyListener() {  

		@Override  
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode=e.getKeyCode();

			switch (keyCode) {

			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_ESCAPE:
				/*msg("sistema","Fechamento");
						if(validar == false){
						System.exit(0);
						}*/
				form.dispose();
				break;
			case KeyEvent.VK_DELETE:
				//removeLinha();
				break;
			case 8: //MAC OSX: DELETE
				//removeLinha();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};

	// MOUSE //////////////////////////////////

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

			if(e.getClickCount() == 2){  

			}
		}
	};
}
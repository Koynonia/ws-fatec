/**
 * @author FERNANDO MORAES OLIVEIRA
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

import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Reserva;

public class ReservaCtrl {

	private ReservaFrm form;
	private JTable tabela;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtValor;
	private JButton btnCancelar;  
	private JButton btnConcluir; 
	private JButton btnVoltar;
	private String diretorio = "../Pousada/resources/";
	private LogonCtrl logon = LogonCtrl.getInstance();
	private boolean validar;
	private List<Reserva>reservas;

	public ReservaCtrl(
			ReservaFrm form, 
			JTable tabela, 
			JFormattedTextField ftxtQtd,  
			JFormattedTextField ftxtValor,  
			JButton btnCancelar, 
			JButton btnConcluir, 
			JButton btnVoltar
			) {

		this.form = form;
		this.tabela = tabela;
		this.ftxtQtd = ftxtQtd;
		this.ftxtValor = ftxtValor;
		this.btnCancelar = btnCancelar;
		this.btnConcluir = btnConcluir;
		this.btnVoltar = btnVoltar;
		this.reservas = new ArrayList<Reserva>();

		todos();
		formataTabela();
	}


	public void atualizaValor(){

		//Atualiza total da reserva
		float total = 0;

		for( int i = 0; i < reservas.size(); i++ ){
			if( reservas.get(i).getDtInicio().getTime() != reservas.get(i).getDtFim().getTime() ){
				total = total + ( 
						((( reservas.get(i).getDtFim().getTime() 
								- reservas.get(i).getDtInicio().getTime() ) + 3600000) / 86400000L)
								* reservas.get(i).getChale().getDiaria()  
						);
			} else {
				total = total + reservas.get(i).getChale().getDiaria();
			}
		}
		ftxtQtd.setValue( Integer.toString ( reservas.size() ) );
		ftxtValor.setValue( total );
	}

	public void cancela(){

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
		} else {
			if(tabela.getRowCount() > 0){
				msg( "cancelar", "" );
				if (validar != false){
					//Atualiza a base de dados excluindo o registro selecionado
					Reserva r = new Reserva();
					for(int i = 0; i < reservas.size(); i ++){
						if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString().replaceFirst("0*", ""))
								.equals( reservas.get(i).getId().toString() )){
							r.setId(reservas.get(i).getId());
							excluir( r );
							todos();
							msg("sucesso", tabela.getValueAt(tabela.getSelectedRow(), 0).toString() );
						}
					}
					validar = false;
					//Atualiza a tabela, removendo a linha
					((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
					tabela.updateUI();

					//Atualiza o valor total
					atualizaValor();
				} 
			}
		}
	}


	// CRUD ///////////////////////////////////

	public void excluir( Reserva r ) {
		
		ReservaDAO dao = new ReservaDAOImpl();
		try {
			dao.excluir( r );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void todos(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// TABELA //////////////////////////////////

	public void formataTabela(){

		//Vetor das linhas da tabela
		List<String[]> linhas = new ArrayList<>(); 

		//Carrega as linhas na tabela com os dados da compra (somente as colunas configuradas)
		if(reservas != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			DecimalFormat formato = new DecimalFormat("#,##0.00");
			for (int i = 0; i < reservas.size(); i++) {
				if( reservas.get(i).getDtInicio().getTime() != reservas.get(i).getDtFim().getTime() ){
					String[] item = { 
							String.format( "%06d",reservas.get(i).getId() ),
							sdf.format( reservas.get(i).getDtInicio() ),
							sdf.format( reservas.get(i).getDtFim() ),
							reservas.get(i).getChale().getCategoria(),   
							formato.format( reservas.get(i).getChale().getDiaria() ),
							formato.format(
									((( reservas.get(i).getDtFim().getTime() 
											- reservas.get(i).getDtInicio().getTime() ) + 3600000) / 86400000L)
											* reservas.get(i).getChale().getDiaria() 
									),
					};
					linhas.add(item);
				} else {
					String[] item = { 
							String.format( "%06d",reservas.get(i).getId() ),
							sdf.format( reservas.get(i).getDtInicio() ),
							sdf.format( reservas.get(i).getDtFim() ),
							reservas.get(i).getChale().getCategoria(),   
							formato.format( reservas.get(i).getChale().getDiaria() ),
							formato.format(reservas.get(i).getChale().getDiaria() ),
					};
					linhas.add(item);
				}
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
		String[] nomesColunas = { "Número","Chegada","Partida", "Chalé", "Diária", "Valor Total"};

		//Cria um defaultablemodel com os dados (linhas e colunas)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)

		//Trava a edição das celulas
		{ boolean[] canEdit = new boolean []{    
				false, false, false, false, false, false };
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
		tabela.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabela.getColumnModel().getColumn(4).setCellRenderer(direito);
		tabela.getColumnModel().getColumn(5).setCellRenderer(direito);

		atualizaValor();
	}


	// JANELA /////////////////////////////////

	public void abrir ( String nome ){

		switch ( nome ){

		case "principal":	
			form.dispose();
			break;
		}
	}

	public void fechar(){
		if(form != null)
			form.dispose();
	}

	public void mostrar(){
		if(form != null)
			form.setVisible(true);
	}

	public void esconder(){
		if(form != null)
			form.setVisible(false);
	}

	public void sair(){
		msg("sistema","Fechamento");
		if(validar == true){
			System.exit(0);
		}
	}


	// BOTAO //////////////////////////////////

	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if(source == btnCancelar){
				cancela();
			}
			if(source == btnConcluir){
				msg( "construir", "" );
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
				msg( "sair", "" );
				break;
			case KeyEvent.VK_DELETE:
				cancela();
				break;
			case 8: //MAC OSX: DELETE
				cancela();
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
				
				cancela();
			}
		}
	};


	// MENSAGENS //////////////////////////////

	public void msg( String tipo, String mensagem ) {
		form.setAlwaysOnTop ( false );

		switch ( tipo ) {

		case "cancelar":
			Object[] opt = { "Confirmar", "Cancelar" };
			int retirar = JOptionPane.showOptionDialog(null, mensagem +
					"ATENÇÃO!\n\nDeseja cancelar esta Reserva?",
					"Cancelar Reserva", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/error.png" ), opt, opt[1]);
			if (retirar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;
		case "sucesso":
			JOptionPane.showMessageDialog(null, 
					"CONFIRMADO!\n\nA reserva " + mensagem + " foi cancelada.", 
					"Cancelamento Efetuado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/confirm.png" ));
			break;
		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione um Chalé para cancelar.", 
					"Chalé não selecionado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "erroDigit":
			JOptionPane.showMessageDialog(null, 
					"Entrada inválida:\n\n" +
							mensagem +
							"\n\nPor favor, entre somente com números para a quantidade.", 
							"Entrada Inválida…", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png" ));
			break;

		case "construir":
			JOptionPane.showMessageDialog(null, 
					"Em construção!\nEsta função ainda não foi implementada.", 
					"Sem implementação", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/builder.png" ));
			break;

		case "sair":
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

		default:
			JOptionPane.showMessageDialog(null, 
					mensagem, 
					"Erro no Sistema", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png" ));
		}
		form.setAlwaysOnTop ( true );
	}
}
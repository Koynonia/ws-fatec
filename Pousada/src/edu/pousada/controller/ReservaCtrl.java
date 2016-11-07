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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import edu.pousada.dao.ReservaDAO;
import edu.pousada.dao.ReservaDAOImpl;
import edu.pousada.entity.Reserva;

public class ReservaCtrl {

	private ReservaFrm janela;
	private JTable tabela;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtValor;
	private JButton btnLimpar;
	private JButton btnCancelar;  
	private JButton btnConcluir; 
	private JButton btnVoltar;
	private String diretorio = "../Pousada/resources/";
	private LogonCtrl logon = LogonCtrl.getInstance();
	private static boolean validar;
	private List<Reserva>reservas;

	public ReservaCtrl(
			ReservaFrm janela, 
			JTable tabela, 
			JFormattedTextField ftxtQtd,  
			JFormattedTextField ftxtValor,
			JButton btnLimpar,
			JButton btnCancelar, 
			JButton btnConcluir, 
			JButton btnVoltar
			) {

		this.janela = janela;
		this.tabela = tabela;
		this.ftxtQtd = ftxtQtd;
		this.ftxtValor = ftxtValor;
		this.btnLimpar = btnLimpar;
		this.btnCancelar = btnCancelar;
		this.btnConcluir = btnConcluir;
		this.btnVoltar = btnVoltar;
		this.reservas = new ArrayList<Reserva>();

		
		//prepara o ambiente
		cargaReserva();
				
		// realiza o login com uma sessão anterior perdida
		logon.autoLogin();

		//atualiza o Logon com o perfil
		if( !logon.getLogon().isEmpty() ){
			logon.getLogon().get(0).setTela( janela.getName() );
		}

		formataTabela();
	}


	// CRUD ///////////////////////////////////

	public void excluir( Reserva r ) {
		
		ReservaDAO dao = new ReservaDAOImpl();
		try {
			dao.excluir( r );
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > excluir()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}

	public void cargaReserva(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todos();
		} catch (SQLException e) {
			msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nReservaCtrl > cargaReserva()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}
	
	
	// RESERVA /////////////////////////
	
	public void atualizaValor(){

		//Atualiza total da reserva
		float total = 0;
		
		NumberFormat nf = NumberFormat.getInstance();

		//verifica se a tabela está preenchida
				if( tabela.getRowCount() > 0 ){
					//precorre as linhas selecionando o valor
					for(int i = 0; i < tabela.getRowCount(); i ++){

						try {
							//converte a String e realiza a soma
							total+= nf.parse( tabela.getValueAt(i, 5).toString() ).doubleValue();
						} catch (ParseException e) {
							msg("", "ERRO " + e.getCause() 
									+ "\n\nLocal:\nReservaCtrl > atualizaValor()."  
									+ "\n\nMensagem:\n" + e.getMessage() );
							//e.printStackTrace();
						}
					}
				}
		ftxtQtd.setValue( Integer.toString ( tabela.getRowCount() ) );
		ftxtValor.setValue( total );
	}

	public void cancela(){

		// seleciona a linha da tabela a ser cancelada
		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
		} else {

			// atualiza a base de dados excluindo o registro selecionado
			Reserva r = new Reserva();
			for(int i = 0; i < reservas.size(); i ++){

				// limpa a mascara no numero da reserva
				if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString().replaceFirst("0*", ""))
						.equals( reservas.get(i).getId().toString() )){		
					msg( "cancelar", "nº " + String.format( "%06d", reservas.get(i).getId()) );
					if (validar != false){
						r.setId(reservas.get(i).getId());
						excluir( r );
						cargaReserva();

						// atualiza o estado do botão Reserva na tela Principal
						if ( !reservas.isEmpty()) {
							PrincipalCtrl.btnReservas.setText( "Reservas ( " + logon.reservaQtd() + " )" );
							PrincipalCtrl.btnReservas.setVisible(true);
						} else {
							PrincipalCtrl.btnReservas.setText( "Reservas");
							PrincipalCtrl.btnReservas.setVisible(false);
						}

						// atualiza a tabela, removendo a linha
						((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
						tabela.updateUI();

						// atualiza o valor total
						atualizaValor();
					} 
				}
			}
			validar = false;	
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

				if( reservas.get(i).getIdCliente().getId().equals( logon.getLogon().get(0).getIdUsuario() )){
					
					//calcula o valor total das reservas em cada linha
					float vlrTotal = ((( reservas.get(i).getDtFim().getTime() 
							- reservas.get(i).getDtInicio().getTime() ) + 3600000) / 86400000L) 
							* reservas.get(i).getIdChale().getDiaria() ;

					float total = 0;
					if ( vlrTotal != 0){
						total = vlrTotal;
					} else {
						total = reservas.get(i).getIdChale().getDiaria();
					}

					String[] item = { 
							String.format( "%06d",reservas.get(i).getId() ),
							sdf.format( reservas.get(i).getDtInicio() ),
							sdf.format( reservas.get(i).getDtFim() ),
							reservas.get(i).getIdChale().getCategoria(),   
							formato.format( reservas.get(i).getIdChale().getDiaria() ),
							formato.format( total )
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
			janela.dispose();
			break;
		}
	}

	public void fechar(){
		if(janela != null)
			janela.dispose();
	}

	public void mostrar(){
		if(janela != null)
			janela.setVisible(true);
	}

	public void esconder(){
		if(janela != null)
			janela.setVisible(false);
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

			if(source == btnLimpar){
				
				if( reservas.size() > 0 ){
					msg("limpar","");
					if ( validar != false ){
						Reserva r = new Reserva();
						for( int i = 0; i < reservas.size(); i++ ){
							r.setId( reservas.get(i).getId() );	
							excluir( r );
						}
						cargaReserva();
						formataTabela();
						//atualiza o estado do botão Reserva na tela Principal
						PrincipalCtrl.btnReservas.setText( "Reservas");
						PrincipalCtrl.btnReservas.setVisible(false);
						msg("sucesso","");
					}
					validar = true;
				}
			}

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
			// ao clicar 2x aciona
			if(e.getClickCount() == 2){ 
				
				cancela();
			}
		}
	};


	// MENSAGENS //////////////////////////////

	
	public void msg( String tipo, String mensagem ) {
		janela.setAlwaysOnTop ( false );

		switch ( tipo ) {

		case "sucesso":
			JOptionPane.showMessageDialog(null, 
					"CANCELADO!\nReserva(s) " + mensagem + " cancelada(s).", 
					"Cancelamento Efetuado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/confirm.png" ));
			break;
			
		case "cancelar":
			Object[] opt = { "Confirmar", "Cancelar" };
			int retirar = JOptionPane.showOptionDialog(null, 
					"ATENÇÃO!\nDeseja cancelar a Reserva " + mensagem + "?",
					"Cancelar Reserva", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/alert.png" ), opt, opt[1]);
			if (retirar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;
			
		case "limpar":
			Object[] l = { "Confirmar", "Cancelar" };
			int limpar = JOptionPane.showOptionDialog(null, mensagem +
					"Deseja cancelar todas as Reservas?",
					"Cancelar Reservas", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/alert.png" ), l, l[1]);
			if (limpar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;
			
		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione uma Reserva para cancelar.", 
					"Reserva não selecionada", 
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
					"ERRO! Algo não deveria ter acontecido…\n\nTermo: \n" 
							+ mensagem + "\n" +  this
							+ "\n\nOcorreu capturado pelo Controller.", 
							"Erro no Controller " + this, 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( diretorio + "/icons/error.png"));;
							return;
		}
		janela.setAlwaysOnTop ( true );
	}
}
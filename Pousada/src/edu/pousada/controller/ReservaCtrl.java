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
	private JButton btnAlterar;
	private JButton btnRetirar; 
	private JButton btnLimpar; 
	private JButton btnConcluir; 
	private JButton btnVoltar;
	private String diretorio = "../Pousada/resources/";
	private String imagem;
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

		formataTabela();
	}
	
	
	public void limpaCampo(){

		reservas.removeAll(reservas);
		atualizaReservaDAO(reservas);
		formataTabela();
		ftxtValor.setValue(null);
	}
	

	public void atualizaValor(){

		//Atualiza total da reserva
		double total = 0;
		int qtd = 0;
		for( int i = 0; i < reservas.size(); i++ ){
			total = total + ( reservas.get(i).getQuantidade() 
					* reservas.get(i).getChale().getDiaria() );	
			qtd = qtd + ( reservas.get(i).getQuantidade() );
		}
		ftxtQtd.setValue( Integer.toString ( qtd ) );
		ftxtValor.setValue( total );
	}


	public void alteraQtd(){

		reservas.clear();
		carregaReservaDAO();
		
		if(tabela.getRowCount() > 0){
			if ( tabela.getSelectedRowCount() != 0){
				for(int i = 0; i <= reservas.size(); i ++){
					if ( tabela.isRowSelected(i)){
						if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
								.equals( reservas.get(i).getNumero() )){
							msg( "alterar", reservas.get(i).getChale().getCategoria() );
							if ( validar != false){
								Reserva item = new Reserva();
								item.setQuantidade( quantidade );
								item.setDtCadastro( new Date() );
								reservas.set(i,item);
								atualizaReservaDAO( reservas );
								formataTabela();
								validar = false;
								msg( "realizado", reservas.get(i).getChale().getCategoria() );
							}
						}
					}
				}
			} else {
				msg( "", "Por Favor, selecione um Livro para alterar a quantidade!");
			} 
		} else {
			msg( "", "Por favor, adicione primeiro uma reserva." );
		}
	}


	// DAO //////////////////////////////////////
	
	public void carregaReservaDAO(){

		ReservaDAO dao = new ReservaDAOImpl();
		try {
			reservas = dao.todasReservas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizaReservaDAO( List<Reserva> item ){
		
		ReservaDAO dao = new ReservaDAOImpl();
		for( Reserva rs : item ){
			
			rs.setNumero( item.get(0).getNumero() );
			rs.setCliente( item.get(0).getCliente() );
			rs.setChale( item.get(0).getChale() );
			rs.setQtdAdulto( item.get(0).getQtdAdulto() );
			rs.setQtdCrianca( item.get(0).getQtdCrianca() );
			rs.setQuantidade( item.get(0).getQuantidade() );
			rs.setDtInicio( item.get(0).getDtInicio() );
			rs.setDtFim( item.get(0).getDtFim() );
			rs.setDesconto( item.get(0).getDesconto() );
			rs.setDtCadastro( item.get(0).getDtCadastro() );
			try {
				dao.adicionaReserva( rs );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// CRUD ///////////////////////////////////

	public void adicionaChale( Reserva r ) {
		
		Reserva item = new Reserva();
		DecimalFormat formato = new DecimalFormat("#,##0.00");
		
		if ( !reservas.isEmpty() ){
			for ( int i = 0; i < reservas.size(); i++ ){
				imagem = reservas.get(i).getChale().getCategoria();
				//Verifica se o Chalé já foi adicionada à Reserva
				if ( reservas.get(i).getChale().getNumero().equals( r.getChale().getNumero() )){
					msg( "adicionar", reservas.get(i).getChale().getCategoria() );
					//Se o chalé já estiver na Reserva, soma + 1 à quantidade
					if ( validar != false){
						for ( int q = 0; q < reservas.size(); q++ ){
							if ( reservas.get(i).getChale().getNumero().equals( r.getChale().getNumero() )){
								item.setNumero( r.getNumero() );
								item.setCliente( r.getCliente() );
								item.setChale( r.getChale() );
								item.setQtdAdulto( r.getQtdAdulto() );
								item.setQtdCrianca( r.getQtdCrianca() );
								item.setQuantidade( reservas.get(q).getQuantidade() + 1 );
								item.setDtInicio( r.getDtInicio() );
								item.setDtFim( r.getDtFim() );
								item.setDesconto( 0 );
								item.setDtCadastro( new Date() );
								reservas.set( q, item );
							}
						}
					} else {
						fechar();
					}
				} 
			}
			if ( validar == false) {
				//Adiciona o chalé se não estiver na Reserva
				imagem = r.getChale().getCategoria();
				msg( "adicionarQtd", "Reserva do chalé: " 
						+ r.getChale().getCategoria() 
						+ "\n\nValor da Diária: R$ " + formato.format( r.getChale().getDiaria() ));
				if ( validar != false ){
					item.setNumero( r.getNumero() );
					item.setCliente( r.getCliente() );
					item.setChale( r.getChale() );
					item.setQtdAdulto( r.getQtdAdulto() );
					item.setQtdCrianca( r.getQtdCrianca() );
					item.setQuantidade( 1 );
					item.setDtInicio( r.getDtInicio() );
					item.setDtFim( r.getDtFim() );
					item.setDesconto( 0 );
					item.setDtCadastro( new Date() );
					reservas.add( item );
				}
			}
		} else {
			//Adiciona à Reserva se já houver chalés adicionados na Reserva
			imagem = r.getChale().getCategoria();
			msg( "adicionarQtd", "Reserva do chalé: " 
					+ r.getChale().getCategoria() 
					+ "\n\nValor da Diária: R$ " + formato.format( r.getChale().getDiaria() ));
			if ( validar != false ){
				item.setNumero( r.getNumero() );
				item.setCliente( r.getCliente() );
				item.setChale( r.getChale() );
				item.setQtdAdulto( r.getQtdAdulto() );
				item.setQtdCrianca( r.getQtdCrianca() );
				item.setQuantidade( 1 );
				item.setDtInicio( r.getDtInicio() );
				item.setDtFim( r.getDtFim() );
				item.setDesconto( 0 );
				item.setDtCadastro( new Date() );
				reservas.add( item );
			}
		}
		atualizaReservaDAO( reservas );
		formataTabela();
	}


	// TABELA //////////////////////////////////

	public void formataTabela(){

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
						reservas.get(i).getChale().getCategoria(), 
						Integer.toString( reservas.get(i).getQuantidade() ),  
						formato.format( reservas.get(i).getChale().getDiaria() ),
						formato.format( reservas.get(i).getQuantidade() 
								* reservas.get(i).getChale().getDiaria() ),
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
		
		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
		} else {
			if(tabela.getRowCount() > 0){
				msg( "retirar", "" );
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
					atualizaReservaDAO( reservas );

					//Atualiza a tabela, removendo o dado
					((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
					tabela.updateUI();

					//Atualiza o valor total
					atualizaValor();
				} 
			}
		}
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

			if(source == btnAlterar){
				alteraQtd();
			}
			if(source == btnRetirar){
				removeLinha();
			}
			if(source == btnLimpar){
				limpaCampo();
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
	
	
	// MENSAGENS //////////////////////////////

	public void msg( String tipo, String mensagem ) {
		form.setAlwaysOnTop ( false );

		switch ( tipo ) {

		case "realizado":
			JOptionPane.showMessageDialog( null, 
					"A reserva do Chalé " + mensagem 
					+ " foi alterada com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/confirm.png" ));
			break;
			
		case "adicionar":
			Object[] save = { "Confirmar", "Cancelar" };  
			int confirmar = JOptionPane.showOptionDialog(null, "Um chalé de categoria" + mensagem
					+ "\n\nJá adicionado à Reserva.\nGostaria de adicionar mais uma reserva?",
					"Adicionar Reserva", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), save, save[1]);
			if (confirmar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;
			
		case "adicionarQtd":
			Object aQtd = JOptionPane.showInputDialog( null,
					mensagem + "\n\nDigite a quantidade do Chalé desta categoria:",
					"Informar a Quantidade", JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/miniaturas/" + imagem + "-thumb.jpg"  ), null, 1 );
			if ( aQtd == null){
				validar = false;
				fechar();
				return;
			} else {
				if ( ! logon.testarNumero( aQtd.toString() ) ){
					msg( "erroDigit", aQtd.toString() );
				} else {
					if ( aQtd.toString().contains( "0" ) ) {
						removeLinha();
					} else {
						quantidade = Integer.parseInt( aQtd.toString() );
						mostrar();
						validar = true;			
					}
				}
			}
			break;
			
		case "alterar":
			String resp = JOptionPane.showInputDialog( null,
					"Digite a quantidade desejada da reserva do chalé " + mensagem + ":",
					"Alterar Quantidade", JOptionPane.QUESTION_MESSAGE );
			if ( resp == null){
				return;
			} else {
				if ( ! logon.testarNumero( resp ) ){
					msg( "erroDigit", resp );
				} else {
					if ( resp.contains( "0" ) ) {
						removeLinha();
					} else {
						quantidade = Integer.parseInt( resp );
						validar = true;			
					}
				}
			}
			break;

		case "retirar":
			Object[] opt = { "Confirmar", "Cancelar" };
			int retirar = JOptionPane.showOptionDialog(null, mensagem +
					"\n\nDeseja retirar o Chalé da Reserva?\nVocê poderá adicioná-lo novamente mais tarde.",
					"Retirar Chalé", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/error.png" ), opt, opt[1]);
			if (retirar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;

		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione um Chalé para retirar.", 
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

		case "sistema":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o " + mensagem 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa!", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), exit, exit[1] );
			if ( fechar == 0 ) {
				validar = true;
			} else {
				validar = false;
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
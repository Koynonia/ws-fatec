/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 11/04/2016
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import boundary.FrmCarrinho;
import dao.ArquivoCarrinho;
import dao.Arquivos;
import entity.Carrinho;
import entity.Livro;

public class CarrinhoController implements ComponentListener {

	private FrmCarrinho janela;
	private JTable tabela;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtValor;
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "carrinho";
	private List<Carrinho> itens;
	private List<Livro> livros;
	private ArquivoCarrinho dao = new ArquivoCarrinho();
	private Arquivos arquivos = new Arquivos();
	private boolean validar;
	private int quantidade = 1;

	public CarrinhoController(
			FrmCarrinho janela, 
			JTable tabela, 
			JFormattedTextField ftxtQtd,  
			JFormattedTextField ftxtValor) {

		this.janela = janela;
		this.tabela = tabela;
		this.ftxtQtd = ftxtQtd;
		this.ftxtValor = ftxtValor;
		this.itens = new ArrayList<Carrinho>();
		this.livros = new ArrayList<Livro>();
		
		lerArquivo();
		formatarTabela();
		temporizador();
	}


	public void limparCampos(){

		itens.removeAll(itens);
		atualizarArquivo(itens);
		formatarTabela();
		ftxtValor.setValue(null);
	}
	
	public String obterData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String data = (dateFormat.format(date));
		return data;
	}
	
	
	private boolean testarNumero(String str) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(str);
		return m.find();    
	}
	
	public void temporizador(){

		if ( itens.size() > 0 ){
			int tempo = 1; //variavel que controla os minutos da sessão
			String dtAtual = obterData();
			String dtCarrinho = itens.get(0).getDtCadastro();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			//Converte para Date
			Date dateA = null;
			Date dateB = null;
			try {
				dateA = df.parse(dtAtual);
				dateB = df.parse(dtCarrinho);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Calendar calB = Calendar.getInstance();
			calB.setTime(dateB);
			//Adiciona o tempo configurado para a B
			//Calendar.MINUTE pode ser alterado para qtd de tempo desejada
			calB.add(Calendar.MINUTE, tempo);
			dateB = calB.getTime();

			if ( dateA.after(dateB) ){
				limparCampos();
			}	
		}
	}
	
	
	public void atualizarValor(){

		//ATUALIZA TOTAL DA COMPRA
		float total = 0;
		int qtd = 0;
		for( int i = 0; i < itens.size(); i++ ){
			total = total + ( itens.get(i).getQuantidade() 
					* itens.get(i).getCarrinho().getPrecoVenda() );	
			qtd = qtd + ( itens.get(i).getQuantidade() );
		}
		ftxtQtd.setValue( Integer.toString ( qtd ) );
		ftxtValor.setValue(total);
	}
	

	// TABELA //////////////////////////////

	public void formatarTabela(){

		//VETOR DAS LINHAS DA TABELA
		List<String[]> linhas = new ArrayList<>(); 

		//CARREGA AS LINHAS NA TABELA COM OS DADOS DA COMPRA (SOMENTE AS COLUNAS CONFIGURADAS)
		if(itens != null){
			DecimalFormat formato = new DecimalFormat("#,##0.00");
			for (int i = 0; i < itens.size(); i++) {
				String[] item = { 
						itens.get(i).getCarrinho().getIsbn(),
						itens.get(i).getCarrinho().getTitulo(),
						itens.get(i).getCarrinho().getAutor(),
						Integer.toString(itens.get(i).getQuantidade()), 
						formato.format( itens.get(i).getCarrinho().getPrecoVenda() ),
						formato.format( itens.get(i).getQuantidade() 
								* itens.get(i).getCarrinho().getPrecoVenda() ),
				};
				linhas.add(item);
			}
		} else {
			msg("", "Problema ao carregar a Base de Dados!");
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
		String[] nomesColunas = { "","Título", "Autor", "Quantidade", "Preço Unitário", "Valor Total"};

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

		//DEFINE COMO SELEÇÃO TODA A LINHA
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tabela.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(1).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(2).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(5).setCellRenderer(direita);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tabela.getColumnModel().getColumn(0).setMinWidth(0);
		tabela.getColumnModel().getColumn(0).setMaxWidth(0);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(0);

		atualizarValor();
	}


	public void removeLinha(){

		if ( tabela.getSelectedRowCount() == 0 ) {
			msg( "erroLinha", "" );
		} else {
			if(tabela.getRowCount() > 0){
				msg( "retirar", "" );

				if (validar != false){
					//ATUALIZA A BASE DE DADOS EXCLUINDO O REGISTRO SELECIONADO
					for(int i = 0; i < itens.size(); i ++){
						if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
								.equals(itens.get(i).getCarrinho().getIsbn())){
							itens.remove(i);
						}
					}
					validar = false;
					//ATUALIZA BASE DE DADOS
					atualizarArquivo(itens);

					//ATUALIZA A TABELA, REMOVENDO O DADO
					((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
					tabela.updateUI();

					//ATUALIZA O VALOR TOTAL
					atualizarValor();
				} 
			}
		}
	}
	
	
	// CRUD //////////////////////////
	

	public void addItem( Livro livro ) {

		Carrinho item = new Carrinho();

		if ( !itens.isEmpty() ){
			for ( int i = 0; i < itens.size(); i++ ){
				//Verifica se o livro já está adicionado ao carrinho
				if ( itens.get(i).getCarrinho().getIsbn().equals(livro.getIsbn())){
					msg( "adicionar", livro.getTitulo() + "\n de " + livro.getAutor() );
					//Se o livro já estiver no Carrinho, soma + 1 à quantidade
					if ( validar != false){
						for ( int q = 0; q < itens.size(); q++ ){
							if ( itens.get(q).getCarrinho().getIsbn().equals( livro.getIsbn() )){
								item.setQuantidade( itens.get(q).getQuantidade() + 1 );
								item.setDesconto( 0 );
								item.setCarrinho( livro );
								item.setDtCadastro(obterData());
								itens.set( q, item );
							}
						}
					} else {
						fechar();
						return;
					}
				} 
			}
			if ( validar == false) {
				//Adiciona o livro se não estiver no carrinho
				item.setQuantidade( quantidade );
				item.setDesconto( 0 );
				item.setCarrinho(livro);
				item.setDtCadastro( obterData() );
				itens.add(item);
			}
		} else {
			//Adiciona ao Carrinho
			item.setQuantidade( quantidade );
			item.setDesconto( 0 );
			item.setCarrinho(livro);
			item.setDtCadastro( obterData() );
			itens.add(item);
		}
		atualizarArquivo(itens);
		formatarTabela();
	}
	
	
	public void alterarQtd(){

		if(tabela.getRowCount() > 0){
			if ( tabela.getSelectedRowCount() != 0){
				for(int i = 0; i < itens.size(); i ++){
					if ( tabela.isRowSelected(i)){
						if((tabela.getValueAt(tabela.getSelectedRow(), 0).toString())
								.equals(itens.get(i).getCarrinho().getIsbn())){
							msg("alterar", itens.get(i).getCarrinho().getTitulo() 
									+ "\n de " + itens.get(i).getCarrinho().getAutor());
							if ( validar != false){
								Carrinho item = new Carrinho();
								item.setQuantidade( quantidade );
								item.setDesconto( 0 );
								item.setCarrinho(itens.get(i).getCarrinho());
								item.setDtCadastro( obterData() );
								itens.set(i,item);
								atualizarArquivo(itens);
								formatarTabela();
								validar = false;
								msg("realizado", "quantidade");
							}
						}
					}
				}
			} else {
				msg( "", "Por Favor, selecione um Livro para alterar a quantidade!");
			} 
		}
	}
	
	
	public void lerLivros() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			arquivos.lerArquivo( diretorio + "data", "livro" );
			linha = arquivos.getBuffer();
			String[] listaItens = linha.split(";");
			for (String s : listaItens) {
				String text = s.replaceAll(".*: ", "");
				lista.add(text);
				if (s.contains("---")) {
					Livro livro = new Livro();
					livro.setIsbn( lista.get(0) );
					livro.setTitulo( lista.get(1) );
					livro.setAutor( lista.get(2) );
					livro.setPrecoVenda( Float.parseFloat( lista.get(11) ));
					livros.add(livro);
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void lerArquivo() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			dao.lerArquivo(diretorio + "data/", arquivo);
			linha = dao.getBuffer();
			String[] listaItens = linha.split(";");
			for (String s : listaItens) {
				String text = s.replaceAll(".*: ", "");
				lista.add(text);
				if (s.contains("---")) {
					Carrinho item = new Carrinho();
					lerLivros();
					Livro listLivros = new Livro();
					for( Livro livro : livros ){
						if( livro.getIsbn().equals( lista.get(0) ) ){
							listLivros = livro ;
						}					
					}
					item.setQuantidade( Integer.parseInt( lista.get(1) ) );
					item.setDesconto( Float.parseFloat( lista.get(2) ) );
					item.setCarrinho( listLivros );
					item.setDtCadastro(lista.get(3));
					itens.add(item);
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void atualizarArquivo(List<Carrinho> listaItens) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
		File f = new File(diretorio + "data/" + arquivo);
		f.delete();
		for (Carrinho itens : listaItens) {
			try {
				dao.escreverArquivo(diretorio  + "data/", arquivo, "", itens);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	


	// MENSAGENS //////////////////////////////


	public void msg( String tipo, String mensagem ) {
		janela.setAlwaysOnTop ( false );

		switch ( tipo ) {

		case "realizado":
			JOptionPane.showMessageDialog( null, 
					"O Livro teve a " + mensagem + " alterada com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/confirm.png" ));
			break;
			
		case "adicionar":
			Object[] save = { "Confirmar", "Cancelar" };  
			int confirmar = JOptionPane.showOptionDialog(null, mensagem 
					+ "\n\nJá adicionado ao Carrinho.\nGostaria de adicionar mais um volume?",
					"Adicionar Livro…", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), save, save[1]);
			if (confirmar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;
			
		case "alterar":
			String resp = JOptionPane.showInputDialog( null,
					"Digite a quantidade desejada do Livro\n\n" + mensagem + ":",
					"Alterar Quantidade…", JOptionPane.QUESTION_MESSAGE );
			if ( resp == null){
				return;
			} else {
				if ( !testarNumero( resp ) ){
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
					"\n\nDeseja retirar o Livro do Carrinho?\nVocê poderá adicioná-lo novamente mais tarde.",
					"Retirar Livro…", 
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
					"Por favor, selecione um Livro para retirar.", 
							"Livro não selecionado…", 
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
		janela.setAlwaysOnTop ( true );
	}
	
	
	// COMPORTAMENTO JANELA //////////////////////////

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
	


	// CONTROLE BOTAO //////////////////////////////

	public ActionListener apagar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			removeLinha();
		}
	};


	public ActionListener alterar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			alterarQtd();
		}
	};
	
	public ActionListener retirar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			removeLinha();
		}
	};

	public ActionListener limpar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			limparCampos();
		}
	};

	public ActionListener concluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			msg("", "CONCLUIR PEDIDO em implementação!");
		}
	};

	public ActionListener voltar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			fechar();
		}
	};

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	// CONTROLE TECLA ///////////////////////////////


	public KeyListener tecla = new KeyListener() {  

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
				fechar();
				break;
			case KeyEvent.VK_DELETE:
				removeLinha();
				break;
			case 8: //MAC OSX: DELETE
				removeLinha();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};



	// CONTROLE MOUSE ///////////////////////////////

	public MouseListener clique = new MouseListener() {

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
				removeLinha();
			}
		}
	};
}
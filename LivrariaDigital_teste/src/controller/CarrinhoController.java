package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import boundary.FrmCarrinho;
import boundary.FrmPrincipal;
import dao.ArquivoCarrinho;
import entity.Carrinho;
import entity.Livro;

public class CarrinhoController implements ComponentListener {

	private FrmCarrinho form;
	private JTable tabela;
	private JFormattedTextField valor;
	private boolean validar;
	private List<Carrinho> itens;
	private ArquivoCarrinho dao = new ArquivoCarrinho();
	private String diretorio = "../LivrariaDigital/data/";
	private String arquivo = "carrinho";

	public CarrinhoController(FrmCarrinho frame, JTable tabCompra, JFormattedTextField ftxtVlrTotal) {

		this.form = frame;
		this.tabela = tabCompra;
		this.valor = ftxtVlrTotal;

		formatarTabela();
		lerArquivo();
		
	}


	public void limparCampos(){
		valor.setValue(null);
	}
	
	
	// CRUD //////////////////////////

		public void lerArquivo() {

			//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
			String linha = new String();
			ArrayList<String> lista = new ArrayList<>();
			try {
				dao.leArquivo(diretorio, arquivo);
				linha = dao.getBuffer();
				String[] listaItens = linha.split(";");
				for (String s : listaItens) {
					String text = s.replaceAll(".*: ", "");
					lista.add(text);
					if (s.contains("---")) {
						Carrinho item = new Carrinho();
						Livro livro = new Livro();
						item.setQuantidade( Integer.parseInt( lista.get(0) ) );
						item.setPreco( Float.parseFloat( lista.get(1) ) );
						item.setDesconto( Float.parseFloat(lista.get(2) ) );
						item.setLivro( livro );
//						itens.add(item);
						lista.clear();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		public void atualizarArquivo(List<Carrinho> listaItens) {

			//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
			File f = new File(diretorio + arquivo);
			f.delete();
			for (Carrinho itens : listaItens) {
				try {
					dao.escreveArquivo(diretorio, arquivo, "", itens);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	// TABELA //////////////////////////////

		public void formatarTabela(){

			//VETOR DAS LINHAS DA TABELA
			List<String[]> linhas = new ArrayList<>(); 

			//CARREGA AS LINHAS NA TABELA COM OS DADOS DA COMPRA (SOMENTE AS COLUNAS CONFIGURADAS)
			if(itens != null){
				for (int i = 0; i < itens.size(); i++) {

					String[] item = { 
							Integer.toString(itens.get(i).getQuantidade()), 
							Float.toString(itens.get(i).getPreco()),
							Float.toString(itens.get(i).getDesconto()), 	
					};
					linhas.add(item);
				}
			} else {
				msg("", "Problema ao carregar a Base de Dados!");
			}

			//CONFIGURA O ALINHAMENTO DOS TITULOS DAS COLUNAS DA TABELA
			((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

			//CONFIGURA O ALINHAMENTO DAS COLUNAS DA TABELA
			DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
			DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

			esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
			centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
			direita.setHorizontalAlignment(SwingConstants.RIGHT);

			//NOMES DAS COLUNAS DA TABELA
			String[] nomesColunas = { "Títuo", "Autor", "Quantidade", "Preço Unitário", "Valor Total"};

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
			tabela.getColumnModel().getColumn(2).setCellRenderer(centralizado);
			tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
			tabela.getColumnModel().getColumn(4).setCellRenderer(direita);

			//CONFIGURA O TAMANHO DAS COLUNAS
			tabela.getColumnModel().getColumn(0).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(15);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(30);
		}
		
		
		public void atualizarTabela(){
			
			
		}



	// MENSAGENS //////////////////////////////


	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "save":
			JOptionPane.showMessageDialog(null, 
					"Ingresso(s) processado(s)\n" + mensagem + "\n\nCompra realizada com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../LivrariaDigital/icons/confirm.png"));
			break;
		case "saveconfirm":
			Object[] save = { "Confirmar", "Cancelar" };  
			int confirmar = JOptionPane.showOptionDialog(null, "Confirma o pagamento da compra " + mensagem 
					+ " ?\n\nApós a confirmação, não será possível Editar/Cancelar esta compra!",
					"Não Localizado", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../LivrariaDigital/icons/warning.png"), save, save[1]);
			if (confirmar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;
		case "system":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nChamada para o " + mensagem 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa!", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../LivrariaDigital/icons/warning.png"), exit, exit[1]);
			if (fechar == 0) {
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
					new ImageIcon("../LivrariaDigital/icons/error.png"));
		}
	}
	
	
	public void fechar()
	{
		if(form != null)
			form.dispose();
	}
	
	public void mostrar()
	{
		if(form != null)
			form.setVisible(true);
	}
	
	public void esconder()
	{
		if(form != null)
			form.setVisible(false);
	}


	// CONTROLE BOTAO //////////////////////////////

	public ActionListener alterar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			msg("", "ALTERAR em implementação!");
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
//			msg("", "VOLTAR em implementação!");
			FrmPrincipal frame = new FrmPrincipal();
			frame.setVisible(true);
			esconder();
		}
	};

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	// CONTROLE TECLA ///////////////////////////////


	public KeyListener tecla = new KeyListener() {  

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
				msg("system","Fechamento");
				if(validar != false){
					System.exit(0);
				}
				break;
			case KeyEvent.VK_DELETE:
				//				removeLinha();
				break;
			case 8: //MAC OSX: DELETE
				//				removeLinha();
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	};

}

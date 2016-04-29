/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 22/04/2016
 */

package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ArquivoCarrinho;
import boundary.FrmCarrinho;
import boundary.FrmDetalhe;
import boundary.FrmLista;
import boundary.FrmPrincipal;
import entity.Livro;

public class ListaController implements ComponentListener {
	
	private FrmLista janela; 
	private FrmPrincipal janelaPrincipal; 
	private FrmCarrinho janelaCarrinho; 
	private FrmDetalhe janelaDetalhe; 
	private List<Livro> livros; 
	private JLabel lblTitulo; 
	private JLabel lblAutor; 
	private JLabel lblValor; 
	private JLabel lblLivroCapa_1; 
	private JLabel lblLivroTitulo_1; 
	private JLabel lblLivroAutor_1; 
	private JLabel lblLivroValor_1; 
	private JLabel lblLivroCapa_2; 
	private JLabel lblLivroTitulo_2; 
	private JLabel lblLivroAutor_2; 
	private JLabel lblLivroValor_2; 
	private JButton btnDetalhes_1; 
	private JButton btnAddCarrinho_2; 
	private JButton btnDetalhes_2; 
	private JButton btnAddCarrinho_1; 
	private JButton btnCarrinho; 
	private JButton btnAnterior; 
	private JButton btnProximo; 
	private JButton btnVoltar; 
	private String diretorio = "../LivrariaDigital_teste/"; 
	private String arquivo = "carrinho";
	private String imagem; 
	private boolean validar; 
	private int select; 
	private int opt;
	private ArrayList<String> isbn = new ArrayList<>();
	
	public ListaController ( 
			FrmLista janela, 
			List<Livro> livros, 
			JLabel lblTitulo, 
			JLabel lblAutor, 
			JLabel lblValor, 
			JLabel lblLivroCapa_1, 
			JLabel lblLivroTitulo_1, 
			JLabel lblLivroAutor_1, 
			JLabel lblLivroValor_1, 
			JLabel lblLivroCapa_2, 
			JLabel lblLivroTitulo_2, 
			JLabel lblLivroAutor_2, 
			JLabel lblLivroValor_2, 
			JButton btnDetalhes_1, 
			JButton btnAddCarrinho_1, 
			JButton btnDetalhes_2, 
			JButton btnAddCarrinho_2, 
			JButton btnCarrinho, 
			JButton btnAnterior, 
			JButton btnProximo, 	
			JButton btnVoltar){
		
		this.janela = janela;
		this.livros = livros;
		this.lblTitulo = lblTitulo;
		this.lblAutor = lblAutor;
		this.lblValor = lblValor;	
		this.lblLivroCapa_1 = lblLivroCapa_1;
		this.lblLivroTitulo_1 = lblLivroTitulo_1;
		this.lblLivroAutor_1 = lblLivroAutor_1;
		this.lblLivroValor_1 = lblLivroValor_1;
		this.lblLivroCapa_2 = lblLivroCapa_2;
		this.lblLivroTitulo_2 = lblLivroTitulo_2;
		this.lblLivroAutor_2 = lblLivroAutor_2;
		this.lblLivroValor_2 = lblLivroValor_2;
		this.btnDetalhes_1 = btnDetalhes_1;	
		this.btnAddCarrinho_1 = btnAddCarrinho_1;
		this.btnDetalhes_2 = btnDetalhes_2;
		this.btnCarrinho = btnCarrinho;
		this.btnAddCarrinho_2 = btnAddCarrinho_2;
		this.btnAnterior = btnAnterior;
		this.btnProximo = btnProximo;
		this.btnVoltar = btnVoltar;
		
		escreverTela( livros );
	}
	
	
	public void escreverTela( List<Livro> livro){
		
		lerCarrinho();
		
		for( int i = 0; i < this.livros.size(); i++ ){
			
			switch (i){
			
			case 0:
				isbn.add( livros.get(i).getIsbn() );
				lblLivroCapa_1.setToolTipText( livro.get(i).getTitulo() );
				lblLivroTitulo_1.setText( livro.get(i).getTitulo() );
				lblLivroAutor_1 .setText( livro.get(i).getAutor() );
				lblLivroValor_1.setText( "R$ " + String.valueOf( livro.get(i).getPrecoVenda() ));
				imagem = livro.get(i).getImagem();
				carregaCapa(i);
			break;
			
			case 1:
				isbn.add( livros.get(i).getIsbn() );
				lblLivroCapa_2.setToolTipText( livro.get(i).getTitulo() );
				lblLivroTitulo_2.setText( livro.get(i).getTitulo() );
				lblLivroAutor_2 .setText( livro.get(i).getAutor() );
				lblLivroValor_2.setText( "R$ " + String.valueOf( livro.get(i).getPrecoVenda() ));
				imagem = livro.get(i).getImagem();
				carregaCapa(i);
				lblLivroCapa_2.setVisible(true);
				lblLivroTitulo_2.setVisible(true);
				lblLivroAutor_2.setVisible(true);
				lblLivroValor_2.setVisible(true);
				btnAddCarrinho_2.setVisible(true);
				btnDetalhes_2.setVisible(true);
			break; 

			}
		}
	}

	public void carregaCapa(int capa){

		switch (capa){

		case 0:
			ImageIcon capa1 = new ImageIcon( imagem );
			lblLivroCapa_1.setIcon(new ImageIcon(capa1.getImage().getScaledInstance(lblLivroCapa_1.getWidth(), 
					lblLivroCapa_1.getHeight(), Image.SCALE_DEFAULT)));
			break;

		case 1:
			ImageIcon capa2 = new ImageIcon( imagem );
			lblLivroCapa_2.setIcon(new ImageIcon(capa2.getImage().getScaledInstance(lblLivroCapa_2.getWidth(), 
					lblLivroCapa_2.getHeight(), Image.SCALE_DEFAULT)));
			break;
		}
	}
	
	
	public void lerCarrinho() {

		int qtd = 0;
		ArquivoCarrinho dao = new ArquivoCarrinho();
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			dao.leArquivo(diretorio + "data/", arquivo);
			linha = dao.getBuffer();
			String[] listaItens = linha.split(";");
			for (String s : listaItens) {
				String text = s.replaceAll(".*: ", "");
				lista.add(text);
				if (s.contains("---")) {
					qtd =  qtd + Integer.parseInt(lista.get(1));
					lista.clear();
				}
				btnCarrinho.setText("Meu Carrinho ( " + qtd + " )");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void Adicionar(){
		
		if( livros.size() > 0 ){
			
			FrmCarrinho carrinho = new FrmCarrinho();
			carrinho.setVisible(true);
			
			CarrinhoController carrinhoCtrl = new CarrinhoController(
					carrinho, 
					carrinho.tabCompra, 
					carrinho.ftxtQtd, 
					carrinho.ftxtVlrTotal);
			carrinhoCtrl.addItem ( livros.get(select) );
		}
	}
	
	
	public void abrirJanela ( String nome ){


		switch ( nome ){

		case "registros":
			msg("", "NAVEGAÇÃO DE REGISTROS em implementação!");
			break;
		case "principal":
			if (janelaPrincipal == null){
				janelaPrincipal = new FrmPrincipal();
				janelaPrincipal.setVisible(true);
			} else {
				janelaPrincipal.setVisible(true);
				janelaPrincipal.setState(JFrame.NORMAL);
			}
			janela.dispose();
			break;
		case "carrinho":
			if (janelaCarrinho == null){
				janelaCarrinho = new FrmCarrinho();
				janelaCarrinho.setVisible(true);
			} else {
				janelaCarrinho.setVisible(true);
				janelaCarrinho.setState(JFrame.NORMAL);
			}
			break;
			
		case "detalhes":
			if( janelaDetalhe == null ){
				FrmDetalhe detalhe;
				detalhe = new FrmDetalhe( janela, opt, isbn, livros );
				detalhe.setVisible(true);
				esconder();
			} else {
				janelaDetalhe.setVisible(true);
				esconder();
			}
			break;
		}
	}
	
	
	// MENSAGENS //////////////////////////////


		public void msg( String tipo, String mensagem ) {
			janela.setAlwaysOnTop (false);

			switch ( tipo ) {

			case "sistema":
				Object[] exit = { "Confirmar", "Cancelar" };  
				int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o " + mensagem 
						+ " do sistema!\n\nDeseja encerrar a aplicação?",
						"Fechamento do Programa!", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
						new ImageIcon( diretorio + "/icons/warning.png" ), exit, exit[1] );
				if ( fechar == 0 ) {
				} else {
					
				}
				break;

			default:
				JOptionPane.showMessageDialog(null, 
						mensagem, 
						"Erro no Sistema", 
						JOptionPane.PLAIN_MESSAGE,
						new ImageIcon( diretorio + "/icons/error.png" ));
			}
			janela.setAlwaysOnTop (true);
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
					if(janela != null)
						System.exit(0);
				}
	
	
	// CONTROLE BOTAO //////////////////////////////

	public ActionListener adicionar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//verifica qual botãoo está solicitando a ação
			Object source = e.getSource();
			
			if(source == btnAddCarrinho_1){
				select = 0;
			}
			if(source == btnAddCarrinho_2){
				select = 1;
			}
			Adicionar();
		}
	};
	
	public ActionListener janelas = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//verifica qual botãoo está solicitando a ação
			Object source = e.getSource();
			
			if(source == btnVoltar){
				abrirJanela( "principal" );
			}
			if (source == btnCarrinho){
				abrirJanela( "carrinho" );
			}
			if (source == btnDetalhes_1){
				opt = 0;
				abrirJanela( "detalhes" );		
			}
			if (source == btnDetalhes_2){
				opt = 1;
				abrirJanela( "detalhes" );
			}
		}
	};
	
	public ActionListener registros = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//verifica qual botãoo está solicitando a ação
			Object source = e.getSource();

			if(source == btnAnterior){
				abrirJanela( "registros" );
			}
			if (source == btnProximo){
				abrirJanela( "registros" );
			}
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
						msg("sistema","Fechamento");
							if(validar == true){
							System.exit(0);
							}
						break;
					case KeyEvent.VK_DELETE:
						break;
					case 8: //MAC OSX: DELETE
						break;
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {

				}
			};
			
			
			// CONTROLE FOCO ///////////////////////////////
			
			public FocusListener foco = new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					lerCarrinho();
				}

				@Override
				public void focusLost(FocusEvent e) {
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

					if(e.getClickCount() == 1){  
						//verifica qual JLABEL está solicitando a ação
						Object source = e.getSource();
						
						if(e.getClickCount() == 1){  
							if(source == lblLivroCapa_1){
								opt = 0;
								abrirJanela( "detalhes" );
							}
							if (source == lblLivroCapa_2){
								opt = 1;
								abrirJanela( "detalhes" );
							}
						}
					}
				}
			};
		}

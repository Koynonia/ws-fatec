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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import boundary.FrmCarrinho;
import boundary.FrmDetalhe;
import boundary.FrmLista;
import boundary.FrmPrincipal;
import entity.Livro;

public class DetalheController implements ComponentListener {
	
	private FrmDetalhe janela;
	private FrmPrincipal janelaPrincipal;
	private FrmLista janelaLista;
	private JFrame janelaAnterior;
	private List<Livro> livros;
	private JLabel lblTitulo; 
	private JLabel lblAutor; 
	private JLabel lblCategria; 
	private JLabel lblTipoCapa; 
	private JLabel lblEditora; 
	private JLabel lblPaginas; 
	private JLabel lblIsbn; 
	private JLabel lblDtPub; 
	private JLabel lblCapa; 
	private JLabel lblPreco;
	private JTextArea txtaSumario;
	private JTextArea txtaResumo;
	private JButton btnAddCarrinho;
	private JButton btnVoltar;
	private String diretorio = "../LivrariaDigital_teste/";
	private String imagem;
	private boolean validar;
	private int select;
	
	public DetalheController (
			FrmDetalhe janela, 
			JFrame janelaAnterior, 
			List<Livro> livros, 
			JLabel lblTitulo, 
			JLabel lblAutor, 
			JLabel lblCategria, 
			JLabel lblTipoCapa, 
			JLabel lblEditora, 
			JLabel lblPaginas, 
			JLabel lblIsbn, 
			JLabel lblDtPub, 
			JLabel lblCapa, 
			JLabel lblPreco,
			JTextArea txtaSumario,
			JTextArea txtaResumo,
			JButton btnAddCarrinho, 
			JButton btnVoltar){
		
		this.janela = janela;
		this.janelaAnterior = janelaAnterior;
		this.livros = livros;
		this.lblTitulo = lblTitulo;
		this.lblAutor = lblAutor;
		this.lblCategria = lblCategria;
		this.lblTipoCapa = lblTipoCapa;
		this.lblEditora = lblEditora;
		this.lblPaginas = lblPaginas;
		this.lblIsbn = lblIsbn;
		this.lblDtPub = lblDtPub;
		this.lblCapa = lblCapa;
		this.lblPreco = lblPreco;
		this.txtaSumario = txtaSumario;
		this.txtaResumo = txtaResumo;
		this.btnAddCarrinho = btnAddCarrinho;
		this.btnVoltar = btnVoltar;
		
	}
	
	
	public void escreverTela( int opt, ArrayList<String> isbn){
		
		int i = 0;
		
		for( Livro livro : livros ){	
			if ( livro.getIsbn().equals( isbn.get( opt ) )) {
				lblTitulo.setText( "Título: " + livro.getTitulo() );
				lblAutor.setText( "Autor(es): " + livro.getAutor() ); 
				lblCategria.setText( "Categoria(s): " + livro.getCategoria() );
				lblTipoCapa.setText( "Tipo de Capa: " + livro.getCapa() );
				lblEditora.setText( "Editora: " + livro.getEditora() );
				lblPaginas.setText( "Páginas: " + livro.getPaginas() );
				lblIsbn.setText( "ISBN: " + livro.getIsbn() );
				lblDtPub.setText( "Data de Publicação: " + livro.getDtPublicacao() );
				imagem = livro.getImagem();
				lblPreco.setText( "Preço: R$ " + livro.getPrecoVenda() );
				//		txtaSumario
				//		txtaResumo
				carregaCapa();
				select = i;
			}
			i = i + 1;
		}
	}
	
	public void carregaCapa(){

		ImageIcon capa = new ImageIcon( imagem );
		lblCapa.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblCapa.getWidth(), 
				lblCapa.getHeight(), Image.SCALE_DEFAULT)));
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
			carrinhoCtrl.addItem ( livros.get( select ) );
		}
	}
	
	
	public void abrirJanela ( String nome ){


		switch ( nome ){

		case "principal":
			if (janelaPrincipal == null){
				janelaPrincipal = new FrmPrincipal();
				janelaPrincipal.setVisible(true);
			} else {
				janelaPrincipal.setVisible(true);
				janelaPrincipal.setState(JFrame.NORMAL);
			}
			fechar();
			break;
			
		case "lista":
			if (janelaLista == null){
				janelaLista = new FrmLista(nome, nome, livros);
				janelaLista.setVisible(true);
			} else {
				janelaLista.setVisible(true);
				janelaLista.setState(JFrame.NORMAL);
			}
			fechar();
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
			Adicionar();
		}
	};
	
	public ActionListener janelas = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//verifica qual botãoo está solicitando a ação
			Object source = e.getSource();

			if(source == btnVoltar){
				switch ( janelaAnterior.getTitle() ){
				case "Resultados da Pesquisa":
					abrirJanela( "lista" );
					fechar();
					break;
				case "Principal":
					abrirJanela( "principal" );
					fechar();
					break;
				}
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
						msg("", "Ainda não Implementado!");
					}
				}
			};
		}

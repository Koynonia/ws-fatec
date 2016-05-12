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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	private String imagem; 
	private boolean validar; 
	private int opt;
	private SessaoController logon = SessaoController.getInstance();
	private ArrayList<String> isbn = new ArrayList<>();
	LinkedList<Livro> regLivro = new LinkedList<>();
	private List<Livro> livros;
	
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
		
		dados();
		tela();
		
	}
	
	
	//  TELA    ///////////////
	
	public void dados(){
		
		logon.rastrear( janela.getName() );
		atualizar("carrinho");
	}

	
	public void tela(){
		
		configurarTela();
	}
	
	
	public void ativarCampos( String opt ){

		switch ( opt ){
		
		case "ativarNavegar":
			btnAnterior.setVisible(true);
			btnProximo.setVisible(true);
			break;
			
		case "desativarNavegar":
			btnAnterior.setVisible(false);
			btnProximo.setVisible(false);
			break;

		case "ativarCapas_1":
			lblLivroCapa_1.setVisible(true);
			lblLivroTitulo_1.setVisible(true);
			lblLivroAutor_1.setVisible(true);
			lblLivroValor_1.setVisible(true);
			btnAddCarrinho_1.setVisible(true);
			btnDetalhes_1.setVisible(true);
			break;
			
		case "ativarCapas_2":
			lblLivroCapa_2.setVisible(true);
			lblLivroTitulo_2.setVisible(true);
			lblLivroAutor_2.setVisible(true);
			lblLivroValor_2.setVisible(true);
			btnAddCarrinho_2.setVisible(true);
			btnDetalhes_2.setVisible(true);
			break;
			
		case "desativarCapas":
			lblLivroCapa_1.setVisible(false);
			lblLivroTitulo_1.setVisible(false);
			lblLivroAutor_1.setVisible(false);
			lblLivroValor_1.setVisible(false);
			btnAddCarrinho_1.setVisible(false);
			btnDetalhes_1.setVisible(false);
			lblLivroCapa_2.setVisible(false);
			lblLivroTitulo_2.setVisible(false);
			lblLivroAutor_2.setVisible(false);
			lblLivroValor_2.setVisible(false);
			btnAddCarrinho_2.setVisible(false);
			btnDetalhes_2.setVisible(false);
			break;
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
	
	
	// CRUD //////////////////////////////
	
	public void configurarTela(){

		ativarCampos( "desativarCapas" );
		btnCarrinho.setText("Meu Carrinho ( " + logon.carrinhoQtd() + " )");
		DecimalFormat formato = new DecimalFormat("#,##0.00");

		if ( livros.size() <= 2){
			ativarCampos( "desativarNavegar" );
		} else {
			ativarCampos( "ativarNavegar" );
		}

		for( int i = 0; i < livros.size(); i++ ){

			switch (i){

			case 0:
				isbn.add( livros.get(i).getIsbn() );
				lblLivroCapa_1.setToolTipText( livros.get(i).getTitulo() );
				lblLivroTitulo_1.setText( livros.get(i).getTitulo() );
				lblLivroAutor_1 .setText( livros.get(i).getAutor() );
				lblLivroValor_1.setText( "R$ " + String.valueOf( formato.format( livros.get(i).getPrecoVenda() )));
				imagem = livros.get(i).getImagem();
				carregaCapa(0);
				break;

			case 1:
				isbn.add( livros.get(i).getIsbn() );
				lblLivroCapa_2.setToolTipText( livros.get(i).getTitulo() );
				lblLivroTitulo_2.setText( livros.get(i).getTitulo() );
				lblLivroAutor_2 .setText( livros.get(i).getAutor() );
				lblLivroValor_2.setText( "R$ " + String.valueOf( formato.format( livros.get(i).getPrecoVenda() )));
				imagem = livros.get(i).getImagem();
				carregaCapa(1);
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
			ativarCampos( "ativarCapas_1" );
			break;

		case 1:
			ImageIcon capa2 = new ImageIcon( imagem );
			lblLivroCapa_2.setIcon(new ImageIcon(capa2.getImage().getScaledInstance(lblLivroCapa_2.getWidth(), 
					lblLivroCapa_2.getHeight(), Image.SCALE_DEFAULT)));
			ativarCampos( "ativarCapas_2" );
			break;
		}
	}
	

	public void navegar( String opt ){

		DecimalFormat formato = new DecimalFormat("#,##0.00"); 
		isbn.clear(); //limpa a referencia para carregar os detalhes do livro
		int reg = 0; //controla os próximos 2 livros
		regLivro.clear();//limpa a navegação
		ativarCampos( "desativarCapas" );//esconde os campos


		switch ( opt ){

		case "proximo":

			//localiza o livro atual e repassa para a variável de controle do registro
			for( int i = 0; i < livros.size(); i++ ){
				if ( livros.get(i).getTitulo() == lblLivroTitulo_2.getText() ){
					reg = i;
				}
			}
			if ( reg < ( livros.size() -1 )  && reg != 0 ){

				//System.out.println( livros.size() % 2 );

				//Verifica se é par ou impar o próximo livro
				if ( !(reg + 2 == livros.size() ) ){
					//Carrega os 2 livros próximos
					for( int i = 0; i < 2 ; i++ ){
						reg++;
						
						Livro l = new Livro();
						
						l.setIsbn( livros.get(reg).getIsbn() );
						l.setTitulo( livros.get(reg).getTitulo() );
						l.setAutor( livros.get(reg).getAutor() );
						l.setPrecoVenda( livros.get(reg).getPrecoVenda());
						l.setImagem( livros.get(reg).getImagem() );
						regLivro.add(l);
						
					}
				} else {
					
					//Carrega o último livro em caso do total ser impar
					reg++;
					Livro l = new Livro();
					l.setIsbn( livros.get(reg).getIsbn() );
					l.setTitulo( livros.get(reg).getTitulo() );
					l.setAutor( livros.get(reg).getAutor() );
					l.setPrecoVenda( livros.get(reg).getPrecoVenda());
					l.setImagem( livros.get(reg).getImagem() );
					regLivro.add(l);
				}
			}
//			System.out.println( reg + " de " + livros.size() + " regLivro: " + regLivro.size() );
			break;

		case "anterior":

			//localiza o livro atual e repassa para a variável de controle
			for( int i = 0; i < livros.size(); i++ ){
				if ( livros.get(i).getTitulo() == lblLivroTitulo_1.getText() ){
					reg = i -1;
				}
			}
			if ( reg <= ( livros.size() )  && reg >= 0 ){
				//Carrega os 2 livros anteriores
				for( int i = 0; i < 2 ; i++ ){
					reg--;
					if ( reg > -1 ){
						
						Livro l = new Livro();
						l.setIsbn( livros.get(reg).getIsbn() );
						l.setTitulo( livros.get(reg).getTitulo() );
						l.setAutor( livros.get(reg).getAutor() );
						l.setPrecoVenda( livros.get(reg).getPrecoVenda());
						l.setImagem( livros.get(reg).getImagem() );
						regLivro.add(l);
						reg = reg +2;
					}
				}
			}
			break;
		}
		
		//Preenche Livro 1
		if ( regLivro != null && regLivro.size() >= 1 ){
			isbn.add( regLivro.get(0).getIsbn() );
			lblLivroCapa_1.setToolTipText( regLivro.get(0).getTitulo() );
			lblLivroTitulo_1.setText( regLivro.get(0).getTitulo() );
			lblLivroAutor_1 .setText( regLivro.get(0).getAutor() );
			lblLivroValor_1.setText( "R$ " + String.valueOf( formato.format( regLivro.get(0).getPrecoVenda() )));
			imagem = regLivro.get(0).getImagem();
			carregaCapa(0);
			
			//Preenche Livro 2
			if ( regLivro != null && regLivro.size() != 1 ){
				isbn.add( regLivro.get(1).getIsbn() );
				lblLivroCapa_2.setToolTipText( regLivro.get(1).getTitulo() );
				lblLivroTitulo_2.setText( regLivro.get(1).getTitulo() );
				lblLivroAutor_2 .setText( regLivro.get(1).getAutor() );
				lblLivroValor_2.setText( "R$ " + String.valueOf( formato.format( regLivro.get(1).getPrecoVenda() )));
				imagem = regLivro.get(1).getImagem();
				carregaCapa(1);
			}
		}
	}
	
	
	public void adicionarCarrinho (Livro livro){
		
		if( livros.size() > 0 ){
			
			FrmCarrinho carrinho = new FrmCarrinho();
			carrinho.setVisible(false);
			
			CarrinhoController carrinhoCtrl = new CarrinhoController(
					carrinho, 
					carrinho.tabCompra, 
					carrinho.ftxtQtd, 
					carrinho.ftxtVlrTotal);
			carrinhoCtrl.addItem ( livro );
		}
	}
	
	
	public void atualizar( final String opt ) {

		// Verifica o estado do carrinho a cada segundo
		// Linhas comentadas de código é para uma implementação de log para relatório futuro
		Timer timer = null;      
		//        final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		if (timer == null)   
		{      
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {     
				public void run()   
				{      
					try {               	 
						//txtPesquisar.setText("Hora: "+format.format( new Date().getTime() )); 

						switch ( opt ){

						case "carrinho":
							btnCarrinho.setText("Meu Carrinho ( " + logon.carrinhoQtd() + " )");
							break;
						}
					} catch (Exception e) {      
						e.printStackTrace();      
					}      
				}
			};      
			timer.scheduleAtFixedRate(tarefa, 0, 1000);      
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
				if ( regLivro.size() > 0){
					adicionarCarrinho (regLivro.get(0));
				} else {
					adicionarCarrinho (livros.get(0));
				}
			}
			if(source == btnAddCarrinho_2){
				if ( regLivro.size() > 1){
					adicionarCarrinho (regLivro.get(1));
				} else {
					adicionarCarrinho (livros.get(1));
				}
			}
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
				navegar ("anterior");
			}
			if (source == btnProximo){
				navegar("proximo");
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
						navegar("anterior");
						break;
					case KeyEvent.VK_RIGHT:
						navegar("proximo");
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
					btnCarrinho.setText("Meu Carrinho ( " + logon.carrinhoQtd() + " )");
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

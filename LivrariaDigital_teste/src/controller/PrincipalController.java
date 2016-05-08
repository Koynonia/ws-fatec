/**
 * @author Fernando Moraes Oliveira e Vitor Fagundes Arantes
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 06/04/2016
 */

package controller;

import java.awt.Component;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import boundary.FrmCarrinho;
import boundary.FrmDetalhe;
import boundary.FrmLista;
import boundary.FrmLivro;
import boundary.FrmLogin;
import dao.ArquivoLivro;
import entity.Livro;

public class PrincipalController implements ComponentListener {
	
	private JFrame janela;
	private FrmLista janelaLista;
	private FrmCarrinho janelaCarrinho;
	private FrmLogin janelaLogin;
	private FrmLivro janelaLivro;
	private FrmDetalhe janelaDetalhe;
	private JPanel painel;
	private JComboBox<String> cboFiltrar;
	private JTextField txtPesquisar;
	private JButton btnLogin; 
	private JButton btnCarrinho;
	private JButton btnLivro;
	private JLabel lblLivro; 
	private JLabel lblLivroLanc_1;
	private JLabel lblLivroLanc_2;
	private JLabel lblLivroLanc_3;
	private JLabel lblLivroLanc_4;
	private JLabel lblLivroLanc_5;
	private JLabel lblLivroLanc_6;
	private JLabel lblLivroVend_1;
	private JLabel lblLivroVend_2;
	private JLabel lblLivroVend_3;
	private JLabel lblLivroVend_4;
	private JLabel lblLivroVend_5;
	private JLabel lblLivroVend_6;
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "livro";
	private String imagem;
	private boolean validar;
	private int opt;
	private SessaoController logon = SessaoController.getInstance();
	private ArquivoLivro dao = new ArquivoLivro();
	private OrdenaLivro ordenar = new OrdenaLivro();
	private List<Livro> livros;
	private List<Livro> livrosVendidos;
	private ArrayList<String> isbn = new ArrayList<>();
	
	public PrincipalController(
			JFrame janela, 
			JPanel painel, 
			JComboBox<String> cboFiltrar, 
			JTextField txtPesquisar,  
			JButton btnLogin, 
			JButton btnCarrinho, 
			JButton btnLivro, 
			JLabel lblLivro,
			JLabel lblLivroLanc_1, 
			JLabel lblLivroLanc_2, 
			JLabel lblLivroLanc_3, 
			JLabel lblLivroLanc_4, 
			JLabel lblLivroLanc_5, 
			JLabel lblLivroLanc_6, 
			JLabel lblLivroVend_1, 
			JLabel lblLivroVend_2, 
			JLabel lblLivroVend_3, 
			JLabel lblLivroVend_4, 
			JLabel lblLivroVend_5, 
			JLabel lblLivroVend_6 
			) {
		
		this.janela = janela;
		this.painel = painel;
		this.cboFiltrar = cboFiltrar;
		this.txtPesquisar = txtPesquisar;
		this.btnLogin = btnLogin;
		this.btnCarrinho = btnCarrinho;
		this.btnLivro = btnLivro;
		this.lblLivro = lblLivro;
		this.lblLivroLanc_1 = lblLivroLanc_1;
		this.lblLivroLanc_2 = lblLivroLanc_2;
		this.lblLivroLanc_3 = lblLivroLanc_3;
		this.lblLivroLanc_4 = lblLivroLanc_4;
		this.lblLivroLanc_5 = lblLivroLanc_5;
		this.lblLivroLanc_6 = lblLivroLanc_6;
		this.lblLivroVend_1 = lblLivroVend_1;
		this.lblLivroVend_2 = lblLivroVend_2;
		this.lblLivroVend_3 = lblLivroVend_3;
		this.lblLivroVend_4 = lblLivroVend_4;
		this.lblLivroVend_5 = lblLivroVend_5;
		this.lblLivroVend_6 = lblLivroVend_6;
		this.livros = new ArrayList<Livro>();
		this.livrosVendidos = new ArrayList<Livro>();
		
		dados();
		tela();
		
	}
	
	
	//   TELA    ///////////////
	
	public void dados(){
		
		//logon.rastrear( janela.getName() );
		logon.logoff();
		lerArquivo();
		atualizar("carrinho");
	}
	
	
	public void tela(){
		
		Collections.sort( livros, Collections.reverseOrder( ordenar ) );
		livrosSpot();
		preencherFiltro();
		focarCampo();
		login();
	}
	
	public void focarCampo(){
		
		SwingUtilities.invokeLater(new Runnable() {  
			public void run() {  
				txtPesquisar.requestFocus();  
			}  
		});
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
	
	
	public void login(){

		lblLivro.setVisible(false);
		btnLivro.setVisible(false);
		btnLogin.setText("Entrar");
		
		if ( logon.getLogon().size() > 0 ){
			btnLogin.setText("Bem-vindo, " + logon.getLogon().get(0).getUsuario() + "!");
			if ( logon.getLogon().get(0).getNivel().equalsIgnoreCase("Administrador") ){
				lblLivro.setVisible(true);
				btnLivro.setVisible(true);
			}
		}
	}

	
	public void livrosSpot(){
		
		String campo;
		String titulo = "";
		
		for ( int i = 0; i < livros.size() + 1; i++){

			switch (i){

			case 1:
				campo =  "lblLivroLanc_" + 1;
				if( livros.size() >= i ){ titulo = livros.get(i-1).getTitulo() 
						+ " ( R$ " + livros.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };			
				isbn.add( livros.get(i-1).getIsbn() );
				imagem = livros.get(i-1).getImagem();
				ImageIcon capa1 = new ImageIcon( imagem );
				lblLivroLanc_1.setIcon(new ImageIcon(capa1.getImage().getScaledInstance(lblLivroLanc_1.getWidth(), 
						lblLivroLanc_1.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				
				campo =  "lblLivroVend_" + 1;
				if( livrosVendidos.size() >= i ){ titulo = livrosVendidos.get(i-1).getTitulo()
						+ " ( R$ " + livrosVendidos.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };
				isbn.add( livrosVendidos.get(i-1).getIsbn() );
				imagem = livrosVendidos.get(i-1).getImagem();
				ImageIcon capa2 = new ImageIcon( imagem );
				lblLivroVend_1.setIcon(new ImageIcon(capa2.getImage().getScaledInstance(lblLivroVend_1.getWidth(), 
						lblLivroVend_1.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				break;

			case 2:
				campo =  "lblLivroLanc_" + 2;
				if( livros.size() >= i ){ titulo = livros.get(i-1).getTitulo()
						+ " ( R$ " + livros.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };			
				isbn.add( livros.get(i-1).getIsbn() );
				imagem = livros.get(i-1).getImagem();
				ImageIcon capa3 = new ImageIcon( imagem );
				lblLivroLanc_2.setIcon(new ImageIcon(capa3.getImage().getScaledInstance(lblLivroLanc_2.getWidth(), 
						lblLivroLanc_2.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				
				campo =  "lblLivroVend_" + 2;
				if( livrosVendidos.size() >= i ){ titulo = livrosVendidos.get(i-1).getTitulo()
						+ " ( R$ " + livrosVendidos.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };
				isbn.add( livrosVendidos.get(i-1).getIsbn() );
				imagem = livrosVendidos.get(i-1).getImagem();
				ImageIcon capa4 = new ImageIcon( imagem );
				lblLivroVend_2.setIcon(new ImageIcon(capa4.getImage().getScaledInstance(lblLivroVend_2.getWidth(), 
						lblLivroVend_2.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				break;

			case 3:
				campo =  "lblLivroLanc_" + 3;
				if( livros.size() >= i ){ titulo = livros.get(i-1).getTitulo()
						+ " ( R$ " + livros.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };			
				isbn.add( livros.get(i-1).getIsbn() );
				imagem = livros.get(i-1).getImagem();
				ImageIcon capa5 = new ImageIcon( imagem );
				lblLivroLanc_3.setIcon(new ImageIcon(capa5.getImage().getScaledInstance(lblLivroLanc_3.getWidth(), 
						lblLivroLanc_3.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				
				campo =  "lblLivroVend_" + 3;
				if( livrosVendidos.size() >= i ){ titulo = livrosVendidos.get(i-1).getTitulo()
						+ " ( R$ " + livrosVendidos.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };
				isbn.add( livrosVendidos.get(i-1).getIsbn() );
				imagem = livrosVendidos.get(i-1).getImagem();
				ImageIcon capa6 = new ImageIcon( imagem );
				lblLivroVend_3.setIcon(new ImageIcon(capa6.getImage().getScaledInstance(lblLivroVend_3.getWidth(), 
						lblLivroVend_3.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				break;

			case 4:
				campo =  "lblLivroLanc_" + 4;
				if( livros.size() >= i ){ titulo = livros.get(i-1).getTitulo()
						+ " ( R$ " + livros.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };			
				isbn.add( livros.get(i-1).getIsbn() );
				imagem = livros.get(i-1).getImagem();
				ImageIcon capa7 = new ImageIcon( imagem );
				lblLivroLanc_4.setIcon(new ImageIcon(capa7.getImage().getScaledInstance(lblLivroLanc_4.getWidth(), 
						lblLivroLanc_4.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				
				campo =  "lblLivroVend_" + 4;
				if( livrosVendidos.size() >= i ){ titulo = livrosVendidos.get(i-1).getTitulo()
						+ " ( R$ " + livrosVendidos.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };
				isbn.add( livrosVendidos.get(i-1).getIsbn() );
				imagem = livrosVendidos.get(i-1).getImagem();
				ImageIcon capa8 = new ImageIcon( imagem );
				lblLivroVend_4.setIcon(new ImageIcon(capa8.getImage().getScaledInstance(lblLivroVend_4.getWidth(), 
						lblLivroVend_4.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				break;

			case 5:
				campo =  "lblLivroLanc_" + 5;
				if( livros.size() >= i ){ titulo = livros.get(i-1).getTitulo()
						+ " ( R$ " + livros.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };			
				isbn.add( livros.get(i-1).getIsbn() );
				imagem = livros.get(i-1).getImagem();
				ImageIcon capa9 = new ImageIcon( imagem );
				lblLivroLanc_5.setIcon(new ImageIcon(capa9.getImage().getScaledInstance(lblLivroLanc_5.getWidth(), 
						lblLivroLanc_5.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				
				campo =  "lblLivroVend_" + 5;
				if( livrosVendidos.size() >= i ){ titulo = livrosVendidos.get(i-1).getTitulo()
						+ " ( R$ " + livrosVendidos.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };
				isbn.add( livrosVendidos.get(i-1).getIsbn() );
				imagem = livrosVendidos.get(i-1).getImagem();
				ImageIcon capa10 = new ImageIcon( imagem );
				lblLivroVend_5.setIcon(new ImageIcon(capa10.getImage().getScaledInstance(lblLivroVend_5.getWidth(), 
						lblLivroVend_5.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				break;

			case 6:
				campo =  "lblLivroLanc_" + 6;
				if( livros.size() >= i ){ titulo = livros.get(i-1).getTitulo()
						+ " ( R$ " + livros.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };			
				isbn.add( livros.get(i-1).getIsbn() );
				imagem = livros.get(i-1).getImagem();
				ImageIcon capa11 = new ImageIcon( imagem );
				lblLivroLanc_6.setIcon(new ImageIcon(capa11.getImage().getScaledInstance(lblLivroLanc_6.getWidth(), 
						lblLivroLanc_6.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				
				campo =  "lblLivroVend_" + 6;
				if( livrosVendidos.size() >= i ){ titulo = livrosVendidos.get(i-1).getTitulo()
						+ " ( R$ " + livrosVendidos.get(i-1).getPrecoVenda() + " )"; } else { titulo = ""; };
				isbn.add( livrosVendidos.get(i-1).getIsbn() );
				imagem = livrosVendidos.get(i-1).getImagem();
				ImageIcon capa12 = new ImageIcon( imagem );
				lblLivroVend_6.setIcon(new ImageIcon(capa12.getImage().getScaledInstance(lblLivroVend_6.getWidth(), 
						lblLivroVend_6.getHeight(), Image.SCALE_DEFAULT)));
				percorrerCampos ( campo, titulo );
				break;
			}
		}
	}
	
	
	private void percorrerCampos(String campo, String titulo) {

		for (Component p : painel.getComponents()) {
			if (p instanceof JLabel) {
				JLabel l = (JLabel)p;
				if ( l.getName() == campo) {
					l.setVisible(true);
					l.setToolTipText( titulo );
				}
			}
		}   
	}
	
	
	// PREENCHE COMBOBOX /////////////////////
	
		public void preencherFiltro() {		
			
			cboFiltrar.addItem("Título");
			cboFiltrar.addItem("Autor");
			cboFiltrar.addItem("Editora");
			cboFiltrar.addItem("Categoria");
			cboFiltrar.setSelectedIndex(0);
		}

	
	public void abrirJanela ( String nome ){

		switch ( nome ){

		case "carrinho":
			if( janelaCarrinho == null ){
				FrmCarrinho carrinho = new FrmCarrinho();
				carrinho.setVisible(true);
			} else {
				janelaCarrinho.setVisible(true);
			}
			break;

		case "login":
			if( janelaLogin == null ){
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
			} else {
				janelaLogin.setVisible(true);
			}
			break;
			
		case "livro":
			if( janelaLivro == null ){
				FrmLivro livro;
				try {
					livro = new FrmLivro();
					livro.setVisible(true);
					fechar();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				janelaLivro.setVisible(true);
				fechar();
			}
			break;
			
		case "detalhes":
			if( janelaDetalhe == null ){
				FrmDetalhe detalhe;
				detalhe = new FrmDetalhe( janela, opt, isbn, livros );
				detalhe.setVisible(true);
				fechar();
			} else {
				janelaDetalhe.setVisible(true);
				fechar();
			}
			break;
		}
	}
	
	
	// CRUD //////////////////////////
	
	public void pesquisarLivro(String itemMenu, String texto){
		
		List<Livro> listLivros = new ArrayList<Livro>();
		
		for( Livro livro : livros ){
			if(itemMenu.equalsIgnoreCase("TÍTULO")){
				if( livro.getTitulo().toLowerCase().startsWith( ( texto.toLowerCase() )) || 
						livro.getTitulo().toLowerCase().contains( texto.toLowerCase() )){
					listLivros.add( livro );
				}
			} else if(itemMenu.equalsIgnoreCase("AUTOR")){
				if(livro.getAutor().toLowerCase().startsWith( ( texto.toLowerCase() )) || 
						livro.getAutor().toLowerCase().contains( texto.toLowerCase() )){
					listLivros.add( livro );
				}
			} else if(itemMenu.equalsIgnoreCase("EDITORA")){
				if(livro.getEditora().toLowerCase().startsWith( ( texto.toLowerCase() )) || 
						livro.getEditora().toLowerCase().contains( texto.toLowerCase() )){
					listLivros.add( livro );
				}
			} else if(itemMenu.equalsIgnoreCase("CATEGORIA")){
				if(livro.getCategoria().toLowerCase().startsWith( ( texto.toLowerCase() )) || 
						livro.getCategoria().toLowerCase().contains( texto.toLowerCase() )){
					listLivros.add( livro );
				}
			}
		}
		if( listLivros.size() > 0 && !itemMenu.isEmpty() ){
			if ( janelaLista == null ){
				janelaLista = new FrmLista( itemMenu, texto, listLivros );
				janelaLista.setVisible(true);
			} else {
				janelaLista.setVisible(true);
				janelaLista.setState(JFrame.NORMAL);					
			}
			janela.dispose();
		} else if( !itemMenu.isEmpty() ){
			msg("vazioPesquisa", txtPesquisar.getText().toUpperCase());
		} else {
			msg("erroPesquisa","");
		}
	}
	
	
	public void lerArquivo() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			dao.lerArquivo(diretorio + "dados/", arquivo);
			linha = dao.getBuffer();
			String[] listaItens = linha.split(";");
			for (String s : listaItens) {
				String text = s.replaceAll(".*: ", "");
				lista.add(text);
				if (s.contains("---")) {
					Livro livro = new Livro();
					livro.setIsbn( lista.get(0) );
					livro.setTitulo( lista.get(1) );
					livro.setAutor( lista.get(2) );
					livro.setEditora( lista.get(3) );
					livro.setDtPublicacao( lista.get(4) );
					livro.setCapa( lista.get(5) );
					livro.setPaginas( Integer.parseInt( lista.get(6) ) );
					livro.setCategoria( lista.get(7) );
					livro.setSumario( lista.get(8));
					livro.setResumo( lista.get(9));
					livro.setPrecoVenda( Float.parseFloat( lista.get(11) ) );
					livro.setImagem( lista.get(12) );
					livro.setDtCadastro( lista.get(13) );
					livro.setDtAlterado( lista.get(14) );
					livros.add( livro );
					livrosVendidos.add( livro );
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// MENSAGENS //////////////////////////////


	public void msg( String tipo, String mensagem ) {
		janela.setAlwaysOnTop (false);

		switch ( tipo ) {
		
		case "vazioPesquisa":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nNenhum resultador encontrado com: " + mensagem, 
					"Não Localizado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroPesquisa":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite algo para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
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

		public ActionListener sairSistema = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logon.encerrar();
				sair();
			}
		};
		
		public ActionListener pesquisar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				pesquisarLivro( 
						cboFiltrar.getSelectedItem().toString().toUpperCase(), 
						txtPesquisar.getText() );
			}
		};
		
		public ActionListener janelas = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//verifica qual JBUTTON está solicitando a ação
				Object source = e.getSource();

				if(source == btnCarrinho){
					abrirJanela( "carrinho" );
				}
				if (source == btnLogin){
					abrirJanela( "login" );
				}
				if (source == btnLivro){
					abrirJanela( "livro" );
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
						sair();
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
				login();
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
				
				//verifica qual JLABEL está solicitando a ação
				Object source = e.getSource();
				
				if(e.getClickCount() == 1){  
					if(source == lblLivroLanc_1){
						opt = 0;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroLanc_2){
						opt = 2;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroLanc_3){
						opt = 4;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroLanc_4){
						opt = 6;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroLanc_5){
						opt = 8;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroLanc_6){
						opt = 10;
						abrirJanela( "detalhes" );
					}
					if(source == lblLivroVend_1){
						opt = 1;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroVend_2){
						opt = 3;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroVend_3){
						opt = 5;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroVend_4){
						opt = 7;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroVend_5){
						opt = 9;
						abrirJanela( "detalhes" );
					}
					if (source == lblLivroVend_6){
						opt = 11;
						abrirJanela( "detalhes" );
					}
				}
			}
		};
	}

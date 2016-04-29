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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
=======
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import boundary.FrmPrincipal;
import dao.ArquivoLivro;
<<<<<<< HEAD
=======
import dao.Arquivos;
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
import entity.Autor;
import entity.Categoria;
import entity.Editora;
import entity.Livro;


public class LivroController implements ComponentListener{

	private JFrame janela;
	private FrmPrincipal janelaPrincipal;
	private JPanel painel;
	private JLabel lblCapa; 
<<<<<<< HEAD
	private JLabel txtIsbnID; 
	private JTextField txtPesquisar;
	private JTextField txtTitulo;
	private JTextField txtAutor;
=======
	private JTextField  txtIsbnID; 
	private JTextField txtPesquisar;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtEstoque;
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
	private JTextField txtCategoria;
	private JTextField ftxtMargem;
	private JTextField ftxtIsbn;
	private JFormattedTextField ftxtDtPub;
	private JTextField ftxtPaginas;	
	private JTextField ftxtPrecoCusto;
	private JTextField ftxtPrecoVenda;
	private JTextArea txtaSumario;
	private JTextArea txtaResumo;
	private JComboBox<String> cboAutor; 
	private JComboBox<String> cboEditora;
	private JComboBox<String> cboTipoCapa;
	private JComboBox<String> cboCategoria;
<<<<<<< HEAD
	private JButton btnLimpar; 
=======
	private JButton btnImagem; 
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
	private JButton btnEditar; 
	private JButton btnExcluir; 
	private JButton btnSalvar; 
	private JButton btnVoltar;
	private ArquivoLivro dao = new ArquivoLivro();
	private Arquivos arquivos = new Arquivos();
	private List<Livro> livros;
	private boolean validar;
	private String imagem;
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "livro";
	

	public LivroController(
			JFrame janela, 
			JPanel painel, 
			JLabel lblCapa, 
<<<<<<< HEAD
			JLabel  txtIsbnID, 
=======
			JTextField  txtIsbnID, 
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
			JTextField txtPesquisar,
			JTextField txtTitulo,
			JTextField txtAutor,
			JTextField txtEstoque,
			JTextField txtCategoria, 
			JFormattedTextField ftxtDtPub,
			JTextField ftxtPaginas,
			JTextField ftxtMargem,
			JTextField ftxtIsbn,
			JTextField ftxtPrecoCusto,
			JTextField ftxtPrecoVenda,
			JTextArea txtaSumario,
<<<<<<< HEAD
			JTextArea  txtaResumo, 
=======
			JTextArea txtaResumo, 
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
			JComboBox<String> cboAutor, 
			JComboBox<String> cboEditora, 
			JComboBox<String> cboTipoCapa, 
			JComboBox<String> cboCategoria, 
<<<<<<< HEAD
			JButton btnLimpar,
			JButton btnEditar,
			JButton btnExcluir, 
			JButton btnSalvar, 
			JButton btnVoltar 
=======
			JButton btnImagem, 
			JButton btnEditar,
			JButton btnExcluir, 
			JButton btnSalvar, 
			JButton btnVoltar
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
			){
		
		this.janela = janela;
		this.painel = painel;
		this.lblCapa = lblCapa;
		this.txtIsbnID = txtIsbnID;
		this.txtPesquisar = txtPesquisar;
		this.txtTitulo = txtTitulo;
		this.txtAutor = txtAutor;
<<<<<<< HEAD
=======
		this.txtEstoque = txtEstoque;
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		this.txtCategoria = txtCategoria;
		this.ftxtIsbn = ftxtIsbn;
		this.ftxtDtPub = ftxtDtPub;
		this.ftxtPaginas = ftxtPaginas;
		this.ftxtMargem = ftxtMargem;
		this.ftxtPrecoCusto = ftxtPrecoCusto;
		this.ftxtPrecoVenda = ftxtPrecoVenda;
<<<<<<< HEAD
		this.txtaSumario = txtaSumario;
		this.txtaResumo = txtaResumo;
=======
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		this.cboAutor = cboAutor;
		this.cboEditora = cboEditora;
		this.cboTipoCapa = cboTipoCapa;
		this.cboCategoria = cboCategoria;
<<<<<<< HEAD
		this.btnLimpar = btnLimpar;
=======
		this.btnImagem = btnImagem;
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		this.btnEditar = btnEditar;
		this.btnExcluir = btnExcluir;
		this.btnSalvar = btnSalvar;
		this.btnVoltar = btnVoltar;
		this.livros = new ArrayList<Livro>();
		
		lerArquivo();
		preencherAutor();
		preencherEditora();
		preencherCategoria();
		preencherCapa();
<<<<<<< HEAD
		imagem = diretorio + "imagem/capa.png";
		carregarCapa();

	}
	
	
	
	public void carregarCapa(){
=======
	}
	
	
	public void carregaCapa(){
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		ImageIcon capa = new ImageIcon( imagem );
		lblCapa.setIcon(new ImageIcon(capa.getImage().getScaledInstance(lblCapa.getWidth(), 
				lblCapa.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	public void calcularMargem(){
<<<<<<< HEAD
		
		double vlr1 = 0.00;
		double vlr2 = 0.00;
		
		if ( !ftxtPrecoVenda.getText().isEmpty() ){
			vlr1 = Double.parseDouble( ftxtPrecoVenda.getText().trim() );
		}
		if ( !ftxtPrecoCusto.getText().isEmpty() ){
			vlr2 = Double.parseDouble( ftxtPrecoCusto.getText().trim() );
		}
				
		ftxtMargem.setText( ( Double.toString(  ( ( vlr1 - vlr2 ) * 100 / vlr1 ))).substring(0,2) + " %" );
	}
	
	public void limparCampos(){
		
		btnLimpar.setText("Limpar");
=======
		//(2615 - 2400) / 2400 * 100 = 8.958%
		//2615-2400=215 - multiplica-se o resultado por 100 e divide-se por 2400 que é igual a 8,95833%
		
 
		double calculo =  Double.parseDouble( ftxtPrecoVenda.getText().trim() ) 
				- Double.parseDouble( ftxtPrecoCusto.getText().trim() );
		calculo = (calculo * 100) / Double.parseDouble( ftxtPrecoVenda.getText().trim() );
		ftxtMargem.setText( ( Double.toString( calculo ) ).substring(0,2) + " %" );
	}
	
	public void limparCampos(){
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535

		for (Component p : painel.getComponents()) {
			if ( p instanceof JTextField ) {
				JTextField l = ( JTextField )p;
				l.setText(null);
			}
			if ( p instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )p;
				l.setValue(null);
			}
			if (p instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )p;
				l.setSelectedIndex(0);
			}
			if ( p instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )p;
				l.setText(null);
			}
		}
		
		imagem = diretorio + "imagem/capa.png";
<<<<<<< HEAD
		carregarCapa();
=======
		carregaCapa();
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		txtAutor.setText("");
		txtCategoria.setText("");
		btnSalvar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
	
	
	public void verificarCampos(){	
		
		for (Component p : painel.getComponents()) {
			if ( p instanceof JTextField ) {
				JTextField l = ( JTextField )p;
				if (l.getText().isEmpty()){
					validar = true;
				}
			}
			if ( p instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )p;
				if (l.getText().isEmpty()){
					validar = true;
				}
			}
			if (p instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )p;
<<<<<<< HEAD
				l.setSelectedIndex(0);
=======
				l.setSelectedIndex(0); //problemas para resolver - não pega todas as combos
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
			}
			if ( p instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )p;
				if (l.getText().isEmpty()){
					validar = true;
				}
			}
		}
		
		if ( validar != false ){
			msg("erroVazio", txtTitulo.getText());
			validar = false;
		}
	}
	
	
	public String obterData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String data = (dateFormat.format(date));
		return data;
	}
	
	
	public void carregarImagem() throws IOException{

		JFileChooser fc = new JFileChooser();    
		int option = fc.showOpenDialog(painel);    
		if (option == JFileChooser.APPROVE_OPTION) { 
			File file = fc.getSelectedFile();  
<<<<<<< HEAD
			imagem = file.getCanonicalPath();
			carregarCapa();
		} else {
=======
//			System.out.println("Arquivo selecionado: " + file.getCanonicalPath());
			imagem = file.getCanonicalPath();
			carregaCapa();
		} else {
//			System.out.println("Nenhum arquivo selecionado!");
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
			imagem = diretorio + "imagem/capa.png";
		} 
	}

	
	public void preencherCampos( String opt ){
		//Transfere e concatena os dados selecionados das
		//combobox Autor e Categoria
		String campo;
		
		switch ( opt ){

		case "autor":
			campo = txtAutor.getText();
			if ( txtAutor.getText().isEmpty() ){
				txtAutor.setText( cboAutor.getSelectedItem().toString() );
			} else {
				campo = campo + ", "+ cboAutor.getSelectedItem().toString();
				txtAutor.setText(campo);
			}
			break;
			
		case "categoria":
			campo = txtCategoria.getText();
			if ( txtCategoria.getText().isEmpty() ){
				txtCategoria.setText( cboCategoria.getSelectedItem().toString() );
			} else {
				campo = campo + ", "+ cboCategoria.getSelectedItem().toString();
				txtCategoria.setText(campo);
			}
			break;
		}
	}
	

	public void abrirJanela ( String nome ){


		switch ( nome ){

		case "principal":
			if( janelaPrincipal == null ){
				FrmPrincipal principal;
				principal = new FrmPrincipal();
				principal.setVisible(true);
				fechar();
			} else {
				janelaPrincipal.setVisible(true);
				fechar();
			}
			break;
		}
	}
	
	
	// PREENCHE COMBOBOX /////////////////////
	
	public void preencherCapa() {
		cboTipoCapa.addItem( "Tipos de Capas…" );
		cboTipoCapa.addItem( "Brochura" );
		cboTipoCapa.addItem( "Dura" );
	}
	
	
	public void preencherAutor() {

		String linha = new String();
		arquivos = new Arquivos();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Autor> listAutor = new ArrayList<>();
		try {
			arquivos.leArquivo(diretorio + "data/", "autor");
			linha = arquivos.getBuffer();
			String[] autor = linha.split(";");
			for ( String d : autor ) {
				String text = d.replaceAll(".*: ", "");
				listString.add( text );
				if (d.contains("---")) {
					Autor a = new Autor();
					a.setNome( listString.get(0) );
					listAutor.add( a );
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		//Ordenar alfabeticamente
		String[] nomes = new String[listAutor.size()];
		for ( int i = 0; i < listAutor.size(); i++ ){		
			String nome = listAutor.get(i).getNome();		
			nomes[i] = nome;	
		}
		Arrays.sort(nomes);
		//Adicionar na combobox
		cboAutor.addItem( "Autores…" );
		for ( int i = 0; i < listAutor.size(); i++ ){
			cboAutor.addItem( nomes[i] );
=======
		cboAutor.addItem( "Autores…" );
		for (Autor a : listAutor) {
			cboAutor.addItem( a.getNome() );
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		}
	}
	
	
	public void preencherEditora() {

		String linha = new String();
		arquivos = new Arquivos();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Editora> listEditora = new ArrayList<>();
		try {
			arquivos.leArquivo(diretorio + "data/", "editora");
			linha = arquivos.getBuffer();
			String[] editora = linha.split(";");
			for ( String d : editora ) {
				String text = d.replaceAll(".*: ", "");
				listString.add( text );
				if (d.contains("---")) {
					Editora a = new Editora();
					a.setNome( listString.get(0) );
					listEditora.add( a );
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		//Ordenar alfabeticamente
		String[] nomes = new String[listEditora.size()];
		for ( int i = 0; i < listEditora.size(); i++ ){		
			String nome = listEditora.get(i).getNome();		
			nomes[i] = nome;	
		}
		Arrays.sort(nomes);
		//Adicionar na combobox
		cboEditora.addItem( "Editoras…" );
		for ( int i = 0; i < listEditora.size(); i++ ){
			cboEditora.addItem( nomes[i] );
=======
		cboEditora.addItem( "Selecione…" );
		for (Editora a : listEditora) {
			cboEditora.addItem( a.getNome() );
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		}
	}
	
	public void preencherCategoria() {

		String linha = new String();
		arquivos = new Arquivos();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Categoria> listCategoria = new ArrayList<>();
		try {
			arquivos.leArquivo(diretorio + "data/", "categoria");
			linha = arquivos.getBuffer();
			String[] categoria = linha.split(";");
			for ( String d : categoria ) {
				String text = d.replaceAll(".*: ", "");
				listString.add( text );
				if (d.contains("---")) {
					Categoria c = new Categoria();
					c.setNome( listString.get(0) );
					listCategoria.add( c );
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		//Ordenar alfabeticamente
				String[] nomes = new String[listCategoria.size()];
				for ( int i = 0; i < listCategoria.size(); i++ ){		
					String nome = listCategoria.get(i).getNome();		
					nomes[i] = nome;	
				}
				Arrays.sort(nomes);
				//Adicionar na combobox
				cboCategoria.addItem( "Categorias…" );
				for ( int i = 0; i < listCategoria.size(); i++ ){
					cboCategoria.addItem( nomes[i] );
				}
=======
		cboCategoria.addItem( "Categorias…" );
		for (Categoria c : listCategoria) {
			cboCategoria.addItem( c.getNome() );
		}
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
	}
	
	
	// CRUD //////////////////////////
	
	public void pesquisar(){

		String pesquisa = "";
		txtIsbnID.setText("");
		ArrayList<Livro> lista = new ArrayList<>();

		if ( !txtPesquisar.getText().isEmpty() ) {
			for (int i = 0; i < livros.size(); i++) {
				if ( livros.get(i).getTitulo().toLowerCase().contains( txtPesquisar.getText().toLowerCase() ) ||
						livros.get(i).getIsbn().toLowerCase().contains( txtPesquisar.getText().toLowerCase() ) || 
						livros.get(i).getAutor().toLowerCase().contains( txtPesquisar.getText().toLowerCase() ) ||
						txtPesquisar.getText().equalsIgnoreCase( livros.get(i).getEditora() ) ||
						livros.get(i).getTitulo().toLowerCase().startsWith( txtPesquisar.getText().toLowerCase() ) ||
						livros.get(i).getAutor().toLowerCase().startsWith( txtPesquisar.getText().toLowerCase() ) ||
						livros.get(i).getEditora().toLowerCase().startsWith( txtPesquisar.getText().toLowerCase() )) {

					txtIsbnID.setText(livros.get(i).getIsbn());
					Livro livro = new Livro();
					livro.setIsbn(livros.get(i).getIsbn());
					livro.setTitulo(livros.get(i).getTitulo());
					lista.add(livro);
				} else {
					if ( livros.size() == i+1 && txtIsbnID.getText().isEmpty() ){
						msg("vazioPesquisa", txtPesquisar.getText());
						limparCampos();
					}
				}

			}
			String[] filtro = new String[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				filtro[i] = lista.get(i).getIsbn() + " : " + lista.get(i).getTitulo();
				pesquisa = lista.get(i).getIsbn();
			}
			if (filtro != null && filtro.length > 1) {
<<<<<<< HEAD
				Arrays.sort( filtro );
=======
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
				pesquisa = (String) JOptionPane.showInputDialog(janela, "Selecione:\n", "Livros Localizados",
						JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
			}
			if (pesquisa == "0" || pesquisa != null){
				for (int i = 0; i < livros.size(); i++) {
					if (pesquisa.replaceAll(" : .*", "").equalsIgnoreCase(livros.get(i).getIsbn())) {
						txtIsbnID.setText( livros.get(i).getIsbn() );
						txtTitulo.setText( livros.get(i).getTitulo() );
						txtAutor.setText( livros.get(i).getAutor() );
						txtCategoria.setText( livros.get(i).getCategoria() );
						ftxtPaginas.setText( Integer.toString(livros.get(i).getPaginas() ));
						ftxtIsbn.setText( livros.get(i).getIsbn() );
						ftxtDtPub.setText( livros.get(i).getDtPublicacao() );
						cboTipoCapa.getModel().setSelectedItem( livros.get(i).getCapa() );
						cboEditora.getModel().setSelectedItem( livros.get(i).getEditora() );
<<<<<<< HEAD
					  	txtaSumario.setText( livros.get(i).getSumario() );
						txtaResumo.setText( livros.get(i).getResumo() );
						ftxtPrecoCusto.setText( Float.toString( livros.get(i).getPrecoCusto() ));
						ftxtPrecoVenda.setText( Float.toString( livros.get(i).getPrecoVenda() ));
						imagem = livros.get(i).getImagem();
						carregarCapa();
						calcularMargem();
						btnLimpar.setText("Novo");
=======
						//							txtaSumario.setText( livros.get(i).getSumario() );
						//							txtaResumo.setText( livros.get(i).getResumo() );
						ftxtPrecoCusto.setText( Float.toString( livros.get(i).getPrecoCusto() ));
						ftxtPrecoVenda.setText( Float.toString( livros.get(i).getPrecoVenda() ));
						imagem = livros.get(i).getImagem();
						carregaCapa();
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
						btnEditar.setEnabled(true);
						btnExcluir.setEnabled(true);
						btnSalvar.setEnabled(false);
					}
				}
			}
		} else {
				msg("erroPesquisa", txtPesquisar.getText());
				limparCampos();
		} 
	} 


	public void editar() {

		Livro livro = new Livro();

		if ( !ftxtIsbn.getText().isEmpty() ) {

			for (int i = 0; i < livros.size(); i++) {
				if ( txtIsbnID.getText().equals(livros.get(i).getIsbn() )) {
					msg( "confirmaEditar", livros.get(i).getTitulo() );
<<<<<<< HEAD
//					verificarCampos();
				}
			}			
			if( validar == true ){
=======
				}
			}			
			if( validar == true ){
				verificarCampos();
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535

				for (int i = 0; i < livros.size(); i++) {
					if ( txtIsbnID.getText().equals(livros.get(i).getIsbn() )) {
						livro.setIsbn( ftxtIsbn.getText() );
						livro.setTitulo( txtTitulo.getText() );
						livro.setAutor( txtAutor.getText() );
						livro.setEditora( cboEditora.getSelectedItem().toString() );
						livro.setDtPublicacao( ftxtDtPub.getText() );
						livro.setDtCadastro( obterData() );
						livro.setCapa( cboTipoCapa.getSelectedItem().toString() );
						livro.setPaginas( Integer.parseInt(ftxtPaginas.getText() ));
						livro.setCategoria( txtCategoria.getText() );
<<<<<<< HEAD
						livro.setSumario( txtaSumario.getText() );
						livro.setResumo( txtaResumo.getText() );
=======
//							livro.setSumario( txtaSumario.getText().toString() );
//							livro.setResumo( txtaResumo.getText() );
//							livro.setImagem( btnImagem.getText() );
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
						livro.setPrecoCusto( Float.parseFloat( ftxtPrecoCusto.getText() ));
						livro.setPrecoVenda( Float.parseFloat( ftxtPrecoVenda.getText()));
						livro.setDtCadastro( obterData() );
						livro.setImagem( imagem );
						livros.set(i, livro);
<<<<<<< HEAD
						atualizarArquivo(livros);
					}
				}
			} 
			limparCampos();
			validar = false;
			msg( "editar", txtTitulo.getText() );
=======
						msg( "editar", txtTitulo.getText() );
						atualizarArquivo(livros);
						limparCampos();
						validar = false;
					}
				}
			} 
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
		}
	}
	
	
	public void salvar() {
		
		Livro livro = new Livro();

		if ( !ftxtIsbn.getText().isEmpty() ) {
			
			for (int i = 0; i < livros.size(); i++) {	
				if ( ftxtIsbn.getText().equals(livros.get(i).getIsbn() )) {
					msg( "erroEditar", livros.get(i).getTitulo() );
					validar = true;
				}
			}			
			if(!(validar == true)){
				verificarCampos();
				livro.setIsbn( ftxtIsbn.getText() );
				livro.setTitulo( txtTitulo.getText() );
				livro.setAutor( txtAutor.getText() );
				livro.setEditora( cboEditora.getSelectedItem().toString() );
				livro.setDtPublicacao( ftxtDtPub.getText() );
				livro.setDtCadastro( obterData() );
				livro.setCapa( cboTipoCapa.getSelectedItem().toString() );
				livro.setPaginas( Integer.parseInt(ftxtPaginas.getText() ));
				livro.setCategoria( txtCategoria.getText() );
<<<<<<< HEAD
				livro.setSumario( txtaSumario.getText() );
				livro.setResumo( txtaResumo.getText() );
=======
//				livro.setSumario( txtaSumario.getText().toString() );
//				livro.setResumo( txtaResumo.getText() );
//				livro.setImagem( btnImagem.getText() );
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
				livro.setPrecoCusto( Float.parseFloat( ftxtPrecoCusto.getText() ));
				livro.setPrecoVenda( Float.parseFloat( ftxtPrecoVenda.getText()));
				livro.setDtCadastro( obterData() );
				livro.setImagem( imagem );
				livros.add(livro);
				msg( "salvar", txtTitulo.getText() );
				atualizarArquivo(livros);
				limparCampos();
				validar = false;
			}
		} else {
			verificarCampos();
		}
	}
	

	public void excluir() {

		if ( !ftxtIsbn.getText().isEmpty() ) {

			for (int i = 0; i < livros.size(); i++) {
				if ( ftxtIsbn.getText().equals(livros.get(i).getIsbn() )) {
					msg( "confirmaExcluir", livros.get(i).getTitulo() );
					if(!(validar == true)){
					livros.remove(i);
					atualizarArquivo( livros );
					lerArquivo();
					msg("excluir", txtTitulo.getText());
					limparCampos();
					validar = false;
					}
				} else {
					if(i == livros.size()){
						msg("erroExcluir", livros.get(i).getTitulo());
					}
				}
			} 
		} else {
			pesquisar();
		}
	}
	

	public void lerArquivo() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			dao.leArquivo( diretorio + "data/", arquivo );
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
					livro.setSumario( lista.get(8) );
					livro.setResumo( lista.get(9) );
					livro.setPrecoCusto( Float.parseFloat(lista.get(10) ) );
					livro.setPrecoVenda( Float.parseFloat(lista.get(11) ) );
					livro.setDtCadastro( lista.get(12) );
					livro.setImagem( lista.get(13) );
					livros.add(livro);
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void atualizarArquivo(List<Livro> listaLivros) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
		File f = new File(diretorio + "data/" + arquivo);
		f.delete();
		for (Livro livros : listaLivros) {
			try {
				dao.escreveArquivo(diretorio  + "data/", arquivo, "", livros);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//  MENSAGENS  //////////////////////////////


	public void msg( String tipo, String mensagem ) {

		switch ( tipo ) {

		case "salvar":
			JOptionPane.showMessageDialog(null, 
					"O Livro " + mensagem + " foi salvo com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
			
		case "editar":
			JOptionPane.showMessageDialog(null, 
					"O Livro " + mensagem + " foi editado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "excluir":
			JOptionPane.showMessageDialog(null, 
					"O Livro " + mensagem + " foi excluído com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "confirmaEditar":
			Object[] editar = { "Confirmar", "Cancelar" };  
			int ed = JOptionPane.showOptionDialog(null, "Você confirma a edição do Livro " + mensagem + " ?",
					"Edição de Livro", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), editar, editar[1]);
			if (ed == 1) { validar = false; } else { validar = true; }
			break;
			
		case "confirmaExcluir":
			Object[] excluir = { "Confirmar", "Cancelar" };  
			int ex = JOptionPane.showOptionDialog(null, "Você confirma a exclusão do Livro " + mensagem + " ?",
					"Exclusão de Livro", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), excluir, excluir[1]);
			if (ex == 1) { validar = true; } else { validar = false; }
			break;
			
		case "vazioPesquisa":
			JOptionPane.showMessageDialog(null, 
<<<<<<< HEAD
					"ATENÇÃO!\n\nNenhum resultador encontrado com: " + mensagem, 
=======
					"ATENÇÃO!\n\nNão localizamos o Livro " + mensagem + ". Verifique sua digitação!", 
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
					"Não Localizado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroPesquisa":
			JOptionPane.showMessageDialog(null, 
<<<<<<< HEAD
					"ATENÇÃO! Por favor, digite algo para pesquisar!", 
=======
					"ATENÇÃO! Por favor, digite para pesquisar!", 
>>>>>>> fefdd4755b80c9939d18a5f6859cf81bd1270535
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroEditar":
			JOptionPane.showMessageDialog(null, 
					"O Livro " + mensagem + " já existe!",
					"Já Cadastrado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
		
		case "erroExcluir":
			JOptionPane.showMessageDialog(null, 
					"O Livro " + mensagem + " não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;

		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\nPor favor, digite o solicitado pelo campo.", 
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
	}
	
	
	//  COMPORTAMENTO JANELA  //////////////////////////

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
		
		


	//   CONTROLE BOTAO   //////////////////////////////
		
		
		public ActionListener editar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				editar();
			}
		};
		
		public ActionListener salvar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				salvar();
			}
		};
		
		public ActionListener excluir = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				excluir();
			}
		};
		
		public ActionListener pesquisar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				pesquisar();
			}
		};
		
		public ActionListener carrgarImagem = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					carregarImagem();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		
		public ActionListener limpar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		};
		
		public ActionListener preencher = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//verifica qual controle está solicitando a ação
				Object source = e.getSource();

				if(source == cboAutor){
					preencherCampos( "autor" );
				}
				if(source == cboCategoria){
					preencherCampos( "categoria" );
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
							
				calcularMargem();
			}
		};


		//  CONTROLE MOUSE  ///////////////////////////////

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
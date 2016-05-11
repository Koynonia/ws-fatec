/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 03/05/2016
 */

package controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import dao.ArquivoCarrinho;
import dao.ArquivoSessao;
import entity.Carrinho;
import entity.Sessao;
import entity.Administrador;

public class SessaoController {

	// Variável estática que conterá a instancia da classe
	private static SessaoController instance;
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "log";
	private List<Sessao> logon;
	private List<Carrinho> itens;
	private ArquivoSessao dao = new ArquivoSessao();

	// Construtor privado (suprime o construtor público padrão).
	private SessaoController() {

		new ArrayList<Administrador>();
		this.setLogon(new ArrayList<Sessao>());
		this.itens = new ArrayList<Carrinho>();

		carregar();
	}
	
	
	// METODOS DE SUPORTE ///////////////
	
	public static SessaoController getInstance() {
		// Método público estático de acesso único ao objeto
		if (instance == null)
			instance = new SessaoController();
		return instance;
	}
	
	public List<Sessao> getLogon() {
		return logon;
	}

	public void setLogon(List<Sessao> logon) {
		this.logon = logon;
	}
	
	
	public void logoff(){

		if ( temporizador() != false ){
			sair();
		}
	}
	
	
	public String obterData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String data = (dateFormat.format(date));
		return data;
	}
	
	
	public String mascaraCampo(String pMask, String pValue,
			boolean pReturnValueEmpty){
		
		/* 
		 * Verifica se foi configurado para nao retornar a  
		 * mascara se a string for nula ou vazia se nao 
		 * retorna somente a mascara. 
		 */ 
		if (pReturnValueEmpty == true
				&& (pValue == null || pValue.trim().equals("")))
			return "";
		
		if ( pValue.contains("-") || 
				( pValue.contains(".") ) || 
				( pValue.contains("(") ) || 
				( pValue.contains(")") ))
			return pValue;
			
		/* 
		 * Substituir as mascaras passadas como  9, X, * por # para efetuar a formatcao
		 */
//		pMask = pMask.replaceAll("*", "#");
		pMask = pMask.replaceAll("9", "#");
//		pMask = pMask.toUpperCase().replaceAll("X", "#");
		/* 
		 * Formata valor com a mascara passada  
		 */
		for(int i = 0; i < pValue.length(); i++){
			pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
		}
		/* 
		 * Subistitui por string vazia os digitos restantes da mascara 
		 * quando o valor passado é menor que a mascara   
		 */
		return pMask.replaceAll("#", "");
	}
	
	
	public Boolean temporizador(){

		boolean dt = false;
		
		if ( logon.size() > 0 ){
			int tempo = 5; //variavel que controla os minutos da sessão
			String dtAtual = obterData();
			String dtLogin = logon.get(0).getData();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			//Converte para Date
			Date dateA = null;
			Date dateB = null;
			try {
				dateA = df.parse(dtAtual);
				dateB = df.parse(dtLogin);
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
				dt = true;
			}	
		}
		return dt;
	}
		
	
	
	// CRUD //////////////////////////
	
	
	public String registrar(String id, String usuario, String acesso, String janela) {

		Sessao log = new Sessao();

		log.setId( id );
		log.setUsuario( usuario );
		log.setNivel( acesso );
		log.setTela( janela );
		log.setData( obterData() );
		getLogon().clear(); //Apaga a sessao atual
		getLogon().add( log );
		atualizar( getLogon() ); //Atualiza a sessao
		return getLogon().get(0).getNivel();	
	}
	
	
	public String rastrear( String janela ){
		
		Sessao log = new Sessao();
		
		if ( !getLogon().isEmpty() ){
			log.setId( logon.get(0).getId() );
			log.setUsuario( logon.get(0).getUsuario() );
			log.setNivel( logon.get(0).getNivel() );
			log.setTela( janela );
			log.setData( obterData() );
			getLogon().clear();
			getLogon().add(0, log);
			atualizar ( getLogon() );
			
			return getLogon().get(0).getNivel();
		} else {
			return "";
		}
	}

	
	public void sair(){
		getLogon().clear();
		File f = new File( diretorio + "dados/", arquivo );
		f.delete();
		
		carrinhoLimpar();
	}
	

	public void carregar() {

		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		try {
			dao.lerArquivo( diretorio + "dados/", arquivo );
			linha = dao.getBuffer();
			String[] listaSession = linha.split(";");
			for (String s : listaSession) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					Sessao log = new Sessao();
					log.setId(list.get(0));
					log.setUsuario(list.get(1));
					log.setNivel(list.get(2));
					log.setTela(list.get(3));
					log.setData(list.get(4));
					getLogon().add(log);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void atualizar(List<Sessao> lista) {

		File f = new File( diretorio + "dados/", arquivo );
		f.delete();
		for (Sessao logon : lista) {
			try {
				dao.escreverArquivo(diretorio + "dados/", arquivo, "", logon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void encerrar(){
		
		carrinhoLimpar();
		sair();
	}
	
	
	public void carrinho() {
		
		//Carrega a lista do Carrinho
		ArquivoCarrinho dao = new ArquivoCarrinho();
		
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			dao.lerArquivo(diretorio + "dados/", "carrinho");
			linha = dao.getBuffer();
			String[] listaItens = linha.split(";");
			for (String s : listaItens) {
				String text = s.replaceAll(".*: ", "");
				lista.add(text);
				if (s.contains("---")) {
					Carrinho item = new Carrinho();
					item.setQuantidade( Integer.parseInt( lista.get(1) ) );
					item.setDtCadastro(lista.get(3));
					itens.add(item);
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	public Integer carrinhoQtd() {
		
		itens.clear(); //limpa a lista do Carrinho
		carrinho(); //recarrega a lista do Carrinho atualizado
		carrinhoTempo(); //verifica se o Carrinho já ultrapassou o tempo
		int qtd = 0;
		
		for ( int i = 0; i < itens.size(); i++ ){
			qtd =  qtd + itens.get(i).getQuantidade();
			
		}
		return qtd;
	}
	
	
	public void carrinhoLimpar(){

		File f = new File(diretorio + "dados/" + "carrinho");
		f.delete();
	}

	
	public void carrinhoTempo() {


		if ( itens.size() > 0 ){
			if ( logon.size() == 0 ){
				int tempo = 2; //variavel que controla os minutos da sessão - colocar no Painel Administrador
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
					carrinhoLimpar();
				}	
			}
		}
	}
	
	
	//Classe que reduz as capas dos livros para serem usadas em mensagens
	public void reduzImagem (String orig, String thumb, int maxDim) { 
		try { 

			Image inImage = new ImageIcon(orig).getImage(); 
			double scale = (double)maxDim/(double)inImage.getWidth(null); 

			int scaledW = (int)(scale*inImage.getWidth(null)); 
			int scaledH = (int)(scale*inImage.getHeight(null)); 
			BufferedImage outImage = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB); 
			AffineTransform tx = new AffineTransform(); 

			if (scale < 1.0d) { 
				tx.scale(scale, scale); 
			} 

			Graphics2D g2d = outImage.createGraphics(); 
			g2d.drawImage(inImage, tx, null); 
			g2d.dispose();
			
			// Diretório de saída
			OutputStream os = new FileOutputStream( diretorio + "/miniaturas/" + thumb );
			
			//formato da imagem de saída
			ImageWriter encoder = (ImageWriter)ImageIO.getImageWritersByFormatName( "jpg" ).next();
			ImageOutputStream ios = ImageIO.createImageOutputStream( os );
			encoder.setOutput( ios );
			IIOMetadata imageMetaData = encoder.getDefaultImageMetadata(new ImageTypeSpecifier( outImage ), null);
			encoder.write(imageMetaData, new IIOImage( outImage, null, null ), null);
			os.close(); 
		} catch (IOException e) { 
			System.out.println("Erro: "+e.getMessage()); 
			e.printStackTrace(); 
		} 
	} 
	
	
	
	// MENSAGENS //////////////////////////////

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errorsession":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nPor favor, solicite a autorização de um administrador.", 
					"Bloqueado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png"));
			break;

		default:
			JOptionPane.showMessageDialog(null, 
					"ERRO! Algo não deveria ter acontecido…\n\nSessaoCtrl - Termo: " + mensagem
					+ "\n\nOcorreu no Controller desta Tela.", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/error.png"));
		}
	}
}

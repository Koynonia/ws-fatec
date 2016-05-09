/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 07/05/2016
 */

package controller;

import java.awt.Component;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import boundary.FrmPrincipal;
import dao.ArquivoEndereco;
import dao.Arquivos;
import entity.Endereco;


public class EnderecoController implements ComponentListener{

	private JFrame janela;
	private FrmPrincipal janelaPrincipal;
	private JPanel painel;
	private JLabel lblId; 
	private JTextField txtTipoEndereco;  
	private JTextField txtEndereco; 
	private JTextField txtComplemento; 
	private JTextField txtCidade; 
	private JTextField txtBairro;	
	private JTextField ftxtCep;
	private JComboBox<String> cboTipoEndereco; 
	private JComboBox<String> cboEstado; 
	private JButton btnLimpar; 
	private JButton btnEditar; 
	private JButton btnExcluir; 
	private JButton btnSalvar; 
	private JButton btnCancelar;
	private JButton btnVoltar;
	private SessaoController logon = SessaoController.getInstance();
	private ArquivoEndereco dao = new ArquivoEndereco();
	private Arquivos arquivos = new Arquivos();
	private List<Endereco> enderecos;
	private boolean validar;
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "endereco";
	

	public EnderecoController( 
			JFrame janela, 
			JPanel painel, 
			JLabel lblId, 
			JTextField txtTipoEndereco,
			JTextField txtEndereco, 
			JTextField txtComplemento, 
			JTextField txtBairro, 
			JTextField txtCidade, 
			JTextField ftxtCep,  
			JComboBox<String> cboTipoEndereco, 
			JComboBox<String> cboEstado, 
			JButton btnLimpar, 
			JButton btnEditar, 
			JButton btnExcluir, 
			JButton btnSalvar, 
			JButton btnCancelar, 
			JButton btnVoltar
			
			){
		
		this.janela = janela;
		this.painel = painel;
		this.lblId = lblId;
		this.txtTipoEndereco = txtTipoEndereco;
		this.txtEndereco = txtEndereco;
		this.txtComplemento = txtComplemento;
		this.txtBairro = txtBairro;
		this.txtCidade = txtCidade;
		this.ftxtCep = ftxtCep;
		this.cboTipoEndereco = cboTipoEndereco;
		this.cboEstado = cboEstado;
		this.btnLimpar = btnLimpar;
		this.btnEditar = btnEditar;
		this.btnExcluir = btnExcluir;
		this.btnSalvar = btnSalvar;
		this.btnCancelar = btnCancelar; 
		this.btnVoltar = btnVoltar;
		this.enderecos = new ArrayList<Endereco>();
		
		dados();
		tela();
	}
	
	
	//   TELA   ////////////////////////
	
	public void dados(){
		
		logon.rastrear( janela.getName() );
		lerArquivo();
	}
	
	
	public void tela(){
		preencherEndereco();
		preencherEstado();
		txtTipoEndereco.setVisible(false);
	}
	
	public void permissao(){
		
		if ( logon.rastrear( janela.getName()) != "administrador" ){
			
			msg("erroAcesso", "Cadastro de Endereços");
			fechar();
		}
	}
	
	
	public void alterarBotao(){
		btnLimpar.setText("Novo");
		btnCancelar.setVisible(false);
		btnEditar.setText("Editar");
		btnEditar.setVisible(true);
		btnExcluir.setVisible(false);
		btnSalvar.setVisible(false);
		alterarCampos( "desprotegerCampos" );
	}
	
	
	public void alterarCampos ( String opt ){
		
		switch ( opt ){

		case "protegerCampos":
			
			btnLimpar.setText("Novo");
			btnCancelar.setVisible(false);
			btnEditar.setText("Editar");
			btnEditar.setVisible(true);
			btnExcluir.setVisible(false);
			btnSalvar.setVisible(false);
			txtEndereco.setEditable(false);
			txtEndereco.setEnabled(false);
			txtComplemento.setEditable(false);
			txtComplemento.setEnabled(false);
			txtBairro.setEditable(false);
			txtBairro.setEnabled(false);
			txtCidade.setEnabled(false);
			txtTipoEndereco.setVisible(false);
			cboTipoEndereco.setVisible(true);
			cboEstado.setEnabled(false);
			break;

		case "desprotegerCampos":
			
			btnLimpar.setText("Limpar");
			btnCancelar.setVisible(true);
			btnEditar.setText("Salvar");
			btnEditar.setVisible(true);
			btnExcluir.setVisible(true);
			btnSalvar.setVisible(false);
			txtEndereco.setEditable(true);
			txtEndereco.setEnabled(true);
			txtComplemento.setEditable(true);
			txtComplemento.setEnabled(true);
			txtBairro.setEditable(true);
			txtBairro.setEnabled(true);
			txtCidade.setEnabled(true);
			txtTipoEndereco.setVisible(true);
			cboTipoEndereco.setVisible(false);
			cboEstado.setEnabled(true);
			break;		
		}
	}
	
	
	public void limparCampos(){
		
		btnLimpar.setText("Limpar");

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
		
		btnCancelar.setVisible(true);
		btnSalvar.setVisible(true);
		btnEditar.setVisible(false);
		btnExcluir.setVisible(false);
	}
	
	
	public void verificarCampos(){	
		
		for (Component p : painel.getComponents()) {
			if ( p instanceof JTextField ) {
				JTextField l = ( JTextField )p;
				if ( l.getText().isEmpty() ){
					validar = true;
				}
			}
			if ( p instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )p;
				if ( l.getText().isEmpty() ){
					validar = true;
				}
			}
			if (p instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )p;
				if ( l.getSelectedIndex() > 0){
					validar = true;
				}
			}
			if ( p instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )p;
				if ( l.getText().isEmpty() ){
					validar = true;
				}
			}
		}
		if ( validar != false ){
			msg("erroVazio", txtEndereco.getText());
			validar = false;
		}
	}
	
	
	public String obterData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String data = (dateFormat.format(date));
		return data;
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
	
	public void preencherEndereco() {

		ArrayList<Endereco> listaEndereco = new ArrayList<>();
		
		listaEndereco.addAll( enderecos );
		//Ordenar alfabeticamente
		String[] enderecos = new String[listaEndereco.size()];
		for ( int i = 0; i < listaEndereco.size(); i++ ){		
			String endereco = listaEndereco.get(i).getTipoEndereco();		
			enderecos[i] = endereco;	
		}
		Arrays.sort(enderecos);
		//Adicionar na combobox
		cboTipoEndereco.addItem( "Selecione…" );
		for ( int i = 0; i < listaEndereco.size(); i++ ){
			cboTipoEndereco.addItem( enderecos[i] );
		}
		cboTipoEndereco.addItem( "Adicionar NOVO" );
	}
	
	
	public void preencherEstado() {

		String linha = new String();
		arquivos = new Arquivos();
		ArrayList<String> listaString = new ArrayList<>();
		ArrayList<String> listaEstado = new ArrayList<>();
		try {
			arquivos.lerArquivo(diretorio + "dados/", "estado");
			linha = arquivos.getBuffer();
			String[] estado = linha.split(";");
			for ( String e : estado ) {
				String text = e;
				listaString.add( text );
				for ( int i = 0; i < listaString.size(); i++ ){
					listaEstado.add( listaString.get(i) );
				}
				listaString.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Ordenar alfabeticamente
		String[] estados = new String[listaEstado.size()];
		for ( int i = 0; i < listaEstado.size(); i++ ){		
			String estado = listaEstado.get(i);		
			estados[i] = estado;	
		}
		Arrays.sort(estados);
		//Adicionar na combobox
		cboEstado.addItem( "Selecione…" );
		for ( int i = 0; i < listaEstado.size(); i++ ){
			cboEstado.addItem( estados[i] );
		}
	}
	
	
	// CRUD //////////////////////////


	public void editar() {

		Endereco endereco = new Endereco();

		if ( !txtEndereco.getText().isEmpty() ) {

			for (int i = 0; i < enderecos.size(); i++) {
				if ( lblId.getText().equals(enderecos.get(i).getCpf() )) {
					msg( "confirmaEditar", enderecos.get(i).getTipoEndereco() );
//					verificarCampos();
				}
			}			
			if( validar == true ){

				for (int i = 0; i < enderecos.size(); i++) {
					if ( lblId.getText().equals(enderecos.get(i).getCpf() )) {
						
						endereco.setCpf( lblId.getText() );
						endereco.setTipoEndereco( txtTipoEndereco.getText() );
						endereco.setEndereco( txtEndereco.getText() );
						endereco.setComplemento( txtComplemento.getText() );
						endereco.setBairro( txtBairro.getText() );
						endereco.setCidade( txtCidade.getText() );
						endereco.setEstado( cboEstado.getSelectedItem().toString() );
						endereco.setCep( ftxtCep.getText() );
						endereco.setDtCadastro( enderecos.get(i).getDtCadastro() );
						endereco.setDtAlterado( obterData() );
						enderecos.set( i, endereco );
						atualizarArquivo( enderecos );
					}
				}
			} 
			validar = false;
			msg( "editar", txtTipoEndereco.getText() );
		}
	}
	
	
	public void salvar() {
		
		Endereco endereco = new Endereco();

		if ( !txtEndereco.getText().isEmpty() ) {
			
			for ( int i = 0; i < enderecos.size(); i++ ) {	
				if ( lblId.getText().equals(enderecos.get(i).getCpf() )) {
					msg( "erroEditar", enderecos.get(i).getTipoEndereco() );
//					verificarCampos();
				}
			}			
			if(!(validar == true)){
				
				endereco.setCpf( lblId.getText() );
				endereco.setTipoEndereco( txtTipoEndereco.getText() );
				endereco.setEndereco( txtEndereco.getText() );
				endereco.setComplemento( txtComplemento.getText() );
				endereco.setBairro( txtBairro.getText() );
				endereco.setCidade( txtCidade.getText() );
				endereco.setEstado( cboEstado.getSelectedItem().toString() );
				endereco.setCep( ftxtCep.getText() );
				endereco.setDtCadastro( obterData() );
				endereco.setDtAlterado( obterData() );
				enderecos.add( endereco );
				atualizarArquivo( enderecos );
				
				msg( "salvar", txtTipoEndereco.getText() );
				limparCampos();
				validar = false;
			}
		} else {
			msg("erroVazio","");
		}
	}
	

	public void excluir() {

		if ( !lblId.getText().isEmpty() ) {

			for (int i = 0; i < enderecos.size(); i++) {
				if ( lblId.getText().equals(enderecos.get(i).getCpf() )) {
					msg( "confirmaExcluir", enderecos.get(i).getTipoEndereco() );
					if(!(validar == true)){
					enderecos.remove(i);
					enderecos.remove(i);
					atualizarArquivo( enderecos );
					msg("excluir", enderecos.get(i).getTipoEndereco() );
					limparCampos();
					validar = false;
					}
				} else {
					if(i == enderecos.size()){
						msg("erroExcluir", enderecos.get(i).getTipoEndereco() );
					}
				}
			} 
		} 
	}
	
		
	public void lerArquivo() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			dao.lerArquivo( diretorio + "dados/", arquivo );
			linha = dao.getBuffer();
			String[] listaItens = linha.split(";");
			for (String s : listaItens) {
				String text = s.replaceAll(".*: ", "");
				lista.add(text);
				if (s.contains("---")) {
					Endereco endereco = new Endereco();
					endereco.setCpf( lista.get(0) );
					endereco.setTipoEndereco( lista.get(1) );
					endereco.setEndereco( lista.get(2) );
					endereco.setComplemento( lista.get(3) );
					endereco.setBairro( lista.get(4) );
					endereco.setCidade( lista.get(5) );
					endereco.setEstado( lista.get(6) );
					endereco.setCep( lista.get(7) );
					endereco.setDtCadastro( lista.get(8) );
					endereco.setDtAlterado( lista.get(9) );
					enderecos.add(endereco);
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void atualizarArquivo( List<Endereco> listaEndereco ) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
		File f = new File( diretorio + "dados/" + arquivo );
		f.delete();
		for (Endereco endereco : listaEndereco) {
			try {
				dao.escreverArquivo( diretorio  + "dados/", arquivo, "", endereco );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//  MENSAGENS  //////////////////////////////


	public void msg( String tipo, String mensagem ) {
		
		janela.setAlwaysOnTop ( false );
		
		switch ( tipo ) {

		case "salvar":
			JOptionPane.showMessageDialog(null, 
					"O Endereço " + mensagem + " foi salvo com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
			
		case "editar":
			JOptionPane.showMessageDialog(null, 
					"O Endereço " + mensagem + " foi editado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "excluir":
			JOptionPane.showMessageDialog(null, 
					"O Endereço " + mensagem + " foi excluído com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "confirmaEditar":
			Object[] editar = { "Confirmar", "Cancelar" };  
			int ed = JOptionPane.showOptionDialog(null, 
					"Você confirma a edição do Endereço " + mensagem + " ?",
					"Edição de Endereço", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), editar, editar[1]);
			if (ed == 1) { validar = false; } else { validar = true; }
			break;
			
		case "confirmaExcluir":
			Object[] excluir = { "Confirmar", "Cancelar" };  
			int ex = JOptionPane.showOptionDialog(null, 
					"Você confirma a exclusão do Endereço " + mensagem + " ?",
					"Exclusão de Endereço", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), excluir, excluir[1]);
			if (ex == 1) { validar = true; } else { validar = false; }
			break;
			
		case "vazioPesquisa":
			Object[] pesquisar = { "Confirmar", "Cancelar" };  
			int pq = JOptionPane.showOptionDialog(null, 
					"ATENÇÃO!\n\nNenhum resultador encontrado com: " + mensagem
					+ "\n Gostaria de adicionar este Endereço?", 
					"Endereço não Localizado", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), pesquisar, pesquisar[1]);
			if (pq == 0) { validar = true; } else { validar = false; }
			break;
			
		case "erroPesquisa":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite algo para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroEditar":
			JOptionPane.showMessageDialog(null, 
					"O Endereço " + mensagem + " já existe!",
					"Já Cadastrado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
		
		case "erroExcluir":
			JOptionPane.showMessageDialog(null, 
					"O Endereço " + mensagem + " não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;

		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nCampo não preenchido.\nPor favor, digite o solicitado pelo campo.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
		
		case "erroAcesso":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nSomente usuário com nível administrativo pode acessar o " + mensagem + "!", 
					"Bloqueado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/warning.png"));
			break;

		case "sistema":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, 
					"ATENÇÃO!\n\nChamada para o " + mensagem 
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
		
		
		public ActionListener registros = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
		
		public ActionListener cancelar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelar.setVisible(false);
				alterarCampos ("protegerCampos");
			}
		};
		
		public ActionListener editar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!btnExcluir.isVisible()){
					alterarCampos ("desprotegerCampos");
				} else {
					editar();
					alterarCampos ("protegerCampos");
				}
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
		
		public ActionListener limpar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				alterarCampos ("desprotegerCampos");
				btnEditar.setVisible(false);
				btnSalvar.setVisible(true);
			}
		};
		
		
		public ActionListener janelas = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//verifica qual botãoo está solicitando a ação
				Object source = e.getSource();

				if(source == btnVoltar){
					fechar();
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
						if( validar == true ){
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
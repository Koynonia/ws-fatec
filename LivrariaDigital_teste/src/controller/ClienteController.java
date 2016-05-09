/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 07/05/2016
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
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import boundary.FrmEndereco;
import boundary.FrmPrincipal;
import dao.ArquivoCliente;
import dao.ArquivoEndereco;
import dao.Arquivos;
import entity.Cliente;
import entity.Endereco;


public class ClienteController implements ComponentListener{

	private JFrame janela;
	private FrmPrincipal janelaPrincipal;
	private FrmEndereco janelaEndereco;
	private JPanel painel;
	private JLabel lblId; 
	private JLabel lblPesquisar; 
	private JLabel lblTipoEndereco; 
	private JLabel lblImagem; 
	private JLabel lblSenha; 
	private JLabel lblSenha2;  
	private JTextField txtPesquisar;
	private JTextField txtUsuario;
	private JTextField txtNome;
	private JTextField txtCidade;
	private JTextField txtEndereco;
	private JTextField txtComplemento;
	private JTextField txtBairro;	
	private JTextField ftxtEmail;
	private JTextField ftxtCpf;
	private JTextField ftxtTelefone;
	private JTextField ftxtCep;
	private JTextField ftxtDtNasc; 
	private JPasswordField pwdSenha;
	private JPasswordField pwdSenha2; 
	private JComboBox<String> cboTipoEndereco; 
	private JComboBox<String> cboEstado;
	private JButton btnPesquisar; 
	private JButton btnImagem; 
	private JButton btnPedidos;
	private JButton btnLimpar; 
	private JButton btnEditar; 
	private JButton btnExcluir; 
	private JButton btnSalvar; 
	private JButton btnCancelar;
	private JButton btnVoltar;
	private SessaoController logon = SessaoController.getInstance();
	private ArquivoCliente dao = new ArquivoCliente();
	private ArquivoEndereco daoEndereco = new ArquivoEndereco();
	private Arquivos arquivos = new Arquivos();
	private List<Cliente> clientes;
	private List<Endereco> enderecos;
	private boolean validar;
	private String imagem;
	private String cpf;
	private String diretorio = "../LivrariaDigital_teste/";
	private String arquivo = "cliente";
	

	public ClienteController( 
			JFrame janela, 
			JPanel painel, 
			String cpf, 
			JLabel lblId, 
			JLabel lblPesquisar, 
			JLabel lblTipoEndereco, 
			JLabel lblImagem, 
			JLabel lblSenha, 
			JLabel lblSenha2, 
			JTextField txtPesquisar, 
			JTextField txtUsuario,  
			JTextField txtNome, 
			JTextField txtEndereco, 
			JTextField txtComplemento, 
			JTextField txtBairro, 
			JTextField txtCidade, 
			JTextField ftxtEmail, 
			JTextField ftxtCpf, 
			JTextField ftxtTelefone, 
			JTextField ftxtCep, 
			JFormattedTextField ftxtDtNasc, 
			JPasswordField pwdSenha, 
			JPasswordField pwdSenha2, 
			JComboBox<String> cboEndereco, 
			JComboBox<String> cboEstado, 
			JButton btnPesquisar, 
			JButton btnImagem, 
			JButton btnPedidos, 
			JButton btnLimpar, 
			JButton btnEditar, 
			JButton btnExcluir, 
			JButton btnSalvar, 
			JButton btnCancelar, 
			JButton btnVoltar
			
			){
		
		this.janela = janela;
		this.painel = painel;
		this.cpf = cpf;
		this.lblId = lblId;
		this.lblPesquisar = lblPesquisar;
		this.lblTipoEndereco = lblTipoEndereco;
		this.lblImagem = lblImagem;
		this.lblSenha = lblSenha;
		this.lblSenha2 = lblSenha2;
		this.txtPesquisar = txtPesquisar;
		this.txtUsuario = txtUsuario;
		this.txtNome = txtNome;
		this.txtEndereco = txtEndereco;
		this.txtComplemento = txtComplemento;
		this.txtBairro = txtBairro;
		this.txtCidade = txtCidade;
		this.ftxtEmail = ftxtEmail;
		this.ftxtCpf = ftxtCpf;
		this.ftxtTelefone = ftxtTelefone;
		this.ftxtCep = ftxtCep;
		this.ftxtDtNasc = ftxtDtNasc;
		this.pwdSenha = pwdSenha;
		this.pwdSenha2 = pwdSenha2;
		this.cboTipoEndereco = cboEndereco;
		this.cboEstado = cboEstado;
		this.btnPesquisar = btnPesquisar;
		this.btnImagem = btnImagem;
		this.btnPedidos = btnPedidos;
		this.btnLimpar = btnLimpar;
		this.btnEditar = btnEditar;
		this.btnExcluir = btnExcluir;
		this.btnSalvar = btnSalvar;
		this.btnCancelar = btnCancelar; 
		this.btnVoltar = btnVoltar;
		this.clientes = new ArrayList<Cliente>();
		this.enderecos = new ArrayList<Endereco>();
		
		dados();
		tela();
	}
	
	
	//   TELA   ////////////////////////
	
	public void dados(){
		
		logon.rastrear( janela.getName() );
		lerArquivo();
		lerEndereco();
		
		if ( cpf != null && !cpf.contains( "USR" ) ) {
			lblId.setText( cpf );
			selecionar();
		} else {
			cpf = clientes.get(0).getCpf();
			selecionar();
		}
	}
	
	
	public void tela(){
		preencherEndereco();
		preencherEstado();
		if ( logon.getLogon().isEmpty() ){
		imagem = diretorio + "avatares/usuario.jpg";
		carregarAvatar();
		}
		
		if ( logon.getLogon().size() == 0 ){
			
			alterarCampos( "novo" );
		}
		
		if ( logon.getLogon().size() > 0 && 
				logon.getLogon().get(0).getNivel().equalsIgnoreCase("Administrador") ){
			lblPesquisar.setVisible(true);
			txtPesquisar.setVisible(true);
			btnPesquisar.setVisible(true);
		} else {
			lblPesquisar.setVisible(false);
			txtPesquisar.setVisible(false);
			btnPesquisar.setVisible(false);
		}
		
		cboTipoEndereco.getModel().setSelectedItem("Residência");
	}
	
	public void permissao(){
		
		if ( logon.rastrear( janela.getName()) != "administrador" ){
			
			msg("erroAcesso", "Cadastro de Clientes");
			fechar();
		}
	}
	
	public void carregarAvatar(){
		ImageIcon avatar = new ImageIcon( imagem );
		lblImagem.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(lblImagem.getWidth(), 
				lblImagem.getHeight(), Image.SCALE_DEFAULT)));
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

		case "novo":
			btnEditar.setVisible(false);
			btnExcluir.setVisible(false);
			btnCancelar.setVisible(false);
			lblTipoEndereco.setVisible(false);
			cboTipoEndereco.setVisible(false);
			break;
			
		case "protegerCampos":
			
			lblSenha.setVisible(false);
			lblSenha2.setVisible(false);
			txtUsuario.setEditable(false);
			txtUsuario.setEnabled(false);
			txtNome.setEditable(false);
			txtNome.setEnabled(false);
			txtEndereco.setEditable(false);
			txtEndereco.setEnabled(false);
			txtComplemento.setEditable(false);
			txtComplemento.setEnabled(false);
			txtBairro.setEditable(false);
			txtBairro.setEnabled(false);
			txtCidade.setEnabled(false);
			ftxtEmail.setEditable(false);
			ftxtEmail.setEnabled(false);
			ftxtDtNasc.setEditable(false);
			ftxtDtNasc.setEnabled(false);
			ftxtCpf.setEditable(false);
			ftxtCpf.setEnabled(false);
			ftxtTelefone.setEditable(false);
			ftxtTelefone.setEnabled(false);
			ftxtCep.setEnabled(false);
			pwdSenha.setVisible(false);
			pwdSenha2.setVisible(false);
//			cboTipoEndereco.setEnabled(false);
			cboEstado.setEnabled(false);
			btnImagem.setVisible(false);
			btnLimpar.setText("Novo");
			btnCancelar.setVisible(false);
			btnEditar.setText("Editar");
			btnEditar.setVisible(true);
			btnExcluir.setVisible(false);
			btnSalvar.setVisible(false);
			break;

		case "desprotegerCampos":
			
			lblSenha.setVisible(true);
			lblSenha2.setVisible(true);
			txtUsuario.setEditable(true);
			txtUsuario.setEnabled(true);
			txtNome.setEditable(true);
			txtNome.setEnabled(true);
			txtEndereco.setEditable(true);
			txtEndereco.setEnabled(true);
			txtComplemento.setEditable(true);
			txtComplemento.setEnabled(true);
			txtBairro.setEditable(true);
			txtBairro.setEnabled(true);
			txtCidade.setEnabled(true);
			ftxtEmail.setEditable(true);
			ftxtEmail.setEnabled(true);
			ftxtDtNasc.setEditable(true);
			ftxtDtNasc.setEnabled(true);
			ftxtCpf.setEditable(true);
			ftxtCpf.setEnabled(true);
			ftxtTelefone.setEditable(true);
			ftxtTelefone.setEnabled(true);
			ftxtCep.setEnabled(true);
			pwdSenha.setVisible(true);
			pwdSenha2.setVisible(true);
//			cboTipoEndereco.setVisible(true);
			cboEstado.setEnabled(true);
			btnImagem.setVisible(true);
			btnLimpar.setText("Limpar");
			btnCancelar.setVisible(true);
			btnEditar.setText("Salvar");
			btnEditar.setVisible(true);
			btnExcluir.setVisible(true);
			btnSalvar.setVisible(false);
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

		imagem = diretorio + "avatares/usuario.jpg";
		carregarAvatar();

		if ( logon.getLogon().size() != 0 ) {
			btnCancelar.setVisible(true);
			btnSalvar.setVisible(true);
			btnEditar.setVisible(false);
			btnExcluir.setVisible(false);
		}
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
			msg("erroVazio", txtUsuario.getText());
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
			imagem = file.getCanonicalPath();
			carregarAvatar();
		} else {
			imagem = diretorio + "avatares/usuario.jpg";
		} 
	}
	
	
	@SuppressWarnings("deprecation")
	public void senhas(){
	
		if( !pwdSenha.getText().equals(pwdSenha2.getText() )){
			
			msg("erroPwd2", txtUsuario.getText());
			pwdSenha.setText(null);
			pwdSenha2.setText(null);
			
			SwingUtilities.invokeLater(new Runnable() {  
				public void run() {  
					pwdSenha.requestFocus();  
				}  
			});
		}
	}
	
	
	public boolean validar(String validaSenha) {  

		if (validaSenha != null){
			char[] senha = pwdSenha.getPassword();
			if (senha.length != validaSenha.length()) {  
				return false;
			} else {  
				for (int i = 0; i < senha.length; i++) {   
					if (senha[i] != validaSenha.charAt(i)) {  
						return false; 
					}  
				}  
			}  
			return true; 
		}
		return false;
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

		case "endereco":
			if( janelaEndereco == null ){
				FrmEndereco endereco;
				try {
					endereco = new FrmEndereco( lblId.getText() );
					endereco.setVisible(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				janelaEndereco.setVisible(true);
			}
			break;
			
		case "pedidos":
			msg("", "Ainda não Implementado!");
			break;
		}
	}
	
	
	// PREENCHE COMBOBOX /////////////////////
	
	public void preencherEndereco() {

		String linha = new String();
		arquivos = new Arquivos();
		ArrayList<String> listaString = new ArrayList<>();
		ArrayList<Endereco> listaEndereco = new ArrayList<>();
		try {
			arquivos.lerArquivo(diretorio + "dados/", "endereco");
			linha = arquivos.getBuffer();
			String[] autor = linha.split(";");
			for ( String d : autor ) {
				String text = d.replaceAll(".*: ", "");
				listaString.add( text );
				if (d.contains("---")) {
					Endereco e = new Endereco();
					for ( int i = 0; i < enderecos.size(); i++ ){
						if ( lblId.getText().equals(enderecos.get(i).getCpf() )) {
							e.setTipoEndereco( listaString.get(1) );
							listaEndereco.add( e );
						}
					}
					listaString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Ordenar alfabeticamente
		String[] enderecos = new String[listaEndereco.size()];
		for ( int i = 0; i < listaEndereco.size(); i++ ){		
			String endereco = listaEndereco.get(i).getTipoEndereco();		
			enderecos[i] = endereco;	
		}
		Arrays.sort(enderecos);
		//Adicionar na combobox
		cboTipoEndereco.addItem( "Residência" );
		for ( int i = 0; i < listaEndereco.size(); i++ ){
			if ( !enderecos[i].equals("Residência" )) {
			cboTipoEndereco.addItem( enderecos[i] );
			}
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
	
	
	public void selecionar(){

		for (int i = 0; i < clientes.size(); i++) {

			if (cpf.equalsIgnoreCase(clientes.get(i).getCpf())) {

				lblId.setText( clientes.get(i).getCpf() );
				txtUsuario.setText( clientes.get(i).getUsuario() );
				ftxtEmail.setText( clientes.get(i).getEmail() );
				txtNome.setText( clientes.get(i).getNome() );
				ftxtDtNasc.setText( clientes.get(i).getDtNasc() );
				ftxtCpf.setText( clientes.get(i).getCpf() );
				ftxtTelefone.setText( clientes.get(i).getTelefone() );
				cboTipoEndereco.getModel().setSelectedItem( clientes.get(i).getTipoEndereco() );
				imagem = clientes.get(i).getImagem();

				if ( cpf.equals( enderecos.get(i).getCpf() ) && 
						cboTipoEndereco.getModel().getSelectedItem().equals( enderecos.get(i).getTipoEndereco())
						){

					txtEndereco.setText( enderecos.get(i).getEndereco() );
					txtComplemento.setText( enderecos.get(i).getComplemento() );
					txtBairro.setText( enderecos.get(i).getBairro() );
					txtCidade.setText( enderecos.get(i).getCidade() );
					cboEstado.getModel().setSelectedItem( enderecos.get(i).getEstado() );
					ftxtCep.setText( enderecos.get(i).getCep() );
				}
				carregarAvatar();
				alterarCampos ("protegerCampos");
			}
		} 
	}

	
	public void pesquisar(){

		String pesquisa = "";
		lblId.setText("");
		ArrayList<Cliente> lista = new ArrayList<>();

		if ( !txtPesquisar.getText().isEmpty() || cpf != null) {
			for (int i = 0; i < clientes.size(); i++) {
				if ( 
						clientes.get(i).getUsuario().toLowerCase().contains( txtPesquisar.getText().toLowerCase() ) ||
						clientes.get(i).getNome().toLowerCase().contains( txtPesquisar.getText().toLowerCase() ) ||
						clientes.get(i).getCpf().toLowerCase().contains( txtPesquisar.getText().toLowerCase() ) || 
						clientes.get(i).getUsuario().toLowerCase().startsWith( txtPesquisar.getText().toLowerCase() ) ||
						clientes.get(i).getCpf().toLowerCase().startsWith( txtPesquisar.getText().toLowerCase() ) || 
						clientes.get(i).getNome().toLowerCase().startsWith( txtPesquisar.getText().toLowerCase() )
						) {

					lblId.setText( clientes.get(i).getCpf() );
					Cliente cliente = new Cliente();
					cliente.setCpf( clientes.get(i).getCpf() );
					cliente.setNome( clientes.get(i).getNome() );
					lista.add(cliente);
				} else {
					if ( clientes.size() == i+1 && lblId.getText().isEmpty() ){
						msg("vazioPesquisa", txtPesquisar.getText());
						if ( validar == true ){
							limparCampos();
						} else {
							return;
						}
					}
				}

			}
			String[] filtro = new String[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				filtro[i] = lista.get(i).getCpf() + " : " + lista.get(i).getNome();
				pesquisa = lista.get(i).getCpf();
			}
			if (filtro != null && filtro.length > 1) {
				Arrays.sort( filtro );
				pesquisa = (String) JOptionPane.showInputDialog(janela, "Selecione:\n", "Usuários Localizados",
						JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
			}
			if (pesquisa == "0" || pesquisa != null){
				for (int i = 0; i < clientes.size(); i++) {
					if (pesquisa.replaceAll(" : .*", "").equalsIgnoreCase(clientes.get(i).getCpf())) {
						
						lblId.setText( clientes.get(i).getCpf() );
						txtUsuario.setText( clientes.get(i).getUsuario() );
						ftxtEmail.setText( clientes.get(i).getEmail() );
						txtNome.setText( clientes.get(i).getNome() );
						ftxtDtNasc.setText( clientes.get(i).getDtNasc() );
						ftxtCpf.setText( clientes.get(i).getCpf() );
						ftxtTelefone.setText( clientes.get(i).getTelefone() );
						cboTipoEndereco.getModel().setSelectedItem( clientes.get(i).getTipoEndereco() );
						imagem = clientes.get(i).getImagem();
						
						if ( lblId.getText().equals( enderecos.get(i).getCpf() ) && 
								cboTipoEndereco.getModel().getSelectedItem().equals( enderecos.get(i).getTipoEndereco())
							){
							
							txtEndereco.setText( enderecos.get(i).getEndereco() );
							txtComplemento.setText( enderecos.get(i).getComplemento() );
							txtBairro.setText( enderecos.get(i).getBairro() );
							txtCidade.setText( enderecos.get(i).getCidade() );
							cboEstado.getModel().setSelectedItem( enderecos.get(i).getEstado() );
							ftxtCep.setText( enderecos.get(i).getCep() );
						}
						carregarAvatar();
						alterarCampos ("protegerCampos");
					}
				}
			}
		} else {
				msg("erroPesquisa", txtPesquisar.getText());
				limparCampos();
		} 
	} 


	@SuppressWarnings("deprecation")
	public void editar() {

		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();

		if ( !ftxtCpf.getText().isEmpty() ) {

			for (int i = 0; i < clientes.size(); i++) {
				if ( lblId.getText().equals(clientes.get(i).getCpf() )) {
					msg( "confirmaEditar", clientes.get(i).getUsuario() );
//					verificarCampos();
				}
			}			
			if( validar == true ){

				for (int i = 0; i < clientes.size(); i++) {
					if ( lblId.getText().equals(clientes.get(i).getCpf() )) {
						
						cliente.setUsuario( txtUsuario.getText() );
						if ( pwdSenha.getText() != "" ){
							cliente.setSenha( clientes.get(i).getSenha() );
						} else {
							cliente.setSenha( pwdSenha.getText() );
						}
						cliente.setEmail( ftxtEmail.getText() );
						cliente.setNome( txtNome.getText() );
						cliente.setDtNasc( ftxtDtNasc.getText());
						cliente.setCpf( ftxtCpf.getText() );
						cliente.setTelefone( ftxtTelefone.getText() );
						cliente.setImagem( imagem );
						cliente.setTipoEndereco( cboTipoEndereco.getSelectedItem().toString() );
						cliente.setNivel( "Usuario" );
						cliente.setDtCadastro( clientes.get(i).getDtCadastro() );
						cliente.setDtAlterado( obterData() );
						clientes.set( i, cliente );
						atualizarArquivo( clientes );
						
						endereco.setCpf( ftxtCpf.getText() );
						endereco.setTipoEndereco( cboTipoEndereco.getSelectedItem().toString() );
						endereco.setEndereco( txtEndereco.getText() );
						endereco.setComplemento( txtComplemento.getText() );
						endereco.setBairro( txtBairro.getText() );
						endereco.setCidade( txtCidade.getText() );
						endereco.setEstado( cboEstado.getSelectedItem().toString() );
						endereco.setCep( ftxtCep.getText() );
						endereco.setDtCadastro( enderecos.get(i).getDtCadastro() );
						endereco.setDtAlterado( obterData() );
						enderecos.set( i, endereco );
						atualizarEndereco( enderecos );
					}
				}
			} 
			validar = false;
			msg( "editar", txtUsuario.getText() );
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void salvar() {

		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();

		if (!txtUsuario.getText().isEmpty() 
				&& pwdSenha.getPassword().length != 0) {
			if ( !ftxtCpf.getText().isEmpty() ) {		
				for ( int i = 0; i < clientes.size(); i++ ) {
					if ( ftxtCpf.getText().equals(clientes.get(i).getCpf() )) {
						msg( "erroEditar", clientes.get(i).getNome() );
//						verificarCampos();
					}
				}			
				if(!(validar == true)){
					if( pwdSenha.getText().equals(pwdSenha2.getText() )){
						cliente.setUsuario( txtUsuario.getText() );
						cliente.setSenha( pwdSenha.getText() );
						cliente.setEmail( ftxtEmail.getText() );
						cliente.setNome( txtNome.getText() );
						cliente.setDtNasc( ftxtDtNasc.getText());
						cliente.setCpf( ftxtCpf.getText() );
						cliente.setTelefone( ftxtTelefone.getText() );
						cliente.setImagem( imagem );
						cliente.setTipoEndereco( cboTipoEndereco.getSelectedItem().toString() );
						cliente.setNivel( "Usuario" );
						cliente.setDtCadastro( obterData() );
						cliente.setDtAlterado( obterData() );
						clientes.add( cliente );
						atualizarArquivo( clientes );

						endereco.setCpf( ftxtCpf.getText() );
						endereco.setTipoEndereco( cboTipoEndereco.getSelectedItem().toString() );
						endereco.setEndereco( txtEndereco.getText() );
						endereco.setComplemento( txtComplemento.getText() );
						endereco.setBairro( txtBairro.getText() );
						endereco.setCidade( txtCidade.getText() );
						endereco.setEstado( cboEstado.getSelectedItem().toString() );
						endereco.setCep( ftxtCep.getText() );
						endereco.setDtCadastro( obterData() );
						endereco.setDtAlterado( obterData() );
						enderecos.add( endereco );
						atualizarEndereco( enderecos );

						msg( "salvar", txtUsuario.getText() );
						limparCampos();
						validar = false;
					} else {
						msg("erroPwd2", txtUsuario.getText());
					}
				}
			} 
		} else {
			msg("erroVazio", txtUsuario.getText());
		}
	}
	

	public void excluir() {

		if ( !ftxtCpf.getText().isEmpty() ) {

			for (int i = 0; i < clientes.size(); i++) {
				if ( lblId.getText().equals(clientes.get(i).getCpf() )) {
					msg( "confirmaExcluir", clientes.get(i).getUsuario() );
					if(!(validar == true)){
					clientes.remove(i);
					enderecos.remove(i);
					atualizarArquivo( clientes );
					atualizarEndereco( enderecos );
					msg("excluir", txtUsuario.getText());
					limparCampos();
					validar = false;
					}
				} else {
					if(i == clientes.size()){
						msg("erroExcluir", clientes.get(i).getUsuario());
					}
				}
			} 
		} else {
			pesquisar();
		}
	}
	
	
	public void lerEndereco() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS
		String linha = new String();
		ArrayList<String> lista = new ArrayList<>();
		try {
			daoEndereco.lerArquivo( diretorio + "dados/", "endereco" );
			linha = daoEndereco.getBuffer();
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
					enderecos.add( endereco );
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void atualizarEndereco(List<Endereco> listaEndereco) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
		File f = new File(diretorio + "dados/" + "endereco" );
		f.delete();
		for (Endereco enderecos : listaEndereco) {
			try {
				daoEndereco.escreverArquivo(diretorio  + "dados/", "endereco", "", enderecos);
			} catch (IOException e) {
				e.printStackTrace();
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
					Cliente cliente = new Cliente();
					cliente.setUsuario( lista.get(0) );
					cliente.setSenha( lista.get(1) );
					cliente.setEmail( lista.get(2) );
					cliente.setNome( lista.get(3) );
					cliente.setDtNasc( lista.get(4) );
					cliente.setCpf( lista.get(5) );
					cliente.setTelefone( lista.get(6) );
					cliente.setImagem( lista.get(7) );
					cliente.setTipoEndereco( lista.get(8) );
					cliente.setNivel( lista.get(9) );
					cliente.setDtCadastro( lista.get(10) );
					cliente.setDtAlterado( lista.get(11) );
					clientes.add(cliente);
					lista.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void atualizarArquivo( List<Cliente> listaClientes ) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
		File f = new File( diretorio + "dados/" + arquivo );
		f.delete();
		for (Cliente clientes : listaClientes) {
			try {
				dao.escreverArquivo( diretorio  + "dados/", arquivo, "", clientes );
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
					"O Usuário " + mensagem + " foi salvo com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
			
		case "editar":
			JOptionPane.showMessageDialog(null, 
					"O Usuário " + mensagem + " foi editado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "excluir":
			JOptionPane.showMessageDialog(null, 
					"O Usuário " + mensagem + " foi excluído com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/record.png" ));
			break;
		
		case "confirmaEditar":
			Object[] editar = { "Confirmar", "Cancelar" };  
			int ed = JOptionPane.showOptionDialog(null, 
					"Você confirma a edição do Usuário " + mensagem + " ?",
					"Edição de Usuário", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), editar, editar[1]);
			if (ed == 1) { validar = false; } else { validar = true; }
			break;
			
		case "confirmaExcluir":
			Object[] excluir = { "Confirmar", "Cancelar" };  
			int ex = JOptionPane.showOptionDialog(null, 
					"Você confirma a exclusão do Usuário " + mensagem + " ?",
					"Exclusão de Usuário", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), excluir, excluir[1]);
			if (ex == 1) { validar = true; } else { validar = false; }
			break;
			
		case "vazioPesquisa":
			Object[] pesquisar = { "Confirmar", "Cancelar" };  
			int pq = JOptionPane.showOptionDialog(null, 
					"ATENÇÃO!\n\nNenhum resultador encontrado com: " + mensagem
					+ "\n Gostaria de adicionar este Usuário?", 
					"Usuário não Localizado", 
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
					"CPF já cadastrado para o Usuário " + mensagem + " !\n\n"
							+ "Verifique sua digitação ou realize seu login com o Usuário correto.",
					"Já Cadastrado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
		
		case "erroExcluir":
			JOptionPane.showMessageDialog(null, 
					"O Usuário " + mensagem + " não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ));
			break;
			
		case "erroPwd2":
			JOptionPane.showMessageDialog(null, 
					"A senha e a confirmação são diferentes… '" + mensagem 
					+ "' !\n\nVerifique sua digitação e tente novamente.",
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
		
		
		public ActionListener pedidos = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//verifica qual botãoo está solicitando a ação
				Object source = e.getSource();

				if(source == btnPedidos){
					abrirJanela( "pedidos" );
				}
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
		
		public ActionListener pesquisar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				pesquisar();
			}
		};
		
		public ActionListener carregarImagem = new ActionListener() {

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
				if ( logon.getLogon().size() != 0 ) {
					alterarCampos ("desprotegerCampos");
					btnEditar.setVisible(false);
					btnSalvar.setVisible(true);
				}
			}
		};
		
		
		public ActionListener adicionar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ( cboTipoEndereco.getSelectedItem().equals( "Adicionar NOVO" )){
					abrirJanela( "endereco" );
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
				//Limita caracteres digitados
				int limite = 330;
				
				if (ftxtCpf.getText().length() <= limite - 1) {
					//deixe passar
				} else {
					//limpa
					e.setKeyChar((char) KeyEvent.VK_CLEAR);  
				}
				if (ftxtTelefone.getText().length() <= limite - 1) {
					//deixe passar
				} else {
					//limpa
					e.setKeyChar((char) KeyEvent.VK_CLEAR);  
				}
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
					}

					@Override
					public void focusLost(FocusEvent e) {
						
						senhas();
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
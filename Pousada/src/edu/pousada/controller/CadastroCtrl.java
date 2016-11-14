/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 11/11/2016
 */

package edu.pousada.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.pousada.dao.ClienteDAO;
import edu.pousada.dao.ClienteDAOImpl;
import edu.pousada.dao.FuncionarioDAO;
import edu.pousada.dao.FuncionarioDAOImpl;
import edu.pousada.entity.Cliente;
import edu.pousada.entity.Funcionario;

public class CadastroCtrl {

	private static JTextField txtCadastroNome;
	private static JTextField txtCadastroEmail;
	private static JTextField txtCadastroEndereco;
	private static JTextField txtCadastroBairro;
	private static JTextField txtCadastroCidade;
	private static JTextField txtCadastroEstado;
	private static JTextField txtCadastroPais;
	private static JTextField txtCadastroLogin;
	private static JFormattedTextField ftxtCadastroDocNum;
	private static JFormattedTextField ftxtCadastroTelefone;
	private static JFormattedTextField ftxtCadastroCelular;
	private static JFormattedTextField ftxtCadastroDtNasc;
	private static JFormattedTextField ftxtCadastroCep;
	private static JPasswordField pwdCadastroSenha;
	private static JPasswordField pwdCadastroSenhaConfirma;
	private static JComboBox<String> cboCadastroDocTipo;
	static JButton btnCadastroLimpar;
	static JButton btnCadastroEditar;
	static JButton btnCadastroExcluir;

	private static int id;
	private static LogonCtrl ctrlLogon = LogonCtrl.getInstance();
	private static List<Funcionario> funcionarios;
	private static List<Cliente> clientes;

	public CadastroCtrl(
			JTextField txtCadastroNome,
			JTextField txtCadastroEmail,
			JTextField txtCadastroEndereco,
			JTextField txtCadastroBairro,
			JTextField txtCadastroCidade,
			JTextField txtCadastroEstado,
			JTextField txtCadastroPais,
			JTextField txtCadastroLogin,
			JFormattedTextField ftxtCadastroDocNum,
			JFormattedTextField ftxtCadastroTelefone,
			JFormattedTextField ftxtCadastroCelular,
			JFormattedTextField ftxtCadastroDtNasc,
			JFormattedTextField ftxtCadastroCep,
			JPasswordField pwdCadastroSenha,
			JPasswordField pwdCadastroSenhaConfirma,
			JComboBox<String> cboCadastroDocTipo,
			JButton btnCadastroLimpar,
			JButton btnCadastroEditar,
			JButton btnCadastroExcluir
			){

		CadastroCtrl.txtCadastroNome = txtCadastroNome;
		CadastroCtrl.txtCadastroEmail = txtCadastroEmail;
		CadastroCtrl.ftxtCadastroDtNasc = ftxtCadastroDtNasc;
		CadastroCtrl.txtCadastroEndereco = txtCadastroEndereco;
		CadastroCtrl.txtCadastroBairro = txtCadastroBairro;
		CadastroCtrl.txtCadastroCidade = txtCadastroCidade;
		CadastroCtrl.txtCadastroEstado = txtCadastroEstado;
		CadastroCtrl.txtCadastroPais = txtCadastroPais;
		CadastroCtrl.txtCadastroLogin = txtCadastroLogin;
		CadastroCtrl.ftxtCadastroDocNum = ftxtCadastroDocNum;
		CadastroCtrl.ftxtCadastroTelefone = ftxtCadastroTelefone;
		CadastroCtrl.ftxtCadastroCelular = ftxtCadastroCelular;
		CadastroCtrl.ftxtCadastroCep = ftxtCadastroCep;
		CadastroCtrl.pwdCadastroSenha = pwdCadastroSenha;
		CadastroCtrl.pwdCadastroSenhaConfirma = pwdCadastroSenhaConfirma;
		CadastroCtrl.cboCadastroDocTipo = cboCadastroDocTipo; 
		CadastroCtrl.btnCadastroLimpar = btnCadastroLimpar;
		CadastroCtrl.btnCadastroEditar = btnCadastroEditar;
		CadastroCtrl.btnCadastroExcluir = btnCadastroExcluir;
		CadastroCtrl.clientes = new ArrayList<Cliente>();

		LogonCtrl.getInstance();
		preencheTipoDoc();
	}


	//  DAO  //////////////////////////////////////////

	
	public static void cargaFuncionario(){

		FuncionarioDAO dao = new FuncionarioDAOImpl();
		try {
			funcionarios = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > cargaFuncionario()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public static void adicionarFuncionario( Funcionario obj ){

		FuncionarioDAO dao = new FuncionarioDAOImpl();
		try {
			dao.adicionar( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > adicionarFuncionario()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public static void alterarFuncionario( Funcionario obj ){

		FuncionarioDAO dao = new FuncionarioDAOImpl();
		try {
			dao.alterar( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > alterarFuncionario()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}
	
	
	public static void excluirFuncionario( Funcionario obj ){

		FuncionarioDAO dao = new FuncionarioDAOImpl();
		try {
			dao.excluir( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > excluirFuncionario()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}
	
	
	public static void cargaCliente(){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			clientes = dao.todos();
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > cargaCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public static void adicionarCliente( Cliente obj ){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			dao.adicionar( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > adicionarCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}


	public static void alterarCliente( Cliente obj ){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			dao.alterar( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > alterarCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}
	
	
	public static void excluirCliente( Cliente obj ){

		ClienteDAO dao = new ClienteDAOImpl();
		try {
			dao.excluir( obj );
		} catch (SQLException e) {
			MensagensCtrl.msg("", "ERRO SQL " + e.getSQLState() 
					+ "\n\nLocal:\nCadastroCtrl > excluirCliente()."  
					+ "\n\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}
	}



	// CRUD ////////////////////////////////////


	public static void adicionaFuncionario() throws ParseException{


		if( CamposCtrl.valida( "cadastro" ) != false ){
			// verifica se os campos foram preenchidos
			
			Funcionario f = new Funcionario();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");

			if( new String( pwdCadastroSenha.getPassword() )
			.equals( new String( pwdCadastroSenhaConfirma.getPassword() ))){

				f.setNome( txtCadastroNome.getText() );
				f.setEmail( txtCadastroEmail.getText() );
				f.setCpf( ftxtCadastroDocNum.getText()
						.replace(".","").replace("/","").replace("-","").replace("_","")  );
				f.setDtNasc( sdf.parse( ftxtCadastroDtNasc.getText().replace("/","") ));
				f.setTelefone( ftxtCadastroTelefone.getText()
						.replace("(","").replace(")", "").replace("_","").replace("-","") );
				f.setCelular( ftxtCadastroCelular.getText()
						.replace("(","").replace(")", "").replace("_","").replace("-","") );
				f.setEndereco( txtCadastroEndereco.getText() );
				f.setBairro( txtCadastroBairro.getText() );
				f.setCidade( txtCadastroCidade.getText() );
				f.setEstado( txtCadastroEstado.getText() );
				f.setPais( txtCadastroPais.getText() );
				f.setCep( ftxtCadastroCep.getText().replace("_","").replace("-","") );
				f.setCargo("");
				f.setSetor("");
				f.setLogin( txtCadastroLogin.getText() );
				f.setSenha( new String( pwdCadastroSenha.getPassword() ));
				f.setAtivo( true );
				f.setDtCadastro( new Date() );

				adicionarFuncionario( f );

				MensagensCtrl.msg("adicionaCadastro", f.getNome() );
			} else {
				MensagensCtrl.msg("erroSenha", "" );
			}
		} 
	}


	public static void alteraFuncionario() throws ParseException{


		if( CamposCtrl.valida( "cadastro" ) != false ){
			// verifica se os campos foram preenchidos
			
			Funcionario f = new Funcionario();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");

			cargaFuncionario();

			//se não houver funcionario na base de dados
			if( !funcionarios.isEmpty() ){


				if( new String( pwdCadastroSenha.getPassword() )
				.equals( new String( pwdCadastroSenhaConfirma.getPassword() ))){

					f.setId( id );
					f.setNome( txtCadastroNome.getText() );
					f.setEmail( txtCadastroEmail.getText() );
					f.setCpf( ftxtCadastroDocNum.getText()
							.replace(".","").replace("/","").replace("-","").replace("_","")  );
					f.setDtNasc( sdf.parse( ftxtCadastroDtNasc.getText().replace("/","") ));
					f.setTelefone( ftxtCadastroTelefone.getText()
							.replace("(","").replace(")", "").replace("_","").replace("-","") );
					f.setCelular( ftxtCadastroCelular.getText()
							.replace("(","").replace(")", "").replace("_","").replace("-","") );
					f.setEndereco( txtCadastroEndereco.getText() );
					f.setBairro( txtCadastroBairro.getText() );
					f.setCidade( txtCadastroCidade.getText() );
					f.setEstado( txtCadastroEstado.getText() );
					f.setPais( txtCadastroPais.getText() );
					f.setCep( ftxtCadastroCep.getText().replace("_","").replace("-","") );
					f.setCargo("");
					f.setSetor("");
					f.setLogin( txtCadastroLogin.getText() );
					f.setSenha( new String( pwdCadastroSenha.getPassword() ));
					f.setAtivo( true );

					alterarFuncionario( f );

					MensagensCtrl.msg("atualizaCadastro", f.getNome() );
				} else {
					MensagensCtrl.msg("erroSenha", "" );
				}
			} 
		}
	}
	
	public static void adicionaCliente() throws ParseException{


		if( CamposCtrl.valida( "cadastro" ) != false ){
			// verifica se os campos foram preenchidos
			
			Cliente c = new Cliente();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");

			if( new String( pwdCadastroSenha.getPassword() )
			.equals( new String( pwdCadastroSenhaConfirma.getPassword() ))){

				c.setNome( txtCadastroNome.getText() );
				c.setEmail( txtCadastroEmail.getText() );
				c.setDocumento( ftxtCadastroDocNum.getText()
						.replace(".","").replace("/","").replace("-","").replace("_","")  );
				c.setDocTipo( cboCadastroDocTipo.getSelectedItem().toString() );
				c.setDtNasc( sdf.parse( ftxtCadastroDtNasc.getText().replace("/","") ));
				c.setTelefone( ftxtCadastroTelefone.getText()
						.replace("(","").replace(")", "").replace("_","").replace("-","") );
				c.setCelular( ftxtCadastroCelular.getText()
						.replace("(","").replace(")", "").replace("_","").replace("-","") );
				c.setEndereco( txtCadastroEndereco.getText() );
				c.setBairro( txtCadastroBairro.getText() );
				c.setCidade( txtCadastroCidade.getText() );
				c.setEstado( txtCadastroEstado.getText() );
				c.setPais( txtCadastroPais.getText() );
				c.setCep( ftxtCadastroCep.getText().replace("_","").replace("-","") );
				c.setLogin( txtCadastroLogin.getText() );
				c.setSenha( new String( pwdCadastroSenha.getPassword() ));
				c.setAtivo( true );
				c.setDtCadastro( new Date() );

				adicionarCliente( c );

				MensagensCtrl.msg("adicionaCadastro", c.getNome() );
			} else {
				MensagensCtrl.msg("erroSenha", "" );
			}
		} 
	}


	public static void alteraCliente() throws ParseException{


		if( CamposCtrl.valida( "cadastro" ) != false ){
			// verifica se os campos foram preenchidos
			
			Cliente c = new Cliente();
			DateFormat sdf = new SimpleDateFormat("ddMMyyyy");

			cargaCliente();

			//se não houver cliente na base de dados
			if( !clientes.isEmpty() ){


				if( new String( pwdCadastroSenha.getPassword() )
				.equals( new String( pwdCadastroSenhaConfirma.getPassword() ))){

					c.setId( id );
					c.setNome( txtCadastroNome.getText() );
					c.setEmail( txtCadastroEmail.getText() );
					c.setDocumento( ftxtCadastroDocNum.getText()
							.replace(".","").replace("/","").replace("-","").replace("_","")  );
					c.setDocTipo( cboCadastroDocTipo.getSelectedItem().toString() );
					c.setDtNasc( sdf.parse( ftxtCadastroDtNasc.getText().replace("/","") ));
					c.setTelefone( ftxtCadastroTelefone.getText()
							.replace("(","").replace(")", "").replace("_","").replace("-","") );
					c.setCelular( ftxtCadastroCelular.getText()
							.replace("(","").replace(")", "").replace("_","").replace("-","") );
					c.setEndereco( txtCadastroEndereco.getText() );
					c.setBairro( txtCadastroBairro.getText() );
					c.setCidade( txtCadastroCidade.getText() );
					c.setEstado( txtCadastroEstado.getText() );
					c.setPais( txtCadastroPais.getText() );
					c.setCep( ftxtCadastroCep.getText().replace("_","").replace("-","") );
					c.setLogin( txtCadastroLogin.getText() );
					c.setSenha( new String( pwdCadastroSenha.getPassword() ));
					c.setAtivo( true );

					alterarCliente( c );

					MensagensCtrl.msg("atualizaCadastro", c.getNome() );
				} else {
					MensagensCtrl.msg("erroSenha", "" );
				}
			} 
		}
	}


	public static void preencheCadastro(){
		//Preenche o cadastro com os dados do cliente logado e trava os campos

		cargaCliente();

		if( !clientes.isEmpty() ){
			for (int i = 0; i < clientes.size(); i++) {

				if( clientes.get(i).getId()
						.equals( ctrlLogon.getSession().get(0).getIdUsuario() )){

					SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

					CamposCtrl.limpa("cadastro");
					
					id = ctrlLogon.getSession().get(0).getIdUsuario();
					txtCadastroNome.setText( clientes.get(i).getNome() );
					ftxtCadastroDocNum.setText( clientes.get(i).getDocumento() );
					cboCadastroDocTipo.setSelectedItem( clientes.get(i).getDocTipo() );
					txtCadastroEmail.setText( clientes.get(i).getEmail() );
					ftxtCadastroDtNasc.setText( dt.format( clientes.get(i).getDtNasc() ));
					ftxtCadastroTelefone.setText( clientes.get(i).getTelefone() );
					ftxtCadastroCelular.setText( clientes.get(i).getCelular() );
					txtCadastroEndereco.setText( clientes.get(i).getEndereco() );
					txtCadastroBairro.setText( clientes.get(i).getBairro() );
					txtCadastroCidade.setText( clientes.get(i).getCidade() );
					txtCadastroEstado.setText( clientes.get(i).getEstado() );
					ftxtCadastroCep.setText( clientes.get(i).getCep() );
					txtCadastroPais.setText( clientes.get(i).getPais() ); 
					txtCadastroLogin.setText( clientes.get(i).getLogin() );
					pwdCadastroSenha.setText( clientes.get(i).getSenha() );
					pwdCadastroSenhaConfirma.setText( clientes.get(i).getSenha() );

					CamposCtrl.desativa("cadastro");
				}
			}
		}
	}


	//  SUPORTE  /////////////////////////////////


	public static void alteraBotao( Boolean editar ) {

		if( editar != false ){
			btnCadastroEditar.setText("Editar");
			btnCadastroLimpar.setEnabled(false);
			btnCadastroExcluir.setEnabled(false);
		} else {

		}
	}

	// COMBOBOX /////////////////////////////////

	public void preencheTipoDoc(){

		String[] tipos = {
				"CPF",
				"RG",
				"CNH",
				"Passaporte"
		};

		//Adicionar na combobox
		cboCadastroDocTipo.addItem( "Selecione…" );
		for ( int i = 0; i < tipos.length; i++ ){
			cboCadastroDocTipo.addItem( tipos[i] );
		}
	}


	// BOTAO //////////////////////////////////


	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if( source == cboCadastroDocTipo ){
				CamposCtrl.mascara( "cadastro" );
			}
			if( source == btnCadastroLimpar ){
				CamposCtrl.limpa( "cadastro" );
			}
			if( source == btnCadastroExcluir ){
				MensagensCtrl.msg( "construir", "" );
			}
			if( source == btnCadastroEditar ){
				try {
					if( btnCadastroEditar.getText() != "Salvar"){
						btnCadastroEditar.setText("Salvar");
						btnCadastroLimpar.setEnabled(true);
						btnCadastroExcluir.setEnabled(true);
						CamposCtrl.ativa("cadastro");
					} else {
						if( CamposCtrl.valida( "cadastro" ) != false ){
							if( ctrlLogon.getSession().isEmpty() != true ){
								alteraCliente();
							} else {
								adicionaCliente();
								btnCadastroEditar.setText("Editar");
								btnCadastroLimpar.setEnabled(false);
								btnCadastroExcluir.setEnabled(false);
								CamposCtrl.desativa("cadastro");
							}
						}
					}
				} catch (ParseException e1) {
					MensagensCtrl.msg("", "ERRO " + e1.getCause() 
							+ "\n\nLocal: ClienteCtrl >  acionar() : alteraCliente()"  
							+ "\n\nMensagem:\n" + e1.getMessage() );
					//e1.printStackTrace();
				}
			}
		}
	};


	// CONTROLE TECLA ///////////////////////////////


	public KeyListener teclas = new KeyListener() {

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
				MensagensCtrl.msg( "sair", "" );
				break;
			case KeyEvent.VK_DELETE:
				break;
			case 8: //MAC OSX: DELETE
				break;
			case KeyEvent.VK_TAB:
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

			String tipo = "";

			//verifica qual componenete e configura o tipo de entrada (alfa ou numero)
			Object source = e.getSource();

			if( source == txtCadastroNome ){
				tipo = "alfa";
			}
			if( source == txtCadastroBairro ){
				tipo = "alfa";
			}
			if( source == txtCadastroCidade ){
				tipo = "alfa";
			}
			if( source == txtCadastroEstado ){
				tipo = "alfa";
			}
			if( source == txtCadastroPais ){
				tipo = "alfa";
			}
			if( source == ftxtCadastroCep ){
				tipo = "numero";
			}

			switch ( tipo ) {

			case "numero":
				String numeros = "0987654321";
				if(!numeros.contains(e.getKeyChar()+"")){
					e.consume();
				}
				break;

			case "alfa":
				String caracteres = "0987654321";
				if(caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};
}
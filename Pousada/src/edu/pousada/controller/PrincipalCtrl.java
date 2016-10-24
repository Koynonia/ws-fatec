/**
 * @author Fernando Moraes Oliveira
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 23/10/2016
 */

package edu.pousada.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.pousada.boundary.PrincipalFrm;
import edu.pousada.boundary.ReservaFrm;
import edu.pousada.dao.PrincipalDAO;
import edu.pousada.dao.PrincipalDAOImpl;
import edu.pousada.entity.Chale;
import edu.pousada.entity.Principal;

public class PrincipalCtrl {

	private PrincipalFrm form; 
	private JPanel painelReserva;
	private JPanel painelContato;
	private JTextField txtPesquisa; 
	private JTextArea txtaPrincipalInfo; 
	private JTextArea txtaPrincipalDetalhe; 
	private JTextArea txtaChaleInfo; 
	private JTextArea txtaChaleDetalhe; 
	private JTextArea txtaLazerInfo; 
	private JTextArea txtaLazerDetalhe; 
	private JTextArea txtaServicoInfo; 
	private JTextArea txtaServicoDetalhe; 
	private JTextArea txtaReservaInfo; 
	private JTextArea txtaReservaObs; 
	private JTextArea txtaContatoInfo; 
	private JTextArea txtaContatoMsg; 
	private JComboBox<String> cboChaleCategoria; 
	private JComboBox<String> cboReservaCategoria; 
	private JComboBox<String> cboReservaDocTipo; 
	private JComboBox<String> cboContatoAssunto; 
	private JTable tabChale; 
	private JTable tabReserva; 
	private JButton btnLogin;  
	private JButton btnReservaEnviar; 
	private JButton btnContatoEnviar;
	private JButton btnPesquisar;
	private JButton btnReservaLimpar;
	private JButton btnContatoLimpar;
	private String diretorio = "../Pousada/resources/";
	private LogonCtrl logon = LogonCtrl.getInstance();
	private String imagem;
	private boolean validar;
	private List<Principal> infos;
	private List<Chale> categorias;

	public PrincipalCtrl(
			PrincipalFrm form, 
			JPanel painelReserva, 
			JPanel painelContato, 
			JTextField txtPesquisa, 
			JTextArea txtaPrincipalInfo, 
			JTextArea txtaPrincipal, 
			JTextArea txtaChaleInfo, 
			JTextArea txtaChale, 
			JTextArea txtaLazerInfo, 
			JTextArea txtaLazer, 
			JTextArea txtaServicoInfo, 
			JTextArea txtaServico, 
			JTextArea txtaReservaInfo, 
			JTextArea txtaReservaObs, 
			JTextArea txtaContatoInfo, 
			JTextArea txtaContatoMsg, 
			JComboBox<String> cboChaleCategoria, 
			JComboBox<String> cboReservaChale, 
			JComboBox<String> cboReservaDocTipo, 
			JComboBox<String> cboContatoAssunto, 
			JTable tabChale, 
			JTable tabReserva, 
			JButton btnLogin,  
			JButton btnReservaEnviar, 
			JButton btnContatoEnviar,
			JButton btnPesquisar, 
			JButton btnReservaLimpar, 
			JButton btnContatoLimpar
			){

		this.form = form; 
		this.painelReserva = painelReserva;
		this.painelContato = painelContato;
		this.txtPesquisa = txtPesquisa;
		this.txtaPrincipalInfo = txtaPrincipalInfo; 
		this.txtaPrincipalDetalhe = txtaPrincipal; 
		this.txtaChaleInfo = txtaChaleInfo; 
		this.txtaChaleDetalhe = txtaChale; 
		this.txtaLazerInfo = txtaLazerInfo; 
		this.txtaLazerDetalhe = txtaLazer; 
		this.txtaServicoInfo = txtaServicoInfo; 
		this.txtaServicoDetalhe = txtaServico; 
		this.txtaReservaInfo = txtaReservaInfo; 
		this.txtaReservaObs = txtaReservaObs; 
		this.txtaContatoInfo = txtaContatoInfo; 
		this.txtaContatoMsg = txtaContatoMsg; 
		this.cboChaleCategoria = cboChaleCategoria; 
		this.cboReservaCategoria = cboReservaChale; 
		this.cboReservaDocTipo = cboReservaDocTipo; 
		this.cboContatoAssunto = cboContatoAssunto; 
		this.tabChale = tabChale; 
		this.tabReserva = tabReserva; 
		this.btnLogin = btnLogin;  
		this.btnReservaEnviar = btnReservaEnviar; 
		this.btnContatoEnviar = btnContatoEnviar;
		this.btnPesquisar = btnPesquisar;
		this.btnReservaLimpar = btnReservaLimpar;
		this.btnContatoLimpar = btnContatoLimpar;
		this.infos = new ArrayList<Principal>();

		carregaDAO();
		preecheInfo();
		preencheCategoria();
		preencheTipoDoc();
		preencheAssunto();
	}
	
	
	public void limpaCampos(){

		Integer count = 0;
		Component[] painelAtivo = null;

		switch ( count ){
		case 0:
			painelAtivo = painelReserva.getComponents();
			break;

		case 1:
			painelAtivo = painelContato.getComponents();
			break;
		}

		for ( Component c : painelAtivo ) {
			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				l.setText(null);
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				l.setValue(null);
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				l.setSelectedIndex(0);
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if( l.getName() == null ){
					l.setText(null);
				}
			}
		}
		count = count + 1;
	}


	// DAO //////////////////////////////////////

	public void carregaDAO(){

		PrincipalDAO dao = new PrincipalDAOImpl();
		try {
			infos = dao.info();
			categorias = dao.categoria();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// AREA DE TEXTO /////////////////////////////

	public void preecheInfo(){

		if(infos != null){
			for (int i = 0; i < infos.size(); i++) {
				txtaPrincipalInfo.setText( infos.get(i).getPrincipalInfo() ); 
				txtaPrincipalDetalhe.setText( infos.get(i).getPrincipalDetalhe() ); 
				txtaChaleInfo.setText( infos.get(i).getChaleInfo() ); 
				txtaChaleDetalhe.setText( infos.get(i).getChaleDetalhe() ); 
				txtaLazerInfo.setText( infos.get(i).getLazerInfo() ); 
				txtaLazerDetalhe.setText( infos.get(i).getLazerDetalhe() ); 
				txtaServicoInfo.setText( infos.get(i).getServicoInfo() ); 
				txtaServicoDetalhe.setText( infos.get(i).getServicoDetalhe() ); 
				txtaReservaInfo.setText( infos.get(i).getReservaInfo() ); 
				txtaContatoInfo.setText( infos.get(i).getContatoInfo() ); 
			}
		}
	}


	// COMBOBOX /////////////////////////////////

	public void preencheCategoria(){

		//Ordenar alfabeticamente
		String[] listaCategoria = new String[categorias.size()];
		for ( int i = 0; i < categorias.size(); i++ ){		
			String item = categorias.get(i).getCategoria();		
			listaCategoria[i] = item;	
		}
		Arrays.sort(listaCategoria);

		//Adicionar na combobox
		cboChaleCategoria.addItem( "Categorias…" );
		cboReservaCategoria.addItem( "Categorias…" );
		for ( int i = 0; i < categorias.size(); i++ ){
			cboChaleCategoria.addItem( listaCategoria[i] );
			cboReservaCategoria.addItem( listaCategoria[i] );
		}
	}

	public void preencheTipoDoc(){
		String[] tipos = {
				"CPF",
				"RG",
				"Passaporte"
		};

		//Adicionar na combobox
		cboReservaDocTipo.addItem( "Selecione…" );
		for ( int i = 0; i < tipos.length; i++ ){
			cboReservaDocTipo.addItem( tipos[i] );
		}
	}

	public void preencheAssunto(){

		String[] assuntos = {
				"Central de Reservas",
				"Reservas para Grupos",
				"Reservas para Eventos Corporativos",
				"Sugestões, Elogios ou Reclamações",
				"Outros"
		};

		//Adicionar na combobox
		cboContatoAssunto.addItem( "Selecione…" );
		for ( int i = 0; i < assuntos.length; i++ ){
			cboContatoAssunto.addItem( assuntos[i] );
		}
	}
	
	
	// TABELA //////////////////////////////////



	// JANELA //////////////////////////////////

	public void abrir ( String nome ){

		switch ( nome ){

		case "reservas":
			ReservaFrm reserva;
			try {
				reserva = new ReservaFrm();
				reserva.setVisible(true);
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			break;
			
		case "construir":
			JOptionPane.showMessageDialog(null, 
					"Em construção!\nEsta função ainda nÃ£o foi implementada.", 
					"Sem implementação", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( diretorio + "/icons/builder.png" ));
			break;
			
		case "sair":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o fechamento" 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( diretorio + "/icons/warning.png" ), exit, exit[1] );
			if ( fechar == 0 ) {
				validar = true;
			} else {
				validar = false;
			}
			if(validar == true){
				System.exit(0);
			}
			break;
		}
	}


	// BOTAO //////////////////////////////////

	public ActionListener acionar = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//verifica qual botao esta solicitando a acao
			Object source = e.getSource();

			if(source == btnLogin){
				abrir( "construir" );
			}
			if(source == btnReservaEnviar){
				abrir( "reservas" );
			}
			if(source == btnContatoEnviar){
				abrir( "construir" );
			}
			if(source == btnPesquisar){
				abrir( "construir" );
			}
			if(source == btnReservaLimpar){
				limpaCampos();
			}
		}
	};

	// CONTROLE TECLA ///////////////////////////////


	public KeyListener teclar = new KeyListener() {  

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
				/*msg("sistema","Fechamento");
							if(validar == false){
							System.exit(0);
							}*/
				abrir( "sair" );
				break;
			case KeyEvent.VK_DELETE:
				//removeLinha();
				break;
			case 8: //MAC OSX: DELETE
				//removeLinha();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};

	// MOUSE //////////////////////////////////

	public MouseListener clicar = new MouseListener() {

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

			if(e.getClickCount() == 2){  

			}
		}
	};
}
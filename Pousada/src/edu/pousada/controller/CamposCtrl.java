/**
 * @author FERNANDO MORAES OLIVEIRA
 * Materia Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 11/11/2016
 */

package edu.pousada.controller;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class CamposCtrl {

	private static JPanel painelReserva;
	private static JPanel painelContato;
	private static JPanel painelCadastro; 
	private static LogonCtrl ctrlLogon = LogonCtrl.getInstance();

	public CamposCtrl(
			JPanel painelReserva, 
			JPanel painelContato, 
			JPanel painelCadastro
			){

		CamposCtrl.painelReserva = painelReserva;
		CamposCtrl.painelContato = painelContato;
		CamposCtrl.painelCadastro = painelCadastro;

	}


	public static void limpa( String guia ){
		// limpa todos os campos da tela

		Component[] painelAtivo = null;

		switch ( guia ){
		case "reserva":
			painelAtivo = painelReserva.getComponents();
			break;

		case "contato":
			painelAtivo = painelContato.getComponents();
			break;

		case "cadastro":
			painelAtivo = painelCadastro.getComponents();
			break;
		}

		for ( Component c : painelAtivo ) {
			if( c.isEnabled() ){
				if ( c instanceof JTextField ) {
					JTextField l = ( JTextField )c;
					l.setText(null);
					l.setBackground(new Color(255,255,255));
				}
				if ( c instanceof JFormattedTextField ) {
					JFormattedTextField  l = ( JFormattedTextField )c;
					l.setValue(null);
					l.setBackground(new Color(255,255,255));
				}
				if (c instanceof JComboBox ) {
					@SuppressWarnings("unchecked")
					JComboBox<String> l = ( JComboBox<String> )c;
					l.setSelectedIndex(0);
					l.setBackground(new Color(255,255,255));
				}
				if ( c instanceof JTextArea ) {
					JTextArea  l = ( JTextArea )c;
					if( !l.getName().contains( "Info") ){
						l.setText(null);
						l.setBackground(new Color(255,255,255));
					}
				}
			}
		}
	}


	public static void ativa( String guia ){
		// ativa os campos para edição

		Component[] painel = null;

		switch ( guia ){

		case "reserva":
			painel = painelReserva.getComponents();
			break;

		case "contato":
			painel = painelContato.getComponents();
			break;

		case "cadastro":
			painel = painelCadastro.getComponents();
			break;
		}

		for ( Component c : painel ) {

			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				l.setEnabled(true);
				if( guia != "cadastro" )
					l.setText(null);
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				l.setEnabled(true);
				if( guia != "cadastro" )
					l.setText(null);
			}
			if (c instanceof JPasswordField ) {
				JPasswordField l = ( JPasswordField )c;
				l.setEnabled(true);
				if( guia != "cadastro" )
					l.setText(null);
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				l.setEnabled(true);
				if( guia != "cadastro" )
					l.setSelectedIndex(0);
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if( !l.getName().contains( "Info") ){
					l.setText(null);
					if( guia != "cadastro" )
						l.setEnabled(true);
				}
			}
		}
	}


	public static void desativa( String guia ){
		// desativa os campos para edição

		if ( ctrlLogon.getSession().get(0).getPerfil() != 2 
				|| ctrlLogon.getSession().isEmpty() != true ){

			Component[] painel = null;

			switch ( guia ){

			case "reserva":
				painel = painelReserva.getComponents();
				break;

			case "contato":
				painel = painelContato.getComponents();
				break;

			case "cadastro":
				painel = painelCadastro.getComponents();
				break;
			}

			for ( Component c : painel ) {

				if ( c instanceof JTextField ) {
					JTextField l = ( JTextField )c;
					if ( l.isEnabled() 
							&& !l.getName().equalsIgnoreCase("adultos")
							&& !l.getName().equalsIgnoreCase("criancas") 
							&& !l.getName().equalsIgnoreCase("inicio")
							&& !l.getName().equalsIgnoreCase("fim")){

						l.setEnabled(false);
					}
				}
				if ( c instanceof JFormattedTextField ) {
					JFormattedTextField  l = ( JFormattedTextField )c;
					if ( l.isEnabled() 
							&& !l.getName().equalsIgnoreCase("inicio")
							&& !l.getName().equalsIgnoreCase("fim") ){
						l.setEnabled(false);
					}
				}
				if (c instanceof JPasswordField ) {
					JPasswordField l = ( JPasswordField )c;
					l.setEnabled(false);
				}
				if (c instanceof JComboBox ) {
					@SuppressWarnings("unchecked")
					JComboBox<String> l = ( JComboBox<String> )c;
					if ( l.isEnabled() 
							&& l.getName() != "categoria"
							&& l.getName() != "assunto"){

						l.setEnabled(false);
					}
				}
				if ( c instanceof JTextArea ) {
					JTextArea  l = ( JTextArea )c;
					if ( !l.getName().equalsIgnoreCase( "obs" )
							&& !l.getName().equalsIgnoreCase( "mensagem" )
							&& !l.getName().contains( "Info") ){
						if ( l.isEnabled() ){

							l.setEnabled(false);
						}
					}
				}
			}
		}
	}


	public static boolean valida( String guia ){
		// valida todos os campos da tela se foram preenchidos

		boolean vazio = false;
		String campo = null;
		Component[] painelAtivo = null;

		switch ( guia ){

		case "reserva":
			painelAtivo = painelReserva.getComponents();
			break;

		case "contato":
			painelAtivo = painelContato.getComponents();
			break;

		case "cadastro":
			painelAtivo = painelCadastro.getComponents();
			break;
		}

		for ( Component c : painelAtivo ) {

			if ( c instanceof JTextField ) {
				JTextField l = ( JTextField )c;
				if ( l.getText().isEmpty() && l.isVisible() ){
					if ( !l.getName().equalsIgnoreCase( "pesquisa")){
						vazio = true;
						campo = l.getName();
						l.requestFocus();
						l.setBackground(new Color(255,240,245));
					}
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if ( c instanceof JFormattedTextField ) {
				JFormattedTextField  l = ( JFormattedTextField )c;
				if ( l.getText().isEmpty() || l.getText().equalsIgnoreCase("  /  /    ")){
					vazio = true;
					campo = l.getName();
					l.requestFocus();
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if (c instanceof JComboBox ) {
				@SuppressWarnings("unchecked")
				JComboBox<String> l = ( JComboBox<String> )c;
				if ( l.getSelectedIndex() == 0){
					vazio = true;
					campo = l.getName();
					l.requestFocus();
					l.setBackground(new Color(255,240,245));
				} else {
					l.setBackground(new Color(255,255,255));
				}
			}
			if ( c instanceof JTextArea ) {
				JTextArea  l = ( JTextArea )c;
				if ( !l.getName().equalsIgnoreCase( "obs")){
					if ( l.getText().isEmpty() ){
						vazio = true;
						campo = l.getName();
						l.requestFocus();
						l.setBackground(new Color(255,240,245));
					} else {
						l.setBackground(new Color(255,255,255));
					}
				}
			}
		}
		if ( vazio == true ){
			return MensagensCtrl.msg( "erroCampo", campo );

		}
		return true;
	}


	public static boolean data( JFormattedTextField dtInicio, JFormattedTextField dtFim ){
		//valida entre as datas inicio e final da reserva

		Date inicio = null;
		Date fim = null;
		Date atual = null;
		Date dt = new Date();
		String dta;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		dta = sdf.format( dt );

		try {
			inicio = sdf.parse( dtInicio.getText() );
			fim = sdf.parse( dtFim.getText() );
			atual = sdf.parse( dta );
		} catch (ParseException e) {
			MensagensCtrl.msg("", "LogonCtrl : cargaLogon. Erro: " + e.getCause() 
					+ "\nMensagem:\n" + e.getMessage() );
			//e.printStackTrace();
		}

		//verifica se a data inicial é inferior à data atual do sistema
		if( !inicio.equals( atual ) ){
			if ( !inicio.after( atual )){
				dtInicio.setText(null);
				dtInicio.requestFocus();
				return MensagensCtrl.msg("erroDataInicial","");

				//após corrigir data inicial, verifica se a data final é anterior à inicial
			} else if( fim.before(inicio) ){
				dtFim.setText(null);
				dtFim.requestFocus();
				return MensagensCtrl.msg("erroDataFinal","");
			}
		} else if( fim.before(inicio) ){
			//verifica se a data final é anterior à inicial
			dtFim.setText(null);
			dtFim.requestFocus();
			return MensagensCtrl.msg("erroDataFinal","");
		} 
		return true;
	}


	public static void mascara( String guia ){
		// altera as mascaras dos campos

		String tipo = null;
		String texto = null;

		Component[] painelAtivo = null;

		switch ( guia ){

		case "reserva":
			painelAtivo = painelReserva.getComponents();
			break;

		case "cadastro":
			painelAtivo = painelCadastro.getComponents();
			break;
		}

		// troca a mascara na seleção da combbox
		try {

			for ( Component c : painelAtivo ) {

				if (c instanceof JComboBox ) {
					@SuppressWarnings("unchecked")
					JComboBox<String> l = ( JComboBox<String> )c;
					if( l.getName().equalsIgnoreCase( "docTipo" )){

						tipo = l.getSelectedItem().toString();
					}
				}

				if ( c instanceof JFormattedTextField ) {
					JFormattedTextField  l = ( JFormattedTextField  )c;
					if ( l.getName().equalsIgnoreCase( "docNum" )){

						if( l.getText() != null ){
							texto = l.getText().replaceAll("\\.","").replaceAll("-","");
						}

						// limpa o campo
						l.setFormatterFactory(null);
						l.setText(null);
						l.setValue(null);

						switch( tipo ){

						case "CPF":
							MaskFormatter cpf = new MaskFormatter("###.###.###-##");
							cpf.setPlaceholderCharacter('_');
							l.setFormatterFactory(new DefaultFormatterFactory( cpf ));
							break;

						case "CNPJ":
							MaskFormatter cnpj = new MaskFormatter("##.###.###/####-##");
							cnpj.setPlaceholderCharacter('_');

							l.setFormatterFactory(new DefaultFormatterFactory( cnpj ));
							break;

						case "CNH":
							MaskFormatter cnh = new MaskFormatter("##.###.###.###");
							cnh.setPlaceholderCharacter('_');
							l.setFormatterFactory(new DefaultFormatterFactory( cnh ));
							break;

						default:
							l.setFormatterFactory(null);
						}
						l.setText( texto );
					}
				}
			}

			if( guia != "reserva" ){
				mascara ( "reserva" );
			}

		} catch (ParseException e1) {
			MensagensCtrl.msg("", "ERRO " + e1.getCause() 
					+ "\n\nLocal: CamposCtrl > mascara"  
					+ "\n\nMensagem:\n" + e1.getMessage() );
			//e1.printStackTrace();
		}
	}
}
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RadioApartamentosController implements ActionListener {
	
	JRadioButton rdbtnInquilino, rdbtnProprietario ,rdbtnVazio;
	JTextField txtNome, txtTelefone;
	JButton btnAtualizarMorador, btnExcluirMorador, btnPesquisarMorador, btnGravarMorador;
	
	private static String ocupacao;

	public RadioApartamentosController(JRadioButton rdbtnInquilino, JRadioButton rdbtnProprietario,
			JRadioButton rdbtnVazio, JTextField txtNome, JTextField txtTelefone, JButton btnAtualizarMorador,
			JButton btnExcluirMorador, JButton btnPesquisarMorador, JButton btnGravarMorador) {
		this.rdbtnInquilino = rdbtnInquilino;
		this.rdbtnProprietario = rdbtnProprietario;
		this.rdbtnVazio = rdbtnVazio;
		this.txtNome = txtNome;
		this.txtTelefone = txtTelefone;
		this.btnAtualizarMorador = btnAtualizarMorador;
		this.btnExcluirMorador = btnExcluirMorador;
		this.btnPesquisarMorador = btnPesquisarMorador;
		this.btnGravarMorador = btnGravarMorador;
		groupButton();
		
		
	}
	
	public RadioApartamentosController(){
		
	}
	
	private void groupButton( ) {

		ButtonGroup bg = new ButtonGroup( );

		bg.add(rdbtnInquilino);
		bg.add(rdbtnProprietario);
		bg.add(rdbtnVazio);
		rdbtnInquilino.setSelected(true);
		setSelectedButton(rdbtnInquilino.getText());
		}
	
	public void selecionaRadio(String ocupacao){
		if(ocupacao.contains("Vazio")){
			rdbtnVazio.setSelected(true);
		}else{
			if(ocupacao.contains("Inquilino")){
				rdbtnInquilino.setSelected(true);
			}else{
				rdbtnProprietario.setSelected(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(rdbtnVazio.isSelected()){
			txtNome.setEditable(false);
			txtTelefone.setEditable(false);
			btnAtualizarMorador.setEnabled(false);
			btnExcluirMorador.setEnabled(false);
			btnPesquisarMorador.setEnabled(false);
			btnGravarMorador.setEnabled(false);
			
			setSelectedButton(rdbtnVazio.getText());
		}else{
			txtNome.setEditable(true);
			txtTelefone.setEditable(true);
			btnAtualizarMorador.setEnabled(true);
			btnExcluirMorador.setEnabled(true);
			btnPesquisarMorador.setEnabled(true);
			btnGravarMorador.setEnabled(true);
			if(rdbtnInquilino.isSelected()){
				setSelectedButton(rdbtnInquilino.getText());
			}else{
				setSelectedButton(rdbtnProprietario.getText());
			}
		}
		System.out.println(getSelectedButton() + " está selecionado");
	}
	public void setSelectedButton(String ocupacao){
		this.ocupacao = ocupacao;
	}
	
	public String getSelectedButton(){
		return ocupacao;	
	}
	

}

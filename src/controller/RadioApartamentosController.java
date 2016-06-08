package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RadioApartamentosController implements ActionListener {

	JRadioButton rdbtnInquilino = new JRadioButton();
	JRadioButton rdbtnProprietario = new JRadioButton();
	JRadioButton rdbtnVazio = new JRadioButton();
	JTextField txtNome, txtTelefone;
	JButton btnAtualizarMorador, btnExcluirMorador, btnPesquisarMorador, btnGravarMorador;
	RadioHandler handler = new RadioHandler();
	
	public static String ocupacao;

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
		
		setSelectedButton(rdbtnInquilino.getText());
		groupButton();

	}

	public RadioApartamentosController() {
		groupButton();
	}
	
	private class RadioHandler implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == rdbtnVazio){
				rdbtnVazio.setSelected(true);
			}else if( e.getSource() == rdbtnInquilino){
				rdbtnInquilino.setSelected(true);
			}else{
				rdbtnProprietario.setSelected(true);
			}
			
		}
		
	}

	private void groupButton() {
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(rdbtnInquilino);
		bg.add(rdbtnProprietario);
		bg.add(rdbtnVazio);
	}

	public void selecionaRadio(String o) {
		if (o.equals("Vazio")) {
			
			rdbtnVazio.setSelected(true);
			setSelectedButton(rdbtnVazio.getText());
			
		}else 	if (o.equals("Inquilino")) {
			
			rdbtnInquilino.setSelected(true);
			setSelectedButton(rdbtnInquilino.getText());
			
		}else {
			
			rdbtnProprietario.setSelected(true);
			setSelectedButton(rdbtnProprietario.getText());
			
		}
		
		groupButton();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (rdbtnVazio.isSelected()) {
			txtNome.setEditable(false);
			txtTelefone.setEditable(false);
			btnAtualizarMorador.setEnabled(false);
			btnExcluirMorador.setEnabled(false);
			btnPesquisarMorador.setEnabled(false);
			btnGravarMorador.setEnabled(false);

			setSelectedButton(rdbtnVazio.getText());
		} else {
			txtNome.setEditable(true);
			txtTelefone.setEditable(true);
			btnAtualizarMorador.setEnabled(true);
			btnExcluirMorador.setEnabled(true);
			btnPesquisarMorador.setEnabled(true);
			btnGravarMorador.setEnabled(true);
			if (rdbtnInquilino.isSelected()) {
				setSelectedButton(rdbtnInquilino.getText());
			} else {
				setSelectedButton(rdbtnProprietario.getText());
			}
		}
	}

	public void setSelectedButton(String ocupacao) {
		RadioApartamentosController.ocupacao = ocupacao;
	}

	public String getSelectedButton() {
		return ocupacao;
	}

}

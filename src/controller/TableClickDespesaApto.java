package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TableClickDespesaApto implements MouseListener {

	private JTable table;
	private DefaultTableModel model;
	private JTextField txtDespesa;
	private JTextField txtRef;
	private JTextField txtVencimento;
	private JTextField txtValor;
	private JTextField idDespesa;
	private JButton btnAtualizarDespesa;

	public TableClickDespesaApto(JTable table,
								 DefaultTableModel model, 
								 JTextField txtDespesa, 
								 JTextField txtRef,
								 JTextField txtVencimento, 
								 JTextField txtValor, 
								 JTextField idDespesa, 
								 JButton btnAtualizarDespesa) {
		this.table = table;
		this.model = model;
		this.txtDespesa = txtDespesa;
		this.txtRef = txtRef;
		this.txtVencimento = txtVencimento;
		this.txtValor = txtValor;
		this.idDespesa = idDespesa;
		this.btnAtualizarDespesa = btnAtualizarDespesa;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int linha = table.rowAtPoint(e.getPoint());
		txtDespesa.setText(model.getValueAt(linha, 1).toString());
		txtRef.setText(model.getValueAt(linha, 2).toString());
		txtVencimento.setText(model.getValueAt(linha, 3).toString());
		txtValor.setText(model.getValueAt(linha, 4).toString());
		idDespesa.setText(model.getValueAt(linha, 0).toString());
		btnAtualizarDespesa.setEnabled(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

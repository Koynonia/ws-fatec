package controller;

import javax.swing.table.DefaultTableModel;

public class TableModelDespesasApto extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelDespesasApto(Object[][] dados, String[] cabecalho) {
		super.setDataVector(dados, cabecalho);
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

}

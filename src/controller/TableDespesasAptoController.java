package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.Despesas;
import persistence.DespesasApartamentoDao;
import persistence.IDespesasApartamentoDao;

public class TableDespesasAptoController  {

	private DefaultTableModel model;
	private JTable table;

	public TableDespesasAptoController(DefaultTableModel model) {
		this.model = model;
	}

	public TableDespesasAptoController() {
		// TODO Auto-generated constructor stub
	}

	public void preencheTable(int idApto) throws SQLException {
		IDespesasApartamentoDao dADao = new DespesasApartamentoDao();
		List<Despesas> listaDespesas = dADao.consultaDespesasApartamento(idApto);
//		despesas = listaDespesas;
//		model.setRowCount(0);

		// JCheckBox cBox = new JCheckBox();
//		for (Despesas d : listaDespesas) {
//			Object[] linha = new Object[4];
//
//			linha[0] = d.getDespesa();
//			linha[1] = d.getDtReferencia();
//			linha[2] = d.getDtVencimento();
//			linha[3] = d.getValor();
//			// linha[4] = cBox;
//			System.out.println(linha[0] + " " + linha[1] + "\n");
//			model.addRow(linha);
//		}
	}
}

	

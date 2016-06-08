package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import model.Despesas;
import persistence.DespesasApartamentoDao;
import persistence.IDespesasApartamentoDao;

public class TableDespesasAptoController {
	private DefaultTableModel model;
	
	public TableDespesasAptoController(DefaultTableModel model){
		this.model = model;
	}
	
	public TableDespesasAptoController(){
	}
	
	public void preencheTable(int idApto) throws SQLException {
		IDespesasApartamentoDao dADao = new DespesasApartamentoDao();
		//Instanciar campo idApto para colocar no parametro do DAO
		List<Despesas> listaDespesas = dADao.consultaDespesasApartamento(0);
		model.setRowCount(0);
		JCheckBox cBox = new JCheckBox();
		for(Despesas d : listaDespesas){
			Object[] linha = new Object[5];

			linha[0] = d.getDespesa();
			linha[1] = d.getDtReferencia();
			linha[2] = d.getDtVencimento();
			linha[3] = d.getValor();
			linha[4] = cBox;
			model.addRow(linha);
		}
	}
}

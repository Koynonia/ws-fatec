package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import persistence.ApartamentosDao;
import persistence.DespesasApartamentoDao;
import persistence.IApartamentosDao;
import persistence.IDespesasApartamentoDao;
import model.Apartamentos;
import model.Despesas;

public class BotaoDespesasApartamentoController implements ActionListener {
	
	JTextField txtNumApto;
	JTextField txtDespesa;
	JTextField txtRef;
	JTextField txtValor;
	JTextField txtVencimento;
	JButton btnPesquisar;
	JButton btnAtualizarDespesa;
	JButton btnExcluirSeleo;
	JButton btnGravarDespesa;
	JButton btnSelecionarTudo;
	private static int idApto;

	public BotaoDespesasApartamentoController(JTextField txtNumApto,
			JTextField txtDespesa, JTextField txtRef, JTextField txtValor,
			JTextField txtVencimento, JButton btnPesquisar,
			JButton btnAtualizarDespesa, JButton btnExcluirSeleo,
			JButton btnGravarDespesa, JButton btnSelecionarTudo) {
		
		this.txtNumApto = txtNumApto;
		this.txtDespesa = txtDespesa;
		this.txtRef = txtRef;
		this.txtValor = txtValor;
		this.txtVencimento = txtVencimento;
		this.btnPesquisar = btnPesquisar;
		this.btnAtualizarDespesa = btnAtualizarDespesa;
		this.btnExcluirSeleo = btnExcluirSeleo;
		this.btnGravarDespesa = btnGravarDespesa;
		this.btnSelecionarTudo = btnSelecionarTudo;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Apartamentos a = new Apartamentos();
		Despesas d = new Despesas();
		
		if(e.getSource() == btnPesquisar){
			a.setNumero(Integer.parseInt(txtNumApto.getText()));
			idApto = pesquisarApto(a);

			if(idApto > 0){
			
				TableDespesasAptoController tDAController = new TableDespesasAptoController();
				try {
					tDAController.preencheTable(idApto);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),
							"ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				//arrumar isso depois
				JOptionPane.showMessageDialog(null, "Apartamento não encontrado",
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
		
	   }else if(e.getSource() == btnAtualizarDespesa){
			//TODO
		}else if(e.getSource() == btnExcluirSeleo){
			//TODO
		}else if(e.getSource() == btnSelecionarTudo){
			//TODO
		}else{
			d.setDespesa(txtDespesa.getText());
			d.setDtReferencia(txtRef.getText());
			d.setDtVencimento(txtVencimento.getText());
			d.setValor(Integer.parseInt(txtValor.getText()));
			gravarDespesa(d,idApto);
			//TODO reload the table to show newer dispenses
		}
	}

	
	private void constroiTable(){
		
		Object[][] dados = new Object[][] {};
		String[] cabecalho = new String[5];

		cabecalho[0] = "Nome da Despesa";
		cabecalho[1] = "Referencia";
		cabecalho[2] = "Vencimento";
		cabecalho[3] = "Valor";
		cabecalho[4] = "Seleção";

		DefaultTableModel model = new TableModelDespesasApto(dados,
				cabecalho);
	}
		
		
		


	private void gravarDespesa(Despesas despesa, int idApartamento) {
		IDespesasApartamentoDao dADao = new DespesasApartamentoDao();
		try {
			dADao.insereDespesaApartamento(despesa, idApartamento);
			JOptionPane.showMessageDialog(null, "Despesa inserida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	//ap pesquisar apto deve carregar as despesas na table
	private int pesquisarApto(Apartamentos a) {
		IApartamentosDao aDao = new ApartamentosDao();
		Apartamentos apartamento = new Apartamentos();
		try {
			apartamento = aDao.consultaApartamento(a);
			JOptionPane.showMessageDialog(null, "Apartamento encontrado", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
			return apartamento.getId();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}

}

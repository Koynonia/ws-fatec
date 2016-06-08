package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import persistence.ApartamentosDao;
import persistence.IApartamentosDao;
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
		Despesas despesa = new Despesas();
		
		if(e.getSource() == btnPesquisar){
			Apartamentos a = new Apartamentos();
			int idApto;
			
			a.setNumero(Integer.parseInt(txtNumApto.getText()));
			idApto = pesquisarApto(a);
			
			if(idApto > 0){
				
				TableDespesasAptoController dATController = new TableDespesasAptoController();
				try {
					dATController.preencheTable(idApto);
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
			
		}else if(e.getSource() == btnExcluirSeleo){
			
		}else if(e.getSource() == btnSelecionarTudo){
			
		}else{
			despesa.setDespesa(txtDespesa.getText());
			//PAROU AQUI VERIFICAR VARIAVEIS DA ENTIDADE
		}
	}


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

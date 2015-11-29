package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ExposicaoMdl;
import persistence.ExposicaoFile;
import view.FrmObraArtistaSelec;

public class ExposicaoCtrl {

	private JPanel form;
	private static JTextField txtDataIni, txtDataFim, txtNomeArtista, txtId;
	private JTextField txtTitulo;
	private JTable tObras;
	private boolean validar;
	private List<ExposicaoMdl> expos;
	private JTextField txtTema;
	private JTextArea txtAreaDescri;
	private ExposicaoFile arquivo = new ExposicaoFile();
	private DefaultTableModel tableModel;
	private ArquivosCtrl arquivos = new ArquivosCtrl();

	public ExposicaoCtrl(JTextField txtDataI, JTextField txtDataF, JTextField txtNomeArtista, JTextField txtId,
			JTable tObras, JTextField txtTitulo, JTextField txtTema, JTextArea txtAreaDescri,
			DefaultTableModel tableModel) {

		ExposicaoCtrl.txtDataIni = txtDataI; // neste caso nao se usa
												// this,porque o metodo que
												// utiliza a variavel � estatico
		ExposicaoCtrl.txtDataFim = txtDataF;
		this.txtNomeArtista = txtNomeArtista;
		this.tObras = tObras;
		this.txtId = txtId;
		this.txtTitulo = txtTitulo;
		this.txtTema = txtTema;
		this.txtAreaDescri = txtAreaDescri;
		this.expos = new ArrayList<ExposicaoMdl>();
		this.tableModel = tableModel;
		lerArquivo();
	}


	/*
	 * As flags funcionam para quando se tem mais de uma chamada de calendario
	 * na mesma tela, ajuda no tratamento de retorno
	 */

	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		txtId.setText("EXP" + id);
	}




	public void chamaSelecaoObras() {
		JDialog frmOASelec = new FrmObraArtistaSelec(txtNomeArtista.getText(), tableModel, tObras);
		frmOASelec.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOASelec.setLocationRelativeTo(null);
		frmOASelec.setModal(true);
		frmOASelec.setVisible(true);
	}

	// CRUD
	public void gravarExposicao() {
		ExposicaoMdl exposicao = new ExposicaoMdl();
		new ExposicaoFile();
		validar = false;
		if (!txtTitulo.getText().isEmpty()) {
			for (int i = 0; i < expos.size(); i++) {
				if (txtTitulo.getText().equalsIgnoreCase(expos.get(i).getTitulo())) {
					msg("erroredit", expos.get(i).getTitulo());
					validar = true;
				}
			}
			if (!(validar == true)) {
				exposicao.setId(txtId.getText());
				exposicao.setTitulo(txtTitulo.getText());
				exposicao.setDataIni(txtDataIni.getText());
				exposicao.setDataFim(txtDataFim.getText());
				exposicao.setTema(txtTema.getText());
				exposicao.setDescricao(txtAreaDescri.getText());
//				exposicao.setObrasExp(tObras.getModel().toString()); //Pega itens da
				// Jtable********
				expos.add(exposicao);
				msg("save", txtTitulo.getText());
				atualizaDados(expos);
				limpaCampos();
				gerarId();
			}
		} else {
			msg("errornull", txtTitulo.getText());
		}

	}

	public void lerArquivo() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "exposicoes");
			linha = arquivos.getBuffer();
			String[] listaExposicao = linha.split(";");
			for (String s : listaExposicao) {
				String text = s.replace(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					ExposicaoMdl exposicao = new ExposicaoMdl();
					exposicao.setId(list.get(0));
					exposicao.setTitulo(list.get(1));
					exposicao.setDataIni(list.get(2));
					exposicao.setDataFim(list.get(3));
					exposicao.setTema(list.get(4));
					exposicao.setDescricao(list.get(5));
					//exposicao.setObrasExp(list.get(6));
					expos.add(exposicao);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void pesquisar() {
		ArrayList<ExposicaoMdl> lista = new ArrayList<>();
		String pesquisa = "";
		if (!txtTitulo.getText().isEmpty() || !txtId.getText().isEmpty()) {
			for (int i = 0; i < expos.size(); i++) {
				if (txtTitulo.getText().equalsIgnoreCase(expos.get(i).getTitulo())
						|| txtId.getText().equalsIgnoreCase(expos.get(i).getId())) {
					txtId.setText(expos.get(i).getId());
					txtTitulo.setText(expos.get(i).getTitulo());
					txtDataIni.setText(expos.get(i).getDataIni());
					txtDataFim.setText(expos.get(i).getDataFim());
					txtTema.setText(expos.get(i).getTema());
					txtAreaDescri.setText(expos.get(i).getDescricao());
					// Colocar o set Text para o JTable aqui
					validar = true;
				} else if (expos.get(i).getTitulo().toLowerCase().startsWith(txtTitulo.getText().toLowerCase())) {
					validar = true;
					// Verificar esse else if
				}
			}
			if (validar == true) {
				for (int i = 0; i < expos.size(); i++) {
					boolean filtro = expos.get(i).getTitulo().toLowerCase()
							.startsWith(txtTitulo.getText().toLowerCase());
					if (filtro == true) {
						ExposicaoMdl item = new ExposicaoMdl();
						item.setId(expos.get(i).getId());
						item.setTitulo(expos.get(i).getTitulo());
						item.setDataIni(expos.get(i).getDataIni());
						item.setDataFim(expos.get(i).getDataFim());
						item.setTema(expos.get(i).getTema());
						item.setDescricao(expos.get(i).getDescricao());
						lista.add(item);
					}
				}
				String[] filtro = new String[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					filtro[i] = lista.get(i).getId() + " : " + lista.get(i).getTitulo();
					pesquisa = lista.get(i).getId();
				}
				if (filtro != null && filtro.length > 1) {
					pesquisa = (String) JOptionPane.showInputDialog(form, "Selecione:\n", "Registros Localizados",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				}
				if (pesquisa == "0" || pesquisa != null) {
					for (int i = 0; i < expos.size(); i++) {
						if (pesquisa.replaceAll(" : .*", "").equalsIgnoreCase(expos.get(i).getId())) {
							txtId.setText(expos.get(i).getId());
							txtTitulo.setText(expos.get(i).getTitulo());
							txtDataIni.setText(expos.get(i).getDataIni());
							txtDataFim.setText(expos.get(i).getDataFim());
							txtTema.setText(expos.get(i).getTema());
							txtAreaDescri.setText(expos.get(i).getDescricao());
						}
					}
				}
				validar = false;
			} else {
				if (pesquisa == "") {
					msg("nosearch", txtTitulo.getText());
					limpaCampos();
				}
				validar = false;
			}
		} else {
			msg("errorsearch", txtTitulo.getText());
		}
	}

	public void editar() {
		ExposicaoMdl exposicao = new ExposicaoMdl();
		validar = false;
		if (!txtId.getText().isEmpty() && !txtTema.getText().isEmpty()) {
			for (int i = 0; i < expos.size(); i++) {
				if (!txtId.getText().equalsIgnoreCase(expos.get(i).getId())
						&& !txtTitulo.getText().equalsIgnoreCase(expos.get(i).getTitulo())) {
					msg("erroredit", expos.get(i).getTitulo());
					validar = true;
				}
			}
			if (!(validar == true)) {
				for (int i = 0; i < expos.size(); i++) {
					if (txtId.getText().equalsIgnoreCase(expos.get(i).getId())) {
						exposicao.setId(txtId.getText());
						exposicao.setTitulo(txtTitulo.getText());
						exposicao.setDataIni(txtDataIni.getText());
						exposicao.setDataFim(txtDataFim.getText());
						exposicao.setTema(txtTema.getText());
						exposicao.setDescricao(txtAreaDescri.getText());
						// set para a JTable aqui
						expos.set(i, exposicao);
						atualizaDados(expos);
						msg("edit", txtTitulo.getText());
						limpaCampos();
					}
				}
			}
		} else {
			msg("errorsearch", txtTitulo.getText());
		}
	}

	public void excluir() {
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < expos.size(); i++) {
				if (txtId.getText().equalsIgnoreCase(expos.get(i).getId())
						&& txtTitulo.getText().equalsIgnoreCase(expos.get(i).getTitulo())) {
					expos.remove(i);
					validar = true;
				} else {
					if (i == expos.size()) {
						msg("errordelete", expos.get(i).getTitulo());
					}
				}
			}
			if (validar == true) {
				msg("deleteconfirm", txtTitulo.getText());
				if (validar == false) {
					atualizaDados(expos);
					msg("delete", txtTitulo.getText());
					limpaCampos();
				} else {
					expos.clear();
					lerArquivo();
				}
			} else {
				validar = false;
				msg("errordelete", txtTitulo.getText());
			}
		} else {
			pesquisar();
		}
	}

	public void limpaCampos() {
		txtId.setText(null);
		txtTitulo.setText(null);
		txtDataIni.setText(null);
		txtDataFim.setText(null);
		txtTema.setText(null);
		txtAreaDescri.setText(null);
		// tObras.setSelected //limpar a jTable
	}

	public void atualizaDados(List<ExposicaoMdl> listExpo) {
		File f = new File("../MASProject/dados/exposicoes");
		f.delete();
		for (ExposicaoMdl exposicao : listExpo) {
			try {
				arquivo.escreveArquivo("../MASProject/dados/", "exposicoes", "", exposicao);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampo Vazio.\nPor favor, digite o ID ou nome do Setor.",
					"Erro", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "nosearch":
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\n\nNão localizamos o registro: '" + mensagem + "' !\nVerifique sua digitação.",
					"Não Localizado", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, "ATENÇÃO! Por favor, digite para pesquisar!", "Erro",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' salvo com sucesso.", "Confirmação",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errorrec":
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\nNão foi possível apagar o registro: " + txtId.getText() + " " + txtTitulo.getText()
							+ "!\nVerifique sua digitação!",
					"Erro", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "edit":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' editado com sucesso.", "Confirmação",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "erroredit":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' já existe!", "Já Cadastrado",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "deleteconfirm":
			Object[] options = { "Confirmar", "Cancelar" };
			int r = JOptionPane.showOptionDialog(null, "Você confirma a exclusão do registro '" + mensagem + "'?",
					"Exclusão de Registro", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"), options, options[0]);
			if (r == 0) {
				validar = false;
			}
			break;
		case "delete":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' excluído com sucesso.", "Confirmação",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errordelete":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' não pode ser alterado para a exclusão.",
					"Erro", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		default:
			JOptionPane.showMessageDialog(null,
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nTermo: "
							+ mensagem + "\n\nVolte ao trabalho duro e conserte isso!!!",
					"Erro no Controller", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
		}
	}

	public ActionListener pesquisaArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			chamaSelecaoObras();
		}
	};

	

	public ActionListener gravarExpo = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarExposicao();
		}
	};

	public ActionListener pesquisar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			pesquisar();
		}
	};

	public ActionListener excluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			excluir();
		}
	};

	public ActionListener editar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			editar();
		}
	};

	
}

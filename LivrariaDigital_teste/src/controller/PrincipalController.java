package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import boundary.FrmCarrinho;
import boundary.FrmLista;
import dao.ManipulaArquivoLivro;
import entity.Livro;

public class PrincipalController implements ActionListener {

	private JComboBox<String> cbItensPesquisa;
	private JTextField txtPesquisa;
	private JButton btnProcurar;
	private JButton btnCarrinho;
	private JFrame janelaPrincipal;
	private FrmLista janelaLista;
	
	public PrincipalController(
			JComboBox<String> cbItensPesquisa, 
			JTextField txtPesquisa, 
			JButton btnProcurar,
			JButton btnCarrinho, 
			JFrame janelaPesquisa) {
		this.cbItensPesquisa = cbItensPesquisa;
		this.txtPesquisa = txtPesquisa;
		this.btnProcurar = btnProcurar;
		this.btnCarrinho = btnCarrinho;
		this.janelaPrincipal = janelaPesquisa;
	}
	
	public void procurarLivro(String itemMenu, String texto){
		ManipulaArquivoLivro arqLivro = new ManipulaArquivoLivro();
		List<Livro> listLivros = new ArrayList<Livro>();
		try {
			texto = texto.toUpperCase();
			for( Livro livro : arqLivro.lerLivro() ){
				if(itemMenu.equals("TITULO")){
					if(livro.getTitulo().contains( texto )){
						listLivros.add( livro );
					}
				} else if(itemMenu.toUpperCase().equals("AUTOR")){
					if(livro.getAutor().contains( texto )){
						listLivros.add( livro );
					}
				} else if(itemMenu.toUpperCase().equals("EDITORA")){
					if(livro.getEditora().contains( texto )){
						listLivros.add( livro );
					}
				} else if(itemMenu.toUpperCase().equals("CATEGORIA")){
					if(livro.getCategoria().contains( texto )){
						listLivros.add( livro );
					}
				}
			}
			if( listLivros.size() > 0 && !(itemMenu.isEmpty()) ){
				if (janelaLista == null){
					janelaLista = new FrmLista(itemMenu, texto, listLivros);
					janelaLista.setVisible(true);
				} else {
					janelaLista.setVisible(true);
					janelaLista.setState(JFrame.NORMAL);					
				}
				janelaPrincipal.dispose();
			} else if( !(itemMenu.isEmpty()) ){
				JOptionPane.showMessageDialog(janelaPrincipal, "Nenhum resultado encontrado!");
			} else {
				JOptionPane.showMessageDialog(janelaPrincipal, "Digite algo ou escolha uma opção!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); //verifica qual botÃ£o estÃ¡ solicitando a aÃ§Ã£o
		if(source == btnProcurar){
			procurarLivro(cbItensPesquisa.getSelectedItem().toString().toUpperCase(), 
					txtPesquisa.getText().toUpperCase());
			//janelaPesquisa.setVisible(false);
			return;
		} else{
			if(source == btnCarrinho){
				FrmCarrinho frame = new FrmCarrinho();
				frame.setVisible(true);
				return;
			}
		}
	}
}

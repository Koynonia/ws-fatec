/**
 * @author Fernando Moraes Oliveira e Vitor Fagundes Arantes
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 06/04/2016
 */

package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import entity.Livro;;

public class ArquivoLivro implements Arquivo {
	private StringBuffer buffer;

	public String getBuffer() {		
		return buffer.toString();
	}
	
	@Override
	public void lerArquivo(String diretorio, String arquivo) throws IOException {
		
		File arq = new File(diretorio, arquivo);
		if (arq.exists()) {
			FileInputStream leFluxo = new FileInputStream(arq);
			InputStreamReader leDados = new InputStreamReader(leFluxo);
			BufferedReader bufferLeitura = new BufferedReader(leDados);
			String linha = bufferLeitura.readLine();
			buffer = new StringBuffer();
			while (linha != null) {
//				System.out.println(linha);
				buffer.append(linha);
				buffer.append(";");
				linha = bufferLeitura.readLine();
			}
			bufferLeitura.close();
			leDados.close();
			leFluxo.close();
		} else {
			//throw new IOException("Arquivo inexistente");
			File novoArquivo = new File(diretorio, arquivo);
			novoArquivo.createNewFile();
			buffer = new StringBuffer();
			buffer.append("");
		}
	}
	
	@Override
	public void escreverArquivo(String diretorio, String arquivo, String texto, Object object) throws IOException {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("ISBN            : " + ( (Livro) object).getIsbn() );
		buffer.append("\r\n");
		buffer.append("Título          : " + ( (Livro) object).getTitulo() );
		buffer.append("\r\n");
		buffer.append("Autor           : " + ( (Livro) object).getAutor() );
		buffer.append("\r\n");
		buffer.append("Editora         : " + ( (Livro) object).getEditora() );
		buffer.append("\r\n");
		buffer.append("Data Publicação : " + ( (Livro) object).getDtPublicacao() );
		buffer.append("\r\n");
		buffer.append("Capa            : " + ( (Livro) object).getCapa() );
		buffer.append("\r\n");
		buffer.append("Páginas         : " + ( (Livro) object).getPaginas() );
		buffer.append("\r\n");
		buffer.append("Categoria       : " + ( (Livro) object).getCategoria() );
		buffer.append("\r\n");
		buffer.append("Sumário         : " + ( (Livro) object).getSumario() );
		buffer.append("\r\n");
		buffer.append("Resumo          : " + ( (Livro) object).getResumo() );
		buffer.append("\r\n");
		buffer.append("Preço Custo     : " + ( (Livro) object).getPrecoCusto() );
		buffer.append("\r\n");
		buffer.append("Preço Venda     : " + ( (Livro) object).getPrecoVenda() );
		buffer.append("\r\n");
		buffer.append("Imagem          : " + ( (Livro) object).getImagem() );
		buffer.append("\r\n");
		buffer.append("Data Cadastro   : " + ( (Livro) object).getDtCadastro() );
		buffer.append("\r\n");
		buffer.append("Data Alterado   : " + ( (Livro) object).getDtAlterado() );
		buffer.append("\r\n");
		buffer.append("--------------------------------------");
		buffer.append("\r\n");
		File arq = new File(diretorio, arquivo);
		boolean arquivoExiste;
		if (arq.exists()) {
			arquivoExiste = true;
		} else {
			arq.createNewFile();
			arquivoExiste = false;
		}
		FileWriter escreveArquivo = new FileWriter(arq, arquivoExiste);
		PrintWriter gravaDados = new PrintWriter(escreveArquivo);
		gravaDados.write(buffer.toString());
		gravaDados.flush();
		gravaDados.close();
		escreveArquivo.close();
	}


	@Override
	public void lerDiretorio(String diretorio) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void excluirDadosArquivo(String diretorio, String arquivo,
			String[] registro) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
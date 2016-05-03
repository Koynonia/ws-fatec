/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 11/04/2016
 */

package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import entity.Carrinho;

public class ArquivoCarrinho implements Arquivo {
	private StringBuffer buffer;

	public String getBuffer() {		
		return buffer.toString();
	}

	
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

	public void escreverArquivo(String diretorio, String arquivo, String texto, Object object) throws IOException {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Livro ISBN    : " + ((Carrinho) object).getCarrinho().getIsbn());
		buffer.append("\r\n");
		buffer.append("Quantidade    : " + ((Carrinho) object).getQuantidade());
		buffer.append("\r\n");
		buffer.append("Desconto      : " + ((Carrinho) object).getDesconto());
		buffer.append("\r\n");
		buffer.append("Data Cadastro : " + ((Carrinho) object).getDtCadastro());
		buffer.append("\r\n");
		buffer.append("------------------------------------");
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
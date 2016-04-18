package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import entity.Carrinho;

public class ArquivoCarrinho {

	private StringBuffer buffer;

	public String getBuffer() {
		
		return buffer.toString();
	}

	
	public void leArquivo(String diretorio, String arquivo) throws IOException {
		
		File arq = new File(diretorio, arquivo);
		if (arq.exists()) {
			FileInputStream leFluxo = new FileInputStream(arq);
			InputStreamReader leDados = new InputStreamReader(leFluxo);
			BufferedReader bufferLeitura = new BufferedReader(leDados);
			String linha = bufferLeitura.readLine();
			buffer = new StringBuffer();
			while (linha != null) {
				System.out.println(linha);
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

	public void escreveArquivo(String diretorio, String arquivo, String texto, Object object) throws IOException {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Quantidade : " + ((Carrinho) object).getQuantidade());
		buffer.append("\r\n");
		buffer.append("Preço      : " + ((Carrinho) object).getPreco());
		buffer.append("\r\n");
		buffer.append("Desconto   : " + ((Carrinho) object).getDesconto());
		buffer.append("\r\n");
		buffer.append("Livro      : " + ((Carrinho) object).getLivro());
		buffer.append("\r\n");
		buffer.append("---------------------------");
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
}
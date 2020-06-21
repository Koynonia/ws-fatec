package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.ArquivosICtrl;
import model.EmprestimoMdl;

public class EmprestimoFile implements ArquivosICtrl {
	StringBuffer buffer;

	public String getBuffer() {
		return buffer.toString();
	}

	@Override
	public void leArquivo(String diretorio, String arquivo) throws IOException {
		File arq = new File(diretorio, arquivo);
		if (arq.exists()) {
			FileInputStream leFluxo = new FileInputStream(arq);
			InputStreamReader leDados = new InputStreamReader(leFluxo);
			BufferedReader bufferLeitura = new BufferedReader(leDados);
			String linha = bufferLeitura.readLine();
			buffer = new StringBuffer();
			while (linha != null) {
				// System.out.println(linha);
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
	public void escreveArquivo(String diretorio, String arquivo, String texto, Object object) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ID             : " + ((EmprestimoMdl) object).getId());
		buffer.append("\r\n");
		buffer.append("ID Obra        : " + ((EmprestimoMdl) object).getObraId());
		buffer.append("\r\n");
		buffer.append("Nome Obra      : " + ((EmprestimoMdl) object).getObra());
		buffer.append("\r\n");
		buffer.append("Artista        : " + ((EmprestimoMdl) object).getArtista());
		buffer.append("\r\n");
		buffer.append("Destino        : " + ((EmprestimoMdl) object).getDestino());
		buffer.append("\r\n");
		buffer.append("Data Inicial   : " + ((EmprestimoMdl) object).getDataInicial());
		buffer.append("\r\n");
		buffer.append("Data Final     : " + ((EmprestimoMdl) object).getDataFinal());
		buffer.append("\r\n");
		buffer.append("ID Museu       : " + ((EmprestimoMdl) object).getMuseuId());
		buffer.append("\r\n");
		buffer.append("Museu          : " + ((EmprestimoMdl) object).getMuseu());
		buffer.append("\r\n");
		buffer.append("ID Responsável : " + ((EmprestimoMdl) object).getResponsavelId());
		buffer.append("\r\n");
		buffer.append("Responsável    : " + ((EmprestimoMdl) object).getResponsavel());
		buffer.append("\r\n");
		buffer.append("Custo          : " + ((EmprestimoMdl) object).getCusto());
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

	@Override
	public void leDiretorio(String diretorio) throws IOException {

	}

	@Override
	public void excluiDadosArquivo(String diretorio, String arquivo, String[] registro) throws IOException {

	}
}
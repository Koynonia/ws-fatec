package dao;

import java.io.IOException;

public interface Arquivo {

	public void lerArquivo(String diretorio, String arquivo) throws IOException;
	public void escreverArquivo(String diretorio, String arquivo, String texto,  Object object) throws IOException;
	public void lerDiretorio(String diretorio) throws IOException; 
	public void excluirDadosArquivo(String diretorio,String arquivo, String registro[]) throws IOException;
	
}

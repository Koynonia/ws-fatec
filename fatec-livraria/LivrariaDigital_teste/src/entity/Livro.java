/**
 * @author Fernando Moraes Oliveira e Vitor Fagundes Arantes
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 06/04/2016
 */

package entity;

public class Livro {
	private String isbn;
	private String titulo;
	private String autor;
	private String editora;
	private String dtPublicacao;
	private String capa;
	private int paginas;
	private String categoria;
	private String sumario;
	private String resumo;
	private String imagem;
	private float precoCusto;
	private float precoVenda;
	private String dtCadastro;
	private String dtAlterado;
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getDtPublicacao() {
		return dtPublicacao;
	}
	public void setDtPublicacao(String dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}
	public String getCapa() {
		return capa;
	}
	public void setCapa(String capa) {
		this.capa = capa;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSumario() {
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public float getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(float precoCusto) {
		this.precoCusto = precoCusto;
	}
	public float getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}
	public String getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public String getDtAlterado() {
		return dtAlterado;
	}
	public void setDtAlterado(String dtAlterado) {
		this.dtAlterado = dtAlterado;
	}
}

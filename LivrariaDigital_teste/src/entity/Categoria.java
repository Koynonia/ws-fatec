package entity;

public class Categoria {
	private String nome;
	private String dtCadastro;
	private String dtAlterado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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

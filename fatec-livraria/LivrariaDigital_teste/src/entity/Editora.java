package entity;

public class Editora {
	private String nome;
	private String cnpj;
	private String endereco;
	private String dtCadastro;
	private String dtAlterado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

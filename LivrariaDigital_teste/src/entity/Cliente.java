package entity;

public class Cliente {
	private String usuario; 
	private String senha; 
	private String email; 
	private String nome; 
	private String dtNasc; 
	private String cpf; 
	private String telefone; 
	private String imagem;
	private String tipoEndereco; 
	private String nivel; 
	private String dtCadastro;
	private String dtAlterado;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(String dtNasc) {
		this.dtNasc = dtNasc;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getTipoEndereco() {
		return tipoEndereco;
	}
	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
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

package entity;

public class Autor {
	private String nome;
	private String dtNasc;
	private String dtFalec;
	private String localNasc;
	private String localFalec;
	private String biografia;
	private String dtCadastro;
	private String dtAlterado;
	
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

	public String getDtFalec() {
		return dtFalec;
	}

	public void setDtFalec(String dtFalec) {
		this.dtFalec = dtFalec;
	}

	public String getLocalNasc() {
		return localNasc;
	}

	public void setLocalNasc(String localNasc) {
		this.localNasc = localNasc;
	}

	public String getLocalFalec() {
		return localFalec;
	}

	public void setLocalFalec(String localFalec) {
		this.localFalec = localFalec;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
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

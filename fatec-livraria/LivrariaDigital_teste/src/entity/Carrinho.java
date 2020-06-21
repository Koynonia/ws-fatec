package entity;


public class Carrinho {

	private int quantidade;
	private float desconto;
	private Livro carrinho;
	private String dtCadastro;

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getDesconto() {
		return desconto;
	}
	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}
	public Livro getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Livro carrinho) {
		this.carrinho = carrinho;
	}
	public String getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}

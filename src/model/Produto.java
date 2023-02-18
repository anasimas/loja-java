package model;

public class Produto {

	private double preco;
	private String descricao;
	private int quantidade;
	private String codigo;

	// ------------------------------- getters e setters -------------------------------//

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	// ------------------------------- fim getters e setters// -------------------------------//
	
	public Produto novoProduto(String descricao, String codigo, int quantidade, double valor) {
		
		this.setDescricao(descricao);

		this.setCodigo(codigo);

		this.setPreco(valor);
		
		this.setQuantidade(quantidade);

		return this;	
	}
}

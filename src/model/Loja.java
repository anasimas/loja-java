package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.time.*;
import view.*;

public class Loja {

	private ArrayList<Produto> estoque = new ArrayList<Produto>(); // adicionar o "= new ArrayList" é equivalente à
																	// inicializar uma variável. É necessário para o
																	// array não ser uma referência nula
	private ArrayList<Produto> listaDeProdutosCadastrados = new ArrayList<Produto>();
	
	private ArrayList<Cupom> vendas = new ArrayList<Cupom>();

	// ------------------------------- getters e setters -------------------------------//

	public ArrayList<Produto> getEstoque() {
		return estoque;
	}

	public void setEstoque(ArrayList<Produto> estoque) {
		this.estoque = estoque;
	}

	public ArrayList<Produto> getListaDeProdutosCadastrados() {
		return listaDeProdutosCadastrados;
	}

	public void setListaDeProdutosCadastrados(ArrayList<Produto> listaDeProdutosCadastrados) {
		this.listaDeProdutosCadastrados = listaDeProdutosCadastrados;
	}

	public ArrayList<Cupom> getVendas() {
		return vendas;
	}

	public void setVendas(ArrayList<Cupom> vendas) {
		this.vendas = vendas;
	}

	// ------------------------------- fim getters e setters-------------------------------//

	public void novoProduto(Produto produto) {
		listaDeProdutosCadastrados.add(produto);
		estoque.add(produto);
	}

	public void entradaDeEstoque(String codigoDoProdutoEntrada, int quantidadeAdicionada) {
			Produto produto = this.validacaoProdutoExistente(codigoDoProdutoEntrada);
			
			if(produto != null) {
				produto.setQuantidade(produto.getQuantidade() + quantidadeAdicionada);
			}
	}
	
	public Produto validacaoProdutoExistente(String codigoDoProdutoEntrada){
		Produto produtoRetornado = null;
		
		for (Produto produto : this.estoque) {
			if (produto.getCodigo().equals(codigoDoProdutoEntrada)) {
				produtoRetornado = produto;
			}
		}
		
		if(produtoRetornado == null) {
				View.msgProdutoNaoEncontradoEstoque(this, codigoDoProdutoEntrada, 0);
		}
		
		return produtoRetornado;
	}

	public void buscarEstoque(String codigoDoProduto) {
		Produto produto = this.validacaoProdutoExistente(codigoDoProduto);
		
		if(produto != null) {
			View.msgResultadoBuscaEstoque(produto.getQuantidade(), produto.getDescricao());
		}
	}
	
	public void vender(String codigoProdutoVendido, int quantidadeVendida) {
		Produto produto = this.validacaoProdutoExistente(codigoProdutoVendido);
		
		if(produto != null) {
			
			if(produto.getQuantidade() == 0 || (produto.getQuantidade() - quantidadeVendida) <= 0) {
				View.msgErroSemEstoque();
			} else { 
				produto.setQuantidade(produto.getQuantidade() - quantidadeVendida);
				double valorVenda = (produto.getPreco() * quantidadeVendida);
				
				LocalDate dataVenda = LocalDate.now();				
				Cupom cupomNovo = new Cupom();
				
				cupomNovo.setCodigoProduto(codigoProdutoVendido);
				cupomNovo.setQuantidadeVendida(quantidadeVendida);
				cupomNovo.setValorVenda(valorVenda);
				cupomNovo.setDataVenda(dataVenda);
				
				vendas.add(cupomNovo);
				
				this.gerarCupomPorVenda(produto, cupomNovo);
			}
		}
		
		}
	
	public String verCupons() {
		
		String totalDeCupons = "Vendas: ";
		double vendaTotal = 0;
		
		for(Cupom cupons : vendas) {
			vendaTotal += cupons.getValorVenda();
			totalDeCupons += "\nProduto: "+cupons.getCodigoProduto()+"\nQuantidade: "+cupons.getQuantidadeVendida()+"\nValor da venda: "+cupons.getValorVenda()+"\nData da venda: "+cupons.getDataVenda()+"\n";
		}
		
		String stringValorVenda = NumberFormat.getCurrencyInstance().format(vendaTotal);
		totalDeCupons += "\nTotal de vendas: "+ vendaTotal;
		
		return totalDeCupons;
	}
	
	public String gerarCupomPorVenda(Produto produtoVendido, Cupom cupomVenda) {
		String cupom = null;
		
		cupom = "\nProduto: "+produtoVendido.getDescricao()+"\nQuantidade: "+produtoVendido.getQuantidade()+"\nValor da venda: "+cupomVenda.getValorVenda()+"\nData da venda: "+cupomVenda.getDataVenda()+"\n";
		
		return cupom;
	}
	
	public String listarProdutosCadastrados(){
		String lista = "Produtos";
		for (Produto produto : listaDeProdutosCadastrados) {
			// preço, descrição, qtd, codigo
			String valorDoProduto = NumberFormat.getCurrencyInstance().format(produto.getPreco());
			lista += "\nDescrição: " + produto.getDescricao() + "\nCódigo: " + produto.getCodigo() + "\nPreço: "
					+ valorDoProduto + "\nQuantidade em estoque: " + produto.getQuantidade()+"\n";
		}
		return lista;
	}
	}

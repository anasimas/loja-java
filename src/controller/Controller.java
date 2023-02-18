package controller;
import model.*;
import view.*;

public class Controller {

	Loja loja = new Loja();
	
	public void exibeMenu() {
		int opcao = 0;
		do {
			opcao =  View.opcaoMenuControladora();
			
			switch(opcao) {
	
			case 0: //cadastrar produto
				Produto produto = new Produto();
				View.msgCadastroDeProduto(loja, produto, null, 0);
			break;
			
			case 1: //listar produtos cadastrados
				View.produtosCadastrados(loja);
			break;
			
			case 2: //vendas ou cupons
				View.verCupons(loja);
			break;
			
			case 3: //estoque
				int opcaoEstoque = View.opcoesEstoque();
				do {
					switch(opcaoEstoque) {
					case 0: //dar entrada no estoque
						View.msgEntradaDeEstoque(loja);
					break;
					
					case 1: //verificar quantidades
						View.msgVerificarQuantidadesEstoque(loja);
					break;
					}
				} while(opcao!=3); //tem que ser 3 porque tem a opção "sair" na posição 2 do JComboBox na view
			break;
			
			case 4: //vender
				View.vender(loja);
			break;
			
			case 5:
				System.exit(0);
			break;
			}
		} while(opcao!=6);
	}
}

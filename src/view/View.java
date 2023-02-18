package view;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.*;

public class View {

	public static int opcaoMenuControladora() {
		String[] opcoesDoMenu = { "Cadastrar produtos", "Listar produtos cadastrados", "Vendas", "Estoque", "Vender",
				"Sair" };
		JComboBox<String> menu = new JComboBox<String>(opcoesDoMenu);
		JOptionPane.showMessageDialog(null, menu);

		return menu.getSelectedIndex();
	}

	public static void msgCadastroDeProduto(Loja loja, Produto produto, String codigo, int quantidade) { //serve para adicionar um novo produto do zero ou quando o código não é encontrado na busca do estoque
		
		if (codigo == null) {
			codigo = JOptionPane.showInputDialog("Informe um código para o produto: ");
		}
		
		if (quantidade == 0) {
			do {
				quantidade = (Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade em estoque: ")));
			}while(quantidade <= 0);
		}
		
		double valor = 0;
		do {
			valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do produto: "));
		} while(valor <= 0);
		
		Produto novoProduto = produto.novoProduto(JOptionPane.showInputDialog("Informe a descrição do produto: "), codigo, quantidade, valor);
		loja.novoProduto(novoProduto);
	}

	public static void produtosCadastrados(Loja loja) {
		// lista de produtos, não é o estoque.
		String listaCompleta = loja.listarProdutosCadastrados();
		JOptionPane.showMessageDialog(null, listaCompleta);
	}

	public static int opcoesEstoque() {
		String[] opcoesDoMenu = { "Dar entrada no estoque", "Ver quantidades", "Sair" };
		JComboBox<String> menu = new JComboBox<String>(opcoesDoMenu);
		JOptionPane.showMessageDialog(null, menu);

		return menu.getSelectedIndex();
	}

	public static void msgEntradaDeEstoque(Loja loja) {
		int quantidade = 0;
		String codigoProd = JOptionPane.showInputDialog("Informe o código do produto que deseja dar entrada no estoque: ");
		
		do {
			quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade a ser adicionada: "));
		}while(quantidade <= 0);
		
		loja.entradaDeEstoque(codigoProd, quantidade);
	}

	public static void msgProdutoNaoEncontradoEstoque(Loja loja, String codigoDigitado, int quantidadeDigitada) { //busca para dar entrada no estoque
		String[] opcoesDoMenu = { "Adicionar novo produto com o código ao estoque", "Buscar outro código", "Sair" };
		JComboBox<String> menu = new JComboBox<String>(opcoesDoMenu);
		JOptionPane.showMessageDialog(null, menu, "Produto não encontrado, selecione uma das opções",
				JOptionPane.WARNING_MESSAGE);
		int resposta = menu.getSelectedIndex();

		do {
			switch (resposta) {
			case 0:
				Produto novoProduto = new Produto();
				View.msgCadastroDeProduto(loja, novoProduto, codigoDigitado, quantidadeDigitada);
				break;
			case 1:
				View.opcoesEstoque();
				break;
			}
		} while (resposta > 1 && resposta < 0);
	}
	
	public static void msgVerificarQuantidadesEstoque(Loja loja) {
		loja.buscarEstoque(
				JOptionPane.showInputDialog("Informe o código do produto que deseja verificar: "));
	}
	
	public static void msgResultadoBuscaEstoque(int quantidade, String descricaoDoProduto) {
		JOptionPane.showMessageDialog(null, "Quantidade total em estoque: "+quantidade+"\nProduto: "+descricaoDoProduto);
	}
	
	public static void msgErroQtdInvalida() {
		JOptionPane.showMessageDialog(null, "Quantidade vendida inválida");
	}
	
	public static void msgErroSemEstoque() {
		JOptionPane.showMessageDialog(null, "Estoque indisponível");
	}
	
	public static void vender(Loja loja){
		//precisa ir em loja retirar do estoque, mas antes precisa validar se existe em estoque
		int quantidade = 0;
		String codigo = JOptionPane.showInputDialog("Informe o código do produto que deseja vender: ");
		
		do {
			quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade a ser vendida: "));
		} while(quantidade <= 0);
		
		loja.vender(codigo,quantidade);	
	}

	public static void verCupons(Loja loja) {	
		String totalDeCupons = loja.verCupons();
		JOptionPane.showMessageDialog(null, totalDeCupons);
	}
}

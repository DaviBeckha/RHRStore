package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainCompras {

    private static final List<Produto> produtos = new ArrayList<>();
    private static final Carrinho carrinho = new Carrinho();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        inicializarProdutos();


        Cliente cliente = cadastrarCliente();
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Listar produtos disponíveis");
            System.out.println("2. Buscar produto por nome");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Finalizar compra");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();


            switch (opcao) {
                case 1:
                    listarProdutos();
                    adicionarAoCarrinho();
                    break;
                case 2:
                    buscarProduto();
                    break;
                case 3:
                    gerenciarCarrinho();
                    break;
                case 4:
                    finalizarCompra(cliente);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void inicializarProdutos() {
        //Como pedido foi pré-cadastrados para facilitar a utilização
        Produto p1 = new Produto();
        p1.nome = "Notebook";
        p1.preco = 3500.00;
        p1.desc = "Notebook acer nitro";
        p1.quantidade = 10;

        Produto p2 = new Produto();
        p2.nome = "Smartphone";
        p2.preco = 1500.00;
        p2.desc = "Smartphone motorola edge 40";
        p2.quantidade = 15;

        Produto p3 = new Produto();
        p3.nome = "Fone de Ouvido";
        p3.preco = 250.00;
        p3.desc = "Fone Bluetooth";
        p3.quantidade = 20;

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);
    }

    private static Cliente cadastrarCliente() {
        System.out.println("\nCadastro do cliente");
        Cliente cliente = new Cliente();

        System.out.print("Nome: ");
        cliente.nome = sc.nextLine();

        System.out.print("Email: ");
        cliente.email = sc.nextLine();

        System.out.print("CPF: ");
        cliente.cpf = sc.nextLine();

        return cliente;
    }

    private static void listarProdutos() {
        System.out.println("\nProdutos disponíveis !");
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.println((i + 1) + ". " + p.nome + " - R$" + p.preco);
            System.out.println("   " + p.desc);
            System.out.println("   Quantidade disponível: " + p.quantidade);
            System.out.println();
        }
    }

    private static void adicionarAoCarrinho() {
        System.out.print("\nDigite o número do produto para adicionar ao carrinho (0 para voltar): ");
        int escolha = sc.nextInt();


        if (escolha > 0 && escolha <= produtos.size()) {
            Produto produtoEscolhido = produtos.get(escolha - 1);

            System.out.print("Quantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            if (quantidade <= produtoEscolhido.quantidade) {
                //Aqui oque eu to fazendo é criando uma cópia para o carrinho
                //Motivo disso é que eu pensei que seria melhor para quando ele for finalizar a compra
                //Assim ele quando for finalizar a compra o carrinho some e fica somente o pedido.
                Produto produtoCarrinho = new Produto();
                produtoCarrinho.nome = produtoEscolhido.nome;
                produtoCarrinho.preco = produtoEscolhido.preco;
                produtoCarrinho.desc = produtoEscolhido.desc;
                produtoCarrinho.quantidade = quantidade;

                carrinho.adicionarProdutos(produtoCarrinho);
                System.out.println("Produto adicionado ao carrinho!");
            } else {
                System.out.println("Quantidade indisponível em estoque!");
            }
        }
    }

    private static void buscarProduto() {
        System.out.print("\nDigite o nome do produto que deseja buscar: ");
        String busca = sc.nextLine().toLowerCase(); // tudo letra minuscula

        System.out.println("\nResultado da busca: ");
        boolean encontrado = false;

        for (Produto p : produtos) {
            if (p.nome.toLowerCase().contains(busca)) {
                System.out.println(p.nome + " - R$" + p.preco);
                System.out.println("   " + p.desc);
                System.out.println("   Quantidade disponível: " + p.quantidade);
                System.out.println();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum produto encontrado com esse nome.");
        } else {
            adicionarAoCarrinho();
        }
    }

    private static void gerenciarCarrinho() {
        System.out.println("\nSEU CARRINHO");
        carrinho.listarProdutos();

        System.out.println("1. Remover produto");
        System.out.println("2. Ajustar quantidade");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");

        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {
            System.out.print("Digite o nome do produto a ser removido: ");
            String nomeProduto = sc.nextLine();

            for (Produto p : carrinho.produtos) {
                if (p.nome.equalsIgnoreCase(nomeProduto)) {
                    carrinho.removerProdutos(p);
                    System.out.println("Produto removido do carrinho!");
                    break;
                }
            }
        } else if (opcao == 2) {
            System.out.print("Digite o nome do produto para ajustar a quantidade: ");
            String nomeProduto = sc.nextLine();

            for (Produto p : carrinho.produtos) {
                if (p.nome.equalsIgnoreCase(nomeProduto)) {
                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = sc.nextInt();


                    for (Produto disponivel : produtos) {
                        if (disponivel.nome.equalsIgnoreCase(nomeProduto)) {
                            if (novaQuantidade <= disponivel.quantidade) {
                                p.quantidade = novaQuantidade;
                                System.out.println("Quantidade ajustada!");
                            } else {
                                System.out.println("Quantidade indisponível em estoque!");
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    private static void finalizarCompra(Cliente cliente) {
        if (carrinho.produtos.isEmpty()) {
            System.out.println("Seu carrinho está vazio!");
            return;
        }

        System.out.println("\nFinalizando compra");


        System.out.println("Itens no carrinho:");
        carrinho.listarProdutos();


        double total = 0;
        for (Produto p : carrinho.produtos) {
            total += p.preco * p.quantidade;
        }
        System.out.println("Total da compra: R$" + total);


        System.out.println("\nFormas de pagamento:");
        System.out.println("1. Pix");
        System.out.println("2. Boleto");
        System.out.println("3. Cartão");
        System.out.print("Escolha a forma de pagamento: ");
        int formaPagamento = sc.nextInt();


        
        Pedido pedido = new Pedido();
        pedido.cliente = cliente;
        pedido.produtos = carrinho.produtos;
        pedido.formaPagamento = FormaPagamento.porCodigo(formaPagamento);


        System.out.println("\n=== RESUMO DO PEDIDO ===");
        System.out.println("Cliente: " + cliente.nome);
        System.out.println("Forma de pagamento: " + pedido.formaPagamento);
        System.out.println("Total: R$" + total);
        System.out.print("\nConfirmar compra (S/N)? ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            System.out.println("\nCompra finalizada com sucesso!");
            System.out.println("Obrigado por comprar conosco, " + cliente.nome + "!");


            carrinho.produtos.clear();
        } else {
            System.out.println("Compra cancelada.");
        }
    }
}
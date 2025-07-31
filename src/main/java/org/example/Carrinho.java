package org.example;

import java.util.ArrayList;

public class Carrinho {
    ArrayList<Produto> produtos = new ArrayList<>();
    FormaPagamento formaPagamento;


    public void adicionarProdutos(Produto produto) {
        produtos.add(produto);
    }

    public void removerProdutos(Produto produto) {


        produtos.remove(produto);
    }



    public void listarProdutos() {
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.nome);
            System.out.println("Preço: R$" + produto.preco);
            System.out.println("Descrição: " + produto.desc);
            System.out.println("Quantidade: " + produto.quantidade);
            System.out.println();
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.preco * produto.quantidade;
        }
        return total;
    }

}

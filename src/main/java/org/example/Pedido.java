package org.example;

import java.util.ArrayList;

public class Pedido {
    Cliente cliente;
    ArrayList<Produto> produtos = new ArrayList<>();
    FormaPagamento formaPagamento;


    public void gerarResumo() {
        System.out.println("=== RESUMO DO PEDIDO ===");
        System.out.println("Cliente: " + cliente.nome);
        System.out.println("Forma de pagamento: " + formaPagamento);

        double total = 0;
        System.out.println("\nItens:");
        for (Produto p : produtos) {
            System.out.println(p.nome + " - " + p.quantidade + " x R$" + p.preco + " = R$" + (p.preco * p.quantidade));
            total += p.preco * p.quantidade;
        }

        System.out.println("\nTotal: R$" + total);
    }
}

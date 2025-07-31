package org.example;

public enum FormaPagamento {
    PIX(1),
    BOLETO(2),
    CARTAO(3);

    private final int codigo;

    FormaPagamento(int codigo) {
        this.codigo = codigo;
    }

    public static FormaPagamento porCodigo(int codigo) {
        for (FormaPagamento f : FormaPagamento.values()) {
            if (f.getCodigo() == codigo) {
                return f;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

    public int getCodigo() {
        return codigo;
    }
}

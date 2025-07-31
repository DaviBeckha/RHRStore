package org.example;

public enum Categorias {

    NOTEBOOK(1),
    CELULAR(2),
    FONES(3);

    private final int codCategoria;

    Categorias(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public static Categorias porCodigo(int codCategoria) {
        for (Categorias c : Categorias.values()) {
            if (c.getCodCategoria() == codCategoria) {
                return c;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codCategoria);
    }

    public int getCodCategoria() {
        return codCategoria;
    }
}

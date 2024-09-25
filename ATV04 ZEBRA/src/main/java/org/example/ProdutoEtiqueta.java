package org.example;

public class ProdutoEtiqueta {
    private String nome;
    private double precoPacote;
    private double precoCaixa;
    private String codigoBarras;

    public ProdutoEtiqueta(String nome, double precoPacote, double precoCaixa, String codigoBarras) {
        this.nome = nome;
        this.precoPacote = precoPacote;
        this.precoCaixa = precoCaixa;
        this.codigoBarras = codigoBarras;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoPacote() {
        return precoPacote;
    }

    public double getPrecoCaixa() {
        return precoCaixa;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }
}

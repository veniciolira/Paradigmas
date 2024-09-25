package org.example;

public class Main {
    public static void main(String[] args) {
        ProdutoEtiqueta batataFrita = new ProdutoEtiqueta("Batata Frita Menino Trakino", 25.00, 150.00, "1234567890");
        String etiquetaZPL = GeradorEtiquetaZebra.gerarEtiquetaZPL(batataFrita);
        System.out.println(etiquetaZPL);
    }
}

package org.example;

public class GeradorEtiquetaZebra {

    public static String gerarEtiquetaZPL(ProdutoEtiqueta produto) {
        String templateZPL = "^XA\n" +
                "^CF0,60\n" +
                "^FO50,50^FD" + produto.getNome() + "^FS\n" +
                "^CFA,50\n" +
                "^FO50,200^FD Pacote 1kg:^FS\n" + // Removido os parênteses
                "^FO480,200^FD R$ " + String.format("%.0f", produto.getPrecoPacote()) + "^FS\n" + // Preço do pacote sem casas decimais
                "^FO50,280^FD Caixa 10kg:^FS\n" + // Removido os parênteses
                "^FO480,280^FD R$ " + String.format("%.0f", produto.getPrecoCaixa()) + "^FS\n" + // Preço da caixa sem casas decimais
                "^BY5,2,270\n" +
                "^FO100,450^BC^FD" + produto.getCodigoBarras() + "^FS\n" +
                "^XZ";
        return templateZPL;
    }
}

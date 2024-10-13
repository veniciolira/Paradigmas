import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ProcessadorDespesas {
    private String caminhoDoArquivo = "src\\despesas.csv";
    private String linhaAtual = "";
    private HashMap<String, Double> mapaDespesas = new HashMap<>();
    private boolean ignorarCabecalho = true;
    private double totalDespesas = 0;

    public double getTotalDespesas() {
        return this.totalDespesas;
    }

    public String processarDespesas() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            while ((linhaAtual = leitor.readLine()) != null) {
                if (ignorarCabecalho) {
                    ignorarCabecalho = false; // Pula a primeira linha
                    continue;
                }
                String data = linhaAtual.split(",")[0].split(" ")[0];
                double valor = Double.parseDouble(linhaAtual.split(",")[1].replace("\"", "").trim());
                totalDespesas += valor;

                mapaDespesas.merge(data, valor, Double::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Despesas Detalhadas: " + mapaDespesas + " \nTotal de Despesas: " + totalDespesas;
    }
}

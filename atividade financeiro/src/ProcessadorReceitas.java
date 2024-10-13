import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ProcessadorReceitas {
    private String caminhoDoArquivo = "src\\main\\java\\ThreadsFinanceiro\\dataBase\\receitas.csv";
    private String linhaAtual = "";
    private HashMap<String, Double> mapaReceitas = new HashMap<>();
    private boolean ignorarCabecalho = true;
    private double totalReceitas = 0;

    public double getTotalReceitas() {
        return this.totalReceitas;
    }

    public String processarReceitas() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            while ((linhaAtual = leitor.readLine()) != null) {
                if (ignorarCabecalho) {
                    ignorarCabecalho = false; // Pula a primeira linha
                    continue;
                }
                String data = linhaAtual.split(",")[0].split(" ")[0];
                double valor = Double.parseDouble(linhaAtual.split(",")[1].replace("\"", "").trim());
                totalReceitas += valor;

                mapaReceitas.merge(data, valor, Double::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Receitas Detalhadas: " + mapaReceitas + " \nTotal de Receitas: " + totalReceitas;
    }
}

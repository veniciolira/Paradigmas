import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ProcessadorProvisao {
    private String caminhoDoArquivo = "src\\provisao.csv";
    private String linhaAtual = "";
    private HashMap<String, Double> mapaProvisao = new HashMap<>();
    private boolean ignorarCabecalho = true;
    private double totalProvisoes = 0;

    public String processarProvisao() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            while ((linhaAtual = leitor.readLine()) != null) {
                if (ignorarCabecalho) {
                    ignorarCabecalho = false; // Pula a primeira linha
                    continue;
                }
                String data = linhaAtual.split(",")[0].split(" ")[0];
                double valor = Double.parseDouble(linhaAtual.split(",")[1].replace("\"", "").trim());
                totalProvisoes += valor;

                mapaProvisao.merge(data, valor, Double::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Provisões Detalhadas: " + mapaProvisao + " \nTotal de Provisões: " + totalProvisoes;
    }
}

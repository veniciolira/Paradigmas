package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LeitorReceitas {
    String caminhoArquivo = "C://Users//Vinicius//Desktop//paradigmas//paradigmas//src//main//java//org//example//files//receitas.csv"; // Caminho atualizado
    String linha = "";
    HashMap<String, Double> receita = new HashMap<>();
    boolean primeiraLinha = true;
    double receitasTotais = 0;

    public double somarReceitas() {
        return this.receitasTotais;
    }

    public String receitas() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false; // Ignora a primeira linha
                    continue;
                }
                String data = linha.split(",")[0].split(" ")[0];
                double valorReceitas = Double.parseDouble(linha.split(",")[1].replace("\"", "").trim());
                receitasTotais += valorReceitas;

                if (receita.containsKey(data)) {
                    double valorAntigo = receita.get(data);
                    double valorAtualizado = valorAntigo + valorReceitas;
                    receita.put(data, valorAtualizado);
                } else {
                    receita.put(data, valorReceitas);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Receitas: " + receita + " \nTotal das Receitas: " + receitasTotais;
    }
}

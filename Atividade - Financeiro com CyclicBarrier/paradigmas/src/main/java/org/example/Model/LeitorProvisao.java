package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class LeitorProvisao implements Runnable {
    private String caminhoArquivo;
    private List<Double> provisoes = new ArrayList<>();
    private CyclicBarrier barrier;

    public LeitorProvisao(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            br.readLine(); // Ignora o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                for (String dado : dados) {
                    dado = dado.replace("\"", "").trim();
                    try {
                        double valorNumerico = Double.parseDouble(dado);
                        provisoes.add(valorNumerico);
                    } catch (NumberFormatException e) {
                        // Ignora valores que não são numéricos
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public double getTotal() {
        return provisoes.stream().mapToDouble(Double::doubleValue).sum();
    }
}
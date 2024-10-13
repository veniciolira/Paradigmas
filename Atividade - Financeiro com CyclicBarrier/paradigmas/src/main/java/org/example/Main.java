package org.example;

import org.example.Model.LeitorDespesas;
import org.example.Model.LeitorProvisao;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        String caminhoDespesas = "C://Users//Vinicius//Desktop//paradigmas//paradigmas//src//main//java//org//example//files//receitas.csv";
        String caminhoProvisao = "C://Users//Vinicius//Desktop//paradigmas//paradigmas//src//main//java//org//example//files//provisao.csv";

        LeitorDespesas leitorDespesas = new LeitorDespesas(caminhoDespesas);
        LeitorProvisao leitorProvisao = new LeitorProvisao(caminhoProvisao);

        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            double totalDespesas = leitorDespesas.getTotal();
            double totalProvisoes = leitorProvisao.getTotal();

            System.out.println("Total das Despesas: " + totalDespesas);
            System.out.println("Total das Provisões: " + totalProvisoes);
        });

        leitorDespesas.setBarrier(barrier);
        leitorProvisao.setBarrier(barrier);

        Thread threadDespesas = new Thread(leitorDespesas);
        Thread threadProvisao = new Thread(leitorProvisao);

        threadDespesas.start();
        threadProvisao.start();

        try {
            threadDespesas.join();
            threadProvisao.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

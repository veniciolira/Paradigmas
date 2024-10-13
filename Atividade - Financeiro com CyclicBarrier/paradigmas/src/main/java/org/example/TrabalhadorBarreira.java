package org.example;

import org.example.Model.LeitorDespesas;
import org.example.Model.LeitorProvisao;
import org.example.Model.LeitorReceitas;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TrabalhadorBarreira implements Runnable {
    private final CyclicBarrier barreira;
    private final Object leitor;

    public TrabalhadorBarreira(CyclicBarrier barreira, Object leitor) {
        this.barreira = barreira;
        this.leitor = leitor;
    }

    @Override
    public void run() {
        try {
            if (leitor instanceof LeitorDespesas) {
                System.out.println(((LeitorDespesas) leitor).getClass());
            } else if (leitor instanceof LeitorReceitas) {
                System.out.println(((LeitorReceitas) leitor).somarReceitas());
            } else if (leitor instanceof LeitorProvisao) {
                System.out.println(((LeitorProvisao) leitor).getClass());
            }

            System.out.println(Thread.currentThread().getName() + " finalizou a tarefa e aguarda na barreira.");
            barreira.await();

            System.out.println(Thread.currentThread().getName() + " ultrapassou a barreira e continua a execução.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

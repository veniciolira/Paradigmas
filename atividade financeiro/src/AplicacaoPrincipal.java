import java.util.concurrent.CyclicBarrier;

public class AplicacaoPrincipal {
    public static void main(String[] args) {
        final int TOTAL_THREADS = 3;

        CyclicBarrier sincronizador = new CyclicBarrier(TOTAL_THREADS, () -> {
            System.out.println("Todas as tarefas alcançaram a barreira e estão sincronizadas. Prosseguindo!");
        });

        ProcessadorDespesas processadorDespesas = new ProcessadorDespesas();
        ProcessadorReceitas processadorReceitas = new ProcessadorReceitas();
        Thread threadDespesas = new Thread(new TarefaSincronizada(sincronizador, processadorDespesas), "Thread-Despesas");
        Thread threadReceitas = new Thread(new TarefaSincronizada(sincronizador, processadorReceitas), "Thread-Receitas");
        Thread threadProvisao = new Thread(new TarefaSincronizada(sincronizador, new ProcessadorProvisao()), "Thread-Provisão");

        threadDespesas.start();
        threadReceitas.start();
        threadProvisao.start();

        try {
            threadDespesas.join();
            threadReceitas.join();
            threadProvisao.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todas as tarefas finalizaram suas operações.");

        double totalReceitas = processadorReceitas.getTotalReceitas();
        double totalDespesas = processadorDespesas.getTotalDespesas();
        double saldoFinal = totalReceitas - totalDespesas;

        System.out.println("Receitas Totais: " + totalReceitas);
        System.out.println("Despesas Totais: " + totalDespesas);
        System.out.println("Saldo Financeiro: " + saldoFinal);
    }
}

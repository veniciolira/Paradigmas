import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TarefaSincronizada implements Runnable {
    private final CyclicBarrier sincronizador;
    private final Object leitorDados;

    public TarefaSincronizada(CyclicBarrier sincronizador, Object leitorDados) {
        this.sincronizador = sincronizador;
        this.leitorDados = leitorDados;
    }

    @Override
    public void run() {
        try {
            if (leitorDados instanceof ProcessadorDespesas) {
                System.out.println(((ProcessadorDespesas) leitorDados).processarDespesas());
            } else if (leitorDados instanceof ProcessadorReceitas) {
                System.out.println(((ProcessadorReceitas) leitorDados).processarReceitas());
            } else if (leitorDados instanceof ProcessadorProvisao) {
                System.out.println(((ProcessadorProvisao) leitorDados).processarProvisao());
            }

            System.out.println(Thread.currentThread().getName() + " completou sua operação e está aguardando na barreira.");
            sincronizador.await();

            System.out.println(Thread.currentThread().getName() + " ultrapassou a barreira e continua executando.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

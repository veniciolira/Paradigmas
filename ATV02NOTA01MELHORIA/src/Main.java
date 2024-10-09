import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "C://Users//VenicioLira//Downloads//ATV02NOTA01//src//new_calibration_text.txt";
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Double> resultados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            List<String> linhas = new ArrayList<>();
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }

            CountDownLatch latch = new CountDownLatch(linhas.size());

            for (String l : linhas) {
                executor.submit(() -> {
                    try {
                        double valor = calcularValorCalibracao(l);
                        synchronized (resultados) {
                            resultados.add(valor);
                        }
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await();
            double somaTotal = resultados.stream().mapToDouble(Double::doubleValue).sum();
            System.out.println("Soma total dos valores de calibração: " + somaTotal);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static double calcularValorCalibracao(String linha) {
        char primeiroDigito = '0';
        char ultimoDigito = '0';

        for (char c : linha.toCharArray()) {
            if (Character.isDigit(c)) {
                if (primeiroDigito == '0') {
                    primeiroDigito = c;
                }
                ultimoDigito = c;
            }
        }

        if (primeiroDigito != '0' && ultimoDigito != '0') {
            return (Character.getNumericValue(primeiroDigito) * 10) + Character.getNumericValue(ultimoDigito);
        }
        return 0;
    }
}

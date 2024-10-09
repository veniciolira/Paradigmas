import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "C:\\Users\\VenicioLira\\Downloads\\ATV02NOTA01\\src\\new_calibration_text.txt";
        ExecutorService executor = Executors.newFixedThreadPool(10);
        double somaTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            somaTotal = calcularSomaTotal(reader, executor);
            System.out.println("Soma total dos valores de calibração: " + somaTotal);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static double calcularSomaTotal(BufferedReader reader, ExecutorService executor) throws Exception {
        String linha;
        double somaTotal = 0;
        while ((linha = reader.readLine()) != null) {
            String finalLinha = linha;
            Future<Double> futuro = executor.submit(() -> calcularValorCalibracao(finalLinha));
            somaTotal += futuro.get();
        }
        return somaTotal;
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

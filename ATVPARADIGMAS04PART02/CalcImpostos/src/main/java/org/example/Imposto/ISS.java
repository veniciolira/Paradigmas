package imposto;

import model.Item;
import model.Servico;
import org.example.Imposto.Imposto;

public class ISS implements Imposto {

    @Override
    public String calcular(Item item) {
        if (item instanceof Servico) {
            Servico servico = (Servico) item;
            double iss = 0.22;
            double imposto = item.getValor() * iss;
            item.setImpostoCalculado(imposto);
            item.setTotal(item.getValor() + imposto);
            return item.toString();
        } else {
            return "ISS não aplicável para produtos.";
        }
    }
}
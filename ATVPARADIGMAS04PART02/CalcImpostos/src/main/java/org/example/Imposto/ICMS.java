package org.example.Imposto;

public class ICMS implements Imposto{
    @Override
    public String calcular(model.Item item) {
        if (item instanceof model.Produto) {
            double icms = 0.18;
            double imposto = item.getValor() * icms;
            item.setImpostoCalculado(imposto);
            item.setTotal(item.getValor() + imposto);
            return item.toString();
        } else {
            return "ICMS não aplicável para serviços.";
        }
    }
}
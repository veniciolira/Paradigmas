package org.example.Imposto;

import model.Item;
import model.Produto;

public class IPI implements Imposto {

    @Override
    public String calcular(Item item) {
        if (item instanceof Produto) {
            Produto produto = (Produto) item;
            if (produto.isIndustrial()) {
                double ipi = 0.12;
                double imposto = item.getValor() * ipi;
                item.setImpostoCalculado(imposto);
                item.setTotal(item.getValor() + imposto);
                return item.toString();
            } else {
                item.setTotal(item.getValor());
                return item.toString();
            }
        } else {
            return "IPI não aplicável para serviços.";
        }
    }
}
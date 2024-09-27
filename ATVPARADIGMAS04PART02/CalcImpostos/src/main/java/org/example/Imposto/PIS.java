package org.example.Imposto;

import model.Item;
import model.Produto;

public class PIS implements Imposto {


    @Override
    public String calcular(Item item) {
        if (item instanceof Produto) {
            Produto produto = (Produto) item;
            double pis = 0.065;
            double imposto = item.getValor() * pis;
            item.setImpostoCalculado(imposto);
            item.setTotal(item.getValor() + imposto);
            return item.toString();
        } else {
            return "ICMS não aplicável para serviços.";
        }
    }
}
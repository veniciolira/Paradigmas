package org.example;

import factory.TipoImposto;
import model.Produto;
import model.Servico;
import org.example.Factory.ImpostoFactory;
import org.example.Imposto.Imposto;

public class Main {

    public static void main(String[] args) {
        try {

            Produto produto = new Produto(1, "Suco de Laranja", 15.0, true);
            Servico servico = new Servico(2, "Consulta Médica", 250.0);


            Imposto impostoIPI = ImpostoFactory.imposto(TipoImposto.TIPO_IPI);
            Imposto impostoPIS = ImpostoFactory.imposto(TipoImposto.TIPO_PIS);
            Imposto impostoICMS = ImpostoFactory.imposto(TipoImposto.TIPO_ICMS);
            Imposto impostoISS = ImpostoFactory.imposto(TipoImposto.TIPO_ISS);


            System.out.println(impostoIPI.calcular(produto));
            System.out.println(impostoPIS.calcular(produto));
            System.out.println(impostoICMS.calcular(produto));
            System.out.println(impostoISS.calcular(servico));    
        } catch (Exception e) {
            e.printStackTrace(); // Exibir a pilha de erros
        }
    }
}

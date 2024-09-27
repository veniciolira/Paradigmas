package org.example.Factory;


import org.example.Imposto.ICMS;
import org.example.Imposto.IPI;
import org.example.Imposto.Imposto;
import org.example.Imposto.PIS;

public class ImpostoFactory {

    public static Imposto imposto(factory.TipoImposto tipoImposto) {
        switch (tipoImposto) {
            case TIPO_ICMS -> {
                return new ICMS();
            }
            case TIPO_IPI -> {
                return new IPI();
            }
            case TIPO_ISS -> {
                return new imposto.ISS();
            }
            case TIPO_PIS -> {
                return new PIS();
            }
            default -> throw new IllegalArgumentException("Tipo de imposto desconhecido");
        }
    }
}

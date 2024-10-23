package br.com.balanca.exceptions;

public class PesoExcedidoException extends Exception {
    public PesoExcedidoException(String mensagem) {
        super(mensagem);
    }
}

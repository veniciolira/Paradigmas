package br.com.balanca.exceptions;

public class TempoLimiteExcedidoException extends Exception {
    public TempoLimiteExcedidoException(String mensagem) {
        super(mensagem);
    }
}

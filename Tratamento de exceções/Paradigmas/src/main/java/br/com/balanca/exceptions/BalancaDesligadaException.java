package br.com.balanca.exceptions;

public class BalancaDesligadaException extends Exception {
    public BalancaDesligadaException(String mensagem) {
        super(mensagem);
    }
}

package br.com.balanca.exceptions;

public class ConexaoBalancaFalhouException extends Exception {
    public ConexaoBalancaFalhouException(String mensagem) {
        super(mensagem);
    }
}

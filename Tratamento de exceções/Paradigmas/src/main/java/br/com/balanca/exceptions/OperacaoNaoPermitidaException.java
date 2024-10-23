package br.com.balanca.exceptions;

public class OperacaoNaoPermitidaException extends Exception {
    public OperacaoNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}

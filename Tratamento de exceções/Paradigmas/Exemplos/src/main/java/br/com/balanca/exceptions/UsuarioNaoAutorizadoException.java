package br.com.balanca.exceptions;

public class UsuarioNaoAutorizadoException extends Exception {
    public UsuarioNaoAutorizadoException(String mensagem) {
        super(mensagem);
    }
}

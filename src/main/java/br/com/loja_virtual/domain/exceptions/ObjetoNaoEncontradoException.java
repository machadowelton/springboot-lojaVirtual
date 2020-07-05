package br.com.loja_virtual.domain.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}

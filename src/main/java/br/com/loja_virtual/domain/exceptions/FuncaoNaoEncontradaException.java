package br.com.loja_virtual.domain.exceptions;

public class FuncaoNaoEncontradaException extends ObjetoNaoEncontradoException {
    public FuncaoNaoEncontradaException(String message) {
        super(message);
    }
}

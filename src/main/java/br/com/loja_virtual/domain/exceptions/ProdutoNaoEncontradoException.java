package br.com.loja_virtual.domain.exceptions;

public class ProdutoNaoEncontradoException extends ObjetoNaoEncontradoException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}

package br.com.loja_virtual.domain.exceptions;

public class ProdutoNaoDisponivelException extends RegraNegocioException {

    public ProdutoNaoDisponivelException(String message) {
        super(message);
    }

}

package br.com.loja_virtual.domain.exceptions;

public class PedidoSemProdutoException extends RegraNegocioException {

    public PedidoSemProdutoException(String message) {
        super(message);
    }

}

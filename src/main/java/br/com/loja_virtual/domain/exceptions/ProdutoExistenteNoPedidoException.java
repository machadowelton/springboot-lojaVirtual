package br.com.loja_virtual.domain.exceptions;

public class ProdutoExistenteNoPedidoException extends RegraNegocioException {

    public ProdutoExistenteNoPedidoException(String message) {
        super(message);
    }

}

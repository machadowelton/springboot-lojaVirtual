package br.com.loja_virtual.domain.exceptions;

public class OperacaoNaoPermitidaException extends  RegraNegocioException{

    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }

}

package br.com.loja_virtual.domain.exceptions;

public class CombinacaoUsuarioSenhaNaoValidaException extends RegraNegocioException{

    public CombinacaoUsuarioSenhaNaoValidaException(String message) {
        super(message);
    }

}

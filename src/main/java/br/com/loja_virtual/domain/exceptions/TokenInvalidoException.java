package br.com.loja_virtual.domain.exceptions;

public class TokenInvalidoException extends RegraNegocioException {

    public TokenInvalidoException(String message) {
        super(message);
    }

}

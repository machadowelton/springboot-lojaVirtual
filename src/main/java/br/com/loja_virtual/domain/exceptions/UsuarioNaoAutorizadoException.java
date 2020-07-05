package br.com.loja_virtual.domain.exceptions;

public class UsuarioNaoAutorizadoException extends RegraNegocioException {

    public UsuarioNaoAutorizadoException(String message) {
        super(message);
    }

}

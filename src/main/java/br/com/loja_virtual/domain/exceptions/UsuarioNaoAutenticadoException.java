package br.com.loja_virtual.domain.exceptions;

public class UsuarioNaoAutenticadoException extends RegraNegocioException {

    public UsuarioNaoAutenticadoException(String message) {
        super(message);
    }

}

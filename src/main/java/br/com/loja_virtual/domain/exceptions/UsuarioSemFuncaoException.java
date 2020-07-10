package br.com.loja_virtual.domain.exceptions;

public class UsuarioSemFuncaoException extends RegraNegocioException{
    public UsuarioSemFuncaoException(String message) {
        super(message);
    }
}

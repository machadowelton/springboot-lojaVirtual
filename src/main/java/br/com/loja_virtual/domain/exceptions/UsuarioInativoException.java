package br.com.loja_virtual.domain.exceptions;

public class UsuarioInativoException extends RegraNegocioException {

    public UsuarioInativoException(String message) {
        super(message);
    }

}

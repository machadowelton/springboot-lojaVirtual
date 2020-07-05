package br.com.loja_virtual.domain.exceptions;

public class NomeUsuarioDuplicadoException extends ViolacaoDadoNaoRepetitivoException{
    public NomeUsuarioDuplicadoException(String message) {
        super(message);
    }
}

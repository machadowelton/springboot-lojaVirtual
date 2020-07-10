package br.com.loja_virtual.domain.exceptions;

public class NomeUsuarioEmUsoException extends ViolacaoDadoNaoRepetitivoException{

    public NomeUsuarioEmUsoException(String message) {
        super(message);
    }

}

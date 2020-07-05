package br.com.loja_virtual.domain.exceptions;

public class FuncaoExistenteException extends ViolacaoDadoNaoRepetitivoException {

    public FuncaoExistenteException(String message) {
        super(message);
    }
}

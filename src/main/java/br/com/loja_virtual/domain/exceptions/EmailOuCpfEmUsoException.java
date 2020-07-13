package br.com.loja_virtual.domain.exceptions;

public class EmailOuCpfEmUsoException extends ViolacaoDadoNaoRepetitivoException {
    public EmailOuCpfEmUsoException(String msg) {
        super(msg);
    }
}

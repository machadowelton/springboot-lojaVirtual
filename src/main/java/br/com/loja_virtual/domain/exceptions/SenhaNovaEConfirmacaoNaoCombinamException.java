package br.com.loja_virtual.domain.exceptions;

public class SenhaNovaEConfirmacaoNaoCombinamException extends RegraNegocioException{
    public SenhaNovaEConfirmacaoNaoCombinamException(String message) {
        super(message);
    }
}

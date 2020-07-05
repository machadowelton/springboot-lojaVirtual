package br.com.loja_virtual.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
public class RespostaErro {

    private final String mensagem;

    private final String dataExecucao;

    private  final String path;

    private final String fraseErroHttp;

    private final Integer codigoErroHttp;

    public String getMensagem() {
        return mensagem;
    }

    public String getDataExecucao() {
        return dataExecucao;
    }

    public String getFraseErroHttp() {
        return fraseErroHttp;
    }

    public Integer getCodigoErroHttp() {
        return codigoErroHttp;
    }

    public String getPath() {
        return path;
    }
}

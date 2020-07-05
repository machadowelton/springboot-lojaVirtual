package br.com.loja_virtual.domain.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RespostaCampoErro extends RespostaErro {

    private final Map<String, List<String>> camposComErro = new HashMap<>();

    public RespostaCampoErro(String mensagem, String dataExecucao, String path, String fraseErroHttp, Integer codigoErroHttp) {
        super(mensagem, dataExecucao, path, fraseErroHttp, codigoErroHttp);
    }

    public RespostaCampoErro(String mensagem, String dataExecucao, String path, String fraseErroHttp, Integer codigoErroHttp, Map<String, List<String>> camposComErro) {
        super(mensagem, dataExecucao, path, fraseErroHttp, codigoErroHttp);
        this.camposComErro.putAll(camposComErro);
    }
}

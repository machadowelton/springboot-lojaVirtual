package br.com.loja_virtual.domain.enums;

public enum EFuncao {

    GERENTE("GERENTE"),
    OPERADOR("OPERADOR"),
    CONSUMIDOR("CONSUMIDOR");

    private String funcao;

    EFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }
}

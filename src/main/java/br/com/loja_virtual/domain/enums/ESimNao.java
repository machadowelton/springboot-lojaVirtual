package br.com.loja_virtual.domain.enums;

public enum ESimNao {

    SIM("SIM"),
    NAO("NAO");

    private final String escolha;

    ESimNao(String escolha) {
        this.escolha = escolha;
    }

    public String getEscolha() {
        return escolha;
    }
}

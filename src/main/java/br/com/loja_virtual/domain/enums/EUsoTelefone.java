package br.com.loja_virtual.domain.enums;

public enum EUsoTelefone {

    PESSOAL("PESSOAL"),
    CORPORATIVO("CORPORATIVO");

    private final String usoTelefone;

    EUsoTelefone(String usoTelefone) {
        this.usoTelefone = usoTelefone;
    }

    public String getUsoTelefone() {
        return usoTelefone;
    }
}

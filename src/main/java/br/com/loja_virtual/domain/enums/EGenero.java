package br.com.loja_virtual.domain.enums;

public enum EGenero {

    MASCULINO("MASCULINO"),
    FEMININO("FEMININO"),
    OUTRO("OUTRO");

    private final String genero;

    EGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}

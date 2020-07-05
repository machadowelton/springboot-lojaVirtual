package br.com.loja_virtual.domain.enums;

public enum ETipoEndereco {

    RESIDENCIAL("RESIDENCIAL"),
    TRABALHO("TRABALHO");

    private final String tipoEndereco;

    ETipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }
}

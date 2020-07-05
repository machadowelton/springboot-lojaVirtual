package br.com.loja_virtual.domain.enums;

public enum EPedidoStatus {

    ABERTO("ABERTO"),
    FECHADO("FECHADO");

    private String status;

    EPedidoStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

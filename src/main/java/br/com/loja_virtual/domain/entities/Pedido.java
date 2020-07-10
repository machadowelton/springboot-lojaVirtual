package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.EPedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    private Long id;

    @Builder.Default
    private LocalDateTime dataHoraInicio = LocalDateTime.now();

    @Builder.Default
    private EPedidoStatus status = EPedidoStatus.ABERTO;

    private LocalDateTime dataHoraFim;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_consumidor", referencedColumnName = "id")
    private Consumidor consumidor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "id_pedido", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_produto",referencedColumnName = "id")
    )
    @Builder.Default
    private Set<Produto> produtos = new HashSet<>();

    @Builder.Default
    private BigDecimal valorTotalPedido = BigDecimal.valueOf(0d);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public EPedidoStatus getStatus() {
        return status;
    }

    public void setStatus(EPedidoStatus status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;

        Pedido pedido = (Pedido) o;

        if (id != null ? !id.equals(pedido.id) : pedido.id != null) return false;
        if (dataHoraInicio != null ? !dataHoraInicio.equals(pedido.dataHoraInicio) : pedido.dataHoraInicio != null)
            return false;
        if (status != pedido.status) return false;
        if (dataHoraFim != null ? !dataHoraFim.equals(pedido.dataHoraFim) : pedido.dataHoraFim != null) return false;
        if (consumidor != null ? !consumidor.equals(pedido.consumidor) : pedido.consumidor != null) return false;
        if (produtos != null ? !produtos.equals(pedido.produtos) : pedido.produtos != null) return false;
        return valorTotalPedido != null ? valorTotalPedido.equals(pedido.valorTotalPedido) : pedido.valorTotalPedido == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dataHoraInicio != null ? dataHoraInicio.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dataHoraFim != null ? dataHoraFim.hashCode() : 0);
        result = 31 * result + (consumidor != null ? consumidor.hashCode() : 0);
        result = 31 * result + (produtos != null ? produtos.hashCode() : 0);
        result = 31 * result + (valorTotalPedido != null ? valorTotalPedido.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pedido(" +
                "id=" + id +
                ", dataHoraInicio=" + dataHoraInicio +
                ", status=" + status +
                ", dataHoraFim=" + dataHoraFim +
                ", consumidor=" + consumidor +
                ", produtos=" + produtos +
                ", valorTotalPedido=" + valorTotalPedido +
                ')';
    }
}

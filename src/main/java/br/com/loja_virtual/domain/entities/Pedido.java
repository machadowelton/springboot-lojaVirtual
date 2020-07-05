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
            strategy= GenerationType.AUTO,
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
            joinColumns = @JoinColumn(name = "id_produto", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_pedido",referencedColumnName = "id")
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
}

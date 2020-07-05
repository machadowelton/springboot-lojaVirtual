package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.ESimNao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private Long id;

    @NotNull(message = "O titulo do produto não pode ser nulo")
    private  String titulo;

    private String descricao;

    @NotNull(message = "O peso do produto não pode ser nulo")
    private BigDecimal peso;

    @NotNull(message = "O valor do produto não pode ser nulo")
    private BigDecimal valor;

    @Builder.Default
    private ESimNao disponivel = ESimNao.SIM;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "produtos")
    private Set<Pedido> pedidos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ESimNao getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(ESimNao disponivel) {
        this.disponivel = disponivel;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.ESimNao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;

        Produto produto = (Produto) o;

        if (id != null ? !id.equals(produto.id) : produto.id != null) return false;
        if (titulo != null ? !titulo.equals(produto.titulo) : produto.titulo != null) return false;
        if (descricao != null ? !descricao.equals(produto.descricao) : produto.descricao != null) return false;
        if (peso != null ? !peso.equals(produto.peso) : produto.peso != null) return false;
        if (valor != null ? !valor.equals(produto.valor) : produto.valor != null) return false;
        return disponivel == produto.disponivel;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (peso != null ? peso.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + (disponivel != null ? disponivel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Produto(" +
                "id=" + id +
                ", titulo=" + titulo +
                ", descricao=" + descricao +
                ", peso=" + peso +
                ", valor=" + valor +
                ", disponivel=" + disponivel +
                ')';
    }
}

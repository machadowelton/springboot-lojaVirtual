package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.EFuncao;
import br.com.loja_virtual.domain.enums.EGenero;
import br.com.loja_virtual.domain.enums.ESimNao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "funcoes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Funcao {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    private Long id;

    @NotNull(message = "O nome da função é obrigatório")
    @Enumerated(EnumType.STRING)
    private EFuncao nome;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EFuncao getNome() {
        return nome;
    }

    public void setNome(EFuncao nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcao)) return false;

        Funcao funcao = (Funcao) o;

        if (id != null ? !id.equals(funcao.id) : funcao.id != null) return false;
        if (nome != funcao.nome) return false;
        return descricao != null ? descricao.equals(funcao.descricao) : funcao.descricao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Funcao(" +
                "id=" + id +
                ", nome=" + nome +
                ", descricao=" + descricao +
                ')';
    }
}

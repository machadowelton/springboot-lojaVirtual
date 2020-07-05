package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.EFuncao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "funcoes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Funcao {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private Long id;

    @NotNull(message = "O nome da função não pode ser nula")
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private EFuncao nome;

    @ManyToMany
    @JoinTable(
            name = "funcoes_usuarios",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_funcao", referencedColumnName = "id")
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder.Default
    private Set<Usuario> usuarios = new HashSet<>();

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

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

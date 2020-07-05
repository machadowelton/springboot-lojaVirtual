package br.com.loja_virtual.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private Long id;

    @Email(message = "O nome de usuário precisa ser um e-mail")
    private String nome;

    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    private Set<Funcao> funcoes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Set<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
}

package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.ESimNao;
import com.fasterxml.jackson.annotation.JsonInclude;
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
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    private Long id;

    @Email(message = "O nome de usuário precisa ser um e-mail")
    @Column(unique = true)
    private String nome;

    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ESimNao ativo = ESimNao.SIM;

    @ManyToMany
    @JoinTable(
            name = "usuarios_funcoes",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_funcao", referencedColumnName = "id")
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder.Default
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

    public ESimNao getAtivo() {
        return ativo;
    }

    public void setAtivo(ESimNao ativo) {
        this.ativo = ativo;
    }

    public Set<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Set<Funcao> funcoes) {
        this.funcoes = funcoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        if (nome != null ? !nome.equals(usuario.nome) : usuario.nome != null) return false;
        if (senha != null ? !senha.equals(usuario.senha) : usuario.senha != null) return false;
        if (ativo != usuario.ativo) return false;
        return funcoes != null ? funcoes.equals(usuario.funcoes) : usuario.funcoes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        result = 31 * result + (ativo != null ? ativo.hashCode() : 0);
        result = 31 * result + (funcoes != null ? funcoes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Usuario(" +
                "id=" + id +
                ", nome=" + nome +
                ", senha=" + senha +
                ", ativo=" + ativo +
                ", funcoes=" + funcoes +
                ')';
    }
}

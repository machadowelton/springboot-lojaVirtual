package br.com.loja_virtual.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "consumidores")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consumidor {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    private Long id;

    @NotNull(message = "O nome do consumidor não pode ser nulo e tem que ter ao menos 1 sobrenome")
    private  String nomeCompleto;

    @NotNull(message = "A data de nascimento do consumidor não pode ser nulo")
    private LocalDate dataNascimento;

    @NotNull(message = "O cpf do consumidor não pode ser nulo")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "O email do consumidor não pode ser nulo")
    @Column(unique = true)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_consumidor")
    @Builder.Default
    private Set<Telefone> telefones = new HashSet<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_consumidor")
    @Builder.Default
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consumidor)) return false;

        Consumidor that = (Consumidor) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nomeCompleto != null ? !nomeCompleto.equals(that.nomeCompleto) : that.nomeCompleto != null) return false;
        if (dataNascimento != null ? !dataNascimento.equals(that.dataNascimento) : that.dataNascimento != null)
            return false;
        if (cpf != null ? !cpf.equals(that.cpf) : that.cpf != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telefones != null ? !telefones.equals(that.telefones) : that.telefones != null) return false;
        if (enderecos != null ? !enderecos.equals(that.enderecos) : that.enderecos != null) return false;
        return usuario != null ? usuario.equals(that.usuario) : that.usuario == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nomeCompleto != null ? nomeCompleto.hashCode() : 0);
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefones != null ? telefones.hashCode() : 0);
        result = 31 * result + (enderecos != null ? enderecos.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Consumidor(" +
                "id=" + id +
                ", nomeCompleto=" + nomeCompleto +
                ", dataNascimento=" + dataNascimento +
                ", cpf=" + cpf +
                ", email=" + email +
                ", telefones=" + telefones +
                ", enderecos=" + enderecos +
                ", usuario=" + usuario +
                ')';
    }
}

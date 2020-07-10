package br.com.loja_virtual.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "gerentes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gerente {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    private Long id;

    @NotNull(message = "O nome do gerente é obrigatório")
    private  String nomeCompleto;

    @NotNull(message = "A data de nascimento do gerente não pode ser nula")
    private LocalDate dataNascimento;

    @NotNull(message = "O cpf do gerente não poder nulo")
    @Column(unique = true)
    private String cpf;

    @Email(message = "O email do gerente tem que ser válido e não nulo")
    @Column(unique = true)
    private String email;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gerente)) return false;

        Gerente gerente = (Gerente) o;

        if (id != null ? !id.equals(gerente.id) : gerente.id != null) return false;
        if (nomeCompleto != null ? !nomeCompleto.equals(gerente.nomeCompleto) : gerente.nomeCompleto != null)
            return false;
        if (dataNascimento != null ? !dataNascimento.equals(gerente.dataNascimento) : gerente.dataNascimento != null)
            return false;
        if (cpf != null ? !cpf.equals(gerente.cpf) : gerente.cpf != null) return false;
        if (email != null ? !email.equals(gerente.email) : gerente.email != null) return false;
        return usuario != null ? usuario.equals(gerente.usuario) : gerente.usuario == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nomeCompleto != null ? nomeCompleto.hashCode() : 0);
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Gerente(" +
                "id=" + id +
                ", nomeCompleto=" + nomeCompleto +
                ", dataNascimento=" + dataNascimento +
                ", cpf=" + cpf +
                ", email=" + email +
                ", usuario=" + usuario +
                ')';
    }
}

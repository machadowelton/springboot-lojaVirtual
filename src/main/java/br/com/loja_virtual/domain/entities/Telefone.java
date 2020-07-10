package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.ESimNao;
import br.com.loja_virtual.domain.enums.EUsoTelefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "telefones")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Telefone {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    private Long id;

    private ESimNao principal;

    private EUsoTelefone tipoUso = EUsoTelefone.PESSOAL;

    private String ddd;

    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ESimNao getPrincipal() {
        return principal;
    }

    public void setPrincipal(ESimNao principal) {
        this.principal = principal;
    }

    public EUsoTelefone getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(EUsoTelefone tipoUso) {
        this.tipoUso = tipoUso;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone)) return false;

        Telefone telefone = (Telefone) o;

        if (id != null ? !id.equals(telefone.id) : telefone.id != null) return false;
        if (principal != telefone.principal) return false;
        if (tipoUso != telefone.tipoUso) return false;
        if (ddd != null ? !ddd.equals(telefone.ddd) : telefone.ddd != null) return false;
        return numero != null ? numero.equals(telefone.numero) : telefone.numero == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (principal != null ? principal.hashCode() : 0);
        result = 31 * result + (tipoUso != null ? tipoUso.hashCode() : 0);
        result = 31 * result + (ddd != null ? ddd.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Telefone(" +
                "id=" + id +
                ", principal=" + principal +
                ", tipoUso=" + tipoUso +
                ", ddd=" + ddd +
                ", numero=" + numero +
                ')';
    }
}

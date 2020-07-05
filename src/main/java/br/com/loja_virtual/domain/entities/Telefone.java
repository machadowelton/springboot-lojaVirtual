package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.ESimNao;
import br.com.loja_virtual.domain.enums.EUsoTelefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "telefones")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Telefone {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private Long id;

    private ESimNao principal;

    private EUsoTelefone tipoUso = EUsoTelefone.PESSOAL;

    private String ddd;

    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consumidor", referencedColumnName = "id")
    private Consumidor consumidor;

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

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }
}

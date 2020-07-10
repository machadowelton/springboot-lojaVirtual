package br.com.loja_virtual.domain.entities;

import br.com.loja_virtual.domain.enums.ESimNao;
import br.com.loja_virtual.domain.enums.ETipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    private Long id;

    private ESimNao principal;

    private ETipoEndereco tipoEndereco;

    @NotNull(message = "O logradouro do endereço não pode ser nulo")
    private String logradouro;

    private String numero;

    private String complemnto;

    @NotNull(message = "O cep do endereço não poder nulo")
    private  String cep;

    private String pontoReferencia;

    @NotNull(message = "O bairro do endereco não pode ser nulo")
    private String bairro;

    @NotNull(message = "A cidade do endereco não pode ser nulo")
    private String cidade;

    @NotNull(message = "O estado(uf) do endereco não pode ser nulo")
    private String estado;

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

    public ETipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(ETipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemnto() {
        return complemnto;
    }

    public void setComplemnto(String complemnto) {
        this.complemnto = complemnto;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;

        Endereco endereco = (Endereco) o;

        if (id != null ? !id.equals(endereco.id) : endereco.id != null) return false;
        if (principal != endereco.principal) return false;
        if (tipoEndereco != endereco.tipoEndereco) return false;
        if (logradouro != null ? !logradouro.equals(endereco.logradouro) : endereco.logradouro != null) return false;
        if (numero != null ? !numero.equals(endereco.numero) : endereco.numero != null) return false;
        if (complemnto != null ? !complemnto.equals(endereco.complemnto) : endereco.complemnto != null) return false;
        if (cep != null ? !cep.equals(endereco.cep) : endereco.cep != null) return false;
        if (pontoReferencia != null ? !pontoReferencia.equals(endereco.pontoReferencia) : endereco.pontoReferencia != null)
            return false;
        if (bairro != null ? !bairro.equals(endereco.bairro) : endereco.bairro != null) return false;
        if (cidade != null ? !cidade.equals(endereco.cidade) : endereco.cidade != null) return false;
        return estado != null ? estado.equals(endereco.estado) : endereco.estado == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (principal != null ? principal.hashCode() : 0);
        result = 31 * result + (tipoEndereco != null ? tipoEndereco.hashCode() : 0);
        result = 31 * result + (logradouro != null ? logradouro.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (complemnto != null ? complemnto.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (pontoReferencia != null ? pontoReferencia.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Endereco(" +
                "id=" + id +
                ", principal=" + principal +
                ", tipoEndereco=" + tipoEndereco +
                ", logradouro=" + logradouro +
                ", numero=" + numero +
                ", complemnto=" + complemnto +
                ", cep=" + cep +
                ", pontoReferencia=" + pontoReferencia +
                ", bairro=" + bairro +
                ", cidade=" + cidade +
                ", estado=" + estado +
                ')';
    }
}

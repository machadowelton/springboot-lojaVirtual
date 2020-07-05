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
            strategy= GenerationType.AUTO,
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

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }
}

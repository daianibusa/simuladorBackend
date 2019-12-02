package br.com.simulador.credito.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Daiani
 */
@Entity
@Table(name = "simulacao")
public class SimulacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_simulacao")
    private Date dataSimulacao;

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(name = "valor_simulacao", nullable = false)
    private BigDecimal valorSimulacao;

    @Column(name = "codigo_modalidade")
    private Integer codigoModalidade;

    @Column(name = "nome_modalidade")
    private String nomeModalidade;

    @Column(name = "quantidade_parcelas")
    private Integer quantidadeParcelas;

    @Column(name = "taxa_simulacao")
    private BigDecimal taxaSimulacao;

    @Column(name = "resultado_valor_parcela")
    private BigDecimal resultadoValorParcela;

    @Column(name = "resultado_valor_total")
    private BigDecimal resultadoValorTotal;

    @Column(length = 30)
    private String situacao;

    @Column
    private String observacao;

    public SimulacaoEntity() {
        this.situacao = "Sem contato";
    }
            
        
            /**
             * @return the id
             */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dataSimulacao
     */
    public Date getDataSimulacao() {
        return dataSimulacao;
    }

    /**
     * @param dataSimulacao the dataSimulacao to set
     */
    public void setDataSimulacao(Date dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the valorSimulacao
     */
    public BigDecimal getValorSimulacao() {
        return valorSimulacao;
    }

    /**
     * @param valorSimulacao the valorSimulacao to set
     */
    public void setValorSimulacao(BigDecimal valorSimulacao) {
        this.valorSimulacao = valorSimulacao;
    }

    /**
     * @return the codigoModalidade
     */
    public Integer getCodigoModalidade() {
        return codigoModalidade;
    }

    /**
     * @param codigoModalidade the codigoModalidade to set
     */
    public void setCodigoModalidade(Integer codigoModalidade) {
        this.codigoModalidade = codigoModalidade;
    }

    /**
     * @return the nomeModalidade
     */
    public String getNomeModalidade() {
        return nomeModalidade;
    }

    /**
     * @param nomeModalidade the nomeModalidade to set
     */
    public void setNomeModalidade(String nomeModalidade) {
        this.nomeModalidade = nomeModalidade;
    }

    /**
     * @return the taxaSimulacao
     */
    public BigDecimal getTaxaSimulacao() {
        return taxaSimulacao;
    }

    /**
     * @param taxaSimulacao the taxaSimulacao to set
     */
    public void setTaxaSimulacao(BigDecimal taxaSimulacao) {
        this.taxaSimulacao = taxaSimulacao;
    }

    /**
     * @return the resultadoValorParcela
     */
    public BigDecimal getResultadoValorParcela() {
        return resultadoValorParcela;
    }

    /**
     * @param resultadoValorParcela the resultadoValorParcela to set
     */
    public void setResultadoValorParcela(BigDecimal resultadoValorParcela) {
        this.resultadoValorParcela = resultadoValorParcela;
    }

    /**
     * @return the resultadoValorTotal
     */
    public BigDecimal getResultadoValorTotal() {
        return resultadoValorTotal;
    }

    /**
     * @param resultadoValorTotal the resultadoValorTotal to set
     */
    public void setResultadoValorTotal(BigDecimal resultadoValorTotal) {
        this.resultadoValorTotal = resultadoValorTotal;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the quantidadeParcelas
     */
    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    /**
     * @param quantidadeParcelas the quantidadeParcelas to set
     */
    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimulacaoEntity other = (SimulacaoEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

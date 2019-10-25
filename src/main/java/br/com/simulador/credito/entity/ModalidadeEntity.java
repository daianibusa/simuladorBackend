package br.com.simulador.credito.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author Daiani
 */
@Entity
@Table(name = "modalidade")
public class ModalidadeEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "codigo_modalidade", nullable = false)
    private Integer codigoModalidade;
    
    @Column(length = 50, nullable = false)
    private String nome;
    
    @Lob
    @Column(length = 5000, nullable = false)
    private String descricao; 
    
    @OneToMany(mappedBy = "modalidade", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("parcelas ASC")
    private List<TaxaEntity> taxas;

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
     * @return the codigo_modalidade
     */
 

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the taxas
     */
    public List<TaxaEntity> getTaxas() {
        return taxas;
    }

    /**
     * @param taxas the taxas to set
     */
    public void setTaxas(List<TaxaEntity> taxas) {
        this.taxas = taxas;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ModalidadeEntity other = (ModalidadeEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}

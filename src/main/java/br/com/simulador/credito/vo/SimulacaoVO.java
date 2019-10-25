package br.com.simulador.credito.vo;

import java.math.BigDecimal;


/**
 *
 * @author Daiani
 */
public class SimulacaoVO {
    
    
    private Integer idModalidade; 
    
    private Integer quantidadeParcelas;
    
    private BigDecimal valorSimulacao;
    
    private String nome;
    
    private String telefone; 

    /**
     * @return the idModalidade
     */
    public Integer getIdModalidade() {
        return idModalidade;
    }

    /**
     * @param idModalidade the idModalidade to set
     */
    public void setIdModalidade(Integer idModalidade) {
        this.idModalidade = idModalidade;
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

   
}

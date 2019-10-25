
package br.com.simulador.credito.service;

import br.com.simulador.credito.entity.ModalidadeEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.simulador.credito.entity.SimulacaoEntity;
import br.com.simulador.credito.entity.TaxaEntity;
import br.com.simulador.credito.repository.ModalidadeRepository;
import br.com.simulador.credito.repository.SimulacaoRepository;
import br.com.simulador.credito.vo.SimulacaoVO;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Daiani
 */
@Service
public class SimulacaoService implements IBaseService<SimulacaoEntity> {
    
    @Autowired //Injeta uma instancia da interface SimulacaoRepository - é o mesmo que Simulacao simulacao = new Simulacao()
    private SimulacaoRepository repository;
    
    @Autowired
    private ModalidadeRepository modalidadeRepository;
    
   
    
     //Método para buscar todos os registros da tabela
    @Override
    public List<SimulacaoEntity> listarTodos(){
        return repository.findAll();
    }
    
    //Método que busca um registro pelo id no banco de dados e retorna este registro 
    @Override
    public SimulacaoEntity listarPorId(Integer id){
        return repository.getOne(id);
    }
    
    //Método que salva o registro no banco de dados

    public SimulacaoEntity salvar (SimulacaoVO vo){

        TaxaEntity taxaSelecionada = obterTaxa(vo);
       
        BigDecimal pmt = calcularValorParcelas(taxaSelecionada, vo);
        
        BigDecimal valorTotal = calcularValorTotal(pmt, vo);
        
        SimulacaoEntity simulacao = new SimulacaoEntity();
        simulacao.setCodigoModalidade(taxaSelecionada.getModalidade().getCodigoModalidade());
        simulacao.setDataSimulacao(new Date());
        simulacao.setNome(vo.getNome());
        simulacao.setNomeModalidade(taxaSelecionada.getModalidade().getNome());
        simulacao.setTaxaSimulacao(taxaSelecionada.getTaxa());
        simulacao.setResultadoValorParcela(pmt);
        simulacao.setResultadoValorTotal(valorTotal);
        simulacao.setTelefone(vo.getTelefone());
        simulacao.setValorSimulacao(vo.getValorSimulacao());
        
        
        return repository.save(simulacao);
    }
    
    private TaxaEntity obterTaxa(SimulacaoVO vo){
           ModalidadeEntity modalidade = modalidadeRepository.getOne(vo.getIdModalidade());
        TaxaEntity taxaSelecionada = null;
        for (TaxaEntity taxa : modalidade.getTaxas()) {
            if(taxa.getParcelas()>=vo.getQuantidadeParcelas()){
                taxaSelecionada = taxa;
                break;
            }
        }
        return taxaSelecionada;
    }
    
    
    
    private BigDecimal calcularValorParcelas(TaxaEntity taxaEntity, SimulacaoVO vo){
        
        Double taxa = taxaEntity.getTaxa().doubleValue()/100;
       Double pmt = vo.getValorSimulacao().doubleValue()/
               ((Math.pow(1+taxa, vo.getQuantidadeParcelas())-1)/
               (Math.pow(1+taxa, vo.getQuantidadeParcelas())*taxa));
       
           
        return new BigDecimal(pmt).setScale(2, BigDecimal.ROUND_HALF_UP);
        
    }
    
    private BigDecimal calcularValorTotal(BigDecimal pmt, SimulacaoVO vo){
        
        return pmt.multiply(new BigDecimal(vo.getQuantidadeParcelas()));
    }
    //Método para excluir um registro 
    @Override
    public void excluir (Integer id){
        repository.deleteById(id);
    }
    
    
    
    
}

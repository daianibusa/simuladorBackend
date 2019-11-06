
package br.com.simulador.credito.service;

import br.com.simulador.credito.entity.RelatorioEntity;
import br.com.simulador.credito.excecao.ExcecaoEsperada;
import br.com.simulador.credito.repository.RelatorioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daiani
 */
@Service
public class RelatorioService {
    
    @Autowired //Injeta uma instancia da interface RelatorioRepository - é o mesmo que Relatorio relatorio = new Relatorio()
    private RelatorioRepository repository; 
    
    
      //Método para buscar todos os registros da tabela
    public List<RelatorioEntity> listarTodos(){
        return repository.findAll();
    }
    
    //Método que busca um registro pelo id no banco de dados e retorna este registro 
    public RelatorioEntity listarPorId(Integer id){
        return repository.getOne(id);
    }
    
    //Método que salva o registro no banco de dados
    public RelatorioEntity salvar (RelatorioEntity entity) throws ExcecaoEsperada{
        return repository.save(entity); 

    }
    
    //Método para excluir um registro 
    public void excluir (Integer id){
        repository.deleteById(id);
    }
}



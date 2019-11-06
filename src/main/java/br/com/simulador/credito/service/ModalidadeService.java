
package br.com.simulador.credito.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.simulador.credito.entity.ModalidadeEntity;
import br.com.simulador.credito.entity.QModalidadeEntity;
import br.com.simulador.credito.excecao.ExcecaoEsperada;
import br.com.simulador.credito.repository.ModalidadeRepository;
import java.util.Optional;

/** 
 *
 * @author Daiani
 */
@Service
public class ModalidadeService  {
    
    @Autowired //Injeta uma instancia da interface ModalidadeRepository - é o mesmo que Modalidade modalidade = new Modalidade()
    private ModalidadeRepository repository;
    
     //Método para buscar todos os registros da tabela
    public List<ModalidadeEntity> listarTodos(){
        return repository.findAll();
    }
    
    //Método que busca um registro pelo id no banco de dados e retorna este registro 
    public ModalidadeEntity listarPorId(Integer id){
         return repository.getOne(id);
 
    }
    
    //Método que salva o registro no banco de dados
   
    public ModalidadeEntity salvar (ModalidadeEntity entity) throws ExcecaoEsperada{
        QModalidadeEntity query = QModalidadeEntity.modalidadeEntity;
        Integer idModalidade = entity.getId() != null ? entity.getId() : 0;
        
        Boolean encontrouNome = repository.exists(query.nome.eq(entity.getNome()).and(query.id.ne(idModalidade)));
        if (encontrouNome) {
            throw new ExcecaoEsperada("O nome da modalidade já está cadastrado.");
        }
        
        
        return repository.saveAndFlush(entity); 
    }
    
    //Método para excluir um registro 
    public void excluir (Integer id){
        repository.deleteById(id);
    }
    
}

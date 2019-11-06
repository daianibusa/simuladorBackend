
package br.com.simulador.credito.service;

import br.com.simulador.credito.entity.QUsuarioEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.simulador.credito.entity.UsuarioEntity;
import br.com.simulador.credito.excecao.ExcecaoEsperada;
import br.com.simulador.credito.repository.UsuarioRepository;

/**
 *
 * @author Daiani
 */
@Service
public class UsuarioService{
    
    @Autowired //Injeta uma instancia da interface UsuarioRepository - é o mesmo que Usuario usuario = new Usuario()
    private UsuarioRepository repository;
    
    
    
    
    //Método para buscar todos os registros da tabela
    public List<UsuarioEntity> listarTodos(){
        return repository.findAll();
    }
    
    //Método que busca um registro pelo id no banco de dados e retorna este registro 
    public UsuarioEntity listarPorId(Integer id){
        return repository.getOne(id);
    }
    
    //Método que salva o registro no banco de dados

    public UsuarioEntity salvar (UsuarioEntity entity) throws ExcecaoEsperada{
        QUsuarioEntity query = QUsuarioEntity.usuarioEntity;
        Integer idUsuario = entity.getId() != null ? entity.getId() : 0;
        
        Boolean encontrouEmail = repository.exists(query.email.eq(entity.getEmail()).and(query.id.ne(idUsuario)));
        if (encontrouEmail) {
            throw new ExcecaoEsperada("O e-mail informado já está cadastrado para outro usuário.");
        }
        
        
        return repository.saveAndFlush(entity); 

    }
    
    //Método para excluir um registro 
    public void excluir (Integer id){
        repository.deleteById(id);
    }
}

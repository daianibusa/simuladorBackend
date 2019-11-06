
package br.com.simulador.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.simulador.credito.entity.UsuarioEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 *
 * @author Daiani
 */
@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Integer>, QuerydslPredicateExecutor<UsuarioEntity> {
    
    UsuarioEntity findByEmail(String email);

    
}

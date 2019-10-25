
package br.com.simulador.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.simulador.credito.entity.ModalidadeEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 *
 * @author Daiani
 */
@Repository
public interface ModalidadeRepository extends JpaRepository <ModalidadeEntity, Integer>,  QuerydslPredicateExecutor<ModalidadeEntity> {




    
}

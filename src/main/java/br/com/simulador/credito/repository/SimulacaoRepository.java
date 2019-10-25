
package br.com.simulador.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.simulador.credito.entity.SimulacaoEntity;

/**
 *
 * @author Daiani
 */
@Repository
public interface SimulacaoRepository extends JpaRepository <SimulacaoEntity, Integer> {



    
}

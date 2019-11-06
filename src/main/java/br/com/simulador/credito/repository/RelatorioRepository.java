
package br.com.simulador.credito.repository;

import br.com.simulador.credito.entity.RelatorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daiani
 */
@Repository
public interface RelatorioRepository extends JpaRepository<RelatorioEntity, Integer>{
    
}


package br.com.simulador.credito.controller;

import br.com.simulador.credito.entity.SimulacaoEntity;
import br.com.simulador.credito.service.SimulacaoService;
import br.com.simulador.credito.vo.SimulacaoVO;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daiani
 */
@CrossOrigin
@RestController
public class SimulacaoController {
    
    @Autowired
    private SimulacaoService simulacaoService;

     //Método para listar todos as simulações 
    @GetMapping("/simulador-de-credito")
    public ResponseEntity<List<SimulacaoEntity>> listar() {
        List<SimulacaoEntity> simulacoes = simulacaoService.listarTodos();

        return new ResponseEntity<List<SimulacaoEntity>>(simulacoes, HttpStatus.OK);
    }

    //Método para listar simulacao por id
    @GetMapping("/simulador-de-credito/{id}")
    public ResponseEntity listarPorId(@PathVariable("id") Integer id) {

        SimulacaoEntity simulacao = this.simulacaoService.listarPorId(id);

        if (simulacao != null) {
            return new ResponseEntity(simulacao, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
        }
    }

    //Método para incluir simulação 
    @PostMapping("/simulador-de-credito")
    public ResponseEntity incluir(@RequestBody SimulacaoVO simulacao) {
        try {
            SimulacaoEntity entity = this.simulacaoService.salvar(simulacao);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

   
    //Método para excluir uma simulação 
    @DeleteMapping("/simulador-de-credito/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id) {
        try {
            this.simulacaoService.excluir(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

            //Exceção quando não encontra o id informado   
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

            //Exceção de violação de chave    
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());

            //Demais exceções 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
}
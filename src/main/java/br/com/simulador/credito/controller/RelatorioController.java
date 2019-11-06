
package br.com.simulador.credito.controller;

import br.com.simulador.credito.entity.RelatorioEntity;
import br.com.simulador.credito.service.RelatorioService;
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
public class RelatorioController {
    
    @Autowired
    private RelatorioService relatorioService;
    
    
      //Método para listar todos os relatórios
    @GetMapping("/admin/relatorios")
    public ResponseEntity<List<RelatorioEntity>> listar() {
        List<RelatorioEntity> relatorios = relatorioService.listarTodos();

        return new ResponseEntity<List<RelatorioEntity>>(relatorios, HttpStatus.OK);
    }

    //Método para listar relatório por id
    @GetMapping("/admin/relatorios/{id}")
    public ResponseEntity listarPorId(@PathVariable("id") Integer id) {

        RelatorioEntity relatorio = this.relatorioService.listarPorId(id);

        if (relatorio != null) {
            return new ResponseEntity(relatorio, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
        }
    }

    //Método para incluir relatorio
    @PostMapping("/admin/relatorios")
    public ResponseEntity incluir(@RequestBody RelatorioEntity relatorio) {
        try {
            this.relatorioService.salvar(relatorio);
            return ResponseEntity.status(HttpStatus.OK).body(relatorio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Método para atualizar relatório 
    @PutMapping("/admin/relatorios/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Integer id, @RequestBody RelatorioEntity relatorio) {
        try {
            RelatorioEntity found = this.relatorioService.listarPorId(id);

            if (found != null) {
                relatorio.setId(id);

                this.relatorioService.salvar(relatorio);
                return new ResponseEntity(relatorio, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Método para excluir um relatorio
    @DeleteMapping("/admin/relatorios/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id) {
        try {
            this.relatorioService.excluir(id);
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

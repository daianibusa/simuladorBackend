package br.com.simulador.credito.controller;

import br.com.simulador.credito.entity.ModalidadeEntity;
import br.com.simulador.credito.entity.TaxaEntity;
import br.com.simulador.credito.service.ModalidadeService;
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
public class ModalidadeController {

    @Autowired
    private ModalidadeService modalidadeService;

    //Método para listar todas as modalidades 
    @GetMapping("/admin/modalidades")
    public ResponseEntity<List<ModalidadeEntity>> listar() {
        List<ModalidadeEntity> modalidades = modalidadeService.listarTodos();

        return new ResponseEntity<List<ModalidadeEntity>>(modalidades, HttpStatus.OK);
    }

    //Método para listar modalidade por id
    @GetMapping("/admin/modalidades/{id}")
    public ResponseEntity listarPorId(@PathVariable("id") Integer id) {

        ModalidadeEntity modalidade = this.modalidadeService.listarPorId(id);

        if (modalidade != null) {
            return new ResponseEntity(modalidade, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
        }
    }

    //Método para incluir modalidade
    @PostMapping("/admin/modalidades")
    public ResponseEntity incluir(@RequestBody ModalidadeEntity modalidade) {
        for(TaxaEntity taxa: modalidade.getTaxas()){
            taxa.setModalidade(modalidade);
        }
        try {
            this.modalidadeService.salvar(modalidade);
            return ResponseEntity.status(HttpStatus.OK).body(modalidade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Método para atualizar modalidade 
    @PutMapping("/admin/modalidades/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Integer id, @RequestBody ModalidadeEntity modalidade) {
        try {
            ModalidadeEntity found = this.modalidadeService.listarPorId(id);

            if (found != null) {
                modalidade.setId(id);
                
                 for(TaxaEntity taxa: modalidade.getTaxas()){
                taxa.setModalidade(modalidade);
            }

                this.modalidadeService.salvar(modalidade);
                return new ResponseEntity(modalidade, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Método para excluir uma modalidade
    @DeleteMapping("/admin/modalidades/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id) {
        try {
            this.modalidadeService.excluir(id);
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

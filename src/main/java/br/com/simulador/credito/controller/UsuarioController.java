package br.com.simulador.credito.controller;

import br.com.simulador.credito.entity.UsuarioEntity;
import br.com.simulador.credito.service.UsuarioService;
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
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Método para listar todos os usuários 
    @GetMapping("/admin/usuarios")
    public ResponseEntity<List<UsuarioEntity>> listar() {
        List<UsuarioEntity> usuarios = usuarioService.listarTodos();

        return new ResponseEntity<List<UsuarioEntity>>(usuarios, HttpStatus.OK);
    }

    //Método para listar usuário por id
    @GetMapping("/admin/usuarios/{id}")
    public ResponseEntity listarPorId(@PathVariable("id") Integer id) {

        UsuarioEntity usuario = this.usuarioService.listarPorId(id);

        if (usuario != null) {
            return new ResponseEntity(usuario, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
        }
    }

    //Método para incluir usuário
    @PostMapping("/admin/usuarios")
    public ResponseEntity incluir(@RequestBody UsuarioEntity usuario) {
        try {
            this.usuarioService.salvar(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Método para atualizar usuário 
    @PutMapping("/admin/usuarios/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Integer id, @RequestBody UsuarioEntity usuario) {
        try {
            UsuarioEntity found = this.usuarioService.listarPorId(id);

            if (found != null) {
                usuario.setId(id);

                this.usuarioService.salvar(usuario);
                return new ResponseEntity(usuario, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Método para excluir um usuário
    @DeleteMapping("/admin/usuarios/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id) {
        try {
            this.usuarioService.excluir(id);
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

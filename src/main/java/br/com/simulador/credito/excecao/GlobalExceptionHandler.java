/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.simulador.credito.excecao;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Daiani
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ResponseStatus(value=HttpStatus.EXPECTATION_FAILED, reason = "ERRO ESPERADO")
    @ExceptionHandler(ExcecaoEsperada.class)
    public String handleExcecaoEsperada(HttpServletRequest request, ExcecaoEsperada ex){
        return ex.getMessage();
    }
}




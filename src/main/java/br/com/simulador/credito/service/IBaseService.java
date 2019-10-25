/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.simulador.credito.service;

import br.com.simulador.credito.excecao.ExcecaoEsperada;
import java.util.List;

/**
 *
 * @author Daiani
 */
public interface IBaseService<T> {

    List<T> listarTodos();

    T listarPorId(Integer id);

    //T salvar(T entity) throws ExcecaoEsperada;

    void excluir(Integer id);

}

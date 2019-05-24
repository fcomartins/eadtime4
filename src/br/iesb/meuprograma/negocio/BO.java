package br.iesb.meuprograma.negocio;

import java.util.List;

/**
 * Interface que estabelece os métodos obrigatórios de persistência
 *
 * @author Francisco Martins
 * @matricula 16286101044
 * @param <T>
 */
public interface BO<T> {

    void validar(T entidade) throws NegocioException;

    void inserir(T entidade) throws NegocioException;

    void alterar(T entidade) throws NegocioException;

    void excluir(T entidade) throws NegocioException;

    T consultar(int id) throws NegocioException;

    List<T> listar() throws NegocioException;
}

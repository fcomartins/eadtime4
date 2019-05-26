package br.iesb.meuprograma.dados;

import java.util.List;

/**
 * Interface que estabelece os métodos padrão de persistência dos dados.
 *
 * @author Francisco Martins
 * @matricula 16286101044
 * @param <T>
 */
public interface DAO<T> {

    void inserir(T entidade) throws DadosException;

    void alterar(T entidade) throws DadosException;

    void excluir(T entidade) throws DadosException;

    T consultar(int id) throws DadosException;

    List<T> listar() throws DadosException;
}

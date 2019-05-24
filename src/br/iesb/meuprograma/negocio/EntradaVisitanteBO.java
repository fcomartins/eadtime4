/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.negocio;

import br.iesb.meuprograma.dados.DadosException;
import br.iesb.meuprograma.dados.EntradaVisitanteDAO;
import br.iesb.meuprograma.entidades.EntradaVisitante;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 *
 * @author giovanna
 */
public class EntradaVisitanteBO implements BO<EntradaVisitante> {

    @Override
    public void validar (EntradaVisitante entidade) throws NegocioException {
    int codigo = 1;
    entidade.setId(codigo);
    String cpf = entidade.getCpf().trim();
    if (cpf.isEmpty()){
    throw new NegocioException (" O Campo CPF é Obrigatório.");
    } else if (!isNumber(cpf)) {
    throw new NegocioException ("O Campo CPF Deve Conter Apenas Números");
    }
    if (entidade.getRg().trim().isEmpty()){
    throw new NegocioException(" O Campo RG é obrigatório.");
    } 
    if (entidade.getApartamento().trim().isEmpty()){
    throw new NegocioException("O Campo APARTAMENTO é obrigatório");
    }
    
    }

    @Override
    public void inserir(EntradaVisitante entidade) throws NegocioException {
    validar(entidade);
        try {
            getEntradaVisitanteDAO().inserir(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(EntradaVisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(EntradaVisitante entidade) throws NegocioException {
         validar(entidade);
        consultar(entidade.getId());
        validar(entidade);
        try {
            getEntradaVisitanteDAO().alterar(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(EntradaVisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void excluir(EntradaVisitante entidade) throws NegocioException {
      consultar(entidade.getId());
        try {
            getEntradaVisitanteDAO().excluir(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(EntradaVisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public EntradaVisitante consultar(int id) throws NegocioException {
    EntradaVisitante entradavisitante = new EntradaVisitante();
        try {
            entradavisitante = getEntradaVisitanteDAO().consultar(id);
            if (entradavisitante.getId() == 0) {
                throw new NegocioException("Condômino não encontrato");
            }
        } catch (DadosException ex) {
            Logger.getLogger(EntradaVisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }

        return entradavisitante;
    }   

    


    @Override
    public List<EntradaVisitante> listar() throws NegocioException {
    List<EntradaVisitante> lista = new ArrayList<>();
        try {
            lista = getEntradaVisitanteDAO().listar();
            if (lista.isEmpty()) {
                throw new NegocioException("Nenhum Visitante cadastrado");

            }
        } catch (DadosException ex) {
            Logger.getLogger(EntradaVisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("",ex);
        }
        return lista;
    }

    /**
     * @return uma instância da EntradaVisitanteDAO
     */
    private EntradaVisitanteDAO getEntradaVisitanteDAO() {
        return  EntradaVisitanteDAO.getInstance();
    }
}

    
    


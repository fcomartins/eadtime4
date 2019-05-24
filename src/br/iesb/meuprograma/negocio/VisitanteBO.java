/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.negocio;

import br.iesb.meuprograma.dados.DadosException;
import br.iesb.meuprograma.dados.EditVisitanteDAO;
import br.iesb.meuprograma.entidades.Visitante;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author anagloria
 */
public class VisitanteBO implements BO<Visitante> {

    @Override
    public void validar(Visitante entidade) throws NegocioException {
        if (entidade.getNome().isEmpty()) {
            throw new NegocioException("Campo Nome é obrigatório");
        } else {
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher = pattern.matcher(entidade.getNome());
            if (matcher.find()) {
                throw new NegocioException("Campo NOME não deve conter números");
            }
        }
        if (entidade.getNome().length() < 8) {
            throw new NegocioException("Inserir NOME completo");

        }
        if (entidade.getApartamento().isEmpty()){
            throw new NegocioException (" Campo Apartamento é obrigatório");
            
        }
        if (entidade.getRg().isEmpty()) {
            throw new NegocioException("Campo RG é obrigatório");
        }
        if (entidade.getCpf().isEmpty()) {
            throw new NegocioException("Campo CPF é obrigatório");
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
        if (entidade.getDataNasc()== null) {
            throw new NegocioException("Campo Data é obrigatório");
        }
        if(entidade.getSexo() == null || entidade.getSexo().isEmpty()){
           throw new NegocioException("Campo Sexo é obrigatório"); 
        }

    }

    @Override
    public void inserir(Visitante entidade) throws NegocioException {
        validar(entidade);
       // validar(entidade);
        try {
            getEditVisitanteDAO().inserir(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(VisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(Visitante entidade) throws NegocioException {
        validar(entidade);
        consultar(entidade.getId());
        validar(entidade);
        try {
            getEditVisitanteDAO().alterar(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(VisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    @Override
    public void excluir(Visitante entidade) throws NegocioException {
        consultar(entidade.getId());
        try {
            getEditVisitanteDAO().excluir(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(VisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public Visitante consultar(int id) throws NegocioException {
         Visitante visitante = new Visitante();
        try {
            visitante = getEditVisitanteDAO().consultar(id);
            if (visitante.getId() == 0) {
                throw new NegocioException("Condômino não encontrato");
            }
        } catch (DadosException ex) {
            Logger.getLogger(VisitanteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
        return new Visitante();
    }

    @Override
    public List<Visitante> listar() throws NegocioException {
        List<Visitante> lista = new ArrayList<>();
        try {
            lista = getEditVisitanteDAO().listar();
            if (lista.isEmpty()) {
                throw new NegocioException("Nenhum visitante cadastrado");
            }
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class.getName()).log(Level.SEVERE, null, ex);
           throw new NegocioException(ex.getMessage(), ex);
        }
        return lista;
       
    }

     private EditVisitanteDAO getEditVisitanteDAO() {
        return EditVisitanteDAO.getInstance();

}
}

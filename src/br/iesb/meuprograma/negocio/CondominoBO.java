package br.iesb.meuprograma.negocio;

import br.iesb.meuprograma.dados.CondominoDAO;
import br.iesb.meuprograma.dados.DadosException;
import br.iesb.meuprograma.entidades.Condomino;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por efetuar as validações e regras de negócio do objeto
 * Condomino.
 *
 * @author Francisco Martins
 * @matricula 16286101044
 */
public class CondominoBO implements BO<Condomino> {

    /**
     * Verifica se o conteúdo de uma string é apenas número.
     *
     * @param value
     * @return true, caso a string seja apenas número.
     */
    private boolean isNumber(String value) {
        return value.matches("[0-9]*");
    }

    @Override
    public void validar(Condomino entidade) throws NegocioException {
        int codigo = 1;
        entidade.setId(codigo);

        String apt = entidade.getApartamento().trim();
        if (apt.isEmpty()) {
            throw new NegocioException("O campo Apartamento é obrigatório.");
        } else if (!isNumber(apt)) {
            throw new NegocioException("O campo Apartamento deve conter apenas números.");
        }

        if (entidade.getNome().trim().isEmpty()) {
            throw new NegocioException("O campo Nome é obrigatório.");
        }

        if (entidade.getEmail()
                .trim().isEmpty()) {
            throw new NegocioException("O campo email é obrigatório.");
        }

        if (entidade.getCpf().trim().isEmpty()) {
            throw new NegocioException("O campo Cpf é obrigatório.");
        }

        //Efetuando a conversão de String para LocalDate
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (entidade.getDataNascimento() == null) {
            throw new NegocioException("O campo Data de Nascimento é obrigatório.");
        } else {
            try {
                LocalDate localData = entidade.getDataNascimento();
            } catch (Exception e) {
                throw new NegocioException("O campo Data de Nascimento não está no formato dd/MM/yyyy");
            }
        }

        if (entidade.getSexo() == null || entidade.getSexo().isEmpty()) {
            throw new NegocioException("O campo Sexo é obrigatório.");
        }

        if (entidade.getVaga().trim().isEmpty()) {
            throw new NegocioException("O campo Vaga é obrigatório.");
        }
    }

    @Override
    public void inserir(Condomino entidade) throws NegocioException {
        validar(entidade);
        try {
            getCondominoDAO().inserir(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(Condomino entidade) throws NegocioException {
        consultar(entidade.getId());
        validar(entidade);
        try {
            getCondominoDAO().alterar(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void excluir(Condomino entidade) throws NegocioException {
        consultar(entidade.getId());
        try {
            getCondominoDAO().excluir(entidade);
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public Condomino consultar(int id) throws NegocioException {
        Condomino condomino = new Condomino();
        try {
            condomino = getCondominoDAO().consultar(id);
            if (condomino.getId() == 0) {
                throw new NegocioException("Condômino não encontrato");
            }
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }

        return condomino;
    }

    @Override
    public List<Condomino> listar() throws NegocioException {
        List<Condomino> lista = new ArrayList<>();
        try {
            lista = getCondominoDAO().listar();
            if (lista.isEmpty()) {
                throw new NegocioException("Nenhum condômino cadastrado");
            }
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
        return lista;
    }

    /**
     * @return uma instância da CondominoDAO
     */
    private CondominoDAO getCondominoDAO() {
        return CondominoDAO.getInstance();
    }
}

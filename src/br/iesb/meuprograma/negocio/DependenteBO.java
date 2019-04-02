package br.iesb.meuprograma.negocio;

import br.iesb.meuprograma.dados.DadosException;
import br.iesb.meuprograma.dados.DependenteDAO;
import br.iesb.meuprograma.entidades.Dependente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Carlos Rodrigues
 */
public class DependenteBO implements BO<Dependente> {

    private boolean isNumber(String value) {
        return value.matches("[0-9]*");
    }

    @Override
    public void validar(Dependente entidade) throws NegocioException {
        int codigo = 1;
        entidade.setId(codigo);

        String apt = entidade.getApartamento().trim();
        if (apt.isEmpty()) {
            throw new NegocioException("O Campo Apartamento é Obrigatório.");
        } else if (!isNumber(apt)) {
            throw new NegocioException("O Campo Apartamento Deve Conter Apenas Números.");
        }

        if (entidade.getNome().trim().isEmpty()) {
            throw new NegocioException("O Campo Nome é Obrigatório.");
        }

        if (entidade.getCpf().trim().isEmpty()) {
            throw new NegocioException("O Campo Cpf é Obrigatório.");
        }

        if (entidade.getDataNascimento().trim().isEmpty()) {
            throw new NegocioException("O campo Data de Nascimento é obrigatório.");
        }

        if (entidade.getSexo()
                == null || entidade.getSexo().isEmpty()) {
            throw new NegocioException("O Campo Sexo é obrigatório.");
        }

        if (entidade.getRg()
                .trim().isEmpty()) {
            throw new NegocioException("O Campo RG é obrigatório.");
        }
    }

    @Override
    public void inserir(Dependente entidade) throws NegocioException {
        validar(entidade);
        try {
            getDependenteDAO().inserir(entidade);

        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(Dependente entidade) throws NegocioException {
        consultar(entidade.getId());
        validar(entidade);
        try {
            getDependenteDAO().alterar(entidade);

        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void excluir(Dependente entidade) throws NegocioException {
        consultar(entidade.getId());
        try {
            getDependenteDAO().excluir(entidade);

        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public Dependente consultar(int id) throws NegocioException {
        Dependente dependente = new Dependente();
        try {
            dependente = getDependenteDAO().consultar(id);
            if (dependente.getId() == 0) {
                throw new NegocioException("Dependente não encontrato");

            }
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("", ex);
        }

        return dependente;
    }

    @Override
    public List<Dependente> listar() throws NegocioException {
        List<Dependente> lista = new ArrayList<>();
        try {
            lista = getDependenteDAO().listar();
            if (lista.isEmpty()) {
                throw new NegocioException("Nenhum Dependente cadastrado");

            }
        } catch (DadosException ex) {
            Logger.getLogger(DependenteBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("", ex);
        }
        return lista;
    }

    /**
     * @return uma instância da DependenteDAO
     */
    private DependenteDAO getDependenteDAO() {
        return new DependenteDAO();
    }
}

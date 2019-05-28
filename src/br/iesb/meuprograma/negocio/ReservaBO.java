package br.iesb.meuprograma.negocio;

import br.iesb.meuprograma.dados.DadosException;
import br.iesb.meuprograma.dados.ReservaDAO;
import br.iesb.meuprograma.entidades.Reserva;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Tulio Monteiro
 */
public class ReservaBO implements BO<Reserva> {

    private boolean isNumber(String value) {
        return value.matches("[0-9]*");
    }

    @Override
    public void validar(Reserva entidade) throws NegocioException {
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
    public void inserir(Reserva entidade) throws NegocioException {
        validar(entidade);
        try {
            getReservaDAO().inserir(entidade);

        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void alterar(Reserva entidade) throws NegocioException {
        consultar(entidade.getId());
        validar(entidade);
        try {
            getReservaDAO().alterar(entidade);

        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

       @Override
    public void excluir(Reserva entidade) throws NegocioException {
        consultar(entidade.getId());
        try {
            getReservaDAO().excluir(entidade);

        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public Reserva consultar(int id) throws NegocioException {
        Reserva reserva = new Reserva();
        try {
            reserva = getReservaDAO().consultar(id);
            if (reserva.getId() == 0) {
                throw new NegocioException("Reserva não encontrada");

            }
        } catch (DadosException ex) {
            Logger.getLogger(CondominoBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("", ex);
        }

        return reserva;
    }

  @Override
    public List<Reserva> listar() throws NegocioException {
        List<Reserva> lista = new ArrayList<>();
        try {
            lista = getReservaDAO().listar();
            if (lista.isEmpty()) {
                throw new NegocioException("Nenhuma Reserva cadastrada");

            }
        } catch (DadosException ex) {
            Logger.getLogger(DependenteBO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("", ex);
        }
        return lista;
    }

    /**
     * @return uma instância da ReservaDAO
     */
    private ReservaDAO getReservaDAO() {
        return new ReservaDAO();
    }
}

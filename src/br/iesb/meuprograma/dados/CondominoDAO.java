/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.dados;

import br.iesb.meuprograma.entidades.Condomino;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência dos dados na tabela do banco de dados.
 *
 * @author Francisco Martins
 * @matricula 16286101044
 */
public class CondominoDAO implements DAO<Condomino> {

    private static CondominoDAO uniqueInstance;
    private final String TABLE_NAME = "SCO_CONDOMINO";

    List<Condomino> list = new ArrayList<>();

    /**
     * Definição do padrão singleton
     *
     * @return uma única instância do objeto
     */
    public static CondominoDAO getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CondominoDAO();
        }

        return uniqueInstance;
    }

    @Override
    public void inserir(Condomino entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(TABLE_NAME + "(");
        sql.append("APARTAMENTO, ");
        sql.append("NOME, ");
        sql.append("EMAIL, ");
        sql.append("CPF, ");
        sql.append("RG, ");
        sql.append("DATA_NASCIMENTO, ");
        sql.append("SEXO, ");
        sql.append("TELEFONE, ");
        sql.append("CELULAR, ");
        sql.append("VAGA");
        sql.append(")");
        sql.append("VALUES (?,?,?,?,?,?,?,?,?,?)");

        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, entidade.getApartamento());
            ps.setString(index++, entidade.getNome());
            ps.setString(index++, entidade.getEmail());
            ps.setString(index++, entidade.getCpf());
            ps.setString(index++, entidade.getRg());
            ps.setDate(index++, Date.valueOf(entidade.getDataNascimento()));
            ps.setString(index++, entidade.getSexo());
            ps.setString(index++, entidade.getTelefone());
            ps.setString(index++, entidade.getCelular());
            ps.setString(index++, entidade.getVaga());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao incluir condômino. Motivo: " + ex.getMessage());
        }
    }

    @Override
    public void alterar(Condomino entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(TABLE_NAME + " SET ");
        sql.append("APARTAMENTO = ?, ");
        sql.append("NOME = ?, ");
        sql.append("EMAIL = ?, ");
        sql.append("CPF = ?, ");
        sql.append("RG = ?, ");
        sql.append("DATA_NASCIMENTO = ?, ");
        sql.append("SEXO = ?, ");
        sql.append("TELEFONE = ?, ");
        sql.append("CELULAR = ?, ");
        sql.append("VAGA = ? ");
        sql.append("WHERE ID = ?");

        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, entidade.getApartamento());
            ps.setString(index++, entidade.getNome());
            ps.setString(index++, entidade.getEmail());
            ps.setString(index++, entidade.getCpf());
            ps.setString(index++, entidade.getRg());
            ps.setDate(index++, Date.valueOf(entidade.getDataNascimento()));
            ps.setString(index++, entidade.getSexo());
            ps.setString(index++, entidade.getTelefone());
            ps.setString(index++, entidade.getCelular());
            ps.setString(index++, entidade.getVaga());

            ps.setInt(index++, entidade.getId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao alterar condômino. Motivo: " + ex.getMessage());
        }

    }

    @Override
    public void excluir(Condomino entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ID = ?");

        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            int index = 1;
            ps.setInt(index++, entidade.getId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao excluir condômino. Motivo: " + ex.getMessage());
        }
    }

    @Override
    public Condomino consultar(int id) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ID = ?");

        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            int index = 1;
            ps.setInt(index++, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rsToObject(rs);
            }

            ps.close();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao consultar condômino. Motivo: " + ex.getMessage());
        }

        return null;
    }

    @Override
    public List<Condomino> listar() throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        List<Condomino> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);

        try {
            Statement ps = conexao.createStatement();

            ResultSet rs = ps.executeQuery(sql.toString());

            while (rs.next()) {
                lista.add(rsToObject(rs));
            }

            ps.close();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao listar condôminos. Motivo: " + ex.getMessage());
        }

        return lista;
    }

    /**
     * Cria um objeto {@link Condomino} e preencher com os dados do ResultSet.
     *
     * @param rs
     * @return objeto {@link Condomino} preenchido com os dados do ResultSet.
     * @throws SQLException
     */
    private Condomino rsToObject(ResultSet rs) throws SQLException {
        Condomino condomino = new Condomino();

        condomino.setApartamento(rs.getString("APARTAMENTO"));
        condomino.setNome(rs.getString("NOME"));
        condomino.setEmail(rs.getString("EMAIL"));
        condomino.setCpf(rs.getString("CPF"));
        condomino.setRg(rs.getString("RG"));
        condomino.setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO").toLocalDateTime().toLocalDate());
        condomino.setSexo(rs.getString("SEXO"));
        condomino.setTelefone(rs.getString("TELEFONE"));
        condomino.setCelular(rs.getString("CELULAR"));
        condomino.setVaga(rs.getString("VAGA"));

        return condomino;
    }
}

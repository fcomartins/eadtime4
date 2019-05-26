/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.dados;

import br.iesb.meuprograma.entidades.Condomino;
import br.iesb.meuprograma.entidades.Dependente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Rodrigues
 */
public class DependenteDAO implements DAO<Dependente> {

    private final String TABLE_NAME = "SCO_DEPENDENTE";

    @Override
    public void inserir(Dependente entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(TABLE_NAME + "(");
        sql.append("APARTAMENTO, ");
        sql.append("NOME, ");
        sql.append("CPF, ");
        sql.append("RG, ");
        sql.append("DATA_NASCIMENTO, ");
        sql.append("SEXO, ");
        sql.append("CONDOMINO");
        sql.append(")");
        sql.append("VALUES (?,?,?,?,?,?,?)");

        try {
            PreparedStatement comando = conexao.prepareStatement(sql.toString());

            int i = 0;

            comando.setInt(i++, entidade.getId());
            comando.setString(i++, entidade.getNome());
            comando.setString(i++, entidade.getApartamento());
            comando.setString(i++, entidade.getCondomino());
            comando.setString(i++, entidade.getCpf());
            comando.setString(i++, entidade.getRg());
            comando.setString(i++, entidade.getDataNascimento());
            comando.setString(i++, entidade.getSexo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao incluir Dependente. Motivo: " + ex.getMessage());
        }
    }

    @Override
    public void alterar(Dependente entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(TABLE_NAME + " SET ");
        sql.append("APARTAMENTO = ?, ");
        sql.append("NOME = ?, ");
        sql.append("CPF = ?, ");
        sql.append("RG = ?, ");
        sql.append("DATA_NASCIMENTO = ?, ");
        sql.append("SEXO = ?, ");
        sql.append("CONDOMINO = ? ");
        sql.append("WHERE ID = ?");

        try {
            PreparedStatement comando = conexao.prepareStatement(sql.toString());

            int i = 0;

            comando.setString(i++, entidade.getApartamento());
            comando.setString(i++, entidade.getNome());
            comando.setString(i++, entidade.getCpf());
            comando.setString(i++, entidade.getRg());
            comando.setString(i++, entidade.getSexo());
            comando.setString(i++, entidade.getCondomino());
            comando.setInt(i++, entidade.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao alterar Dependente. Motivo: " + ex.getMessage());
        }
    }

    @Override
    public void excluir(Dependente entidade) throws DadosException {

        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ID = ?");

        try {
            PreparedStatement comando = conexao.prepareStatement(sql.toString());
            comando.setInt(1, entidade.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            throw new DadosException("Erro ao excluir Dependente. Motivo: " + ex.getMessage());
        }
    }

    @Override
    public Dependente consultar(int id) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ID = ?");

        try {
            PreparedStatement comando = conexao.prepareStatement(sql.toString());
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                return rsToObject(resultado);
            }
        } catch (SQLException ex) {
            throw new DadosException("Erro ao consultar Dependente.Motivo: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Dependente> listar() throws DadosException {
        Connection conexao = ConexaoBD.getConexao();

        List<Dependente> lista = new ArrayList<>();

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
    private Dependente rsToObject(ResultSet rs) throws SQLException {
        Dependente dependente = new Dependente();

        dependente.setApartamento(rs.getString("APARTAMENTO"));
        dependente.setNome(rs.getString("NOME"));
        dependente.setCpf(rs.getString("CPF"));
        dependente.setRg(rs.getString("RG"));
        dependente.setDataNascimento(rs.getString("DATA_NASCIMENTO"));
        dependente.setSexo(rs.getString("SEXO"));
        return dependente;
    }
}

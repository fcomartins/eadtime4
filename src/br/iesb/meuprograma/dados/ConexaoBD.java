package br.iesb.meuprograma.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por configurar o drive e conexão com o banco de dados
 * PostgresSQL
 *
 * @author Francisco Martins
 * @matricula 16286101044
 */
public final class ConexaoBD {

    public static Connection getConexao() throws DadosException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/condomino", "postgres", "123456");
        } catch (ClassNotFoundException e) {
            throw new DadosException("Erro ao carregar JDBC: " + e.getMessage());
        } catch (SQLException e) {
            throw new DadosException("Erro ao conectar com SGBD: " + e.getMessage());
        }
    }
}

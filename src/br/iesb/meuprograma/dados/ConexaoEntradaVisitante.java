/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author giovanna
 */
public final class ConexaoEntradaVisitante {
    public static Connection getConexao() throws DadosException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection ("jdbc:postgresql://localhost:5432/banco_entradavisitante","giovanna","banco");
        } catch (ClassNotFoundException e) {
            throw new DadosException ("Erro ao carregar JDBC: " + e.getMessage());
        } catch (SQLException e) {
            throw new DadosException ("Erro ao conectar com SGBD:  " + e.getMessage());
        }
            
        
        
        
        
        }
    
    }


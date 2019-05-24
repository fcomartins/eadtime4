/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.dados;

import br.iesb.meuprograma.entidades.Condomino;
import br.iesb.meuprograma.entidades.EntradaVisitante;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.crypto.Data;

/**
 *
 * @author giovanna
 */
public abstract class EntradaVisitanteDAO implements DAO<EntradaVisitante>{
 
    private static EntradaVisitanteDAO uniqueInstance;
    private final String TABLE_NAME = "SCO_ENTRADAVISITANTE";
    
    List<EntradaVisitante> list = new ArrayList<>();
    
    public static EntradaVisitanteDAO getInstance(){
        if (uniqueInstance == null) {
            uniqueInstance = new EntradaVisitanteDAO() {};
        }
        return uniqueInstance;
    }
    
    @Override
    public void inserir(EntradaVisitante entidade) throws DadosException {
    Connection conexao = null;
        try {
            conexao = ConexaoEntradaVisitante.getConexao ();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EntradaVisitanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    StringBuilder sql = new StringBuilder ("INSERT INTO");
    sql.append (TABLE_NAME + "(");
    sql.append ("VISITANTE, ");
    sql.append ("CPF, ");
    sql.append ("RG, ");
    sql.append ("APARTAMENTO, ");
    sql.append ("CONDOMINO, ");
    sql.append ("DATA, ");
    sql.append (")");
    sql.append ("VALUES (?,?,?,?,?,?,?)");
    try {
    PreparedStatement ps = conexao.prepareStatement (sql.toString());
    int index = 1;
    ps.setString(index++, entidade.getVisitante());
    ps.setString (index++, entidade.getCpf());
    ps.setString(index++,entidade.getRg());
    ps.setString(index++, entidade.getApartamento());
    ps.setString(index++, entidade.getCondomino());
    ps.setDate(index++, Date.valueOf(entidade.getData()));
    ps.executeUpdate();
    ps.close();
    } catch (SQLException ex) {
    throw new DadosException ("Erro ao incluir visitante. Motivo: " + ex.getMessage());
    
    }
    
    }

    @Override
    public void alterar(EntradaVisitante entidade) throws DadosException {
    Connection conexao = null;
        try {
            conexao = ConexaoEntradaVisitante.getConexao ();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EntradaVisitanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    StringBuilder sql = new StringBuilder ("UPDATE");
    sql.append (TABLE_NAME + " SET ");
    sql.append ("VISITANTE = ?, ");
    sql.append ("CPF = ?, ");
    sql.append ("RG = ?, ");
    sql.append ("APARTAMENTO = ?, ");
    sql.append ("CONDOMINO = ?, ");
    sql.append ("DATA = ?, ");
    sql.append ("WHERE ID = ?");
    try { PreparedStatement ps = conexao.prepareStatement (sql.toString());
    int index = 1;
    ps.setString(index++, entidade.getVisitante());
    ps.setString (index++, entidade.getCpf());
    ps.setString(index++,entidade.getRg());
    ps.setString(index++, entidade.getApartamento());
    ps.setString(index++, entidade.getCondomino());
    ps.setDate(index++, Date.valueOf(entidade.getData()));
    ps.setInt(index++,entidade.getId());
    ps.executeUpdate();
    ps.close();

} catch (SQLException ex) {
    throw new DadosException ("Erro ao alterar visitante. Motivo: " + ex.getMessage());
    
    }
        
    }

    @Override
    public void excluir(EntradaVisitante entidade) throws DadosException {
        Connection conexao = null;
        try {
            conexao = ConexaoEntradaVisitante.getConexao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EntradaVisitanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            throw new DadosException("Erro ao excluir visitante. Motivo: " + ex.getMessage());
        }
    }

   

    


    @Override
    public EntradaVisitante consultar(int id) throws DadosException {
    Connection conexao = ConexaoEntradaVisitante.getConexao();

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
            throw new DadosException("Erro ao consultar visitante. Motivo: " + ex.getMessage());
        }

        return null;
    }


    @Override
    public List<EntradaVisitante> listar() throws DadosException {
    Connection conexao = ConexaoEntradaVisitante.getConexao();

        List<EntradaVisitante> lista = new ArrayList<>();

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
            throw new DadosException("Erro ao listar visitantes. Motivo: " + ex.getMessage());
        }

        return lista;
    }

  private EntradaVisitante rsToObject (ResultSet rs) throws SQLException {
    EntradaVisitante entradavisitante = new EntradaVisitante ();
    entradavisitante .setVisitante (rs . getString (" VISITANTE "));
    entradavisitante .setCpf (rs . getString (" CPF "));
    entradavisitante .setRg (rs . getString (" RG "));
    entradavisitante .setApartamento (rs . getString (" APARTAMENTO "));
    entradavisitante .setCondomino (rs . getString (" CONDOMINO "));
    entradavisitante .setData (rs . getTimestamp("DATA").toLocalDateTime().toLocalDate());
    return entradavisitante;

}  

}

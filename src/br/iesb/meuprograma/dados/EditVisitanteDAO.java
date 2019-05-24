/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.dados;

import br.iesb.meuprograma.entidades.Visitante;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class EditVisitanteDAO implements DAO<Visitante>{
  private static EditVisitanteDAO uniqueInstance;
  private final String TABLE_NAME = "public.visitante";
  List <Visitante> list = new ArrayList<>();
  
  public  static  EditVisitanteDAO  getInstance () {
        
      if (uniqueInstance ==  null ) {
          EditVisitanteDAO novo;
            uniqueInstance = new EditVisitanteDAO();
        }

        return uniqueInstance;
    }

    @Override
    public  void  inserir ( Visitante  entidade ) throws  DadosException {
        Connection conexao = ConexaoBD.getConexao ();

        StringBuilder sql =  new  StringBuilder ( " INSERT INTO " );
        sql.append ( TABLE_NAME +  " ( " );
        sql.append ( " ID, " );
        sql.append ( " APARTAMENTO, " );
        sql.append ( " NOME, " );
        sql.append ( " CPF, " );
        sql.append ( " RG, " );
        sql.append ( " DATA_NASCIMENTO, " );
        sql.append ( " SEXO " );
        sql.append ( " ) " );
        sql.append ( " VALUES (DEFAULT,?,?,?,?,?,?) " );

        //System.out.println(sql);
        
        try{
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            int index = 1;
            ps.setString (index++, entidade.getApartamento ());
            ps.setString (index++, entidade.getNome ());
            ps.setString (index ++ , entidade . getCpf ());
            ps.setString (index ++ , entidade . getRg ());
            ps.setDate (index ++ , Date . valueOf (entidade . getDataNasc ()));
            ps.setString (index ++ , entidade . getSexo ());
            
            ps.executeUpdate ();
            ps.close();

        } 
       catch ( SQLException ex) {
            throw  new  DadosException ( " Erro ao incluir visitante. Motivo: "  + ex . getMessage ());
        }
    }

    @Override
    public  void  alterar ( Visitante  entidade ) throws  DadosException {
        Connection conexao =  ConexaoBD.getConexao ();

         StringBuilder sql =  new  StringBuilder ( " INSERT INTO " );
        sql.append ( TABLE_NAME  +  " ( " );
        sql.append ( " NOME, " );
         sql.append ( " APARTAMENTO, " );
        sql.append ( " CPF, " );
        sql.append ( " RG, " );
        sql.append ( " DATA_NASCIMENTO, " );
        sql.append ( " SEXO " );
        sql.append ( " WHEREN ID = ?");
       // System.out.println(sql);

        try{
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            int index = 1;
           
            ps.setString (index++, entidade.getNome ());
            ps.setString (index++, entidade.getApartamento ());
            ps.setString (index ++ , entidade . getCpf ());
            ps.setString (index ++ , entidade . getRg ());
            ps.setDate (index ++ , Date . valueOf (entidade . getDataNasc ()));
            ps.setString (index ++ , entidade . getSexo ());
        
            ps.setInt(index++, entidade.getId());
            ps.close();

        } 
        catch( SQLException ex) {
            throw new  DadosException ( " Erro ao alterar visitane. Motivo: "  + ex . getMessage ());
        }

    }

    @Override
    public  void  excluir ( Visitante entidade ) throws  DadosException {
        Connection conexao =  ConexaoBD.getConexao ();

        StringBuilder sql =  new  StringBuilder ( " DELETE FROM " );
        sql . append ( TABLE_NAME );
        sql . append ( " WHERE ID =? " );

        try {
            PreparedStatement ps = conexao . prepareStatement (sql.toString());
            int index =  1 ;
            ps.setInt (index ++ , entidade . getId ());

            ps.executeUpdate ();
            ps.close();

        } catch ( SQLException ex) {
            throw  new  DadosException ( " Erro ao excluir visitante. Motivo: "  + ex . getMessage ());
        }
    }

    @Override
    public  Visitante  consultar ( int id ) throws  DadosException {
        Connection conexao =  ConexaoBD.getConexao ();
        
        StringBuilder sql =  new  StringBuilder ();
        sql . append ( " SELECT * FROM " );
        sql . append ( TABLE_NAME );
        sql . append ( " WHERE ID =? " );
       
        
        try {
            PreparedStatement ps = conexao . prepareStatement (sql.toString ());
             int index =  1 ;
            ps . setInt (index ++ , id);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next ()) {
                return rsToObject(rs);
            }
            
            ps.close();

        } catch ( SQLException ex) {
            throw  new  DadosException ( " Erro ao excluir visitante. Motivo: "  + ex . getMessage ());
        }
      
        return  null ;
    }

    @Override
     public List<Visitante> listar() throws  DadosException {
        Connection conexao =  ConexaoBD.getConexao();

        List<Visitante> lista = new ArrayList<>();
        
        StringBuilder sql =  new StringBuilder();
        sql.append( " SELECT * FROM " );
        sql.append( TABLE_NAME );
       
               
        try {
            Statement ps = conexao.createStatement();

            ResultSet rs = ps.executeQuery(sql.toString());
            
               
            
            while (rs.next()) {
                lista.add(rsToObject(rs));
                
            }
            
            ps.close();

        } catch ( SQLException ex) {
            throw  new  DadosException ( " Erro ao excluir visitante. Motivo: "  + ex.getMessage ());
        }
      
        return lista;
    }
    
     
    
    private  Visitante  rsToObject (ResultSet rs) throws SQLException {
        Visitante visitante =  new Visitante ();
        
        visitante .setId (rs . getInt ( " ID, " ));
        visitante .setApartamento (rs . getString ( " APARTAMENTO, " ));
        visitante .setNome (rs . getString ( " NOME, " ));
        visitante .setCpf (rs . getString ( " CPF, " ));
        visitante .setRg (rs . getString ( " RG, " ));
        visitante.setData (rs.getTimestamp("DATA_NASCIMENTO,").toLocalDateTime().toLocalDate());
        visitante.setSexo(rs.getString("SEXO"));
        return visitante;
        
    }

  
  
}

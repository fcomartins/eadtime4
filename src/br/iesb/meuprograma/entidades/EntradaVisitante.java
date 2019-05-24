/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.meuprograma.entidades;

import java.time.LocalDate;

/**
 *
 * @author giovanna
 */
public class EntradaVisitante {
    private int id;
    private String visitante;
    private String cpf;
    private String rg;
    private String apartamento;
    private String condomino;
    private LocalDate data;
    
    public EntradaVisitante() {
    }
    
    public EntradaVisitante(int id){
    this.id = id;
    }
    public Integer getId(){
    return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getVisitante() {
        return visitante;
    }
    public void setVisitante(String visitante) {
            this.visitante = visitante;

    }
    public String getCpf(){
    return cpf;
    }
    public void setCpf (String cpf){
    this.cpf = cpf;
    }
    public String getRg(){
        return rg;
    }
    public void setRg (String rg) {
    this.rg = rg;
    }
    public String getApartamento() {
        return apartamento;
    }
    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }
    public String getCondomino() {
        return condomino;
    }
    public void setCondomino(String condomino) {
        this.condomino = condomino;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData (LocalDate data) {
        this.data = data;
    }

    
}

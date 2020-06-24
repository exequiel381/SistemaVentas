/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author FIume
 */
public class Detalle {
    private String Periodo;
    private String Proximo_cobro;
    private String Banco;
    
    public Detalle(String Periodo,String Proximo_cobro,String Banco){
        this.Banco=Banco;
        this.Periodo=Periodo;
        this.Proximo_cobro=Proximo_cobro;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
    }

    public String getProximo_cobro() {
        return Proximo_cobro;
    }

    public void setProximo_cobro(String Proximo_cobro) {
        this.Proximo_cobro = Proximo_cobro;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }
    
    
}

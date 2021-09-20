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
public class Concepto {
    private int idConcepto;
    private String Detalle;
   private double porcentaje;
   private String Tipo;

    public Concepto() {
    }

    
    
    public Concepto(int idConcepto, String Detalle, double porcentaje) {
        this.idConcepto = idConcepto;
        this.Detalle = Detalle;
        this.porcentaje = porcentaje;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
    
    
    
}

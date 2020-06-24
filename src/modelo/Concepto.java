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
    private int Codigo;
    private String Detalle;
    private double monto ;
    private double haberes;
    private double deducciones;
    
    private Concepto(int Codigo,String Detalle,double monto,double haberes,double deducciones){
        
        this.Codigo=Codigo;
        this.Detalle=Detalle;
        this.deducciones=deducciones;
        this.haberes=haberes;
        this.monto=monto;
        
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getHaberes() {
        return haberes;
    }

    public void setHaberes(double haberes) {
        this.haberes = haberes;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(double deducciones) {
        this.deducciones = deducciones;
    }
    
    
}

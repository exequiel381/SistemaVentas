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
public class Liquidacion {
    
    private Empleado empleado;
    private double neto_cobrar; 
    
    public Liquidacion(Empleado empleado,double neto_cobrar){
        this.empleado=empleado;
        this.neto_cobrar=neto_cobrar;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public double getNeto_cobrar() {
        return neto_cobrar;
    }

    public void setNeto_cobrar(double neto_cobrar) {
        this.neto_cobrar = neto_cobrar;
    }
    
    
}

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
public class Empleado extends Persona {
    
    private String fecha_ingreso;
    private String fecha_salida;
    private String Cuil;
    private int Sueldo;
    private int Antiguedad;
    private String Seccion;
    
    
    public Empleado(){
        
    }
    public Empleado(int dni, String nombre, String apellido,String telefono,String Direccion,String fecha_ingreso,String fecha_salida,String Cuil, int sueldo, int Antiguedad,String Seccion,Localidad localidad){
        super(dni,nombre,apellido,telefono,Direccion,localidad);
        this.Cuil=Cuil;
        this.Sueldo=sueldo;
        this.fecha_ingreso=fecha_ingreso;
        this.fecha_salida=fecha_salida;
        this.Antiguedad=Antiguedad;
        this.Seccion=Seccion;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Sector) {
        this.Seccion = Seccion;
    }
    

    public String getCuil() {
        return Cuil;
    }

    public void setCuil(String Cuil) {
        this.Cuil = Cuil;
    }

    public int getSueldo() {
        return Sueldo;
    }

    public void setSueldo(int Sueldo) {
        this.Sueldo = Sueldo;
    }

    public int getAntiguedad() {
        return Antiguedad;
    }

    public void setAntiguedad(int Antiguedad) {
        this.Antiguedad = Antiguedad;
    }
    
    
    
    

    

    @Override
    public String toString() {
        return "Empleado{" +super.toString()+ "fecha_ingreso=" + fecha_ingreso + ", fecha_salida=" + fecha_salida + ", Cuil=" + Cuil + ", Sueldo=" + Sueldo + '}';
    }
    
}

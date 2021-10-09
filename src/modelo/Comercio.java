/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author FIume
 */
public class Comercio {
    
    private String Nombre;
    private String Direccion;
    private String Telefono;
    private String Cuit;
    
    public Comercio(){
        
    }

    public Comercio(String Nombre, String Direccion, String Telefono, String Cuit) {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Cuit = Cuit;
    }
    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCuit() {
        return Cuit;
    }

    public void setCuit(String Cuit) {
        this.Cuit = Cuit;
    }
    
     public ArrayList<BoletaSueldo> CrearBoletasSueldo(ArrayList<Empleado> empleados, ArrayList<Concepto> conceptos,ArrayList<Novedad> novedades,int mes, int anio,int ultimoIdBoleta){
        ArrayList<BoletaSueldo> boletas = new ArrayList<>();
        int idBoleta = ultimoIdBoleta;
        for (Empleado e : empleados) {
            idBoleta+=1;
            BoletaSueldo bs = new BoletaSueldo();
            bs.setIdBoleta(idBoleta);
            bs.setAnio(anio);
            bs.setMes(mes);
            bs.setEmpleado(e);
            bs.setBruto(e.getSueldo());
            bs.GenerarDetalle(conceptos,novedades);
            boletas.add(bs);
        }
        
        
        return boletas;
    }
    
    
}

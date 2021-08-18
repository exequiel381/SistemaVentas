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
public class Localidad {
    
    private String Nombre;
    private String Provincia;
    private int idLocalidad;
    private int codigopostal;
    
    public Localidad(){
        
    }
    
    public Localidad(int idLocalidad,String nombre, String Provincia,int codigopostal){
        this.idLocalidad=idLocalidad;
        this.Nombre=nombre;
        this.Provincia = Provincia;
        this.codigopostal = codigopostal;
    }

    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    
    
    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }
    
    
}

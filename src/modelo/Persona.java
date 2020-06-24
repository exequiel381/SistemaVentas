/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author sistemas
 */
public abstract class Persona {
    private int dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    public Localidad localidad = new Localidad();
    
    public Persona(){}
    
    public Persona(int dni, String nombre, String apellido,String telefono,String Direccion,Localidad localidad){
        this.dni=dni;
        this.nombre=nombre;
        this.apellido=apellido;
        this.localidad=localidad;
        this.direccion=direccion;
        this.telefono=telefono;
        
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

   
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String domicilio) {
        this.direccion = domicilio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

   

    @Override
    public String toString() {
        return "dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", domicilio=" + direccion + ", telefono=" + telefono ;
    }
    
}

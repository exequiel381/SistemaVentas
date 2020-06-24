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
public class Autenticacion {
    
    private String usuario="";
    private String pass="";
    
    private String usuario_ingresado;
    private String pass_ingresado;
    
    private int Dni;
    
    public Autenticacion(int Dni){
        this.Dni=Dni;
    }
    public Autenticacion(){
       
    }
    
    public Autenticacion(String Usuario,String pass){
        this.usuario_ingresado=Usuario;
        this.pass_ingresado=pass;
        
    }
    
    public boolean Ingreso(){
        
        if(usuario_ingresado.equalsIgnoreCase(usuario) && pass_ingresado.equals(pass))
        return true;
        else return false;
    }

    public int getDni() {
        return Dni;
    }

    public void setDni(int Dni) {
        this.Dni = Dni;
    }
    
    
}

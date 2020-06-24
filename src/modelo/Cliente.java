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
public class Cliente extends Persona{

    public Cliente(int Dni,String Nombre,String Apellido,String telefono,String direccion,Localidad localidad){
        super(Dni,Nombre,Apellido,telefono,direccion,localidad);
    }
}

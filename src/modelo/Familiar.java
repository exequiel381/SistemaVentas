/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author fiume
 */
public class Familiar extends Persona{
    private Empleado _empleado;
    private String _parentesco;

    public Familiar(Empleado _empleado, int dni, String nombre, String apellido, String telefono, String Direccion,String parentesco) {
        super(dni, nombre, apellido, telefono, Direccion);
        this._empleado = _empleado;
        this._parentesco=parentesco;
    }

    public Familiar(String _parentesco, int dni, String nombre, String apellido, String direccion, String telefono) {
        super(dni, nombre, apellido, direccion, telefono);
        this._parentesco = _parentesco;
    }
    
    

    public Empleado getEmpleado() {
        return _empleado;
    }

    public void setEmpleado(Empleado _empleado) {
        this._empleado = _empleado;
    }

    public String getParentesco() {
        return _parentesco;
    }

    public void setParentesco(String _parentesco) {
        this._parentesco = _parentesco;
    }
    
    
    
    
    
    
}

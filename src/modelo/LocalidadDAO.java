/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;

/**
 *
 * @author FIume
 */
public class LocalidadDAO {
    
    private Localidad localidad;
    private Conexion con ;
    
    public LocalidadDAO(Localidad localidad,Conexion con){
        this.con=con;
        this.localidad=localidad;
    }
    
     public void agregarLocalidad(){
        
        
        
        try{
           String sql = "INSERT INTO localidad SET idLocalidad='"+localidad.getIdLocalidad()+"',"
                    + "Nombre='"+localidad.getNombre()+ "',"
                    +"Provincia_idProvincia='"+1+"'";
             
           
            con.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("error en agregar localidad");
        }
    }
    
    
}

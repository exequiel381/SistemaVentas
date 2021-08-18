/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Localidad;

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
     
     public ArrayList<Localidad> ObtnerLocalidades(){
         ArrayList<Localidad> lista = new ArrayList<>(); 
         try{
           String sql = "SELECT l.idLocalidad,l.Nombre,p.nombre,l.codigopostal FROM localidad as l,provincia as p where p.idProvincia = l.Provincia_idProvincia GROUP BY codigopostal";
           ResultSet fila = con.getConsulta().executeQuery(sql);
            
           while(fila.next()){
                
                Localidad tmp = new Localidad();
                
                tmp.setIdLocalidad(fila.getInt("l.idLocalidad") );
                tmp.setNombre(fila.getString("l.Nombre"));
                tmp.setProvincia(fila.getString("p.nombre"));
                tmp.setCodigopostal(fila.getInt("l.codigopostal"));
              
                lista.add(tmp);
            }
           
            
        }
        catch(SQLException e){
            System.out.println("error al leer datos de la tabla localidad o provincia");
            System.out.println(e);
        }
          
          return lista;
     }
     
     public Localidad ObtenerLocalidad(int id){
        try {
            ResultSet fila= con.getConsulta().executeQuery("select * from provincia as p , localidad as l where l.idLocalidad='"+id+"' and p.idProvincia=l.Provincia_idProvincia");
            
            if(fila.next()){
            
                Localidad tmp = new Localidad();
                
                tmp.setNombre(fila.getString("l.Nombre"));
            tmp.setProvincia(fila.getString("p.Nombre"));
            tmp.setCodigopostal(fila.getInt("l.codigopostal"));
            
            return tmp;
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LocalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
     }
    
    
}

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
import javax.swing.JOptionPane;
import modelo.Proveedor;

/**
 *
 * @author FIume
 */
public class ProveedorDAO {
    private Proveedor Proveedor;
    private Conexion con;
    
    public ProveedorDAO(Conexion con){
        this.con=con;
    }
    public ProveedorDAO(Proveedor Proveedor,Conexion con){
        this.Proveedor=Proveedor;
        this.con=con;
    }
    
    public void AgregarProveedor(){
        try {
            String sql = "INSERT INTO proveedores SET CUIL_CUIT='"+Proveedor.getCuit()+"', "
                    +"Razoc='"+Proveedor.getRazoc()+"', "
                    +"Direccion='"+Proveedor.getDireccion()+"', "
                    +"Telefono='"+Proveedor.getTelefono()+"',"
                    +"Localidad_idLocalidad='4000'";
            JOptionPane.showMessageDialog(null,"Proveedor Agregado");
            con.getConsulta().execute(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar Proveedor");
        }
    }
    
     public ArrayList<Proveedor> leer(){
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
        
        try{
            String sql = "SELECT Razoc FROM proveedores";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            while(fila.next()){
                Proveedor tmp = new Proveedor();
                
                tmp.setRazoc(fila.getString("Razoc"));
               
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
     
     public Proveedor buscar(){
        try {
           
            
            //String sql = "SELECT CUIL_CUIT,razoc,direccion,telefono,localidad.Nombre FROM proveedores,Localidad WHERE Razoc ='"+Proveedor.getRazoc()+"' and localidad.idLocalidad=proveedores.Localidad_idLocalidad";
            
            String sql = "SELECT * FROM proveedores WHERE Razoc ='"+Proveedor.getRazoc()+"'";
            
            
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            if(fila.next()){
                Proveedor tmp = new Proveedor();
                
                
                tmp.setCuit(fila.getString("CUIL_CUIT"));
                tmp.setRazoc(fila.getString("razoc"));
                tmp.setDireccion(fila.getString("direccion"));
                tmp.setTelefono(fila.getString("Telefono"));
                
                
                return tmp;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return null;
     }
     
     
}

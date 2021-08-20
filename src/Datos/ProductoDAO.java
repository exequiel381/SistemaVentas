/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Producto;

public class ProductoDAO {
private Producto producto;    
private Conexion con;
    
    public ProductoDAO(Producto producto,Conexion con){
        this.producto=producto;
        this.con=con;
    }

    public ProductoDAO(Conexion con) {
        this.con=con;
    }
    
    public void AgregarProducto(){
        try{
            
            String sql ="INSERT INTO producto SET idProducto='"+producto.getIdProducto()+"', "
                    + "Descripcion='"+producto.getDescripcion()+"', "
                    + "PrecioU='"+producto.getPrecio()+ "', "
                    + "Stock_seguridad='"+1+"', "
                    + "Tipo='"+producto.getTipo()+"',"
                    + "Talle='"+producto.getTalle()+"',"
                    + "precioCompra='"+producto.getPrecioCompra()+"'";
            
            con.getConsulta().execute(sql);
           JOptionPane.showMessageDialog(null,"Producto Agregado");
        }
        catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error al agregar datos a la tabla, Asegurese de rellenar todos los campos");
        }
    }
    
    
    public Producto buscar(){
        try{
          
            String sql = "SELECT * FROM producto WHERE idProducto='"+producto.getIdProducto()+"' or Descripcion='"+producto.getDescripcion()+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                Producto tmp = new Producto();
                tmp.setIdProducto(fila.getString("idProducto"));
                tmp.setDescripcion(fila.getString("Descripcion"));
                tmp.setTipo(fila.getString("Tipo"));
                tmp.setPrecio(fila.getInt("PrecioU"));
                tmp.setPrecioCompra(fila.getFloat("precioCompra"));
                tmp.setStockSeguridad(fila.getInt("Stock_seguridad"));
                tmp.setTalle(fila.getString("Talle"));
                return tmp;
            }
            
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }    
        
        return null;
    }
    
    
    public ArrayList<Producto> leer(){
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try{
          
            String sql = "SELECT * FROM producto";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            while(fila.next()){
                Producto tmp = new Producto();
                tmp.setIdProducto(fila.getString("idProducto"));
                tmp.setDescripcion(fila.getString("Descripcion"));
                tmp.setTipo(fila.getString("Tipo"));
                tmp.setPrecio(fila.getInt("PrecioU"));
                tmp.setPrecioCompra(fila.getFloat("precioCompra"));
                tmp.setStockSeguridad(fila.getInt("Stock_seguridad"));
                tmp.setTalle(fila.getString("Talle"));
                
                lista.add(tmp);
                
            }
            return lista;
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }    
        
        return null;
    }
    
    
    
    
     
     public void modificar(){
        try{
            String sql = "UPDATE producto SET "
                    + "Descripcion='"+producto.getDescripcion()+"', "
                    + "PrecioU='"+producto.getPrecio()+ "', "
                    + "Stock_seguridad='"+1+"', "
                    + "Tipo='"+producto.getTipo()+"',"
                    + "Talle='"+producto.getTalle()+"',"
                    + "precioCompra='"+producto.getPrecioCompra()+"'"
                    +" WHERE idProducto='"+producto.getIdProducto().replaceAll(" ","")+"'";
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Producto Modificado");
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }        
    }
     
     
     
    public void borrar(){
        try{
            String sql = "DELETE FROM producto WHERE idProducto='"+producto.getIdProducto().replaceAll(" ","")+"'";
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Producto Eliminado");
        }
        catch(SQLException e){
            System.out.println("Error al borrar datos a la tabla");
        }        
    }
    
    
}

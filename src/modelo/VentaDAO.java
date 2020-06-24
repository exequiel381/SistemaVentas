/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FIume
 */
public class VentaDAO {
    private Venta venta;
    private Conexion con;
    
    public VentaDAO(Conexion con){
        this.con=con;
    }
    
    public VentaDAO(Venta venta,Conexion con){
        this.con=con;
        this.venta=venta;
    }
    
    public void Agregar(){
        try {
            String sql = "INSERT INTO venta SET idVenta='"+venta.getIdVenta()+"', "
                    +"Fecha='"+venta.getFecha()+"', "
                    +"Precio_Total='"+venta.getTotal()+"', "
                    +"Empleado_DNI='"+venta.getEmpleado().getDni()+"'";
                    
            
            
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Venta realizada");
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int UltimaVenta(){
        try {
            
            String sql = "SELECT MAX(idVenta) FROM venta";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next())
            return fila.getInt("MAX(idVenta)");
           
          
            
       } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }return 0;
    }
   
    
    public void AgregarLineasDeVenta(ArrayList<LineaDeVenta> LineasVenta,int idVenta){
        
        for(LineaDeVenta Linea : LineasVenta){
            try {
                String sql = "INSERT INTO lineaVenta SET Producto_idProducto='"+ Linea.getProducto().getCodigo()+"', "
                        +"Venta_idVenta='"+idVenta+"', "
                        +"Cantidad='"+Linea.getCantidad()+"', "
                        +"subTotal='"+Linea.getSubTotal()+"'";
                con.getConsulta().execute(sql);
               
            } catch (SQLException ex) {
                System.out.println("No se agrego la linea de venta");
                
                System.out.println(Linea.getProducto().getCodigo());
                System.out.println(idVenta);
                System.out.println(Linea.getCantidad());
                System.out.println(Linea.getSubTotal());
            }
            
        }
    }
    
     public ArrayList<Venta> leer(){
        ArrayList<Venta> lista = new ArrayList<Venta>();
        try{
            
            String sql = "SELECT * FROM empleado,venta WHERE  venta.Empleado_DNI=empleado.DNI";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            
            while(fila.next()){
                
                Venta tmp = new Venta();
                Empleado emp = new Empleado();
                
                emp.setDni(fila.getInt("DNI"));
                emp.setNombre(fila.getString("Nombre"));
                
                tmp.setIdVenta(fila.getInt("idVenta") );
                tmp.setFecha(fila.getString("Fecha"));
                tmp.setTotal(fila.getDouble("Precio_Total"));
                tmp.setEmpleado(emp);
                
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla PEDIDO");
        }        
        return lista;
    }
     
     
     public ArrayList<Venta> leer(String Desde, String Hasta){
        ArrayList<Venta> lista = new ArrayList<Venta>();
        try{
            
            String sql = "SELECT * FROM empleado,venta WHERE  venta.Empleado_DNI=empleado.DNI and venta.Fecha<='"+Hasta+"' and venta.Fecha>='"+Desde+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            
            while(fila.next()){
                
                Venta tmp = new Venta();
                Empleado emp = new Empleado();
                
                emp.setDni(fila.getInt("DNI"));
                emp.setNombre(fila.getString("Nombre"));
                
                tmp.setIdVenta(fila.getInt("idVenta") );
                tmp.setFecha(fila.getString("Fecha"));
                tmp.setTotal(fila.getDouble("Precio_Total"));
                tmp.setEmpleado(emp);
                
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla PEDIDO");
        }        
        return lista;
    }
     
      public ArrayList<LineaDeVenta> leerLineasVentas(){
         ArrayList<LineaDeVenta> lista = new ArrayList<LineaDeVenta>();
          try{
            
            String sql = "SELECT * FROM lineaventa,producto WHERE lineaventa.Venta_idVenta='"+venta.getIdVenta()+"' and lineaventa.Producto_idProducto=producto.idProducto";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            
            while(fila.next()){
                
                LineaDeVenta tmp = new LineaDeVenta();
                Producto pro = new Producto();
                
                pro.setCodigo(fila.getString("idProducto"));
                pro.setDescripcion(fila.getString("Descripcion"));
                pro.setPrecio(fila.getInt("PrecioU"));
                
                tmp.setIdLineaVenta(fila.getInt("idLineaVenta"));
                tmp.setProducto(pro);
                tmp.setCantidad(fila.getInt("Cantidad"));
                tmp.setSubTotal(fila.getDouble("subTotal"));
                
                
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla Linea Ventas");
        } 
         return lista;
    }
    
    
    
    
}

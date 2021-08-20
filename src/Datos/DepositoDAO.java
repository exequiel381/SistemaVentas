/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Fecha;
import modelo.Stock;
import modelo.Producto;

/**
 *
 * @author FIume
 */
public class DepositoDAO {
    
    private Stock deposito;
    private Conexion con;
    private int n;
    
    public DepositoDAO(Stock deposito,Conexion con){
        this.con=con;
        this.deposito=deposito;
    }
    public DepositoDAO(Conexion con){
       this.con=con;
    }
    
    public void AgregarProductoADeposito(){
        try {
            
            Fecha f = new Fecha();
            String sql = "INSERT INTO Stock SET "
                    +"Cantidad='"+deposito.getCantidad_producto()+"',"
                    +"UltimaActualizacion='"+f.toString()+"',"
                    +"Producto_idProducto='"+deposito.getProducto().getIdProducto()+"'";
         
            con.getConsulta().execute(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al agregar producto al Deposito");
        }
    }
    
    public ArrayList<Stock> leer(String Filtro){
        ArrayList<Stock> lista = new ArrayList<Stock>();
        String Filtrado = "";

        if (!"".equals(Filtro)) {
            try
            {
                Integer.parseInt(Filtro);   //Verifico que sea numero entero, si no puede hacer la conversion salta al catch
                Filtrado = " && producto.idProducto='"+Filtro+"'";
            }catch(Exception x){
                //Filtrado = "&& producto.Descripcion='"+Filtro+"'";
                Filtrado = "&& producto.Descripcion LIKE '%"+Filtro+"%'"; // Con el LIKE BUSCAMOS EN LA BASE DE DATOS textos que contengan esta palabra.
            }
                    
        }
        try{
            String sql = "SELECT Producto.idProducto,Producto.precioCompra,Producto.Talle,Producto.Descripcion,Producto.PrecioU,Stock.Cantidad,Stock.idStock FROM Producto,Stock WHERE Producto.idProducto = Stock.Producto_idProducto"+Filtrado;
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            while(fila.next()){
                
                Producto prod = new Producto();
                
                n=fila.getInt("Cantidad");
                
                
               
                prod.setIdProducto(fila.getString("idProducto") );
                prod.setDescripcion(fila.getString("Descripcion"));
                prod.setPrecio(fila.getInt("PrecioU"));
                prod.setTalle(fila.getString("talle"));
                prod.setPrecioCompra(fila.getFloat("precioCompra"));
                
                Stock tmp = new Stock(prod,n);
                
                tmp.setIdStock(fila.getInt("idStock"));
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }        
        return lista;
    }
    
     
    public void AgregarStock(int valor){
         int Valor = valor;
        try{
            String sql = "UPDATE Stock SET "
                    + "Cantidad='"+(deposito.getCantidad_producto()+valor)+"' WHERE Producto_idProducto='"+deposito.getProducto().getIdProducto()+"'";
        con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Stock Actualizado");
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }        
    }
    
    public void QuitarStock(int valor){
         int Valor = valor;
        try{
            String sql = "UPDATE Stock SET "
                    + "Cantidad='"+(deposito.getCantidad_producto()-valor)+"' WHERE Producto_idProducto='"+deposito.getProducto().getIdProducto()+"'";
        
            con.getConsulta().execute(sql);
            //JOptionPane.showMessageDialog(null,"Stock Actualizado");
            JOptionPane.showMessageDialog(null, "Stock Actualizado");
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla "+ e);
        }        
    }
     
    public boolean getDisponible(String Codigo, int cantidad){
        try {
            String sql = "SELECT Stock.Cantidad FROM Stock WHERE Producto_idProducto='"+Codigo+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                int Stock=fila.getInt("Cantidad");
                if(Stock>=cantidad) return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepositoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int getCantidadDeStock(String Codigo){
        try {
            String sql = "SELECT Stock.Cantidad FROM Stock WHERE Producto_idProducto='"+Codigo+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                int Stock=fila.getInt("Cantidad");
                return Stock;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepositoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
   
}

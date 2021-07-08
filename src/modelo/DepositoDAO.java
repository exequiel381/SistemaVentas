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
public class DepositoDAO {
    
    private Deposito deposito;
    private Conexion con;
    private int n;
    
    public DepositoDAO(Deposito deposito,Conexion con){
        this.con=con;
        this.deposito=deposito;
    }
    public DepositoDAO(Conexion con){
       this.con=con;
    }
    
    public void AgregarProductoADeposito(){
        try {
            String sql = "INSERT INTO deposito SET idDeposito='"+"1"+"', "
                    +"Stock='"+deposito.getCantidad_producto()+"',"//
                    +"Producto_idProducto='"+deposito.getProducto().getCodigo()+"'";//
         
            con.getConsulta().execute(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al agregar producto al Deposito");
        }
    }
    
    public ArrayList<Deposito> leer(String Filtro){
        ArrayList<Deposito> lista = new ArrayList<Deposito>();
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
            String sql = "SELECT Producto.idProducto,Producto.precioCompra,Producto.Talle,Producto.Descripcion,Producto.PrecioU,deposito.idDeposito,deposito.Stock FROM Producto,deposito WHERE Producto.Deposito_idDeposito = deposito.idDeposito"+Filtrado;
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            while(fila.next()){
                
                Producto prod = new Producto();
                
                n=fila.getInt("Stock");
                
                Deposito tmp = new Deposito(prod,n);
               
                prod.setCodigo(fila.getString("idProducto") );
                prod.setDescripcion(fila.getString("Descripcion"));
                prod.setPrecio(fila.getInt("PrecioU"));
                prod.setTamanio(fila.getString("talle"));
                prod.setPrecioCompra(fila.getFloat("precioCompra"));
                tmp.setIdDeposito(fila.getInt("idDeposito"));
                
               
                
                
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
            String sql = "UPDATE deposito SET "
                    + "Stock='"+(deposito.getCantidad_producto()+valor)+"' WHERE Producto_idProducto='"+deposito.getProducto().getCodigo()+"'";
        
                    
                    
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
            String sql = "UPDATE deposito SET "
                    + "Stock='"+(deposito.getCantidad_producto()-valor)+"' WHERE Producto_idProducto='"+deposito.getProducto().getCodigo()+"'";
        
                    
                    
            con.getConsulta().execute(sql);
            //JOptionPane.showMessageDialog(null,"Stock Actualizado");
            System.out.println("Stock Actualizado");
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }        
    }
     
    public boolean getDisponible(String Codigo, int cantidad){
        try {
            String sql = "SELECT deposito.stock FROM deposito WHERE Producto_idProducto='"+Codigo+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                int Stock=fila.getInt("stock");
                if(Stock>=cantidad) return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepositoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int getCantidadDeStock(String Codigo){
        try {
            String sql = "SELECT deposito.stock FROM deposito WHERE Producto_idProducto='"+Codigo+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                int Stock=fila.getInt("stock");
                return Stock;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepositoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
   
}

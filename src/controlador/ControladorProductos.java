/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Datos.Conexion;
import modelo.Stock;
import Datos.DepositoDAO;
import modelo.Producto;
import Datos.ProductoDAO;
import modelo.Usuario;
import vista.GestionarProductos;

/**
 *
 * @author FIume
 */
public class ControladorProductos implements ActionListener{
    private Conexion con;
    private Producto producto;
    private GestionarProductos GestionarProductos;
    private  Usuario _usuarioAutenticado;
    
    public ControladorProductos(Conexion con, Usuario usuarioAutenticado){
        this._usuarioAutenticado=usuarioAutenticado;
        this.con=con;
        producto = new Producto();
        GestionarProductos = new GestionarProductos(null,true);
    }
    
    public void ejecutar(){
        GestionarProductos.setControlador(this);
        GestionarProductos.ejecutar();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        
        if(e.getActionCommand().equals(GestionarProductos.AGREGARP)){
            
        
            Producto Producto = new Producto();
            Producto.setDescripcion(GestionarProductos.getDescripcion());
            Producto.setIdProducto(GestionarProductos.getCodigo());
            Producto.setPrecio(GestionarProductos.getPrecioVenta());
            Producto.setTipo(GestionarProductos.getTipo());
            Producto.setPrecioCompra(Double.parseDouble(GestionarProductos.getPrecioCompra()));
            Producto.setTalle(GestionarProductos.getTamaño());
            Producto.setDeposito(GestionarProductos.getAlmacen());
            Producto.setStockSeguridad(GestionarProductos.getStockS());
            
            
            Stock dep = new Stock(Producto,0);
            DepositoDAO depDAO = new DepositoDAO(dep,con);
            
            ProductoDAO ProductoDAO = new ProductoDAO(Producto,con);
            ProductoDAO.AgregarProducto();
            
            
            depDAO.AgregarProductoADeposito();
            
        }
        
        if(e.getActionCommand().equals(GestionarProductos.BUSCARP)){
            
            Producto Aux = new Producto();
            Aux.setIdProducto(GestionarProductos.getCodigo());
            ProductoDAO Prod = new ProductoDAO(Aux, con);
            Producto tmp = Prod.buscar();
          
            
            if( tmp != null){
                GestionarProductos.setCodigo(tmp.getIdProducto());
                GestionarProductos.setDescripcion(tmp.getDescripcion());
                GestionarProductos.setPrecioVenta(tmp.getPrecio());
                GestionarProductos.setTipo(tmp.getTipo());
                GestionarProductos.setPrecioCompra(""+tmp.getPrecioCompra());
                GestionarProductos.setTamaño(tmp.getTalle());
                
                
                JOptionPane.showMessageDialog(null,"Encontrado");
                GestionarProductos.Habilitar();
               
            }
        }
        
            
        
           
        
        if(e.getActionCommand().equals(GestionarProductos.MODIFICARP)){
            
            Producto Producto = new Producto();
            Producto.setDescripcion(GestionarProductos.getDescripcion());
            Producto.setIdProducto(GestionarProductos.getCodigo());
            Producto.setPrecio(GestionarProductos.getPrecioVenta());
            Producto.setTipo(GestionarProductos.getTipo());
            Producto.setPrecioCompra(Double.parseDouble(GestionarProductos.getPrecioCompra()));
            Producto.setDeposito(GestionarProductos.getAlmacen());
            Producto.setTalle(GestionarProductos.getTamaño());
            Producto.setStockSeguridad(GestionarProductos.getStockS());
           
            
            ProductoDAO ProductoDAO = new ProductoDAO(Producto,con);
            ProductoDAO.modificar();
            
        }
        
        if(e.getActionCommand().equals(GestionarProductos.ELIMINARP)){
            Producto Producto = new Producto();
            Producto.setIdProducto(GestionarProductos.getCodigo());
            ProductoDAO ProductoDAO = new ProductoDAO(Producto,con);
            ProductoDAO.borrar();
        }
}
}


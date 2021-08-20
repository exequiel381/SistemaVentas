/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Datos.Conexion;
import modelo.Stock;
import Datos.DepositoDAO;
import modelo.Producto;
import modelo.Usuario;
import vista.GestionarStock;

/**
 *
 * @author FIume
 */
public class ControladorStock implements ActionListener{
    
    private Conexion con;
    private GestionarStock gestionarstock;
    private  Usuario _usuarioAutenticado;
    
    
    public ControladorStock(Conexion con, Usuario usuarioAutenticado){
        gestionarstock = new GestionarStock(null,true);
        this._usuarioAutenticado=usuarioAutenticado;
        this.con=con;
    }
    
    public void ejecutar(){
       
        gestionarstock.setControlador(this);
        this.RellenarTabla();
        //gestionarstock.agregarDepositos(depositos);
        gestionarstock.ejecutar();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        
        if(e.getActionCommand().equals(gestionarstock.AGREGAR_STOCK)){
            
            Producto prod = new Producto();
            prod.setIdProducto(gestionarstock.seleccionarFila());
            
            Stock deposito = new Stock(prod,1);
            deposito.setCantidad_producto(gestionarstock.CantidadStock());
            DepositoDAO depDAO= new DepositoDAO(deposito,con);
            
            depDAO.AgregarStock(gestionarstock.getStock());
            
                       
            this.RellenarTabla();
        }
        
        
        if(e.getActionCommand().equals(gestionarstock.QUITAR_STOCK)){
           Producto prod = new Producto();
            prod.setIdProducto(gestionarstock.seleccionarFila());
            
            Stock deposito = new Stock(prod,1);
            deposito.setCantidad_producto(gestionarstock.CantidadStock());
            DepositoDAO depDAO= new DepositoDAO(deposito,con);
            
            depDAO.QuitarStock(gestionarstock.getStock());
            
                       
            this.RellenarTabla();
        }
        
        if(e.getActionCommand().equals(gestionarstock.BUSCAR_STOCK)){
         this.RellenarTabla();
        }
        
    }
    
    
    public void RellenarTabla(){
        
            Stock deposito = new Stock();
            DepositoDAO depDAO= new DepositoDAO(deposito,con);
            double TotalStock=0;
            
            ArrayList<String[]> lista = new ArrayList<String[]>();
            for(Stock Emp : depDAO.leer(gestionarstock.getBusqueda()) ){
                String linea[] = new String[6];
                linea[0] = Emp.getProducto().getIdProducto(); 
                linea[1] = Emp.getProducto().getDescripcion();
                linea[2] = ""+Emp.getProducto().getTalle();
                //linea[2] = ""+Emp.getIdDeposito();
                linea[3] = ""+Emp.getCantidad_producto();
                linea[4] = ""+Emp.getProducto().getPrecio();
                linea[5] = ""+Emp.getProducto().getPrecioCompra();
                
                TotalStock = TotalStock + Emp.getCantidad_producto()*Emp.getProducto().getPrecioCompra();
              
                lista.add(linea);
               
            }
            
            gestionarstock.setTotalStock(TotalStock);
            gestionarstock.cargarLista(lista);
            gestionarstock.LimpiarCaja();
         }
}

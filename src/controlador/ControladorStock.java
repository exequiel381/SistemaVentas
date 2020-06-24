/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.Deposito;
import modelo.DepositoDAO;
import modelo.Producto;
import vista.GestionarStock;

/**
 *
 * @author FIume
 */
public class ControladorStock implements ActionListener{
    
    private Conexion con;
    private GestionarStock gestionarstock;
    
    public ControladorStock(Conexion con){
        gestionarstock = new GestionarStock(null,true);
        this.con=con;
    }
    
    public void ejecutar(){
       
        gestionarstock.setControlador(this);
        this.RellenarTabla();
        gestionarstock.ejecutar();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        
        if(e.getActionCommand().equals(gestionarstock.AGREGAR_STOCK)){
            
            Producto prod = new Producto();
            prod.setCodigo(gestionarstock.seleccionarFila());
            
            Deposito deposito = new Deposito(prod,1);
            deposito.setCantidad_producto(gestionarstock.CantidadStock());
            DepositoDAO depDAO= new DepositoDAO(deposito,con);
            
            depDAO.AgregarStock(gestionarstock.getStock());
            
                       
            this.RellenarTabla();
        }
        
        
        if(e.getActionCommand().equals(gestionarstock.QUITAR_STOCK)){
           Producto prod = new Producto();
            prod.setCodigo(gestionarstock.seleccionarFila());
            
            Deposito deposito = new Deposito(prod,1);
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
        
            Deposito deposito = new Deposito();
            DepositoDAO depDAO= new DepositoDAO(deposito,con);
            double TotalStock=0;
            
            ArrayList<String[]> lista = new ArrayList<String[]>();
            for(Deposito Emp : depDAO.leer(gestionarstock.getBusqueda()) ){
                String linea[] = new String[6];
                linea[0] = Emp.getProducto().getCodigo(); 
                linea[1] = Emp.getProducto().getDescripcion();
                linea[2] = ""+Emp.getProducto().getTamanio();
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

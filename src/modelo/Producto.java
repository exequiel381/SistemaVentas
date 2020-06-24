/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author FIume
 */
public class Producto extends Articulo {
    
    private String idProv;
    private String Color;
    private int deposito;
    private int StockSeguridad;
    private double precioCompra;
    
    
    
    
    public Producto(){
        
    }
    
    public Producto(String idProd,String Codigo,String Descripcion,int Precio,String tipo,int StockSeguridad,String Tamanio,int deposito){
        super(Codigo,Descripcion,Precio,tipo,StockSeguridad,Tamanio);
        this.idProv=idProv;
        this.deposito=deposito;
    }
    
    public Producto(String idProd,String Codigo,String Descripcion,int Precio,String tipo,int StockSeguridad,String Tamanio,int deposito,String Color){
        super(Codigo,Descripcion,Precio,tipo,StockSeguridad,Tamanio);
        this.idProv=idProv;
        this.deposito=deposito;
        this.Color=Color;
    }
    
    
    
    

    public String getIdProv() {
        return idProv;
    }

    public void setIdProv(String idProv) {
        this.idProv = idProv;
    }

    public int getDeposito() {
        return deposito;
    }

    public void setDeposito(int deposito) {
        this.deposito = deposito;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

   

    public void setStockSeguridad(int StockSeguridad) {
        this.StockSeguridad = StockSeguridad;
    }
    
     public void DescripcionProducto(){
        JOptionPane.showMessageDialog(null, super.toString());
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
     
     

    @Override
    public String toString() {
        return "Producto{" + "idProv=" + idProv + ", Color=" + Color + ", deposito=" + deposito + ", StockSeguridad=" + StockSeguridad + '}';
    }

   

   
    
    
    
}

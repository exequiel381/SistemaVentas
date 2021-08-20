/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author FIume
 */
public class Stock {
    
    private Producto _producto;
    private int _cantidadProducto;
    private int _idStock;

    public int getCantidadProducto() {
        return _cantidadProducto;
    }

    public void setCantidadProducto(int _cantidadProducto) {
        this._cantidadProducto = _cantidadProducto;
    }

    public int getIdStock() {
        return _idStock;
    }

    public void setIdStock(int _idStock) {
        this._idStock = _idStock;
    }
    
    
    public Stock(Producto producto,int Cantidad){
        this._producto=producto;
        this._cantidadProducto=Cantidad;
    }
   
    public Stock(){
        
    }

    
    

    public Producto getProducto() {
        return _producto;
    }

    public void setProducto(Producto producto) {
        this._producto = producto;
    }

   
  

    public int getCantidad_producto() {
        return _cantidadProducto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this._cantidadProducto = cantidad_producto;
    }

    
    
    
    
}

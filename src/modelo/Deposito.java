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
public class Deposito {
    
    private Producto producto;
    private Insumo insumo;
    private int cantidad_insumo;
    private int cantidad_producto;
    private int idDeposito;
    
    public Deposito(Producto producto,int Cantidad){
        this.producto=producto;
        this.cantidad_producto=Cantidad;
    }
    public Deposito(Insumo insumo,int Cantidad){
        this.insumo=insumo;
        this.cantidad_insumo=Cantidad;
    }
    
    public Deposito(){
        
    }

    public int getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }
    
    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public int getCantidad_insumo() {
        return cantidad_insumo;
    }

    public void setCantidad_insumo(int cantidad_insumo) {
        this.cantidad_insumo = cantidad_insumo;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }
    
    
}

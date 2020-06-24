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
public class LineaDeVenta {
    
    private int idLineaVenta;
    private Producto producto;
    private String fecha;
    private int Cantidad;
    private Double subTotal;
    

    public LineaDeVenta() {
    }

    public int getIdLineaVenta() {
        return idLineaVenta;
    }

    public void setIdLineaVenta(int idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }
    
    
    
    public LineaDeVenta(Producto producto){
        
        this.producto=producto;
        
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getSubTotal() {
        return producto.getPrecio()*this.Cantidad;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
    
}

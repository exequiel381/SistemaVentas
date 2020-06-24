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
public abstract class Articulo {
    private String Descripcion;
    private int Precio;
    private String tipo;
    private String codigo;
    private int StockSeguridad;
    private String Tamanio;
    public Articulo(){
        
    }
    
    public Articulo(String Codigo,String Descripcion,int Precio,String tipo,int StockSeguridad,String Tamanio){//
        this.Descripcion=Descripcion;
        this.Precio=Precio; 
        this.Descripcion=Descripcion;
        this.codigo=Codigo;
        this.StockSeguridad=StockSeguridad;
        this.Tamanio=Tamanio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStockSeguridad() {
        return StockSeguridad;
    }

    public void setStockSeguridad(int StockSeguridad) {
        this.StockSeguridad = StockSeguridad;
    }

    public String getTamanio() {
        return Tamanio;
    }

    public void setTamanio(String Tamanio) {
        this.Tamanio = Tamanio;
    }

    
    @Override
    public String toString() {
        return "       Articulo\n\n" +" Codigo= "+ codigo + "\n Descripcion= " + Descripcion + "\n Precio= " + Precio + "\n tipo= " + tipo;
    }
    
}

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
public class Producto  {
    
    private String _idProducto;
    private String _color;
    private int _deposito;
    private double _precioCompra;
    private String _descripcion;
    private int _precio;
    private int _stockSeguridad;
    private String _talle;
    private String _tipo;
    
    
    
    
    public Producto(){
    }

    public Producto(String _idProducto, String _color, int _deposito, double _precioCompra, String _descripcion, int _precio, int _stockSeguridad, String _talle, String _tipo) {
        this._idProducto = _idProducto;
        this._color = _color;
        this._deposito = _deposito;
        this._precioCompra = _precioCompra;
        this._descripcion = _descripcion;
        this._precio = _precio;
        this._stockSeguridad = _stockSeguridad;
        this._talle = _talle;
        this._tipo = _tipo;
    }

    public String getTipo() {
        return _tipo;
    }

    public void setTipo(String _tipo) {
        this._tipo = _tipo;
    }

   
    public String getIdProducto() {
        return _idProducto;
    }

    public void setIdProducto(String _idProducto) {
        this._idProducto = _idProducto;
    }


    public String getColor() {
        return _color;
    }

    public void setColor(String _color) {
        this._color = _color;
    }

    public int getDeposito() {
        return _deposito;
    }

    public void setDeposito(int _deposito) {
        this._deposito = _deposito;
    }

    public double getPrecioCompra() {
        return _precioCompra;
    }

    public void setPrecioCompra(double _precioCompra) {
        this._precioCompra = _precioCompra;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public int getPrecio() {
        return _precio;
    }

    public void setPrecio(int _precio) {
        this._precio = _precio;
    }

    public int getStockSeguridad() {
        return _stockSeguridad;
    }

    public void setStockSeguridad(int _stockSeguridad) {
        this._stockSeguridad = _stockSeguridad;
    }

    public String getTalle() {
        return _talle;
    }

    public void setTalle(String _talle) {
        this._talle = _talle;
    }
    
    
    
    }
    
   
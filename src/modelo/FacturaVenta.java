/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;




public class FacturaVenta {
private double montoFactura;
private double montoVenta;


    public FacturaVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }

    
    
public Double getTotalFactura(){
    this.montoFactura = this.montoVenta;
    return montoFactura;
}    
    
}

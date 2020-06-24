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
public class PagoVenta {
    
    private int idVenta;
    private int idPago;
    private String tipoPago;
    private Double montoPago;
    private String fecha;

    public PagoVenta() {
    }

    public PagoVenta(int idVenta, int idPago, String tipoPago, Double montoPago) {
        this.idVenta = idVenta;
        this.idPago = idPago;
        this.tipoPago = tipoPago;
        this.montoPago = montoPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Double montoPago) {
        this.montoPago = montoPago;
    }
    
    
    
}

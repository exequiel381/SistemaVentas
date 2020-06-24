/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;



public class LiquidacionSueldo {
    
private Detalle detalle;
private Concepto concepto;
private Liquidacion liquidacion;

public LiquidacionSueldo(Detalle detalle,Concepto concepto,Liquidacion liquidacion){
    this.concepto=concepto;
    this.detalle=detalle;
    this.liquidacion=liquidacion;
}

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }


    
}

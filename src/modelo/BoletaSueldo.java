/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author fiume
 */
public class BoletaSueldo {
    
    private int idBoleta;
    private double NetoCobrar;
    private double NetoCobrarAuxiliar;
    private double bruto;
    private Empleado empleado;
    private String periodo ;
    private int anio;
    private int mes;
    
    
    private ArrayList<LineaBoleta> lineasBoleta ;

    public BoletaSueldo() {
        lineasBoleta = new ArrayList<>();
    }

    public double getNetoCobrarAuxiliar() {
        return NetoCobrarAuxiliar;
    }

    public void setNetoCobrarAuxiliar(double NetoCobrarAuxiliar) {
        this.NetoCobrarAuxiliar = NetoCobrarAuxiliar;
    }

    
    
    public ArrayList<LineaBoleta> getLineasBoleta() {
        return lineasBoleta;
    }

    public void setLineasBoleta(ArrayList<LineaBoleta> lineasBoleta) {
        this.lineasBoleta = lineasBoleta;
    }

    
    
    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public void setNetoCobrar(double NetoCobrar) {
        this.NetoCobrar = NetoCobrar;
    }

    public double getNetoCobrar() {
       this.NetoCobrar = this.bruto;
        for (LineaBoleta lineaBoleta : lineasBoleta) {
            this.NetoCobrar+= lineaBoleta.getRemuneracion();
        }
        
        return NetoCobrar;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getPeriodo() {
        return mes+"/"+anio;
    }


    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
        this.periodo = mes+"/"+anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
         this.periodo = mes+"/"+anio;
    }

    public double getBruto() {
        return bruto;
    }

    public void setBruto(double bruto) {
        this.bruto = bruto;
    }
    
    
    
    public void GenerarDetalle(ArrayList<Concepto> conceptos,ArrayList<Novedad> novedades){
        for (Concepto c : conceptos) {
                LineaBoleta l = new LineaBoleta();
                l.setConcepto(c);
                l.setDetalle(c.getDetalle());
                if(c.getTipo().equalsIgnoreCase("Descuento")) l.setRemuneracion(-bruto*(c.getPorcentaje()/100));
                else l.setRemuneracion(bruto*(c.getPorcentaje()/100));
                this.lineasBoleta.add(l);
            }
        
        for (Novedad n : novedades) {
              LineaBoleta l = new LineaBoleta();
              l.setNovedad(n);
              l.setDetalle(n.getDescripcion());
              l.setRemuneracion(n.getValor());
              this.lineasBoleta.add(l);
        }
    }
    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;



public class Venta implements IEstrategiaFijarPrecios{
    
    private Cliente cliente;
   
    private Empleado empleado;
    private String fecha;
    private double total;
    private int idVenta;
    private ArrayList<LineaDeVenta> LineasVenta;
    private FacturaVenta fv;
    private PagoVenta pv ;
    private double TotalConIVA;
    private double descuento=0;
    private double montoIVA=0;

   
    public Venta() {
    }
    
    
    
    public Venta(String fecha,ArrayList LineasDeVenta,Cliente cliente){
        this.LineasVenta=LineasDeVenta;
        this.cliente=cliente;
    }
    public Venta(int idVenta,String fecha,Double Total,ArrayList<LineaDeVenta> LineasDeVenta,Empleado emp){
        
        this.LineasVenta=LineasDeVenta;
        this.empleado=emp;
        this.fecha=fecha;
        this.total=Total;
        this.idVenta=idVenta;
        this.TotalConIVA = Total*1.21;
        }

 

    
    
    
//    public void LineasDeventa(ArrayList<LineaDeVenta> LineasVenta){
//       
//        this.LineasVenta=LineasVenta;
//        
//    }
    
    public double DescuentoMasDeCincoPrendas(){
        
        int CantidadPrendas=0;
        
        for(LineaDeVenta l : LineasVenta){
            CantidadPrendas+=l.getCantidad();
        }
        if(CantidadPrendas>=5) return 50*CantidadPrendas;
        else return 0.0;
       
    }
    
    
    public PagoVenta GenerarPago(boolean tipoPago){
        
        fv = new FacturaVenta(TotalConIVA);
            
        pv = new PagoVenta();
        pv.setMontoPago(fv.getTotalFactura());
        pv.setIdVenta(idVenta);
        pv.setFecha(fecha);
       
        
        if(tipoPago) pv.setTipoPago("tarjeta");
        else pv.setTipoPago("efectivo");
        
        return pv;
        
    }

    public double getMontoIVA() {
        return total*0.21;
    }

   

    
    
    public ArrayList<LineaDeVenta> getLineasVenta() {
        return LineasVenta;
    }

    public void setLineasVenta(ArrayList<LineaDeVenta> LineasVenta) {
        this.LineasVenta = LineasVenta;
    }

    public double getTotalConIVA() {
        return TotalConIVA;
    }

    public void setTotalConIVA(double TotalConIVA) {
        this.TotalConIVA = TotalConIVA;
    }

    
    public FacturaVenta getFv() {
        return fv;
    }

    public void setFv(FacturaVenta fv) {
        this.fv = fv;
    }

    public PagoVenta getPv() {
        return pv;
    }

    public void setPv(PagoVenta pv) {
        this.pv = pv;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
   

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList getLineas() {
        return LineasVenta;
    }

    public void setLineas(ArrayList LineasVenta) {
        this.LineasVenta = LineasVenta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public double CalcularTotal(){
         
        double TotalVenta=0;
        
        for(LineaDeVenta lv : LineasVenta){
            TotalVenta+=lv.getSubTotal();
        }
        this.TotalConIVA = TotalVenta*1.21;
        return TotalVenta;
    }
    
    public void setTotal(double total) {
        this.total = total;
        this.TotalConIVA = total*1.21;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    
    
}

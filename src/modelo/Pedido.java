
package modelo;

import java.util.ArrayList;

public class Pedido {
   
    private ArrayList<LineaPedido> LineaPedido;
    private Double total;
    private int idPedido;
    private Empleado empleado;
    private String fecha;
    private Proveedor proveedor;
    private String Estado;
    
    public Pedido(){
        
    }
    
    public Pedido(int idPedido,ArrayList<LineaPedido> LineaPedido, double total,String fecha,Empleado emp,Proveedor proveedor){
        this.LineaPedido=LineaPedido;
        this.total=total;
        this.idPedido=idPedido;
        this.empleado=emp;
        this.fecha=fecha;
        this.proveedor=proveedor;
     
        
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    
    
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public ArrayList<LineaPedido> getLineaPedido() {
        return LineaPedido;
    }

    public void setLineaPedido(ArrayList<LineaPedido> LineaPedido) {
        this.LineaPedido = LineaPedido;
    }

    public Double gettotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
}

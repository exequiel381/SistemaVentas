
package modelo;


public class LineaPedido {
   
    private Insumo insumo;
    private int Cantidad;
    private double Precio_unitario;
    private double SubTotal;
    private Producto producto;
 
    
    /*public LineaPedido(Insumo insumo,int Cantidad,double Precio_unitario,double MontoTot){
     
        this.Cantidad=Cantidad;
        this.SubTotal=SubTotal;
        this.Precio_unitario=Precio_unitario;
        this.insumo=insumo;
        this.proveedor=proveedor;
    }*/
    
    public LineaPedido(){
        
    }
    
    public LineaPedido(Producto producto,int Cantidad,double Precio_unitario,double SubTotal){
     
        this.Cantidad=Cantidad;
        this.SubTotal=SubTotal;
        this.Precio_unitario=Precio_unitario;
        this.producto=producto;
        
      
    }

    public  String getCodigoProducto(){
        return producto.getCodigo();
    }
    
    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
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

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecio_unitario() {
        return Precio_unitario;
    }

    public void setPrecio_unitario(double Precio_unitario) {
        this.Precio_unitario = Precio_unitario;
    }

    
}

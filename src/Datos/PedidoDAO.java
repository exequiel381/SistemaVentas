/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.LineaPedido;
import modelo.Pedido;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author FIume
 */
public class PedidoDAO {
   
    private Pedido pedido;
    private Conexion con;
    
    public PedidoDAO(Conexion con){
        this.con=con;
    }
    
    public PedidoDAO(Pedido pedido,Conexion con){
        this.pedido=pedido;
        this.con=con;
    }
    
    public void Agregar(){
        try {
            String sql = "INSERT INTO pedido SET idPedido='"+pedido.getIdPedido()+"', "
                    +"Fecha='"+pedido.getFecha()+"', "
                    +"Precio_Total='"+pedido.gettotal() +"', "
                    +"Empleado_idEmpleado='"+pedido.getEmpleado().getIdEmpleado()+"', "
                    +"Proveedores_CUIL_CUIT='"+pedido.getProveedor().getCuit()+"', "
                    +"Estado='"+pedido.getEstado()+"'";
                    
            
            
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Pedido realizado");
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"NO SE REALIZO EL PEDIDO :" + ex);
        }
    }
    
    public void modificar(){
        try{
            String sql = "UPDATE pedido SET "
                    + " estado='"+pedido.getEstado()+ "'"
                    + " WHERE idPedido='"+pedido.getIdPedido()+"'";
            con.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }      
    }
    
    public int UltimoPedido(){
        try {
            
            String sql = "SELECT MAX(idPedido) FROM pedido";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next())
            return fila.getInt("MAX(idPedido)");
           
          
            
       } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }return 0;
    }
    
    public void agregarLineasDePedido(ArrayList<LineaPedido> LineasPedido,int idPedido){
       
        for(LineaPedido Linea : LineasPedido){
            try {
                String sql = "INSERT INTO detalle_pedido SET "
                             + "pedido_idPedido='"+idPedido+"', "
                             +"cantidad='"+Linea.getCantidad()+"', "
                             +"producto_idProducto='"+Linea.getCodigoProducto()+"',"
                             +"subtotal='"+Linea.getSubTotal()+"', "
                             +"precioUnitario='"+Linea.getPrecio_unitario()+"'";
                             
                con.getConsulta().execute(sql);
               
            } catch (SQLException ex) {
                System.out.println("No se agrego la linea de pedido");
            }
            
        }
        
    }
    
    public ArrayList<Pedido> leer(String Estado){
        ArrayList<Pedido> lista = new ArrayList<Pedido>();
        try{
            
            String sql = "SELECT * FROM empleado,pedido,proveedores WHERE  pedido.Empleado_idEmpleado=empleado.idEmpleado and pedido.Proveedores_CUIL_CUIT=proveedores.CUIL_CUIT"
                    +" and pedido.Estado='"+Estado+"'";
            
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            
            while(fila.next()){
                
                Pedido tmp = new Pedido();
                Empleado emp = new Empleado();
                Proveedor prov = new Proveedor();
                
                emp.setDni(fila.getInt("DNI"));
                emp.setNombre(fila.getString("Nombre"));
                
                prov.setCuit(fila.getString("CUIL_CUIT"));
                prov.setRazoc(fila.getString("Razoc"));
              
                
                tmp.setIdPedido(fila.getInt("idPedido") );
                tmp.setFecha(fila.getString("Fecha"));
                tmp.setTotal(fila.getDouble("Precio_Total"));
                tmp.setEstado(fila.getString("Estado"));
                tmp.setEmpleado(emp);
                tmp.setProveedor(prov);
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla PEDIDO "+e);
        }        
        return lista;
    }
    
    public ArrayList<LineaPedido> leerLineasPedido(){//Revisar funcion la parte de la consulta a la base de datos
         ArrayList<LineaPedido> lista = new ArrayList<LineaPedido>();
          try{
            
            String sql = "SELECT * FROM detalle_pedido,producto WHERE detalle_pedido.Pedido_idPedido='"+pedido.getIdPedido()+"' and detalle_pedido.Producto_idProducto=producto.idProducto";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            
            while(fila.next()){
                
                LineaPedido tmp = new LineaPedido();
                Producto pro = new Producto();
                
                pro.setIdProducto(fila.getString("idProducto"));
                pro.setDescripcion(fila.getString("Descripcion"));
                pro.setPrecio(fila.getInt("PrecioU"));
                
                tmp.setProducto(pro);
                tmp.setCantidad(fila.getInt("Cantidad"));
                tmp.setSubTotal(fila.getDouble("subTotal"));
                tmp.setPrecio_unitario(fila.getDouble("precioUnitario"));
                
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla DETALLE PEDIDO");
        } 
         return lista;
    }

    public ArrayList<Pedido> ObtenerPedidosFiltrados(String desde, String hasta) {
       
    ArrayList<Pedido> lista = new ArrayList<Pedido>();
        try{
            
            String sql = "SELECT * FROM empleado,pedido,proveedores WHERE  pedido.Empleado_idEmpleado=empleado.idEmpleado and pedido.Proveedores_CUIL_CUIT=proveedores.CUIL_CUIT"
                    +" and pedido.Fecha>='"+desde+"' and pedido.fecha<='"+hasta+"'";
            
            ResultSet fila = con.getConsulta().executeQuery(sql);
            
            
            while(fila.next()){
                
                Pedido tmp = new Pedido();
                Empleado emp = new Empleado();
                Proveedor prov = new Proveedor();
                
                emp.setDni(fila.getInt("DNI"));
                emp.setNombre(fila.getString("Nombre"));
                
                prov.setCuit(fila.getString("CUIL_CUIT"));
                prov.setRazoc(fila.getString("Razoc"));
              
                
                tmp.setIdPedido(fila.getInt("idPedido") );
                tmp.setFecha(fila.getString("Fecha"));
                tmp.setTotal(fila.getDouble("Precio_Total"));
                tmp.setEstado(fila.getString("Estado"));
                tmp.setEmpleado(emp);
                tmp.setProveedor(prov);
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al filtrar datos de la tabla PEDIDO "+e);
        }        
        return lista;
    }
    
    
}

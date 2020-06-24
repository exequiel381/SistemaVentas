/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.*;

public class LineaPedidoDAO {
    
    private LineaPedido linea;
    private Conexion con;
    
    public LineaPedidoDAO(LineaPedido linea,Conexion con){
        this.con=con;
        this.linea=linea;
    }
    
    public void moficarDetalle(int idPedido){
         try {
            String sql = "UPDATE detalle_pedido SET "
                             +"cantidad='"+linea.getCantidad()+"', "
                             +"subtotal='"+linea.getSubTotal()+"', "
                             +"precioUnitario='"+linea.getPrecio_unitario()+"' "
                    + "WHERE Pedido_idPedido='"+idPedido+"' "+ "and Producto_idProducto='"+linea.getProducto().getCodigo()+"'";
            
            
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"SE MODIFICO EL DETALLE");
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"NO SE MODIFICO EL DETALLE");
        }
    }
    public void borrarLinea(int idPedido){
        try{
            String sql = "DELETE FROM detalle_Pedido WHERE pedido_idPedido='"+idPedido+"' and Producto_idProducto='"+linea.getProducto().getCodigo()+"'";
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"SE BORRO EL DETALLE");
        }
        catch(SQLException e){
            System.out.println("NO SE BORRO EL DETALLE");
        }    
    }
}

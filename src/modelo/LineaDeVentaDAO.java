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

/**
 *
 * @author FIume
 */
public class LineaDeVentaDAO {
    private LineaDeVenta linea;
    private Conexion con;

    public LineaDeVentaDAO(LineaDeVenta linea, Conexion con) {
        this.linea = linea;
        this.con = con;
    }
    
    public void modificarLineaVenta(int idVenta){
         try {
            String sql = "UPDATE lineaVenta SET "
                             +"cantidad='"+linea.getCantidad()+"', "
                             +"subtotal='"+linea.getSubTotal()+"'"
                             
                    + "WHERE Venta_idVenta='"+idVenta+"' "+ "and idLineaVenta='"+linea.getIdLineaVenta()+"'";
            
            
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"SE MODIFICO EL DETALLE");
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"NO SE MODIFICO EL DETALLE");
        }
    }
    
    public void borrarLinea(int idVenta){
        try{
            String sql = "DELETE FROM lineaVenta WHERE Venta_idVenta='"+idVenta+"' "+ "and idLineaVenta='"+linea.getIdLineaVenta()+"'";
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"SE BORRO EL DETALLE");
        }
        catch(SQLException e){
            System.out.println("NO SE BORRO EL DETALLE");
        }    
    }
}

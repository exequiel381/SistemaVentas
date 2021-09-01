/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.PagoVenta;

/**
 *
 * @author FIume
 */
public class PagoVentaDAO {
    
    private PagoVenta pv;
    private Conexion con;

    public PagoVentaDAO(PagoVenta pv, Conexion con) {
        this.pv = pv;
        this.con = con;
    }
    
    public void AgregarPagoVenta(){
        try{ 
            String sql = "INSERT INTO factura_de_venta SET idFactura_de_venta='"+pv.getIdVenta()+"', "
                    +"Fecha='"+pv.getFecha()+"', "
                    +"Total='"+pv.getFactura().getTotalFactura()+"'";
            
            con.getConsulta().execute(sql);
            
            sql = "INSERT INTO pago_venta SET Venta_idVenta='"+pv.getIdVenta()+"', "
                    +"Factura_de_venta_idFactura_de_venta='"+pv.getIdVenta()+"', "
                    +"tipo='"+pv.getTipoPago()+"', "
                    +"monto='"+pv.getMontoPago()+"'";
                    
            
            
            con.getConsulta().execute(sql);
            System.out.println("Pago Guardado");
        } catch (SQLException ex) {
            System.out.println("No se registro el pago: "+ex);
           
        }
    }
    
}

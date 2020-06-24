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
public class PagoVentaDAO {
    
    private PagoVenta pv;
    private Conexion con;

    public PagoVentaDAO(PagoVenta pv, Conexion con) {
        this.pv = pv;
        this.con = con;
    }
    
    public void AgregarPagoVenta(){
        try {
            String sql = "INSERT INTO pagoVenta SET Venta_idVenta='"+pv.getIdVenta()+"', "
                    +"Fecha='"+pv.getFecha()+"', "
                    +"tipoPago='"+pv.getTipoPago()+"', "
                    +"montoPago='"+pv.getMontoPago()+"'";
                    
            
            
            con.getConsulta().execute(sql);
            System.out.println("Pago Guardado");
        } catch (SQLException ex) {
            System.out.println("No se registro el pago");
        }
    }
    
}

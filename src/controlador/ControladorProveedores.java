/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Datos.Conexion;
import modelo.Proveedor;
import Datos.ProveedorDAO;
import vista.RegistrarProveedor;


/**
 *
 * @author FIume
 */
public class ControladorProveedores implements ActionListener {
    private Conexion con;
    private RegistrarProveedor registrarProveedor;
    private Proveedor Proveedor;
    
    public ControladorProveedores(Conexion con){
        this.con=con;
        registrarProveedor = new RegistrarProveedor(null,true);
    }
    
    public void ejecutar(){
        
        registrarProveedor.setControlador(this);
        registrarProveedor.ejecutar();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getActionCommand().equals(registrarProveedor.REGISTRAR_PROVEEDOR)){
            Proveedor= new Proveedor(registrarProveedor.getRazoc(),registrarProveedor.getCuil(),registrarProveedor.getDireccion(),registrarProveedor.getTelefono());
            Proveedor.setCuit(registrarProveedor.getCuil());
            Proveedor.setDireccion(registrarProveedor.getDireccion());
            Proveedor.setRazoc(registrarProveedor.getRazoc());
            Proveedor.setTelefono(registrarProveedor.getTelefono());
            
            ProveedorDAO ProvDAO = new ProveedorDAO(Proveedor,con);
            ProvDAO.AgregarProveedor();
        }
        
    }
}

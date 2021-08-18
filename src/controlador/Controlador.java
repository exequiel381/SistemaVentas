/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Datos.Conexion;
import Datos.UsuarioDAO;
import seguridad.MD5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 *
 * @author FIume
 */
public class Controlador implements ActionListener {

    private Conexion con;
    private AutenticacionVista autenticacion_vista;
    private Autenticacion autenticacion;
    private RealizarVenta RealizarVenta;
    private Principal principal;
    private GestionarEmpleado GestionarEmpleado;
    private GestionarStock gestionarstock;
    private Usuario _usuarioAutenticado;

    public Controlador(Conexion con) {//
        this.con = con;
        autenticacion_vista = new AutenticacionVista();
        principal = new Principal();
        

    }

    public void ejecutar() {
        autenticacion_vista.setControlador(this);
        autenticacion_vista.ejecutar();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(autenticacion_vista.BTN_INGRESAR)) {

                
                Usuario u = new Usuario(autenticacion_vista.getUsuario(),MD5.getMd5(autenticacion_vista.getContraseña()));
                UsuarioDAO ud = new UsuarioDAO(u, con);
                _usuarioAutenticado = ud.buscar();
                //buscamos un usuario y si lo encontramos con el usuario y contraseña, damos permiso segun su rol
                
                    //if (empDAO.buscar() != null) {
                    if (_usuarioAutenticado == null) {//ponerle !=
                        try {
                           autenticacion_vista.dispose();
                            principal.setControlador(this);
                            principal.ejecutar();
                        } catch (NumberFormatException j) {
                            JOptionPane.showMessageDialog(null, "Dni Invalido");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Empleado inexistente");
                    }
                

            
        }
        if (e.getActionCommand().equals(principal.EMPLEADOS)) {
            //AQUI VAMOS A CORROBORAR QUE AUTENTICACION.DNI SEA IGUAL AL DEL ADMINISTRADOR
            //if(_usuarioAutenticado.getDescripcionRol().equalsIgnoreCase("Administrador")){
             
            ControladorEmpleado ControladorEmpleado = new ControladorEmpleado(con,_usuarioAutenticado);
            principal.setVisible(false);
            ControladorEmpleado.ejecutar();
            principal.setVisible(true);
            //}else JOptionPane.showMessageDialog(null, "DEBES SER ADMINISTRADOR");
            
        }

        if (e.getActionCommand().equals(principal.STOCK)) {
            
            
            ControladorStock controladorstock = new ControladorStock(con,_usuarioAutenticado);
            principal.setVisible(false);
            controladorstock.ejecutar();
            principal.setVisible(true);
        }
        if (e.getActionCommand().equals(principal.PRODUCTOS)) {
            ControladorProductos controladorproductos = new ControladorProductos(con,_usuarioAutenticado);
            principal.setVisible(false);
            controladorproductos.ejecutar();
            principal.setVisible(true);
        }
        if (e.getActionCommand().equals(principal.VENTAS)) {
            ControladorVentas controladorventas = new ControladorVentas(con,_usuarioAutenticado);
            principal.setVisible(false);
            controladorventas.ejecutar();
            principal.setVisible(true);
        }
        if (e.getActionCommand().equals(principal.COMPRAS)) {
            ControladorPedidos controladorpedidos = new ControladorPedidos(con, _usuarioAutenticado);
            principal.setVisible(false);
            controladorpedidos.ejecutar();
            principal.setVisible(true);
        }
        if (e.getActionCommand().equals(principal.PROVEEDORES)) {
            ControladorProveedores controladorProv = new ControladorProveedores(con);
            principal.setVisible(false);
            controladorProv.ejecutar();
            principal.setVisible(true);
        }
        if (e.getActionCommand().equals(principal.LISTAVENTAS)) {
            ControladorVentas controladorventas = new ControladorVentas(con, _usuarioAutenticado);
            principal.setVisible(false);
            controladorventas.listarVentas();
            principal.setVisible(true);
        }
        if (e.getActionCommand().equals(principal.LISTAPENDIENTES)) {
            ControladorPedidos controladorpedidos = new ControladorPedidos(con,_usuarioAutenticado);
            principal.setVisible(false);
            controladorpedidos.listarPedidosPendientes();
            principal.setVisible(true);
        }
        if (e.getActionCommand().equals(principal.LISTAFINALIZADAS)) {
            ControladorPedidos controladorpedidos = new ControladorPedidos(con,_usuarioAutenticado);
            principal.setVisible(false);
            controladorpedidos.listarPedidosFinalizados();
            principal.setVisible(true);
        }

    }

}

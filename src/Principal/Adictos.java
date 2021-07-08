/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import controlador.Controlador;
import javax.swing.JOptionPane;
import modelo.Autenticacion;
import modelo.Conexion;
import seguridad.MD5;
import vista.AutenticacionVista;

/**
 *
 * @author FIume
 */
public class Adictos {

    
    
    public static void main(String[] args) {
       
           Conexion con = new Conexion();
        
        if(con.conectar()>0){
            Controlador control = new Controlador(con);
            control.ejecutar();
            
        }
       
      
        }
    }
    
    


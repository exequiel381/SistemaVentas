/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fiume
 */
public class UsuarioDAO {
    private Conexion con;
    private Usuario usuario;

    public UsuarioDAO(Usuario usuario,Conexion con) {
        this.con = con;
        this.usuario = usuario;
    }
    
    public void AgregarUsuario(){
        
        try{
           
           
            
           String sql = "INSERT INTO usuarios SET usuario='"+usuario.getUsuario()+"', "
                    + "contraseña=MD5('"+usuario.getContraseña()+"'), "
                    + "Empleado_DNI='"+ usuario.getUsuario()+ "', "
                    + "Rol_idRol='"+usuario.getRol().getIdRol()+"'";
             
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Usuario agregado");
        }
        catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"fallando el ingreso de usuario y contraseña");
            System.out.println(e);
        }
    }

    
    public Usuario buscar(){
        try{
          
            String sql = "SELECT * FROM usuarios WHERE usuario='"+usuario.getUsuario()+"'"+ " and " + "contraseña='"+usuario.getContraseña()+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                Usuario tmp = new Usuario();
                tmp.setUsuario(fila.getString("usuario"));
                tmp.setContraseña(fila.getString("contraseña"));
                
                
                return tmp;
            }
            
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla USUARIOS");
        }        
        return null;
    }
}

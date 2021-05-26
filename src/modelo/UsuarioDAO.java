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
    /*
    public void AgregarEmpleado(){
        
        try{
           
            
           String sql = "INSERT INTO usuarios SET usuario='"+usuario.getDni()+"', "
                    + "AES_ENCRYPT(contraseña='"+usuario.getContraseña())+ "', "
                    + "Empleado_DNI='"+ usuario.getDni()+ "', "
                    + "Rol_idRol='"+usuario.getRol.getId+"'";
             
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Usuario agregado");
        }
        catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"fallando el ingreso de usuario y contraseña");
        }
    }
*/
    
    public Usuario buscar(){
        try{
          
            String sql = "SELECT * FROM empleado WHERE dni='"+usuario.getUsuario()+"'"+ " and " + "contrasenia='"+usuario.getContraseña()+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                Usuario tmp = new Usuario();
                tmp.setUsuario(fila.getString("usuario"));
                tmp.setContraseña(fila.getString("contrasenia"));
                
                
                return tmp;
            }
            
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }        
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author FIume
 */
public class EmpleadoDAO {
    private Empleado empleado;
    private Conexion con;
    private Localidad localidad;
    private int idSeccion;
    
    public EmpleadoDAO(Empleado empleado,Conexion con){
        this.empleado=empleado;
        this.con=con;
    }
    
   
    public void AgregarEmpleado(){
        
        try{
            
            if(empleado.getSeccion().equalsIgnoreCase("ventas")) idSeccion=1;
            
           String sql = "INSERT INTO empleado SET DNI='"+empleado.getDni()+"', "
                    + "Nombre='"+empleado.getNombre()+ "', "
                    + "Apellido='"+ empleado.getApellido()+ "', "
                    + "Telefono='"+empleado.getTelefono()+"', "
                    + "Direccion='"+empleado.getDireccion()+"', "
                    + "Fecha_ingreso='"+empleado.getFecha_ingreso()+"', "
                    + "Fecha_salida='"+empleado.getFecha_salida()+"', "
                    + "CUIL='"+empleado.getCuil()+"', "
                    + "Sueldo='"+empleado.getSueldo()+"', "
                    + "Antiguedad='"+empleado.getAntiguedad()+"', "
                    + "Localidad_idLocalidad='"+empleado.getLocalidad().getIdLocalidad()+"', "
                    + "Seccion_idSeccion='"+idSeccion+"'";
             
           
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Empleado agregado");
        }
        catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"1- Error al agregar datos a la tabla\n2-Asegurese de rellenar todos los campos\n3- Solo puede dejar vacia la fecha salida\n4-El formato de fecha debe ser Año-Mes-Dia");
        }
    }
    
     public ArrayList<Empleado> leer(){
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        
        try{
            String sql = "SELECT empleado.DNI,empleado.Nombre,empleado.Apellido,empleado.Telefono,empleado.Direccion,empleado.Fecha_ingreso,empleado.Fecha_salida,empleado.Localidad_idLocalidad,empleado.Cuil,empleado.Sueldo,empleado.Antiguedad,localidad.Nombre,localidad.idLocalidad "
                    + "FROM empleado,localidad WHERE empleado.Localidad_idLocalidad=localidad.idLocalidad";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            while(fila.next()){
                Empleado tmp = new Empleado();
                tmp.setDni( fila.getInt("dni") );
                tmp.setNombre(fila.getString("nombre"));
                tmp.setApellido(fila.getString("apellido"));
                tmp.setTelefono(fila.getString("telefono"));
                tmp.setDireccion(fila.getString("Direccion"));
                tmp.setFecha_ingreso(fila.getString("Fecha_ingreso"));
                tmp.setFecha_salida(fila.getString("Fecha_salida"));
                tmp.localidad.setIdLocalidad(fila.getInt("Localidad_idLocalidad"));
                tmp.setCuil(fila.getString("Cuil"));
                tmp.setSueldo(fila.getInt("Sueldo"));
                tmp.setAntiguedad(fila.getInt("Antiguedad"));
                tmp.localidad.setNombre(fila.getString("Nombre"));
                
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
     
     
     public Empleado buscar(){
        try{
          
            String sql = "SELECT * FROM empleado WHERE dni='"+empleado.getDni()+"'"+ " or " + "Apellido='"+empleado.getApellido()+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                Empleado tmp = new Empleado();
                tmp.setDni( fila.getInt("dni") );
                tmp.setNombre(fila.getString("nombre"));
                tmp.setApellido(fila.getString("apellido"));
                tmp.setTelefono(fila.getString("telefono"));
                tmp.setDireccion(fila.getString("Direccion"));
                tmp.setFecha_ingreso(fila.getString("Fecha_ingreso"));
                tmp.setFecha_salida(fila.getString("Fecha_salida"));
                tmp.localidad.setIdLocalidad(fila.getInt("Localidad_idLocalidad"));
                tmp.setCuil(fila.getString("Cuil"));
                tmp.setSueldo(fila.getInt("Sueldo"));
                tmp.setAntiguedad(fila.getInt("Antiguedad"));
                tmp.localidad.setNombre(fila.getString("Nombre"));
                
                return tmp;
            }
            
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }        
        return null;
    }
     
     public void modificar(String Filtro ){
        try{
            
             if(empleado.getSeccion().equalsIgnoreCase("ventas")) idSeccion=1;
            
            String sql = "UPDATE empleado SET "
                    + "DNI='"+empleado.getDni()+"',"
                    + "Nombre='"+empleado.getNombre()+ "',"
                    + "Apellido='"+ empleado.getApellido()+ "',"
                    + "Telefono='"+empleado.getTelefono()+"', "
                    + "Direccion='"+empleado.getDireccion()+"',"
                    + "Fecha_ingreso='"+empleado.getFecha_ingreso()+"',"
                    + "Fecha_salida='"+empleado.getFecha_salida()+"',"
                    + "CUIL='"+empleado.getCuil()+"',"
                    + "Sueldo='"+empleado.getSueldo()+"',"
                    + "Antiguedad='"+empleado.getAntiguedad()+"',"
                    + "Localidad_idLocalidad='"+empleado.getLocalidad().getIdLocalidad()+"',"
                    + "Seccion_idSeccion='"+idSeccion+"'"
                    + " WHERE DNI='"+Filtro+"'"+" or "+"Apellido='"+Filtro+"'";
                    
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Empleado Modificado");
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }        
    }
     
     
     
    public void borrar(){
        try{
            String sql = "DELETE FROM empleado WHERE dni='"+empleado.getDni()+"'";
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Empleado Eliminado");
        }
        catch(SQLException e){
            System.out.println("Error al borrar datos a la tabla");
            
            System.out.println(empleado.getDni());
            System.out.println(empleado.getApellido());
        }        
    }
    
    
}

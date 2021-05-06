
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Localidad;
import modelo.LocalidadDAO;
import vista.GestionarEmpleado;


public class ControladorEmpleado implements ActionListener {
     
    private Conexion con;
    private GestionarEmpleado GestionarEmpleado;
    private Empleado modelo;
    private Localidad localidad;
     
     public ControladorEmpleado(Conexion con){
         this.con=con;
         GestionarEmpleado = new GestionarEmpleado(null,true);
         
    
     }
     
      public void ejecutar(){
         
         GestionarEmpleado.setControlador(this);
         
         Empleado empleado = new Empleado();
         EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado,con);
            
         this.RellenarTablas(empleadoDAO);
         GestionarEmpleado.ejecutar();
         
     }    
     
     @Override
     public void actionPerformed(ActionEvent e){
       
         
     if(e.getActionCommand().equals(GestionarEmpleado.AGREGAR_EMPLEADO)){
            
            
         
         
            String FSalida=GestionarEmpleado.getFegreso();
            FSalida=FSalida.replaceAll(" ", "");
            if(FSalida.length()==0) FSalida="0000-00-00";
            else FSalida=GestionarEmpleado.getFegreso();
        
            
            
            localidad = new Localidad();
            localidad.setIdLocalidad(Integer.parseInt(GestionarEmpleado.getLocalidadCP()));
            localidad.setNombre(GestionarEmpleado.getLocalidad());
            LocalidadDAO localidadDAO = new LocalidadDAO(localidad,con);
            localidadDAO.agregarLocalidad();
            
           
            
            Empleado empleado = new Empleado(GestionarEmpleado.getDni(),GestionarEmpleado.getNombre(),GestionarEmpleado.getApellido(),GestionarEmpleado.getTelefono(),GestionarEmpleado.getDireccion()
            ,GestionarEmpleado.getFingreso(),FSalida,GestionarEmpleado.getCuil(),
            GestionarEmpleado.getSueldo(),GestionarEmpleado.getAntiguedad(),GestionarEmpleado.getSeccion(),localidad);
            
            
            empleado.setApellido(GestionarEmpleado.getApellido());
            empleado.setCuil(GestionarEmpleado.getCuil());
            empleado.setDireccion(GestionarEmpleado.getDireccion());
            empleado.setDni(GestionarEmpleado.getDni());
            empleado.setFecha_ingreso(GestionarEmpleado.getFingreso());
            empleado.setFecha_salida(FSalida);
            empleado.setLocalidad(localidad);
            empleado.setNombre(GestionarEmpleado.getNombre());
            empleado.setSueldo(GestionarEmpleado.getSueldo());
            empleado.setTelefono(GestionarEmpleado.getTelefono());
            empleado.setAntiguedad(GestionarEmpleado.getAntiguedad());
            empleado.setSeccion(GestionarEmpleado.getSeccion());
            
            
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado,con);
            
            empleadoDAO.AgregarEmpleado();
            
            //UsuarioDAO usuarioDAP = new UsuarioDAO(usuario,con);
            //usuarioDAO.AgregarUsuario();
            this.RellenarTablas(empleadoDAO);
         
            
        }
        
        if(e.getActionCommand().equals(GestionarEmpleado.BUSCAR)){
              
            try{
               int dni = Integer.parseInt(GestionarEmpleado.getBusqueda());
               modelo =  new Empleado(dni,"","","","","","","",1,1,"",null);
               
            }catch(NumberFormatException q){
                String apellido=GestionarEmpleado.getBusqueda();
                modelo =  new Empleado(1,"",apellido,"","","","","",1,1,"",null);
                
            }
             
             
             EmpleadoDAO Emp = new EmpleadoDAO(modelo, con);
             Empleado tmp = Emp.buscar();
            
            if( tmp != null){
                GestionarEmpleado.setDni(tmp.getDni());
                GestionarEmpleado.setNombre(tmp.getNombre());
                GestionarEmpleado.setApellido(tmp.getApellido());
                GestionarEmpleado.setTelefono(tmp.getTelefono());
                GestionarEmpleado.setDireccion(tmp.getDireccion());
                GestionarEmpleado.setFingreso(tmp.getFecha_ingreso());
                GestionarEmpleado.setFegreso(tmp.getFecha_salida());
                GestionarEmpleado.setCuil(tmp.getCuil());
                GestionarEmpleado.setSueldo(tmp.getSueldo());
                GestionarEmpleado.setLocalidadCP(""+tmp.getLocalidad().getIdLocalidad());
                GestionarEmpleado.setAntiguedad(tmp.getAntiguedad());
                GestionarEmpleado.setLocalidad(tmp.getLocalidad().getNombre());
                
                JOptionPane.showMessageDialog(null,"Encontrado");
            }
        }
        
        if(e.getActionCommand().equals(GestionarEmpleado.MODIFICAR_EMPLEADO)){
            
            String FSalida=GestionarEmpleado.getFegreso();
            FSalida=FSalida.replaceAll(" ", "");
            if(FSalida.length()==0) FSalida="0000-00-00";
            else FSalida=GestionarEmpleado.getFegreso();
            
            localidad = new Localidad();
            localidad.setIdLocalidad(Integer.parseInt(GestionarEmpleado.getLocalidadCP()));
            localidad.setNombre(GestionarEmpleado.getLocalidad());
            LocalidadDAO localidadDAO = new LocalidadDAO(localidad,con);
            localidadDAO.agregarLocalidad();
            
           
            
            Empleado empleado = new Empleado(GestionarEmpleado.getDni(),GestionarEmpleado.getNombre(),GestionarEmpleado.getApellido(),GestionarEmpleado.getTelefono(),GestionarEmpleado.getDireccion()
            ,GestionarEmpleado.getFingreso(),FSalida,GestionarEmpleado.getCuil(),
            GestionarEmpleado.getSueldo(),GestionarEmpleado.getAntiguedad(),GestionarEmpleado.getSeccion(),localidad);
            
            
            empleado.setApellido(GestionarEmpleado.getApellido());
            empleado.setCuil(GestionarEmpleado.getCuil());
            empleado.setDireccion(GestionarEmpleado.getDireccion());
            empleado.setDni(GestionarEmpleado.getDni());
            empleado.setFecha_ingreso(GestionarEmpleado.getFingreso());
            empleado.setFecha_salida(FSalida);
            empleado.setLocalidad(localidad);
            empleado.setNombre(GestionarEmpleado.getNombre());
            empleado.setSueldo(GestionarEmpleado.getSueldo());
            empleado.setTelefono(GestionarEmpleado.getTelefono());
            empleado.setAntiguedad(GestionarEmpleado.getAntiguedad());
            empleado.setSeccion(GestionarEmpleado.getSeccion());
            
            
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado,con);
            
            empleadoDAO.modificar(GestionarEmpleado.getBusqueda());
            
            this.RellenarTablas(empleadoDAO);
          
          
            
        }
        
        if(e.getActionCommand().equals(GestionarEmpleado.ELIMINAR_EMPLEADO)){
             Empleado empleado =new Empleado();
             try{
                 int dni = Integer.parseInt(GestionarEmpleado.getBusqueda());
                 empleado.setDni(dni);
             }catch(NumberFormatException q){
                 String apellido=GestionarEmpleado.getBusqueda();
                 empleado.setApellido(apellido);
             }
             
             EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado,con);
             empleadoDAO.borrar();
             
             this.RellenarTablas(empleadoDAO);
             
        }
            
        
    }
     
     
     public void RellenarTablas(EmpleadoDAO empleadoDAO){
         ArrayList<String[]> lista = new ArrayList<String[]>();
            for(Empleado Emp : empleadoDAO.leer() ){
                String linea[] = new String[10];
                linea[0] = ""+Emp.getDni(); 
                linea[1] = Emp.getNombre();
                linea[2] = Emp.getApellido();
                linea[3] = Emp.getTelefono();
                linea[4] = Emp.getDireccion();
                linea[5] = Emp.getFecha_ingreso();
                linea[6] = Emp.getFecha_salida();
                linea[7] = Emp.getCuil();
                linea[8] = ""+Emp.getSueldo();
                linea[9] = ""+Emp.getAntiguedad();
                lista.add(linea);
            }
            GestionarEmpleado.cargarLista(lista);
            GestionarEmpleado.limpiarCajas();
     }
     
}

package controlador;

import Datos.Conexion;
import Datos.UsuarioDAO;
import Datos.LocalidadDAO;
import Datos.EmpleadoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.*;
import vista.GestionarEmpleado;

public class ControladorEmpleado implements ActionListener {

    private Conexion con;
    private GestionarEmpleado GestionarEmpleado;
    private Empleado modelo;
    private Localidad localidad;
    private Usuario _usuarioAutenticado;

    public ControladorEmpleado(Conexion con, Usuario usuarioAutenticado) {
        this.con = con;
        this._usuarioAutenticado = usuarioAutenticado;
        GestionarEmpleado = new GestionarEmpleado(null, true);

    }

    public void ejecutar() {

        GestionarEmpleado.setControlador(this);

        Empleado empleado = new Empleado();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado, con);
        LocalidadDAO ld = new LocalidadDAO(null, con);
        this.RellenarTablas(empleadoDAO);
        GestionarEmpleado.ejecutar(ld.ObtnerLocalidades(), empleadoDAO.ObtenerRoles());//mandar localidades y provincias // mandar roles.

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(GestionarEmpleado.AGREGAR_EMPLEADO)) {

            localidad = new Localidad();
            localidad.setCodigopostal(Integer.parseInt(GestionarEmpleado.getLocalidadCP()));
            localidad.setNombre(GestionarEmpleado.getLocalidad());

            Empleado empleado = new Empleado(GestionarEmpleado.getDni(), GestionarEmpleado.getNombre(), GestionarEmpleado.getApellido(), GestionarEmpleado.getTelefono(), GestionarEmpleado.getDireccion(),
                     GestionarEmpleado.getFingreso(), GestionarEmpleado.getFegreso(), GestionarEmpleado.getCuil(),
                    GestionarEmpleado.getSueldo(), GestionarEmpleado.getAntiguedad(), localidad);

            EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado, con);
            empleadoDAO.AgregarEmpleado();

            //Con el rol podemos hacer que lo seleccione en un combo box que carguemos de la base 
            Rol r = new Rol(2, "empleado");
            if (GestionarEmpleado.getRol().equals("admin")) {
                r = new Rol(1, "admin");
            }
            Usuario usuarioNuevo = new Usuario("" + empleado.getDni(), GestionarEmpleado.getContraseña());
            usuarioNuevo.setRol(r);
            UsuarioDAO usuarioDAO = new UsuarioDAO(usuarioNuevo, con);
            usuarioDAO.AgregarUsuario();
            this.RellenarTablas(empleadoDAO);

        }

        if (e.getActionCommand().equals(GestionarEmpleado.BUSCAR)) {

            GestionarEmpleado.BloquearDni();
            
            try {
                int dni =Integer.parseInt(GestionarEmpleado.getBusqueda());
                modelo = new Empleado(dni, "", "", "", "", "", "", "", 1, 1, null);

            } catch (NumberFormatException q) {
                String apellido = GestionarEmpleado.getBusqueda();
                modelo = new Empleado(1, "", apellido, "", "", "", "", "", 1, 1, null);

            }
            try {
                EmpleadoDAO Emp = new EmpleadoDAO(modelo, con);

                Usuario u = Emp.buscar();

                Empleado tmp = u.getEmpleado();

                Localidad l = new Localidad();
                LocalidadDAO ld = new LocalidadDAO(l, con);
                l = ld.ObtenerLocalidad(tmp.getLocalidad().getIdLocalidad());

                if (tmp != null) {

                    GestionarEmpleado.setDni(tmp.getDni());
                    GestionarEmpleado.setNombre(tmp.getNombre());
                    GestionarEmpleado.setApellido(tmp.getApellido());
                    GestionarEmpleado.setTelefono(tmp.getTelefono());
                    GestionarEmpleado.setDireccion(tmp.getDireccion());
                    GestionarEmpleado.setFingreso(tmp.getFecha_ingreso());
                    GestionarEmpleado.setFegreso(tmp.getFecha_salida());
                    GestionarEmpleado.setCuil(tmp.getCuil());
                    GestionarEmpleado.setSueldo(tmp.getSueldo());

                    GestionarEmpleado.setAntiguedad(tmp.getAntiguedad());
                    GestionarEmpleado.setProvincia(l.getProvincia());
                    GestionarEmpleado.setLocalidad(l.getNombre());

                    GestionarEmpleado.setRol(u.getRol().getDescripcion());

                    JOptionPane.showMessageDialog(null, "Encontrado");

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "En controlador empleado, No se encotro el empleado");
                Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getActionCommand().equals(GestionarEmpleado.MODIFICAR_EMPLEADO)) {

            localidad = new Localidad();
            localidad.setIdLocalidad(Integer.parseInt(GestionarEmpleado.getLocalidadCP()));
            localidad.setNombre(GestionarEmpleado.getLocalidad());

            Empleado empleado = new Empleado(GestionarEmpleado.getDni(), GestionarEmpleado.getNombre(), GestionarEmpleado.getApellido(), GestionarEmpleado.getTelefono(), GestionarEmpleado.getDireccion(),
                     GestionarEmpleado.getFingreso(), GestionarEmpleado.getFegreso(), GestionarEmpleado.getCuil(),
                    GestionarEmpleado.getSueldo(), GestionarEmpleado.getAntiguedad(), localidad);

            EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado, con);

            empleadoDAO.modificar(GestionarEmpleado.getBusqueda());
            
            Rol r = new Rol(2, "empleado");
            if (GestionarEmpleado.getRol().equals("admin")) {
                r = new Rol(1, "admin");
            }
            Usuario usuarioModificado = new Usuario("" + empleado.getDni(), GestionarEmpleado.getContraseña());
            usuarioModificado.setRol(r);
            UsuarioDAO usuarioDAO = new UsuarioDAO(usuarioModificado, con);
            usuarioDAO.ModificarUsuario(empleado.getDni());
            

            this.RellenarTablas(empleadoDAO);

        }

        if (e.getActionCommand().equals(GestionarEmpleado.ELIMINAR_EMPLEADO)) {
            Empleado empleado = new Empleado();
            try {
                int dni = Integer.parseInt(GestionarEmpleado.getBusqueda());
                empleado.setDni(dni);
            } catch (NumberFormatException q) {
                String apellido = GestionarEmpleado.getBusqueda();
                empleado.setApellido(apellido);
            }

            EmpleadoDAO empleadoDAO = new EmpleadoDAO(empleado, con);
            empleadoDAO.borrar();

            this.RellenarTablas(empleadoDAO);

        }

    }

    public void RellenarTablas(EmpleadoDAO empleadoDAO) {
        ArrayList<String[]> lista = new ArrayList<String[]>();
        for (Empleado Emp : empleadoDAO.leer()) {
            String linea[] = new String[10];
            linea[0] = "" + Emp.getDni();
            linea[1] = Emp.getNombre();
            linea[2] = Emp.getApellido();
            linea[3] = Emp.getTelefono();
            linea[4] = Emp.getDireccion();
            linea[5] = Emp.getFecha_ingreso();
            linea[6] = Emp.getFecha_salida();
            linea[7] = Emp.getCuil();
            linea[8] = "" + Emp.getSueldo();
            linea[9] = "" + Emp.getAntiguedad();
            lista.add(linea);
        }
        GestionarEmpleado.cargarLista(lista);
        GestionarEmpleado.limpiarCajas();
    }

}

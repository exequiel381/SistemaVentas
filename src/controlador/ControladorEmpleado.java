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
import vista.Principal;
import vista.VistaFamiliares;
import vista.VistaNovedades;

public class ControladorEmpleado implements ActionListener {

    private Conexion con;
    private GestionarEmpleado GestionarEmpleado;
    private Empleado _empleado;
    private Localidad localidad;
    private Usuario _usuarioAutenticado;
    private VistaFamiliares _vistaFamiliar;
    private EmpleadoDAO _empleadoDAO;
    private VistaNovedades _vistaNovedades;

    public ControladorEmpleado(Conexion con, Usuario usuarioAutenticado) {
        this.con = con;
        this._usuarioAutenticado = usuarioAutenticado;
        GestionarEmpleado = new GestionarEmpleado(null, true);
        this._empleado = new Empleado();

    }

    public void ejecutar() {

        GestionarEmpleado.setControlador(this);

        Empleado empleado = new Empleado();
        _empleadoDAO = new EmpleadoDAO(empleado, con);
        LocalidadDAO ld = new LocalidadDAO(null, con);
        this.RellenarTablas(_empleadoDAO);
        GestionarEmpleado.ejecutar(ld.ObtnerLocalidades(), _empleadoDAO.ObtenerRoles());//mandar localidades y provincias // mandar roles.

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(GestionarEmpleado.AGREGAR_EMPLEADO)) {
            //DEJE COMENTADOS DONDE SE GUARDA LA BASE DE DATOS
            if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea agregarlo ?") == 0) {
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

                //Buscamos el empleado con dni para mandar el ID, lo seteamos en usuario
                Usuario usuarioNuevo = new Usuario("" + empleado.getDni(), GestionarEmpleado.getContraseña());
                empleado.setIdEmpleado(empleadoDAO.ObtenerIdEmpleado());
                usuarioNuevo.setEmpleado(empleado);
                usuarioNuevo.setRol(r);
                UsuarioDAO usuarioDAO = new UsuarioDAO(usuarioNuevo, con);
                usuarioDAO.AgregarUsuario();

                if (JOptionPane.showConfirmDialog(null, "Agregar Grupo Familiar ?") == 0) {
                    _vistaFamiliar = new VistaFamiliares(null, true, empleado);
                    _vistaFamiliar.setControlador(this);
                    GestionarEmpleado.setVisible(false);
                    _vistaFamiliar.setVisible(true);

                }

                this.RellenarTablas(empleadoDAO);
            }

        }

        if (e.getActionCommand().equals(GestionarEmpleado.BUSCAR)) {

            GestionarEmpleado.BloquearDni();

            try {
                int dni = Integer.parseInt(GestionarEmpleado.getBusqueda());
                _empleado = new Empleado(dni, "", "", "", "", "", "", "", 1, 1, null);

            } catch (NumberFormatException q) {
                String apellido = GestionarEmpleado.getBusqueda();
                _empleado = new Empleado(1, "", apellido, "", "", "", "", "", 1, 1, null);

            }
            try {
                EmpleadoDAO Emp = new EmpleadoDAO(_empleado, con);

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

            if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificarlo?") == 0) {

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

        }

        if (e.getActionCommand().equals(GestionarEmpleado.ELIMINAR_EMPLEADO)) {

            if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminarlo ?") == 0) {
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

        if (e.getActionCommand().equals(_vistaFamiliar.GUARDAR_FAMILIARES)) {
            for (Familiar f : _vistaFamiliar.getFamilia()) {
                _empleadoDAO.AgregarFamiliar(f);
            }
            _vistaFamiliar.dispose();
        }

        if (e.getActionCommand().equals(GestionarEmpleado.AGREGARNOVEDAD)) {
            
            
            if (GestionarEmpleado.getBusqueda().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un DNI en el campo buscar");
            } else {
                try {
                    int dni = Integer.parseInt(GestionarEmpleado.getBusqueda());
                    _empleado.setDni(dni);

                } catch (NumberFormatException q) {
                    String apellido = GestionarEmpleado.getBusqueda();

                    _empleado.setApellido(apellido);
                }

                _empleadoDAO.setEmpleado(_empleado);
                _empleado = _empleadoDAO.buscar().getEmpleado();

                if (_empleado != null) {
                    _vistaNovedades = new VistaNovedades(null, true, _empleado);
                    _vistaNovedades.setControlador(this);
                    _vistaNovedades.setNovedades(_empleadoDAO.ObtenerNovedades());
                    _vistaNovedades.Ejecutar();
                  

                }

            }
        }
     
        
       

    }

    
    public void GuardarNovedad(Novedad novedad , Empleado empleado) {
        _empleadoDAO.setEmpleado(empleado);
        _empleadoDAO.GuardarNovedad(novedad);
        JOptionPane.showMessageDialog(null, "Novedad Guardada");
        _vistaNovedades.setNovedades(_empleadoDAO.ObtenerNovedades());
        _vistaNovedades.RellenarTabla();
        
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

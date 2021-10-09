/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Datos.Conexion;
import Datos.EmpleadoDAO;
import Datos.ProductoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.scripts.JO;
import modelo.BoletaSueldo;
import modelo.Comercio;
import modelo.Empleado;
import modelo.Producto;
import modelo.Usuario;
import vista.RecibosDeSueldoVista;

/**
 *
 * @author fiume
 */
public class ControladorSueldos {

    private Conexion _con;
    private Empleado _empleado;
    private Usuario _usuarioAutenticado;
    private EmpleadoDAO _empleadoDAO;
    private RecibosDeSueldoVista _reciboSueldoVista;
    private Comercio _comercio;
    private ArrayList<BoletaSueldo> _boletas;
    
  

    public ControladorSueldos(Conexion con, Usuario usuarioAutenticado,Comercio com) {
        this._con = con;
        this._comercio=com;
        this._usuarioAutenticado = usuarioAutenticado;
        this._empleadoDAO = new EmpleadoDAO(con);
        
        

    }

    public void Ejecutar() {
            _reciboSueldoVista = new RecibosDeSueldoVista(null, true,this);
            _boletas = _empleadoDAO.ObtenerTodasBoletas();
            _reciboSueldoVista.RellenarTabla(_boletas);
            _reciboSueldoVista.Ejecutar();
    }
    
    public void HacerBoletas(int mes, int anio) {

        if (_empleadoDAO.VerificarLiquidacionEnPeriodo(mes, anio)) {
            //Obtener ultimo id de boletas y mandar     
            int ultimoIdBoleta = _empleadoDAO.ObtenerUltimoIdBoleta();
            ArrayList<BoletaSueldo> boletas = _comercio.CrearBoletasSueldo(_empleadoDAO.leer(), _empleadoDAO.LeerConceptos(),_empleadoDAO.ObtenerNovedades(mes,anio), mes, anio, ultimoIdBoleta);
            for (BoletaSueldo boleta : boletas) {
                _empleadoDAO.GuardarBoletas(boleta);
                _empleadoDAO.GuardarDetalleBoleta(boleta);

                _boletas = _empleadoDAO.ObtenerTodasBoletas();
                _reciboSueldoVista.RellenarTabla(_boletas);
         
            }

        }else JOptionPane.showMessageDialog(null, "Ya se liquido ese periodo");
    }

    public void FiltrarBoletas(String Filtro) {
        ArrayList<BoletaSueldo> filtrado = new ArrayList<>();
        for (BoletaSueldo b : _boletas) {
            if (b.getEmpleado().getApellido().contains(Filtro) || (""+b.getEmpleado().getDni()).contains(Filtro) ) {
                filtrado.add(b);
            }
        }
        _reciboSueldoVista.RellenarTabla(filtrado);
    }


    
    
}

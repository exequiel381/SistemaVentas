/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Datos.PagoVentaDAO;
import Datos.Conexion;
import Datos.DepositoDAO;
import Datos.EmpleadoDAO;
import Datos.ProductoDAO;
import Datos.VentaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.FacturaVentaVista;
import vista.ListaVentas;

import vista.RealizarVenta;

/**
 *
 * @author FIume
 */
public class ControladorVentas implements ActionListener {

    private Conexion con;
    private RealizarVenta VentanaVentas;
    private ArrayList<LineaDeVenta> LineaVenta = new ArrayList<LineaDeVenta>();
    private Usuario _usuarioAutenticado;
    private double Subtotal;
    private VentaDAO aux;
    private int idVenta;
    private ListaVentas listaVentas;
    private Venta venta;
    private double Descuento = 0;
    private int _numeroLineaVenta = 0;

    public ControladorVentas(Conexion con, Usuario autenticacion) {
        this.con = con;
        VentanaVentas = new RealizarVenta(null, false);
        listaVentas = new ListaVentas(null, true);

        //Para poder asignar el valor de la ultima venta
        this.aux = new VentaDAO(con);
        this.idVenta = aux.UltimaVenta();
        this._usuarioAutenticado = autenticacion;
    }

    public void ejecutar() {

        VentanaVentas.setControlador(this);
        ProductoDAO p = new ProductoDAO(con);
        venta = new Venta();
        VentanaVentas.RellenarComboProductos(p.leer());
        VentanaVentas.OcultarModificar();
        VentanaVentas.ejecutar();

    }

    public void listarVentas() {
        listaVentas.setControlador(this);
        ProductoDAO p = new ProductoDAO(con);
        listaVentas.RellenarComboProductos(p.leer());
        this.RellenarTablasVentas(aux);
        listaVentas.ejecutar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase(VentanaVentas.VER_DETALLEV)) {

            //Parte de esto es innecesario debido a que solo buscare por codigo ahora
            Producto producto = new Producto();
            producto.setIdProducto(VentanaVentas.getPD());
            DepositoDAO dd = new DepositoDAO(con);
            if (!"".equals(producto.getIdProducto())) {
                ProductoDAO productoDAO = new ProductoDAO(producto, con);
                producto = productoDAO.buscar();

                if (producto != null) {
                    JOptionPane.showMessageDialog(null, "Descripcion: " + producto.getDescripcion() + "\nTalle :" + producto.getTalle() + "\nStock :" + dd.getCantidadDeStock(producto.getIdProducto()));
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el Producto");
                }
            }
        }
        if (e.getActionCommand().equalsIgnoreCase(VentanaVentas.AGREGAR_PROD)) {

            int cantidadTotalParaVender = VentanaVentas.getCantidadAgregar();

            DepositoDAO depDAO = new DepositoDAO(con);
            Producto producto = new Producto();
            producto.setIdProducto(VentanaVentas.getPD());
            producto.setDescripcion(VentanaVentas.getPD());

            //COMPROBAMOS QUE LA CANTIDAD QUE TENEMOS EN TODAS LAS LINEAS DE VENTA NO SEA MAYOR AL STOCK QUE TENEMOS, AQUI SUMAMOS TODO LO AGREGADO DE UN MISMO PRODUCTO
            for (LineaDeVenta l : LineaVenta) {

                //JOptionPane.showMessageDialog(null, producto.getCodigo() + " " + l.getProducto().getCodigo());
                String a = l.getProducto().getIdProducto().replaceAll(" ", "");
                String b = producto.getIdProducto().replaceAll(" ", "");

                if (a.equals(b)) {
                    cantidadTotalParaVender = cantidadTotalParaVender + l.getCantidad();

                }

            }

            if (!"".equals(producto.getIdProducto())
                    && depDAO.getDisponible(VentanaVentas.getPD(), cantidadTotalParaVender)) {
                ProductoDAO productoDAO = new ProductoDAO(producto, con);
                producto = productoDAO.buscar();
                if (producto != null) {
                    LineaDeVenta lv = new LineaDeVenta();
                    lv.setIdLineaVenta(_numeroLineaVenta++);
                    lv.setProducto(producto);
                    lv.setCantidad(VentanaVentas.getCantidadAgregar());

                    LineaVenta.add(lv); // este array lo mandamos a la vista para llenar la tabla, y al finalizar la venta, lo vaciamos
                    this.RellenarTabla();
                    venta.setLineas(LineaVenta);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el producto");
                }

            } else {
                JOptionPane.showMessageDialog(null, "No hay Stock Disponible");
            }
        }

        if (e.getActionCommand().equals(VentanaVentas.QUITAR_PROD)) {
//           //debemos crear un id de linea de venta
            //podemos hacer que coincida con la cantidad tambien

            int IndexARemover = -1;
            for (int i = 0; i < LineaVenta.size(); i++) {
                if (LineaVenta.get(i).getIdLineaVenta() == Integer.parseInt(VentanaVentas.getFilaSeleccionada())) {//podemos poner varias lineas del mismo producto
                    IndexARemover = i;
                }
            }

            if (IndexARemover >= 0) {
                LineaVenta.remove(LineaVenta.get(IndexARemover));
            }

            this.RellenarTabla();

        }

        if (e.getActionCommand().equals(VentanaVentas.CANCELAR_VENTA)) {
            LineaVenta.clear();
            this.RellenarTabla();
        }

        if (e.getActionCommand().equals(VentanaVentas.FINALIZAR_VENTA)) {
            idVenta++;
            PagoVenta pv;

            Empleado emp = new Empleado();
            emp.setDni(Integer.parseInt(_usuarioAutenticado.getUsuario()));
            EmpleadoDAO empDAO = new EmpleadoDAO(emp, con);
            emp.setIdEmpleado(empDAO.ObtenerIdEmpleado());
            Fecha fecha = new Fecha();

            venta.setIdVenta(idVenta);
            venta.setFecha(fecha.toString());
            venta.setLineas(LineaVenta);
            venta.setDescuento(Descuento);
            venta.setTotal(venta.CalcularTotal() - Descuento);
            venta.setEmpleado(emp);

            VentaDAO ventaDAO = new VentaDAO(venta, con);
            ventaDAO.Agregar();
            ventaDAO.AgregarLineasDeVenta(LineaVenta, idVenta);

            for (LineaDeVenta lv : LineaVenta) {

                DepositoDAO dd = new DepositoDAO(con);
                Stock d = new Stock(lv.getProducto(), 1);
                d.setCantidad_producto(dd.getCantidadDeStock(lv.getProducto().getIdProducto()));//Traer la Cantidad de Stock Existente
                dd = new DepositoDAO(d, con);

                dd.QuitarStock(lv.getCantidad());
            }

            JOptionPane.showMessageDialog(null, "Stock Actualizado");

            FacturaVentaVista fv = new FacturaVentaVista();
            fv.LlenarFactura(venta.getIdVenta(), venta);
            //VentanaVentas.setVisible(false);
            LineaVenta.clear();
            this.RellenarTabla();

            //La idea seria que la factura calcule su total con impuestos o lo que fuere que lleva
            //Que atributos van en factura, y si debemos guardarla en la base de da
            if (VentanaVentas.getTarjeta() != null) {
                pv = venta.GenerarPago(true);

            } else {
                pv = venta.GenerarPago(false);
            }

            pv.GenerarFactura(venta.getTotal());
            pv.setMontoPago(venta.getTotal());

            PagoVentaDAO pvd = new PagoVentaDAO(pv, con);//La factura la guardamos con el mismo id de venta
            pvd.AgregarPagoVenta();//Guardamos venta y factura

            //this.ImprimirFactura(pv.GenerarFactura());
        }

        if (e.getActionCommand().equals(listaVentas.VERDETALLE)) {

            Venta venta = new Venta();
            venta.setIdVenta(listaVentas.getIdSeleccionado());
            aux = new VentaDAO(venta, con);
            this.RellenarTablaDetalleVenta(aux);

        }

        if (e.getActionCommand().equals(VentanaVentas.DESCUENTO_VENTA)) {

            try {
                Descuento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de descuento"));
                Descuento += venta.DescuentoMasDeCincoPrendas();
            } catch (Exception u) {
                Descuento = venta.DescuentoMasDeCincoPrendas();
            }
            venta.setTotal(venta.CalcularTotal() - Descuento);
            venta.setDescuento(Descuento);
            VentanaVentas.setSubTotal(venta.getTotal());
            VentanaVentas.setSubTotalConIva(venta.getTotal() * 1.21);
        }

        if (e.getActionCommand().equals(listaVentas.FILTRAR)) {

            Empleado emple = new Empleado();
            Producto p = new Producto();

            if (listaVentas.getEmpleado().equals("") && listaVentas.getIdProducto().equalsIgnoreCase("TODOS")) {
                this.RellenarTablasVentas(aux, listaVentas.getDesde(), listaVentas.getHasta(), null, null);
            }

            if (listaVentas.getEmpleado().equals("") && !listaVentas.getIdProducto().equalsIgnoreCase("TODOS")) {
                p.setIdProducto(listaVentas.getIdProducto());
                ProductoDAO pd = new ProductoDAO(p, con);
                this.RellenarTablasVentas(aux, listaVentas.getDesde(), listaVentas.getHasta(), null, pd.buscar());
            }

            if (!listaVentas.getEmpleado().equals("") && listaVentas.getIdProducto().equalsIgnoreCase("TODOS")) {
                try {
                    int dni = Integer.parseInt(listaVentas.getEmpleado());
                    emple.setDni(dni);

                } catch (NumberFormatException q) {
                    String apellido = listaVentas.getEmpleado();
                    emple.setApellido(apellido);

                }
                EmpleadoDAO ed = new EmpleadoDAO(emple, con);
                emple = ed.buscar().getEmpleado();

                this.RellenarTablasVentas(aux, listaVentas.getDesde(), listaVentas.getHasta(), ed.buscar().getEmpleado(), null);

            }

            if (!listaVentas.getEmpleado().equals("") && !listaVentas.getIdProducto().equalsIgnoreCase("TODOS")) {
                try {
                    int dni = Integer.parseInt(listaVentas.getEmpleado());
                    emple.setDni(dni);

                } catch (NumberFormatException q) {
                    String apellido = listaVentas.getEmpleado();
                    emple.setApellido(apellido);

                }
                EmpleadoDAO ed = new EmpleadoDAO(emple, con);
                p.setIdProducto(listaVentas.getIdProducto());
                ProductoDAO pd = new ProductoDAO(p, con);
                this.RellenarTablasVentas(aux, listaVentas.getDesde(), listaVentas.getHasta(), ed.buscar().getEmpleado(), pd.buscar());
            }

        }

        /* if (e.getActionCommand().equals(listaVentas.MODIFICARLINEAVENTA)) {

            LineaDeVenta l = new LineaDeVenta();
            l.setIdLineaVenta(listaVentas.getIdFilaDetalle());
            l.setCantidad(listaVentas.getCantidad());

            Producto p = new Producto();
            p.setCodigo(listaVentas.getProducto());
            ProductoDAO pd = new ProductoDAO(p, con);
            p = pd.buscar();

            l.setProducto(p);

            LineaDeVentaDAO ld = new LineaDeVentaDAO(l, con);
            ld.modificarLineaVenta(listaVentas.getIdVenta());

            Venta venta = new Venta();
            venta.setIdVenta(listaVentas.getIdSeleccionado());
            aux = new VentaDAO(venta, con);
            this.RellenarTablaDetalleVenta(aux);

        }

        if (e.getActionCommand().equals(listaVentas.BORRARLINEAVENTA)) {
            LineaDeVenta l = new LineaDeVenta();
            l.setIdLineaVenta(listaVentas.getIdFilaDetalle());
            LineaDeVentaDAO ld = new LineaDeVentaDAO(l, con);
            ld.borrarLinea(listaVentas.getIdVenta());

            Venta venta = new Venta();
            venta.setIdVenta(listaVentas.getIdSeleccionado());
            aux = new VentaDAO(venta, con);
            this.RellenarTablaDetalleVenta(aux);
        }*/
    }//PERTENECEN A LA CREACION DE UNA VENTA

    public void FiltrarProductos() {
        ProductoDAO p = new ProductoDAO(con);
        ArrayList<Producto> filtrado = new ArrayList<>();
        for (Producto producto : p.leer()) {
            if (producto.getDescripcion().contains(VentanaVentas.getDescripcionFiltro())) {
                filtrado.add(producto);
            }
        }
        VentanaVentas.RellenarComboProductos(filtrado);
    }
    
    public void IniciarProcesoDeCambio(int idVenta){
        VentaDAO vd = new VentaDAO(con);
        Venta venta = vd.ObtenerVenta(idVenta);
        
       
    }

    public void RellenarTabla() {
        this.Subtotal = 0;
        ArrayList<String[]> lista = new ArrayList<String[]>();
        for (LineaDeVenta lv : LineaVenta) {
            Producto p = new Producto();
            p = lv.getProducto();
            String linea[] = new String[5];
            linea[0] = "" + lv.getIdLineaVenta();
            linea[1] = p.getIdProducto();
            linea[2] = p.getDescripcion();
            linea[3] = "" + lv.getCantidad();
            linea[4] = "" + lv.getSubTotal();
            lista.add(linea);
            Subtotal = Subtotal + lv.getSubTotal();
        }
        VentanaVentas.cargarLista(lista);
        VentanaVentas.setSubTotal(Subtotal);
        VentanaVentas.setSubTotalConIva(Subtotal * 1.21);
    }

    public void RellenarTablasVentas(VentaDAO ventaDAO) {

        Double TotalVentas = 0.0;

        ArrayList<String[]> lista = new ArrayList<String[]>();
        for (Venta venta : ventaDAO.leer()) {

            String linea[] = new String[10];
            linea[0] = "" + venta.getIdVenta();
            linea[1] = "" + venta.getFecha();
            linea[2] = "" + venta.getTotal();
            linea[3] = "" + venta.getEmpleado().getNombre();

            TotalVentas = TotalVentas + venta.getTotal();

            lista.add(linea);
        }
        listaVentas.cargarListaVentas(lista);
        listaVentas.setTotalVentas(TotalVentas);
    }

    public void RellenarTablasVentas(VentaDAO ventaDAO, String Desde, String Hasta, Empleado empleado, Producto p) {

        Double TotalVentas = 0.0;

        ArrayList<String[]> lista = new ArrayList<String[]>();
        for (Venta venta : ventaDAO.leer(Desde, Hasta, empleado, p)) {

            String linea[] = new String[10];
            linea[0] = "" + venta.getIdVenta();
            linea[1] = "" + venta.getFecha();
            linea[2] = "" + venta.getTotal();
            linea[3] = "" + venta.getEmpleado().getNombre();

            TotalVentas = TotalVentas + venta.getTotal();
            lista.add(linea);
        }
        listaVentas.cargarListaVentas(lista);
        listaVentas.setTotalVentas(TotalVentas);
    }

    public void RellenarTablaDetalleVenta(VentaDAO ventaDAO) {
        ArrayList<String[]> lista = new ArrayList<String[]>();
        for (LineaDeVenta lineaVenta : ventaDAO.leerLineasVentas()) {

            String linea[] = new String[10];
            linea[0] = "" + lineaVenta.getIdLineaVenta();
            linea[1] = "" + lineaVenta.getProducto().getIdProducto();
            linea[2] = "" + lineaVenta.getProducto().getDescripcion();
            linea[3] = "" + lineaVenta.getProducto().getPrecio();
            linea[4] = "" + lineaVenta.getCantidad();
            linea[5] = "" + lineaVenta.getSubTotal();

            lista.add(linea);
        }
        listaVentas.cargarListaDetalle(lista);
    }

   

    public void FiltrarVenta(String nFactura) {
        
      
    }



    
    
}

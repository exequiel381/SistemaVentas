/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Datos.Conexion;
import Datos.LineaPedidoDAO;
import Datos.ProveedorDAO;
import Datos.PedidoDAO;
import Datos.DepositoDAO;
import Datos.EmpleadoDAO;
import Datos.ProductoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.ListaPedidos;
import vista.ListaPedidosFinalizados;

import vista.RegistrarPedido;

/**
 *
 * @author FIume
 */
public class ControladorPedidos implements ActionListener {

    private Conexion con;
    private RegistrarPedido VentanaCompra;
    private ListaPedidos listaCompras;
    private ListaPedidosFinalizados listaPedidosFinalizados;
    private LineaPedido lineaPedido;
    private Producto producto;
    private ArrayList<LineaPedido> lineasDePedido = new ArrayList<LineaPedido>();
    private PedidoDAO aux;
    private int idPedido;
    private Usuario _usuarioAutenticado;
    private double totalPedido;
    
    
    
    public ControladorPedidos(Conexion con, Usuario autenticacion) {
        this.con = con;
        
        VentanaCompra = new RegistrarPedido(null, true);
        listaPedidosFinalizados = new ListaPedidosFinalizados(null,true,this);
        listaCompras = new ListaPedidos(null,true);
        
        this.aux = new PedidoDAO(con);
        this.idPedido = aux.UltimoPedido();
        this._usuarioAutenticado = autenticacion;

    }

    public void ejecutar() {
        ProveedorDAO p = new ProveedorDAO(con);
        VentanaCompra.RellenarCombo(p.leer());
        VentanaCompra.setControlador(this);
        VentanaCompra.ejecutar();
       
    }
    
    public void listarPedidosPendientes(){
        listaCompras.setControlador(this);
        this.RellenarTablasPedido(aux,"Pendiente");
        this.aux = new PedidoDAO(con);
        listaCompras.ejecutar();
        
    }
   
    public void listarPedidosFinalizados(){
        
        this.RellenarTablasPedido(aux,"Finalizado");
        this.aux = new PedidoDAO(con);
        listaPedidosFinalizados.ejecutar();
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(VentanaCompra.REGISTRAR_PEDIDO)) {
            idPedido++;
  
            Empleado emp = new Empleado();
            emp.setDni(Integer.parseInt(_usuarioAutenticado.getUsuario()));
            EmpleadoDAO empDAO = new EmpleadoDAO(emp, con);
            emp.setIdEmpleado(empDAO.ObtenerIdEmpleado());
            
            Proveedor prov = new Proveedor();
            prov.setRazoc(VentanaCompra.getProveedor());
            ProveedorDAO provDAO = new ProveedorDAO(prov, con);
            
            prov = provDAO.buscar();

            
            Fecha fecha = new Fecha();
           
            Pedido pedido = new Pedido(idPedido, lineasDePedido, VentanaCompra.getTotal() , fecha.toString(),emp, prov);
            pedido.setEstado("Pendiente");
            
            PedidoDAO pedidoDAO = new PedidoDAO(pedido, con);
            pedidoDAO.Agregar();
            
            pedidoDAO.agregarLineasDePedido(lineasDePedido, idPedido);//Crear metodo
           
            lineasDePedido.clear();
            this.RellenarTabla();

        }
        if (e.getActionCommand().equals(VentanaCompra.REGISTRAR_LINEA)) {
            producto = new Producto();
            producto.setIdProducto(VentanaCompra.getProducto());

            ProductoDAO productoDAO = new ProductoDAO(producto, con);
            producto = productoDAO.buscar();

            
            if (producto != null) {

                lineaPedido = new LineaPedido(producto, VentanaCompra.getCantidad(), VentanaCompra.getPrecioUnitario(), VentanaCompra.getMontoTot());
                lineasDePedido.add(lineaPedido);
                this.RellenarTabla();
            }
            else JOptionPane.showMessageDialog(null, "EL PRODUCTO NO ESTA REGISTRADO, POR FAVOR INGRESELO ANTES DE INGRESARLO A PEDIDO");

        }

        if (e.getActionCommand().equals(VentanaCompra.QUITAR_LINEA)) {
            int IndexARemover = 0;
            for (int i = 0; i < lineasDePedido.size(); i++) {

                if (lineasDePedido.get(i).getCodigoProducto().equals(VentanaCompra.seleccionarFila())) {
                    IndexARemover = i;
                }
            }
            lineasDePedido.remove(lineasDePedido.get(IndexARemover));

            this.RellenarTabla();
        }
        
        if (e.getActionCommand().equals(listaCompras.VERDETALLE)){
             
             Pedido pedido = new Pedido();
             pedido.setIdPedido(listaCompras.getIdSeleccionado());
             aux = new PedidoDAO(pedido,con);
             this.RellenarTablaDetallePedido(aux);//falta completar
             
        }
        if (e.getActionCommand().equals(listaCompras.FINALIZAR)){
            
            
            
             Pedido pedido = new Pedido();
             pedido.setIdPedido(listaCompras.getIdSeleccionado());
             pedido.setEstado("Finalizado");
             aux = new PedidoDAO(pedido,con);
             aux.modificar();
             
             
             Stock d = new Stock();
             DepositoDAO dd = new DepositoDAO(d, con);
             
             for(LineaPedido lp : aux.leerLineasPedido()){
                 
                 d.setProducto(lp.getProducto());
                 d.setCantidad_producto(dd.getCantidadDeStock(lp.getProducto().getIdProducto()));
                 dd.AgregarStock(lp.getCantidad());
                 
             }
             
             this.RellenarTablasPedido(aux,"Pendiente");
             
             
             
        }
        if (e.getActionCommand().equals(listaCompras.CANCELAR)){
             
             Pedido pedido = new Pedido();
             pedido.setIdPedido(listaCompras.getIdSeleccionado());
             pedido.setEstado("Cancelado");
             aux = new PedidoDAO(pedido,con);
             aux.modificar();
             this.RellenarTablasPedido(aux,"Pendiente");
             
        }
       /* if (e.getActionCommand().equals(listaCompras.BORRAR_COMPRA)){
             
             LineaPedido linea = new LineaPedido();
             Producto p = new Producto();
             p.setIdProducto((listaCompras.getProducto()));
             linea.setCantidad(listaCompras.getCantidad());
             linea.setProducto(p);
             linea.setPrecio_unitario(listaCompras.getPrecioCompra());
             linea.setSubTotal(listaCompras.getSubtotal());
             
             LineaPedidoDAO lineapedidoDAO = new LineaPedidoDAO(linea,con);
             lineapedidoDAO.borrarLinea(listaCompras.getPedido());
             
             this.RellenarTablaDetallePedido(aux);
        }
        if (e.getActionCommand().equals(listaCompras.MODIFICAR_COMPRA)){
             
             LineaPedido linea = new LineaPedido();
             Producto p = new Producto();
             p.setIdProducto((listaCompras.getProducto()));
             linea.setCantidad(listaCompras.getCantidad());
             linea.setProducto(p);
             linea.setPrecio_unitario(listaCompras.getPrecioCompra());
             linea.setSubTotal(listaCompras.getSubtotal());
             
             LineaPedidoDAO lineapedidoDAO = new LineaPedidoDAO(linea,con);
             lineapedidoDAO.moficarDetalle(listaCompras.getPedido());
             
             this.RellenarTablaDetallePedido(aux);
        }*/
        
        

    }
    
  public void FiltrarPedidos(String desde, String hasta) {
       PedidoDAO pd = new PedidoDAO(con);
       if(!desde.equalsIgnoreCase("") && !hasta.equalsIgnoreCase(""))     
       listaPedidosFinalizados.cargarListaPedido(pd.ObtenerPedidosFiltrados(desde,hasta));
       else this.RellenarTablasPedido(pd, "finalizado");
       
       
    }
    
    
    
    

    public void RellenarTabla() {

        this.totalPedido = 0;
        
        ArrayList<String[]> lista = new ArrayList<String[]>();
        for (LineaPedido p : lineasDePedido) {
            String linea[] = new String[4];
            linea[0] = p.getProducto().getIdProducto();
            linea[1] = "" + p.getCantidad();
            linea[2] = "" + p.getPrecio_unitario();
            linea[3] = "" + p.getSubTotal();
            lista.add(linea);
            
            totalPedido=totalPedido + p.getSubTotal();
        }
        VentanaCompra.cargarLista(lista);
        VentanaCompra.LimpiarCajas();
        VentanaCompra.setTotal(totalPedido);

    }
    
    public void RellenarTablasPedido(PedidoDAO pedidoDAO,String Estado){
         ArrayList<String[]> lista = new ArrayList<String[]>();
            for(Pedido pedido : pedidoDAO.leer(Estado) ){

                String linea[] = new String[10];
                linea[0] = ""+pedido.getIdPedido(); 
                linea[1] = ""+pedido.getFecha(); 
                linea[2] = ""+pedido.gettotal(); 
                linea[3] = ""+pedido.getProveedor().getRazoc(); 
                linea[4] = ""+pedido.getEmpleado().getNombre(); 
                linea[5] = ""+pedido.getEstado(); 
                 

                lista.add(linea);
            }
            listaCompras.cargarListaPedidos(lista);
            listaPedidosFinalizados.cargarLista(lista);
     }
    
    public void RellenarTablaDetallePedido(PedidoDAO pedidoDAO){
         ArrayList<String[]> lista = new ArrayList<String[]>();
            for(LineaPedido lineaPedido : pedidoDAO.leerLineasPedido()){

                String linea[] = new String[10];
                linea[0] = ""+lineaPedido.getProducto().getIdProducto(); 
                linea[1] = ""+lineaPedido.getProducto().getDescripcion(); 
                linea[2] = ""+lineaPedido.getPrecio_unitario(); 
                linea[3] = ""+lineaPedido.getCantidad(); 
                linea[4] = ""+lineaPedido.getCantidad()*lineaPedido.getPrecio_unitario(); 
              

                lista.add(linea);
            }
            listaCompras.cargarListaDetalle(lista);
     }

  
}

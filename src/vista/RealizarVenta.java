/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import controlador.ControladorVentas;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

/**
 *
 * @author FIume
 */
public class RealizarVenta extends javax.swing.JDialog{

   public static final String VER_DETALLEV="VER DETALLE DEL PRODUCTO";
   public static final String AGREGAR_PROD="AGREGAR PRODUCTO A LA VENTA";
   public static final String QUITAR_PROD="QUITAR PRODUCTO DE LA VENTA";
   public static final String FINALIZAR_VENTA="FINALIZAR LA VENTA";
   public static final String CANCELAR_VENTA="CANCELAR LA VENTA";
   public static final String DESCUENTO_VENTA="HACER DESCUENTO";
   private int filaSeleccionada;
   private String idProductoSeleccionado;
   private ArrayList<Producto> productos;
   private ControladorVentas _controladorVentas;
           
    public RealizarVenta(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        initComponents();
        
        this.setLocationRelativeTo(null);
        
         SpinnerNumberModel spn = new SpinnerNumberModel();
         spn.setMaximum(100);
         spn.setMinimum(1);
         spn.setValue(1);
         spnCantidadAgregar.setModel(spn);
        
         
         SpinnerNumberModel spn2 = new SpinnerNumberModel();
         spn.setMaximum(100);
         spn.setMinimum(1);
         spn2.setValue(1);
         //spnCantidadQuitar.setModel(spn2);
    }
    
    public void ejecutar(){
        this.setVisible(true);
    }
    
    public void setControlador(ControladorVentas control){
       this._controladorVentas=control;
       btnVerDetalle.setActionCommand(VER_DETALLEV);
       btnVerDetalle.addActionListener(control);
       
       btnAgregar.setActionCommand(AGREGAR_PROD);
       btnAgregar.addActionListener(control);
       
       btnQuitar.setActionCommand(QUITAR_PROD);
       btnQuitar.addActionListener(control);
       
       btnFinalizarVenta.setActionCommand(FINALIZAR_VENTA);
       btnFinalizarVenta.addActionListener(control);
       
       btnCancelar.setActionCommand(CANCELAR_VENTA);
       btnCancelar.addActionListener(control);
       
       btnDescuento.setActionCommand(DESCUENTO_VENTA);
       btnDescuento.addActionListener(control);
    }
        
    
    
    public void cargarLista(ArrayList<String[]> lista){
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Linea");
        tabla.addColumn("idProducto");
        tabla.addColumn("Producto");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Subtotal");
      
        
        
       for(String[] fila : lista ){
            tabla.addRow(fila);
        }        
        tablaRVenta.setModel(tabla);
    }
    
    
     
     public void RellenarComboProductos(ArrayList<Producto> productos){
       
        
         DefaultTableModel tablaProd = new DefaultTableModel();
         tablaProd .addColumn("IdProducto");
         tablaProd .addColumn("Descripcion");
          tablaProd .addColumn("Precio");
         tablaProd .addColumn("Talle");
        
      for(Producto p : productos){
            //cbxProducto.addItem(p.getIdProducto()+" - "+p.getDescripcion()+" T " + p.getTalle());
            tablaProd.addRow(new Object[]{p.getIdProducto(),p.getDescripcion(),p.getPrecio(),p.getTalle()});
        }
          
        tablaProductos.setModel(tablaProd);
         
         
         
    }
     
     public void MostrarModificar(){
         btnModificarVenta.setVisible(true);
     }
     public void OcultarModificar(){
         btnModificarVenta.setVisible(false);
     }
     
    public int getCantidadAgregar(){
        return Integer.parseInt(spnCantidadAgregar.getValue().toString());
    }   
      
     
    
    public String getPD(){
        //return cbxProducto.getSelectedItem().toString().substring(0,2);// Tomamos de la cadena Desde el indice 0 al 2
        return this.idProductoSeleccionado+"";
    }
    
    
    
     public void setSubTotal(double SubTotal){
        txtSubtotal.setText(Double.toString(SubTotal));
    }
     
     public double getTotal(){
         return Double.parseDouble(txtSubtotal.getText());
     }
     
     public Object getTarjeta(){
         return CheckTarjeta.getSelectedObjects();
         
     }
     
     public void setFilaSeleccionada(int filaSeleccionada){
         this.filaSeleccionada=filaSeleccionada;
     }
     
     public String getFilaSeleccionada(){
        return ""+filaSeleccionada;
     }
   
    
    public String getDescripcionFiltro(){
        return txtDescripcionFiltro.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRVenta = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnVerDetalle = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnFinalizarVenta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        CheckTarjeta = new javax.swing.JCheckBox();
        spnCantidadAgregar = new javax.swing.JSpinner();
        btnDescuento = new javax.swing.JButton();
        btnModificarVenta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        txtDescripcionFiltro = new javax.swing.JTextField();
        txtPrecioIVA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Principal - Registrar Ventas");

        tablaRVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "idProducto", "Descripcion", "Precio"
            }
        ));
        tablaRVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaRVenta);
        tablaRVenta.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel2.setText("Buscar por Descripcion");

        btnVerDetalle.setText("Ver Detalles");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar ");

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        txtSubtotal.setEditable(false);
        txtSubtotal.setText(" ");

        jLabel3.setText("Subtotal  $");

        btnFinalizarVenta.setText("Finalizar Venta");

        btnCancelar.setText("Cancelar");

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        CheckTarjeta.setText("Pago Tarjeta");

        btnDescuento.setText("Realizar Descuento");

        btnModificarVenta.setText("Modificar Venta");

        jLabel1.setText("Incluye IVA %21");

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductos);

        txtDescripcionFiltro.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtDescripcionFiltroInputMethodTextChanged(evt);
            }
        });
        txtDescripcionFiltro.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDescripcionFiltroPropertyChange(evt);
            }
        });
        txtDescripcionFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionFiltroKeyTyped(evt);
            }
        });

        txtPrecioIVA.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Realizar una nueva Venta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btnFinalizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(btnModificarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(txtPrecioIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDescuento)
                        .addGap(54, 54, 54)
                        .addComponent(CheckTarjeta)
                        .addGap(93, 93, 93))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(54, 54, 54)
                        .addComponent(txtDescripcionFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(313, 313, 313))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(360, 360, 360))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(spnCantidadAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnVerDetalle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcionFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnCantidadAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(btnQuitar)
                    .addComponent(btnVerDetalle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(CheckTarjeta)
                                    .addComponent(btnDescuento))
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addGap(11, 11, 11)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFinalizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar)
                            .addComponent(jButton1)
                            .addComponent(btnModificarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaRVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRVentaMouseClicked
        int filaVenta = tablaRVenta.rowAtPoint(evt.getPoint());
        filaSeleccionada = Integer.parseInt(String.valueOf(tablaRVenta.getValueAt(filaVenta, 0)));
        this.setFilaSeleccionada(filaSeleccionada);
    }//GEN-LAST:event_tablaRVentaMouseClicked

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        int filaProductos = tablaProductos.rowAtPoint(evt.getPoint());
        idProductoSeleccionado= String.valueOf(tablaProductos.getValueAt(filaProductos, 0));
        
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void txtDescripcionFiltroInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtDescripcionFiltroInputMethodTextChanged
        
    }//GEN-LAST:event_txtDescripcionFiltroInputMethodTextChanged

    private void txtDescripcionFiltroPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDescripcionFiltroPropertyChange
       
    }//GEN-LAST:event_txtDescripcionFiltroPropertyChange

    private void txtDescripcionFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionFiltroKeyTyped
         
                 _controladorVentas.FiltrarProductos();
           
          
    }//GEN-LAST:event_txtDescripcionFiltroKeyTyped

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckTarjeta;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDescuento;
    private javax.swing.JButton btnFinalizarVenta;
    private javax.swing.JButton btnModificarVenta;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spnCantidadAgregar;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTable tablaRVenta;
    private javax.swing.JTextField txtDescripcionFiltro;
    private javax.swing.JTextField txtPrecioIVA;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables

    public void setSubTotalConIva(double d) {
        txtPrecioIVA.setText(""+d);
    }
}

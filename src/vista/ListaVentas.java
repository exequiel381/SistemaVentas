/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.bulenkov.darcula.DarculaLaf;
import controlador.Controlador;
import controlador.ControladorVentas;
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import modelo.Producto;

/**
 *
 * @author FIume
 */
public class ListaVentas extends javax.swing.JDialog {

    //SI NO ANDA EL FILTRAR O LOS TEXBOX ES POR QUE LOS COPIE DE OTRA VISTA
    public static final String MODIFICARLINEAVENTA = "MODIFICAR UNA LINEA DE VENTA";
    public static final String BORRARLINEAVENTA = "BORRAR UNA LINEA DE VENTA";
    public static final String FILTRAR = "FILTRAR VENTAS";
    public static final String VERDETALLE = "VER DETALLE DE PEDIDO";
    
    public int idSeleccionado;
    private int idLineaVenta;
    private ControladorVentas _controladorVentas;

    public ListaVentas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
       
            
            initComponents();
            this.setLocationRelativeTo(null);
            
            
//            //Le damos el formato de fecha al texto
//            MaskFormatter formato1 = new MaskFormatter("HHHH-HH-HH"); // 4 cifras
//            formato1.setValidCharacters("-0123456789");
//            formato1.install(txtDesde);
//
//            MaskFormatter formato2 = new MaskFormatter("HHHH-HH-HH"); // 4 cifras
//            formato2.setValidCharacters("-0123456789");
//            formato2.install(txtHasta);
       
    }

    public void ejecutar() {
        this.setVisible(true);
    }
    
    public void RellenarComboProductos(ArrayList<Producto> producto){
        for(Producto p : producto){
            cbxProducto.addItem(p.getIdProducto()+" - "+p.getDescripcion()+" T " + p.getTalle());
            
        }
    }

    public void setControlador(ControladorVentas control) {
        
        this._controladorVentas=control;
        
        btnBorrar.setActionCommand(BORRARLINEAVENTA);
        btnBorrar.addActionListener(control);
        btnBorrar.setVisible(false);

        btnModificar.setActionCommand(MODIFICARLINEAVENTA);
        btnModificar.addActionListener(control);
        

        btnFiltrar.setActionCommand(FILTRAR);
        btnFiltrar.addActionListener(control);

        btnVerDetalle.setActionCommand(VERDETALLE);
        btnVerDetalle.addActionListener(control);
        
        
    }
    
    

    public void cargarListaVentas(ArrayList<String[]> lista) {
        DefaultTableModel ventas = new DefaultTableModel();
        ventas.addColumn("idVenta");
        ventas.addColumn("Fecha");
        ventas.addColumn("Total");
        ventas.addColumn("Empleado");

        for (String[] fila : lista) {
            ventas.addRow(fila);
        }
        tablaVentas.setModel(ventas);
    }

    public void cargarListaDetalle(ArrayList<String[]> lista) {
        DefaultTableModel detalle = new DefaultTableModel();
        detalle.addColumn("idLineaDeVenta");
        detalle.addColumn("idProducto");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Precio Unitario");
        detalle.addColumn("Cantidad");
        detalle.addColumn("Subtotal");

        for (String[] fila : lista) {
            detalle.addRow(fila);
        }
        tablaDetalle.setModel(detalle);
    }

    public void setTotalVentas(Double Total){
        txtTotaVentas.setText(Total.toString());
    }
            
    public String getEmpleado(){
        return txtEmpleado.getText();
    }
    
    public String getIdProducto(){
        if(cbxProducto.getSelectedItem().toString().equalsIgnoreCase("TODOS"))  return "TODOS";
        return cbxProducto.getSelectedItem().toString().substring(0,2);
    }
    

    public int getIdFilaDetalle() {
        return idLineaVenta;
    }

    public void setIdFilaDetalle(int filaDetalle) {
        this.idLineaVenta= filaDetalle;
    }

    

    public void setFactura(int Factura) {
        txtFactura.setText(Integer.toString(Factura));
    }

    public int getFactura() {
        return Integer.parseInt(txtFactura.getText());
    }

    

    
    
    public void setIdVenta(int idVenta) {
        txtIdVenta.setText(Integer.toString(idVenta));
    }

    public int getIdVenta() {
        try{
             return Integer.parseInt(txtIdVenta.getText());
        }catch(Exception e){
            return -1;
        }
       
    }

    


    public int getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(int idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }


    public String getDesde() {
        Date date = txtDesde.getDate();
       
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        String f = fecha.toString();
       
        return f;
    }


    public String getHasta() {
        Date date = txtHasta.getDate();
       
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        String f = fecha.toString();
       
        return f;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        txtTotaVentas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDesde = new com.toedter.calendar.JDateChooser();
        txtHasta = new com.toedter.calendar.JDateChooser();
        Empleado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbxProducto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 152, 255));

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_Factura", "Id_Producto", "Cantidad", "Precio Total", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);
        tablaVentas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablaVentas.getColumnModel().getColumnCount() > 0) {
            tablaVentas.getColumnModel().getColumn(4).setMinWidth(25);
            tablaVentas.getColumnModel().getColumn(4).setPreferredWidth(25);
            tablaVentas.getColumnModel().getColumn(4).setMaxWidth(25);
        }

        btnModificar.setText("Realizar Cambios");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("id_Factura");

        txtFactura.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel6.setText("Detalle");

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "idProducto", "Descripcion", "Precio", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDetalleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDetalle);
        tablaDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablaDetalle.getColumnModel().getColumnCount() > 0) {
            tablaDetalle.getColumnModel().getColumn(3).setMinWidth(25);
            tablaDetalle.getColumnModel().getColumn(3).setPreferredWidth(25);
            tablaDetalle.getColumnModel().getColumn(3).setMaxWidth(25);
        }

        jLabel7.setText("Hasta");

        jLabel10.setText("Desde");

        btnFiltrar.setText("Filtrar");

        btnVerDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_scroll_down_26px.png"))); // NOI18N

        jLabel3.setText("idVenta");

        txtIdVenta.setEditable(false);

        txtTotaVentas.setEditable(false);

        jLabel11.setText("Total En Ventas $");

        Empleado.setText("Empleado");

        jLabel2.setText("Producto");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel8.setText("Ventas");

        jLabel4.setText("Buscar Por Factura");

        txtNumeroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroFacturaKeyTyped(evt);
            }
        });

        jButton1.setText("Buscar");

        jLabel5.setText("Falta programar esta busqueda");

        cbxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtIdVenta)
                                                    .addComponent(txtFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                                .addGap(85, 85, 85)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel7)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(53, 53, 53)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(Empleado)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(16, 16, 16)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                            .addComponent(cbxProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTotaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnFiltrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel5)
                                        .addGap(117, 117, 117))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1043, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(461, 461, 461)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
            .addGroup(layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(Empleado)
                        .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerDetalle))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetalleMouseClicked
        int filaDetalle = tablaDetalle.rowAtPoint(evt.getPoint());
        idLineaVenta = Integer.parseInt(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 0)));
        Double subTotal;
        //txtCantidad.setText(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 4)));
        //txtProducto.setText(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 1)));
        //subTotal = Double.parseDouble(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 3))) * Double.parseDouble(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 4)));
        //txtSubtotal.setText(Double.toString(subTotal));        // TODO add your handling code here:
    }//GEN-LAST:event_tablaDetalleMouseClicked

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        int filaPedido = tablaVentas.rowAtPoint(evt.getPoint());
        idSeleccionado = Integer.parseInt(String.valueOf(tablaVentas.getValueAt(filaPedido, 0)));
        this.setIdVenta(idSeleccionado);
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int idVenta = this.getIdVenta();
        if(idVenta >= 0)  _controladorVentas.IniciarProcesoDeCambio(idVenta);
        else JOptionPane.showMessageDialog(null,"No selecciono ninguna venta");
            
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtNumeroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturaKeyTyped
        this._controladorVentas.FiltrarVenta(txtNumeroFactura.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroFacturaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Empleado;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JComboBox<String> cbxProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaVentas;
    private com.toedter.calendar.JDateChooser txtDesde;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtFactura;
    private com.toedter.calendar.JDateChooser txtHasta;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtTotaVentas;
    // End of variables declaration//GEN-END:variables
}

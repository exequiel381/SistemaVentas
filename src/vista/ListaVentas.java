/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import controlador.ControladorVentas;
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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

    public void setControlador(ControladorVentas control) {
        btnBorrar.setActionCommand(BORRARLINEAVENTA);
        btnBorrar.addActionListener(control);
         btnBorrar.setVisible(false);

        btnModificar.setActionCommand(MODIFICARLINEAVENTA);
        btnModificar.addActionListener(control);
         btnModificar.setVisible(false);

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

        for (String[] fila : lista) {
            detalle.addRow(fila);
        }
        tablaDetalle.setModel(detalle);
    }

    public void setTotalVentas(Double Total){
        txtTotaVentas.setText(Total.toString());
    }
            
    
    public Double getSubtotal() {
        return Double.parseDouble(txtSubtotal.getText());
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

    public void setSubTotal(double SubTotal) {
        txtSubtotal.setText(Double.toString(SubTotal));
    }

    public void setCantidad(int Cantidad) {
        txtCantidad.setText(Integer.toString(Cantidad));
    }

    public int getCantidad() {
        return Integer.parseInt(txtCantidad.getText());
    }
    
    
    public void setIdVenta(int idVenta) {
        txtIdVenta.setText(Integer.toString(idVenta));
    }

    public int getIdVenta() {
        return Integer.parseInt(txtIdVenta.getText());
    }

    public void setProducto(int Producto) {
        txtProducto.setText(Integer.toString(Producto));
    }

    public String getProducto() {
        return txtProducto.getText();
    }

    public int getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(int idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }

    public void setDesde(String Desde) {
        txtProducto.setText(Desde);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        txtTotaVentas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDesde = new com.toedter.calendar.JDateChooser();
        txtHasta = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btnModificar.setText("Modificar");

        btnBorrar.setText("Borrar");

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("id_Factura");

        jLabel2.setText("Id_Producto");

        jLabel4.setText("Cantidad");

        jLabel5.setText("Subtotal");

        txtFactura.setEnabled(false);

        txtSubtotal.setEnabled(false);

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

        jLabel9.setText("AAAA-MM-DD");

        jLabel7.setText("Hasta");

        jLabel8.setText("AAAA-MM-DD");

        jLabel10.setText("Desde");

        btnFiltrar.setText("Filtrar");

        btnVerDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_scroll_down_26px.png"))); // NOI18N

        jLabel3.setText("idVenta");

        txtIdVenta.setEditable(false);

        txtTotaVentas.setEditable(false);

        jLabel11.setText("Total En Ventas $");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificar)
                .addGap(40, 40, 40)
                .addComponent(btnBorrar)
                .addGap(45, 45, 45)
                .addComponent(jButton3)
                .addGap(236, 236, 236))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(333, 333, 333))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtIdVenta)
                                                    .addComponent(txtFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addComponent(jLabel11))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9)
                                                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTotaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(75, 75, 75)))
                                .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVerDetalle)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7)
                                    .addComponent(btnFiltrar))
                                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrar)
                    .addComponent(btnModificar)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetalleMouseClicked
        int filaDetalle = tablaDetalle.rowAtPoint(evt.getPoint());
        idLineaVenta = Integer.parseInt(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 0)));
        Double subTotal;
        txtCantidad.setText(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 4)));
        txtProducto.setText(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 1)));
        subTotal = Double.parseDouble(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 3))) * Double.parseDouble(String.valueOf(tablaDetalle.getValueAt(filaDetalle, 4)));
        txtSubtotal.setText(Double.toString(subTotal));        // TODO add your handling code here:
    }//GEN-LAST:event_tablaDetalleMouseClicked

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        int filaPedido = tablaVentas.rowAtPoint(evt.getPoint());
        idSeleccionado = Integer.parseInt(String.valueOf(tablaVentas.getValueAt(filaPedido, 0)));
        this.setIdVenta(idSeleccionado);
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVerDetalle;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtCantidad;
    private com.toedter.calendar.JDateChooser txtDesde;
    private javax.swing.JTextField txtFactura;
    private com.toedter.calendar.JDateChooser txtHasta;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotaVentas;
    // End of variables declaration//GEN-END:variables
}

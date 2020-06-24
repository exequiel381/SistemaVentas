/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author FIume
 */
public class Principal extends javax.swing.JFrame {

    public static final String EMPLEADOS="MENU EMPLEADOS";
    public static final String COMPRAS="MENU COMPRAS";
    public static final String VENTAS="MENU VENTAS";
    public static final String STOCK="MENU STOCK";
    public static final String PRODUCTOS="MENU PRODUCTOS";
    public static final String PROVEEDORES="MENU PROVEEDORES";
    public static final String LISTAVENTAS="LISTA DE VENTAS";
    public static final String LISTAPENDIENTES="LISTA DE PEDIDOS PENDIENTES";
    public static final String LISTAFINALIZADAS="LISTA DE PEDIDOS FINALIZADOS Y CANCELADOS";
    
    
    public Principal() {
        this.Apariencia();
        initComponents();
        this.setTitle("Menu Principal");
         this.setLocationRelativeTo(null);
    }

    public void ejecutar(){
        this.setVisible(true);
    }
    
    public void setControlador(Controlador control){
       MenuEmpleado.setActionCommand(EMPLEADOS);
       MenuEmpleado.addActionListener(control);
       
       MenuCompras.setActionCommand(COMPRAS);
       MenuCompras.addActionListener(control);
       
       MenuVentas.setActionCommand(VENTAS);
       MenuVentas.addActionListener(control);
       
       MenuStock.setActionCommand(STOCK);
       MenuStock.addActionListener(control);
       
       MenuProductos.setActionCommand(PRODUCTOS);
       MenuProductos.addActionListener(control);
       
       MenuProveedores.setActionCommand(PROVEEDORES);
       MenuProveedores.addActionListener(control);
       
       MenuListaVentas.setActionCommand(LISTAVENTAS);
       MenuListaVentas.addActionListener(control);
       
       MenuPendientes.setActionCommand(LISTAPENDIENTES);
       MenuPendientes.addActionListener(control);
       
       MenuFinalizados.setActionCommand(LISTAFINALIZADAS);
       MenuFinalizados.addActionListener(control);
    }
    
    
     public void Apariencia(){
       
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPrueba = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuVentas = new javax.swing.JMenuItem();
        MenuListaVentas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuEmpleado = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MenuStock = new javax.swing.JMenuItem();
        MenuProductos = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MenuCompras = new javax.swing.JMenuItem();
        MenuPendientes = new javax.swing.JMenuItem();
        MenuFinalizados = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        MenuProveedores = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPrueba.setFont(new java.awt.Font("Yu Gothic", 0, 24)); // NOI18N
        jPrueba.setText("BIENVENIDO");
        jPrueba.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPrueba.setContentAreaFilled(false);
        jPrueba.setFocusPainted(false);
        jPrueba.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPruebaMouseMoved(evt);
            }
        });
        jPrueba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPruebaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPruebaMouseExited(evt);
            }
        });
        getContentPane().add(jPrueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Negro Blanco Vintage Ropa Moda Instagram Publicación.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-130, 0, 910, 610));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuBar1.setAlignmentX(1.0F);

        jMenu1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas.PNG"))); // NOI18N
        jMenu1.setText("Ventas");
        jMenu1.setBorderPainted(true);
        jMenu1.setFocusPainted(true);
        jMenu1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenu1MouseMoved(evt);
            }
        });
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenu1MouseExited(evt);
            }
        });

        MenuVentas.setText("Ventas");
        jMenu1.add(MenuVentas);

        MenuListaVentas.setText("Lista de Ventas");
        jMenu1.add(MenuListaVentas);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empleado.PNG"))); // NOI18N
        jMenu2.setText("Empleado");

        MenuEmpleado.setText("Gestionar Empleados");
        jMenu2.add(MenuEmpleado);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Stock.PNG"))); // NOI18N
        jMenu3.setText("Stock");

        MenuStock.setText("Gestionar Stock");
        MenuStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuStockActionPerformed(evt);
            }
        });
        jMenu3.add(MenuStock);

        MenuProductos.setText("Productos");
        jMenu3.add(MenuProductos);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compras.PNG"))); // NOI18N
        jMenu4.setText("Compras");

        MenuCompras.setText("Registrar Compra");
        jMenu4.add(MenuCompras);

        MenuPendientes.setText("Lista de Pedidos Pendientes");
        jMenu4.add(MenuPendientes);

        MenuFinalizados.setText("Lista de Pedidos Finalizados/Cancelados");
        jMenu4.add(MenuFinalizados);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Facturas.PNG"))); // NOI18N
        jMenu5.setText("Facturas");

        jMenuItem3.setText("Facturas de Venta");
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Facturas de Compras");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proveedores.PNG"))); // NOI18N
        jMenu6.setText("Proveedores");

        MenuProveedores.setText("Registrar Proveedor");
        jMenu6.add(MenuProveedores);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuStockActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jPruebaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPruebaMouseMoved
        jPrueba.setBorder(javax.swing.BorderFactory.createLineBorder(Color.yellow));
            // TODO add your handling code here:
    }//GEN-LAST:event_jPruebaMouseMoved

    private void jPruebaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPruebaMouseExited
        jPrueba.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK ));
    }//GEN-LAST:event_jPruebaMouseExited

    private void jPruebaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPruebaMouseClicked
        JOptionPane.showMessageDialog(null, "Holaa");        // TODO add your handling code here:
    }//GEN-LAST:event_jPruebaMouseClicked

    private void jMenu1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseMoved
       jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED));
    }//GEN-LAST:event_jMenu1MouseMoved

    private void jMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseExited
       jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
       // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseExited

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuCompras;
    private javax.swing.JMenuItem MenuEmpleado;
    private javax.swing.JMenuItem MenuFinalizados;
    private javax.swing.JMenuItem MenuListaVentas;
    private javax.swing.JMenuItem MenuPendientes;
    private javax.swing.JMenuItem MenuProductos;
    private javax.swing.JMenuItem MenuProveedores;
    private javax.swing.JMenuItem MenuStock;
    private javax.swing.JMenuItem MenuVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JButton jPrueba;
    // End of variables declaration//GEN-END:variables
}

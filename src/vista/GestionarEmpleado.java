/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import modelo.Autocomplete;
import controlador.Controlador;
import controlador.ControladorEmpleado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import modelo.Localidad;
import modelo.Rol;

/**
 *
 * @author FIume
 */
public class GestionarEmpleado extends javax.swing.JDialog {

    public static final String GENERAR_RECIBO="Generar Recibo de sueldo";
    public static final String AGREGAR_EMPLEADO="AGREGAR EMPLEADO";
    public static final String ELIMINAR_EMPLEADO="ELIMINAR EMPLEADO";
    public static final String MODIFICAR_EMPLEADO="MODIFICAR EMPLEADO";
    public static final String BUSCAR="BUSCAR EMPLEADO";
    public ArrayList<Localidad> localidades = new ArrayList<>();
    
            
    public GestionarEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
            try {
            initComponents();
            this.setLocationRelativeTo(null);
            /*
            //Le damos el formato de fecha al texto
            MaskFormatter formato1 = new MaskFormatter("HHHH-HH-HH"); // 4 cifras
            formato1.setValidCharacters("-0123456789");
            formato1.install(txtFechaIngreso);
            //Formato para texto
            MaskFormatter formato2 = new MaskFormatter("HHHH*HH*HH"); // 4 cifras
            formato2.setValidCharacters("-0123456789");
            formato2.install(txtFechaSalida);
            */
        } catch (Exception ex) {
            Logger.getLogger(ListaVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.setTitle("Gestion de Empleados");
    }
    
     public void ejecutar(ArrayList<Localidad> localidades, ArrayList<Rol> roles){
        this.localidades = localidades;
        
        ArrayList<String> p = new ArrayList<>();
         for (Localidad l : localidades) {
             if(!p.contains(l.getProvincia())){
                 p.add(l.getProvincia());
            }
         }
         Collections.sort(p);
         for(String s : p){
             cbxProvincia.addItem(s);
         }
         
         for(Rol r : roles){
             cbxRoles.addItem(r.getDescripcion());
         }
           
        
        this.setVisible(true);
    }
    
    public void setControlador(ControladorEmpleado control){
       btnGenerarRecibo.setActionCommand(GENERAR_RECIBO);
       btnGenerarRecibo.addActionListener(control);
       
       btnAgregarEmpleado.setActionCommand(AGREGAR_EMPLEADO);
       btnAgregarEmpleado.addActionListener(control);
       
       btnDarBajaEmpleado.setActionCommand(ELIMINAR_EMPLEADO);
       btnDarBajaEmpleado.addActionListener(control);
       
       btnModificarEmpleado.setActionCommand(MODIFICAR_EMPLEADO);
       btnModificarEmpleado.addActionListener(control);
       
       btnBuscar.setActionCommand(BUSCAR);
       btnBuscar.addActionListener(control);
    }
    
    
    public void cargarLista(ArrayList<String[]> lista){
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Dni");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Telefono");
        tabla.addColumn("Direccion");
        tabla.addColumn("Fecha_Ingreso");
        tabla.addColumn("Fecha_Salida");
        tabla.addColumn("Cuil");
        tabla.addColumn("Sueldo");
        tabla.addColumn("Antiguedad");
        
       for(String[] fila : lista ){
            tabla.addRow(fila);
        }      
 
    
      
        TablaEmpleado.setModel(tabla);
    }
    
    
    public String getBusqueda(){
        return txtBusqueda.getText();
    }
    
    public void setNombre(String Nombre){
        txtNombre.setText(Nombre);
    }
    public String getNombre(){
        return txtNombre.getText();
    }
    
    public void setApellido(String Apellido){
        txtApellido.setText(Apellido);
    }
    public String getApellido(){
        return txtApellido.getText();
    }
    
    public void setDireccion(String direccion){
        txtDireccion.setText(direccion);
    }
    public String getDireccion(){
        return txtDireccion.getText();
    }
    
    public void setDni(int Dni){
        txtDni.setText(Integer.toString(Dni));
    }
    public int getDni(){
        return Integer.parseInt(txtDni.getText());
    }
    public void setTelefono(String telefono){
        txtTelefono.setText(telefono);
    }
    public String getTelefono(){
        return txtTelefono.getText(); 
    }
    
    public void setSueldo(int sueldo){
        txtSueldo.setText(Integer.toString(sueldo));
    }
    public int getSueldo(){
        return Integer.parseInt(txtSueldo.getText());
    }
    
    public void setCuil(String Cuil){
        
        txtCuil.setText(Cuil);
    }
   
    public String getContraseña(){
        return txtContraseña.getText();
    }
    
    public String getCuil(){
        return txtCuil.getText();
    }
    public void setLocalidadCP(String Localidad){
       txtLocalidadCP.setText(Localidad);
    }
    public String getLocalidadCP(){
        return txtLocalidadCP.getText();
    }
    
    public void setLocalidad(String Localidad){
        cbxLocalidad.getModel().setSelectedItem(Localidad);
        
    }
    public String getLocalidad(){
        return cbxLocalidad.getSelectedItem().toString();
    }
    
      public void setProvincia(String prov){
        cbxProvincia.getModel().setSelectedItem(prov);
        
    }
    
      public void BloquearDni(){
          txtDni.setEnabled(false);
          btnModificarEmpleado.setEnabled(true);
      }
    
   
    
    public void setAntiguedad(int Antiguedad){
        txtAntiguedad.setText(Integer.toString(Antiguedad));
    }
    public int getAntiguedad(){
        return Integer.parseInt(txtAntiguedad.getText());
    }

    
    public void setFingreso(String Fingreso) throws ParseException{
      
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date = formato.parse(Fingreso);
        txtFecha.setDate(date);
       
        
        
    }
    public String getFingreso(){
        
        Date date = txtFecha.getDate();
        
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        String f = fecha.toString();
       
        return f;
    }
    
    public void setFegreso(String Fegreso) throws ParseException{
        if(!Fegreso.equals("0000-00-00")){
            System.out.println(Fegreso);
           SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date = formato.parse(Fegreso);
        txtFechaSalida.setDate(date);  
        }
       
        
    }
    public String getFegreso(){
       String f="";
        try{
          Date date = txtFechaSalida.getDate();
       
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        f = fecha.toString();
       
        
       }catch(Exception e){
           f = "0000-00-00";
       }
        return f;
    }
    
    public String getRol(){
        return cbxRoles.getSelectedItem().toString();
    }
    public void setRol(String rol){
        cbxRoles.getModel().setSelectedItem(rol);
    }
    
    public void limpiarCajas(){
        txtAntiguedad.setText(null);
        txtApellido.setText(null);
        txtCuil.setText(null);
        txtDireccion.setText(null);
        txtDni.setText(null);
        txtLocalidadCP.setText(null);
        txtNombre.setText(null);
        txtSueldo.setText(null);
        txtTelefono.setText(null);
       
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
        TablaEmpleado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtSueldo = new javax.swing.JTextField();
        txtAntiguedad = new javax.swing.JTextField();
        btnAgregarEmpleado = new javax.swing.JButton();
        btnModificarEmpleado = new javax.swing.JButton();
        btnDarBajaEmpleado = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnGenerarRecibo = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtLocalidadCP = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCuil = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        txtFechaSalida = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        cbxRoles = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbxLocalidad = new javax.swing.JComboBox<>();
        cbxProvincia = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txtFecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TablaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Dni", "Nombre", "Apellido", "Telefono", "Direccion", "Fecha De Ingreso", "Fecha Salida", "Cuil", "Sueldo", "Antiguedad"
            }
        ));
        TablaEmpleado.setColumnSelectionAllowed(true);
        TablaEmpleado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(TablaEmpleado);
        TablaEmpleado.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setText("Empleados");

        jLabel2.setText("Gestionar Empleados");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Dni");

        jLabel6.setText("Telefono");

        jLabel7.setText("Direccion");

        jLabel8.setText("Cuil");

        jLabel9.setText("Sueldo");

        jLabel10.setText("Antiguedad");

        jLabel11.setText("Fecha de Ingreso");

        jLabel12.setText("Fecha de Salida");

        jLabel13.setText("Datos Personales");

        jLabel14.setText("Datos Laborales");

        txtAntiguedad.setText("0");
        txtAntiguedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAntiguedadActionPerformed(evt);
            }
        });

        btnAgregarEmpleado.setText("Agregar Empleado");

        btnModificarEmpleado.setText("Modificar Empleado");

        btnDarBajaEmpleado.setText("Dar de Baja Empleado");

        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnGenerarRecibo.setText("Generar Recibo de sueldo");

        jLabel15.setText("Codigo Postal");

        btnBuscar.setText("Buscar");

        jLabel16.setText("Dni/Apellido");

        jLabel18.setText("Localidad");

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        txtLocalidadCP.setEnabled(false);
        txtLocalidadCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalidadCPActionPerformed(evt);
            }
        });
        txtLocalidadCP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLocalidadCPKeyTyped(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel21.setText("Contraseña nueva ");

        jLabel22.setText("Rol");

        jLabel17.setText("Provincia");

        cbxLocalidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLocalidadItemStateChanged(evt);
            }
        });
        cbxLocalidad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxLocalidadPropertyChange(evt);
            }
        });

        cbxProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaItemStateChanged(evt);
            }
        });
        cbxProvincia.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxProvinciaPropertyChange(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(41, 41, 41)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnBuscar)
                        .addGap(56, 56, 56)
                        .addComponent(btnGenerarRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235))))
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(txtCuil))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel17))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel5))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDni)
                                    .addComponent(cbxLocalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(33, 33, 33)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLocalidadCP, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtSueldo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                                .addComponent(txtContraseña))
                                            .addComponent(btnAgregarEmpleado))
                                        .addGap(52, 52, 52)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(btnDarBajaEmpleado)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(jLabel11)))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(29, 29, 29)
                                                                .addComponent(jLabel12)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(74, 74, 74)
                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                            .addComponent(btnModificarEmpleado))))
                                .addGap(0, 153, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel16)
                    .addComponent(btnGenerarRecibo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel18)
                    .addComponent(jLabel15)
                    .addComponent(txtLocalidadCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cbxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addComponent(txtFechaSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cbxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarEmpleado)
                    .addComponent(btnModificarEmpleado)
                    .addComponent(btnDarBajaEmpleado)
                    .addComponent(jButton5)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtAntiguedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAntiguedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAntiguedadActionPerformed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        char Validar = evt.getKeyChar();
        if(Character.isLetter(Validar)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
         char Validar = evt.getKeyChar();
        if(Character.isLetter(Validar)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtLocalidadCPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalidadCPKeyTyped
        // TODO add your handling code here:
        char Validar = evt.getKeyChar();
        if(Character.isLetter(Validar)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtLocalidadCPKeyTyped

    private void txtLocalidadCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalidadCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalidadCPActionPerformed

    private void cbxProvinciaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxProvinciaPropertyChange
        
    }//GEN-LAST:event_cbxProvinciaPropertyChange

    private void cbxLocalidadPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxLocalidadPropertyChange
    
    }//GEN-LAST:event_cbxLocalidadPropertyChange

    private void cbxProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaItemStateChanged
        
        Collections.sort(localidades, new Comparator<Localidad>() {
            public int compare(Localidad obj1, Localidad obj2) {
                return obj1.getNombre().compareTo(obj2.getNombre());
            }
        });
        
        cbxLocalidad.removeAllItems();
        for(Localidad l : localidades){
        if(l.getProvincia().equalsIgnoreCase(cbxProvincia.getSelectedItem().toString())){
            cbxLocalidad.addItem(l.getNombre());
        }
        }// TODO add your handling code here:
    }//GEN-LAST:event_cbxProvinciaItemStateChanged

    private void cbxLocalidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLocalidadItemStateChanged
       try{
          for(Localidad l : localidades){
        if(l.getNombre().equals(cbxLocalidad.getSelectedItem().toString())){
            txtLocalidadCP.setText(""+l.getCodigopostal());
        }
        }  
       }catch(Exception e){
           
       }
              // TODO add your handling code here:
    }//GEN-LAST:event_cbxLocalidadItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
txtDni.setEnabled(true);   
btnModificarEmpleado.setEnabled(false);
this.limpiarCajas();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaEmpleado;
    private javax.swing.JButton btnAgregarEmpleado;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDarBajaEmpleado;
    private javax.swing.JButton btnGenerarRecibo;
    private javax.swing.JButton btnModificarEmpleado;
    private javax.swing.JComboBox<String> cbxLocalidad;
    private javax.swing.JComboBox<String> cbxProvincia;
    private javax.swing.JComboBox<String> cbxRoles;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField txtAntiguedad;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtCuil;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private com.toedter.calendar.JDateChooser txtFecha;
    private com.toedter.calendar.JDateChooser txtFechaSalida;
    private javax.swing.JTextField txtLocalidadCP;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.BoletaSueldo;
import modelo.Concepto;
import modelo.Empleado;
import modelo.Familiar;
import modelo.LineaBoleta;
import modelo.Localidad;
import modelo.Novedad;
import modelo.Rol;
import modelo.Usuario;
import org.eclipse.persistence.internal.libraries.antlr.runtime.tree.DOTTreeGenerator;

/**
 *
 * @author FIume
 */
public class EmpleadoDAO {
    private Empleado empleado;
    private Conexion con;
    private Localidad localidad;
    private int idSeccion;

    
    public EmpleadoDAO(Empleado empleado,Conexion con){
        this.empleado=empleado;
        this.con=con;
    }
    
    public EmpleadoDAO(Conexion con){
       this.con=con;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

  
    
    
    
   
    public void AgregarEmpleado(){
        int idLocalidad=0;
        try{
           String sql = "select * from localidad where Nombre='"+empleado.getLocalidad().getNombre()+"'and codigopostal='"+empleado.getLocalidad().getCodigopostal()+"'";
           ResultSet fila = con.getConsulta().executeQuery(sql);
           while(fila.next()){
               idLocalidad = fila.getInt("idLocalidad");
           }
            
                sql = "INSERT INTO empleado SET DNI='"+empleado.getDni()+"', "
                    + "Nombre='"+empleado.getNombre()+ "', "
                    + "Apellido='"+ empleado.getApellido()+ "', "
                    + "Telefono='"+empleado.getTelefono()+"', "
                    + "Direccion='"+empleado.getDireccion()+"', "
                    + "Fecha_ingreso='"+empleado.getFecha_ingreso()+"', "
                    + "Fecha_salida='"+empleado.getFecha_salida()+"', "
                    + "CUIL='"+empleado.getCuil()+"', "
                    + "Sueldo='"+empleado.getSueldo()+"', "
                    + "Antiguedad='"+empleado.getAntiguedad()+"', "
                    + "Localidad_idLocalidad='"+idLocalidad+"'";
             
           
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Empleado agregado");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"1- Error al agregar datos a la tabla\n2-Asegurese de rellenar todos los campos\n3- Solo puede dejar vacia la fecha salida\n4-El formato de fecha debe ser Año-Mes-Dia");
        }
    }
    
     public ArrayList<Empleado> leer(){
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        
        try{
            String sql = "SELECT empleado.idEmpleado,empleado.DNI,empleado.Nombre,empleado.Apellido,empleado.Telefono,empleado.Direccion,empleado.Fecha_ingreso,empleado.Fecha_salida,empleado.Localidad_idLocalidad,empleado.Cuil,empleado.Sueldo,empleado.Antiguedad,localidad.Nombre,localidad.idLocalidad "
                    + "FROM empleado,localidad WHERE empleado.Localidad_idLocalidad=localidad.idLocalidad";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            while(fila.next()){
                Empleado tmp = new Empleado();
                tmp.setIdEmpleado(fila.getInt("idEmpleado"));
                tmp.setDni( fila.getInt("dni") );
                tmp.setNombre(fila.getString("nombre"));
                tmp.setApellido(fila.getString("apellido"));
                tmp.setTelefono(fila.getString("telefono"));
                tmp.setDireccion(fila.getString("Direccion"));
                tmp.setFecha_ingreso(fila.getString("Fecha_ingreso"));
                tmp.setFecha_salida(fila.getString("Fecha_salida"));
                tmp.localidad.setIdLocalidad(fila.getInt("Localidad_idLocalidad"));
                tmp.setCuil(fila.getString("Cuil"));
                tmp.setSueldo(fila.getInt("Sueldo"));
                tmp.setAntiguedad(fila.getInt("Antiguedad"));
                tmp.localidad.setNombre(fila.getString("Nombre"));
                
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla"+e);
        }        
        return lista;
    }
     
     
     public Usuario buscar(){
        try{
          
            String sql = "SELECT * FROM empleado as e,usuarios as u,rol as r WHERE e.dni=u.usuario and u.Rol_idRol = r.idRol and (e.dni='"+empleado.getDni()+"'"+ " or " + "e.Apellido='"+empleado.getApellido()+"')";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                Empleado tmp = new Empleado();
                tmp.setLocalidad(new Localidad());
                Usuario u = new Usuario();
                
                tmp.setDni( fila.getInt("dni") );
                tmp.setNombre(fila.getString("nombre"));
                tmp.setApellido(fila.getString("apellido"));
                tmp.setTelefono(fila.getString("telefono"));
                tmp.setDireccion(fila.getString("Direccion"));
                tmp.setFecha_ingreso(fila.getString("Fecha_ingreso"));
                tmp.setFecha_salida(fila.getString("Fecha_salida"));
                tmp.localidad.setIdLocalidad(fila.getInt("Localidad_idLocalidad"));
                tmp.setCuil(fila.getString("Cuil"));
                tmp.setSueldo(fila.getInt("Sueldo"));
                tmp.setAntiguedad(fila.getInt("Antiguedad"));
                tmp.setIdEmpleado(fila.getInt("idEmpleado"));
                u.setEmpleado(tmp);
                u.setUsuario(fila.getString("usuario"));
                u.setRol(new Rol(fila.getInt("idRol"),fila.getString("Descripcion")));
            
                
                return u;
            }
            
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla"+ e);
        }        
        return null;
    }
     
     public void modificar(String Filtro ){
        try{
            
            
            
            String sql = "UPDATE empleado SET "
                    + "DNI='"+empleado.getDni()+"',"
                    + "Nombre='"+empleado.getNombre()+ "',"
                    + "Apellido='"+ empleado.getApellido()+ "',"
                    + "Telefono='"+empleado.getTelefono()+"', "
                    + "Direccion='"+empleado.getDireccion()+"',"
                    + "Fecha_ingreso='"+empleado.getFecha_ingreso()+"',"
                    + "Fecha_salida='"+empleado.getFecha_salida()+"',"
                    + "CUIL='"+empleado.getCuil()+"',"
                    + "Sueldo='"+empleado.getSueldo()+"',"
                    + "Antiguedad='"+empleado.getAntiguedad()+"',"
                    + "Localidad_idLocalidad='"+empleado.getLocalidad().getIdLocalidad()+"'"
                    + " WHERE DNI='"+Filtro+"'"+" or "+"Apellido='"+Filtro+"'";
                    
            con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Empleado Modificado");
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }        
    }
     
     
     
    public void borrar(){
        try{
             String sql = "DELETE FROM usuarios WHERE usuario='"+empleado.getDni()+"'";
            con.getConsulta().execute(sql);
            sql = "DELETE FROM empleado WHERE dni='"+empleado.getDni()+"'";
            con.getConsulta().execute(sql);
            
            JOptionPane.showMessageDialog(null,"Empleado Eliminado");
        }
        catch(SQLException e){
            System.out.println("Error al borrar datos a la tabla");
           
        }        
    }
    
    public ArrayList<Rol> ObtenerRoles(){
        ArrayList<Rol> lista = new ArrayList<Rol>();
        try{
            String sql = "SELECT * FROM rol";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            while(fila.next()){
                Rol tmp = new Rol(fila.getInt("idRol"),fila.getString("Descripcion"));
                lista.add(tmp);
            }
            
            
        }
        catch(SQLException e){
            System.out.println("Error al leer Roles");
       
         
        }   
        return lista;
    }
    
    public int ObtenerIdEmpleado()
    {
        int id = 0;
        try{
            String sql = "SELECT * FROM empleado WHERE dni='"+empleado.getDni()+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                id = fila.getInt("idEmpleado");
            }
            
            
        }
        catch(SQLException e){
            System.out.println("Error al leer id empleado");
       
         
        }   
        return id;
    }
    
    public void AgregarFamiliar(Familiar f){
        try{
            String sql = "INSERT INTO grupo_familiar SET Empleado_idEmpleado ='"+f.getEmpleado().getIdEmpleado()
                    +"', nombre='"+f.getNombre()+"',"
                    +"apellido ='"+f.getApellido()+"',"
                    +"telefono='"+f.getTelefono()+"',"
                    +"direccion='"+f.getTelefono()+"', "
                    +"dni='"+f.getDni()+"', "
                    +"parentesco='"+f.getParentesco()+"'";
            con.getConsulta().execute(sql);
           JOptionPane.showMessageDialog(null,"Familiares agreados");
            
            
        }
        catch(SQLException e){
            System.out.println("Error al agregar familiar: " + e);
       
         
        }   
    }
    
    public ArrayList<Novedad> ObtenerNovedades(){
       
        ArrayList<Novedad> novedades = new ArrayList<>();
         try{
            String sql = "SELECT * FROM novedades WHERE empleado_idEmpleado='"+empleado.getIdEmpleado()+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
           while(fila.next()){
               Novedad n = new Novedad();
               n.setId(fila.getInt("idNovedades"));
               n.setAnio(fila.getInt("anio"));
               n.setMes(fila.getInt("Mes"));
               n.setValor(fila.getDouble("valor"));
               n.setDescripcion(fila.getString("Descripcion"));
              novedades.add(n);
            }
            
            
        }
        catch(SQLException e){
            System.out.println("Error al leer Novedades"+e);
       
        }   
        return novedades;
    }

    public void GuardarNovedad(Novedad novedad) {
        try {
            String sql = "INSERT INTO novedades SET Descripcion='"+novedad.getDescripcion()+"',  "
                    +"Mes='"+novedad.getMes()+"', "
                    + "Valor='"+novedad.getValor()+"',"
                    + "anio='"+novedad.getAnio()+"',"
                    + "Empleado_idEmpleado='"+empleado.getIdEmpleado()+"'";
             con.getConsulta().execute(sql);
            JOptionPane.showMessageDialog(null,"Novedad agregada");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<Concepto> LeerConceptos(){
        ArrayList<Concepto> conceptos = new ArrayList<>();
         try{
            String sql = "SELECT * FROM conceptos ";
            ResultSet fila = con.getConsulta().executeQuery(sql);
           while(fila.next()){
               Concepto n = new Concepto();
               n.setIdConcepto(fila.getInt("idConcepto"));
               n.setPorcentaje(fila.getDouble("porcentaje"));
               n.setTipo(fila.getString("tipo"));
               n.setDetalle(fila.getString("Detalle"));
              conceptos.add(n);
            }
            
            
        }
        catch(SQLException e){
            System.out.println("Error al leer Conceptos"+e);
       
        }   
        return conceptos;
    }

    public void GuardarBoletas(BoletaSueldo b) {
        
       
            try {
            String sql = "INSERT INTO boletas SET idBoleta='"+b.getIdBoleta()+"',  "
                     + "mes='"+b.getMes()+"',"
                    + "periodo='"+b.getPeriodo()+"',"
                    + "anio='"+b.getAnio()+"',"
                    + "bruto='"+b.getBruto()+"',"
                    + "Neto_cobrar='"+b.getNetoCobrar()+"',"
                    + "Empleado_idEmpleado='"+b.getEmpleado().getIdEmpleado()+"'";
             con.getConsulta().execute(sql);
             
             
                System.out.println("Boleta generada");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    

    public int ObtenerUltimoIdBoleta() {
      
          
           try{
            String sql = "SELECT MAX(idBoleta) FROM boletas";
            ResultSet fila = con.getConsulta().executeQuery(sql);
           if(fila.next()){
              return fila.getInt("MAX(idBoleta)");
            }
       
        }   catch (SQLException ex) {
               System.out.println(ex);
        }
          return 0;
    }

    public void GuardarDetalleBoleta(BoletaSueldo Boleta) {
      
        for (LineaBoleta lb : Boleta.getLineasBoleta()) {
            String auxSql = ""; 
            if(lb.getConcepto()!=null) auxSql = ", Concepto_idConcepto='"+lb.getConcepto().getIdConcepto()+"'";
            else  auxSql = ", Novedad_idNovedad='"+lb.getNovedad().getId()+"'";
             try {
            String sql = "INSERT INTO lineas_boleta SET detalle='"+lb.getDetalle()+"',  "
                     + "remuneracion='"+lb.getRemuneracion()+"',"
                    + "Boleta_idBoleta='"+Boleta.getIdBoleta()+"'"+auxSql;
                    
                    //+ "Concepto_idConcepto='"+lb.getConcepto().getIdConcepto()+"'"; //No lo relaciono porque puede ser novedad tambien y esto daria Null
                    
             con.getConsulta().execute(sql);
           
        } catch (Exception e) {
            System.out.println("error al agregar detalle de boleta"+e);
        }
        }
    }

    public ArrayList<BoletaSueldo> ObtenerTodasBoletas() {
      ArrayList<BoletaSueldo> lista = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM boletas as b,empleado as e WHERE b.Empleado_idEmpleado=e.idEmpleado";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            while(fila.next()){
                Empleado emp = new Empleado();
                emp.setDni(fila.getInt("dni"));
                emp.setApellido(fila.getString("Apellido"));
                emp.setNombre(fila.getString("Nombre"));
                
                BoletaSueldo tmp = new BoletaSueldo();
                tmp.setIdBoleta(fila.getInt("idBoleta"));
                tmp.setBruto(fila.getDouble("bruto") );
                tmp.setNetoCobrarAuxiliar(fila.getDouble("Neto_cobrar"));
                tmp.setMes(fila.getInt("mes"));
                tmp.setAnio(fila.getInt("anio"));
                
                tmp.setEmpleado(emp);
                //tmp.setLineasBoleta(this.ObtenerLineasBoleta(tmp.getIdBoleta()));
                
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla boletas"+e);
        }        
        return lista;
    }
    
    public ArrayList<LineaBoleta> ObtenerLineasBoleta(int idBoleta){
        ArrayList<LineaBoleta> lista = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM lineas_boleta  WHERE Boleta_idBoleta='"+idBoleta+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            while(fila.next()){
                
                LineaBoleta tmp = new LineaBoleta();
                tmp.setIdDetalle(fila.getInt("idDetalle"));
                tmp.setDetalle(fila.getString("Detalle") );
                tmp.setRemuneracion(fila.getDouble("Remuneracion"));
               
                
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla detalle boletas"+e);
        }        
        return lista;
    }

    public boolean VerificarLiquidacionEnPeriodo(int mes, int anio) {
        try{
            String sql = "SELECT * FROM boletas WHERE anio='"+anio+"'  and mes='"+mes+"' limit 1";
            ResultSet fila = con.getConsulta().executeQuery(sql);
            if(fila.next()){
                return false;
            }
        }
        catch(SQLException e){
            System.out.println("Error al Verificar las boletas"+e);
        }        
        return true;
    }

    public ArrayList<Novedad> ObtenerNovedades(int mes,int anio) {
       ArrayList<Novedad> novedades = new ArrayList<>();
         try{
            String sql = "SELECT * FROM novedades as n,empleado as e WHERE n.empleado_idEmpleado=e.idEmpleado and mes='"+mes+"' and anio='"+anio+"'";
            ResultSet fila = con.getConsulta().executeQuery(sql);
           while(fila.next()){
               Novedad n = new Novedad();
               Empleado e = new Empleado();
               e.setIdEmpleado(fila.getInt("idEmpleado"));
               e.setDni(fila.getInt("DNI"));
               
               n.setEmpleado(empleado);
               n.setId(fila.getInt("idNovedades"));
               n.setAnio(fila.getInt("anio"));
               n.setMes(fila.getInt("Mes"));
               n.setValor(fila.getDouble("valor"));
               n.setDescripcion(fila.getString("Descripcion"));
              novedades.add(n);
            }
            
            
        }
        catch(SQLException e){
            System.out.println("Error al leer Novedades"+e);
       
        }   
        return novedades;
    }
    
    
}

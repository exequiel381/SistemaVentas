
package modelo;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    private String basededatos="mydb2";
    private String servidor="localhost:3306";
    private String zona = "?useTimezone=true&serverTimezone=UTC";
    private String URL="jdbc:mysql://"+servidor+"/"+basededatos+zona;
    private String USER="root";
    private String PASS="bgnyvxpg";
 
    private Statement consulta;
    private Connection con;
    
    public int conectar(){
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL,USER,PASS); 
            consulta = con.createStatement();
            return 1;
                    
        // } catch (ClassNotFoundException ex) {
          //  System.out.println("error en driver");
            //return -1;
        } catch (SQLException ex) {
            System.out.println("error en base de datos");
            return-2;
        }
        
    }

    public String getBasededatos() {
        return basededatos;
    }

    public void setBasededatos(String basededatos) {
        this.basededatos = basededatos;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public Statement getConsulta() {
        return consulta;
    }

    public void setConsulta(Statement consulta) {
        this.consulta = consulta;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
}

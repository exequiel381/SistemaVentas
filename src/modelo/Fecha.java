/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Calendar;

public class Fecha {
    
    private int dia;
    private int mes;
    private int año; 
    
    public Fecha(int dia,int mes,int año){
        this.dia=dia;
        this.mes=mes;
        this.año=año;
    }
    public Fecha(){
        
        this.dia=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        this.mes=Calendar.getInstance().get(Calendar.MONTH)+1;
        this.año=Calendar.getInstance().get(Calendar.YEAR);
        
    }
    
    @Override

 public String toString() {
        return año+"-"+mes+"-"+dia;
    }
    
}

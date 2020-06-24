/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class Insumo extends Articulo {
    
    
    public Insumo(String idProd,String Codigo,String Descripcion,int Precio,String tipo,int StockSeguridad,String Tamanio,int deposito,String Color){
        super(Codigo,Descripcion,Precio,tipo,StockSeguridad,Tamanio);
    }
}


package modelo;

public class Proveedor{
    
    private String Razoc; 
    private String Cuit;
    private String Direccion;
    private String Telefono;
    private String Localidad;
    
    public Proveedor(String Razoc,String Cuit,String Direccion,String Telefono,String Localidad){
        
        this.Cuit=Cuit;
        this.Direccion=Direccion;
        this.Localidad=Localidad;
        this.Razoc=Razoc;
        this.Telefono=Telefono;
    }
    public Proveedor(String Razoc,String Cuit,String Direccion,String Telefono){
        
        this.Cuit=Cuit;
        this.Direccion=Direccion;
        this.Razoc=Razoc;
        this.Telefono=Telefono;
    }
    public Proveedor(){
        
       
    }

    public String getRazoc() {
        return Razoc;
    }

    public void setRazoc(String Razoc) {
        this.Razoc = Razoc;
    }

    public String getCuit() {
        return Cuit;
    }

    public void setCuit(String Cuit) {
        this.Cuit = Cuit;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String Localidad) {
        this.Localidad = Localidad;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "Razoc=" + Razoc + ", Cuit=" + Cuit + ", Direccion=" + Direccion + ", Telefono=" + Telefono + ", Localidad=" + Localidad + '}';
    }
    
}


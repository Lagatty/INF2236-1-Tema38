package com.mycompany.tema38;
import java.util.ArrayList;

public class Cliente {
    
    
    private String rut;
    private String nombre;
    private String apellido;
    private ArrayList<Arrendamiento> historial;
    
    // Constructor
    public Cliente(){
        this.historial = new ArrayList<>();
    }
    public String getRut() {
        return rut;
    }
    
    public String limpiarRut(String rut) throws RutNoValidoException{
        // Se limpia el String
        String rutLimpio = rut.replace(".", "").replace(" ", "");
        
        // Validar que contiene un guión
        if(!rutLimpio.contains("-")){
            throw new RutNoValidoException("El RUT debe contener un guión para ser válido");
        }
        return rutLimpio;
    }
    
    public void setRut(String rut) throws RutNoValidoException{
        
        String rutLimpio = limpiarRut(rut);
        // Separar la parte del digito verificador
        
        String[] partes = rutLimpio.split("-");
        
        try {
            // Convierte la parte numerica en un entero
            int numeroRut = Integer.parseInt(partes[0]);
            
            // Se valida si esta en el rango solicitado
            if(numeroRut < 6000000 || numeroRut > 28000000){
                throw new RutNoValidoException("El número de RUT no existe.");
            }
            
            this.rut = rutLimpio;
        } catch (NumberFormatException e){
            throw new RutNoValidoException("La parte númerica del RUT contiene caracteres no válidos");
        }
    }
    public String getNombre() {
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public String getApellido() { 
        return apellido; 
    }
    public void setApellido(String apellido) { 
        this.apellido = apellido; 
    }
    public ArrayList<Arrendamiento> getHistorial() { 
        return historial; 
    }
}

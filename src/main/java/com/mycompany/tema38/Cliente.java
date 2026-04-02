
package com.mycompany.tema38;
import java.util.ArrayList;

public class Cliente {
    
    
    private String rut;
    private String nombre;
    private String apellido;
    private ArrayList<Arrendamiento> historial;
    
   
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    
    
}

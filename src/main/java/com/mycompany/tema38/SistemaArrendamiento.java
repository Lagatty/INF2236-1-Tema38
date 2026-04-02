
package com.mycompany.tema38;
import java.util.Map;
import java.util.HashMap;

//Clase controladora

public class SistemaArrendamiento {
    private  Map<String, Sucursal> sedes;
    private  Map<String, Cliente> registroClientes;
    private String nombreArchivo;
    private static SistemaArrendamiento instance;
    
    //constructor privado para respetar patron Singleton
    private SistemaArrendamiento() {
        this.sedes =  new HashMap<>();
        this.registroClientes = registroClientes;
        
        cargarDatos(nombreArchivo);
    }
    
    
    //Persistencia de datos con archivos CSV
    
    public void cargarDatos(String nombreArchivo){
        
    }
    
    public void guardarDatos(){
        
    }
    
    
    //Patron singleton para obtener referencia de esta clase desde ventana principal y en general de otras clases
    
    public static SistemaArrendamiento getInstance() {
        if (instance == null) {
            instance = new SistemaArrendamiento();
        }
        return instance;
    }

    
    
    
    
    
}

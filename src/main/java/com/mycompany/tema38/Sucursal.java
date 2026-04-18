package com.mycompany.tema38;
import java.util.Map;
import java.util.HashMap;

public class Sucursal {
    
    private String nombre;
    private String direccion;
    private Map<String, Equipo> inventario;
    
    
    public Sucursal(String nombre, String direccion){
        this.nombre = nombre;
        this.direccion = direccion;
        this.inventario = new HashMap<>();
    }
    
    public void agregarEquipo(Equipo e) throws IdDuplicadoException {
        //Se utiliza la ID del equipo como clave del mapa
        if(e != null && e.getId() != null){ 
            // Verificamos si la clave (ID) ya está en el mapa
            if (inventario.containsKey(e.getId())) {
                throw new IdDuplicadoException("Error: Ya existe un equipo con el ID '" + e.getId() + "' en esta sucursal.");
            }
            inventario.put(e.getId(), e);
        }
    }
    
    public void eliminarEquipo(String id){
        inventario.remove(id);
    }
    
    public void listarInventario(){
        System.out.println("--- Inventario de Sede: " + nombre + " ---");
        if(inventario.isEmpty()){
            System.out.println("No hay equipos registrados");
        }else{
            for(Equipo e : inventario.values()){
                System.out.println("ID: " + e.id + " | Modelo: " + e.modelo + " | Disponible: " +e.disponible);
            }
        }
    }
    // Getters y Setters
    public String getNombre(){
        return nombre;
    }
    public String getDireccion(){
        return direccion;
    }
    
    public Map<String, Equipo> getInventario(){
        return inventario;
    }
}

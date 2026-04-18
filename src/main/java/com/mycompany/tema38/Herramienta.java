package com.mycompany.tema38;

public class Herramienta extends Equipo{
    private double costoLimpieza;
    
    public Herramienta(String id, String modelo, double precioBase){
        //Elementos heredados de la clase Equipo
        this.id = id;
        this.modelo = modelo;
        this.precioBase = precioBase;
        this.disponible = true; // Por defecto estara disponible
        
        this.costoLimpieza = 2500.0; // Costo de limpieza fijo
    }
    
    @Override
    public double calcularPrecioFinal(int dias){
        double total = (precioBase * dias) + costoLimpieza;
        return total;
    }
    
}

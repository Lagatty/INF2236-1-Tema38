package com.mycompany.tema38;

public abstract class Equipo {
    protected String id;
    protected String modelo;
    protected Double precioBase;
    protected boolean disponible;
    
    public Equipo(){}
    
    public abstract double calcularPrecioFinal(int dias);
}

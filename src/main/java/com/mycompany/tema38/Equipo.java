package com.mycompany.tema38;

import java.io.Serializable;

public abstract class Equipo implements Serializable{
    protected String id;
    protected String modelo;
    protected Double precioBase;
    protected boolean disponible;
    
    public Equipo(){}
    
    public abstract double calcularPrecioFinal(int dias);
    public String getId() { return id; }
    public String getModelo() { return modelo; }
    public double getPrecioBase() { return precioBase; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}

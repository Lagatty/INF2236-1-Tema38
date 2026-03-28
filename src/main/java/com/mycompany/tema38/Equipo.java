/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema38;

/**
 *
 * @author pa447
 */
public abstract class Equipo {
    protected String id;
    protected String modelo;
    protected Double precioBase;
    protected boolean disponible;
    
    public Equipo(){}
    
    public abstract double calcularPrecioFinal(int dias);
}

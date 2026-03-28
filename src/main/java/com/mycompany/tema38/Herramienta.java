/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema38;

/**
 *
 * @author pa447
 */
public class Herramienta extends Equipo{
    private int dias;
    
    public Herramienta(int dias){
        this.dias = dias;
        precioBase = 5.99;
    }
    
    @Override
    public double calcularPrecioFinal(int dias){
        return precioBase * dias;
    }
    
}

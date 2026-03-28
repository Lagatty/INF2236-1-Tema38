/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema38;

/**
 *
 * @author pa447
 */
public class Maquinaria extends Equipo{
    private int dias;
    private int horasMotor;
    
    public Maquinaria(int dias, int horasMotor){
        this.dias = dias;
        this.horasMotor = horasMotor;
    }
    
    @Override
    public double calcularPrecioFinal(int dias){
        return precioBase * horasMotor * dias;
    }
}

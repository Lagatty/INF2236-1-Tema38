package com.mycompany.tema38;

public class Maquinaria extends Equipo{
    private int horasMotor;
    private double tarifaPorHoraMotor;
    
    public Maquinaria(String id, String modelo, double precioBase, int horasMotor) throws HorasMotorException{
        //Atributos heredados de la clase equipo
        this.id = id;
        this.modelo = modelo;
        this.precioBase = precioBase;
        this.disponible = true;
        
        //Atributos de la subclase
        this.tarifaPorHoraMotor = 1500.0;
        this.setHorasMotor(horasMotor);
    }
    
    public void setHorasMotor(int horasMotor) throws HorasMotorException{
        // Validar que las horas del motor no sean negativas
        if(horasMotor < 0){
            throw new HorasMotorException("Error: Las horas del motor no pueden ser negativas.");
        }
        this.horasMotor = horasMotor;
    }
    
    public int getHorasMotor(){
        return horasMotor;
    }
    
    @Override
    public double calcularPrecioFinal(int dias){
        return (precioBase * dias) + (horasMotor * tarifaPorHoraMotor); 
    }    
}

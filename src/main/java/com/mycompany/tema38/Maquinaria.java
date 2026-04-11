package com.mycompany.tema38;

public class Maquinaria extends Equipo{
    private int horasMotor;
    
    public Maquinaria(String id, String modelo, double precioBase, int horasMotor) throws HorasMotorException{
        this.id = id;
        this.modelo = modelo;
        this.precioBase = precioBase;
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
        return (precioBase * dias) + (horasMotor * 150); 
    }
}

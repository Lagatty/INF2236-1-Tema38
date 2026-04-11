package com.mycompany.tema38;


public class Arrendamiento {
    
    private Cliente arrendatario;
    private Equipo recurso;
    private int diasDuracion;
    
    public Arrendamiento(){}
    
    // Costo estándar
    public double calcularCosto(){
        // Se llama al metodo abstracto de Equipo, comportandose diferente si es maquinaria o herramienta
        return recurso.calcularPrecioFinal(diasDuracion);
    }
    
    // Costo con descuento (sobrecarga)
    public double calcularCosto(double porcentajeDescuento){
        double costoBase = calcularCosto();
        return costoBase - (costoBase * (porcentajeDescuento / 100));
    }
    
    public Cliente getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(Cliente arrendatario) {
        this.arrendatario = arrendatario;
    }

    public Equipo getRecurso() {
        return recurso;
    }

    public void setRecurso(Equipo recurso) {
        this.recurso = recurso;
    }

    public int getDiasDuracion() {
        return diasDuracion;
    }

    public void setDiasDuracion(int diasDuracion) {
        this.diasDuracion = diasDuracion;
    }
}

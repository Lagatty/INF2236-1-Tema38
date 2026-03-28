
package com.mycompany.tema38;


public class Arrendamiento {
    
    private Cliente arrendatario;
    private Equipo recurso;
    private int diasDuracion;
    
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

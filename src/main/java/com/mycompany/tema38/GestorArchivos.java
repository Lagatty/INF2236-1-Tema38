package com.mycompany.tema38;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class GestorArchivos {    
    public GestorArchivos(){
        
    }
    public static void guardarDatos(Map<String, Sucursal> sedes, String nombreArchivo){
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))){
            writer.println("Sede,Tipo,ID,Modelo,PrecioBase,AtributoExtra");
            
            for(Sucursal sede : sedes.values()){
                
                for(Equipo e : sede.getInventario().values()){
                    
                    if(e instanceof Herramienta){
                        writer.println(sede.getNombre() + ",Herramienta," + e.id + "," + e.modelo + "," + e.precioBase + ",NA");
                        
                    } else if (e instanceof Maquinaria) {
                        Maquinaria m = (Maquinaria) e; // Aquí hacemos el "casteo" manual
                        writer.println(sede.getNombre() + ",Maquinaria," + m.getId() + "," + m.getModelo() + "," + m.getPrecioBase() + "," + m.getHorasMotor()); 
                    }
                }
            }
            System.out.println("Datos guardados exitosamente en " + nombreArchivo);
        }catch(IOException e){
            System.out.println("Error grave al intentar guardar el archivo" + e.getMessage());
        }
    }
    
    public static void cargarDatos(Map<String, Sucursal> sedes, String nombreArchivo) throws IdDuplicadoException{
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            boolean primeraLinea = true;

            while((linea = reader.readLine()) != null){
                // Saltamos la cabecera (Sede, Tipo, ID, etc.)
                if(primeraLinea){
                    primeraLinea = false;
                    continue;
                }

                // Separamos los datos por la coma
                String[] datos = linea.split(",");
                if (datos.length < 6) continue; // Seguridad: línea incompleta

                String nombreSede = datos[0];
                String tipo = datos[1];
                String id = datos[2];
                String modelo = datos[3];
                double precioBase = Double.parseDouble(datos[4]);
                String atributoExtra = datos[5];

                // 1. Obtener o crear la sucursal en el mapa
                Sucursal sede = sedes.get(nombreSede);
                if(sede == null){
                    sede = new Sucursal(nombreSede, "Dirección desconocida");
                    sedes.put(nombreSede, sede);
                }

                // 2. Re-instanciar el objeto según el tipo
                if(tipo.equalsIgnoreCase("Herramienta")){
                    Herramienta h = new Herramienta(id, modelo, precioBase);
                    sede.agregarEquipo(h);
                }else if(tipo.equalsIgnoreCase("Maquinaria")){
                    try{
                        // El AtributoExtra en maquinaria es el entero de horas de motor
                        int horas = Integer.parseInt(atributoExtra);
                        Maquinaria m = new Maquinaria(id, modelo, precioBase, horas);
                        sede.agregarEquipo(m);
                    }catch(HorasMotorException e) {
                        System.out.println("Error al cargar maquinaria " + id + ": " + e.getMessage());
                    }catch(IdDuplicadoException e) {
                        System.out.println("Advertencia: Se omitió el equipo " + id + " desde el CSV porque su ID está duplicado.");
                    }
                }
            }
            System.out.println("Datos cargados correctamente desde el CSV.");

        }catch(IOException e){
            System.out.println("No se encontró un archivo previo o hubo un error al leer: " + e.getMessage());
        }catch(NumberFormatException e){
            System.out.println("Error de formato numérico en el archivo: " + e.getMessage());
        }
    }
}

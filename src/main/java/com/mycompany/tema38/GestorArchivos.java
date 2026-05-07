package com.mycompany.tema38;

import java.io.File;
import java.io.*;


public class GestorArchivos {    
    public GestorArchivos(){
        
    }
    private static final String RUTA = "datos_sistema.dat";

    public static void guardarDatos(Object sistema) { // Quitamos el String del parámetro
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RUTA))) {
            out.writeObject(sistema);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public static Object cargarDatos() { // Quitamos el String del parámetro
        File archivo = new File(RUTA);
        if (!archivo.exists()) return null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return in.readObject();
        } catch (Exception e) {
            System.err.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
    }
    
    


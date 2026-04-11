package com.mycompany.tema38;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

//Clase controladora

public class SistemaArrendamiento {
    private  Map<String, Sucursal> sedes;
    private  Map<String, Cliente> registroClientes;
    
    private String nombreArchivo;
    private static SistemaArrendamiento instance;
    
    //constructor privado para respetar patron Singleton
    private SistemaArrendamiento() {
        this.sedes =  new HashMap<>();
        this.registroClientes = registroClientes;
        
        cargarDatos(nombreArchivo);
    }
    
    //Patron singleton para obtener referencia de esta clase desde ventana principal y en general de otras clases
    
    public static SistemaArrendamiento getInstance() {
        if (instance == null) {
            instance = new SistemaArrendamiento();
        }
        return instance;
    }
    //Persistencia de datos con archivos CSV en clase GestorArchivos
    
    public void guardarDatos(){
        System.out.println("Iniciando proceso de guardado...");
        // Le pasamos nuestro mapa de sedes a la clase experta en archivos
        GestorArchivos.guardarDatos(this.sedes);
    }
    
    // Gestion clientes
    
    public void registrarCliente(Cliente c){
        registroClientes.put(c.getRut(),c); //se utiliza el RUT como clave unica
    }
    
    // Sobrecarga
    
    // Buscar por RUT
    public Cliente buscarCliente(String rut){
        return registroClientes.get(rut);
    }
    // Buscar por nombre y apellido
    public Cliente buscarCliente(String nombre, String apellido){
        for(Cliente c : registroClientes.values()){
            if(c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)){
                return c;
            }
        }
        return null;
    }
    
    // Gestión sucursales
    
    public void agregarSucursal(Sucursal s){
        sedes.put(s.getNombre().toUpperCase(), s);
    }
    
    public void listarSedes(){
        if(sedes.isEmpty()){
            System.out.println("No hay sucursales registradas.");
        }else{
            for(String nombreSede : sedes.keySet()){
                System.out.println("- Sucursal: " + nombreSede);
            }
        }
    }
    
    public void agregarEquipoASucursal(String nombreSucursal, Equipo equipoNuevo) throws SucursalNoEncontradaException{
        // 1. Buscamos la sucursal en nuestro mapa principal usando el nombre como clave
        Sucursal sedeEncontrada = sedes.get(nombreSucursal);
        
        // 2. Validamos: Si es null, significa que la sucursal no existe
        if (sedeEncontrada == null) {
            throw new SucursalNoEncontradaException("Error: La sucursal '" + nombreSucursal + "' no existe en el sistema.");
        }
        
        // 3. Si la sucursal existe, usamos el método que creamos en la clase Sucursal
        sedeEncontrada.agregarEquipo(equipoNuevo);
    }
    
    public void iniciar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=== SISTEMA DE ARRENDAMIENTO ===");
        System.out.println("Seleccione la interfaz a usar: ");
        System.out.println("1. Consola.");
        System.out.println("2. Ventana.");
        
        int opcion = sc.nextInt();
        
        switch (opcion){
            
            case 1: {
                // Si se elije consola, se llama a la clase MenuConsola
                MenuConsola menu = new MenuConsola();
                menu.mostrarMenuPrincipal();
                break;
            }
            case 2: {
                // Si se elije ventana, se incializa el JFrame
                System.out.println("Incializando interfaz gráfica...");
                break;
            }
            default:
                System.out.println("Opción no válida, favor de ingresar 1 o 2.");
        }
    }
}

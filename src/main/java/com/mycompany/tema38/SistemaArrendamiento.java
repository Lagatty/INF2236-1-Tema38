package com.mycompany.tema38;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

//Clase controladora

public class SistemaArrendamiento {
    private  Map<String, Sucursal> sedes;
    private  Map<String, Cliente> registroClientes;

    
    private JFrame_Main ventanaPrincipal;
    private String nombreArchivo;
    private static SistemaArrendamiento instance;
    
    //constructor privado para respetar patron Singleton
    private SistemaArrendamiento() {
        this.sedes =  new HashMap<>();
        JFrame_Main ventanaPrincipal = new JFrame_Main();
        this.registroClientes = new HashMap<>();
    }
    
    //Patron singleton para obtener referencia de esta clase desde ventana principal y en general de otras clases
    
    public static SistemaArrendamiento getInstance() {
        if (instance == null) {
            instance = new SistemaArrendamiento();
        }
        return instance;
    }
    
    
    public JFrame_Main getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(JFrame_Main ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
    //Persistencia de datos con archivos CSV en clase GestorArchivos
    
    public void guardarDatos(String nombreArchivo){
        System.out.println("Iniciando proceso de guardado...");
        if (this.nombreArchivo != null) {
            GestorArchivos.guardarDatos(this.sedes, this.nombreArchivo); 
        }
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
    
    public String getListaClientes_ventana(){
        //string para guardar clientes
        String clientesLista = "Lista de Clientes\n";
        
        // 2. Recorres SOLO los valores del HashMap
        for (Cliente c : registroClientes.values()) {
               clientesLista.concat("\n-");
               clientesLista.concat(c.getNombre());
        }
        return clientesLista;
        }
        
    
    public void devolverEquipo(String nombreSede, String idEquipo) throws Exception {
        //Validaciones
        
        Sucursal s = sedes.get(nombreSede);
        if (s == null) throw new Exception("Error: La sucursal no existe.");
        
        Equipo e = s.getInventario().get(idEquipo);
        if (e == null) throw new Exception("Error: Equipo no encontrado.");
        
        if (e.isDisponible()) {
            throw new Exception("El equipo ya figura como devuelto/disponible en el sistema.");
        }
        
        //Cambiamos el estado a disponible
        e.setDisponible(true);
    }
    
    public void agregarEquipoASucursal(String nombreSucursal, Equipo equipoNuevo) throws SucursalNoEncontradaException, IdDuplicadoException{
        //Se busca la sucursal en nuestro mapa principal usando el nombre como clave
        Sucursal sedeEncontrada = sedes.get(nombreSucursal.toUpperCase());
        
        //Se valida: Si es null, significa que la sucursal no existe
        if (sedeEncontrada == null) {
            throw new SucursalNoEncontradaException("Error: La sucursal '" + nombreSucursal + "' no existe en el sistema.");
        }
        
        //Si la sucursal existe, se usa el método que creamos en la clase Sucursal
        sedeEncontrada.agregarEquipo(equipoNuevo);
    }
    
    public void listarEquiposDisponibles(String nombreSede) {
        Sucursal s = sedes.get(nombreSede);
        
        if (s == null) {
            System.out.println("Error: La sucursal '" + nombreSede + "' no existe.");
            return;
        }
        
        System.out.println("\n--- EQUIPOS DISPONIBLES EN " + nombreSede.toUpperCase() + " ---");
        boolean hayDisponibles = false;
        
        for (Equipo e : s.getInventario().values()) {
            if (e.isDisponible()) {
                System.out.println("ID: " + e.getId() + " | Modelo: " + e.getModelo() + " | Precio Base: $" + e.getPrecioBase());
                hayDisponibles = true;
            }
        }
        
        if (!hayDisponibles) {
            System.out.println("No hay equipos disponibles en esta sucursal en este momento.");
        }
    }
    
    // Metodo para elegir entre ventana o consola 
    
    public void iniciar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=== BIENVENIDO AL SISTEMA DE ARRENDAMIENTO ===");
        
        //Preguntamos el nombre del archivo ANTES de mostrar el menú
        System.out.print("Ingrese el nombre del archivo de base de datos (ej. inventario.csv): ");
        this.nombreArchivo = sc.nextLine();
        
        //Si el usuario presiona Enter sin escribir nada, se le da un nombre por defecto
        if (this.nombreArchivo.trim().isEmpty()) {
            this.nombreArchivo = "data.csv";
        }

        try {
            //Ahora sí se cargan los datos usando el nombre que nos dio el usuario
            GestorArchivos.cargarDatos(this.sedes, this.nombreArchivo);
        } catch (IdDuplicadoException ex) {
            Logger.getLogger(SistemaArrendamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int opcion;
        
        do{
            System.out.println("Interfaces disponibles: ");
            System.out.println("1. Consola.");
            System.out.println("2. Ventana.");
            System.out.println("0. Salir.");
            System.out.print("Seleccione la interfaz a usar: ");
        
            opcion = sc.nextInt();
            
            switch (opcion){
            
                case 1: {
                    //Si se elije consola, se llama a la clase MenuConsola
                    MenuConsola menu = new MenuConsola();
                    menu.mostrarMenuPrincipal();
                    break;
                }
                case 2: {
                    //Si se elije ventana, se muestra el Frame
                    System.out.println("Incializando interfaz gráfica...");
                    ventanaPrincipal = new JFrame_Main();
                    ventanaPrincipal.setVisible(true);              
                    break;
                }
                case 0:{
                    System.out.println("Saliendo del programa...");
                    break;
                }
                default:
                    System.out.println("Opción no válida, favor de ingresar 1, 2 o 0.");
                    break;
                }
        }while(opcion != 0);
    }
    
    // Gestion Arrendamiento
    
    public double registrarArriendo(String rutCliente, String nombreSede, String idEquipo, int dias) throws Exception {
        
        //Validaciones
        //Se valida que el cliente exista
        Cliente c = buscarCliente(rutCliente);
        if (c == null) {
            throw new Exception("Error: Cliente con RUT " + rutCliente + " no encontrado.");
        }
        
        //Se valida que la sucursal exista
        Sucursal s = sedes.get(nombreSede);
        if (s == null) {
            throw new Exception("Error: La sucursal '" + nombreSede + "' no existe.");
        }
        
        //Se valida que el equipo exista en esa sucursal
        Equipo e = s.getInventario().get(idEquipo);
        if (e == null) {
            throw new Exception("Error: El equipo ID '" + idEquipo + "' no se encuentra en esta sucursal.");
        }
        
        //Se valida que el equipo esté disponible 
        if (!e.isDisponible()) {
            throw new Exception("Error: El equipo ya se encuentra arrendado actualmente.");
        }
        
        //Se Crea el arriendo si pasa todas las validaciones
        Arrendamiento nuevoArriendo = new Arrendamiento();
        nuevoArriendo.setArrendatario(c);
        nuevoArriendo.setRecurso(e);
        nuevoArriendo.setDiasDuracion(dias);
        
        //Se cambia el estado del equipo a NO disponible
        e.setDisponible(false);
        
        //Se guarda el arriendo en el historial del cliente 
        c.getHistorial().add(nuevoArriendo);
        
        //Se retorna el costo total calculado con polimorfismo
        return nuevoArriendo.calcularCosto();
    }
    
    // --- FUNCIONALIDAD ESTRELLA: SUGERENCIAS ---
    
    public void generarSugerencia(String rutCliente, String nombreSede) {
        System.out.println("\n--- ANALIZANDO PERFIL DEL CLIENTE ---");
        
        // 1. Se busca al cliente y la sucursal
        Cliente c = buscarCliente(rutCliente);
        Sucursal s = sedes.get(nombreSede);
        
        //Si no encuentra al cliente se corta la ejecución
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return; 
        }
        
        //Si no encuentra la sucursal se corta la ejecución
        if (s == null) {
            System.out.println("Sucursal no encontrada.");
            return;
        }

        // 2. Se verifica si tiene historial
        if (c.getHistorial().isEmpty()) {
            System.out.println("El cliente " + c.getNombre() + " no tiene historial previo.");
            //Sugerencia: Ofrecer nuestra promoción del 20% para clientes nuevos
            return;
        }

        // 3. Contadores para perfilar al cliente
        int contadorHerramientas = 0;
        int contadorMaquinaria = 0;

        for (Arrendamiento arriendo : c.getHistorial()) {
            if (arriendo.getRecurso() instanceof Herramienta) {
                contadorHerramientas++;
            } else if (arriendo.getRecurso() instanceof Maquinaria) {
                contadorMaquinaria++;
            }
        }

        // 4. Se determina su categoría favorita
        String categoriaFavorita = (contadorHerramientas >= contadorMaquinaria) ? "Herramienta" : "Maquinaria";
        System.out.println("Cliente: " + c.getNombre() + " | Categoría favorita: " + categoriaFavorita);
        System.out.println("\n>> Equipos sugeridos disponibles en " + nombreSede + " <<");

        // 5. Se busca en el inventario de la sucursal equipos que coincidan
        boolean encontroSugerencias = false;
        
        for (Equipo e : s.getInventario().values()) {
            // Revisa si es de la categoría favorita y además está disponible
            if (e.isDisponible()) { 
                if (categoriaFavorita.equals("Herramienta") && e instanceof Herramienta) {
                    System.out.println("- " + e.getModelo() + " (ID: " + e.getId() + ") | Precio Base: $" + e.getPrecioBase());
                    encontroSugerencias = true;
                } 
                else if (categoriaFavorita.equals("Maquinaria") && e instanceof Maquinaria) {
                    System.out.println("- " + e.getModelo() + " (ID: " + e.getId() + ") | Precio Base: $" + e.getPrecioBase());
                    encontroSugerencias = true;
                }
            }
        }

        if (!encontroSugerencias) {
            System.out.println("Lo sentimos, no hay equipos de su categoría favorita disponibles en esta sucursal por el momento.");
        }
    }
}

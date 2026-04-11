package com.mycompany.tema38;
import java.util.Scanner;

public class MenuConsola {
    //Se muestran las mismas opciones que en las ventanas 
    private Scanner sc;
    private SistemaArrendamiento sistema = SistemaArrendamiento.getInstance();
    
    public MenuConsola(){
        this.sc = new Scanner(System.in);
    }
    
    public void mostrarMenuPrincipal(){
        boolean salir = false;
        
        while(!salir){
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Sucursales.");
            System.out.println("2. Gestionar Equipo.");
            System.out.println("3. Gestionar Clientes.");
            System.out.println("4. Salir.");
            System.out.println("Selecione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion){
                case 1: {
                    menuSucursales();
                    break;
                }
                case 2: {
                    menuEquipos();
                    break;
                }
                case 3: {
                    menuClientes();
                    break;
                }
                case 4: {
                    System.out.println("Saliendo del programa.");
                    salir = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    // SUBMENUS
    
    private void menuSucursales(){
        boolean volver = false;
        
        while(!volver){
            System.out.println("\n=== GESTION SUCURSALES ===");
            System.out.println("1. Agregar nueva sucursal.");
            System.out.println("2. Listar sucursales.");
            System.out.println("3. Salir.");
            System.out.println("Selecione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion){
                case 1: {
                    agregarSucursalUI();
                    break;
                }
                case 2: {
                    sistema.listarSedes();
                    break;
                }
                case 3: {
                    System.out.println("Volviendo.");
                    volver = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private void menuEquipos(){
        boolean volver = false;
        while(!volver){
            System.out.println("\n=== GESTION DE EQUIPOS ===");
            System.out.println("1. Agregar Herramienta.");
            System.out.println("2. Agregar Maquinaria.");
            System.out.println("3. Volver al menú principal.");
            System.out.println("Selecione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion){
                case 1: {
                    agregarHerramientaUI();
                    break;
                }
                case 2: {
                    agregarMaquinariaUI();
                    break;
                }
                case 3: {
                    System.out.println("Volviendo.");
                    volver = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private void agregarHerramientaUI(){
        System.out.println("\n-- Nueva Herramienta --");
        System.out.println("Ingrese el nombre de la sucursal en donde desea agregar la herramienta: ");
        String nombreSucursal = sc.nextLine();
        System.out.println("Ingrese ID del equipo: ");
        String id = sc.nextLine();
        System.out.println("Ingrese modelo: ");
        String modelo = sc.nextLine();
        System.out.println("Ingrese precio base por día: ");
        double precio = sc.nextDouble();
        sc.nextLine();
        
        Herramienta h = new Herramienta(id, modelo, precio);
        sistema.agregarEquipoASucursal(nombreSucursal, h);
        System.out.println("Herramienta agregada con éxito (en memoria temporal).");
    }
    
    private void agregarMaquinariaUI(){
        System.out.println("\n-- Nueva Maquinaria --");
        System.out.println("Ingrese el nombre de la sucursal en donde desea agregar la maquinaria: ");
        String nombreSucursal = sc.nextLine();
        System.out.println("Ingrese ID del equipo: ");
        String id = sc.nextLine();
        System.out.println("Ingrese modelo: ");
        String modelo = sc.nextLine();
        System.out.println("Ingrese precio base por día: ");
        double precio = sc.nextDouble();
        System.out.println("Ingrese horas de uso del motor");
        int horas = sc.nextInt();
        sc.nextLine();
        
        try {
            // Intentamos crear la maquinaria. Si las horas son negativas, saltará al catch.
            Maquinaria m = new Maquinaria(id, modelo, precio, horas);
            
            sistema.agregarEquipoASucursal(nombreSucursal, m);
            System.out.println("Maquinaria agregada con éxito (en memoria temporal).");
            
        } catch (HorasMotorException e) {
            System.out.println("Error al crear maquinaria: " + e.getMessage());
        }
    }
    
    private void menuClientes(){
        boolean volver = false;
        while(!volver){
            System.out.println("\n=== GESTION DE CLIENTES ===");
            System.out.println("1. Registrar nuevo cliente.");
            System.out.println("2. Buscar cliente (por RUT).");
            System.out.println("3. Buscar cliente (por nombre y apellido).");
            System.out.println("4. Volver al menú principal.");
            System.out.println("Selecione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion){
                case 1: {
                    registrarClienteUI();
                    break;
                }
                case 2: {
                    System.out.println("Buscando por RUT");
                    
                    System.out.println("Ingrese el RUT a buscar (EJ: 12.345.678-9):");
                    String rut = sc.nextLine();
                    
                    sistema.buscarCliente(rut);
                    break;
                }
                case 3: {
                    System.out.println("Buscando por nombre y apellido");
                    
                    System.out.println("Ingrese el nombre y apellido del cliente.");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    String apellido = sc.nextLine();
                    
                    sistema.buscarCliente(nombre, apellido);
                    break;
                }
                case 4: {
                    System.out.println("Volviendo.");
                    volver = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private void registrarClienteUI(){
        System.out.println("\n -- Nuevo Cliente --");
        Cliente nuevoCliente = new Cliente();
        
        System.out.println("Ingrese nombre: ");
        nuevoCliente.setNombre(sc.nextLine());
        
        System.out.println("Ingrese Apellido: ");
        nuevoCliente.setApellido(sc.nextLine());
        
        System.out.println("Ingrese RUT (EJ: 12.345.678-9: ");
        String rutIngresado = sc.nextLine();
        
        try{
            nuevoCliente.setRut(rutIngresado);
            
            sistema.registrarCliente(nuevoCliente);
            System.out.println("Cliente " + nuevoCliente.getNombre() + " " + nuevoCliente.getApellido() + " registrado con éxito.");
        }catch (RutNoValidoException e){
            System.out.println("Error de validación: " + e.getMessage());
            System.out.println("El cliente NO fue registrado. Intente nuevamente.");
        }
    }
    
    private void agregarSucursalUI(){
        System.out.println("Ingrese el nombre de la sucursal: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese la direccion: ");
        String direccion = sc.nextLine();
        
        Sucursal nuevaSucursal = new Sucursal(nombre, direccion);
        sistema.agregarSucursal(nuevaSucursal);
        
        System.out.println("Sucursal " + nombre + " agregada con éxito.");
    }
    
    private int leerOpcion(){
        try{
            int op = sc.nextInt();
            sc.nextInt(); // Limpiar buffer
            return op;
        }catch (Exception e){
            sc.nextLine(); // Se limpia la basura
            return -1;
        }
    }
}

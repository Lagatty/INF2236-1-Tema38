package com.mycompany.tema38;
import java.util.Scanner;

public class MenuConsola {
    //Se muestran las mismas opciones que en las ventanas 
    private Scanner sc;
    private SistemaArrendamiento sistema = SistemaArrendamiento.getInstance();
    
    public MenuConsola(){
        this.sc = new Scanner(System.in);
    }
    
    
    // MENÚ PRINCIPAL 
    public void mostrarMenuPrincipal(){
        
        boolean salir = false;
        while(!salir){
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Sucursales.");
            System.out.println("2. Gestionar Equipo.");
            System.out.println("3. Gestionar Clientes.");
            System.out.println("4. Registrar Nuevo Arriendo.");
            System.out.println("0. Salir.");
            System.out.print("Selecione una opción: ");
            
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
                    registrarArriendoUI();
                    break;
                }
                case 0: {
                    System.out.println("Saliendo del programa.");
                    salir = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    //  SUBMENUS

    // Submnú sucursales
    
    private void menuSucursales(){
        boolean volver = false;
        
        while(!volver){
            System.out.println("\n=== GESTION SUCURSALES ===");
            System.out.println("1. Agregar nueva sucursal.");
            System.out.println("2. Listar sucursales.");
            System.out.println("0. Salir.");
            System.out.print("Selecione una opción: ");
            
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
                case 0: {
                    System.out.println("Volviendo.");
                    volver = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    // SubMenú Equipos
    private void menuEquipos(){
        boolean volver = false;
        while(!volver){
            System.out.println("\n=== GESTION DE EQUIPOS ===");
            System.out.println("1. Agregar Herramienta.");
            System.out.println("2. Agregar Maquinaria.");
            System.out.println("3. Devolver Equipo.");
            System.out.println("4. Equipo Disponible.");
            System.out.println("0. Volver al menú principal.");
            System.out.print("Selecione una opción: ");
            
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
                    devolverEquipoUI();
                    break;
                }
                case 4: {
                    mostrarDisponiblesUI();
                    break; 
                }
                case 0: {
                    System.out.println("Volviendo.");
                    volver = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    //Submenú Clientes
    
    private void menuClientes(){
        boolean volver = false;
        while(!volver){
            System.out.println("\n=== GESTION DE CLIENTES ===");
            System.out.println("1. Registrar nuevo cliente.");
            System.out.println("2. Mostrar historial cliente.");
            System.out.println("3. Buscar cliente (por RUT).");
            System.out.println("4. Buscar cliente (por nombre y apellido).");
            System.out.println("0. Volver al menú principal.");
            System.out.print("Selecione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion){
                case 1: {
                    registrarClienteUI();
                    break;
                }
                case 2: {
                    System.out.println("MOSTRANDO HISTORIAL CLIENTE");
                    System.out.print("Ingrese el RUT a buscar (EJ: 12.345.678-9): ");
                    String rut = sc.nextLine();
                    String rutLimpio = limpiarRut(rut);
                    
                    Cliente buscar = sistema.buscarCliente(rutLimpio);
                    
                    if(buscar != null){
                        mostrarHistorial(buscar);
                    }else{
                        System.out.println("Cliente con el RUT " + rut + " no existe.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("BUSCANDO POR RUT");
                    
                    System.out.print("Ingrese el RUT a buscar (EJ: 12.345.678-9): ");
                    String rut = sc.nextLine();
                    String rutLimpio = limpiarRut(rut);
                    
                    Cliente buscar = sistema.buscarCliente(rutLimpio);
                    
                    if(buscar != null){
                        System.out.println("El cliente que busca es: \nNombre: " + 
                                buscar.getNombre() + ".\nApellido: " + 
                                buscar.getApellido() + ".\nRUT: " + buscar.getRut() + 
                                ".");
                    }else{
                        System.out.println("Cliente con el RUT " + rut + " no existe.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("BUSCANDO POR NOMBRE Y APELLIDO");
                    System.out.println("Ingrese el nombre y apellido del cliente.");
                    
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    
                    Cliente buscar = sistema.buscarCliente(nombre, apellido);
                    
                    if(buscar != null){
                        System.out.println("El cliente que busca es: \nNombre: " + 
                                buscar.getNombre() + ".\nApellido: " + 
                                buscar.getApellido() + ".\nRUT: " + buscar.getRut() + 
                                ".");
                    }else{
                        System.out.println("Cliente con el nombre y apellido dado no existe.");
                    }
                    
                    break;
                }
                case 0: {
                    System.out.println("Volviendo.");
                    volver = true;
                    break;
                }
                default: 
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    // Métodos auxiliares de cada menú
    
    // Métodos auxiliares de Equipo
    private void agregarHerramientaUI(){
        System.out.println("\n-- NUEVA HERRAMIENTA --");
        
        System.out.print("Ingrese el nombre de la sucursal en donde desea agregar la herramienta: ");
        String nombreSucursal = sc.nextLine();
        
        System.out.print("Ingrese ID del equipo: ");
        String id = sc.nextLine();
        
        System.out.print("Ingrese modelo: ");
        String modelo = sc.nextLine();
        
        System.out.print("Ingrese precio base por día: ");
        double precio = sc.nextDouble();
        sc.nextLine();
        
        Herramienta h = new Herramienta(id, modelo, precio);
        try {
            sistema.agregarEquipoASucursal(nombreSucursal.toUpperCase(), h);
            System.out.println("Herramienta agregada con éxito a la sucursal " + nombreSucursal + ".");
        }catch(SucursalNoEncontradaException e) {
            System.out.println(e.getMessage());
            System.out.println("La herramienta NO fue guardada.");
        }catch(IdDuplicadoException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void agregarMaquinariaUI(){
        System.out.println("\n-- NUEVA MAQUINARIA --");
        
        System.out.print("Ingrese el nombre de la sucursal en donde desea agregar la maquinaria: ");
        String nombreSucursal = sc.nextLine();
        
        System.out.print("Ingrese ID del equipo: ");
        String id = sc.nextLine();
        
        System.out.print("Ingrese modelo: ");
        String modelo = sc.nextLine();
        
        System.out.print("Ingrese precio base por día: ");
        double precio = sc.nextDouble();
        
        System.out.print("Ingrese horas de uso del motor: ");
        int horas = leerOpcion();
        
        try {
            
            Maquinaria m = new Maquinaria(id, modelo, precio, horas);
            sistema.agregarEquipoASucursal(nombreSucursal.toUpperCase(), m);
            
            System.out.println("Maquinaria agregada con éxito a la sucursal " + nombreSucursal + ".");
            
        }catch(HorasMotorException e){
            System.out.println("Error de motor: " + e.getMessage());
            
        }catch(SucursalNoEncontradaException e){
            System.out.println(e.getMessage());
            System.out.println("La maquinaria NO fue guardada.");
        }catch(IdDuplicadoException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void devolverEquipoUI() {
        System.out.println("\n--- DEVOLVER EQUIPO ---");
        
        System.out.print("Ingrese el nombre de la Sucursal: ");
        String sede = sc.nextLine();
        
        System.out.print("Ingrese el ID del equipo a devolver: ");
        String idEquipo = sc.nextLine();
        
        try {
            sistema.devolverEquipo(sede.toUpperCase(), idEquipo);
            System.out.println("El equipo " + idEquipo + " ha sido devuelto y está disponible nuevamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void mostrarDisponiblesUI() {
        System.out.println("\n--- VER EQUIPOS DISPONIBLES ---");
        System.out.print("Ingrese el nombre de la Sucursal a consultar: ");
        String sede = sc.nextLine();
        
        sistema.listarEquiposDisponibles(sede.toUpperCase());
    }
    
    // Metodos auxiliares de Clientes
    
    private void registrarClienteUI(){
        System.out.println("\n -- NUEVO CLIENTE --");
        Cliente nuevoCliente = new Cliente();
        
        System.out.print("Ingrese nombre: ");
        nuevoCliente.setNombre(sc.nextLine());
        
        System.out.print("Ingrese Apellido: ");
        nuevoCliente.setApellido(sc.nextLine());
        
        System.out.print("Ingrese RUT (EJ: 12.345.678-9): ");
        String rutIngresado = sc.nextLine();
        
        try{
            nuevoCliente.setRut(rutIngresado);
            sistema.registrarCliente(nuevoCliente);
            System.out.println("Cliente " + nuevoCliente.getNombre() + " " + nuevoCliente.getApellido() + " registrado con éxito.");
            
        }catch (RutNoValidoException e){ 
            //Salta la excepcion si no es válido el RUT
            System.out.println("Error de validación: " + e.getMessage());
            System.out.println("El cliente NO fue registrado. Intente nuevamente.");
        }
    }
    
    public String limpiarRut(String rut){
        // Se limpia el String
        String rutLimpio = rut.replace(".", "").replace(" ", "");
        return rutLimpio;
    }
    
    public void mostrarHistorial(Cliente buscar){
        System.out.println("Historial del cliente '" + buscar.getNombre() + " " + buscar.getApellido() + "'");
        if(buscar.getHistorial().isEmpty()){
            System.out.println("El cliente no tiene un historial de arrendamientos registrados.");
            return;
        }
        
        int cont = 1;
        for(Arrendamiento arriendo : buscar.getHistorial()){
            Equipo equipo = arriendo.getRecurso();
            
            System.out.println("Arriendo #" + cont + ":");
            System.out.println("    -ID del Equipo: " + equipo.getId());
            System.out.println("    -Modelo del Equipo: " + equipo.getModelo());
            System.out.println("    -Días de duración: " + arriendo.getDiasDuracion());
            
            System.out.println("    -Costo total: $" + arriendo.calcularCosto());
            System.out.println("================================================");
            cont++;
        }
    }
    
    // Metodos auxiliares de Sucursal
    
    private void agregarSucursalUI(){
        System.out.println("\n -- NUEVA SUCURSAL --");
        
        System.out.print("Ingrese el nombre de la sucursal: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese la direccion: ");
        String direccion = sc.nextLine();
        
        Sucursal nuevaSucursal = new Sucursal(nombre, direccion);
        sistema.agregarSucursal(nuevaSucursal);
        
        System.out.println("Sucursal " + nombre + " agregada con éxito.");
    }
    
    private void registrarArriendoUI(){
        System.out.println("\n--- REGISTRAR NUEVO ARRIENDO ---");
        
        System.out.print("Ingrese el RUT del cleinte (EJ: 12.345.678-9): ");
        String rut = sc.nextLine();
        String rutLimpio = limpiarRut(rut);
        
        System.out.print("Ingrese el nombre de la suscursal: ");
        String sede = sc.nextLine();
        
        //Se aplica la funcionalidad estrella, sugerencias inteligentes
        System.out.println("\nGenerando sugerencias inteligentes...");
        sistema.generarSugerencia(rut, sede);
        System.out.println("----------------------------------------");
        
        //Se muestra el inventario disponible
        System.out.println("\nO si prefiere este es el equipo disponible.");
        sistema.listarEquiposDisponibles(sede.toUpperCase());
        
        //Se continua con el arrendamiento
        System.out.print("\nIngrese el ID del equipo a arrendar: ");
        String idEquipo = sc.nextLine();
        
        System.out.print("Ingrese la cantidad de días de arriendo: ");
        int dias = leerOpcion();
        
        try{
            double costoTotal = sistema.registrarArriendo(rutLimpio, sede.toUpperCase(), idEquipo, dias);
            System.out.println("\n¡Arriendo registrado con éxito!");
            System.out.println("El costo total a pagar es: $" + costoTotal);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("La transacción ha sido cancelada.");
        }
    }
    
    // Método Auxiliar para que no se lea un dato vacio si llega a quedar en el buffer
    
    private int leerOpcion(){
        try{
            int op = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            return op;
        }catch (Exception e){
            sc.nextLine(); // Se limpia la basura
            return -1;
        }
    }
}

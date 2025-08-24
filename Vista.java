import java.util.Scanner;

/* Esta clase vista maneja toda la interacción con el usuario */
public class Vista {
    private Scanner scanner;
    
    public Vista() {
        this.scanner = new Scanner(System.in);
    }
    
    /*Este es el menú principal que le mostrare al usuario */
    public int mostrarMenuPrincipal() {
        System.out.println("\n *** SISTEMA DE GESTION DE SALONES ***");
        System.out.println("1. Realizar solicitud de reserva");
        System.out.println("2. Ver salones disponibles");
        System.out.println("3. Ver eventos realizados");
        System.out.println("4. Ver lista de espera");
        System.out.println("5. Liberar salon");
        System.out.println("6. Ver estadisticas mensuales");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
        
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /*Al solicitar la reserva se deben de tomar todos los datos para poder hacer la reserva */
    public SolicitudReserva solicitarDatosReserva() {
        try {
            System.out.println("\n*** NUEVA SOLICITUD DE RESERVA ***");
            
            System.out.print("Nombre del encargado: ");
            String encargado = scanner.nextLine();
            
            System.out.print("Nombre del evento: ");
            String nombreEvento = scanner.nextLine();
            
            TipoEvento tipoEvento = seleccionarTipoEvento();
            if (tipoEvento == null) return null;
            
            System.out.print("Fecha y hora (ej: 2025-12-25 14:30): ");
            String fechaHora = scanner.nextLine();
            
            System.out.print("Duracion en horas: ");
            int duracion = Integer.parseInt(scanner.nextLine());
            
            return new SolicitudReserva(encargado, nombreEvento, tipoEvento, fechaHora, duracion);
            
        } catch (NumberFormatException e) {
            System.out.println("Error en los datos ingresados");
            return null;
        }
    }
    
    /*Como su nombre lo dice, permite seleccionar el tipo del evento que necesite el usuario*/
    private TipoEvento seleccionarTipoEvento() {
        System.out.println("\nTipos de evento:");
        System.out.println("1. CONFERENCIA_INTERNACIONAL (VIP)");
        System.out.println("2. GALA (VIP)");
        System.out.println("3. REUNION");
        System.out.println("4. CAPACITACION");
        System.out.println("5. CELEBRACION");
        System.out.println("6. OTRO");
        
        System.out.print("Seleccione el tipo de evento: ");
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: return TipoEvento.CONFERENCIA_INTERNACIONAL;
                case 2: return TipoEvento.GALA;
                case 3: return TipoEvento.REUNION;
                case 4: return TipoEvento.CAPACITACION;
                case 5: return TipoEvento.CELEBRACION;
                case 6: return TipoEvento.OTRO;
                default: 
                    System.out.println("Opcion invalida");
                    return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Seleccion invalida");
            return null;
        }
    }
    
    public int solicitarNumeroSalon() {
        System.out.print("Ingrese el numero del salon a liberar: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Numero de salon invalido");
            return -1;
        }
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void mostrarSalones(Salon[] salones) {
        System.out.println("\n=== SALONES ===");
        for (int i = 0; i < 4; i++) { // Solo 4 salones inicializados
            if (salones[i] != null) {
                System.out.println((i + 1) + ". " + salones[i]);
            }
        }
    }
    
    public void mostrarEventos(Evento[] eventos, int contador) {
        System.out.println("\n=== EVENTOS REALIZADOS ===");
        if (contador == 0) {
            System.out.println("No hay eventos realizados");
        } else {
            for (int i = 0; i < contador; i++) {
                System.out.println((i + 1) + ". " + eventos[i]);
            }
        }
    }
    
    public void mostrarListaEspera(SolicitudReserva[] listaEspera, int contador) {
        System.out.println("\n=== LISTA DE ESPERA ===");
        if (contador == 0) {
            System.out.println("No hay solicitudes en espera");
        } else {
            for (int i = 0; i < contador; i++) {
                System.out.println((i + 1) + ". " + listaEspera[i]);
            }
        }
    }
    
    /*En caso de que el usuario ingrese datos invalidos, el programa no puede continuar hasta que le de enter. */
    public void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    
    public void cerrar() {
        scanner.close();
    }
}
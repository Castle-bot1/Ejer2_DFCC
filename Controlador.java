/*El controlador es el encargado de coordinar las interacciones entre el modelo y la vista*/
public class Controlador {
    private ModeloGestionSalones modelo;
    private Vista vista;
    
    public Controlador() {
        this.modelo = new ModeloGestionSalones();
        this.vista = new Vista();
    }
    /*Este es un método que ejecuta el sistema */
    public void ejecutarSistema() {
        vista.mostrarMensaje("Bienvenido al Sistema de Gestion de Salones");
        
        boolean continuar = true;
        /*se utiliza un do-while para asegurar que el menu se muestre al menos una vez */
        do {
            int opcion = vista.mostrarMenuPrincipal();
            
            switch (opcion) {
                case 1:
                    procesarSolicitudReserva();
                    break;
                case 2:
                    mostrarSalonesDisponibles();
                    break;
                case 3:
                    mostrarEventosRealizados();
                    break;
                case 4:
                    mostrarListaEspera();
                    break;
                case 5:
                    liberarSalon();
                    break;
                case 6:
                    mostrarEstadisticas();
                    break;
                case 0:
                    continuar = false;
                    vista.mostrarMensaje("Gracias por usar el sistema!");
                    break;
                default:
                    vista.mostrarMensaje("Opcion invalida. Intente nuevamente.");
                    break;
            }
            
            if (continuar && opcion != 0) {
                vista.pausar();
            }
            
        } while (continuar);
        
        vista.cerrar();
    }
    
    private void procesarSolicitudReserva() {
        SolicitudReserva solicitud = vista.solicitarDatosReserva();
        
        if (solicitud != null) {
            String resultado = modelo.procesarSolicitud(solicitud);
            vista.mostrarMensaje(resultado);
        } else {
            vista.mostrarMensaje("Error al procesar la solicitud");
        }
    }
    
    private void mostrarSalonesDisponibles() {
        vista.mostrarSalones(modelo.getSalones());
    }
    
    private void mostrarEventosRealizados() {
        vista.mostrarEventos(modelo.getEventosRealizados(), modelo.getContadorEventos());
    }
    
    private void mostrarListaEspera() {
        vista.mostrarListaEspera(modelo.getListaEspera(), modelo.getContadorEspera());
    }
    
    private void liberarSalon() {
        int numeroSalon = vista.solicitarNumeroSalon();
        
        if (numeroSalon > 0) {
            String resultado = modelo.liberarSalon(numeroSalon);
            vista.mostrarMensaje(resultado);
        }
    }
    
    private void mostrarEstadisticas() {
        String estadisticas = modelo.getEstadisticas();
        vista.mostrarMensaje("\nESTADISTICAS MENSUALES");
        vista.mostrarMensaje(estadisticas);
    }
}
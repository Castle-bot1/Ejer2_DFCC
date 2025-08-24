/*Este modelo sera en el encargado de llevar todo el control del sistema de reservas */
public class ModeloGestionSalones {
    private Salon[] salones;
    private Evento[] eventosRealizados;
    private SolicitudReserva[] listaEspera;
    private int contadorEventos;
    private int contadorEspera;
    private double ingresosMensuales;
    private int eventosMes;
    
    public ModeloGestionSalones() {
        this.salones = new Salon[10]; // Maximo 10 salones
        this.eventosRealizados = new Evento[100]; // Maximo 100 eventos
        this.listaEspera = new SolicitudReserva[50]; // Maximo 50 en espera
        this.contadorEventos = 0;
        this.contadorEspera = 0;
        this.ingresosMensuales = 0.0;
        this.eventosMes = 0;
        inicializarSalones();
    }
    
    /*Inicializo por lo menos 4 salones como lo pidió la guía */
    private void inicializarSalones() {
        salones[0] = new Salon(101, TipoSalon.PEQUENO, 50, 100.0);
        salones[1] = new Salon(102, TipoSalon.MEDIANO, 100, 200.0);
        salones[2] = new Salon(103, TipoSalon.GRANDE, 200, 400.0);
        salones[3] = new Salon(104, TipoSalon.MEDIANO, 120, 250.0);
    }
    

    public String procesarSolicitud(SolicitudReserva solicitud) {
        Salon salonAsignado = buscarSalonDisponible(solicitud.getTipoEvento());
        
        if (salonAsignado != null) {
            // Verificar reglas (al menos 3 reglas)
            if (cumpleReglas(solicitud)) {
                /* Crear evento */
                Evento evento = new Evento(solicitud.getEncargado(), 
                                         solicitud.getNombreEvento(),
                                         solicitud.getTipoEvento(), 
                                         solicitud.getFechaHora(),
                                         solicitud.getDuracionHoras());
                evento.setSalonAsignado(salonAsignado);
                salonAsignado.setDisponible(false);

                /* Agregar a eventos realizados */
                eventosRealizados[contadorEventos] = evento;
                contadorEventos++;
                
                solicitud.setProcesada(true);

                /* Actualizar estadisticas */
                ingresosMensuales += evento.getMontoTotal();
                eventosMes++;
                
                return "Reserva confirmada: " + evento.toString();
            } else {
                return "Solicitud rechazada: No cumple las reglas del centro";
            }
        } else {
            /* Agregar a lista de espera */
            if (contadorEspera < listaEspera.length) {
                listaEspera[contadorEspera] = solicitud;
                contadorEspera++;
                return "Salon no disponible. Agregado a lista de espera.";
            } else {
                return "Lista de espera llena";
            }
        }
    }
    
    private Salon buscarSalonDisponible(TipoEvento tipoEvento) {
        for (int i = 0; i < 4; i++) { 
            if (salones[i] != null && salones[i].isDisponible()) {
                /* Eventos VIP solo pueden usar salones grandes */
                if (tipoEvento.esVIP() && salones[i].getTipo() != TipoSalon.GRANDE) {
                    continue;
                }
                return salones[i];
            }
        }
        return null;
    }

    /* Al menos 3 reglas como pide la guía */
    private boolean cumpleReglas(SolicitudReserva solicitud) {
        /* Regla 1: Duracion minima 1 hora */
        if (solicitud.getDuracionHoras() < 1) {
            return false;
        }

        /* Regla 2: Nombre del evento no puede estar vacio */
        if (solicitud.getNombreEvento() == null || solicitud.getNombreEvento().trim().isEmpty()) {
            return false;
        }

        /* Regla 3: Encargado debe tener nombre */
        if (solicitud.getEncargado() == null || solicitud.getEncargado().trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    public String liberarSalon(int numeroSalon) {
        for (int i = 0; i < 4; i++) {
            if (salones[i] != null && salones[i].getNumero() == numeroSalon && !salones[i].isDisponible()) {
                salones[i].setDisponible(true);
                procesarListaEspera();
                return "Salon " + numeroSalon + " liberado correctamente";
            }
        }
        return "Salon no encontrado o ya disponible";
    }
    
    private void procesarListaEspera() {
        for (int i = 0; i < contadorEspera; i++) {
            if (listaEspera[i] != null && !listaEspera[i].isProcesada()) {
                String resultado = procesarSolicitud(listaEspera[i]);
                if (resultado.startsWith("Reserva confirmada")) {
                    // Remover de lista de espera
                    for (int j = i; j < contadorEspera - 1; j++) {
                        listaEspera[j] = listaEspera[j + 1];
                    }
                    listaEspera[contadorEspera - 1] = null;
                    contadorEspera--;
                    break;
                }
            }
        }
    }
    
    
    public Salon[] getSalones() {
        return salones;
    }
    
    public Evento[] getEventosRealizados() {
        return eventosRealizados;
    }
    
    public int getContadorEventos() {
        return contadorEventos;
    }
    
    public SolicitudReserva[] getListaEspera() {
        return listaEspera;
    }
    
    public int getContadorEspera() {
        return contadorEspera;
    }
    
    public double getIngresosMensuales() {
        return ingresosMensuales;
    }
    
    public int getEventosMes() {
        return eventosMes;
    }
    
    public String getEstadisticas() {
        return "Eventos realizados este mes: " + eventosMes + 
               " | Ingresos mensuales: $" + ingresosMensuales;
    }
}
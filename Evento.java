public class Evento {
    private String encargado;
    private String nombre;
    private TipoEvento tipoEvento;
    private String fechaHora; /* Lo simplifique como String, para que sea más fácil de manejar */
    private int duracionHoras;
    private Salon salonAsignado;
    private double montoTotal;
    
    public Evento(String encargado, String nombre, TipoEvento tipoEvento, 
                 String fechaHora, int duracionHoras) {
        this.encargado = encargado;
        this.nombre = nombre;
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.duracionHoras = duracionHoras;
    }
    
    public String getEncargado() {
        return encargado;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }
    
    public String getFechaHora() {
        return fechaHora;
    }
    
    public int getDuracionHoras() {
        return duracionHoras;
    }
    
    public Salon getSalonAsignado() {
        return salonAsignado;
    }
    
    public double getMontoTotal() {
        return montoTotal;
    }
    
    public void setSalonAsignado(Salon salon) {
        this.salonAsignado = salon;
        if (salon != null) {
            this.montoTotal = salon.getCostoPorHora() * duracionHoras;
        }
    }
    
    public String toString() {
        return "Evento: " + nombre + " | Encargado: " + encargado + 
               " | Tipo: " + tipoEvento + " | Fecha: " + fechaHora + 
               " | Duracion: " + duracionHoras + " horas | Monto: $" + montoTotal;
    }
}
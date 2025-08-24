public class SolicitudReserva {
    private String encargado;
    private String nombreEvento;
    private TipoEvento tipoEvento;
    private String fechaHora;
    private int duracionHoras;
    private boolean procesada;
    
    public SolicitudReserva(String encargado, String nombreEvento, TipoEvento tipoEvento,
                           String fechaHora, int duracionHoras) {
        this.encargado = encargado;
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.duracionHoras = duracionHoras;
        this.procesada = false;
    }
    
    public String getEncargado() {
        return encargado;
    }
    
    public String getNombreEvento() {
        return nombreEvento;
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
    
    public boolean isProcesada() {
        return procesada;
    }
    
    public void setProcesada(boolean procesada) {
        this.procesada = procesada;
    }
    
    public String toString() {
        return "Solicitud: " + nombreEvento + " | Encargado: " + encargado + 
               " | Tipo: " + tipoEvento + " | Fecha: " + fechaHora + 
               " | Duracion: " + duracionHoras + " horas";
    }
}
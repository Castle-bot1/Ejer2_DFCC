/*Tipos de eventos que se tienen permitidos */
public enum TipoEvento {
    CONFERENCIA_INTERNACIONAL, GALA, REUNION, CAPACITACION, CELEBRACION, OTRO;
    
    public boolean esVIP() {
        return this == CONFERENCIA_INTERNACIONAL || this == GALA;
    }
}

public class Salon {
    private int numero;
    private TipoSalon tipo;
    private int capacidadMaxima;
    private double costoPorHora;
    private boolean disponible;

    public Salon(int numero, TipoSalon tipo, int capacidadMaxima, double costoPorHora) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.costoPorHora = costoPorHora;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public TipoSalon getTipo() {
        return tipo;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getCostoPorHora() {
        return costoPorHora;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
        return String.format("Salon %d: (%s), Capacidad: %d, Costo por hora: %.2f, %s",
                numero, tipo, capacidadMaxima, costoPorHora, disponible ? "Disponible" : "Ocupado");
    }
}
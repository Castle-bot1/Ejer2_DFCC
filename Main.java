/** Clase principal que ejecuta todo el código e inicia el sistema*/
public class Main {
    public static void main(String[] args) {
        try {
            Controlador controlador = new Controlador();
            controlador.ejecutarSistema();
            
        } catch (Exception e) {
            System.out.println("Error en el sistema: " + e.getMessage());
        }
    }
}
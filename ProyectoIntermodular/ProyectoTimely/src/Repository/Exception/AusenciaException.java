package Repository.Exception;

public class AusenciaException extends Exception {
    String mensaje;
    public AusenciaException(String message) {
        this.mensaje = message;
    }
    public String imprimirMensaje() {
        return mensaje;
    }
}

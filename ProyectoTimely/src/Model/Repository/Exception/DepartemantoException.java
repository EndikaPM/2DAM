package Model.Repository.Exception;

public class DepartemantoException extends Exception {
    private String mensaje;
    public DepartemantoException() {}
    public DepartemantoException(String mensaje) {
        this.mensaje = mensaje;
    }
    public String imprimirMensaje() {
        return mensaje;
    }
}

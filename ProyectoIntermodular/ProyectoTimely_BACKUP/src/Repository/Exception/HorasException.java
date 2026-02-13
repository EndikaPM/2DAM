package Repository.Exception;

public class HorasException extends RuntimeException {
    public HorasException(String message) {
        super(message);
    }
    public HorasException() {}
    private String mensaje;
    public String imprimirMensaje() {
        return mensaje;
    }
}

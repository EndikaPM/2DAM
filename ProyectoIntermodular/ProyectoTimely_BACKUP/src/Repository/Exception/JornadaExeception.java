package Repository.Exception;

public class JornadaExeception extends RuntimeException {
    public JornadaExeception(String message) {
        super(message);
    }
    public JornadaExeception() {}
    private String mensaje;
    public String imprimirMensaje() {
        return mensaje;
    }
}

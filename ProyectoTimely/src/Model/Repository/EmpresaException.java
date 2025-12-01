package Model.Repository;

public class EmpresaException extends RuntimeException {
    public EmpresaException(String message) {
        super(message);
    }
    private String mensaje;
    public EmpresaException() {}
    public String imprimirMensaje() {
        return mensaje;
    }
}

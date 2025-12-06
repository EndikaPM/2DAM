package Model.Repository.Exception;

public class EmpresaException extends RuntimeException {
    public EmpresaException(String message) {
        super(message);
    }
    public EmpresaException() {}
    private String mensaje;
    public String imprimirMensaje() {
        return mensaje;
    }
}

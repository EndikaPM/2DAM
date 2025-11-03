package Model;

public class ExceptionUsuario extends Exception{
    private String mensaje;
    public ExceptionUsuario() {}
    public ExceptionUsuario(String mensaje) {
        this.mensaje = mensaje;
    }
    public String imprimirMensaje() {
        return mensaje;
    }
}

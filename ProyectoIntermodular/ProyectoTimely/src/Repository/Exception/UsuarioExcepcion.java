package Repository.Exception;

public class UsuarioExcepcion extends Exception {
    private String mensaje;
    public UsuarioExcepcion() {}
    public UsuarioExcepcion(String mensaje) {
        this.mensaje = mensaje;
    }
    public String imprimirMensaje() {
        return mensaje;
    }
}

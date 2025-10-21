package org.example.agendafmx.Model;

public class ExceptionPersona extends Exception {
    private String mensaje;
    public ExceptionPersona() {}
    public ExceptionPersona(String mensaje) {
        this.mensaje = mensaje;
    }
    public String imprimirMensaje() {
        return mensaje;
    }
}

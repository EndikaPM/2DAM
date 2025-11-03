package com.example.gestionhoteles.Model.Repositorio;

public class ExeptionReserva extends Exception {
    private String mensaje;
    public ExeptionReserva() {}
    public ExeptionReserva(String mensaje) {
        this.mensaje = mensaje;
    }
    public String imprimirMensaje() {
        return mensaje;
    }
}

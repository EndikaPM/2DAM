package com.example.ejemplo_monedas.model.repository;

public class ExcepcionMoneda extends Exception{
    private String mensaje;

    public ExcepcionMoneda() {
    }

    public ExcepcionMoneda(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}

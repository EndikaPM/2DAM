package com.example.tarea1tarea2.Model;

public class Libro {
    private int imagen;
    private String titulo;
    private String texto;
    private String fecha;
    private boolean dato1;
    public Libro(int idImagen, String textTitulo, String textoContenido,String fecha, boolean favorito) {
        this.imagen = idImagen;
        this.titulo = textTitulo;
        this.texto = textoContenido;
        this.fecha = fecha;
        this.dato1 = favorito;
    }

    public int getImagenId() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return texto;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean isDato1() {
        return dato1;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDato1(boolean dato1) {
        this.dato1 = dato1;
    }
}

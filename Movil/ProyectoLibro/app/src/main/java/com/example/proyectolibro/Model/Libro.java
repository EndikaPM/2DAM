package com.example.proyectolibro.Model;

import java.io.Serializable;

public class Libro implements Serializable {
    private int id;
    private int imagen;
    private String titulo;
    private String texto;
    private String fecha;
    private boolean dato1;

    public Libro(int idImagen, String textoTitulo, String textoContenido, String fecha, boolean favorito){
        this.imagen = idImagen;
        this.titulo = textoTitulo;
        this.texto = textoContenido;
        this.fecha = fecha;
        this.dato1 = favorito;
    }

    public int getImagen() {
        return imagen;
    }
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public boolean isDato1() {
        return dato1;
    }
    public void setDato1(boolean dato1) {
        this.dato1 = dato1;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

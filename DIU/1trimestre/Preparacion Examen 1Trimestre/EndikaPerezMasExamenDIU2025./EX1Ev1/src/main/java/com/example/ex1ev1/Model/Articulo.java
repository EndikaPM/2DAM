package com.example.ex1ev1.Model;

import javafx.beans.property.*;

public class Articulo {
    private int id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;
    private final SimpleFloatProperty precio;
    private final SimpleIntegerProperty stock;

    public Articulo(){
        this.id = 0;
        this.nombre = new SimpleStringProperty("");
        this.descripcion = new SimpleStringProperty("");
        this.precio = new SimpleFloatProperty(0);
        this.stock = new SimpleIntegerProperty(0);
    }

    public Articulo(String nombre, float precio, int stock){
        this.id = 0;
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty("Descripcion por defecto");
        this.precio = new SimpleFloatProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {this.stock.set(stock);}

    public IntegerProperty setStockProperty() {
        return stock;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty setDescripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {this.descripcion.set(descripcion);}

    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty setPrecioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {this.nombre.set(nombre);}

    public StringProperty nombreProperty() {
        return nombre;
    }
}

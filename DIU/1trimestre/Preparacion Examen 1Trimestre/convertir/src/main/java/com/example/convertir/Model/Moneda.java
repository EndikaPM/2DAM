package com.example.convertir.Model;

import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.beans.property.*;

//UPDATE monedas SET multiplicador = 0.87 WHERE codigo = 2;
public class Moneda {

    SimpleStringProperty nombre;
    SimpleFloatProperty multiplicador;
    SimpleIntegerProperty idMoneda;

    public Moneda(String nombre, float multiplicador) {
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
    }


    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty getNombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public float getMultiplicador() {
        return multiplicador.get();
    }

    public SimpleFloatProperty getMultiplicadorProperty() {
        return multiplicador;
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador.set(multiplicador);
    }

    public int getIdMoneda() {
        return idMoneda.get();
    }

    public SimpleIntegerProperty getIdMonedaProperty() {
        return idMoneda;
    }

    public void setIdMoneda(int idMoneda) {
        this.idMoneda.set(idMoneda);
    }
}

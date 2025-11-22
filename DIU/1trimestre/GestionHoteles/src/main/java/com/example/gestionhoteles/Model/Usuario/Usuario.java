package com.example.gestionhoteles.Model.Usuario;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Usuario {
    private final SimpleStringProperty dni;
    private final  SimpleStringProperty nombre;
    private final  SimpleStringProperty apellido;
    private final SimpleStringProperty direccion;
    private final  SimpleStringProperty localidad;
    private final  SimpleStringProperty provincia;
    private final  SimpleIntegerProperty codigoPostal;

    public Usuario() {
        this(null, null, null);
    }

    public Usuario(String dni, String nombre, String apellido, String direccion, String localidad, String provincio, int codigoPostal) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincio);
        this.codigoPostal = new SimpleIntegerProperty(codigoPostal);
    }

    public Usuario(String dni, String nombre, String apellido) {
       this.dni = new SimpleStringProperty(dni);
       this.nombre = new SimpleStringProperty(nombre);
       this.apellido = new SimpleStringProperty(apellido);

       // INICIALIZACIÓN DE EJEMEPLO
       this.direccion = new SimpleStringProperty("C/borrar");
       this.localidad = new SimpleStringProperty("Temoral");
       this.provincia = new SimpleStringProperty("Sevilla");
       this.codigoPostal = new SimpleIntegerProperty(41008);
    }

    public String getDni() {
        return dni.get();
    }

    public SimpleStringProperty getDNIProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public SimpleStringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public SimpleStringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getLocalidad() {
        return localidad.get();
    }

    public SimpleStringProperty localidadProperty() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public String getProvincia() {
        return provincia.get();
    }

    public SimpleStringProperty provinciaProperty() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }

    public int getCodigoPostal() {
        return codigoPostal.get();
    }

    public SimpleIntegerProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }
}

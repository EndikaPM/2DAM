package com.example.gestionhoteles.Model.Usuario;

public class UsuarioVO {

    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String localidad;
    private String provincia;
    private int codigoPostal;

    public UsuarioVO() {}
    public UsuarioVO(String dni,  String nombre, String apellido, String direccion, String localidad,
                     String provincia, int codigoPostal) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" +
                ", " + dni +
                ", " + nombre +
                ", " + apellido +
                ", " + direccion +
                ", " + localidad +
                ", " + provincia +
                ", " + codigoPostal +
                '}';
    }
}

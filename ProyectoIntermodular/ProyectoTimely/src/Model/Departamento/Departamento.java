package Model.Departamento;

import Model.Empresa.Empresa;

public class Departamento {

    private int id;
    private String nombre;
    private Empresa id_empresa;

    public Departamento(){}

    public Departamento(int id, String nombre, Empresa id_empresa) {
        this.id = id;
        this.nombre = nombre;
        this.id_empresa = id_empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Empresa getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Empresa id_empresa) {
        this.id_empresa = id_empresa;
    }

    @Override
    public String toString() {
        return "\n\t\tDepartamento{" +
                "\n\t\t\tid=" + id +
                "\n\t\t\tnombre='" + nombre + '\'' +
                "\n\t\t\tid_empresa=" + id_empresa +
                "\n\t}";
    }
}

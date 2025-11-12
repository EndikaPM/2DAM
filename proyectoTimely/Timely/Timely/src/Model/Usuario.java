package Model;

import java.time.LocalDate;

public class Usuario {
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private LocalDate fechaNacimiento;
    private LocalDate fechaContrato;
    private String ss;
    private String userType;
    private String departamento;

    public Usuario() {}

    public Usuario(String dni, String nombre, String apellido, String email, String password, LocalDate fechaNacimiento,
                   LocalDate fechaContrato, String ss, String userType, String departamento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContrato = fechaContrato;
        this.ss = ss;
        this.userType = userType;
        this.departamento = departamento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(LocalDate fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

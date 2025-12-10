package Model.Horas;

import Model.Usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

public class Jornada {
    private int id;
    private Usuario idTrabajador;
    private LocalDate fechaActual;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private boolean modificado;

    public Jornada(Usuario usuario, LocalDate fechaActual, LocalTime horaEntrada, LocalTime horaSalida) {
        this.idTrabajador = usuario;
        this.fechaActual = fechaActual;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public Jornada(Usuario usuario, LocalDate fechaActual, LocalTime horaEntrada) {
        this.idTrabajador = usuario;
        this.fechaActual = fechaActual;
        this.horaEntrada = horaEntrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Usuario idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
}

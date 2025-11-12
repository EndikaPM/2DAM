package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Jornada {
    private int idJornada;
    private String dniTrabaja;
    private LocalDate fechaActual;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean modificado;
    public Jornada() {
    }

    public Jornada(String dniTrabaja, boolean modificado, LocalDate fechaActual, LocalTime horaInicio, LocalTime horaFin) {
        this.dniTrabaja = dniTrabaja;
        this.modificado = modificado;
        this.fechaActual = fechaActual;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public String getDniTrabaja() {
        return dniTrabaja;
    }

    public void setDniTrabaja(String dniTrabaja) {
        this.dniTrabaja = dniTrabaja;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
}

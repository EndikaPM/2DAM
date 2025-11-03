package com.example.gestionhoteles.Model;

import java.time.LocalDate;

public class ReservaVO {

    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int numHabitaciones;
    private TipoHabitaciones tipoHAbitaciones;
    private boolean isFumador;
    private RegimenAlogamiento regimenAlogamiento;
    private String idUsuario;
    private int id;

    public ReservaVO(int id, String idUsuario, boolean isFumador, RegimenAlogamiento regimenAlogamiento, TipoHabitaciones tipoHAbitaciones, int numHabitaciones, LocalDate fechaSalida, LocalDate fechaEntrada) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.isFumador = isFumador;
        this.regimenAlogamiento = regimenAlogamiento;
        this.tipoHAbitaciones = tipoHAbitaciones;
        this.numHabitaciones = numHabitaciones;
        this.fechaSalida = fechaSalida;
        this.fechaEntrada = fechaEntrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public TipoHabitaciones getTipoHAbitaciones() {
        return tipoHAbitaciones;
    }

    public void setTipoHAbitaciones(TipoHabitaciones tipoHAbitaciones) {
        this.tipoHAbitaciones = tipoHAbitaciones;
    }

    public boolean getIsFumador() {
        return isFumador;
    }

    public void setFumador(boolean fumador) {
        isFumador = fumador;
    }

    public RegimenAlogamiento getRegimenAlogamiento() {
        return regimenAlogamiento;
    }

    public void setRegimenAlogamiento(RegimenAlogamiento regimenAlogamiento) {
        this.regimenAlogamiento = regimenAlogamiento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}

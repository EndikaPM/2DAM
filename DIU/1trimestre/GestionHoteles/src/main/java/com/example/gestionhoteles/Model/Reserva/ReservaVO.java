package com.example.gestionhoteles.Model.Reserva;

import com.example.gestionhoteles.Util.DateUtil;

import java.time.LocalDate;

public class ReservaVO {

    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int numHabitaciones;
    //private TipoHabitaciones tipoHAbitaciones;
    private String tipoHAbitaciones;
    private boolean isFumador;
    //private RegimenAlogamiento regimenAlogamiento;
    private String regimenAlogamiento;
    private String idUsuario;
    private int id;

    public ReservaVO() {}
    public ReservaVO( String idUsuario, boolean isFumador, String regimenAlogamiento, String tipoHAbitaciones, int numHabitaciones, String fechaSalida, String fechaEntrada) {
        this.idUsuario = idUsuario;
        this.isFumador = isFumador;
        this.regimenAlogamiento = regimenAlogamiento;
        this.tipoHAbitaciones = tipoHAbitaciones;
        this.numHabitaciones = numHabitaciones;
        this.fechaSalida = DateUtil.parse(fechaSalida);
        this.fechaEntrada = DateUtil.parse(fechaEntrada);
    }
    public String getTipoHAbitaciones() {
        return tipoHAbitaciones;
    }

    public String getRegimenAlogamiento() {
        return regimenAlogamiento;
    }

    public void setRegimenAlogamiento(String regimenAlogamiento) {
        this.regimenAlogamiento = regimenAlogamiento;
    }

    public void setTipoHAbitaciones(String tipoHAbitaciones) {
        this.tipoHAbitaciones = tipoHAbitaciones;
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

    /*public TipoHabitaciones getTipoHAbitaciones() {
        return tipoHAbitaciones;


    public void setTipoHAbitaciones(TipoHabitaciones tipoHAbitaciones) {
        this.tipoHAbitaciones = tipoHAbitaciones;
    }*/

    public boolean getIsFumador() {
        return isFumador;
    }

    public void setFumador(boolean fumador) {
        isFumador = fumador;
    }

    //public RegimenAlogamiento getRegimenAlogamiento() {
        //return regimenAlogamiento;
   // }

   // public void setRegimenAlogamiento(RegimenAlogamiento regimenAlogamiento) {
      //  this.regimenAlogamiento = regimenAlogamiento;
    //}

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "ReservaVO{" +
                ", " + fechaEntrada +
                ", " + fechaSalida +
                ", " + numHabitaciones +
                ", " + tipoHAbitaciones +
                ", " + isFumador +
                ", " + regimenAlogamiento +
                ", " + idUsuario +
                ", " + id +
                '}';
    }
}

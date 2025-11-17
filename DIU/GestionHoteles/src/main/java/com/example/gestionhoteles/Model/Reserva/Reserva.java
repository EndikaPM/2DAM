package com.example.gestionhoteles.Model.Reserva;

import com.example.gestionhoteles.Util.DateUtil;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {
    private final IntegerProperty id;
    private final ObjectProperty<LocalDate> fechaLlegada;
    private final ObjectProperty<LocalDate> fechaSalida;
    private final IntegerProperty numHabitaciones;
    private final StringProperty tipoHabitacion;
    private final BooleanProperty fumador;
    private final StringProperty regimenAlojamiento;
    private final StringProperty dni_cliente;

    /*public Reserva() {
        this(null, null, null, null, null,
                null, null, null);
    }*/
    public Reserva() {
        this.id = new SimpleIntegerProperty(0);
        this.fechaLlegada = new SimpleObjectProperty<>(null);
        this.fechaSalida = new SimpleObjectProperty<>(null);
        this.numHabitaciones = new SimpleIntegerProperty(0);
        this.tipoHabitacion = new SimpleStringProperty("");
        this.fumador = new SimpleBooleanProperty(false);
        this.regimenAlojamiento = new SimpleStringProperty("");
        this.dni_cliente = new SimpleStringProperty("");
    }
    public Reserva(Integer id, String fechaLlegada, String fechaSalida, Integer numHabitaciones, String tipoHabitacion,
                   Boolean fumador, String regimenAlojamiento, String dni_cliente) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaLlegada = new SimpleObjectProperty(fechaLlegada);
        this.fechaSalida = new SimpleObjectProperty(fechaSalida);
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitacion);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.regimenAlojamiento = new SimpleStringProperty(regimenAlojamiento);
        this.dni_cliente = new SimpleStringProperty(dni_cliente);
    }
    public int getId() {
        return id.get();
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) { this.id.set(id); }

    public LocalDate getFechaLlegada() {
        return fechaLlegada.get();
    }

    public ObjectProperty<LocalDate> getFechaLlegadaProperty() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public ObjectProperty<LocalDate> getFechaSalidaProperty() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida){ this.fechaSalida.set(fechaSalida); }

    public int getNumHabitaciones() {
        return numHabitaciones.get();
    }

    public IntegerProperty getNumHabitacionesProperty() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) { this.numHabitaciones.set(numHabitaciones); }

    public String getTipoHabitacion() {
        return tipoHabitacion.get();
    }

    public StringProperty getTipoHabitacionProperty() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) { this.tipoHabitacion.set(tipoHabitacion); }

    public boolean getIsFumador() {
        return fumador.get();
    }


    public BooleanProperty fumadorProperty() {
        return fumador;
    }
    /* public IntegerProperty getIsFumadorProperty() {
        return fumador;
    }*/

    public void setIsFumador(Boolean isFumador) { this.fumador.set(isFumador); }

    public String getRegimenAlojamiento() {
        return regimenAlojamiento.get();
    }

    public StringProperty getRegimenAlojamientoProperty() {
        return regimenAlojamiento;
    }
    public void setRegimenAlojamiento(String regimenAlojamiento) { this.regimenAlojamiento.set(regimenAlojamiento); }

    public String getDniCliente() {
        return dni_cliente.get();
    }

    public StringProperty getDniClienteProperty() {
        return dni_cliente;
    }

    public void setDniCliente(String dni_cliente) { this.dni_cliente.set(dni_cliente); }
}

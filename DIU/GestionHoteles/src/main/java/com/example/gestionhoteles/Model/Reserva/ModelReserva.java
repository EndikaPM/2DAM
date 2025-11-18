package com.example.gestionhoteles.Model.Reserva;

import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ModelReserva {
    private ReservaRepository reservaRepositorio;

    public ModelReserva(ReservaRepository reservaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
    }

    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExeptionReserva{
        return reservaRepositorio.ObtenerListaReservas();
    }

    public void addReserva(ReservaVO rVO) throws ExeptionReserva{
        reservaRepositorio.addReserva(rVO);
    }

    public void updateReserva(ReservaVO rVO) throws ExeptionReserva{
        reservaRepositorio.updateReserva(rVO);
    }
    public void deleteReserva(int id) throws ExeptionReserva{
        reservaRepositorio.deleteReserva(id);
    }

    public int lastId() throws ExeptionReserva{
        return reservaRepositorio.lastId();
    }
    public ArrayList<ReservaVO> obtenerListaFiltrada(String otherDni) throws ExeptionReserva{
        return reservaRepositorio.obtenerFiltroDniReservas(otherDni);
    }
}

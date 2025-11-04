package com.example.gestionhoteles.Model;

import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.Implem.ReservaRepoitoryImple;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;

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
    public void deleteReserva(String dni) throws ExeptionReserva{
        reservaRepositorio.deleteReserva(dni);
    }

    public int lastId() throws ExeptionReserva{
        return reservaRepositorio.lastId();
    }
}

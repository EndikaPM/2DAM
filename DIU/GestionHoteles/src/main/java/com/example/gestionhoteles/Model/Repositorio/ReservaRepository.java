package com.example.gestionhoteles.Model.Repositorio;



import com.example.gestionhoteles.Model.Reserva.ReservaVO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ReservaRepository {
    ArrayList<ReservaVO> ObtenerListaReservas() throws ExeptionReserva;

    void addReserva(ReservaVO reservaVO) throws ExeptionReserva;
    void deleteReserva(String reservaDni) throws ExeptionReserva;
    void updateReserva(ReservaVO reservaVO) throws ExeptionReserva;
    int lastId() throws ExeptionReserva;

    ArrayList<ReservaVO> obtenerFiltroDniReservas(String dni) throws ExeptionReserva;
}

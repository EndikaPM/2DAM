package com.example.gestionhoteles.Util;


import com.example.gestionhoteles.Model.Reserva.Reserva;
import com.example.gestionhoteles.Model.Reserva.ReservaVO;

public class ReservaUtil {

    public static ReservaVO getReserva(Reserva reserva){
        ReservaVO reservaVo = new ReservaVO();
        reservaVo.setId(reserva.getId());
        reservaVo.setFechaEntrada(reserva.getFechaLlegada());
        reservaVo.setFechaSalida(reserva.getFechaSalida());
        reservaVo.setIdUsuario(reserva.getDniCliente());
        reservaVo.setFumador( reserva.getIsFumador());
        reservaVo.setNumHabitaciones(reserva.getNumHabitaciones());
        reservaVo.setTipoHAbitaciones(reserva.getTipoHabitacion());
        reservaVo.setRegimenAlogamiento(reserva.getRegimenAlojamiento());
        System.out.println("Fecha en reservaUtil; " +  reserva.getFechaLlegada()+ "|"+ reserva.getFechaSalida());
        return reservaVo;
    }

    public static Reserva getReserva(ReservaVO reservaVO){
        Reserva reserva = new Reserva();
        reserva.setId(reservaVO.getId());
        reserva.setFechaLlegada(reservaVO.getFechaEntrada());
        reserva.setFechaSalida(reservaVO.getFechaSalida());
        reserva.setDniCliente(reservaVO.getIdUsuario());
        reserva.setIsFumador( reservaVO.getIsFumador());
        reserva.setNumHabitaciones(reservaVO.getNumHabitaciones());
        reserva.setTipoHabitacion(reservaVO.getTipoHAbitaciones());
        reserva.setRegimenAlojamiento(reservaVO.getRegimenAlogamiento());
        return reserva;
    }
}

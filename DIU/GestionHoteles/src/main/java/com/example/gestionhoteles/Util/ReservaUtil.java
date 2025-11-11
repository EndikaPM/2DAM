package com.example.gestionhoteles.Util;

import Reserva;
import ReservaVO;

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
        return reservaVo;
    }

    public static Reserva getReserva(ReservaVO reservaVO){
        Reserva reserva = new Reserva();
        reserva.setId(reservaVO.getId());
        reserva.setFechaLlegada(DateUtil.format(reservaVO.getFechaEntrada()));
        reserva.setFechaSalida(DateUtil.format(reservaVO.getFechaSalida()));
        reserva.setDniCliente(reservaVO.getIdUsuario());
        reserva.setIsFumador( reservaVO.getIsFumador());
        reserva.setNumHabitaciones(reservaVO.getNumHabitaciones());
        reserva.setTipoHabitacion(reservaVO.getTipoHAbitaciones());
        reserva.setRegimenAlojamiento(reservaVO.getRegimenAlogamiento());
        return reserva;
    }
}

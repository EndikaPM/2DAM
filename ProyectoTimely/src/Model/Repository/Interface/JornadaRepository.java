package Model.Repository.Interface;

import Model.Horas.HorasTrabajadas;
import Model.Horas.Jornada;
import Model.Repository.Exception.HorasException;
import Model.Repository.Exception.JornadaExeception;

import java.util.ArrayList;

public interface JornadaRepository {
    ArrayList<Jornada> obtenerListJornadas() throws JornadaExeception;
    boolean addJornada(Jornada jornada) throws JornadaExeception;
    boolean addEntradaJornada(Jornada jornada) throws JornadaExeception;
    boolean addSalidaJornada(Jornada jornada) throws JornadaExeception;
    boolean updateJornada(Jornada jornada) throws JornadaExeception;
    boolean deleteJornada(int id, String dniUsuario) throws JornadaExeception;
    int lastId ()throws JornadaExeception;
}

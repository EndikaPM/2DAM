package Model.Repository.Interface;


import Model.Horas.HorasTrabajadas;
import Model.Repository.Exception.HorasException;

import java.util.ArrayList;

public interface HorasRepository {
    ArrayList<HorasTrabajadas> obtenerListHorasTrabajadas() throws HorasException;
    boolean addHoras(HorasTrabajadas horasTrabajadas) throws HorasException;
    boolean updateHoras(HorasTrabajadas horasTrabajadas) throws HorasException;
    boolean deleteHoras(String dniUsuario) throws HorasException;
}

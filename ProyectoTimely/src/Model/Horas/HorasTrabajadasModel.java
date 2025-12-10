package Model.Horas;

import Model.Repository.Exception.HorasException;
import Model.Repository.Interface.HorasRepository;

import java.util.ArrayList;

public class HorasTrabajadasModel {
    private HorasRepository horasRepository;

    public HorasTrabajadasModel(HorasRepository horasRepository){this.horasRepository = horasRepository;}

    public ArrayList<HorasTrabajadas> obtenerListHorasTrabajadas() throws HorasException {
        return this.horasRepository.obtenerListHorasTrabajadas();
    }

    public boolean addHoras(HorasTrabajadas horasTrabajadas) throws HorasException {
        return this.horasRepository.addHoras(horasTrabajadas);
    }

    public boolean updateHoras(HorasTrabajadas horasTrabajadas) throws HorasException {
        return this.horasRepository.updateHoras(horasTrabajadas);
    }

    public boolean deleteHoras(String dniUsuario) throws HorasException {
        return this.horasRepository.deleteHoras(dniUsuario);
    }
}


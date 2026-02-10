package Model.Horas;

import Repository.Exception.HorasException;
import Repository.Interface.HorasRepository;

import java.util.ArrayList;

public class HorasTrabajadasModel implements HorasRepository{
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


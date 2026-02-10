package Model.Ausencia;

import Repository.Exception.AusenciaException;
import Repository.Interface.AusenciaRepository;

import java.util.ArrayList;

public class AusenciaModel implements AusenciaRepository{
    private AusenciaRepository ausenciaRepository;

    public AusenciaModel(AusenciaRepository ausenciaRepository){this.ausenciaRepository = ausenciaRepository;}

    @Override
    public ArrayList<AusenciaRepository> getListAusencias() throws AusenciaException {
        return ausenciaRepository.getListAusencias();
    }

    @Override
    public boolean addAusencia(Ausencia ausencia) throws AusenciaException {
        return ausenciaRepository.addAusencia(ausencia);
    }

    @Override
    public boolean updateAusencia(Ausencia ausencia) throws AusenciaException {
        return ausenciaRepository.updateAusencia(ausencia);
    }

    @Override
    public boolean deleteAusecia(Ausencia ausencia) throws AusenciaException {
        return ausenciaRepository.deleteAusecia(ausencia);
    }
}

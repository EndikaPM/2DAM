package Repository.Interface;

import Model.Ausencia.Ausencia;
import Repository.Exception.AusenciaException;

import java.util.ArrayList;

public interface AusenciaRepository {
    ArrayList<AusenciaRepository> getListAusencias() throws AusenciaException;
    boolean addAusencia(Ausencia ausencia) throws AusenciaException;
    boolean updateAusencia(Ausencia ausencia) throws AusenciaException;
    boolean deleteAusecia(Ausencia ausencia) throws AusenciaException;
}

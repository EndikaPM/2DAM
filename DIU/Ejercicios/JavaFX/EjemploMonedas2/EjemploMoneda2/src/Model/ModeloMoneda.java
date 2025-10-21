package Model;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

import java.util.ArrayList;

public class ModeloMoneda {
    private MonedaRepository monedaRepository;

    public ModeloMoneda() {
    }

    public MonedaRepository getMonedaRepository() {
        return monedaRepository;
    }

    public void setMonedaRepository(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public void addMoneda (MonedaVO monedaVO) throws ExcepcionMoneda {
        monedaRepository.addMoneda(monedaVO);
    }

    public ArrayList<MonedaVO> ObtenerListaMonedas() throws ExcepcionMoneda{
        return monedaRepository.ObtenerListaMonedas();
    }
    public void editMoneda(MonedaVO monedaVO) throws ExcepcionMoneda{
        monedaRepository.editMoneda(monedaVO);
    }
}

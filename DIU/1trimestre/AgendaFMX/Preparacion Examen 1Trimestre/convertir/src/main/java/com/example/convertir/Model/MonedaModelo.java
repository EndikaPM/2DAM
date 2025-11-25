package com.example.convertir.Model;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

import java.util.ArrayList;

public class MonedaModelo {

    private MonedaRepository  monedaRepository;

    public void setMonedaRepository(MonedaRepository monedaRepository) {this.monedaRepository = monedaRepository;}

    public ArrayList<MonedaVO> ObtenerListaMonedas() throws ExcepcionMoneda {
        return monedaRepository.ObtenerListaMonedas();
    }

    public void addMoneda(MonedaVO m) throws ExcepcionMoneda {
        monedaRepository.addMoneda(m);
    }

    public void editMoneda(MonedaVO monedaVO) throws ExcepcionMoneda {
        monedaRepository.editMoneda(monedaVO);
    }

    public int lastId() throws ExcepcionMoneda {
        return monedaRepository.lastId();
    }

    public float calular(float euro, float multiplicador) throws ExcepcionMoneda {
        return euro*multiplicador;
    }
}

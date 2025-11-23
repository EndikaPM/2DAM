package com.example.convertir.Util;

import Modelo.MonedaVO;
import com.example.convertir.Model.Moneda;

public class MonedaUtil {

    public static MonedaVO convertirMoneda (Moneda moneda){
       return new MonedaVO(moneda.getNombre(), moneda.getMultiplicador());
    }

    public static Moneda convertirMoneda (MonedaVO monedaVO){
        return new Moneda(monedaVO.getNombre(), monedaVO.getMultiplicador());
    }
}

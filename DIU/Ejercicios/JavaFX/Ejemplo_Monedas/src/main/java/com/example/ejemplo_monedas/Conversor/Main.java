package com.example.ejemplo_monedas.Conversor;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main  {
    public static void main(String[] args) {
        try {
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            MonedaVO monedaPrueba = new MonedaVO("prueba", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);
            System.out.println(monedarepositoryImpl.ObtenerListaMonedas().size());
            monedarepositoryImpl.deleteMoneda(8);
            System.out.println(monedarepositoryImpl.ObtenerListaMonedas().size());
            monedaPrueba.setNombre("Holassssss");
            monedaPrueba.setMultiplicador(2.0F);
            monedaPrueba.setCodigo(22);
            monedarepositoryImpl.editMoneda(monedaPrueba);
            System.out.println(monedarepositoryImpl.lastId() + " last id");

            for (MonedaVO mon : monedarepositoryImpl.ObtenerListaMonedas()) {
                System.out.println(mon.getCodigo() + " " + mon.getNombre() + ' ' + mon.getMultiplicador());
            }
        } catch (ExcepcionMoneda e) {
            System.out.println(e.imprimirMensaje());
        }

    }
}
package com.example.convertir.Controller;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import com.example.convertir.Model.Moneda;
import com.example.convertir.Model.MonedaModelo;
import com.example.convertir.Util.MonedaUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class ConversorContoller {
   @FXML
    private TextField euros;
   @FXML
    private TextField dolar;


   private MonedaModelo monedaModel;

   @FXML
    public void initialize(){
       euros.setText("0");
       dolar.setText("0");
   }

   public void setMonedaModel(MonedaModelo monedaModel){this.monedaModel = monedaModel;}

    public Float optenerMultiplicado() throws ExcepcionMoneda {
       float multiplicador = 0.0F;

        for (MonedaVO moneda : monedaModel.ObtenerListaMonedas()){
            if (moneda.getNombre().equals("dolar")){
                multiplicador = moneda.getMultiplicador();
            }
        }
        return multiplicador;
    }

   public void calcular(){
        euros.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){

                try {
                    dolar.setText(String.valueOf(monedaModel.calular(Integer.parseInt(euros.getText()),
                            optenerMultiplicado())));
                } catch (ExcepcionMoneda e) {
                    throw new RuntimeException(e);
                }
            }
        });
   }

}

package org.example.demo.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Label pantalla = new Label();
    @FXML
    private SimpleFloatProperty porcentaje;
    @FXML
    private TextField cantidad = new TextField();
    @FXML
    private ProgressBar barraProgreso = new ProgressBar(0.0);
     @FXML
    private Label viewPorce = new Label("0%");
    @FXML
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty(0);


    @FXML
    public void onClickPlusConuter() {
        numPulsaciones.setValue(numPulsaciones.getValue()+1);
        actualizarTexto();
    }

    @FXML
    public void onClickMinusConuter() {
        numPulsaciones.setValue(numPulsaciones.getValue()-1);
        actualizarTexto();
    }

    @FXML
    public void onClickResetConuter() {
        numPulsaciones.setValue(0);
        actualizarTexto();
    }
    @FXML
    public void actualizarTexto() {
        if (getNumPulsaciones() == 1 || getNumPulsaciones() == -1) {
            pantalla.setText("El contador es ha pulsado " +getNumPulsaciones()+ " vez.");
        }else if(getNumPulsaciones() == 0){
            pantalla.setText("La pantalla se a reiniciado a cero, pulsa un boton.");
            onBarrProgres();
            viewPorce.setText("0%");
        }else{
            pantalla.setText("La contador es ha pulsado " +getNumPulsaciones()+ " veces.");
            if(getNumPulsaciones() >= 0) {
                float progress = (float) getNumPulsaciones() / 50;
                barraProgreso.setProgress(progress);
                viewPorce.setText((int) (barraProgreso.getProgress() * 100) + "%");
            }
        }
    }

    @FXML
    public void onAlimentarConuter() {
       String num =  "";
       String cadena = String.valueOf(cantidad.getText());
       // si me da tiempo poner try/catch y un while

       for(int i=0; i< cadena.length(); i++){
           if(Character.isDigit(cadena.charAt(i))) {
               num += cadena.charAt(i);
               Integer pulsado = Integer.parseInt(num);
               numPulsaciones.setValue(pulsado);
               actualizarTexto();
               onBarrProgres();
           }
       }
    }

    @FXML
    public void onBarrProgres() {
        float progress = (float)getNumPulsaciones()/50;
        if (progress >= 0.0 && progress <= 1) {
            barraProgreso.setProgress(progress);
        }
    }

    public int getNumPulsaciones() {
        return numPulsaciones.get();
    }

    public IntegerProperty numPulsacionesProperty() {
        return numPulsaciones;
    }

    @FXML
    public void initialaze(){
        numPulsaciones.addListener((observableValue,
                                    number, t1) -> actualizarTexto());
    }
}

package com.example.contadorconscenebuider;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Text pantalla;
    private int numPulsaciones;



    @FXML
    public void onClickPlusConuter() {
        numPulsaciones++;
        actualizarTexto();
    }

    @FXML
    public void onClickMinusConuter() {
        numPulsaciones--;
        actualizarTexto();
    }

    @FXML
    public void onClickResetConuter() {
        numPulsaciones = 0;
        actualizarTexto();
    }
    @FXML
    public void actualizarTexto() {
        if (numPulsaciones == 1 || numPulsaciones == -1) {
            pantalla.setText("El contador es ha pulsado " +numPulsaciones+ " vez.");
        }else if(numPulsaciones == 0){
            pantalla.setText("La pantalla se a reiniciado a cero, pulsa un boton.");
        }else{
            pantalla.setText("La contador es ha pulsado " +numPulsaciones+ " veces.");
        }
    }

}

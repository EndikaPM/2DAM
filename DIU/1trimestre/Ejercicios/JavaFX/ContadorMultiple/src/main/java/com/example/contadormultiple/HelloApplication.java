package com.example.contadormultiple;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        try {
            Escenas escena1 = new Escenas();
            Escenas escena2 = new Escenas();

            escena1.setEscena();
            escena2.setEscena();
            escena1.getPulsaciones().bindBidirectional(escena2.getPulsaciones());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package com.example.contadormultiple;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Binding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Escenas {
    private Label etiqueta;
    private final SimpleIntegerProperty nunPulsaciones = new SimpleIntegerProperty();


    public void setEscena() {


        Stage escenarioPrincipal = new Stage();
        HBox raiz = new HBox(30);
        raiz.setPadding(new Insets(10));
        raiz.setAlignment(Pos.CENTER);

        // Boton Mas
        Button botonMas = new Button("+");
        botonMas.setFont(Font.font("Arial", 25));
        botonMas.setOnAction(event -> botonpulsadoMas());
        //boton Menos
        Button botonReset = new Button("Reset");
        botonReset.setFont(Font.font("Arial", 30));
        botonReset.setOnAction(event -> botonpulsadoReset());
        //boton Menos
        Button botonMenos = new Button("-");
        botonMenos.setFont(Font.font("Arial", 25));
        botonMenos.setOnAction(event -> botonpulsadoMenos());


        etiqueta = new Label();
        etiqueta.setFont(Font.font("Arial", 20));
        etiqueta.setText("El boton aun no se ha pulsado!");
        /*nunPulsaciones.addListener((InvalidationListener) e ->
                escribirEtiquet()
        );*/
        nunPulsaciones.addListener((observable, oldValue, newValue)
                -> escribirEtiquet());



        raiz.getChildren().addAll(botonMas,botonReset, botonMenos, etiqueta);

        Scene escena = new Scene(raiz, 750, 150);
        escenarioPrincipal.setTitle("Pulsaciones del botón");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    public SimpleIntegerProperty getPulsaciones(){
        return nunPulsaciones;
    }
    public void botonpulsadoMas(){
        nunPulsaciones.setValue(nunPulsaciones.getValue()+1);
        escribirEtiquet();
    }

    public void botonpulsadoMenos(){
        nunPulsaciones.setValue(nunPulsaciones.getValue()-1);
        escribirEtiquet();
    }

    public void botonpulsadoReset(){
        nunPulsaciones.setValue(0);
        escribirEtiquet();
    }
    public void escribirEtiquet(){
        if (nunPulsaciones.getValue() == 0){
            etiqueta.setText("Boton reseteado");
        }else if(nunPulsaciones.getValue() == 1 || nunPulsaciones.getValue() == - 1){
            etiqueta.setText("El boton se ha pulsado " + nunPulsaciones.getValue() + " vez!");
        }else {
            etiqueta.setText("El boton se ha pulsado " + nunPulsaciones.getValue() + " veces");
        }
    }
}


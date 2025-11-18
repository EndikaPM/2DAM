package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Reserva.Reserva;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class RootController {
    private Main mainapp;

    @FXML
    private void initialize() {}

    public void setMain (Main mainapp) {
        this.mainapp = mainapp;
    }

    @FXML
    private void showVerReserva(){
        mainapp.showVerReservas();
    }
    @FXML
    private void showStatsRoom(){mainapp.showStatsRoom();}
    @FXML
    private void showStats(){}
    @FXML
    private void handleClose(){
        Platform.exit(); // Cierra la aplicación
    }
}

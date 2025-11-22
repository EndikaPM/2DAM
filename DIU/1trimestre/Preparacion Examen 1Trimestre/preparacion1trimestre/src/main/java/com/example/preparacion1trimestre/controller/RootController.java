package com.example.preparacion1trimestre.controller;

import com.example.preparacion1trimestre.View.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RootController {
    private Main mainApp;

    @FXML
    private void initialize() {}
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }
}

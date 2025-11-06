package com.example.gestionhoteles.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UsuarioCotroller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleNewPerson(ActionEvent actionEvent) {
    }

    public void handleEditPerson(ActionEvent actionEvent) {
    }

    public void handleDeletePerson(ActionEvent actionEvent) {

    }
}

package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class HabitacionesViewController {
    @FXML
    private Label tituDoble;
    @FXML
    private Label reservaTable;

    private Main mainapp;

    public void setMain (Main mainapp) {
        this.mainapp = mainapp;
    }

    public void backdoble(ActionEvent actionEvent) {}
    public void nextDoble(ActionEvent actionEvent) {}
    public void backDobleInvidial(ActionEvent actionEvent) {}
    public void nextDobleIndi(ActionEvent actionEvent) {}
    public void backJuniorSuit(ActionEvent actionEvent) {}
    public void nextJuniorSuit(ActionEvent actionEvent) {}
    public void backSuit(ActionEvent actionEvent) {}
    public void nextSuite(ActionEvent actionEvent) {}
}

package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UsuarioCotroller {
    @FXML
    private TableView<Usuario> usuarioTable;
    @FXML
    private TableColumn<Usuario, String> firstNameColumn;
    @FXML
    private TableColumn<Usuario, String> lastNameColumn;
    @FXML
    private javafx.scene.control.Label firstNameLabel;
    @FXML
    private javafx.scene.control.Label lastNameLabel;
    @FXML
    private javafx.scene.control.Label streetLabel;
    @FXML
    private javafx.scene.control.Label postalCodeLabel;
    @FXML
    private javafx.scene.control.Label cityLabel;

// TODO: tengo que añadir provincia , la localidad  y la codigo postal
    ç


    public void handleNewPerson(ActionEvent actionEvent) {
    }

    public void handleEditPerson(ActionEvent actionEvent) {
    }
}

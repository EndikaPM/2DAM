package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Reserva.Reserva;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import com.example.gestionhoteles.Util.DateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    @FXML
    private javafx.scene.control.Label provinciaLabel;
    @FXML
    private javafx.scene.control.Label dni;

    private Main mainApp;

    public UsuarioCotroller() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        // Clear person details.
        showUsersDetails(null);
        // Listen for selection changes and show the person details when changed.
        usuarioTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUsersDetails(newValue));
    }

    //Añadimos la informacion del observableList a la tabla y al main
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        usuarioTable.setItems(mainApp.getUserData());
    }

    private void showUsersDetails(Usuario user) {
        if (user != null) {
            // Fill the labels with info from the person object.
            dni.setText(user.getDni());
            firstNameLabel.setText(user.getNombre());
            lastNameLabel.setText(user.getApellido());
            streetLabel.setText(user.getDireccion());
            postalCodeLabel.setText(Integer.toString(user.getCodigoPostal()));
            cityLabel.setText(user.getLocalidad());
            provinciaLabel.setText(user.getProvincia());
        } else {
            // Person is null, remove all the text.
            dni.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            provinciaLabel.setText("");
        }
    }


    public void handleNewPerson(ActionEvent actionEvent) {
        Usuario usuarioTemp = new Usuario();
        boolean okClicked = mainApp.showUserEditDialog(usuarioTemp, "Añadir Persona");

        if (okClicked) {
            usuarioTemp.setDni(dni.getText());
            mainApp.getUserData().add(usuarioTemp);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }

    public void handleEditPerson(ActionEvent actionEvent) {
        Usuario selectedPerson = usuarioTable.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            boolean okClicked = mainApp.showUserEditDialog(selectedPerson, "Editar Persona");
            if (okClicked) {
                showUsersDetails(selectedPerson);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }

    public void handleViewReser(ActionEvent actionEvent) {
        Reserva reserva = new Reserva();

    }

}
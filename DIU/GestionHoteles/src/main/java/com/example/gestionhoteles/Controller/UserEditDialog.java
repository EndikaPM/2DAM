package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import com.example.gestionhoteles.Util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditDialog {
    @FXML
    private TextField dni_edit;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField provinciaField;

    private Stage dialogStage;
    private Usuario user;
    private boolean okClicked = false;
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUser(Usuario user) {
        this.user = user;

        dni_edit.setText(user.getDni());
        firstNameField.setText(user.getNombre());
        lastNameField.setText(user.getApellido());
        streetField.setText(user.getDireccion());
        postalCodeField.setText(Integer.toString(user.getCodigoPostal()));
        cityField.setText(user.getLocalidad());
        provinciaField.setText(user.getProvincia());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            user.setDni(dni_edit.getText());
            user.setNombre(firstNameField.getText());
            user.setApellido(lastNameField.getText());
            user.setDireccion(streetField.getText());
            user.setCodigoPostal(Integer.parseInt(postalCodeField.getText()));
            user.setLocalidad(cityField.getText());
            user.setProvincia(postalCodeField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (dni_edit.getText() == null || !dni_edit.getText().trim().isEmpty()) {
            errorMessage += "No valid dni!";
        }
        if (dni_edit.getText().length() == 9 && Character.isLetter(dni_edit.getText().charAt(8))) {
            //String letras = "TRWAGMYFPDXBNJZSQVHLCKE"
            int numerosDNI = Integer.parseInt(dni_edit.getText().substring(0, 8));
            char letraDNI = Character.toUpperCase(dni_edit.getText().charAt(8));

            //calculamos la letra
            int resultado = numerosDNI % 23;


        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            //TODO Alerta
            return false;
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}

package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import static com.example.gestionhoteles.Main.checkDni;

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
    private boolean isNewUser = false;
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

        if(user.getDni() == null || user.getDni().isEmpty()){
            dni_edit.setText("");
            dni_edit.setDisable(false); // Permite introducir el DNI
            firstNameField.setText("");
            lastNameField.setText("");
            streetField.setText(user.getDireccion());
            postalCodeField.setText(Integer.toString(user.getCodigoPostal()));
            cityField.setText(user.getLocalidad());
            provinciaField.setText(user.getProvincia());

            isNewUser = true;
        }
        else{ // El usuario no es nuevo
            dni_edit.setText(user.getDni());
            dni_edit.setDisable(true); // Deshabilita la edición del DNI
            firstNameField.setText(user.getNombre());
            lastNameField.setText(user.getApellido());
            streetField.setText(user.getDireccion());
            postalCodeField.setText(Integer.toString(user.getCodigoPostal()));
            cityField.setText(user.getLocalidad());
            provinciaField.setText(user.getProvincia());

            isNewUser = false;
        }

    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if (checkDni()) {
                // Solo actualizar el DNI si es un usuario nuevo
                if (isNewUser) {
                    user.setDni(dni_edit.getText().toUpperCase());
                }
                // Si NO es nuevo, el DNI ya existe en el objeto user, no lo toques
                user.setNombre(firstNameField.getText());
                user.setApellido(lastNameField.getText());
                user.setDireccion(streetField.getText());
                user.setCodigoPostal(Integer.parseInt(postalCodeField.getText()));
                user.setLocalidad(cityField.getText());
                user.setProvincia(provinciaField.getText());

                okClicked = true;
                dialogStage.close();
            }
        }
    }

    public boolean checkDni() {
        String dni = dni_edit.getText().trim().toUpperCase(); // Garantiza que la última letra sea mayúscula

        // En caso de que no tenga 9 caractéres, no es válido
        if (dni.length() != 9) {
            return false;
        }

        // Para asegurar que el formato esté bien, se divide la parte númerica de la letra
        String numberPart = dni.substring(0, 8);
        char dniLetter = dni.charAt(8);

        // Comprueba que la letra es realmente una letra
        if (!Character.isLetter(dniLetter)) {
            return false;
        }

        // Comprueba que los primeros 8 son dígitos
        if (!numberPart.matches("\\d{8}")) {
            return false;
        }

        // Asegura que los números del DNI correspondan a su letra
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int dniNum = Integer.parseInt(numberPart);
        char correctLetter = letters.charAt(dniNum % 23);

        return dniLetter == correctLetter;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (!checkDni()) {errorMessage += "No valid dni!";}
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() != 5) {
            errorMessage += "No valid postal code!\n";
        }
        else {
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Los campos no validos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}

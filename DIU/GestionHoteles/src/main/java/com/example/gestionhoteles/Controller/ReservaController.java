package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Reserva.Reserva;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReservaController {
    @FXML
    private CheckBox isFumador;
    @FXML
    private TextArea textIsFumador;
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label dni;
    @FXML
    private DatePicker data_entry;
    @FXML
    private DatePicker data_exit;
    @FXML
    private ChoiceBox<String> typeRoom;
    @FXML
    private RadioButton AlojaYDesayu;
    @FXML
    private RadioButton mediaPension;
    @FXML
    private RadioButton personaCompleta;

    private Stage dialogStage;
    private Usuario user;
    private Reserva reserva;
    private boolean okClicked = false;
    private Main mainApp;
    ToggleGroup toggleGroup;


    @FXML
    private void initialize() {

        toggleGroup = new ToggleGroup();

        AlojaYDesayu.setToggleGroup(toggleGroup);
        mediaPension.setToggleGroup(toggleGroup);
        personaCompleta.setToggleGroup(toggleGroup);

        typeRoom.getItems().addAll("Doble de uso individual",
                                    "Doble", "Junior suite", "Suite");

        // Listener para cambios en la selección
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                RadioButton selected = (RadioButton) newVal;
                System.out.println(selected.getText() + " esta seleccionado");
            }
        });
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void handleIsSmoker(){
        // En caso de que sea fumador se muestra el mensaje
        if(isFumador.isSelected()){
            textIsFumador.setDisable(false);
        }
        else{
            textIsFumador.setDisable(true);
        }
    }

    public void setReserva(Usuario reservaDni) {
        this.user  = reservaDni;

        name.setText(reservaDni.getNombre());
        surname.setText(reservaDni.getApellido());
        dni.setText(reservaDni.getDni());
    }

    @FXML
    private void handleOkReserva() {
        if (isInputValid()){
            reserva.setDniCliente(dni.getText());
            reserva.setFechaLlegada(data_entry.getAccessibleText());
            reserva.setFechaSalida(data_exit.getAccessibleText());
            reserva.setTipoHabitacion(typeRoom.getValue());
            reserva.setIsFumador(isFumador.isSelected());
            reserva.setRegimenAlojamiento(toggleGroup.getSelectedToggle().getUserData().toString());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleClear() {
        data_entry.setValue(null);
        data_exit.setValue(null);
        isFumador.setSelected(false);
        toggleGroup.selectToggle(null);
        typeRoom.setValue(null);
    }

    @FXML
    private void handleBack() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (data_entry.getValue() == null) {
            errorMessage += "Fecha de entrada no válida!\n";
        }

        if (data_exit.getValue() == null) {
            errorMessage += "Fecha de salida no válida!\n";
        }

        if (typeRoom.getValue() == null) {
            errorMessage += "Debe seleccionar un tipo de habitación!\n";
        }

        if (toggleGroup.getSelectedToggle() == null) {
            errorMessage += "Debe seleccionar un régimen!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            //Alerta
            return false;
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

}

package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
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
    private Reserva reserva; // El objeto Reserva que vamos a crear
    private boolean okClicked = false;
    private Main mainApp;
    private ToggleGroup toggleGroup;
    private RadioButton selected;


    @FXML
    private void initialize() {

        toggleGroup = new ToggleGroup();

        AlojaYDesayu.setToggleGroup(toggleGroup);
        mediaPension.setToggleGroup(toggleGroup);
        personaCompleta.setToggleGroup(toggleGroup);

        typeRoom.getItems().addAll("doble_uso_individual",
                                    "doble", "junior_suite", "suite");

        // Listener para cambios en la selección
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                selected = (RadioButton) newVal;
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
        name.setText(user.getNombre());
        surname.setText(user.getApellido());
        //añadimos una nueva reserva
        this.reserva = new Reserva(); //creamos la reserva

        //Cogo el dni de usuario directamente
        dni.setText(reservaDni.getDni());
        dni.setDisable(true);

    }

    @FXML
    private void handleOkReserva() {
        if (isInputValid()){
            reserva.setDniCliente(user.getDni());//Solucionar el usuario es nulo sus MUERTOS A CABALLO
            System.out.println("Fecha entrada: " + data_entry.getValue());
            reserva.setFechaLlegada(data_entry.getValue());// en la clase Reserva He tocado el metodo setFechaLlegada
            System.out.println("Fecha salida: " + data_exit.getValue());
            reserva.setFechaSalida(data_exit.getValue());// en la clase Reserva He tocado el metodo setFechaSalida
            reserva.setTipoHabitacion(typeRoom.getValue());
            reserva.setIsFumador(isFumador.isSelected());
            reserva.setRegimenAlojamiento(selected.getText());

            try {
                //Guardar la reserva en la base de datos
                mainApp.addReservaDb(reserva);
                //Agregar a la lista observable
                mainApp.getReservaData().add(reserva);

                okClicked = true;
                dialogStage.close();
            } catch (ExeptionReserva e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al crear reserva");
                System.out.println(e.getMessage());
                alert.showAndWait();
            }
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CAMPOS NO VALIDOS");
            alert.showAndWait();
            return false;
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

}

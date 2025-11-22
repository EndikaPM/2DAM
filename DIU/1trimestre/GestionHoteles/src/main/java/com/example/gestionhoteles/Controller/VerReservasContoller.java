package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.Implem.ReservaRepoitoryImple;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.Reserva.ModelReserva;
import com.example.gestionhoteles.Model.Reserva.Reserva;
import com.example.gestionhoteles.Model.Reserva.ReservaVO;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import com.example.gestionhoteles.Util.DateUtil;
import com.example.gestionhoteles.Util.ReservaUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class VerReservasContoller {
    @FXML
    private TableView<Reserva> tableViewReservas;
    @FXML
    private TextField dni_find;
    @FXML
    private TableColumn<Reserva, String> dniColumn;
    @FXML
    private TableColumn<Reserva, String> fechaLlegadaColumn;
    @FXML
    private TableColumn<Reserva, String> fechaSalidaColumn;
    @FXML
    private TableColumn<Reserva, String> tipoHabitacion;
    @FXML
    private TableColumn<Reserva, String> fumador;
    @FXML
    private TableColumn<Reserva, String> regimenAlojamiento;
    
    @FXML
    private javafx.scene.control.Label labelDni;
    @FXML
    private javafx.scene.control.Label LabelLlegada;
    @FXML
    private javafx.scene.control.Label LabelSalida;
    @FXML
    private javafx.scene.control.Label LabelTHabitacion;
    @FXML
    private javafx.scene.control.Label LAbelFumador;
    @FXML
    private javafx.scene.control.Label LabelREgimen;
    
    @FXML
    private TableColumn<Reserva, String> codigoPostalColumn;



    private Main mainApp;

    @FXML
    public void initialize(){
        // Initialize the person table with the two columns.
        dniColumn.setCellValueFactory(cellData -> cellData.getValue().getDniClienteProperty());
        fechaLlegadaColumn.setCellValueFactory(cellData -> cellData.getValue().getFechaLlegadaProperty().asString());
        fechaSalidaColumn.setCellValueFactory(cellData -> cellData.getValue().getFechaSalidaProperty().asString());
        tipoHabitacion.setCellValueFactory(cellData -> cellData.getValue().getTipoHabitacionProperty());
        fumador.setCellValueFactory(cellData -> cellData.getValue().fumadorProperty().asString());
        regimenAlojamiento.setCellValueFactory(cellData -> cellData.getValue().getRegimenAlojamientoProperty());

        // Clear person details.
        showReservasDetails(null);
        // Listen for selection changes and show the person details when changed.
        tableViewReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservasDetails(newValue));
    }

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        tableViewReservas.setItems(mainApp.getReservaData());
    }

    private void showReservasDetails(Reserva rese){
        if (rese != null) {
            // Fill the labels with info from the person object.
            labelDni.setText(rese.getDniCliente());
            LabelLlegada.setText(rese.getFechaLlegada().toString());
            LabelSalida.setText(rese.getFechaSalida().toString());
            LabelTHabitacion.setText(rese.getTipoHabitacion().toString());
            LAbelFumador.setText(rese.getIsFumador()? "Fumador" : "No fumador");
            LabelREgimen.setText(rese.getRegimenAlojamiento().toString());
        } else {
            // Person is null, remove all the text.
            labelDni.setText("");
            LabelLlegada.setText("");
            LabelSalida.setText("");
            LabelTHabitacion.setText("");
            LAbelFumador.setText("");
            LabelREgimen.setText("");
        }
    }
    @FXML
    private void handlerFindDni() throws ExeptionReserva {
        tableViewReservas.setItems(mainApp.filterReserva(dni_find.getText()));
    }

    @FXML
    private void cleanFilter(){
        tableViewReservas.setItems(mainApp.getReservaData());
    }
    @FXML
    private void handleDelete() throws ExeptionReserva {
        Reserva rese = tableViewReservas.getSelectionModel().getSelectedItem();
        mainApp.getReservaData().remove(rese);
        System.out.println("Eliminado reserva: " + rese.getId());
        mainApp.getReserva().deleteReserva(rese.getId());
    }

    @FXML
    private void handleEdit() {
        Reserva reservaSeleccionada = tableViewReservas.getSelectionModel().getSelectedItem();

        if (!(reservaSeleccionada.getFechaLlegada().isBefore(LocalDate.now()))) {
            if (reservaSeleccionada != null) {
                boolean okClicked = mainApp.showReservaEditDialog(reservaSeleccionada);

                if (okClicked) {
                    // Refrescar la tabla para mostrar los cambios
                    tableViewReservas.refresh();
                    showReservasDetails(reservaSeleccionada);
                }
            } else {
                // Mostrar alerta si no hay ninguna reserva seleccionada
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No hay selección");
                alert.setHeaderText("No hay reserva seleccionada");
                alert.setContentText("Por favor seleccione una reserva de la tabla.");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No es posible Editar");
            alert.setContentText("Reserva seleccionada no se puede editar porque\n la fecha es menor a a actual");
            alert.showAndWait();
        }
    }
}

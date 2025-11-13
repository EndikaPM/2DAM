package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Model.Reserva.Reserva;
import com.example.gestionhoteles.Util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VerReservasContoller {
    @FXML
    private TextField dni_find;

    @FXML
    private TableColumn<Reserva, String> dniColumnLabel;
    @FXML
    private TableColumn<Reserva, String> tipoColumnLabel;

    @FXML
    private TableView<Reserva> tableViewReservas;

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
    private TableColumn<Reserva, String> codigoPostalColumn;

    private ObservableList<Reserva> reservasList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        // Initialize the person table with the two columns.
        dniColumnLabel.setCellValueFactory(cellData -> cellData.getValue().getDniClienteProperty());
        tipoColumnLabel.setCellValueFactory(cellData -> cellData.getValue().getRegimenAlojamientoProperty());
        // Clear person details.
        showReservasDetails(null);
        // Listen for selection changes and show the person details when changed.
        tableViewReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservasDetails(newValue));
    }

    private void showReservasDetails(Reserva reserva) {
        if (reserva != null) {
            // Fill the labels with info from the person object.
            dniColumn.setText(reserva.getDniCliente());
            fechaLlegadaColumn.setText(reserva.getFechaLlegada().toString());
            fechaSalidaColumn.setText(reserva.getFechaSalida().toString());
            tipoHabitacion.setText(reserva.getTipoHabitacion());
            String esFumador = reserva.getIsFumador() ? "Es Fumador" : "No es Fumador";
            fumador.setText(esFumador);
            regimenAlojamiento.setText(reserva.getRegimenAlojamiento());
        } else {
            // Person is null, remove all the text.
            dniColumn.setText("");
            fechaLlegadaColumn.setText("");
            fechaSalidaColumn.setText("");
            tipoHabitacion.setText("");
            fumador.setText("");
            regimenAlojamiento.setText("");
        }
    }

}

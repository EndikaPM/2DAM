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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class VerReservasContoller {
    @FXML
    private TableView<Reserva> reservaTable;
    @FXML
    private TextField dni_find;

    @FXML
    private TableColumn<Reserva, String> dniColumnLabel;
    @FXML
    private TableColumn<Reserva, String> nombreColumnLabel;

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

    //TODO preguntar a jacobo como lo manejo de forma adecuada

    private Main mainApp;

    @FXML
    public void initialize(){
        // Initialize the person table with the two columns.
        dniColumnLabel.setCellValueFactory(cellData -> cellData.getValue().getDniClienteProperty());
        nombreColumnLabel.setCellValueFactory(cellData -> cellData.getValue().getRegimenAlojamientoProperty());
        // Clear person details.
        showReservasDetails(null);
        // Listen for selection changes and show the person details when changed.
        tableViewReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservasDetails(newValue));
    }

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        reservaTable.setItems(mainApp.getReservaData());
    }

    private void showReservasDetails(Reserva reserva){}
    @FXML
    public void handlerFindDni(){}
}

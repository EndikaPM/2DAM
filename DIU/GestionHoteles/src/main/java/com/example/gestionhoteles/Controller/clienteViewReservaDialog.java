package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Reserva.Reserva;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class clienteViewReservaDialog {
    @FXML
    private TableView<Reserva> tableViewReservas;
    @FXML
    private TableColumn<Reserva, String> columDni;
    @FXML
    private TableColumn<Reserva, String> columFechaLLegada;
    @FXML
    private TableColumn<Reserva, String> columFechaSalida;
    @FXML
    private TableColumn<Reserva, String> ColumTipoHabit;
    @FXML
    private TableColumn<Reserva, String> ColumIsFumador;
    @FXML
    private TableColumn<Reserva, String> ColumRegimen;
    @FXML
    private Label LableDNIDetalles;
    @FXML
    private Label LableNombreDetalles;
    @FXML
    private Label LableApellidoDetalles;

    private Stage dialogStage;
    private Usuario user;
    private Reserva reserva;
    private boolean okClicked = false;
    private boolean isNewUser = false;
    private Main mainApp;

    @FXML
    public void initialize(){
        columDni.setCellValueFactory(cellData -> cellData.getValue().getDniClienteProperty());
        columFechaLLegada.setCellValueFactory(cellData -> cellData.getValue().getFechaLlegadaProperty().asString());
        columFechaSalida.setCellValueFactory(cellData -> cellData.getValue().getFechaSalidaProperty().asString());
        ColumTipoHabit.setCellValueFactory(cellData -> cellData.getValue().getTipoHabitacionProperty());
        ColumIsFumador.setCellValueFactory(cellData -> cellData.getValue().fumadorProperty().asString());
        ColumRegimen.setCellValueFactory(cellData -> cellData.getValue().getRegimenAlojamientoProperty());

        tableViewReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservasDetails(newValue));
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    public void setUsuario(Usuario usuario) {
        this.user = usuario;
        cargarReservasDelCliente();
    }

    private void cargarReservasDelCliente() {
        if (user != null && user.getDni() != null) {
            try {
                // Usamos el método filterReserva de Main que internamente
                // usa reserva.obtenerListaFiltrada(dni) del ReservaRepositoryImple
                ObservableList<Reserva> reservasCliente = mainApp.filterReserva(user.getDni());
                tableViewReservas.setItems(reservasCliente);

                // Mostramos información si no hay reservas
                if (reservasCliente.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sin reservas");
                    alert.setHeaderText(null);
                    alert.setContentText("Este cliente no tiene reservas registradas.");
                    alert.showAndWait();
                }

            } catch (ExeptionReserva e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al cargar las reservas");
                alert.setContentText("No se pudieron obtener las reservas del cliente: " + e.getMessage());
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public void showReservasDetails(Reserva reserv){
        this.reserva = reserv;
        if (reserv != null) {
            LableDNIDetalles.setText(reserv.getDniCliente());
            LableNombreDetalles.setText(reserv.getFechaLlegada().toString());
            LableApellidoDetalles.setText(reserv.getFechaSalida().toString());
        } else {
            LableDNIDetalles.setText("");
            LableNombreDetalles.setText("");
            LableApellidoDetalles.setText("");
        }
    }

    @FXML
    private void closeDialog(){
        dialogStage.close();
    }


    public boolean isOkClicked() {
        return okClicked;
    }
}

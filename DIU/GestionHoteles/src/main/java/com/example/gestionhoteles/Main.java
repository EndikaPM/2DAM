package com.example.gestionhoteles;

import com.example.gestionhoteles.Controller.ReservaController;
import com.example.gestionhoteles.Controller.UserEditDialog;
import com.example.gestionhoteles.Controller.UsuarioCotroller;
import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.Implem.ReservaRepoitoryImple;
import com.example.gestionhoteles.Model.Repositorio.Implem.UsuarioRepositoryImple;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.Repositorio.UsuarioRepository;
import com.example.gestionhoteles.Model.Reserva.ModelReserva;
import com.example.gestionhoteles.Model.Reserva.Reserva;
import com.example.gestionhoteles.Model.Reserva.ReservaVO;
import com.example.gestionhoteles.Model.Usuario.ModelUsuario;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import com.example.gestionhoteles.Model.Usuario.UsuarioVO;
import com.example.gestionhoteles.Util.ReservaUtil;
import com.example.gestionhoteles.Util.UsuarioUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private static ObservableList<Usuario> userData = FXCollections.observableArrayList();
    private static ObservableList<Reserva> reservaData = FXCollections.observableArrayList();
    private UsuarioRepository  usuarioRepository = new UsuarioRepositoryImple();
    private ReservaRepository reservaRepository = new ReservaRepoitoryImple();
    private ModelUsuario usuario= new ModelUsuario(usuarioRepository);
    private ModelReserva reserva = new ModelReserva(reservaRepository);

    public Main()  {
        try { //TODO pregntar a Jacabo
            ArrayList<UsuarioVO> usuarioVo = usuario.ObtenerListaUsuario();

            for (UsuarioVO usu : usuarioVo) {
                userData.add(UsuarioUtil.getUsuario(usu));
            }

            ArrayList<ReservaVO> reservaVO = reserva.ObtenerListaReservas();

            for (ReservaVO reserv : reservaVO) {
                reservaData.add(ReservaUtil.getReserva(reserv));
            }

        }catch (ExeptionReserva |ExceptionUsuario e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al conectar con la base de datos");
            alert.setContentText("No ha sido posible obtener la lista de clientes.");
            alert.showAndWait();
        }
    }

    public void addUserDb(Usuario otherUser) throws ExceptionUsuario {
        usuario.addUsuario(UsuarioUtil.getUsuario(otherUser));
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion Hoteles");
        initRootLayout();
        showUsuario();
        this.primaryStage.getIcons().add(new Image(getClass().getResource("/images/icoHotel.png").toExternalForm()));
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/root_view.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showUsuario(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/usuario_view.fxml"));
            AnchorPane usuarioVista = (AnchorPane) loader.load();

            rootLayout.setCenter(usuarioVista);

            UsuarioCotroller controllerUsuario = loader.getController();
            controllerUsuario.setMain(this);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static ObservableList<Usuario> getUserData() {
        return userData;
    }

    public boolean showUserEditDialog(Usuario user, String title) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/userEdirDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UserEditDialog controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showReserva(Usuario userReserva){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/reservar_view.fxml"));
            AnchorPane reservaVista = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Reserva");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(reservaVista);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ReservaController cotrolleReserva = loader.getController();
            cotrolleReserva.setMainApp(this);
            cotrolleReserva.setDialogStage(dialogStage);
            cotrolleReserva.setReserva(userReserva);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return cotrolleReserva.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

}

package com.example.gestionhoteles;

import com.example.gestionhoteles.Controller.UsuarioCotroller;
import com.example.gestionhoteles.Model.*;
import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.Implem.ReservaRepoitoryImple;
import com.example.gestionhoteles.Model.Repositorio.Implem.UsuarioRepositoryImple;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.Repositorio.UsuarioRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.module.ResolvedModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("usuario_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        List<UsuarioVO> listUseres = new ArrayList<>();
        try {
            //Prueba inseccion
            UsuarioRepository repoUsu = new UsuarioRepositoryImple();
            ModelUsuario usuario = new ModelUsuario(repoUsu);
            UsuarioVO cl = new UsuarioVO("1234567A", "Editado", "Morgan",
                    "C/ Valentine", "Valentine", "Floreada", 1);
            //usuario.addUsuario(cl);
            listUseres = usuario.ObtenerListaUsuario();
            for (UsuarioVO u : listUseres) {
                //System.out.println(u.toString());
                //}
                //usuario.updateUsuario(cl);
                //usuario.deleteUsuario("1234567A");

                ReservaRepository repoReser = new ReservaRepoitoryImple();
                ModelReserva reserva = new ModelReserva(repoReser);
                ReservaVO rev = new ReservaVO("1234567A", true,
                        "alojamiento_desayuno", "doble", 99, "15/10/2025", "25/10/2025");
                reserva.addReserva(rev);
                List<ReservaVO> listReservas = new ArrayList<>();
                listReservas = repoReser.ObtenerListaReservas();
                for (ReservaVO res : listReservas) {
                    System.out.println(res.toString());
                }

                System.out.println("CLIENTE INSERTADO");

            }
        }catch(ExceptionUsuario | ExeptionReserva e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al conectar con la base de datos");
            alert.setContentText("No ha sido posible obtener la lista de clientes.");
            alert.showAndWait();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }*/
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
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public Main(){}
    private static ObservableList<Usuario> usuarioData = FXCollections.observableArrayList();
    public void showUsuario(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/usuario_view.fxml"));
            AnchorPane usuarioVista = (AnchorPane) loader.load();
            rootLayout.setCenter(usuarioVista);
            UsuarioCotroller controllerUsuario = loader.getController();
            //controllerUsuario.setMain(this);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}

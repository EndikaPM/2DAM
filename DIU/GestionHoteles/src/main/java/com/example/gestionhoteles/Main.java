package com.example.gestionhoteles;

import com.example.gestionhoteles.Controller.UsuarioCotroller;
import com.example.gestionhoteles.Model.Usuario.Usuario;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private static ObservableList<Usuario> userData = FXCollections.observableArrayList();

    public Main(){}

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
            //controllerUsuario.setMain(this);
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


}

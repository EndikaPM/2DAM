package com.example.gestionhoteles;

import com.example.gestionhoteles.Model.*;
import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.Implem.ReservaRepoitoryImple;
import com.example.gestionhoteles.Model.Repositorio.Implem.UsuarioRepositoryImple;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.Repositorio.UsuarioRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.module.ResolvedModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

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
        }
    }
}

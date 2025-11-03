package com.example.gestionhoteles;

import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.Implem.UsuarioRepositoryImple;
import com.example.gestionhoteles.Model.UsuarioVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        try {
            //Prueba inseccion
            UsuarioRepositoryImple repo =  new UsuarioRepositoryImple();
            UsuarioVO cl = new UsuarioVO("1234567A", "Arthur", "Morgan",
                    "C/ Valentine", "Valentine", "Floreada",1);


            repo.addUsuario(cl);

            System.out.println("CLIENTE INSERTADO");

        }
        catch (ExceptionUsuario e) {
            throw new RuntimeException(e);
        }
    }
}

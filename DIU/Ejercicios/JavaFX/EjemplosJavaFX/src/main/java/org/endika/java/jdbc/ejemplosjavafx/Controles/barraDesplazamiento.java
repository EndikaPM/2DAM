package org.endika.java.jdbc.ejemplosjavafx.Controles;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class barraDesplazamiento extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            ScrollPane sp = new ScrollPane();
            Image imagen = new Image(getClass().getResourceAsStream("/imagenes/iconoCerveza.png"));
            sp.setContent(new ImageView(imagen));

            Scene escena = new Scene(sp, 500, 500);
            escenarioPrincipal.setTitle("Panel de desplazamiento");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

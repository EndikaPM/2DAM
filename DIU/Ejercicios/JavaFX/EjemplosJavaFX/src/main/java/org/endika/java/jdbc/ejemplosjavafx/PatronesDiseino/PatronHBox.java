package org.endika.java.jdbc.ejemplosjavafx.PatronesDiseino;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PatronHBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox(10);
            raiz.setPadding(new Insets(10));
            raiz.setAlignment(Pos.CENTER_RIGHT);

            Button bt1, bt2, bt3;
            bt1 = new Button("Botón 1");
            bt2 = new Button("Botón 2");
            bt3 = new Button("Botón 3");

            raiz.getChildren().addAll(bt1, bt2, bt3);

            Scene escena = new Scene(raiz, 300, 50);
            escenarioPrincipal.setTitle("Panel horizontal");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

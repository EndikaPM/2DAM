package org.endika.java.jdbc.ejemplosjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CampoTexto extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER_LEFT);

            Label lbNombre;
            lbNombre = new Label("Nombre:");
            Label lbContrasena;
            lbContrasena = new Label("Contraseña:");

            PasswordField pfContrasena = new PasswordField();

            TextField tfNombre = new TextField();

            raiz.getChildren().addAll(lbNombre, tfNombre, lbContrasena, pfContrasena);

            Scene escena = new Scene(raiz, 600, 100);
            escenarioPrincipal.setTitle("Campos de texto y contraseña");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

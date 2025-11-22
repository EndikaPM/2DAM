package com.example.campodetexto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;


public class Launcher extends Application {
    private Label lbTexto, lbInfo;
    private TextField tfNumerico;

    private void compruebaNumero(KeyEvent e) {
        String texto = tfNumerico.getText();
        int longitud = texto.length();
        if (e.getCharacter().matches("[0-9]?\\.?") || Character.isISOControl(e.getCharacter().charAt(0))) {
            longitud = (Character.isISOControl(e.getCharacter().charAt(0)) ? longitud : longitud + 1);
            lbInfo.setText("Longitud: " + longitud + " caracteres");
        }
        else {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        try {
            VBox raiz = new VBox(20);
            raiz.setPadding(new Insets(10));
            raiz.setAlignment(Pos.CENTER);

            HBox hbTexto =new HBox(30);
            hbTexto.setPadding(new Insets(10));
            hbTexto.setAlignment(Pos.CENTER);

            lbTexto = new Label("Introduce un número\n(entero o decimal)");
            lbTexto.setWrapText(true);
            lbTexto.setFont(Font.font("Arial", 14));
            tfNumerico = new TextField();
            tfNumerico.setOnKeyTyped(e -> compruebaNumero(e));
            hbTexto.getChildren().addAll(lbTexto, tfNumerico);

            lbInfo = new Label("Longitud: 0 caracteres");
            lbInfo.setFont(Font.font("Arial", 24));

            raiz.getChildren().addAll(hbTexto, lbInfo);

            Scene escena = new Scene(raiz, 450, 150);
            escenarioPrincipal.setTitle("Campo numérico");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

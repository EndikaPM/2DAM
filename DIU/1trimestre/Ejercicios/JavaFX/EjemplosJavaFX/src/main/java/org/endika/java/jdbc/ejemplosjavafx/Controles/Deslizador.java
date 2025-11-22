package org.endika.java.jdbc.ejemplosjavafx.Controles;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Deslizador extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);

            Label lbElige;
            lbElige = new Label("Elige el porcentaje:");
            lbElige.setFont(Font.font(20));

            Slider porcentaje = new Slider();
            porcentaje.setMin(0);
            porcentaje.setMax(100);
            porcentaje.setValue(50);
            porcentaje.setShowTickLabels(true);
            porcentaje.setShowTickMarks(true);
            porcentaje.setMajorTickUnit(30);
            //setMajorTickUnit esto es pra que divida el maximo y minime en saltos de los que le pongas
            porcentaje.setMinorTickCount(4);
            porcentaje.setBlockIncrement(10);
            porcentaje.setSnapToTicks(true);

            raiz.getChildren().addAll(lbElige, porcentaje);

            Scene escena = new Scene(raiz, 430, 100);
            escenarioPrincipal.setTitle("Deslizadores");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

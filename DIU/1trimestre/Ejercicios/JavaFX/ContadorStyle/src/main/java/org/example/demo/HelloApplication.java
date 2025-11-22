package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo.controller.HelloController;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String css = HelloApplication.class.getResource("/Style/StyleFMX.css").toExternalForm();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Parent root = fxmlLoader.load();
        Scene scene1 = new Scene(fxmlLoader.load());
        scene1.getStylesheets().add(css);
        HelloController controller = fxmlLoader.getController();
        stage.setScene(scene1);
        stage.setX(10);
        stage.show();


        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Parent root2 = fxmlLoader2.load();
        Scene scene2 = new Scene(fxmlLoader2.load());
        scene2.getStylesheets().add(css);
        HelloController controller2 = fxmlLoader2.getController();
        stage2.setScene(scene2);
        stage2.setX(30);
        stage2.show();

        controller.numPulsacionesProperty().bindBidirectional(controller2.numPulsacionesProperty());
        controller.initialaze();
        controller2.initialaze();

    }
}

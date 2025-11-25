package com.example.convertir.View;

import Modelo.ExcepcionMoneda;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.convertir.Controller.ConversorContoller;
import com.example.convertir.Model.MonedaModelo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    MonedaRepository monedaRepo;
    ConversorContoller conversorContoller;
    MonedaModelo monedaModel;

    @Override
    public void start(Stage stage) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/convertir/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Conversor!");
            stage.setScene(scene);

            monedaRepo = new MonedaRepositoryImpl();
            monedaModel = new MonedaModelo();
            monedaModel.setMonedaRepository(monedaRepo);
            conversorContoller = fxmlLoader.getController();
            conversorContoller.setMonedaModel(monedaModel);

            stage.show();
        }catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

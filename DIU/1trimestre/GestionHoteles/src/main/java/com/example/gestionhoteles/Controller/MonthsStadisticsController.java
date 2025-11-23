package com.example.gestionhoteles.Controller;

import com.example.gestionhoteles.Main;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class MonthsStadisticsController {
    @FXML
    private BarChart<String, Integer> stadisticMonths;
    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    private Main mainApp;

    @FXML
    public void initialize() {
        String[] months = DateFormatSymbols.getInstance(new Locale("es", "ES")).getMonths();
        monthNames.addAll(Arrays.asList(months).subList(0,12));
        xAxis.setCategories(monthNames);
    }

    public void setMainApp(Main mainApp) throws ExeptionReserva {
        this.mainApp = mainApp;
        setReservaData();
    }

    public void setReservaData() throws ExeptionReserva {

        int[] monthCounter = mainApp.getReserva().roomOcupationMonth();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();


        for (int i = 0; i < 12; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        stadisticMonths.getData().add(series);
    }
}

module com.example.ejemplo_monedas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires AccesoBBDDMoneda;

    opens com.example.ejemplo_monedas to javafx.fxml;
    exports com.example.ejemplo_monedas;
    exports com.example.ejemplo_monedas.Conversor;
    opens com.example.ejemplo_monedas.Conversor to javafx.fxml;
    exports com.example.ejemplo_monedas.controller;
    opens com.example.ejemplo_monedas.controller to javafx.fxml;
}
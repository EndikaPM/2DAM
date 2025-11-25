module com.example.preparacion1trimestre {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires Modelo;

    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;

    exports com.example.preparacion1trimestre.View;
    opens com.example.preparacion1trimestre.View to javafx.fxml;
    exports com.example.preparacion1trimestre.controller;
    opens com.example.preparacion1trimestre.controller to javafx.fxml;
}
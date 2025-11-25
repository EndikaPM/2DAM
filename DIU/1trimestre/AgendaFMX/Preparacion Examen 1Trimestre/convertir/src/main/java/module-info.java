module com.example.convertir {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires AccesoBBDDMoneda;
    requires java.sql;

    opens com.example.convertir to javafx.fxml;
    exports com.example.convertir.View;
    opens com.example.convertir.View to javafx.fxml;
    exports com.example.convertir.Controller;
    opens com.example.convertir.Controller to javafx.fxml;
}
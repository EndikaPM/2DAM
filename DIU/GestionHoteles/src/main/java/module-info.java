module com.example.gestionhoteles {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;


    opens com.example.gestionhoteles to javafx.fxml;
    exports com.example.gestionhoteles;
    exports com.example.gestionhoteles.Controller;
    opens com.example.gestionhoteles.Controller to javafx.fxml;
}
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
    requires com.example.gestionhoteles;

    opens com.example.gestionhoteles to javafx.fxml;
    exports com.example.gestionhoteles;
}
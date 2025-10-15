module com.example.contadormultiple {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;

    opens com.example.contadormultiple to javafx.fxml;
    exports com.example.contadormultiple;
}
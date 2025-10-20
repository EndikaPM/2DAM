module org.example.agendafmx {
    requires javafx.controls;
    requires javafx.fxml;


    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires controlsfx;
    requires java.sql;

    opens org.example.agendafmx to javafx.fxml;
    exports org.example.agendafmx;
    exports org.example.agendafmx.Controller;
    opens org.example.agendafmx.Controller to javafx.fxml;
}
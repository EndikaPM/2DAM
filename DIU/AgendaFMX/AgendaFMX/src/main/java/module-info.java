module org.example.agendafmx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens org.example.agendafmx to javafx.fxml;
    exports org.example.agendafmx;
    exports org.example.agendafmx.Controller;
    opens org.example.agendafmx.Controller to javafx.fxml;
}
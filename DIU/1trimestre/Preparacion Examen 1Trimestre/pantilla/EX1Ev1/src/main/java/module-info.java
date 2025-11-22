module com.example.ex1ev1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.ex1ev1 to javafx.fxml;
    exports com.example.ex1ev1;
    exports com.example.ex1ev1.View;
    opens com.example.ex1ev1.View to javafx.fxml;
    exports com.example.ex1ev1.Controller;
    opens com.example.ex1ev1.Controller to javafx.fxml;
}
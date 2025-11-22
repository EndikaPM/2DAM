module org.endika.java.jdbc.ejemplosjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;

    // opens org.endika.java.jdbc.ejemplosjavafx to javafx.fxml;
    //exports org.endika.java.jdbc.ejemplosjavafx;

    exports org.endika.java.jdbc.ejemplosjavafx.Controles;
    opens org.endika.java.jdbc.ejemplosjavafx.Controles to javafx.fxml;
    exports org.endika.java.jdbc.ejemplosjavafx.PatronesDiseino;
    opens org.endika.java.jdbc.ejemplosjavafx.PatronesDiseino to javafx.fxml;
    exports StilosInLIne;
    opens StilosInLIne to javafx.fxml;

}
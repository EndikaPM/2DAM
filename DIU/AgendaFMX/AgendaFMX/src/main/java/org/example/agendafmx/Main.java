package org.example.agendafmx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.agendafmx.Controller.PersonController;
import org.example.agendafmx.Controller.PersonEditDialogController;
import org.example.agendafmx.Model.ExceptionPersona;
import org.example.agendafmx.Model.Person;
import org.example.agendafmx.Model.PersonVO;
import org.example.agendafmx.Model.PersonaModel;
import org.example.agendafmx.Model.Repository.PersonaRepository;
import org.example.agendafmx.Model.Repository.impl.PersonaRepositorioImpl;
import org.example.agendafmx.util.PersonUtil;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private SimpleFloatProperty progressBar = new SimpleFloatProperty();
    private final int MAX_PERSON_AGENDA = 50;


    public SimpleFloatProperty getProgressProperty() {
        return progressBar;
    }

    public void updateProgressProperty() {
        this.progressBar.set((float)getPersonData().size() / MAX_PERSON_AGENDA);
    }

    @Override
    public void start(Stage primaryStage) throws ExceptionPersona {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        this.primaryStage.getIcons().add(new Image(getClass().getResource("/images/libretaPng.png").toExternalForm()));

        initRootLayout();
        showPersonOverview();

        getProgressProperty().addListener((observable, oldValue, newValue) ->{updateProgressProperty();});

        /*PersonaModel personaModel = new PersonaModel();
        PersonaRepository personaRepository = new PersonaRepositorioImpl();

        personaModel.setPersonaRepository(personaRepository);

        PersonVO personaVO = new PersonVO("Maria", "Comesaña", "Calle Silos 18", "41500", "Alcala de guadaira","2025-11-23");
        PersonVO personaVO2 = new PersonVO("Endika", "Perez", "Calle Silos 18", "41500", "Alcala de guadaira","2025-11-23");

        personaModel.addPersona(personaVO);
        personaModel.addPersona(personaVO2);*/
        //personaModel.deletePersona(3);
        //personaModel.deletePersona(4);
        //personaVO.setLastName("Perez");
        //personaVO.setId(1);
        //personaModel.updatePersona(personaVO);

        //System.out.println(personaModel.ObtenerListaPersona());
    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/root_layout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private static ObservableList<Person> personData = FXCollections.observableArrayList();

    public Main()  {
       try {
           PersonaModel personaModel = new PersonaModel();
           PersonaRepository personaRepositorio = new PersonaRepositorioImpl();
           personaModel.setPersonaRepository(personaRepositorio);
           ArrayList<PersonVO> personaVo = personaModel.ObtenerListaPersona();

           for (PersonVO persona : personaVo) {
               personData.add(PersonUtil.getPerson(persona));
           }

           updateProgressProperty();
       }catch (ExceptionPersona e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error al conectar con la base de datos");
           alert.setContentText("No ha sido posible obtener la lista de clientes.");
           alert.showAndWait();
       }
    }

    public static void main(String[] args) {
        launch(args);

    }

    public static ObservableList<Person> getPersonData() {
        return personData;
    }

    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/person_overview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person, String title) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}

package org.example.agendafmx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;
import org.example.agendafmx.Main;
import org.example.agendafmx.Model.ExceptionPersona;
import org.example.agendafmx.Model.Person;
import org.example.agendafmx.Model.PersonaModel;
import org.example.agendafmx.Model.Repository.impl.PersonaRepositorioImpl;
import org.example.agendafmx.util.DateUtil;
import org.example.agendafmx.util.PersonUtil;

import java.awt.*;


public class PersonController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private javafx.scene.control.Label firstNameLabel;
    @FXML
    private javafx.scene.control.Label lastNameLabel;
    @FXML
    private javafx.scene.control.Label streetLabel;
    @FXML
    private javafx.scene.control.Label postalCodeLabel;
    @FXML
    private javafx.scene.control.Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private Main mainApp;
    private PersonaRepositorioImpl personaRepositorio = new PersonaRepositorioImpl();
    private PersonaModel personaModel = new PersonaModel();



    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Clear person details.
        showPersonDetails(null);
        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    @FXML
    public void handleDeletePerson() throws ExceptionPersona {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        personaModel.setPersonaRepository(personaRepositorio);
        if (selectedIndex >= 0) {
            Person perElimi = personTable.getSelectionModel().getSelectedItem();
            int personID = perElimi.getIdProperty();
            personaModel.deletePersona(personID);
            personTable.getItems().remove(selectedIndex);
        }else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            // TODO: We need a way to convert the birthday into a String!
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() throws ExceptionPersona {
        Person tempPerson = new Person();
        personaModel.setPersonaRepository(personaRepositorio);
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            personaModel.addPersona(PersonUtil.getPerson(tempPerson));

            int lastId = personaRepositorio.lastId();
            tempPerson.setIdPorperty(lastId);
            System.out.println("id A eliminar "+lastId);
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() throws ExceptionPersona{
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
                personaModel.setPersonaRepository(personaRepositorio);
                personaModel.updatePersona(PersonUtil.getPerson(selectedPerson));
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }
}

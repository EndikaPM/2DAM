package org.example.agendafmx.Controller;

import javafx.fxml.FXML;
import org.example.agendafmx.Main;
import org.example.agendafmx.Model.Person;

import java.awt.*;

/**
 * Fills all text fields to show details about the person.
 * If the specified person is null, all text fields are cleared.
 *
 * @param person the person or null
 */
public class PersonController {
    private void showPersonDetails(Person person) {
        Label firstNameLabel = null;
        Label lastNameLabel = null;
        Label streetLabel= null;
        Label cityLabel = null;
        Label birthdayLabel  = null;
        Label postalCodeLabel = null;
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
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

    public void setMain(Main main) {
    }
}

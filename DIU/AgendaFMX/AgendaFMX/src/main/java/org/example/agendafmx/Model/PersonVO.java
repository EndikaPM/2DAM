package org.example.agendafmx.Model;

import java.time.LocalDate;

public class PersonVO {
    private int id;
    private String firstName;
    private String lastName;
    private String streetLabel;
    private String postalCode;
    private String city;
    private LocalDate birthDate;

    public PersonVO() {}
    public PersonVO(String firstName, String lastName, String streetLabel, String postalCode, String city, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName =  lastName;
        this.streetLabel = streetLabel;
        this.postalCode = postalCode;
        this.city = city;
        this.birthDate = birthDate;
    }
    public PersonVO(int id, String firstName, String lastName, String streetLabel, String postalCode, String city, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetLabel = streetLabel;
        this.postalCode = postalCode;
        this.city = city;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetLabel() {
        return streetLabel;
    }

    public void setStreetLabel(String streetLabel) {
        this.streetLabel = streetLabel;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String toString(){
        return firstName + " " + lastName + " " + streetLabel + " " + postalCode + " " + city;
    }
}

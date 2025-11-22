package org.example.agendafmx.util;

import org.example.agendafmx.Model.Person;
import org.example.agendafmx.Model.PersonVO;

public class PersonUtil {
    public static PersonVO getPerson(Person tempPerson) {
        PersonVO personVO = new PersonVO();
        personVO.setId(tempPerson.getIdProperty());
        personVO.setFirstName(tempPerson.getFirstName());
        personVO.setLastName(tempPerson.getLastName());
        personVO.setStreetLabel(tempPerson.getStreet());
        personVO.setCity(tempPerson.getCity());
        personVO.setPostalCode(Integer.toString(tempPerson.getPostalCode()));
        personVO.setBirthDate(tempPerson.getBirthday());
        return personVO;
    }

    public static Person getPerson(PersonVO tempPersonVO) {
        Person person = new Person();
        person.setIdPorperty(tempPersonVO.getId());
        person.setFirstName(tempPersonVO.getFirstName());
        person.setLastName(tempPersonVO.getLastName());
        person.setLastName(tempPersonVO.getLastName());
        person.setStreet(tempPersonVO.getStreetLabel());
        person.setCity(tempPersonVO.getCity());
        person.setPostalCode(Integer.parseInt(tempPersonVO.getPostalCode()));
        person.setBirthday(tempPersonVO.getBirthDate());

        return person;
    }
}

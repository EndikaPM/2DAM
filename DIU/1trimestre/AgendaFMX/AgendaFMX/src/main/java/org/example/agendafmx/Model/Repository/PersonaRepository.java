package org.example.agendafmx.Model.Repository;

import org.example.agendafmx.Model.ExceptionPersona;
import org.example.agendafmx.Model.Person;
import org.example.agendafmx.Model.PersonVO;

import java.util.ArrayList;

public interface PersonaRepository {
    ArrayList<PersonVO> ObtenerListaPersona() throws ExceptionPersona;

    void addPersona(PersonVO persona) throws ExceptionPersona;
    void deletePersona(int idPersona) throws ExceptionPersona;
    void updatePersona(PersonVO persona) throws ExceptionPersona;
    int lastId() throws ExceptionPersona;
}

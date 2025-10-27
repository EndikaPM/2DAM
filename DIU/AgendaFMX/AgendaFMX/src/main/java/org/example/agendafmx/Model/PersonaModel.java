package org.example.agendafmx.Model;

import org.example.agendafmx.Model.Repository.PersonaRepository;

import java.util.ArrayList;

public class PersonaModel {
    private PersonaRepository personaRepository;

    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public ArrayList<PersonVO> ObtenerListaPersona() throws ExceptionPersona{
        return personaRepository.ObtenerListaPersona();
    }
    public void addPersona(PersonVO personVO) throws ExceptionPersona {
        this.personaRepository.addPersona(personVO);
    }
    public void deletePersona(int idPersona) throws ExceptionPersona{
        this.personaRepository.deletePersona(idPersona);
    }

    public void updatePersona(PersonVO personVO) throws ExceptionPersona{
        this.personaRepository.updatePersona(personVO);
    }
    public int lastId() throws ExceptionPersona{
    return this.personaRepository.lastId();
    }

}




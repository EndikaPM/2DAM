package main.personas.controller;

import main.personas.model.PersonaDto;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface PersonaAPI {
    List<PersonaDto> getAllPersonas();
    Optional<PersonaDto> getPersonaById(int id);
    Optional<PersonaDto> findByNombreContaining(@PathVariable String nombre);
    PersonaDto save(PersonaDto personaDto);
    PersonaDto updatePersona(PersonaDto personaDto, int id);
    ResponseEntity deletePersona(int id);
    ResponseEntity deleteAllPersosnas();
}

package main.personas.service;

import main.personas.model.PersonaDto;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;


public interface PersonaService {
    List<PersonaDto> getAllPersonas(); //funciona
    Optional<PersonaDto> getPersonaById(int id); //funciona
    Optional<PersonaDto> findByNameContaining(String nombre);
    PersonaDto save(PersonaDto tutorial); //funciona
    PersonaDto updatePersona(PersonaDto personaDto); //funciona
    ResponseEntity deletePersona(int id); //funciona
    ResponseEntity deleteAllPersonas(); //funciona
}

package main.personas.controller.impl;

import main.personas.controller.PersonaAPI;
import main.personas.model.PersonaDto;
import main.personas.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class PersonaContollerImpl implements PersonaAPI {

    @Autowired
    private PersonaService personaService;

    @Override
    @GetMapping("/persona")
    public List<PersonaDto> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @Override
    @GetMapping("/persona/{id}")
    public Optional<PersonaDto> getPersonaById(@PathVariable int id) {
        return personaService.getPersonaById(id);
    }


    @Override
    @GetMapping("/persona/nombre/{nombre}")
    public Optional<PersonaDto> findByNombreContaining(@PathVariable String nombre) {
        return personaService.findByNameContaining(nombre);
    }

    @Override
    @PostMapping("/persona")
    public PersonaDto save(PersonaDto personaDto) {
        return personaService.save(personaDto);
    }

    @Override
    @PutMapping("/persona/{id}")
    public PersonaDto updatePersona(@RequestBody PersonaDto personaDto, @PathVariable int id) {
        // Aquí deberías asegurarte de que el ID del DTO sea el mismo que el del Path
        return personaService.updatePersona(personaDto);
    }

    @Override
    @DeleteMapping("/persona/{id}")
    public ResponseEntity deletePersona(int id) {
        return personaService.deletePersona(id);
    }

    @Override
    @DeleteMapping("/persona")
    public ResponseEntity deleteAllPersosnas() {
        return personaService.deleteAllPersonas();
    }
}

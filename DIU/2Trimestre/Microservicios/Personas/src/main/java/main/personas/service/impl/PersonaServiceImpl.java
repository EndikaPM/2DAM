package main.personas.service.impl;

import main.personas.model.Persona;
import main.personas.model.PersonaDto;
import main.personas.repository.PersonaRepository;
import main.personas.service.PersonaService;
import main.personas.util.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<PersonaDto> getAllPersonas() {
        List<Persona> personaList = personaRepository.findAll();
        return personaList.stream()
                .map(PersonaMapper::personaMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonaDto> getPersonaById(int id) {
        Optional<Persona> personaOptional = personaRepository.getPersonaById(id);

        return personaOptional.map(PersonaMapper::personaMapperEntityToDto);
    }

    @Override
    public Optional<PersonaDto> findByNameContaining(String nombre) {
        Optional<Persona> personaOptional = personaRepository.findByNombreCompleto(nombre);

        return personaOptional.map(PersonaMapper::personaMapperEntityToDto);
    }

    @Override
    public PersonaDto save(PersonaDto personaDto) {
        Persona tutorials = PersonaMapper.personaMapperDtoToEntity(personaDto);
        Persona savedTutorialEntity = personaRepository.save(tutorials);
        return PersonaMapper.personaMapperEntityToDto(savedTutorialEntity);
    }

    @Override
    public PersonaDto updatePersona(PersonaDto personaDto) {
        Optional<Persona> existingPersonaOptional = personaRepository.getPersonaById(personaDto.getId());

        if (existingPersonaOptional.isPresent()) {
            Persona existingPersona = existingPersonaOptional.get();
            existingPersona.setNombreCompleto(personaDto.getNombreCompleto());
            existingPersona.setImagen(personaDto.getImagen());
            existingPersona.setIdTutoriales(personaDto.getIdTutoriales());

            Persona updatedTutorial = personaRepository.save(existingPersona);

            return PersonaMapper.personaMapperEntityToDto(updatedTutorial);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity deletePersona(int id) {
        try {
            Optional<Persona> existingPersonaOptional = personaRepository.getPersonaById(id);
            if (existingPersonaOptional.isPresent()) {
                personaRepository.deleteById(id);
                return ResponseEntity.ok("Persona eliminada exitosamente!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar a la persona");
        }
    }

    @Override
    public ResponseEntity deleteAllPersonas() {
        personaRepository.deleteAll();
        ResponseEntity.ok("Persona eliminada exitosamente!");
        return ResponseEntity.ok().build();
    }
}

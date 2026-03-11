package main.personas.repository;

import main.personas.model.Persona;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends MongoRepository <Persona,Integer> {
    List<Persona> findAll();
    Optional<Persona> getPersonaById(int id);
    Optional<Persona> findByNombreCompleto(String nombreCompleto);
}

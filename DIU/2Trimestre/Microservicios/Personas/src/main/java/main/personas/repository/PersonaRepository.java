package main.personas.repository;

import main.personas.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository <Persona,String> {
}

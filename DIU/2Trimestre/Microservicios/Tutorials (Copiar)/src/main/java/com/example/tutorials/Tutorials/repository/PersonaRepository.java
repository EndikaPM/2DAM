package com.example.tutorials.Tutorials.repository;

import com.example.tutorials.Tutorials.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<Persona,String> {
}

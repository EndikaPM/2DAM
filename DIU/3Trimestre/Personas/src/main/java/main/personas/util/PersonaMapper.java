package main.personas.util;

import main.personas.model.Persona;
import main.personas.model.PersonaDto;

import java.util.List;
import java.util.stream.Collectors;

public class PersonaMapper {
    public static Persona personaMapperDtoToEntity(PersonaDto personaDto){
        return Persona.builder()
                .id(personaDto.getId())
                .nombreCompleto(personaDto.getNombreCompleto())
                .imagen(personaDto.getImagen())
                .idTutoriales(personaDto.getIdTutoriales())
                .build();
    }

    public static PersonaDto personaMapperEntityToDto(Persona persona){
        return PersonaDto.builder()
                .id(persona.getId())
                .nombreCompleto(persona.getNombreCompleto())
                .imagen(persona.getImagen())
                .idTutoriales(persona.getIdTutoriales())
                .build();
    }


    public static List<Persona> tutorialsListMapperDtoToEntity(List<PersonaDto> personaDtoList) {
        return personaDtoList.stream()
                .map(PersonaMapper::personaMapperDtoToEntity)
                .collect(Collectors.toList());
    }


    public static List<PersonaDto> tutorialsListMapperEntityToDto(List<Persona> personaList) {
        return personaList.stream()
                .map(PersonaMapper::personaMapperEntityToDto)
                .collect(Collectors.toList());
    }
}

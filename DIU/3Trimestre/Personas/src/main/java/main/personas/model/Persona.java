package main.personas.model;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personas")
@Getter
@Setter
@Builder
public class Persona {
    private int id;
    private String nombreCompleto;
    private String imagen;
    private int idTutoriales[];
}

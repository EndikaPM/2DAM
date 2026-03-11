package main.personas.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class PersonaDto {
    @Id
    private int id;
    private String nombreCompleto;
    private String imagen;
    private int idTutoriales [];
}

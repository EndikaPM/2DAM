package main.personas.model;
import lombok.*;

@Getter
@Setter
@Builder
public class Persona {
    private int id;
    private String nombreCpmpleto;
    private String imagen;
}

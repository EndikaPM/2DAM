package com.example.Tutorials.Model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "tutorials")
public class TutoarialsVO {

    private String id;
    private  String title;
    private String description;
    private boolean published = false;


}

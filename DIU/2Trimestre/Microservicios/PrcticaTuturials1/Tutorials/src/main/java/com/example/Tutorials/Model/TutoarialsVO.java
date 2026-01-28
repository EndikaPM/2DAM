package com.example.Tutorials.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



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

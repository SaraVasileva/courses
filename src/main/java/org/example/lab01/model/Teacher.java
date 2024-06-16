package org.example.lab01.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Location location;

    public Teacher(){}

    public Teacher(String name, String surname, Location location) {
        this.name = name;
        this.surname = surname;
        this.location = location;
    }
}

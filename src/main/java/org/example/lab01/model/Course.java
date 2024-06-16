package org.example.lab01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CourseType type;
    @ManyToOne
    private Teacher teacher;
    private Integer numStudents;

    public Course(){}
    public Course(String name, CourseType type, Teacher teacher, Integer numStudents) {
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.numStudents = numStudents;
    }
}

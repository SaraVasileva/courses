package org.example.lab01.model.dto;

import lombok.Data;
import org.example.lab01.model.CourseType;

@Data
public class CourseDto {

    private String name;
    private CourseType type;
    private Long teacher;
    private Integer numStudents;

}

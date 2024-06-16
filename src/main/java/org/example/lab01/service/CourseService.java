package org.example.lab01.service;

import org.example.lab01.model.Course;
import org.example.lab01.model.CourseType;

import java.util.List;

public interface CourseService {

    List<Course> listAll();
    Course findById(Long id);
    void deleteById(Long id);
    Course create(String name, CourseType type, Long host, Integer numStudents);
    Course update(Long id, String name, CourseType type, Long host, Integer numStudents);
    void mark(Long id);
    void book();


}

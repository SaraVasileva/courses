package org.example.lab01.service;

import org.example.lab01.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> listAll();
    Teacher findById(Long id);
    void deleteById(Long id);
    Teacher create(String name, String surname, Long countryId);
    Teacher update(Long id, String name, String surname, Long countryId);
}
